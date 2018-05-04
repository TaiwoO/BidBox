const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const auctionSchema = new Schema({

    bookid: {
        type: Schema.Types.ObjectId,
        ref: 'Book',
        required: true
    },
    userid: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    bids: [{
        type: Schema.Types.ObjectId,
        ref: 'Bid'
    }],
    startDate: Date || Date.now,
    endDate: Date
});

module.exports = mongoose.model('Auction', auctionSchema); // compile schema into a Model
