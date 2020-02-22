/*异步提交评论*/
function post() {
    var question_id = $("#question_id").val();
    var context = $("#context").val();
    $.ajax({
        type: "post",
        contentType: "application/json",
        url: "/post",
        data: JSON.stringify({"parentId": question_id, "context": context, type: 1}),
        success: function (res) {
            if (res.code == 200) {
                $("#context").hidden;
                window.location.reload();
                alert("提交评论成功！");
            } else {
                if (res.code == 20003) {
                    var confirm1 = confirm(res.message);
                    if (confirm1) {
                        window.open("https://github.com/login/oauth/authorize?client_id=5f89d14f3cf0f817373b&redirect_uri=http://localhost:8077/callback&scope=user&state=1");
                        window.localStorage.setItem("closeable", true);
                    }
                } else {
                    alert(res.message);
                }
            }
        },
        dataType: "json"
    });
}

/*
展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    console.log(id);
    var comments = $("#comment-" + id);
    debugger;
    var attribute = e.getAttribute("data-collapse");
    if (attribute) {//获取得到则为true
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        comments.addClass("in");
        e.setAttribute("data-collapse", "in");
        e.classList.add("active");
    }
}