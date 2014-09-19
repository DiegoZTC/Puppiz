var mongoose = require('mongoose');
var fs = require('fs');
var PetPhoto= mongoose.model('PetPhoto');

var Pet= mongoose.model('Pet');


	exports.addPetPhoto=function(req, res) {
		
		var petPhoto= new PetPhoto();
		var pet= new Pet(); 		// create a new instance of the Bear model
		 // set the bears name (comes from the request)
		petPhoto.id = req.body.pet_id;
		petPhoto.photo =fs.readFileSync(req.body.photoURL);
		petPhoto.photo_type=req.body.photo_type;
		// save the bear and check for errors
		
		petPhoto.save(function(err) {
			if (err)
				res.send(err);
			res.json({ message: 'Pet Photo created!' });
			
			
			Pet.findByOne(req.params.pet_id, function(err, pet) {

			if (err)
				res.send(err);
			
			pet.photo_id = findByPetID(petPhoto.id); 	// update the bears info

			// save the bear
			pet.save(function(err) {
				if (err)
					res.send(err);
			});

			});
		});
		
		
		
		
	};
	
		var findByPetID=function(pet_id, res) {
		PetPhoto.findOne(pet_id).select('_id',function(err,id) {
			if (err)
				res.send(err);
			res.json(id);
		});
		};
		
		// get all the bears (accessed at GET http://localhost:8080/api/bears)
	exports.findById=function(req, res) {
		PetPhoto.findById(req.params.pet_id,function(err,photo) {
			if (err)
				res.send(err);
			res.json(photo);
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
                    res.write(JSON.stringify({"msg":"Not Exist"}));
                    res.end();
				}
			  
		});
	};
