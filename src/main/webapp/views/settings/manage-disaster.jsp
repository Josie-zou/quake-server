<%--
  Created by IntelliJ IDEA.
  User: glacier
  Date: 15-5-25
  Time: 下午8:30
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
        <br /><br />
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <form id="form-addfilter" method="get">
                    <div class="panel panel-info">
                        <div class="panel-heading">增加灾情获取匹配式</div>
                        <div class="panel-body">
                            <div id="filterinfo">
                                <div class="row" name="disaster" count="1">
                                    <div class="col-lg-12">
                                        <div class="input-group">
                                            <span class="input-group-addon">匹配规则</span>
                                            <input type="text" class="form-control" placeholder="乌恰*地震*人死亡" name="rule" value="" />
                                        </div>
                                    </div>
                                    <br /><br />
                                </div>
                            </div>
                            <!--filterinfo-->
                            <div class="row">
                                <div class="col-lg-4">
                                    <div class="input-group">
                                        <button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="bottom" title="增加一条规则" name="addfilter">
                                            <span class="glyphicon glyphicon-plus" />
                                        </button>
                                        <button type="button" class="btn btn-default " data-toggle="tooltip" data-placement="bottom" title="减少一条规则" name="minufilter">
                                            <span class="glyphicon glyphicon-minus" />
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12" align="right">
                                    <button type="submit" class="btn btn-info">提交</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-2"></div>
        </div>
        <br /><br />
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div id="filters-div">
                    <table class="table table-striped table-bordered table-hover" id="filters-table" style="table-layout: fixed;">
                        <thead>
                            <tr>
                                <th class="text-center" width="120px">创建人</th>
                                <th class="text-center" width="200">创建时间</th>
                                <th class="text-center">规则</th>
                                <th class="text-center" width="140px">设置</th>
                            </tr>
                        </thead>
                        <tbody id="filters-tbody"></tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-2"></div>
        </div>
        <div class="modal fade" id="modifyRule" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <form method="post" id="modifyForm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h3>修改规则</h3>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="input-group">
                                        <span class="input-group-addon">匹配规则</span>
                                        <input type="text" id="modifyName" class="form-control" name="rule" value="" />
                                        <input hidden="hidden" name="id" value="" id="modifyID" />
                                    </div>
                                </div>
                            </div>
                            <br /><br />
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success">更新规则</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script>
            $(function() {
                $("[name='addfilter']").click(function() {
                    var f = $("#filterinfo div:first");
                    q = f.clone();
                    q.find("input").attr("value", "");
                    q.attr("count", parseInt(q.attr("count"))+1);
                    $("#filterinfo").append(q.prop('outerHTML'));
                });
            });
            $(function() {
                $("[name='minufilter']").click(function() {
                    var o = $("#filterinfo");
                    var f = $("#filterinfo").find("div[name]:last");
                    if (o.children().length > 1 ) {
                        f.remove();
                    }
                });
            });
            function del(val)
            {
                $.ajax({
                    type: "get",
                    url: "<%=request.getContextPath()%>/api/filter/deleteRule",
                    data: "id="+val,
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        var jsonObj = eval(msg);
                        if (jsonObj.code == 0) {
                            alert("删除成功");
                            location.reload();
                        }
                        else {
                            alert(jsonObj.msg);
                        }
                    }
                });
            }
        </script>
        <script>
            createTable();
            function createTable() {
                $.ajax({
                    url: "<%=request.getContextPath()%>/api/filter/getAll",
                    type: "GET",
                    dataType: "json",
                    success: function(data) {
                        var jsonObj = eval(data);
                        if (jsonObj.code == 0) {
                            var objson = jsonObj.data;
                            for (var i = 0; i < objson.length; i++) {
                                var row = document.createElement("tr");
                                row.setAttribute("id", objson[i].id);
                                row.setAttribute("class", "text-info");
                                var col0 = document.createElement("td");
                                col0.setAttribute("valign", "middle");
                                col0.setAttribute("class", "text-center");
                                var span0 = document.createElement("span");
                                span0.setAttribute("class", "label label-default");
                                span0.innerHTML = objson[i].username;
                                col0.appendChild(span0);
                                row.appendChild(col0);
                                var col1 = document.createElement("th");
                                col1.setAttribute("class", "text-center");
                                col1.appendChild(document.createTextNode(objson[i].createTime));
                                row.appendChild(col1);
                                var col2 = document.createElement("th");
                                col2.setAttribute("class", "text-center");
                                col2.setAttribute("name", "name");
                                col2.appendChild(document.createTextNode(objson[i].rule));
                                row.appendChild(col2);

                                var col5 = document.createElement("th");
                                col5.setAttribute("class", "text-center");
                                var button = document.createElement("button");
                                button.setAttribute("class", "btn btn-warning");
                                button.setAttribute("value", objson[i].id);
                                button.setAttribute("name", "filter");
                                button.setAttribute("onClick", "modify(this.value)");
                                button.innerHTML = "修改";

                                var button2 = document.createElement("button");
                                button2.setAttribute("class", "btn btn-danger");
                                button2.setAttribute("value", objson[i].id);
                                button2.setAttribute("name", "filter");
                                button2.setAttribute("onClick", "del(this.value)");
                                button2.innerHTML = "删除";

                                var text = document.createTextNode(" ");
                                col5.appendChild(button);
                                col5.appendChild(text);
                                col5.appendChild(button2);
                                row.appendChild(col5);

                                document.getElementById("filters-tbody").appendChild(row);
                            }
                        }
                        else{
                            alert(jsonObj.msg);
                        }
                    }
                });
            }
        </script>
        <script>
            function modify(val)
            {
                var row = document.getElementById(val);
                $("#modifyID").attr("value", val);
                if ( row.querySelector('[name=name]').innerHTML != null ) {
                    $("#modifyName").attr("value", row.querySelector('[name=name]').innerHTML);
                }
                else {
                    $("#modifyName").attr("value", "");
                }
                $("#modifyRule").modal("toggle");
            }
            $("#modifyForm").submit(function() {
                var ajax_url = "<%=request.getContextPath()%>/api/filter/update";
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
                            location.reload();
                        }
                        else {
                            alert(jsonObj.msg);
                        }
                    }
                });
            });
            $("#form-addfilter").submit(function() {
                var ajax_url = "<%=request.getContextPath()%>/api/filter/addRule";
                var ajax_type = $(this).attr('method');
                var ajax_data = $(this).serialize();
                $.ajax({
                    type: ajax_type,
                    url: ajax_url,
                    data: ajax_data,
                    success: function(msg) {    //msg是后台调用action时，你传过来的参数
                        var jsonObj = eval(msg);
                        if (jsonObj.code == 0) {
                            alert("添加成功");
                            location.reload();
                        }
                        else {
                            alert(jsonObj.msg);
                        }
                    }
                });
                return false;   //阻止表单的默认提交事件
            });
            function uploadiv() {
                $("#uploadiv").modal("toggle");
            }
            $(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
        <script src="<%=request.getContextPath()%>/resource/js/menu.js"></script>
        <%@include file="../footer.jsp"%>
    </body>
</html>