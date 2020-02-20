
function post() {
    var question_id=$("#question_id").val();
    var context=$("#context").val();
    $.ajax({
    type: "post",
        contentType:"application/json",
    url: "/post",
    data: JSON.stringify({"parentId":question_id,"context":context,type:"1"}),
    success: function(res){
        if (res.code==200){
            $("#context").hidden;
            alert("提交评论成功！");
        }else {
            if (res.code==20003){
                var confirm1 = confirm(res.message);
                if (confirm1){
                    window.open("https://github.com/login/oauth/authorize?client_id=5f89d14f3cf0f817373b&redirect_uri=http://localhost:8077/callback&scope=user&state=1");
                    window.localStorage.setItem("closeable",true);
                }
            }else {
                alert(res.message);
            }
        }
    },
    dataType: "json"
});
}