exports = module.exports = function(app, mongoose) {

var Schema = mongoose.Schema;

var BearSchema   = new Schema({
	name:{type:String}
});

mongoose.model('Bear', BearSchema);
		
};

