<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<link rel="stylesheet" href="/crm/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="/crm/css/size.css" />
	<style type="text/css">
		*{
			padding: 0;
			margin: 0;
		}
		#right{
			background-color: #F1F1F1;
			display: inline-block;
			margin: 0;
			position: fixed;
		}
		#breadcrumb{
			/*margin-top: 15px;*/
			/*height: 50px;*/
			padding-left: 82px;
		}
		#breadcrumb span{
			font-size: large;
		}
		#table01{
			width: 1000px;
			margin: 0  auto;
		}
		#table02,#table03{
			width: 1000px;
			margin: 10px auto;
		}
		#label01{
			vertical-align: middle;
			font-size: 16px;
		}
		#resetButton{
			margin-left: 88%;
		}
		#submitButton{
			float: right;
		}
		tr td{
			text-align: center;
		}
	</style>
</head>
<body>
	<div id="left"></div>
	<div id="right" class="size">
		<ol class="breadcrumb" id="breadcrumb">
			<li><span>服务管理</span></li>
			<li style="color: #269ABC;"><span>服务处理</span></li>
		</ol>
			<table id="table01" class="table table-bordered">
				<tr>
					<td class="col-md-1">编号</td>
					<td class="col-md-5">${row.fwcl_no}</td>
					<td class="col-md-1">服务类型</td>
					<td class="col-md-5">${row.cs_name}</td>
				</tr>
				<tr>
					<td>概要</td>
					<td colspan="3">${row.fw03}</td>
				</tr>
				<tr>
					<td>客户</td>
					<td>${row.kh02}</td>
					<td>状态</td>
					<td>${row.fwcl06}</td>
				</tr>
				<tr>
					<td>服务请求</td>
					<td colspan="3">${row.fw05}</td>
				</tr>
				<tr>
					<td>创建人</td>
					<td>${row.cjr}</td>
					<td>创建时间</td>
					<td>${row.fw08}</td>
				</tr>
				<br />
			</table>
			<table id="table02" class="table table-bordered">
				<tr>
					<td class="col-md-1">分配给</td>
					<td class="col-md-5">${row.fpr }</td>
					<td class="col-md-1">分配时间</td>
					<td class="col-md-5">${row.fwfp04}</td>
				</tr>
			</table>
			<form action="/crm/fw_FcUpdateServlet.do" method="post">
			<table id="table03" class="table table-bordered">
			<input type="hidden" name="fwcl_no" value="${row.fwcl_no}"/> 
				<tr>
					<td class="col-md-1" style="vertical-align: middle;">服务处理</td>
					<td colspan="3">
						<textarea class="form-control" name="fwcl03" value="${row.fwcl03 }" style="display: inline-block;width: 98%;" rows="4" name="textarea"></textarea>
						<span style="color: red;vertical-align: middle;">*</span>
					</td>
				</tr>
				<tr>
					<td class="col-md-1" style="vertical-align: middle;">处理人</td>
					<td class="col-md-5">
						<input class="form-control"  name="name" value="${userInfoMap.name}" style="width: 97% !important;" readonly="readonly"/ value="${map1111.NAME}">
					</td>
					<td class="col-md-1" style="vertical-align: middle;">处理时间</td>
					<td class="col-md-5">
						<input class="form-control" value="当前时间" style="width: 97% !important;" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="submit" class="btn btn-primary btn-sm" style="float: right;" value="保存"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js" ></script>
<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js" ></script>
</html>
