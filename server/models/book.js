const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const bookSchema = new Schema({

    name: {
        type: String,
        required: true,
        index: true
    },
    owner: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    version: String,
    condition: String,
    img: Buffer,
    imgUrl: String,

});

module.exports = mongoose.model('Book', bookSchema); // compile schema into a Model
