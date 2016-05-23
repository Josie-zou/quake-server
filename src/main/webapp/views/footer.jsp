<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            url: "<%=request.getContextPath()%>/SettingServlet?operate=system",
            data: "",
            success: function (msg) {    //msg是后台调用action时，你传过来的参数
                if ( msg == "stoping" ) {
                    $("#system-stop").remove();
                    $("#system-body").html("系统处于关闭状态.");
                } else if ( msg == "starting" ) {
                    $("#system-body").html("系统处于运行状态.");
                    $("#system-start").remove();
                }
                footer_fun();
            }
        });
        $("#confirm").modal("toggle");
    }
    $(function () {
        $("#system").click(function () {
            document.getElementById("menu").click();
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/SettingServlet?operate=system",
                data: "",
                success: function (msg) {    //msg是后台调用action时，你传过来的参数
                    if ( msg == "stoping" ) {
                        $("#system-stop").remove();
                        $("#system-body").html("系统处于关闭状态.");
                    } else if ( msg == "starting" ) {
                        $("#system-body").html("系统处于运行状态.");
                        $("#system-start").remove();
                    }
                    footer_fun();
                }
            });
            $("#confirm").modal("toggle");
        });
        $("#system-start").click(function() {
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/SettingServlet?operate=system-start",
                data: "",
                success: function (msg) {    //msg是后台调用action时，你传过来的参数
                    if ( msg == "permission denied" ) {
                        alert("您没有权限进行此操作")
                    } else if ( msg == "success" ) {
                        alert("系统已经开启");
                        location.reload();
                    }
                    footer_fun();
                }
            });
        });
        $("#system-stop").click(function() {
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/SettingServlet?operate=system-stop",
                data: "",
                success: function (msg) {    //msg是后台调用action时，你传过来的参数
                    if ( msg == "permission denied" ) {
                        alert("您没有权限进行此操作")
                    } else if ( msg == "success" ) {
                        alert("系统已经关闭");
                        location.reload();
                    }
                    footer_fun();
                }
            });
        });
    });
</script>