exports = module.exports = function(app, mongoose) {

var Schema = mongoose.Schema;

var FindSchema = new Schema({
	user_id: {
      type: String
    },
    near_place:{
      type: String
    , required: true
    },
    state_pet:{
      type: String
    , required: true
	}
	,
	 type_pet:{
      type: String
    , required: true
	},
	
    details_pet:{
      type: String
    , required: true
	}
	,id_pet:{
		type: Boolean
    , required: true
	}
	,nom_pet:{
		type: Boolean
    , default:'NN'
	}
});

mongoose.model('Find', FindSchema);
		
};
