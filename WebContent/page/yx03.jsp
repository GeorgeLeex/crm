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
</head>
<body>
	<div id="right" class="size">
		<ol class="breadcrumb" id="breadcrumb">
			<li><span>营销管理</span></li>
			<li><span>销售机会管理</span></li>
			<li style="color: #269ABC;"><span>指派销售机会</span></li>
			<li><span>${msg }</span></li>
		</ol>
		<form action="/crm/dgp_FpAddServlet.do?xs_no=${row.xs_no}"
			method="post">
			<table id="table01" class="table table-bordered">
				<tr>
					<td class="col-md-1" align="center" id="label01">编号</td>
					<td class="col-md-5">${row.xs_no}</td>
					<td class="col-md-1" align="center" id="label01">机会来源</td>
					<td class="col-lg-5">${row.xs02}</td>
				</tr>
				<tr>
					<td align="center" id="label01">客户名称</td>
					<td>${row.xs03}</td>
					<td align="center" id="label01">成功几率</td>
					<td>${row.xs04}</td>
				</tr>
				<tr>
					<td align="center" id="label01">概要</td>
					<td colspan="3">${row.xs05}</td>
				</tr>
				<tr>
					<td align="center" id="label01">联系人</td>
					<td>${row.xs06}</td>
					<td align="center" id="label01">联系电话</td>
					<td>${row.xs07}</td>
				</tr>
				<tr>
					<td align="center" id="label01">机会描述</td>
					<td colspan="3">${row.xs08}</td>
				</tr>
				<tr>

				</tr>
				<tr>
					<td align="center" id="label01">创建人</td>
					<td>${userInfoMap.name}</td>
					<td align="center" id="label01">创建时间</td>
					<td>${row.xs10 }</td>
				</tr>
			</table>
			<table id="table02" class="table table-bordered" >
				<tr>
					<td class="col-md-1" align="center" id="label01">指派给</td>
					<td class="col-md-5">
					<select class="form-control" name="select">
							<c:forEach var="ibm" items="${rows }">
								<option value="${ibm.name }">${ibm.name }</option>
							</c:forEach>
					</select>
					</td>
					<td class="col-md-1" align="center" id="label01">指派时间</td>
					<td class="col-md-5"><input class="form-control"
						readonly="readonly" value="当前时间" /></td>
				</tr>
				<tr>
					<td colspan="6">
						<div>
							<input id="resetButton" type="button" value="返回"
								class="btn btn-default" /> <input id="submitButton"
								type="submit" value="保存" class="btn btn-primary" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
</html>
