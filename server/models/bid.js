const mongoose = require('mongoose');
const Schema = mongoose.Schema;


const bidSchema = new Schema({
    bidder: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    price: Number
});

module.exports = mongoose.model('Bid', bidSchema); // compile schema into a Model
