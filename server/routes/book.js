const express = require('express');
const router = express.Router();
const bookCtrl = require('../controllers/bookCtrl');

router.get('/', function (req, res, next) {
    res.send("welcome to the book's route");
  });

router.get('/search/', bookCtrl.search);
router.get('/search/:bookid', bookCtrl.searchById);


module.exports = router;