cordova.define("cordova-plugin-prompt.PromptPlugin", function(require, exports, module) {
// var exec = require('cordova/exec');
//
// exports.coolMethod = function (arg0, success, error) {
//     exec(success, error, 'PromptPlugin', 'coolMethod', [arg0]);
// };

// cordova.define("cordova-plugin-prompt.PromptPlugin",function(require,exports,module){
  var exec = require("cordova/exec");

  // 弹出Toast
  exports.showToast = function(arg0,success,error){
    exec(success,error,"PromptPlugin",'showToast',[arg0]);
  };

  // 显示一般的dialog
  exports.showDialog = function(arg0,success,error){
    exec(success,error,"PromptPlugin",'showDialog',[arg0]);
  };

  //带有回调的dialog
  exports.showOnClickDialog = function(arg0,success,error){

    exec(success,error,"PromptPlugin",'showOnClickDialog',[arg0]);
  }; 

// });

});
