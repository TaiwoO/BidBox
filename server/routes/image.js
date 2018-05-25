const fs = require('fs');
const path = require('path');
const configConstants = require('../config/config-constants');
const express = require('express');
const router = express.Router();

router.get('/:imgid', (req, res) => {
    const imgid = req.params.imgid;
    
    const file_path= path.join( configConstants.UPLOAD_PATH, imgid)

    if (!fs.existsSync(file_path)) {
        console.log("File doesn't exisit")
        res.status(404);
        res.json({message: "image doesnt exisit"});
        return
    }

    fs.createReadStream(file_path).pipe(res);

});

module.exports = router;