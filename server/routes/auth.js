const passport = require('passport');

const express = require('express');
const authCtrl = require('../controllers/authCtrl');


const router = express.Router();
const requireLoginMiddleware = passport.authenticate('local', { session: false });

router.get('/', function (req, res, next) {
    res.send("welcome to the auth route");
});

router.post('/register', authCtrl.register);
router.post('/login', requireLoginMiddleware, authCtrl.login);

module.exports = router;