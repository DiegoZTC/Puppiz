var mongoose = require('mongoose');
var User  = mongoose.model('User');




	exports.addUser=function(req, res) {
		
		var user = new User(); 		// create a new instance of the Bear model
		 // set the bears name (comes from the request)
		
		user.username = req.body.username;
		user.name = req.body.name; 
		user.email=req.body.email;
		user.password =req.body.password;
		
		 console.log(user);
		// save the bear and check for errors
		user.save(function(err) {
			if (err)
				res.send(err);
			res.json({ message: 'User created!' });
		});
		
	};
	
	// get all the bears (accessed at GET http://localhost:8080/api/bears)
	exports.findAll=function(req, res) {
		User.find(function(err, users) {
			if (err)
				res.send(err);

			res.json(users);
		});
	};
	
	// get all the bears (accessed at GET http://localhost:8080/api/bears)
	exports.findById=function(req, res) {
		User.findById(req.params.user_id,function(err,user) {
			if (err)
				res.send(err);
			res.json(user);
		});
	};
	
	exports.deleteUser=function(req, res) {
		User.remove({_id: req.params.user_id
		}, function(err, user) {
			if (err)
				res.send(err);
			res.json({ message: 'Successfully deleted' });
		});
	};
	
	// get all the bears (accessed at GET http://localhost:8080/api/bears)
	exports.getLogin=function(req, res) {
		User.where('username').equals(req.params.username)
			.where('password').equals(req.params.password).find(function(err, user) {
			if (JSON.stringify(user) != '[]'||JSON.stringify(user) != '{}')
				{
					res.json(user);
				 }
			else
			  {res.contentType('application/json');
                    res.statusCode=404;
                    res.write(JSON.stringify({"msg":"No Existe"}));
                    res.end();
				}
			  
		});
	};
	
    
 
