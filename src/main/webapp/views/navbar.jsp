<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="header-div">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-left">
                    <li><a href="<%=request.getContextPath()%>/index"><h4>主页</h4></a></li>
                    <li><a href="#" onclick="system()"><h4>系统</h4></a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            <h4>知识库管理<span class="caret"></span></h4></a>
                        <ul class="dropdown-menu" role="menu" style="background-color: #dcecf7">
                            <li><a href="<%=request.getContextPath()%>/settings/disaster">灾情获取匹配式管理</a></li>
                            <%--<li class="divider"></li>--%>
                            <%--<li><a href="<%=request.getContextPath()%>/settings/manage-public.jsp">舆情监测匹配式管理</a></li>--%>
                            <li class="divider"></li>
                            <li><a href="<%=request.getContextPath()%>/settings/whitelist">白名单管理</a></li>
                        </ul>
                    </li>
                    <li><a href="<%=request.getContextPath()%>/settings/examine"><h4>审核管理</h4></a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            <h4>设置<span class="caret"></span></h4></a>
                        <ul class="dropdown-menu" role="menu" style="background-color: #dcecf7">
                            <li><a href="<%=request.getContextPath()%>/settings/warning">报警设置</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                            <h4>用户管理<span class="caret"></span></h4></a>
                        <ul class="dropdown-menu" role="menu" style="background-color: #dcecf7">
                            <li><a href="<%=request.getContextPath()%>/settings/setting">修改资料</a></li>
                            <li class="divider"></li>
                            <li><a href="<%=request.getContextPath()%>/settings/setting?password=true">修改密码</a></li>
                            <li class="divider"></li>
                            <li><a href="<%=request.getContextPath()%>/settings/user">用户管理</a></li>
                        </ul>
                    </li>
                    <li><a href="<%=request.getContextPath()%>/about"><h4>关于</h4></a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <%if ( request.getServletPath().equals("/views/showdata.jsp") ) {%>
                    <li style="background-color: #eeeeff"><a href="<%=request.getContextPath()%>/charts"><h4>查看数据概览</h4></a></li>
                    <%}if ( request.getServletPath().equals("/views/charts.jsp") ) {%>
                    <li style="background-color: #eeeeff"><a href="<%=request.getContextPath()%>/showdata"><h4>查看数据记录</h4></a></li>
                    <%--<%}if ( request.getServletPath().contains("/settings/") ) {%>--%>
                    <%--<li style="background-color: #eeeeff"><a href="<%=request.getContextPath()%>/showdata"><h4>查看数据记录</h4></a></li>--%>
                    <%}if ( request.getServletPath().equals("/views/settings/manage-disaster.jsp") ) {%>
                    <li style="background-color: #eeeeff"><a href="#"><h4>灾情获取匹配式管理</h4></a></li>
                    <%}if ( request.getServletPath().equals("/views/settings/manage-whitelist.jsp") ) {%>
                    <li style="background-color: #eeeeff"><a href="#"><h4>白名单管理</h4></a></li>
                    <%}if ( request.getServletPath().equals("/views/settings/manage-warning.jsp") ) {%>
                    <li style="background-color: #eeeeff"><a href="#"><h4>报警设置</h4></a></li>
                    <%}if ( request.getServletPath().equals("/views/settings/manage-examine.jsp") ) {%>
                    <li style="background-color: #eeeeff"><a href="#"><h4>审核管理</h4></a></li>
                    <%}if ( request.getServletPath().equals("/views/settings/setting.jsp") ) {%>
                    <li style="background-color: #eeeeff"><a href="#"><h4>修改资料</h4></a></li>
                    <%}if ( request.getServletPath().equals("/views/settings/manage-user.jsp") ) {%>
                    <li style="background-color: #eeeeff"><a href="#"><h4>用户管理</h4></a></li>
                    <%}if ( request.getServletPath().equals("/views/about.jsp") ) {%>
                    <li style="background-color: #eeeeff"><a href="#"><h4>关于</h4></a></li>
                    <%}%>
                </ul>
            </div>
        </div>
    </nav>
</div>
