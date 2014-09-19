exports = module.exports = function(app, mongoose) {

var Schema = mongoose.Schema;

var PetSchema = new Schema({
	user_id: {
      type: String
    , required: true
    },name: {
      type: String
    , required: true
    }
  , type: {
      type: String
    , required: true
    }
  , color: {
      type: String
    , required: true
    }
    ,
    details: {
      type: String
    , required: true
    }
    ,
    photo_url: {
      type: String
    }
});

mongoose.model('Pet', PetSchema);
		
};
