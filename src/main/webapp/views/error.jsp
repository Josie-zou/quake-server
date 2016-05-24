<%--
  Created by IntelliJ IDEA.
  User: Glacier
  Date: 16/5/24
  Time: 下午6:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Error</title>
    <link href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resource/css/style.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/resource/js/pace.js"></script>
    <link href="<%=request.getContextPath()%>/resource/css/pace-theme-flash.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
</head>
<body>

<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6" style="padding-top: 200px;">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <%--<h3>信息审核<a class="anchorjs-link" href="#"><span class="anchorjs-icon"></span></a></h3>--%>
            <br />
            <div class="row">
                <div class="col-lg-2"></div>
                <div class="col-lg-8" align="center">
                    <h3>您没有权限访问</h3>
                    <p>&nbsp;</p>
                </div>
                <div class="col-lg-2"></div>
            </div>
        </div>
    </div>
    <div class="col-md-3"></div>
</div>
</body>
</html>
