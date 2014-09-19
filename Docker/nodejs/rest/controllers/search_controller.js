var mongoose = require('mongoose');

var Search = mongoose.model('Search');

	exports.addSearch=function(req, res) {
		
		var search = new Search(); 		// create a new instance of the Bear model
		 // set the bears name (comes from the request)
		search.pet_id = req.body.pet_id;
		search.near_place = req.body.near_place; 
		search.date = req.body.date; 
		search.description = req.body.description; 
	
		// save the bear and check for errors
		search.save(function(err) {
			if (err)
				res.send(err);
			res.json({ message: 'Search created!' });
		});
		
	};
	
	// get all the bears (accessed at GET http://localhost:8080/api/bears)
	exports.findAll=function(req, res) {
		Search.find(function(err, searchs) {
			if (err)
				res.send(err);
			res.json(searchs);
		});
	};
	
	// get all the bears (accessed at GET http://localhost:8080/api/bears)
	exports.findById=function(user,clave, res) {
		Search.findOne(req.params.pet_id,req.params.user_id,function(err,search) {
			if (err)
				res.send(err);
			
			res.send(search);
		});
	};
	
	exports.updateState=function(req, res) {

		Search.findOne(req.params.pet_id, function(err, search) {

			if (err)
				res.send(err);

			search.state = req.body.state; 	

			
			search.save(function(err) {
				if (err)
					res.send(err);

				res.json({ message: 'Search updated!' });
			});

	}); };
	
	
