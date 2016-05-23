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
<%--<%@include file="header.jsp"%>--%>
<%@include file="system.jsp"%>
<%@include file="navbar.jsp"%>
<div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">
        <div class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <h4>查看数据记录<a class="anchorjs-link" href="#"><span class="anchorjs-icon"></span></a></h4>
            <br/>

            <div class="row">
                <div class="col-lg-2"></div>
                <div class="col-lg-8" align="center">
                    <p>以下是系统收集到的符合条件的数据记录信息</p>
                </div>
                <div class="col-lg-2"></div>
            </div>
        </div>
    </div>
    <div class="col-md-3"></div>
</div>
<br/><br/>


<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <div id="filters-div">
            <div class="row">
                <button type="button" class="btn btn-info" onclick="showAll()">全部</button>
                <button type="button" class="btn btn-warning" onclick="showDis()">灾情获取</button>
                <button type="button" class="btn btn-success" onclick="showPub()">舆情监控</button>
                <a type="button" class="btn btn-default" href="<%=request.getContextPath()%>/settings/manage-whitelist.jsp#tips">白名单</a>
            </div>
            <br />
            <div class="row">
                <table class="table table-striped table-bordered table-hover" id="filters-table" style="table-layout: fixed;">
                    <thead>
                    <tr>
                        <th class="text-center" width="50px">序号</th>
                        <th class="text-center" width="60px">来源</th>
                        <th class="text-center" width="110px">获取时间</th>
                        <th class="text-center" width="110px">发布时间</th>
                        <th class="text-center" width="320px">标题</th>
                        <th class="text-center" width="170px">关键字</th>
                        <th class="text-center" width="80px">状态</th>
                        <th class="text-center" width="100px">审核人</th>
                        <th class="text-center" width="110px">审核时间</th>
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
            <br/><br/><br/>
        </div>
    </div>
    <div class="col-md-1"></div>
</div>
<script>
    var nowPage = 0;
    var flag = false;
    function getPage(start) {
        var link = "<%=request.getContextPath()%>/api/quake/getall?count=10&start=";
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

            var examiner = document.createElement("th");
            examiner.setAttribute("class", "text-center");
            examiner.innerHTML = data[i].manager;
            row.appendChild(examiner);

            var examine_date = document.createElement("th");
            examine_date.setAttribute("class", "text-center");
            examine_date.innerHTML = data[i].verifyTime;
            row.appendChild(examine_date);

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
</script>
<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
    function show(value) {
        $.ajax({
            type: "get",
            url: "<%=request.getContextPath()%>/SettingServlet?operate=spiderinfo",
            data: "id=" + value,
            dataType: "json",
            success: function (msg) {    //msg是后台调用action时，你传过来的参数
                var objson = eval(msg);

                if ( objson.source != null ) {
                    $("#filter-iframe").attr("hidden","");
                    $("#filter-source").removeAttr("hidden");
                    $("#filter-source").html(objson.source);
                }
                else {
                    $("#filter-source").attr("hidden", "");
                    $("#filter-iframe").removeAttr("hidden");
                    $("#filter-iframe").attr("src", objson.url);
                }

                if (objson.type == "disaster") {
                    $("#filter-type").html("信息类型: 灾情获取");
                    $("#filter-patten").remove();
                    $("#filter-unexist").remove();
                    $("#filter-rule").attr("title", objson.rule);
                } else if (objson.type == "public") {
                    $("#filter-type").html("信息类型: 舆情监测");
                    $("#filter-rule").attr("title", objson.name);
                    $("#filter-patten").attr("title", objson.matcher);
                    $("#filter-unexist").attr("title", objson.unexist);
                }
                $("#show-div").modal("toggle");
            }
        });
    }
    function showAll() {
        $("tr[status]").attr("style", "display:");
    }
    function showDis() {
        $("tr[status='0']").attr("style", "display:");
        $("tr[status='1']").attr("style", "display:none");
    }
    function showPub() {
        $("tr[status='1']").attr("style", "display:");
        $("tr[status='0']").attr("style", "display:none");
    }
</script>
<script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
<%@include file="footer.jsp" %>
</body>
</html>
