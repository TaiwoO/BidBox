const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const bookSchema = new Schema({

    name: {
        type: String,
        required: true,
        index: true
    },
    version: String,
    condition: String,
    isbn: String,
    img: Buffer,
    imgUrl: String,
    price: Number

});

module.exports = mongoose.model('Book', bookSchema); // compile schema into a Model
