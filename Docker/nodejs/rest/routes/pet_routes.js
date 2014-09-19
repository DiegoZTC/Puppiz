var express = require('express');


var Pet = require('../controllers/pet_controller.js');

var pets = express.Router();
pets.route('/pet')
	.get(Pet.findAll)
	.post(Pet.addPet);
	

pets.route('/pet/:pet_id')
	 .get(Pet.findById);



module.exports = pets;
