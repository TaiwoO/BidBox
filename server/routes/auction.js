const express = require('express');
const router = express.Router();
const auctionCtrl = require('../controllers/auctionCtrl');

router.get('/', auctionCtrl.getAll);

module.exports = router;