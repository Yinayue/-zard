// globalData 可以随便定义，调用的时候相同即可
var globalData ={

 /**
  * setUserInfo 方法名
  *  uid 用户id
  *  uname 用户姓名
  *  usex 用户性别
  */
 //多个存储
 setUserInfo:function (uid,uname,usex){
  //单个存储
  sessionStorage.setItem("uid",uid);
  sessionStorage.setItem("uname",uname);
  sessionStorage.setItem("usex",usex);
 },
 //单个获取
 getUserUid:function(){
  return sessionStorage.getItem("uid");
 },
 getUserUName:function(){
  return sessionStorage.getItem("uname");
 },
 getUserUSex:function(){
  return sessionStorage.getItem("usex");
 }
}
//sessionStorage是一个很方便的属性
// 另外还有删除
// sessionStorage.removeItem(‘key’);
// 以及清空sessionStorage
// sessionStorage.clear();

