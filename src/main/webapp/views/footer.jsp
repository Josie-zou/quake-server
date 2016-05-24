<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<br/><br/><br/>
<div>
    <nav class="navbar navbar-default navbar-fixed-bottom" style="margin-bottom: -20; background-color: #7D9EC0;">
        <div class="container text-center">
            <p style="color: #ffffff; margin: 0;">Desgin By Josie</p>
        </div>
    </nav>
</div>
<script>
    $(function () {
        $(".unlogin").click(function () {
            document.getElementById("menu").click();
            document.getElementById("login").click();
        });
    });
    function system() {
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/api/system/status",
            success: function (msg) {    //msg是后台调用action时，你传过来的参数
                var jsonObj = eval(msg);
                if (jsonObj.code == 0) {
                    if (jsonObj.data == true) {
                        $("#system-body").html("系统处于运行状态.");
                        $("#system-start").remove();
                    }
                    else {
                        $("#system-stop").remove();
                        $("#system-body").html("系统处于关闭状态.");
                    }
                }
                else {
                    alert(jsonObj.msg);
                }
            }
        });
        $("#confirm").modal("toggle");
    }
    $("#system-start").click(function() {
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/api/system/start",
            success: function (msg) {    //msg是后台调用action时，你传过来的参数
                var jsonObj = eval(msg);
                if (jsonObj.code == 0) {
                    alert("系统已开启");
                    location.reload();
                }
                else {
                    alert(jsonObj.msg);
                }
            }
        });
    });
    $("#system-stop").click(function() {
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/api/system/shutdown",
            success: function (msg) {    //msg是后台调用action时，你传过来的参数
                var jsonObj = eval(msg);
                if (jsonObj.code == 0) {
                    alert("系统已关闭");
                    location.reload();
                }
                else {
                    alert(jsonObj.msg);
                }
            }
        });
    });
</script>