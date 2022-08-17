const port = process.env.PORT || 8080;

var express = require('express');
var app = express();

app.get('/example/message/get', function (req, res) {
    console.log("Consuming method: /example/message/get")
    res.statusCode = 200;
    res.statusMessage = "Successful"
    const msg = 'Hello World!\n'
    res.end(msg);
})

var server = app.listen(port, function () {
    console.log("Example app listening at http://%s:%s%s", "localhost", port, "/example/message/get")
})