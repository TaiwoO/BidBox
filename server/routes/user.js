const express = require('express');
const router = express.Router();
const passport = require('passport');
const multer  = require('multer');
const userCtrl = require('../controllers/userCtrl');
const config = require('../config/config-constants');

const requireJwtAuth = passport.authenticate('jwt', { session: false });
const upload = multer({dest: config.UPLOAD_PATH})

router.get('/', function (req, res, next) {
  res.send("welcome to the user's route");
});

router.get('/:userid', userCtrl.getUser);
router.get('/:userid/auction', userCtrl.getAuctions);
router.get('/:userid/bid', userCtrl.getBids);
router.get('/:userid/shoppingchart', userCtrl.getShoppingChart);
router.post('/:userid/shoppingchart', requireJwtAuth, userCtrl.addToShoppingChart);
router.post('/auction', requireJwtAuth, upload.single('image'), userCtrl.addAuction);
router.post('/auction/:auctionid/bid', requireJwtAuth, userCtrl.addBid);
router.delete('/auction/:auctionid', requireJwtAuth, userCtrl.deleteAuction);
router.delete('/bid/:bidid', requireJwtAuth, userCtrl.deleteBid);
router.put('/auction/:auctionid', requireJwtAuth, userCtrl.updateAuction);
router.put('/bid/:bidid', requireJwtAuth, userCtrl.updateBid);

module.exports = router;
