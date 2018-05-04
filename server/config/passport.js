const passport = require('passport');
const LocalStrategy = require('passport-local');
const User = require('../models/user');
const ExtractJwt = require('passport-jwt').ExtractJwt;
const JwtStrategy = require('passport-jwt').Strategy;
const config = require('./config-constants');


const localStrategyOptions = { usernameField: 'email' };

// Setting up local login strategy
const localLoginStrategy = new LocalStrategy(localStrategyOptions, function (email, password, done) {
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

const jwtStrategyOptions = {
    // Telling Passport to check authorization headers for JWT
    jwtFromRequest: ExtractJwt.fromAuthHeaderWithScheme("jwt"), // FIXME: THis might need to be ExtractJwt.fromAuthHeaderWithScheme("jwt")
    // Telling Passport where to find the secret
    secretOrKey: config.SECRET
};
// Setting up JWT login strategy
const jwtValidationStrategy = new JwtStrategy(jwtStrategyOptions, function (payload, done) {
    // NOTE: might be payload.doc._id or payload.document._id
    User.findById(payload._id, function (err, user) {
        if (err) { return done(err, false); }

        if (user) {
            done(null, user);
        } else {
            done(null, false);
        }
    });
});

passport.use(localLoginStrategy);
passport.use(jwtValidationStrategy);
