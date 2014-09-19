var express = require('express');


var User = require('../controllers/user_controller.js');

var users = express.Router();
users.route('/user')
	.get(User.findAll)
	.post(User.addUser);
	

users.route('/user/:user_id')
	 .get(User.findById);

users.route('/user/:username/:password')
	 .get(User.getLogin);


module.exports = users;
