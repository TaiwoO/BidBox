const fs = require('fs');
const path = require('path');
const configConstants = require('../config/config-constants');
const express = require('express');
const router = express.Router();

router.get('/:imgid', (req, res) => {
    const imgid = req.params.imgid;
    // console.log(path.join(configConstants.UPLOAD_PATH, imgid));
    fs.createReadStream(path.join(configConstants.UPLOAD_PATH, imgid)).pipe(res);

    // res.sendFile(path.join(configConstants.UPLOAD_PATH, imgid))
});

module.exports = router;