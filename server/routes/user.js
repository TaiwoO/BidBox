const express = require('express');
const router = express.Router();
const passport = require('passport');
const userCtrl = require('../controllers/userCtrl');

const requireJwtAuth = passport.authenticate('jwt', { session: false });


router.get('/', function (req, res, next) {
  res.send("welcome to the user's route");
});

router.get('/:userid', userCtrl.getUser);
router.get('/:userid/auction', userCtrl.getAuctions);
router.get('/:userid/bid', userCtrl.getBids);
router.post('/auction', requireJwtAuth, userCtrl.addAuction);
router.post('/bid', requireJwtAuth, userCtrl.addBid);
router.delete('/auction/:auctionid', requireJwtAuth, userCtrl.deleteAuction);
router.delete('/bid/:bidid', requireJwtAuth, userCtrl.deleteBid);
router.put('/auction/:auctionid', requireJwtAuth, userCtrl.updateAuction);
router.put('/bid/:bidid', requireJwtAuth, userCtrl.updateBid);

module.exports = router;
