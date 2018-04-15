const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const auctionSchema = new Schema({

    book: {
        type: Schema.Types.ObjectId,
        ref: 'Book',
        required: true
    },
    seller: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    bids: [{
        type: Schema.Types.ObjectId,
        ref: 'Bid'
    }],
    startDate: Date,
    endDate: Date
});

module.exports = mongoose.model('Auction', auctionSchema); // compile schema into a Model
