var mongoose = require('mongoose');

var Pet = mongoose.model('Pet');

	exports.addPet=function(req, res) {
		
		var pet = new Pet(); 		// create a new instance of the Bear model
		 // set the bears name (comes from the request)
		pet.user_id = req.body.user_id;
		pet.name = req.body.name;
		pet.type = req.body.type; 
		pet.color=req.body.color;
		pet.details =req.body.details;
		
	
		// save the bear and check for errors
		pet.save(function(err) {
			if (err)
				res.send(err);
			res.json({ message: 'Pet created!' });
		});
		
	};
	
	// get all the bears (accessed at GET http://localhost:8080/api/bears)
	exports.findAll=function(req, res) {
		Pet.find(function(err, pets) {
			if (err)
				res.send(err);
			res.json(pets);
		});
	};
	
	// get all the bears (accessed at GET http://localhost:8080/api/bears)
	exports.findById=function(req, res) {
		Pet.findById(req.params.pet_id,function(err,pet) {
			if (err)
				res.send(err);
			
			res.send(pet);
		});
	};
	
	
