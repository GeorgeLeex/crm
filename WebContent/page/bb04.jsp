<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="/crm/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/crm/css/size.css" />
<style type="text/css">
* {
	padding: 0;
	margin: 0;
}

#right {
	background-color: #F1F1F1;
	display: inline-block;
	margin: 0;
	position: fixed;
}

#breadcrumb {
	/*margin-top: 50px;*/
	/*height: 50px;*/
	padding-left: 80px;
}

#breadcrumb span {
	font-size: large;
}

#table01 {
	width: 1000px;
	margin: 0 auto;
}

#table02, #table03, #table04 {
	width: 1000px;
	margin: 10px auto;
}

#label01 {
	vertical-align: middle;
	font-size: 16px;
}

#resetButton {
	margin-left: 88%;
}

#submitButton {
	float: right;
}

tr td, th {
	text-align: center;
}
</style>
</head>
<body>
	<div id="left"></div>
	<div id="right" class="size">
		<ol class="breadcrumb" id="breadcrumb">
			<li><span>统计报表</span></li>
			<li style="color: #269ABC;"><span>客户流失分析</span></li>
		</ol>
		<form action="" method="post">
			<table id="table01" class="table table-bordered">
				<tr>
					<td align="center" id="label01">客户名称</td>
					<td><input class="form-control" name="khname" autofocus="autofocus"></input></td>
					<td align="center"  id="label01">客户经理名称</td>
					<td><input class="form-control" name="usname" /></td>
					<td><input type="submit" value="查询"   class="btn btn-primary" /></td>
				</tr>
			</table>
			<table id="table02" class="table table-bordered table-hover">
				<tr>
					<th>编号</th>
					<th>年份</th>
					<th>客户名称</th>
					<th>客户经理名称</th>
					<th>流失原因</th>
				</tr>
				<c:forEach var="ibm" items="${rows }">
				<tr>
					<td>${ibm.klno }</td>
					<td>${ibm.years }</td>
					<td>${ibm.khname }</td>
					<td>${ibm.usname }</td>
					<td>${ibm.khls07 }</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="7" align="center">共99条记录,每页<input type="text"
						size="1" class="input1" />条, 第 <input type="text" size="1"
						class="input1" />页/ 共 <input type="text" size="1" class="input1" />页
						<a href="#">第一页</a> <a href="#">上一页</a> <a href="#">下一页</a> <a
						href="#">最后一页</a> 转到<input type="text" size="1" class="input1" />
						页 <a class="input1 btn btn-sm btn-success" href="#">GO</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
</html>
