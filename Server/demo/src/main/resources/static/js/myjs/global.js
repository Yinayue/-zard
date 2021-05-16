function getParams(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}
function searchbox() {
    var input=document.getElementById("searchbox").value;
    var url = "commend2.html?q="+input;
    window.location.href=url;
}