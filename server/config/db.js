const mongoose = require('mongoose');
const dbURI = 'mongodb://localhost/BidBox';

// if (process.env.NODE_ENV === 'production') {
//     dbURI = process.env.THE_PRODUCTION_dB_URI 
// }

mongoose.connect(dbURI);

// CONNECTION EVENTS
//
const db = mongoose.connection;

db.on('connected', () => {
    console.log("Mongoose connected to: " + dbURI);
});
db.on('error', (err) => {
    console.log('Mongooose connection error ' + err);
});
db.on('disconnected', () => {
    console.log('Mongoose disconnected');
});


// CAPTURE APP TERMINATION / RESTART EVENTS
//

// For app termination
process.on('SIGINT', () => {

    db.close()
        .then(() => {
            console.log("Mongoose disconnected through app termination");
            process.exit(0);
        })
});

// For nodemon restarts
process.once('SIGUSR2', () => {

    db.close()
        .then(() => {
            console.log("Mongoose disconnected through nodemon restart");
            process.kill(process.pid, 'SIGUSR2');
        })
});

// Load Schema Models
//

require('../models/auction');
require('../models/bid'); 
require('../models/book'); 
require('../models/user');


