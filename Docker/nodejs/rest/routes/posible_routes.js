var express = require('express');


var Posible = require('../controllers/posible_controller.js');

var posibles = express.Router();
posibles.route('/posible')
	.get(Posible.findAll)
	.post(Posible.addPosible);
	

posibles.route('/posible/:posible_id')
	 .get(Search.findById);


module.exports = posibles;
