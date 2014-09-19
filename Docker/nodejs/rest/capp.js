var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

var routes = require('./routes/index');
var users = require('./routes/users');


var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

// uncomment after placing your favicon in /public
//app.use(favicon(__dirname + '/public/favicon.ico'));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', routes);
app.use('/users', users);


//Database connection
var mongoose = require('mongoose');
var address = process.env.MONGODB_PORT_27017_TCP_ADDR;
var port_mongo = process.env.MONGODB_PORT_27017_TCP_PORT;


mongoose.connect('mongodb://' + address + ':'+ port_mongo + '/bears', function(err, res) {
	
	
        
	if(err) {
		console.log('ERROR: connecting to Database. ' + err);
	} else {
		console.log('Connected to Database');
		}
		
		
});


var model_bears = require('./models/bear.js')(app,mongoose);
var Bear = require('./controllers/bear_controller.js');

var bears = express.Router();
bears.route('/bears')
	.get(Bear.findAll)
	.post(Bear.addBear);
	
var routes_bear = require('./routes/bear_routes');	
app.use('/api', routes_bear);


// catch 404 and forward to error handler
app.use(function(req, res, next) {
    var err = new Error('Not Found');
    err.status = 404;
    next(err);
});
// error handlers


// development error handler


// will print stacktrace
if (app.get('env') === 'development') {
    app.use(function(err, req, res, next) {
        res.status(err.status || 500);
        res.render('error', {
            message: err.message,
            error: err
        });
    });
}


// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
        message: err.message,
        error: {}
    });
});


module.exports = app;
