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
			@import url("/crm/css/right_page.css")
		</style>
		<link rel="stylesheet" href="/crm/css/size.css" />
	</head>

	<body>
		<div id="right" class="size">
			<ol class="breadcrumb" id="breadcrumb">
				<li><span>营销管理</span></li>
				<li><span>客户开发计划</span></li>
				<li style="color: #269ABC;"><span>计划执行</span></li>
			</ol>
			<form action="" method="post">
				<div id="button1">
					<input type="button" class="btn btn-primary btn-sm" value="制定计划" />
					<a href="/crm/dgp_SuccessServlet.do?xs_no=${rows[0].xs_no }"><input type="button" class="btn btn-primary btn-sm" value="开发成功" /></a>
					<a href="/crm/dgp_FailServlet.do?xs_no=${rows[0].xs_no }"><input type="button" class="btn btn-primary btn-sm" value="终止开发" /></a>
					<input type="button" class="btn btn-default btn-sm" value="返回" />
				</div>
				<table id="table01" style="clear: right;" class="table table-bordered table-condensed">
					<tr>
						<td class="col-md-1" align="center" id="label01">编号</td>
						<td class="col-md-5">${rows[0].xs_no }</td>
						<td class="col-md-1" align="center" id="label01">机会来源</td>
						<td class="col-lg-5">${rows[0].xs02 }</td>
					</tr>
					<tr>
						<td align="center" id="label01">客户名称</td>
						<td>${rows[0].xs03 }</td>
						<td align="center" id="label01">成功几率</td>
						<td>${rows[0].xs04 }</td>
					</tr>
					<tr>
						<td align="center" id="label01">概要</td>
						<td colspan="3">${rows[0].xs05 }</td>
					</tr>
					<tr>
						<td align="center" id="label01">联系人</td>
						<td>${rows[0].xs06 }</td>
						<td align="center" id="label01">联系电话</td>
						<td>${rows[0].xs07 }</td>
					</tr>
					<tr>
						<td align="center" id="label01">机会描述</td>
						<td colspan="3">${rows[0].xs08 }</td>
					</tr>
					<tr>

					</tr>
					<tr>
						<td align="center" id="label01">创建人</td>
						<td>${rows[0].cjr }</td>
						<td align="center" id="label01">创建时间</td>
						<td>${rows[0].xs10 }</td>
					</tr>
					<tr>
						<td class="col-md-1" align="center" id="label01">指派给</td>
						<td class="col-md-5">${rows[0].name }</td>
						<td class="col-md-1" align="center" id="label01">指派时间</td>
						<td class="col-md-5">${rows[0].fp04 }</td>
					</tr>
				</table>
				<table id="table02" class="table table-bordered table-condensed">
					
					<tr>
						<td class="col-md-3" align="center" id="label01">日期</td>
						<td class="col-md-5" align="center" id="label01">计划</td>
						<td class="col-md-4" align="center" id="label01">执行效果</td>
					</tr>
					
					<c:forEach var="ibm" items="${rows[1] }">
					<form action="/crm/dgp_ZXJGServlet.do" method="post"> 
					<tr>
						<input type="hidden" name="xs_no" value="${ibm.xs_no }"/>
						<td>${ibm.jh06 }<input type="hidden" name="jh01" value="${ibm.jh01 }"/></td>
						<td>${ibm.jh07 }<input type="hidden" name="jh07" value="${ibm.jh07 }"/></td>
						<td>
							<div>
							<%-- value="${rows[0].jh09 }" --%>
								<input class="input2" type="text" name="jh09" value="${ibm.jh09 }" style="width: 80%;"/>&nbsp;&nbsp;
								<input type="submit" class="btn btn-primary btn-sm" value="保存" />&nbsp;
							</div>
						</td>
					</tr>
					</form>
					</c:forEach>
					<!--<tr>
					<td colspan="6">
						<div>
							<input id="submitButton" type="submit" value="保存" class="btn btn-primary"/>	
						</div>
					</td>
				</tr>-->
				</table>
			</form>
		</div>
	</body>
	<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>

</html>