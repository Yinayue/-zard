/**
 * Created by Administrator on 2018/2/2.
 */
var stompClient = null;
var sessionId = null;
function setConnected(connected) {
  $("#connect").html('连接');
  if (connected) {
    $("#connect").html('Successful');
  }
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  $("#send").prop("disabled", !connected);
  $("#sendtouser").prop("disabled", !connected);
  $("#username").prop("disabled", connected);

}

function login() {
  var name = $("#username").val().trim();
  if (name === '') {
    $("#username").val('用户名不能为空');
    return;
  }

  $.ajax({
    type: "POST",
    url: "/login",
    data: {username: name},
    success: function () {
      //初始化一下用户的Message
      //
      connect();
    },
    error: function () {
      $("#username").val('重名了');
    }
  });
}

function connect() {
  //Endpoint 是 /gs-guide-websocket
  var socket = new SockJS('/gs-guide-websocket');

  //表示使用STOMP来创建WebSocket客户端
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    setConnected(true);
    sessionId = /\/([^\/]+)\/websocket/.exec(socket._transport.url)[1];
   // showUser($("#username").val(), sessionId);
    stompClient.subscribe('/topic/greetings', function (greeting) {
      showGreeting(JSON.parse(greeting.body).content);
    });
    stompClient.subscribe('/user/topic/private', function (greeting) {
      var parse = JSON.parse(greeting.body);
      console.log(parse);
      showMessage(parse.content, parse.receiver, parse.name);
    });
    stompClient.subscribe('/topic/userlist', function (greeting) {
      var parse = JSON.parse(greeting.body);

      if (parse.online) {

        var exist = false;
        var usr_table = document.getElementById("user");
        var all_users = usr_table.getElementsByTagName("td");
        //防止一个用户重复出现
        for(var i = 0; i < all_users.length; i++){
            if(all_users.item(i).textContent === parse.name){
              exist = true;
            }
        }
        if(!exist){
            showUser(parse.name, parse.id);
        }
        //整个上线提醒
        $("#private").append("<tr class='msg-" + parse.name + "' ><td class='tips' >" +
            "<span class=\"tips-success\">"
            + parse.name + " is online!" + "</span>"+ "</td></tr>");
      } else {
        //前端移除该user不应该写在这
        $("#private").append("<tr class='msg-" + parse.name + "' ><td class='tips' >" +
            "<span>"
            + parse.name + " is offline!" + "</span>"+ "</td></tr>");
        // removeUser(parse.id);
        //后端移除该user
        removeOnlineUser(parse.name);
      }
    });
    load_hist();
  });
}

function disconnect() {
  clearUserMessage();
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  //removeUser(sessionId);
  setConnected(false);
  console.log("Disconnected");
}

function load_hist() {
    $.ajax({
        type: "POST",
        url: "/load_hist",
        data: {username:$("#username").val().trim()},
        success: function (result) {
            //pass
            if(result)
                console.log("Load History Successfully!")
            else{
                console.log("Load History Fails!")
            }
        }
    });
}

function sendName() {
  var content = $("#content").val();
  if (content.trim() === '') {
    return;
  }
  stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#username").val(), 'content': content}));
  $("#content").val('');
}
function sendToUser() {
  var touser = $("#privateuser").html();
  var patt1 = new RegExp(/【(.*?)】/g);
  var tousername = patt1.exec($("#privateuser").html())[1];
  var content = $("#contentuser").val();
  if (content.trim() === '') {
    return;
  }
  if (touser.trim() !== '私信聊天') {
    stompClient.send("/app/private", {}, JSON.stringify({'name': $("#username").val(), 'content': content, 'receiver': tousername}));
    $("#contentuser").val('');
  }
}

//点击列表发生事件
function touser(message) {
  $("#" + message.id + " span").html('');
  if ($("#privateuser").html() === 'PrivateChat') {
    $("#privateuser").html("Private Chat with 【" + message.textContent + "】");
    $(".msg-" + message.textContent).prop("hidden", false);
    return;
  }
  var patt1 = new RegExp(/【(.*?)】/g);
  //当签窗口的user名
  var tousername = patt1.exec($("#privateuser").html())[1];
  console.log( "Message\n" + message.textContent + "Tousername: " + tousername);
  if(message.class===tousername){
    return;
  }
  $(".msg-" + tousername).prop("hidden", true);
  $("#privateuser").html("Private Chat with 【" + message.textContent + "】");
  $(".msg-" + message.textContent).prop("hidden", false);

}

function hidetlobby(){
  if($("#lobby").is(":hidden")){
    $("#lobby").show();
    $("#conversation +div").show();
  }else{
    $("#lobby").hide();
    $("#conversation +div").hide();
  }
}
function hidetprivate(){
  if($("#private").is(":hidden")){
    $("#private").show();
    $("#privatechat +div").show();
  }else{
    $("#private").hide();
    $("#privatechat +div").hide();
  }
}
function hidetuser(){
  if($("#user").is(":hidden")){
    $("#user").show();
  }else{
    $("#user").hide();
  }
}
function showGreeting(message) {
  $("#lobby").append("<tr><td>" + message + "</td></tr>");
  var div = document.getElementById('lobby');
  div.scrollTop = div.scrollHeight;
}

//to user = receiver
function showMessage(message, touser, sender) {
  var patt1 = new RegExp(/【(.*?)】/g);


  //当前前端channel的聊天用户
  var tousername='';
  console.log('Touser: ' + touser + " Username: " + $("#username").val()) ;
  if($("#privateuser").html()!=='PrivateChat'){
    tousername = patt1.exec($("#privateuser").html())[1];
  }
  // console.log("username: " + $("#username").val() + "  touser: " + touser + " tousername: " + tousername)
  if (touser === $("#username").val()) {
    console.log("Sender: "+ sender + "Receiver: " + touser)
    tail = sender;
    if(touser === "Buyer_Kory"){
      $("#private").append("<tr class='msg-" + tail + "' ><td class='cleft cmsg' >" +
          "<img class=\"headIcon radius\" ondragstart=\"return false;\"  oncontextmenu=\"return false;\" src=\"./img/bili.png\" /><span class=\"content\">"
          + message + "</span>"+ "</td></tr>");
    }else{
      $("#private").append("<tr class='msg-" + tail + " ' style='width: 300px;'><td class='cleft cmsg' >" +
          "<img class=\"headIcon radius\" ondragstart=\"return false;\"  oncontextmenu=\"return false;\" src=\"./img/pw.png\" /><span class=\"content\">"
          + message + "</span>"+ "</td></tr>");
    }
  }
  else if (sender === $("#username").val()) {
    if(touser ==="Buyer_Kory"){
      $("#private").append("<tr class='msg-" + touser + "' ><td class='cright cmsg' style='float: right'>" +
          "<img class=\"headIcon radius\" ondragstart=\"return false;\"  oncontextmenu=\"return false;\" src=\"./img/bili.png\" /><span class=\"content\">"
          + message + "</span>"+ "</td></tr>");
    }else{
      $("#private").append("<tr class='msg-" + touser + "'><td class='cright cmsg' style='float: right'>" +
          "<img class=\"headIcon radius\" ondragstart=\"return false;\"  oncontextmenu=\"return false;\" src=\"./img/pw.png\" /><span class=\"content\">"
          + message + "</span>"+ "</td></tr>");
    }
  } else {
    var i = $("." + touser + " span").html();
    if (i === '') {
      i = 0;
    }
    $("." + touser + " span").html(++i);
    //这部分是干嘛的？？？？？？
    $("#private").append("<tr class='msg-" + touser + "' hidden><td class='cleft cmsg'>" +
        "<img class=\"headIcon radius\" ondragstart=\"return false;\"  oncontextmenu=\"return false;\" src=\"./img/pw.png\" /><span class=\"content\">"
        + message + "</span>"+ "</td></tr>");
  }

  if(tousername != sender ){
    $(".msg-" + sender).prop("hidden", true);
  }

  if(tousername != touser){
    $(".msg-" + touser).prop("hidden", true);
  }

  var div = document.getElementById('private');
  div.scrollTop = div.scrollHeight;
}
function showUser(user, id) {
  $("#user").append("<tr id='" + id + "' onclick='javascript:touser(this)' class='" + user + "'><td>" + user + "<span class='badge pull-right'></span></td></tr>");
}

//防止message重复出现
function clearUserMessage() {
   $("#private").empty();
}
function removeUser(id) {
  $("tr").remove("#" + id);
}

function removeOnlineUser(usr){
  $.ajax({
    type: "GET",
    url: "/rm_ol_usr",
    dataType:{username:usr},
    success: function (res) {
    console.log("从后台移除在线用户成功");
  }
  });
}

$(function () {
  $.ajax({
    type: "GET",
    url: "/userlist",
    dataType: "json",
    success: function (json) {
      for (var p in json) {//遍历json对象的每个key/value对,p为key
        showUser(json[p], p);
      }
    }
  });
  $("form").on('submit', function (e) {
    e.preventDefault();
  });
  $("#connect").click(function () {
    login();
  });
  $("#disconnect").click(function () {
    disconnect();
  });
  $("#send").click(function () {
    sendName();
  });
  $("#sendtouser").click(function () {
    sendToUser();
  });
});

//Offline user
$(function () {
  $.ajax({
    type: "GET",
    url: "/offline_userlist",
    dataType: "json",
    success: function (json) {
      for (var p in json) {//遍历json对象的每个key/value对,p为key
        showUser(json[p], p);
      }
    }
  });
  $("form").on('submit', function (e) {
    e.preventDefault();
  });

  // $("#send").click(function () {
  //   sendName();
  // });
  // $("#sendtouser").click(function () {
  //   sendToUser();
  // });
});

$(document).ready(function () {
  var div = document.getElementById('lobby');
  div.scrollTop = div.scrollHeight;
});
