const express = require('express');
const router = express.Router();
const userCtrl = require('../controllers/userCtrl');


router.get('/', function (req, res, next) {
  res.send("welcome to the user's route");
});

router.get('/:userid', userCtrl.getUser);
router.get('/:userid/auction', userCtrl.getAuctions);
router.get('/:userid/bid', userCtrl.getBids);
router.post('/auction', userCtrl.addAuction);
router.post('/bid', userCtrl.addBid);
router.delete('/auction/:auctionid', userCtrl.deleteAuction);
router.delete('/bid/:bidid', userCtrl.deleteBid);
router.put('/auction/:auctionid', userCtrl.updateAuction);
router.put('/bid/:bidid', userCtrl.updateBid);

module.exports = router;
