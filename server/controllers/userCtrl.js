const Book = require('../models/book');
const Auction = require('../models/auction');
const User = require('../models/user');

/**
 *  ============================================ HELPER FUNCTIONS ====================================================
 */


function _sendJsonResponse(res, status, content) {
    res.status(status);
    res.json(content);
}

// ========================================================


/**
 *  ============================================ REQUEST INTERCEPTIONS ====================================================
 */

// TODO:
function getUser(req, res) {

};

// TODO:
function getAuctions(req, res) {

};

// TODO:
function getBids(req, res) {

};

// TODO:
function addAuction(req, res) {
    const user = req.user; // From successful jwt passport validation

    const bookName = req.body.name;
    const bookVersion = req.body.version
    const bookCondiction = req.body.condition;
    const bookIsbn = req.body.isbn;
    const askingPrice = req.body.askingPrice;
    // const bookImg = req.??.bookImg // TODO: read in binary for book img

    const endDate = req.body.endDate;

    if (!bookName || !bookCondiction || !bookIsbn || bookVersion || !askingPrice) {
        _sendJsonResponse(res, 404, { message: "All fields are required" });
        return
    }

    let newBook = new Book({
        name: bookName,
        version: bookVersion,
        condition: bookCondiction,
        isbn: bookIsbn
    });

    newBook.save()
        .then((newBook) => {
            let newAuction = new Auction({
                bookid: newBook._id,
                userid: user._id,
                askingPrice: askingPrice,
                endDate: endDate
            });
            return newAuction.save();
        })
        .then((newAuction) => {
            user.auctions.push(newAuction);
            return user.save()
        })
        .then((updatedUser) => { _sendJsonResponse(res, 200, User.getPublicInfo(updatedUser)) })
        .catch((err) => {
            console.log("Error: " + err.message);
            _sendJsonResponse(res, 404, { "message": "There was an error sorry" });
        });
};

// TODO:
function addBid(req, res) {

};

// TODO:
function deleteAuction(req, res) {
    // TODO: add validation to check that that auction to be deleted belongs to the user that requests for it to be deleted
};

// TODO:
function deleteBid(req, res) {

};

// TODO:
function updateAuction(req, res) {

};

// TODO: 
function updateBid(req, res) {

}

// ========================================================

module.exports = {
    getUser: getUser,
    getAuctions: getAuctions,
    getBids: getBids,
    addAuction: addAuction,
    addBid: addBid,
    deleteAuction: deleteAuction,
    deleteBid: deleteBid,
    updateAuction: updateAuction,
    updateBid: updateBid
};