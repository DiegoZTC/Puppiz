exports = module.exports = function(app, mongoose) {

var Schema = mongoose.Schema;

var UserSchema = new Schema({
	username:  { type: String,lowercase : true,unique: true }
  , name: {
      type: String
    , required: true
    }
  , email: {
      type: String
    , required: true
    }
    ,
    password: {
      type: String
    , required: true
    }
});

mongoose.model('User', UserSchema);
		
};

