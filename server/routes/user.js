const express = require('express');
const router = express.Router();
const userCtrl = require('../controllers/userCtrl');


router.get('/', function (req, res, next) {
  res.send("welcome to the user's route");
});

router.post('/book', userCtrl.addBook);
router.delete('/book/:bookid', userCtrl.deleteBook);
router.get('/:userid', userCtrl.getUser);
router.get('/:userid/book', userCtrl.getBooks);
router.post('/auction', userCtrl.addAuction);
router.delete('/auction/:auctionid', userCtrl.deleteAuction);
router.put('/auction/:auctionid', userCtrl.updateAuction);


module.exports = router;
