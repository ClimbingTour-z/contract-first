<%--
  Created by IntelliJ IDEA.
  User: Da
  Date: 2018/9/19
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 这个是重点！！！jstl使用获取数据的前提-->
    <%@ page isELIgnored="false" %>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>合同视图</title>

    <!-- Bootstrap3.3.7 核心 CSS 文件 -->
    <link href="/static/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>

    <!--[if lt IE 9]>
    <script type="text/javascript" src="/static/js/html5shiv.min.js"/>
    <script type="text/javascript" src="/static/js/respond.min.js"/>
    <![endif]-->

</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="/excel/imExcel">上传</a></li>
    <li class="active"><a href="/excel/List">合同列表</a></li>
    <li><a href="/excel/checkCA">合同总金额</a></li>
    <li><a href="/excel/checkRS">回款详情</a></li>
</ul>


    <%--条件查询--%>
    <form id="search" action="/excel/List" method="post" class="breadcrumb form-search">
        <div style="margin-top:8px;">
            <label>签署年度：</label><input id="contractSignYear" name="contractSignYear" type="text" maxlength="50" class="input-mini" />
            <label>项目编码：</label><input id="itemCoding" name="itemCoding" type="text" maxlength="50" class="input-mini" />
            <label>销售人员：</label><input id="salePerson" name="salePerson" type="text" maxlength="50" class="input-mini" />
            &nbsp;&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
        </div>
    </form>

    <div layout:fragment="content">
        <c:if test="${empty compactList.list}">
            <div class="alert alert-warning" role="alert">
                <span class="glyphicon glyphicon-info-sign" aria-hidden="true">
                </span>没有查询到相关信息，请<a href="/excel/imExcel" type="button" class="btn btn-primary btn-sm">上传信息！</a>
            </div>
        </c:if>
        <%--合同详情表--%>
        <c:if test="${!empty compactList.list}">
        <table id="compactTable" class="table table-striped table-bordered table-condensed">
            <tr>
                <th>合同签署年度</th><th>项目编码</th><th>销售人员</th><th>项目名称</th>
                <th>合同额</th><th>2015年开票金额</th><th>2015年回款金额</th><th>2016年开票金额</th>
                <th>2016年回款日期</th><th>2016年回款金额</th><th>2017年开票金额</th><th>2017年回款日期</th>
                <th>2017年回款金额</th><th>2018年开票日期</th><th>2018年开票金额</th><th>2018年回款日期</th>
                <th>2018年回款金额</th><th>开票应收款</th><th>开票合计</th><th>未开票金额</th>
                <th>回款合计</th><th>操作</th>
            </tr>
            <c:forEach items="${compactList.list}" var="compact">
                <tr>
                    <td>${compact.contractSignYear}</td>
                    <td>${compact.itemCoding}</td>
                    <td>${compact.salePerson}</td>
                    <td>${compact.projectName}</td>
                    <td>${compact.contractAmount}</td>
                    <td>${compact.invoiceAmount2015}</td>
                    <td>${compact.recoveredAmount2015}</td>
                    <td>${compact.invoiceAmount2016}</td>
                    <td>${compact.recoveredAmountDate2016}</td>
                    <td>${compact.recoveredAmount2016}</td>
                    <td>${compact.invoiceAmount2017}</td>
                    <td>${compact.recoveredAmountDate2017}</td>
                    <td>${compact.recoveredAmount2017}</td>
                    <td>${compact.invoiceAmount2018}</td>
                    <td>${compact.invoiceAmountDate2018}</td>
                    <td>${compact.recoveredAmount2018}</td>
                    <td>${compact.recoveredAmountDate2018}</td>
                    <td>${compact.due}</td>
                    <td>${compact.invoiceAmount}</td>
                    <td>${compact.unbilledAmount}</td>
                    <td>${compact.receivableTotal}</td>
                    <td>
                        <a href="/excel/delete?id=${compact.id}" onclick="return confirmx('要删除该合同信息吗？', this.href)">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
    </div>

    <!-- jquery 核心 JS 文件 -->
    <script type="text/javascript" src="/static/jquery/jquery-1.12.4.min.js"></script>
    <!-- Bootstrap3.3.7 核心 JavaScript 文件 -->
    <script type="text/javascript" src="/static/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
