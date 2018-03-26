<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e" uri="http://org.wangxg/jsp/extl" %>
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
			font-family: "微软雅黑";
			font-size: 16px;
		}
		
		#b2 {
			padding-left: 73%;
			margin-top: 30px;
		}
		
		#b3 {
			width: 1000px;
			margin: 10px auto;
		}
		
		.xx {
			color: red;
		}
		
		#label01 {
			vertical-align: middle;
		}
		
		#breadcrumb span {
			font-size: large;
		}
		
		table tr td,
		th {
			vertical-align: middle;
			text-align: center;
		}
		
		.input1 {
			border-radius: 4px;
		}
		.btn1 {  
	        color: #337ab7;      
	        background-color: #F1F1F1;  
	        border: 0px none;  //去边框  
	        font-family: "宋体";  
	        font-size: 15px;  
	        text-decoration:none;  //加下划线  
    	}  
    	.btn1:hover{  
	        border: none;  
	        cursor: hand;  
	        cursor: pointer;  
	        text-decoration:underline;  //加下划线  
    	}  
    	.btn1:focus {   
        	outline: none;    //去边框  
    	}
	</style>

	<body>
		<div id="body" class="size">
			<div id="b1">
				<!--<h3>&nbsp;&nbsp;&nbsp;客户信息管理>客户信息>历史订单</h3>-->
				<ol class="breadcrumb" style="margin-bottom: 0px;padding-left: 72px;" id="breadcrumb">
					<li><span>商品管理</span></li>
					<li style="color: #269ABC;"><span>商品订单</span></li>
				</ol>
			</div>
			<div id="b2">
				<a class="btn btn-xs btn-primary" href="/crm/page/dd1_1.jsp?nowPage=${nowPage}">新建订单</a>
				<a class="btn btn-xs btn-primary" onclick="query()">查询</a>
			</div>
			<div id="b3">
				<form id="form01" action="/crm/lx_FindAllOrders.do" method="post" class="form-horizontal">
					<table class="table table-bordered table-hover">
						<tr>
							<td id="label01">客户名称</td>
							<td><e:text name="qkh02" placeholder="请输入客户名称" styleClass="form-control"/></td>
							<td id="label01">日期</td>
							<td><e:date name="qls04" styleClass="form-control"/></td>
						</tr>
					</table>
				<table class="table table-bordered table-hover">
					<tr>
						<th>订单编号</th>
						<th>姓名</th>
						<th>日期</th>
						<th>送货地址</th>
						<th>电话号码</th>
						<th>状态</th>
						<th class="col-md-1">操作</th>
					</tr>
					<c:choose>
						<c:when test="${not empty rows}">
							<c:forEach var="row" items="${rows}">
								<tr>
									<td>${row.ls_no}</td>
									<td>${row.kh02}</td>
									<td>${row.ls04}</td>
									<td>${row.ls05}</td>
									<td>${row.kh10}</td>
									<td>${row.jy_name}</td>
									<td>
										<a href="/crm/lx_FindOneOrders.do?ls_no=${row.ls_no}&nowPage=${nowPage}"><span class="glyphicon glyphicon-eye-open"></span></a>
										&nbsp;&nbsp;&nbsp;
										<a href="/crm/lx_DeleteOneOrders.do?ls_no=${row.ls_no}"><span class="glyphicon glyphicon-remove"></span></a>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="8" align="center">
									共${rowCount }条记录,每页<input id="numbers" name="numbers" type="text" size="1" class="input1" value="${numbers}" style="text-align: center;"/>条, 第
									<span id="nowPage">${nowPage}</span>页/ 共<span id="pageCount">${pageCount}</span>页
									<c:choose>
										<c:when test="${nowPage == 1}">
											第一页
										</c:when>
										<c:otherwise>
											<button class="btn1" formaction="/crm/lx_FindAllOrders.do?nowPage=1">第一页</button>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${nowPage - 1 >= 1}">
											<button class="btn1" formaction="/crm/lx_FindAllOrders.do?nowPage=${nowPage - 1}">上一页</button>
										</c:when>
										<c:otherwise>上一页</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${nowPage + 1 <= pageCount}">
											<button class="btn1" formaction="/crm/lx_FindAllOrders.do?nowPage=${nowPage + 1}">下一页</button>
										</c:when>
										<c:otherwise>下一页</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${nowPage < pageCount}">
											<button class="btn1" formaction="/crm/lx_FindAllOrders.do?nowPage=${pageCount}">最后一页</button>
										</c:when>
										<c:otherwise>最后一页</c:otherwise>
									</c:choose>
									转到<input id="page" type="text" size="1" class="input1" style="text-align: center;"/> 页
									<a class="input1 btn btn-sm btn-default" style="margin-bottom:5px;" onclick="jump()">GO</a>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		function jump(){
			var nowPage = document.getElementById("nowPage").innerText;
			var pageCount = document.getElementById("pageCount").innerText;
			var page = document.getElementById("page").value;
			if ((page < 1) || (page > pageCount)) {
				alert('输入有误!');
			} else {
				var form = document.getElementById("form01");
				form.action = '/crm/lx_FindAllOrders.do?nowPage=' + page;
				form.submit();
			}
		}
	</script>
	<script type="text/javascript">
		function query(){
			document.getElementById("form01").submit();
		}
	</script>
	<script type="text/javascript">
		$(document).ready(function(){
			var msg = '<%=request.getAttribute("msg")%>';
			if (msg != null && msg != 'null') {
				alert(msg);
			}
		});
	</script>
</html>