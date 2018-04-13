const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const auctionSchema = new Schema({

    book: {
        type: Schema.Types.ObjectId,
        ref: 'Book'
    },
    seller: {
        type: Schema.Types.ObjectId,
        ref: 'User'
    },
    bids: [{
        type: Schema.Types.ObjectId,
        ref: 'Bid'
    }],
    startDate: Date,
    endDate: Date
});

mongoose.model('Auction', auctionSchema); // compile schema into a Model
