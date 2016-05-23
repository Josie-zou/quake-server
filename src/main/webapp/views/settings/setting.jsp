<%@ page import="com.josie.quake.model.User" %><%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-5-25
  Time: 下午4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Earthquake Eye</title>
    <link href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resource/css/animate.css">
    <link href="<%=request.getContextPath()%>/resource/css/style.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resource/fonts/google_api.css?family=Montserrat|Varela+Round" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/resource/js/pace.js"></script>
    <link href="<%=request.getContextPath()%>/resource/css/pace-theme-flash.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="../system.jsp"%>
<%@include file="../navbar.jsp"%>
<%
    User user = (User)session.getAttribute("user");
    if ( user != null ) {
%>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4>个人资料<a class="anchorjs-link" href="#"><span class="anchorjs-icon"></span></a></h4>
            <form id="form-userinfo" method="post">
                <br />
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <span class="input-group-addon">名称</span>
                            <input type="text" class="form-control" placeholder="username" name="username" value="<%=user.getUsername()%>" required/>
                            <input type="text" hidden name="id" value="<%=user.getId()%>" />
                        </div>
                    </div>
                    <div class="col-md-2"></div>
                </div>
                <br />
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <span class="input-group-addon">邮箱</span>
                            <input type="text" class="form-control" placeholder="email" name="mailAdress" value="<%=user.getMailAdress()%>" required/>
                        </div>
                    </div>
                    <div class="col-md-4"></div>
                </div>
                <br />
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <span class="input-group-addon">手机</span>
                            <input type="text" class="form-control" placeholder="phone number" name="phoneNumber" value="<%=user.getPhoneNumber()%>" required/>
                        </div>
                    </div>
                    <div class="col-md-4"></div>
                </div>
                <br /><br />
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-4">
                        <div class="input-group">
                            <span class="input-group-addon">QQ</span>
                            <input type="text" class="form-control" placeholder="qq number" name="qq" value="<%=user.getQq()%>" />
                        </div>
                    </div>
                    <div class="col-md-4"></div>
                </div>
                <br />
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-5">
                        <div class="input-group">
                            <span class="input-group-addon">工作单位</span>
                            <input type="text" class="form-control" placeholder="workplace" name="workPlace" value="<%=user.getWorkPlace()%>" />
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="input-group">
                            <span class="input-group-addon">职位</span>
                            <input type="text" class="form-control" placeholder="position" name="positon" value="<%=user.getPositon()%>" />
                        </div>
                    </div>
                    <div class="col-md-4"></div>
                </div>
                <br /><br />
                <div class="row">
                    <div class="col-md-12" align="right">
                        <button class="btn btn-info" type="submit">更新资料</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="col-md-2"></div>
</div>
<br />

<div class="modal fade" id="show-div" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form id="form-password" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4>修改密码</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                            <div class="input-group">
                                <span class="input-group-addon">初始密码</span>
                                <input type="password" class="form-control" name="password" value="" required/>
                            </div>
                        </div>
                    </div>
                    <br /><br />
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <span class="input-group-addon">新的密码</span>
                                <input type="password" class="form-control" name="new-password" id="newpassword" value="" required/>
                            </div>
                        </div>
                        <div class="col-lg-4"></div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-6">
                            <div class="input-group">
                                <span class="input-group-addon">重复密码</span>
                                <input type="password" class="form-control" name="re-password" id="repassword" value="" required/>
                            </div>
                        </div>
                        <div class="col-lg-4"></div>
                    </div>
                    <br /><br />
                </div>
                <div class="modal-footer">
                    <div class="row">
                        <div class="col-lg-12" align="right">
                            <button class="btn btn-danger" type="submit">修改密码</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    <%if ( request.getParameter("password") != null && request.getParameter("password").equals("true") ) {%>
    show();
    <%}%>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
    function show() {
        $("#show-div").modal("toggle");
    }
    $("#form-userinfo").submit(function() {
        var ajax_url = "<%=request.getContextPath()%>/api/user/update";
        var ajax_type = $(this).attr('method');
        var ajax_data = $(this).serialize();
        $.ajax({
            type: ajax_type,
            url: ajax_url,
            data: ajax_data,
            success: function(msg) {    //msg是后台调用action时，你传过来的参数
                var jsonObj = eval(msg);
                if (jsonObj.code == 0) {
                    alert("修改成功");
                }
                else {
                    alert(jsonObj.msg);
                }
            }
        });
        return false;   //阻止表单的默认提交事件
    });
    $("#form-password").submit(function() {
        var password = $("#newpassword").val();
        var repassword = $("#repassword").val();
        if (password == repassword) {
            var ajax_url = "<%=request.getContextPath()%>/api/user/password";
            var ajax_type = $(this).attr('method');
            var ajax_data = $(this).serialize();
            $.ajax({
                type: ajax_type,
                url: ajax_url,
                data: ajax_data,
                success: function (msg) {    //msg是后台调用action时，你传过来的参数
                    var jsonObj = eval(msg);
                    if (jsonObj.code == 0) {
                        alert("修改成功");
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
    function no() {
        alert("您没有权限进行此操作");
    }
</script>
<script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
<%}%>
<%@include file="../footer.jsp"%>
</body>
</html>
