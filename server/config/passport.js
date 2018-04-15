const passport = require('passport');
const LocalStrategy = require('passport-local');
const User = require('../models/user');

const localStrategyOptions = { usernameField: 'email' };

// Setting up local login strategy
const localLogin = new LocalStrategy(localStrategyOptions, function (email, password, done) {
    User.findOne({ email: email }, function (err, user) {
        if (err) { return done(err); }
        if (!user) { return done(null, false, { message: 'Incorrect email or password.' }); }

        user.validatePassword(password, function (err, isMatch) {
            if (err) { return done(err); }
            if (!isMatch) { return done(null, false, { message: 'Incorrect email or password.' }); }

            return done(null, user);
        });
    });
});

passport.use(localLogin);