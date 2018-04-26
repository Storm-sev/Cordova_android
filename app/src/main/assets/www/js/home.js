
var app = {

  initialize:function(){
    console.log("----------------------------");

    // 参数1  事件类型
    // 参数二 事件回调函数
    // 用于描述事件是冒泡还是捕获 参数多选
    document.addEventListener('deviceready',this.onDeviceReady.bind(this),false);

  },
  onDeviceReady: function(){

    this.receivedEvent('deviceready');

    // destinationType = navigator.camera.DestinationType;


  }

  receivedEvent: function(id){
    var parentElement = document.getElementById(id);

    var listeningElement = parentElement.querySelector('.listening');
    var receivedElement = parentElement.querySelector('.received');

    listeningElement.setAttribute('style','display:none;');
    receivedElement.setAttribute('style','display:block;');

  };



};

app.initialize();
