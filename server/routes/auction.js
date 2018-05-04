const express = require('express');
const router = express.Router();
const auctionCtrl = require('../controllers/auctionCtrl');

router.get('/', auctionCtrl.getAll);
router.get('/:auctionid', auctionCtrl.getAuction);
router.get('/:auctionid/bid', auctionCtrl.getBids);

module.exports = router;