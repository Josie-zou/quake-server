<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Earthquake Eye</title>
    <link href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resource/css/animate.css">
    <link href="<%=request.getContextPath()%>/resource/css/style.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resource/fonts/google_api.css?family=Montserrat|Varela+Round"
          rel="stylesheet">
    <script src="<%=request.getContextPath()%>/resource/js/pace.js"></script>
    <link href="<%=request.getContextPath()%>/resource/css/pace-theme-flash.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
</head>
<body>
<%--<%@include file="header.jsp" %>--%>
<%--<%@include file="system.jsp" %>--%>

<div class="modal fade" id="login-div" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form id="form-login" method="post" class="form-submit">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h3>用户登陆</h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                            <div class="input-group">
                                <span class="input-group-addon">账户</span>
                                <input type="text" class="form-control" name="account" value="" placeholder="手机号/邮箱" required/>
                            </div>
                        </div>
                    </div>
                    <br /><br />
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <span class="input-group-addon">密码</span>
                                <input type="password" class="form-control" name="password" value="" placeholder="登录密码" required/>
                            </div>
                        </div>
                        <div class="col-lg-4"></div>
                    </div>
                    <br /><br />
                </div>
                <div class="modal-footer">
                    <div class="row">
                        <div class="col-lg-12" align="right">
                            <button class="btn btn-info btn-lg" type="submit">登陆</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="modal fade" id="register-div" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <form id="form-register" method="post" class="form-submit">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h3>用户注册</h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-5">
                            <div class="input-group">
                                <span class="input-group-addon">名称</span>
                                <input type="text" class="form-control" name="username" value="" placeholder="真实姓名" required/>
                            </div>
                        </div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-5">
                            <div class="input-group">
                                <span class="input-group-addon">邮箱</span>
                                <input type="text" class="form-control" name="mail" value="" placeholder="邮箱" required/>
                            </div>
                        </div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-5">
                            <div class="input-group">
                                <span class="input-group-addon">手机</span>
                                <input type="text" class="form-control" name="mobile" value="" placeholder="手机号" required/>
                            </div>
                        </div>
                    </div>
                    <br /><br />
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <span class="input-group-addon">密码</span>
                                <input type="password" class="form-control" name="password" value="" placeholder="登录密码" id="password" required/>
                            </div>
                        </div>
                        <div class="col-lg-4"></div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <span class="input-group-addon">确认密码</span>
                                <input type="password" class="form-control" name="repassword" value="" placeholder="确认密码" id="repassword" required/>
                            </div>
                        </div>
                        <div class="col-lg-4"></div>
                    </div>
                    <br /><br />
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-4">
                            <div class="input-group">
                                <span class="input-group-addon">QQ</span>
                                <input type="text" class="form-control" name="qqnumber" value="" placeholder="QQ号" required/>
                            </div>
                        </div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-5">
                            <div class="input-group">
                                <span class="input-group-addon">工作单位</span>
                                <input type="text" class="form-control" placeholder="工作单位" name="workplace" value="" required/>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="input-group">
                                <span class="input-group-addon">职位</span>
                                <input type="text" class="form-control" placeholder="职位" name="position" value="" required/>
                            </div>
                        </div>
                        <div class="col-md-4"></div>
                    </div>
                    <br /><br />
                </div>
                <div class="modal-footer">
                    <div class="row">
                        <div class="col-lg-12" align="right">
                            <button class="btn btn-info btn-lg" type="submit">注册</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<header class="container-fluid intro-lg bkg">
    <%if (session.getAttribute("user") != null) {%>
    <a id="logout" href="#" class="btn btn-custom animated fadeInUp" data-toggle="tooltip" data-placement="bottom"
       title="退出" style="position:absolute;top:0px;right:30px">LOGOUT</a>
    <%}%>
    <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-8 col-md-offset-2">
        <h3 id="supra" class="animated fadeInUp">地震灾情获取与舆情监控</h3>

        <h1 id="title" class="animated fadeInUp">Earthquake Eye</h1>

        <h3 id="sub" class="animated bounceIn">一个<span class="hidden-xs">多维度</span><span
                class="hidden-xs hidden-sm hidden-md">的地震信息监测报警系统</span></h3>

        <div class="divider divider-intro animated bounceIn"></div>
        <%%>
        <%if (session.getAttribute("user") == null) {%>
        <button type="button" id="login" class="btn btn-custom animated fadeInUp" data-toggle="tooltip"
                data-placement="top" onclick="login()" title="登录">SIGN IN
        </button>
        &nbsp;
        <button type="button" id="register" class="btn btn-custom animated fadeInUp" data-toggle="tooltip"
                data-placement="top" onclick="register()" title="注册">SIGN UP
        </button>
        <%} else if (session.getAttribute("user") != null) {%>
        <a id="lookup" href="<%=request.getContextPath()%>/showdata" class="btn btn-custom animated fadeInUp"
           data-toggle="tooltip" data-placement="bottom" title="查看">LOOK UP</a>
        <%}%>
    </div>
</header>
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
    function login() {
        $("#login-div").modal("toggle");
    }
    function register() {
        $("#register-div").modal("toggle");
    }
    $("#form-login").submit(function () {
        var ajax_url = "<%=request.getContextPath()%>/api/login";
        var ajax_type = $(this).attr('method');
        var ajax_data = $(this).serialize();
        $.ajax({
            type: ajax_type,
            url: ajax_url,
            data: ajax_data,
            success: function (msg) {    //msg是后台调用action时，你传过来的参数
                var jsonObj = eval(msg);
                if (jsonObj.code == 0) {
                    location.href = "<%=request.getContextPath()%>/showdata";
                }
                else {
                    alert(jsonObj.msg);
                    if (jsonObj.code == 200001) {
                        login();
                        register();
                    }
                }
            }
        });
        return false;   //阻止表单的默认提交事件
    });
    $("#form-register").submit(function () {
        var password = $("#password").val();
        var repassword = $("#repassword").val();
        if (password == repassword) {
            var ajax_url = "<%=request.getContextPath()%>/api/signup";
            var ajax_type = $(this).attr('method');
            var ajax_data = $(this).serialize();
            $.ajax({
                type: ajax_type,
                url: ajax_url,
                data: ajax_data,
                success: function (msg) {    //msg是后台调用action时，你传过来的参数
                    var jsonObj = eval(msg);
                    if (jsonObj.code == 0) {
                        alert("signup success");
                        register();
                        login();
                    }
                    else {
                        alert(jsonObj.msg);
                    }
                }
            });
        }
        else {
            alert("两次输入密码不一致");
        }
        return false;   //阻止表单的默认提交事件
    });
    $("#logout").click(function () {
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/api/logout",
            data: "logout",
            success: function (msg) {    //msg是后台调用action时，你传过来的参数
                location.reload();
            }
        });
        return false;   //阻止表单的默认提交事件
    });
</script>
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
<%--<%@include file="footer.jsp" %>--%>
</body>
</html>