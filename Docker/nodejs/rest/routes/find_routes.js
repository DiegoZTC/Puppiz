var express = require('express');


var Find= require('../controllers/find_controller.js');

var finds = express.Router();
finds.route('/find')
	.get(Find.findAll)
	.post(Find.addFind);
	

finds.route('/find/:near_place')
	 .get(Find.findByPlace);


module.exports = finds;
