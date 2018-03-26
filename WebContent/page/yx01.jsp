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
			<li style="color: #269ABC;"><span>销售机会管理</span></li>
			<li><span id="msg"></span></li>
		</ol>
		<form action="/crm/dgp_MhselectServlet.do" method="post">
			<table id="table01" class="table table-bordered">
				<tr>
					<td align="center" id="label01">客户名称</td>
					<td><input class="form-control" name="xs03" /></td>
					<td align="center" id="label01">概要</td>
					<td><input class="form-control" name="xs05" /></td>
					<td align="center" id="label01">联系人</td>
					<td><input class="form-control" name="xs07" /></td>
				</tr>
				<tr>
					<td colspan="6">
						<div>
							<a id="resetButton" class="btn btn-default"
								href="/crm/page/yx02.jsp">新建销售机会</a> <input id="submitButton"
								type="submit" value="查询" class="btn btn-primary" />
						</div>
					</td>
				</tr>
			</table>
		</form>
		<table id="table02" class="table table-bordered table-hover">
			<tr>
				<th>编号</th>
				<th>客户名称</th>
				<th>概要</th>
				<th>联系人</th>
				<th>联系人电话</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			<c:choose>
				<c:when test="${sessionScope.userInfoMap.us03 == '销售主管'}">
					<c:forEach var="ibm" items="${rows }">
						<tr id="t_${ibm.xs_no }">
							<td>${ibm.xs_no }</td>
							<td>${ibm.xs03 }</td>
							<td>${ibm.xs05 }</td>
							<td>${ibm.xs06 }</td>
							<td>${ibm.xs07 }</td>
							<td>${ibm.xs10 }</td>
							<td class="col-md-2 operation1"><a href="/crm/dgp_FpSelectServlet.do?xs_no=${ibm.xs_no }">
									<span class="glyphicon glyphicon-hand-right span1"></span>
							</a> <a href="/crm/dgp_UpdateSelectServlet.do?xs_no=${ibm.xs_no }"> <span
									class="glyphicon glyphicon-pencil span1"></span>
							</a> <a href="#" onclick="dodelete('${ibm.xs_no }')"> <span
									class="glyphicon glyphicon-remove span1"></span>
									 <input type="hidden" id="xs_no" value=""/> 
							</a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="ibm" items="${rows }">
						<tr id="t_${ibm.xs_no }">
							<td>${ibm.xs_no }</td>
							<td>${ibm.xs03 }</td>
							<td>${ibm.xs05 }</td>
							<td>${ibm.xs06 }</td>
							<td>${ibm.xs07 }</td>
							<td>${ibm.xs10 }</td>
							<td class="col-md-2 operation1">
							<a href="/crm/dgp_UpdateSelectServlet.do?xs_no=${ibm.xs_no }"> <span
									class="glyphicon glyphicon-pencil span1"></span>
							</a> 
							<a href="#" onclick="dodelete('${ibm.xs_no }')"> <span
									class="glyphicon glyphicon-remove span1"></span>
									 <input type="hidden" id="xs_no" value=""/> 
							</a></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tr>
				<td colspan="7">
					<p align="center" style="padding-top: 10px;">
						共59条记录 每页<input type="text" size="1" class="input1" />条 第 <input
							type="text" size="1" class="input1" />页/共5页 <a href="">第一页</a> <a
							href="">上一页</a> <a href="">下一页</a> <a href="">最后一页</a> 转到<input
							class="input1" type="text" size="1" />页
						<button size="1" class="btn btn-success btn-sm" class="input1">GO</button>
					</p>
				</td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
function dodelete(no){
	var url="/crm/dgp_deleteOneServlet.do";
	$.ajax({
		url:"/crm/dgp_deleteOneServlet.do",
		data:{xs_no:no},
		dataType:'json',
		success:function(data){
			if(data.status=='OK')
			{
				$("#t_"+no).remove();
			}
		},
		error:function(){
			alert("Error!");
		}
	});
}
</script>
</html>