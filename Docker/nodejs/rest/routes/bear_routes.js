var express = require('express');


var Bear = require('../controllers/bear_controller.js');

var bears = express.Router();
bears.route('/bears')
	.get(Bear.findAll)
	.post(Bear.addBear);



module.exports = bears;
