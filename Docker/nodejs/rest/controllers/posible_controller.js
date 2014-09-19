var mongoose = require('mongoose');

var Posible = mongoose.model('Posible');
var Search = mongoose.model('Search');
var Pet = mongoose.model('Pet');
var  = mongoose.model('User');	
	var searchStop=function(req,res){
		
		Search.findOne(req.params.pet_id, function(err, search) {

			if (err)
				res.send(err);

			search.state = req.body.state; 	

			
			search.save(function(err) {
				if (err)
					res.send(err);

				res.json({ message: 'Search updated!' });
			});

	});
	};
	
	exports.addPosible=function(req, res) {
		
		var posible = new Posible();
		var search = new Search();
		var pet = new Pet();
		 // set the bears name (comes from the request)
		
		posible.pet = req.body.pet;
		posible.finder = req.body.finder; 
		posible.tel_finder=req.body.tel_finder;
		posible.date_find=req.body.date_find;
		
		
	
		// save the bear and check for errors
		find.save(function(err) {
			if (err)
				res.send(err);
			res.json({ message: 'Find created!' });
			
			
		});
		
	};
	
	
	
	// get all the bears (accessed at GET http://localhost:8080/api/bears)
	exports.findAll=function(req, res) {
		Posible.find(function(err, searchs) {
			if (err)
				res.send(err);
			res.json(searchs);
		});
	};
	
	// get all the bears (accessed at GET http://localhost:8080/api/bears)
	exports.findById=function(req, res) {
		Posible.findOne(req.params.posible_id,function(err,posible) {
			if (err)
				res.send(err);
			
			res.send(posible);
		});
	};
	
	
	
