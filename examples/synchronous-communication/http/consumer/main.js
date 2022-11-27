import { Server, request } from 'http';
import { setDefaultResultOrder } from 'dns';
setDefaultResultOrder('ipv4first');

const getDurationInMs = (start) => {
  const NS_PER_SEC = 1e9;
  const NS_TO_MS = 1e6;
  const diff = process.hrtime(start);
  return (diff[0] * NS_PER_SEC + diff[1]) / NS_TO_MS;
}

const server = new Server();

server.on('request', (req, res) => {
  if(req.url == '/favicon.ico') return;

  console.log(`${req.method} ${req.url} [STARTED]`)
  const start = process.hrtime()

  const options = new URL('http://localhost:8080/users/1');

  request(options, (response) => {
    let body = '';
    response.on('data', (chunk) => body += chunk);
    response.on('end', () => {
      res.end(body);
      const durationInMilliseconds = getDurationInMs(start);
      console.log(`${req.method} ${req.url} [FINISHED] ${durationInMilliseconds.toLocaleString()} ms`);
    });
  }).end();
});

server.listen(8081);
