exports = module.exports = function(app, mongoose) {

var Schema = mongoose.Schema;

var PetPhotoSchema = new Schema({
photo: {
      type: Buffer
    },
    photo_type: {
      type: String
    , required: true
    },
     pet_id: {
      type: String
    , required: true
    }
});

mongoose.model('PetPhoto', PetPhotoSchema);
		
};
