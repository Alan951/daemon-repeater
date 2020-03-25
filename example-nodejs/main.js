var net = require('net');

var host = 'localhost';
var port = 951;

const client = net.createConnection({host: host, port: port}, () => {
    console.log('connected to DaemonRepeater');

    sendRequest();
});

function sendData(jsonData){
    client.write(Buffer.from(JSON.stringify(jsonData)).toString('base64')+'\n');
}

function sendRequest(){
    let message = {
        host: "google.com",
        port: 443,
        useHttps: true,
        request: "GET / HTTP/1.1\nHost: www.google.com\nUser-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0\nAccept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\nAccept-Language: es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3\nAccept-Encoding: gzip, deflate\nUpgrade-Insecure-Requests: 1\nConnection: close\n\n",
        tabName: "Google Test"
    }

    sendData(message);
    console.log('Request sended!');

    client.destroy();
}