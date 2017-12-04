
var base = 'http://'+window.location.host;

const Storage =  {}

Storage.get = function (name) {
    return JSON.parse(localStorage.getItem(name))
}

Storage.set = function (name, val) {
    localStorage.setItem(name, JSON.stringify(val))
}

Storage.add = function (name, addVal) {
    let oldVal = Storage.get(name)
    let newVal = oldVal.concat(addVal)
    Storage.set(name, newVal)
}

//登录


$(document).ready(function() {

    $('#loginBtn').click(function() {
        $.ajax({
            url : base+'/login/login',
            type : "POST",
            async : false,
            dataType : "json",
            data : {userName:"hahaha",password:"hehehe"},
            success : function(result) {
                var code = result.code;
                var message = result.message;
                if (code == "0"){
                    Storage.set("key","wangjie");
                    alert(Storage.get("key"));
                }
            }

        });
    });

});