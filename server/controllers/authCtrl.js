const jwt = require('jsonwebtoken');
const config = require('../config/config-constants');
const User = require('../models/user');

/**
 *  ============================================ HELPER FUNCTIONS ====================================================
 */

function _isValidateEmail(email) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

function _sendJsonResponse(res, status, content) {
    res.status(status);
    res.json(content);
}

function _generateUserToken(user) {
    let publicData = User.getPublicInfo(user); // dont allow private data inside the jwt for security purpses
    return jwt.sign(publicData, config.SECRET, {
        expiresIn: 86400 * 30 // in seconds
    });
}
// ========================================================


/**
 *  ============================================ REQUEST INTERCEPTIONS ====================================================
 */

function register(req, res, next) {
    const name = req.body.name;
    const email = req.body.email;
    const password = req.body.password;

    if (!email) { return _sendJsonResponse(res, 422, { "message": "No email provided" }); }
    if (!_isValidateEmail(email)) { return _sendJsonResponse(res, 422, { "message": "Please enter a valid email" }); }
    if (!password) { return _sendJsonResponse(res, 422, { "message": "No password provided" }); }

    User.findOne({ email: email }, (err, user) => {

        if (err) { return next(err) }
        if (user) { return _sendJsonResponse(res, 422, { "message": "email already exisits" }); }

        let newUser = new User({
            name: name,
            email: email,
            password: password
        });

        newUser.save((err, user) => {

            if (err) { return next(err) }

            let publicUserInfo = User.getPublicInfo(user);
            _sendJsonResponse(res, 201, {
                token: 'JWT ' + _generateUserToken(publicUserInfo),
                user: publicUserInfo
            });
        });
    });
};

function login(req, res) {
    // No validation required since it is already hanndled in password's local login stragtegy

    const publicUserInfo = User.getPublicInfo(req.user);

    _sendJsonResponse(res, 200, {
        token: 'JWT' + _generateUserToken(publicUserInfo),
        user: publicUserInfo
    });
};

// ========================================================


module.exports = {
    register: register,
    login: login
}