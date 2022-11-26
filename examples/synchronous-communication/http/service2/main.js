import { Server } from 'http';
import request from 'request';
import { setDefaultResultOrder } from 'dns';
setDefaultResultOrder('ipv4first');

const server = new Server();

server.on('request', (req, res) => {
  request('http://localhost:8080/users/1', (err, resonse, body) => {
    console.log(body);
    res.end(body)
  });
});

server.listen(8081);
