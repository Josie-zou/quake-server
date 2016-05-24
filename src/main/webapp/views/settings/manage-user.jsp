<%@ page import="com.josie.quake.model.User" %><%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-5-27
  Time: 上午12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>Earthquake Eye</title>
        <link href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/resource/css/style.min.css" rel="stylesheet">
        <script src="<%=request.getContextPath()%>/resource/js/pace.js"></script>
        <link href="<%=request.getContextPath()%>/resource/css/pace-theme-flash.min.css" rel="stylesheet">
        <script src="<%=request.getContextPath()%>/resource/js/jquery-2.1.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="../header.jsp" %>
        <%@include file="../system.jsp" %>
        <%@include file="../navbar.jsp"%>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3>警告<a class="anchorjs-link" href="#"><span class="anchorjs-icon"></span></a></h3>
                    <br />
                    <div class="row">
                        <div class="col-lg-2"></div>
                        <div class="col-lg-8" align="center">
                            <h3>您可以对以下用户进行删除操作</h3>
                            <p>&nbsp;</p>
                        </div>
                        <div class="col-lg-2"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
        <br /><br />
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div id="filters-div">
                    <table class="table table-striped table-bordered table-hover" id="filters-table">
                        <thead>
                        <tr>
                            <th class="text-center" width="50px"></th>
                            <th class="text-center">用户名</th>
                            <th class="text-center">邮箱</th>
                            <th class="text-center">手机</th>
                            <th class="text-center">删除用户</th>
                            <th class="text-center">设置管理</th>
                        </tr>
                        </thead>
                        <tbody id="filters-tbody"></tbody>
                    </table>
                </div>
                <div class="text-center">
                    <nav>
                        <ul class="pagination">
                            <li id="pagePre">
                                <a href="#" aria-label="Previous" onclick="getPage(-1)">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li class="active"><a href="#" id="pageNum">1</a></li>
                            <li id="pageNext">
                                <a href="#" aria-label="Next" onclick="getPage(1)">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <script>
            var nowPage = 0;
            var flag = false;
            function getPage(start) {
                var link = "<%=request.getContextPath()%>/api/user/getAllByStatus?count=10&start=";
                if (start == -1) {
                    if (nowPage == 0) {
                        nowPage = 0;
                        return;
                    }
                    else {
                        nowPage --;
                    }
                }
                else if (start == 1) {
                    if (flag) {
                        return;
                    }
                    nowPage++;
                }
                else if (start == 0) {
                    nowPage = 0;
                }
                if (nowPage == 0) {
                    $("#pagePre").attr("class", "disabled");
                }
                else {
                    $("#pagePre").removeAttr("class");
                }

                $("#pageNum").text(nowPage+1);
                $.ajax({
                    url: link + (nowPage*10),
                    type: "GET",
                    dataType: "json",
                    success: function(data) {
                        var jsonObj = eval(data);
                        if (jsonObj.code == 0) {
                            if (jsonObj.data.length != 10) {
                                $("#pageNext").attr("class", "disabled");
                                flag = true;
                            }
                            else {
                                $("#pageNext").removeAttr("class");
                                flag = false;
                            }
                            createTable(jsonObj.data);
                        }
                        else {
                            alert(jsonObj.msg);
                        }
                    }
                });
            }
            getPage(0);

            function createTable(data) {
                document.getElementById("filters-tbody").innerHTML = "";
                for (var i = 0; i < data.length; i ++) {

                    var row = document.createElement("tr");
                    row.setAttribute("id", data[i].id);
                    row.setAttribute("class", "text-info");

                    var label_col = document.createElement("td");
                    label_col.setAttribute("class", "text-center");
                    var label = document.createElement("span");
                    if (data[i].privilege == 1) {
                        label.setAttribute("class", "label label-default");
                        label.innerHTML = "普通用户";
                    }
                    else if (data[i].privilege == 2) {
                        label.setAttribute("class", "label label-warning");
                        label.innerHTML = "管理员";
                    }
                    label_col.appendChild(label);
                    row.appendChild(label_col);

                    var col1 = document.createElement("th");
                    col1.setAttribute("class", "text-center");
                    col1.appendChild(document.createTextNode(data[i].username));
                    row.appendChild(col1);
                    var col2 = document.createElement("th");
                    col2.setAttribute("class", "text-center");
                    col2.appendChild(document.createTextNode(data[i].mailAdress));
                    row.appendChild(col2);
                    var col3 = document.createElement("th");
                    col3.setAttribute("class", "text-center");
                    col3.appendChild(document.createTextNode(data[i].phoneNumber));
                    row.appendChild(col3);

                    var col4 = document.createElement("th");
                    col4.setAttribute("class", "text-center");
                    var button1 = document.createElement("button");
                    button1.setAttribute("class", "btn btn-danger");
                    button1.setAttribute("value", data[i].id);
                    button1.setAttribute("name", "user");
                    button1.innerHTML = "删除";
                    if (data[i].privilege == 1) {
                        button1.setAttribute("onClick", "del(this.value)");
                    }
                    else {
                        button1.setAttribute("disabled", "disabled");
                    }
                    col4.appendChild(button1);

                    var col5 = document.createElement("th");
                    col5.setAttribute("class", "text-center");
                    var button2 = document.createElement("button");
                    if (data[i].privilege == 1) {
                        button2.setAttribute("class", "btn btn-success");
                        button2.setAttribute("value", data[i].id);
                        button2.setAttribute("name", "admin");
                        <%
                            User user = (User)session.getAttribute("user");
                            if (user.getPrivilege() == User.Privilege.Root.toInt()) {
                        %>
                        button2.setAttribute("onClick", "manage(this.value)");
                        <%
                            }
                            else {
                        %>
                        button2.setAttribute("disabled", "disabled");
                        <%
                            }
                        %>
                        button2.innerHTML = "管理员";
                        col5.appendChild(button2);
                    }
                    else if (data[i].privilege == 2) {
                        button2.setAttribute("class", "btn btn-warning");
                        button2.setAttribute("value", data[i].id);
                        button2.setAttribute("name", "admin");
                        button2.setAttribute("onClick", "delManager(this.value)");
                        button2.innerHTML = "撤销管理";
                        col5.appendChild(button2);
                    }

                    row.appendChild(col4);
                    row.appendChild(col5);
                    document.getElementById("filters-tbody").appendChild(row);
                }
            }
        </script>
        <script>
            function del(uid) {
                $.ajax({
                    type: "get",
                    url: "<%=request.getContextPath()%>/api/user/delete",
                    data: "id="+uid,
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        var jsonObj = eval(msg);
                        if (jsonObj.code == 0) {
                            location.reload();
                        }
                        else {
                            alert(jsonObj.msg);
                        }
                    }
                });
            }
            function manage(id) {
                $.ajax({
                    type: "get",
                    url: "<%=request.getContextPath()%>/api/user/setManager",
                    data: "id="+id,
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        var jsonObj = eval(msg);
                        if (jsonObj.code == 0) {
                            location.reload();
                        }
                        else {
                            alert(jsonObj.msg);
                        }
                    }
                });
            }
            function delManager(id) {
                $.ajax({
                    type: "get",
                    url: "<%=request.getContextPath()%>/api/user/delManager",
                    data: "id="+id,
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        var jsonObj = eval(msg);
                        if (jsonObj.code == 0) {
                            location.reload();
                        }
                        else {
                            alert(jsonObj.msg);
                        }
                    }
                });
            }
        </script>
        <script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
        <%@include file="../footer.jsp" %>
    </body>
</html>
