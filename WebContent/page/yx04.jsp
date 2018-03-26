<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/crm/bootstrap/css/bootstrap.min.css" />
<style type="text/css">
@import
url("/crm/css/right_page.css")
</style>
<link rel="stylesheet" href="/crm/css/size.css" />
<style type="text/css">
#table01 {
	width: 1000px;
	margin: 0 auto;
	margin-bottom: 10px;
}

#table02 {
	width: 1000px;
	margin: 0 auto;
}

#planeForm {
	margin-top: 10px;
	margin-left: 80px;
}
</style>
</head>
<body>
	<div id="right" class="size">
		<ol class="breadcrumb" id="breadcrumb">
			<li><span>营销管理</span></li>
			<li><span>客户开发计划</span></li>
			<li style="color: #269ABC;"><span>制定计划</span></li>
		</ol>
		<form action="" method="post">
			<div id="button1">
				<a href="/crm/dgp_ZXUpdateServlet.do?xs_no=${row.xs_no }" class="btn btn-primary btn-sm">执行计划</a> <input
					type="button" class="btn btn-default btn-sm" value="返回" />
			</div>
			<table id="table01" style="clear: right;"
				class="table table-bordered table-condensed">
				<tr>
					<td class="col-md-1" align="center" id="label01">编号</td>
					<td class="col-md-5">${row.xs_no }</td>
					<td class="col-md-1" align="center" id="label01">机会来源</td>
					<td class="col-lg-5">${row.xs02 }</td>
				</tr>
				<tr>
					<td align="center" id="label01">客户名称</td>
					<td>${row.xs03 }</td>
					<td align="center" id="label01">成功几率</td>
					<td>${row.xs04 }</td>
				</tr>
				<tr>
					<td align="center" id="label01">概要</td>
					<td colspan="3">${row.xs05 }</td>
				</tr>
				<tr>
					<td align="center" id="label01">联系人</td>
					<td>${row.xs06 }</td>
					<td align="center" id="label01">联系电话</td>
					<td>${row.xs07 }</td>
				</tr>
				<tr>
					<td align="center" id="label01">机会描述</td>
					<td colspan="3">${row.xs08 }</td>
				</tr>
				<tr>

				</tr>
				<tr>
					<td align="center" id="label01">创建人</td>
					<td>${row.cjr }</td>
					<td align="center" id="label01">创建时间</td>
					<td>${row.xs10 }</td>
				</tr>
				<tr>
					<td class="col-md-1" align="center" id="label01">指派给</td>
					<td class="col-md-5">${row.name }</td>
					<td class="col-md-1" align="center" id="label01">指派时间</td>
					<td class="col-md-5">${row.fp04 }</td>
				</tr>
			</table>
			<table id="table02" class="table table-bordered table-condensed">
				<tr>
					<td class="col-md-3" align="center" id="label01">日期</td>
					<td class="col-md-8" align="center" id="label01">计划项</td>
				</tr>
				<%-- <c:forEach var="ibm" items="${row }"> --%>
					<tr>
						<td>${row.jh06 }</td>
						<td>
							<div>
								<input class="input2" value="${row.jh07 }" style="width: 80%;">&nbsp;&nbsp;
								<input type="button" class="btn btn-primary btn-sm" value="保存" />&nbsp;
								<input type="button" class="btn btn-danger btn-sm" value="删除" />
							</div>
						</td>
					</tr>
		<%-- 		</c:forEach> --%>
				<!--<tr>
					<td colspan="6">
						<div>
							<input id="submitButton" type="submit" value="保存" class="btn btn-primary"/>	
						</div>
					</td>
				</tr>-->
			</table>
		</form>
		<div id="planeForm">
			<form class="form-inline" action="/crm/dgp_jhAddServlet.do"
				method="post">
				<input type="hidden" name="xs_no" value="${row.xs_no }"> <input
					type="hidden" name="fp_no" value="${row.fp_no }">
				<div class="form-group" style="display: inline-block;">
					<label>日期</label> 
					<input type="date" name="jh06" class="form-control" /> <label style="margin-left: 33px;">计划</label>
					<input type="text" name="jh07" style="width: 570px;"
						class="form-control" />
				</div>
				&nbsp;
				<button type="submit" class="btn btn-primary btn-sm">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加计划&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
</html>
