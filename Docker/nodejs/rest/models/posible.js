exports = module.exports = function(app, mongoose) {

var Schema = mongoose.Schema;
var Pet = mongoose.model('Pet');
var User = mongoose.model('User');
var PosibleSchema = new Schema({
	pet: {
      type:Pet
      required: true
    },
    ,finder:{
      type: User
    , required: true
    },
     tel_finder:{
      type: String
	}
	,date_find:{
      type: Date
    , default: Date.now
	}
});

mongoose.model('Posible', PosibleSchema);
		
};
