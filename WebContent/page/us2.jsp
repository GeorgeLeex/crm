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
    <link rel="stylesheet" href="/crm/css/jquery-ui.css"/>
    <link rel="stylesheet" href="/crm/css/size.css" />
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
			padding-left:74%;
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
		table tr td,th{
			vertical-align: middle;
			text-align: center;
		}
		.input1{
			border-radius: 4px;
		}
    </style>
    <body>
    	<div id="body" class="size">
    	<div id="b1">
    		<!--<h3>&nbsp;&nbsp;&nbsp;数据字典管理 > 新建数据字典条目</h3>-->
    		<ol class="breadcrumb" style="margin-bottom: 0px;padding-left: 72px;" id="breadcrumb">
    			<li><span>权限管理</span></li>
    			<li><span>用户管理</span></li>
				<li style="color: #269ABC;"><span>${empty row? "新建":"修改"}用户</span></li>
			</ol>
    	</div>
    	<div id="b2">
				<a class="btn btn-xs btn-primary" href="/crm/lx_FindAllUser.do?nowPage=${empty param.nowPage?nowPage:param.nowPage}">返回</a>
				<a class="btn btn-xs btn-primary" onclick="${empty row? 'save':'update'}()">保存</a>
    	</div>
    	<div id="b3">
    		<form id="form01" action="/crm/lx_AddUser.do" method="post" class="form-horizontal">
    		<input type="hidden" name="us_no" value="${row.us_no}"/>
    		<table border="1" class="table table-bordered table-hover">
    			<tr >
				<td align="center" id="label01">姓名
				<b class="xx">☆</td>
    				<td><input value="${row.name}" type="text" name="name" id="no" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入用户姓名" /></td>
    				<td align="center" id="label01">身份
    				<b class="xx">☆</td>
    				<td><e:select name="us03" value="客户经理:客户经理,销售主管:销售主管,高管:高管,系统管理员:系统管理员" defval="${row.us03}" styleClass="form-control"/></td>
    			</tr>
    			<tr>
	    			<td align="center" id="label01">登录名<b class="xx">☆</td>
	    			<td align="center" id="label01">
	    				<input value="${row.us_name}" type="text" name="us_name" id="zdClass" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入登录名" />
	    			</td>
    				<td align="center" id="label01">登录密码</td>
    				<td colspan="3"><input name="us_pwd" value="${row.us_pwd}" class="form-control" placeholder="请输入登录密码"/></td>
    			</tr>
    		</table>
    		</form>
    	</div>
    	</div>
 	</body>
 	<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js" ></script>
 	<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js" ></script>
 	<script type="text/javascript" src="/crm/js/jquery-ui.min.js"></script>
 	<script type="text/javascript">
 		function save(){
 			document.getElementById("form01").submit();
 		}
 	</script>
 	<script type="text/javascript">
 		function update(){
 			var form01 = document.getElementById("form01");
 			form01.action = '/crm/lx_UpdateUser.do';
 			form01.submit();
 		}
 	</script>
</html>