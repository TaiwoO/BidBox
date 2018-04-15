const express = require('express');
const router = express.Router();
const authCtrl = require('../controllers/authCtrl');

router.get('/', function (req, res, next) {
    res.send("welcome to the auth route");
});

router.post('/register', authCtrl.register);
router.post('/login', authCtrl.login);

module.exports = router;