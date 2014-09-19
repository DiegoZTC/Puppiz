var express = require('express');


var Search = require('../controllers/search_controller.js');

var searchs = express.Router();
searchs.route('/search')
	.get(Search.findAll)
	.post(Search.addSearch);
	

searchs.route('/search/:pet_id/:user_id')
	 .get(Search.findById);


searchs.route('/search/:pet_id')
	 .put(Search.updateState);

module.exports = searchs;
