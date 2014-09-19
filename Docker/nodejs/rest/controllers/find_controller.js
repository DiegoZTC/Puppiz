var mongoose = require('mongoose');

var Find = mongoose.model('Find');
var Search = mongoose.model('Search');
var Pet = mongoose.model('Pet');
var User = mongoose.model('User');	

	var getUser=function(id_user,res){
		
		
		user=User.findById(req.params.user_id,function(err,user) {
			if (err)
				res.send(err);
			
		}); };
		
	//var getPet=function(req,user_id,res,function(err,pet){
		
		//Pet.find({
    //"$or": [{
        //"name": /req/
    //}, {
        //"type": /req/
    //},
    //{
        //"color": /req/
    //},
    //{
        //"details": /req/
    //},
    //]
//}, {
    //"name": 1,
    //"type": 1,
    //"color": 1,
    //"details": 1
//}).where('user_id').equals(user_id);

//}; });
		
	
	
	
	exports.addFind=function(req, res) {
		
		var find = new Find(); 		// create a new instance of the Bear model
		var search = new Search();
		var pet = new Pet();
		 // set the bears name (comes from the request)
		
		find.user_id = req.body.user_id;
		find.near_place = req.body.near_place; 
		find.state_pet=req.body.state_pet;
		find.type_pet=req.body.type_pet;
		find.details_pet=req.body.details_pet;
		find.id_pet=req.body.id_pet;
		find.nom_pet=req.body.nom_pet;
		
	
		// save the bear and check for errors
		find.save(function(err) {
			if (err)
				res.send(err);
			res.json({ message: 'Find created!' });
		
			
		});
		
	};
	
	
	
	
	
	
	
	
	
	
	
	
	// get all the bears (accessed at GET http://localhost:8080/api/bears)
	exports.findAll=function(req, res) {
		Find.find(function(err, finds) {
			if (err)
				res.send(err);
			res.json(finds);
		});
	};
	
	exports.findByPlace=function(req, res) {
		Find.find('near_place',/req.body.req/,function(err, finds) {
			if (err)
				res.send(err);
			res.json(finds);
		});
	};
	
	
	
