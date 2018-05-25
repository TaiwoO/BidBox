module.exports = {
    // Secret key for JWT signing and encryption
    'SECRET': 'super secret passphrase', // TODO: Make this a process.env.secret
    // Database connection information
    'DATABASE': 'mongodb://localhost/BidBox',
    'DATABASE_PRODUCTION': process.env.MLAB_URI,
    'NODE_ENV': process.env.NODE_ENV || "testing",
    // Setting port for server
    'PORT': process.env.PORT || 3000,
    // path for images uploaded to the server
    'UPLOAD_PATH': 'uploads'
}