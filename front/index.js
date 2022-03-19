const express = require('express');
const app = express();
const path = require('path');
const router = express.Router();

app.use('/scripts', express.static(__dirname + '/scripts'));
app.use('/assets', express.static(__dirname + '/assets'));
app.use('/styles', express.static(__dirname + '/styles'));

router.get('/', function (req, res) {
  res.sendFile(path.join(__dirname + '/pages/index.html'));
});

router.get('/list', function (req, res) {
  res.sendFile(path.join(__dirname + '/pages/list.html'));
});

router.get('/new', function (req, res) {
  res.sendFile(path.join(__dirname + '/pages/new.html'));
});

router.get('/stats', function (req, res) {
  res.sendFile(path.join(__dirname + '/pages/stats.html'));
});

router.get('*', function (req, res) {
  res.sendFile(path.join(__dirname + '/pages/404.html'));
});

app.use('/', router);

app.listen(process.env.port || 3000);

console.log('Running at Port 3000');

