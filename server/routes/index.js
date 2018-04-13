const express = require('express');
const router = express.Router();

/* GET home page. */
router.get('/', function (req, res, next) {
  res.render('index', { title: 'BidBox' });
});

/* GET Test */
router.get('/test', function (req, res, next) {
  res.send({ message: "working!" });
});


module.exports = router;
