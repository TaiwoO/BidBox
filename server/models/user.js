const mongoose = require('mongoose');
const Schema = mongoose.Schema;
// const crypto = require('crypto');
const bcrypt = require('bcryptjs');

const userSchema = new Schema({
    // username: {
    //     type: String,
    //     unique: true,
    //     required: true
    // },
    name: String,
    email: {
        type: String,
        unique: true,
    },
    // books: [{
    //     type: Schema.Types.ObjectId,
    //     ref: 'Book'
    // }],
    auctions: [{
        type: Schema.Types.ObjectId,
        ref: 'Auction'
    }],
    bids: [{
        type: Schema.Types.ObjectId,
        ref: 'Bid'
    }],
    shoppingChart: [{
        type: Schema.Types.ObjectId,
        ref: 'Book'
    }],
    password: {
        type: String,
        required: true
    }
},
    {
        timestamps: true
    });

// Pre-save of user to database, hash password if password is modified or new

userSchema.pre('save', function (next) {
    const user = this;
    const SALT_FACTOR = 5;

    if (!user.isModified('password'))
        return next();

    bcrypt.genSalt(SALT_FACTOR, function (err, salt) {
        if (err) return next(err);

        bcrypt.hash(user.password, salt, function (err, hash) {
            if (err) return next(err);
            user.password = hash;
            next();
        });
    });
});


userSchema.methods.validatePassword = function (candidatePassword, cb) {
    bcrypt.compare(candidatePassword, this.password, function (err, isMatch) {
        if (err) { return cb(err); }

        cb(null, isMatch);
    });
}

userSchema.statics.getPublicInfo = function (user) { // static method
    return {
        _id: user._id,
        // username: user.username,
        name: user.name,
        email: user.email,
        books: user.books,
        auctions: user.auctions,
        bids: user.bids,
        shoppingChart: user.shoppingChart
    };
}

module.exports = mongoose.model('User', userSchema); // compile schema into a Model 