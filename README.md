# daemon-repeater
DaemonRepeater es un plugin para BurpSuite que ayuda a enviar peticiones a la herramienta Repeater de Burp de manera automatica.

DaemonRepeater pone a la escucha un socket por el puerto 951 el cual esta a la espera de peticiones las cuales seran enviadas a Repeater.

### Como Instalarlo
Primero debes clonar este repositorio
 
     git clone https://github.com/Alan951/daemon-repeater.git

Despu�s deber�s compilarlo haciendo uso de ant

     ant main

Finalmente, deber�s de importar el plugin `dist/DaemonRepeater.jar` en Burp. 

    BurupSuite -> Extender tab -> Add option -> Extension file (.jar).  

### Como usarlo
Cuando el servicio DaemonRepeater este inicializado, empezara a recibir los mensajes los cuales deber�n de ser en formato JSON codificados en base64.

Ejemplo de uso con NodeJS:

```

var net = require('net');

var host = 'localhost'
var port = 951

var client = net.createConn[...]

let message = {
    host: "google.com",
    port: 443,
    useHttps: true,
    request: "GET / HTTP/1.1\nHost: www.google.com\nUser-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0\nAccept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\nAccept-Language: es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3\nAccept-Encoding: gzip, deflate\nUpgrade-Insecure-Requests: 1\nConnection: close\n\n",
    tabName: "Google Test"
}

client.write(Buffer.from(JSON.stringify(message)).toString('base64')+'\n');

```

<p align="center">
  <img width="780" src="https://raw.githubusercontent.com/Alan951/daemon-repeater/master/images/burp-req.PNG">
</p>

