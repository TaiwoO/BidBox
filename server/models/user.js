const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const crypto = require('crypto');
const jwt = require('jsonwebtoken');
const config = require('../config/config-constants')

const userSchema = new Schema({
    username: {
        type: String,
        unique: true,
        required: true
    },
    email: {
        type: String,
        unique: true,
    },
    books: [{
        type: Schema.Types.ObjectId,
        ref: 'Book'
    }],
    auctions: [{
        type: Schema.Types.ObjectId,
        ref: 'Auction'
    }],

    hash: String,
    salt: String
});

userSchema.methods.setPassword = (password) => {
    this.salt = crypto.randomBytes(16).toString('hex');
    this.hash = crypto.pbkdf2Sync(password, this.salt, 1000, 64, 'sha512').toString('hex');
}

userSchema.methods.isValidPassword = (password) => {
    let passwordHash = crypto.pbkdf2Sync(password, this.salt, 1000, 64, 'sha512').toString('hex');
    return this.hash === passwordHash;
}

userSchema.methods.generateJwt = () => {
    let expiry = new Date();
    expiry.setDate(expiry.getDate() + 31);

    return jwt.sign({
        _id: this._id,
        username: this.username,
        expiration: parseInt(expiry.getTime() / 1000)
    }, config.SECRET);
}

module.exports = mongoose.model('User', userSchema); // compile schema into a Model