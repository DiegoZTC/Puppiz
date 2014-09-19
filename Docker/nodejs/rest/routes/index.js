var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res) {
  res.render('index', { title: 'Express' });
});


// test route to make sure everything is working (accessed at GET http://localhost:3000/api)
router.get('/api', function(req, res) {
	res.json({ message: 'hooray! welcome to our api!' });	
});

module.exports = router;
