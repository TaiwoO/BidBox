const Book = require('../models/book');
const Auction = require('../models/auction');
const User = require('../models/user');
const Bid = require('../models/bid');
const config_constant = require('../config/config-constants');

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

// Gets information the the user passed through jwt aucthentication
function getUser(req, res) {
    const user = req.user;
    
    User.findById(user._id)
        .populate("bids")
        .populate("shoppingChart")
        .populate({
            path: 'auctions',
            populate: { path: 'bids', }
        })
        .populate({
            path: 'auctions',
            populate: { path: 'book', }
        })
        .then((user) => {
            _sendJsonResponse(res, 200, User.getPublicInfo(user));
        })
        .catch((err) => {
            console.log("Error: " + err.message);
            _sendJsonResponse(res, 404, { "message": "There was an error sorry" });
        });
}


function getUserById(req, res) {
    const userid = req.params.userid;

    User.findById(userid)
        .populate("bids")
        .populate("shoppingChart")
        .populate({
            path: 'auctions',
            populate: { path: 'bids', }
        })
        .populate({
            path: 'auctions',
            populate: { path: 'book', }
        })
        .then((user) => {
            _sendJsonResponse(res, 200, User.getPublicInfo(user));
        })
        .catch((err) => {
            console.log("Error: " + err.message);
            _sendJsonResponse(res, 404, { "message": "There was an error sorry" });
        });
};

function getAuctions(req, res) {

    const userid = req.params.userid;

    Auction.find({ user: userid })
        .populate("book")
        .populate("bids")
        .then((userAuctions) => {
            _sendJsonResponse(res, 200, userAuctions);
        })
        .catch((err) => {
            _sendJsonResponse(res, 404, { "message": "There was an error sorry" });
            console.log("Error: ", err.message);
        });
};

function getShoppingChart(req, res) {
    //TODO: make this a protected GET request

    const userId = req.params.userid;
    User.findById(userId)
        .populate('shoppingChart')
        .then((user) => {
            _sendJsonResponse(res, 200, User.getPublicInfo(user).shoppingChart);
        })
        .catch((err) => {
            console.log("Error: " + err.message);
            _sendJsonResponse(res, 404, { "message": "There was an error sorry" });
        })
};

// TODO:
function getBids(req, res) {
    const userId = req.params.userid;

    User.findById(userId)
        .populate('bids')
        .then((user) => {
            _sendJsonResponse(res, 200, User.getPublicInfo(user).bids);
        })
        .catch((err) => {
            console.log("Error: " + err.message);
            _sendJsonResponse(res, 404, { "message": "There was an error sorry" });
        })
};

function addToShoppingChart(req, res) {
    const user = req.user;

    const bookid = req.body.bookid;

    if (!bookid) {
        _sendJsonResponse(res, 404, { message: "all fields are required" })
        return
    }
    // TODO: Make sure auction exiists first
    //       Make sure that you can only add it once to shopping chart
    user.shoppingChart.push(bookid);
    user.save()
        .then((updatedUser) => {
            _sendJsonResponse(res, 200, User.getPublicInfo(updatedUser))
        })
        .catch((err) => {
            console.log("Error: " + err.message);
            _sendJsonResponse(res, 404, { "message": "There was an error sorry" });
        })
};

function addAuction(req, res) {
    const user = req.user; // From successful jwt passport validation
    const file = req.file; // From Multer


    const bookName = req.body.name;
    const bookVersion = req.body.version
    const bookCondiction = req.body.condition;
    const bookIsbn = req.body.isbn;
    const askingPrice = req.body.askingPrice;
    // const bookImg = req.??.bookImg // TODO: read in binary for book img

    const endDate = req.body.endDate;


    if (!bookName || !bookCondiction || !bookIsbn || !bookVersion || !askingPrice) {
        _sendJsonResponse(res, 404, { message: "All fields are required" });
        return
    }

    let newBook = new Book({
        name: bookName,
        version: bookVersion,
        condition: bookCondiction,
        isbn: bookIsbn,
        price: askingPrice
    });

    // Mimic the endpoint for getting an image
    if (file) {
        console.log("YES!: ", file.size);
        const curAddr = req.get('host');
        newBook.imgUrl = "https://" + curAddr + "/image/" + file.filename;
    }

    newBook.save()
        .then((newBook) => {
            let newAuction = new Auction({
                user: user._id,
                book: newBook._id,
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

function addBid(req, res) {

    const user = req.user;
    const auctionid = req.params.auctionid;
    const price = req.body.price;

    if (!price) {
        _sendJsonResponse(res, 404, { message: "all fields are required" })
        return
    }

    Auction.findById(auctionid, (err, auction) => {

        if (err) {
            _sendJsonResponse(res, 404, { message: "An error occured while looking for the auciton" })
            return
        }
        if (!auction) {
            _sendJsonResponse(res, 404, { message: "This auction does not exisit" })
            return
        }

        const newBid = new Bid({
            user: user.id,
            price: price
        });

        newBid.save()
            .then((newBid) => {
                user.bids.push(newBid);
                auction.bids.push(newBid);

                return Promise.all([user.save(), auction.save()])
            })
            .then((updates) => {
                let updatedUser = updates[0];
                let updatedAuction = updates[1];
                _sendJsonResponse(res, 200, User.getPublicInfo(updatedUser))
            })
            .catch(err => {
                console.log("Error: " + err.message);

                _sendJsonResponse(res, 404, { message: "Error adding new bid" });
            });
    });
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
    getUserById: getUserById,
    getShoppingChart: getShoppingChart,
    getAuctions: getAuctions,
    getBids: getBids,
    addToShoppingChart: addToShoppingChart,
    addAuction: addAuction,
    addBid: addBid,
    deleteAuction: deleteAuction,
    deleteBid: deleteBid,
    updateAuction: updateAuction,
    updateBid: updateBid
};