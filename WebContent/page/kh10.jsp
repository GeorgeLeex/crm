<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<link rel="stylesheet" href="/crm/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/crm/css/size.css"/>
	<style type="text/css">
		#body {
			background-color: #F1F1F1;
			display: inline-block;
		}
		
		#b2 a {
			font-size: 16px;
				font-family: "微软雅黑";
				font-weight: 100;
		}
		
		#b2 {
			padding-left:70%;
    		margin-top: 30px;
		}
		
		#b3 {
			width: 1000px;
			margin: 10px auto;
		}
		
		.xx {
			color: red;
		}
		#label01{
			vertical-align: middle;
		}
		#breadcrumb span{
				font-size: large;
			}
		
		.t{
			vertical-align: middle;
			text-align: center;
		}
		.input1{
			border-radius: 4px;
		}
		
	</style>

	<body>
		<c:if test="${!empty row.khls05}">
			<c:set var="reprieves" value="${fn:split(row.khls05, ';')}"/>
		</c:if>
		<div id="body" class="size">
			<div id="b1">
				<!--<h3>&nbsp;&nbsp;&nbsp;客户流失管理 >确认流失</h3>-->
				<ol class="breadcrumb" style="margin-bottom: 0px;padding-left: 72px;" id="breadcrumb">
				<li><span>客户管理</span></li>	
				<li><span>客户流失管理</span></li>
				<li style="color: #269ABC;"><span>${empty requestScope.flag?"暂缓":"确认"}流失</span></li>
				</ol>
			</div>
			<div id="b2">
    			<a class="btn btn-xs btn-primary" href="/crm/lx_FindAllCustomerLoss.do?nowPage=${nowPage}">返回</a>
    			<c:choose>
    				<c:when test="${!empty requestScope.flag}">
    					<a class="btn btn-xs btn-primary" href="/crm/lx_FindOneCustomerLoss.do?khls_no=${row.khls_no}&nowPage=${nowPage}">暂缓流失</a>
    				</c:when>
    				<c:otherwise>
    					<a class="btn btn-xs btn-primary" href="/crm/lx_FindOneCustomerLoss.do?khls_no=${row.khls_no}&nowPage=${nowPage}&flag=loss">确认流失</a>
    				</c:otherwise>
    			</c:choose>
    			<a class="btn btn-xs btn-primary" onclick="${empty requestScope.flag? 'save':'loss'}()">保存</a>
			</div>
			<div id="b3">
				<form id="form01" action="/crm/lx_AddReprieve.do" method="post" class="form-horizontal">
					<input type="hidden" name="khls_no" value="${row.khls_no}">
					<input type="hidden" name="kh_no" value="${row.kh_no}">
					<table class="table table-bordered table-hover">
						<tr>
							<td class="col-md-2 t" align="center" id="label01">编号</td>
							<td class="col-md-4 t">${row.khls_no}</td>
							<td class="col-md-2 t" align="center" id="label01">客户</td>
							<td class="col-md-4 t">${row.kh02}</td>
						</tr>
						<tr>
							<td class="t" align="center" id="label01">客户经理</td>
							<td class="t">${row.name}</td>
							<td class="t" align="center" id="label01">上次下单时间</td>
							<td class="t">${row.khls09}</td>
						</tr>
						<tr>
							<td align="center" id="label01">暂缓措施</td>
							<td colspan="3">
								<c:if test="${!empty reprieves}">
									<c:forEach var="reprieve" items="${reprieves}">
										<c:out value="${reprieve}。"></c:out><br/>
									</c:forEach>
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="center" id="label01">${empty requestScope.flag? "追加暂缓措施":"流失原因"}</td>
							<td colspan="3" class="other">
								<textarea name="${empty requestScope.flag?'khls05':'khls07'}" class="form-control" rows="5"></textarea>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		function save(){
			document.getElementById("form01").submit();
		}
	</script>
	<script type="text/javascript">
		function loss(){
			var form01 = document.getElementById("form01");
			form01.action = '/crm/lx_SureCustomerLoss.do';
			form01.submit();
		}
	</script>
</html>