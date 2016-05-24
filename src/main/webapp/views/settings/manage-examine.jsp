<%@ page import="com.josie.quake.model.User" %><%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-5-25
  Time: 下午8:32
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
        <%@include file="../header.jsp"%>
        <%@include file="../system.jsp"%>
        <%@include file="../navbar.jsp"%>
        <div class="row" align="center">
            <button id="examine-start" type="button" onclick="fun(1)" class="btn btn-info btn-lg">开启审核</button>
            <button id="examine-stop" type="button" onclick="fun(0)" class="btn btn-warning btn-lg">关闭审核</button>
        </div>
        <br />
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3>信息审核<a class="anchorjs-link" href="#"><span class="anchorjs-icon"></span></a></h3>
                    <br />
                    <div class="row">
                        <div class="col-lg-2"></div>
                        <div class="col-lg-8" align="center">
                            <h3>您可以对以下信息进行审核, 普通用户可以查看审核通过的信息</h3>
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
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <div id="filters-div">
                    <form id="examine-ok" method="post">
                        <div class="row">
                            <button type="button" class="btn btn-success" onclick="showUnexamine()">未审核</button>
                            <button type="button" class="btn btn-warning" onclick="showDeleted()">已删除</button>
                        </div>
                        <br />
                        <div class="row">
                            <table class="table table-striped table-bordered table-hover" id="filters-table" style="table-layout: fixed;">
                                <thead>
                                <tr>
                                    <th class="text-center" width="30px"></th>
                                    <th class="text-center" width="50px">序号</th>
                                    <th class="text-center" width="50px">来源</th>
                                    <th class="text-center" width="100px">获取时间</th>
                                    <th class="text-center" width="100px">发布时间</th>
                                    <th class="text-center" width="280px">标题</th>
                                    <th class="text-center" width="140px">关键字</th>
                                    <th class="text-center" width="60px">状态</th>
                                    <th class="text-center" width="80px">设置</th>
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
                        <div class="row">
                            <div class="col-lg-12" align="right">
                                <input class="btn btn-danger btn-lg" onclick="submit_form(0)" type="button" value="删除记录" data-toggle="tooltip2" data-placement="top" title="审核淘汰" />
                                <input class="btn btn-info btn-lg" onclick="submit_form(1)" type="button" value="通过审核" data-toggle="tooltip2"  data-placement="top" title="审核通过" />
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-1"></div>
        </div>
        <script>
            var nowPage = 0, param = 2;
            var flag = false;
            function getPage(start) {
                var link = "<%=request.getContextPath()%>/api/quake/getall?count=10&status="+param+"&start=";
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
                    row.setAttribute("class", "text-info");

                    var col_select = document.createElement("th");
                    col_select.setAttribute("class", "text-center");
                    var select = document.createElement("input");
                    select.setAttribute("type", "checkbox");
                    select.setAttribute("name", "id");
                    select.setAttribute("value", data[i].id);
                    col_select.appendChild(select);
                    row.appendChild(col_select);

                    var num = document.createElement("td");
                    num.setAttribute("class", "text-center");
                    num.innerHTML = data[i].id;
                    row.appendChild(num);

                    var type = document.createElement("td");
                    type.setAttribute("class", "text-center");
                    var span = document.createElement("span");
                    span.setAttribute("class", "label label-default");
                    span.innerHTML = data[i].type;
                    type.appendChild(span);
                    row.appendChild(type);

                    var createTime = document.createElement("th");
                    createTime.setAttribute("class", "text-center");
                    createTime.innerHTML = data[i].createTime;
                    row.appendChild(createTime);

                    var pageTime = document.createElement("th");
                    pageTime.setAttribute("class", "text-center");
                    pageTime.innerHTML = data[i].publishTime;
                    row.appendChild(pageTime);

                    var title = document.createElement("th");
                    title.setAttribute("class", "text-center");
                    title.setAttribute("style", "overflow-x:hidden;");
                    title.appendChild(document.createTextNode(data[i].title));
                    row.appendChild(title);

                    var desc = document.createElement("th");
                    desc.setAttribute("class", "text-center");
                    desc.innerHTML = data[i].description;
                    row.appendChild(desc);

                    var status = document.createElement("th");
                    status.setAttribute("class", "text-center");
                    var span_status = document.createElement("span");
                    span_status.setAttribute("class", "label label-info");
                    span_status.innerHTML = data[i].status;
                    status.appendChild(span_status);
                    row.appendChild(status);

                    var check = document.createElement("th");
                    check.setAttribute("class", "text-center");
                    var button = document.createElement("a");
                    button.setAttribute("class", "btn btn-success");
                    button.setAttribute("target", "_blank");
                    button.setAttribute("href", data[i].jumpTo);
                    button.innerHTML = "查看";
                    check.appendChild(button);
                    row.appendChild(check);
                    document.getElementById("filters-tbody").appendChild(row);
                }
            }

            function showDeleted() {
                param = 3;
                getPage(0)
            }
            function showUnexamine() {
                param = 2;
                getPage(0);
            }
        </script>
        <script>
            function fun(value) {

                $.ajax({
                    type: "get",
                    url: "<%=request.getContextPath()%>/api/examine/status",
                    success: function (msg) {    //msg是后台调用action时，你传过来的参数
                        var jsonObj = eval(msg);
                        if (jsonObj.code == 0) {
                            if (jsonObj.data == false && value == 0) {
                                alert("系统已经关闭");
                            }
                            else if (jsonObj.data == true && value == 1) {
                                alert("系统已经开启");
                            }
                            else {
                                var urlVal;
                                if (value == 1) {
                                    urlVal = "<%=request.getContextPath()%>/api/examine/start";
                                }
                                else if (value == 0) {
                                    urlVal = "<%=request.getContextPath()%>/api/examine/shutdown";
                                }
                                $.ajax({
                                    type: "get",
                                    url: urlVal,
                                    success: function (msg) {    //msg是后台调用action时，你传过来的参数
                                        var jsonObj = eval(msg);
                                        if (jsonObj.code == 0) {
                                            alert("操作成功");
                                        }
                                        else {
                                            alert(jsonObj.msg);
                                        }
                                    }
                                });
                            }
                        }
                        else {
                            alert(jsonObj.msg);
                        }
                    }
                });


            }

            function submit_form(status) {
                var urlVal = "<%=request.getContextPath()%>/api/quake/examine/";
                if (status == 0) {
                    urlVal = urlVal + "delete";
                }
                else if (status == 1) {
                    urlVal = urlVal + "pass";
                }
                var ajax_url = urlVal;
                var ajax_type = $("#examine-ok").attr('method');
                var ajax_data = $("#examine-ok").serialize();
                $.ajax({
                    type: ajax_type,
                    url: ajax_url,
                    data: ajax_data,
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        var jsonObj = eval(msg);
                        if (jsonObj.code == 0) {
                            alert("提交成功");
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
        <%@include file="../footer.jsp"%>
    </body>
</html>
