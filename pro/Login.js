function login() {
    var username=document.getElementById("#login_user");
    var password=document.getElementById("#login_pas");
    $.ajax({
                   type: "POST",
                   url:"http://175.24.51.64:8081/users/register",
                   data: "name=nihaohh&phoneNumber=121233&email=71@qq.com&password=1&status=1&deleteFlag=1",
                   success: function(msg){
                     console.log( "Data Saved: " + msg );
                   },
                   error:function (msg) {
                    console.log( "Data not Saved: " + msg );
                   }
                });
}
function obj2str(data) {
    var res = [];
    for(var key in data){
        // 在URL中是不可以出现中文的, 如果出现了中文需要转码
        // 可以调用encodeURIComponent方法
        // URL中只可以出现字母/数字/下划线/ASCII码
        res.push(encodeURIComponent(key)+"="+encodeURIComponent(data[key])); // [userName=lnj, userPwd=123456];
    }
    return res.join("&"); // userName=lnj&userPwd=123456
}
function Regist() {
    var username=document.getElementById("#login_user");
    var password=document.getElementById("#login_pas");



    $.ajax({
                   type: "POST",
                   url:"http://175.24.51.64:8081/users/register",
                   data: "name=nihaohh&phoneNumber=121233&email=71@qq.com&password=1&status=1&deleteFlag=1",
                   success: function(msg){
                     console.log( "Data Saved: " + msg );
                   },
                   error:function (msg) {
                    console.log( "Data not Saved: " + msg );
                   }
                });
}
function f() {
    $.ajax({
        type: "POST",
        url: "http://175.24.51.64:8081/users/register",
        data: "name=nihaohh&phoneNumber=121233&email=71@qq.com&password=1&status=1&deleteFlag=1",
        success: function (msg) {
            console.log("Data Saved: " + msg);
        },
        error: function (msg) {
            console.log("Data not Saved: " + msg);
        }
    });
}