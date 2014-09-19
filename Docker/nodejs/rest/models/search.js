exports = module.exports = function(app, mongoose) {

var Schema = mongoose.Schema;

var SearchSchema = new Schema({
	user_id: {
      type: String
    },
	pet_id: {
      type: String
    },
    state:{
		type:Boolean,
		default: true
	}
    ,near_place:{
      type: String
    , required: true
    }
  ,date: {type: Date, default: Date.now},
    longitude: Number,
    latitude: Number,
    description:{type: String,default:'Ninguno'}
});

mongoose.model('Search', SearchSchema);
		
};
