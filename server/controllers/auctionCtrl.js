const Auction = require('../models/auction');

/**
 *  ============================================ HELPER FUNCTIONS ====================================================
 */


function _sendJsonResponse(res, status, content) {
    res.status(status);
    res.json(content);
}

/**
 *  ============================================ REQUEST INTERCEPTIONS ====================================================
 */

//TODO:
function find(req, res) {

};

function getAll(req, res) {
    Auction.find({})
        .populate("bids")
        .populate("book")
        .then((allAuctions) => {
            console.log("YEET")
            _sendJsonResponse(res, 200, allAuctions);
        })
        .catch((err) => {
            _sendJsonResponse(res, 404, { "message": "There was an error sorry" });
            console.log("Error: ", err.message);
        });
};

// TODO:
function getAuctionById(req, res) {

};


// TODO:
function getBids(req, res) {

}

// ========================================================


module.exports = {
    find: find,
    getAll: getAll,
    getAuctionById: getAuctionById,
    getBids: getBids
};