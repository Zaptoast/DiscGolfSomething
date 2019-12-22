console.log('hello, node!');

var http = require('http');

http.createServer(function (req, res) {
  res.writeHead(200, {'Content-Type': 'text/html'});
  res.end('Hello from Skill Distillery!');
}).listen(3000);
