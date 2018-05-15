const express = require('express');
const router = express.Router();
const auctionCtrl = require('../controllers/auctionCtrl');

router.get('/', auctionCtrl.find);
router.get('/all', auctionCtrl.getAll);
router.get('/:auctionid', auctionCtrl.getAuctionById);
router.get('/:auctionid/bid', auctionCtrl.getBids);

module.exports = router;