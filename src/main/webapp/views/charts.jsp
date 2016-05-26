<%--
  Created by IntelliJ IDEA.
  User: Glacier
  Date: 16/5/23
  Time: 下午3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page import="com.glacier.earthquake.monitor.server.pojo.Chart" %>--%>
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
    <script src="<%=request.getContextPath()%>/resource/js/highcharts.js"></script>
    <script src="<%=request.getContextPath()%>/resource/js/exporting.js"></script>
</head>
<body>
<%@include file="header.jsp"%>
<%@include file="system.jsp"%>
<%@include file="navbar.jsp"%>
<%--<%Chart chart = new Chart();%>--%>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <div class="alert alert-success" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">x</span>
            </button>
            <p>本系统自2016年5月20日开始收集数据</p>
        </div>
    </div>
    <div class="col-md-1"></div>
</div>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-4">
        <div class="row">
            <div class="alert alert-info" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
                <h4 id="today"></h4>
                <p id="total"></p>
                <p id="today_count"></p>
                <p id="yes_count"></p>
            </div>
        </div>
        <div class="row">
            <div class="alert alert-danger" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
                <p id="enable"></p>
                <p id="disable"></p>
                <p id="unverify"></p>
            </div>
        </div>
    </div>
    <br />
    <div class="col-md-6">
        <div class="row">
            <div id="zhe1" style="min-width:600px; height:343px"></div>
        </div>
    </div>
</div>
<br /><br /><br /><br />
<div class="row">
    <div class="col-md-4">
        <div id="bing1" style="min-width:500px; height:286px"></div>
    </div>
    <div class="col-md-4">
        <div id="bing2" style="min-width:500px; height:286px"></div>
    </div>
    <div class="col-md-4">
        <div id="bing3" style="min-width:500px; height:286px"></div>
    </div>
</div>
<br /><br /><br /><br />
<script>
    $(function() {
        $(".unlogin").click(function(){
            document.getElementById("menu").click();
            document.getElementById("login").click();
        });
    });
</script>
<script>
    $(function () {
        $.ajax({
            url: "<%=request.getContextPath()%>/api/quake/getGatherInfo",
            type: "GET",
            dataType: "json",
            success: function (data) {
                var jsonObj = eval(data);
                if (jsonObj.code == 0) {
                    var charts = jsonObj.data;
                    $("#today").text(charts.today);
                    $("#total").text("系统收录信息：" + charts.total + " 条");
                    $("#yes_count").text("昨日获取数据：" + charts.yes_count + " 条");
                    $("#today_count").text("今日获取数据：" + charts.today_count + " 条");
                    $("#enable").text("已通过审核信息：" + charts.enable + "条");
                    $("#disable").text("未通过审核信息：" + charts.disable + "条");
                    $("#unverify").text("未审核信息：" + charts.unverify + "条");
                }
            }
        });
    });
    $(function () {
        $.ajax({
            url: "<%=request.getContextPath()%>/api/quake/getByType",
            type: "GET",
            dataType: "json",
            success: function (data) {
                var jsonObj = eval(data);
                if (jsonObj.code == 0) {
                    var charts = jsonObj.data;
                    var big = new Array();
                    for (var i = 0; i < charts.length; i++) {
                        var small = new Array();
                        small[0] = charts[i].type;
                        small[1] = parseInt(charts[i].count);
                        big[i] = small;
                    }
                    $('#bing1').highcharts({
                        chart: {
                            plotBackgroundColor: null,
                            plotBorderWidth: null,
                            plotShadow: false
                        },
                        title: {
                            text: '信息来源分布'
                        },
                        tooltip: {
                            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                        },
                        plotOptions: {
                            pie: {
                                allowPointSelect: true,
                                cursor: 'pointer',
                                dataLabels: {
                                    enabled: true,
                                    color: '#000000',
                                    connectorColor: '#000000',
                                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                                }
                            }
                        },
                        series: [{
                            type: 'pie',
                            name: '来源比例',
                            data: big
                        }]
                    });
                }
            }
        });

    });
</script>
<script>
    $(function () {
        $.ajax({
            url: "<%=request.getContextPath()%>/api/quake/getByKeywords",
            type: "GET",
            dataType: "json",
            success: function (data) {
                var jsonObj = eval(data);
                if (jsonObj.code == 0) {
                    var charts = jsonObj.data;
                    var big = new Array();
                    for (var i = 0; i < charts.length; i++) {
                        var j = charts.length - 1 - i;
                        var small = new Array();
                        small[0] = charts[i].keywords;
                        small[1] = parseInt(charts[i].count);
                        big[j] = small;
                    }
                    $('#bing2').highcharts({
                        chart: {
                            plotBackgroundColor: null,
                            plotBorderWidth: null,
                            plotShadow: false
                        },
                        title: {
                            text: '信息采集关键字分布'
                        },
                        tooltip: {
                            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                        },
                        plotOptions: {
                            pie: {
                                allowPointSelect: true,
                                cursor: 'pointer',
                                dataLabels: {
                                    enabled: true,
                                    color: '#000000',
                                    connectorColor: '#000000',
                                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                                }
                            }
                        },
                        series: [{
                            type: 'pie',
                            name: '关键字占比',
                            data: big
                        }]
                    });
                }
            }
        });
    });
</script>
<script>
    $(function () {
        $.ajax({
            url: "<%=request.getContextPath()%>/api/quake/getByPublishTime",
            type: "GET",
            dataType: "json",
            success: function (data) {
                var jsonObj = eval(data);
                if (jsonObj.code == 0) {
                    var charts = jsonObj.data;
                    var big = new Array();
                    for (var i = 0; i < charts.length; i++) {
                        var small = new Array();
                        small[0] = charts[i].publish_time;
                        small[1] = parseInt(charts[i].count);
                        big[i] = small;
                    }
                    $('#bing3').highcharts({
                        chart: {
                            plotBackgroundColor: null,
                            plotBorderWidth: null,
                            plotShadow: false
                        },
                        title: {
                            text: '信息发布时间分布'
                        },
                        tooltip: {
                            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                        },
                        plotOptions: {
                            pie: {
                                allowPointSelect: true,
                                cursor: 'pointer',
                                dataLabels: {
                                    enabled: true,
                                    color: '#000000',
                                    connectorColor: '#000000',
                                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                                }
                            }
                        },
                        series: [{
                            type: 'pie',
                            name: '信息发布时间占比',
                            data: big
                        }]
                    });
                }
            }
        });
    });
</script>
<script>
    $(function () {
            $.ajax({
            url: "<%=request.getContextPath()%>/api/quake/getByDate",
            type: "GET",
            dataType: "json",
            success: function(data) {
                var jsonObj = eval(data);
                if (jsonObj.code == 0) {
                    var charts = jsonObj.data;
                    var axis = new Array();
                    var datas = new Array();
                    for (var i = 0; i < charts.length; i ++) {
                        axis[i] = charts[i].date;
                        datas[i] = parseInt(charts[i].count);
                    }
                    $('#zhe1').highcharts({
                        title: {
                            text: '近期数据采集情况',
                            x: -20
                        },
                        xAxis: {
                            categories: axis,
                            type: 'datetime'
                        },
                        yAxis: {
                            title: {
                                text: '信息数目(条)'
                            },
                            plotLines: [{
                                value: 0,
                                width: 1,
                                color: '#808080'
                            }]
                        },
                        tooltip: {
                            valueSuffix: '条'
                        },
                        legend: {
                            layout: 'vertical',
                            align: 'right',
                            verticalAlign: 'middle',
                            borderWidth: 0
                        },
                        series: [{
                            name: '采集情况',
                            data: datas
                        }]
                    });
                }
                else {
                    alert(jsonObj.msg);
                }
            }
        });
    });
</script>
<%@include file="footer.jsp"%>
</body>
</html>
