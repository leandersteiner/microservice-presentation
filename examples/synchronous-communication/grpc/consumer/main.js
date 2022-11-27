import * as grpc from '@grpc/grpc-js';
import * as loader from '@grpc/proto-loader';

import util from "util";
import { Server } from 'http';
import { fileURLToPath } from 'url';
import { dirname } from 'path';
import { setDefaultResultOrder } from 'dns';
setDefaultResultOrder('ipv4first');

const getDurationInMs = (start) => {
  const NS_PER_SEC = 1e9;
  const NS_TO_MS = 1e6;
  const diff = process.hrtime(start);
  return (diff[0] * NS_PER_SEC + diff[1]) / NS_TO_MS;
}

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const pkg_def = loader.loadSync(__dirname + '/../proto/user.proto');
const user = grpc.loadPackageDefinition(pkg_def).user;
const PORT = process.env.PORT || 3000;
const TARGET = process.env.TARGET || 'localhost:4000';

const client = new user.UserService(
  TARGET,
  grpc.credentials.createInsecure()
);

const getUser = util.promisify(client.getUser.bind(client));

const server = new Server();

server.on('request', async (req, res) => {
  if(req.url == '/favicon.ico') return;

  console.log(`${req.method} ${req.url} [STARTED]`)
  const start = process.hrtime()

  const body = await getUser({});
  res.end(JSON.stringify(body));
  const durationInMilliseconds = getDurationInMs(start);
  console.log(`${req.method} ${req.url} [FINISHED] ${durationInMilliseconds.toLocaleString()} ms`);
});

server.listen(PORT);
