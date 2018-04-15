module.exports = {
    // Secret key for JWT signing and encryption
    'SECRET': 'super secret passphrase', // TODO: Make this a process.env.secret
    // Database connection information
    'DATABASE': 'mongodb://localhost/BidBox',
    // Setting port for server
    'PORT': process.env.PORT || 3000
}