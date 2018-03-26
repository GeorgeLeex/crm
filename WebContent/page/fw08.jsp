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
			/*margin-top: 80px;*/
			/*height: 50px;*/
			padding-left: 82px;
		}
		#breadcrumb span{
			font-size: large;
		}
		#table01{
			width: 1000px;
			margin: 50px  auto;
		}
		#table02{
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
		.timeInput{
			width: 165px;
			height: 34px;
			padding: 6px 12px;
			font-size: 14px;
			line-height: 1.42857143;
			color: #555;
			background-color: #fff;
			background-image: none;
			border: 1px solid #ccc;
			border-radius: 4px;
		}
		#table02 tr th{
			text-align: center;
		}
		#table02 tbody tr td{
			text-align: center;
		}
		.span1{
		 	font-size: 18px;
		 	padding: 0 8px;
		}
	</style>
</head>
<body>
	<div id="right" class="size">
		<ol class="breadcrumb" id="breadcrumb">
			<li><span>服务管理</span></li>
			<li style="color: #269ABC;"><span>服务归档</span></li>
		</ol>
		<form action="/crm/fw_Fw05Servlet.do" method="post">
			<table id="table01" class="table table-bordered">
				<input type="hidden" name="type" value="like"/>
				<tr>
				<td align="center" id="label01">客户</td>
					<td>
						<input class="form-control" name="name"/>
					</td>
					<td align="center" id="label01">服务类型</td>
					<td>
					<select class="form-control" name="t" id="type" onchange="cs()">
							<option>---请选择---</option>
							<option>1&nbsp;&nbsp;&nbsp;---咨询---</option>
							<option>2&nbsp;&nbsp;&nbsp;---投诉---</option>
							<option>3&nbsp;&nbsp;&nbsp;---服务---</option>
							<option>4&nbsp;&nbsp;&nbsp;---显示全部---</option>
							</select>
					</td>
					<td align="center" id="label01">状态</td>
					<td align="center" id="label01">
						<input class="form-control" value="服务归档" style="width: 97% !important;" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td align="center" id="label01">概要</td>
					<td>
						<input class="form-control" name="fw03"/>
					</td>
					<td align="center" id="label01">创建日期</td>
					<td colspan="5" width="300px">
				<input type="date" class="timeInput" name="begin"/>&nbsp;&nbsp;-&nbsp;&nbsp;
				<input type="date" class="timeInput" name="end"/>
				<input id="submit" type="submit" value="查询" class="btn btn-primary"/>
					</td>
				</tr>
			</table>
			</form>
			<table id="table02" class="table table-hover table-bordered">
				<tr>
					<th>编号</th>
					<th>客户</th>
					<th>概要</th>
					<th>服务类型</th>
					<th>创建人</th>
					<th>创建日期</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<tbody>
						<c:forEach var="rows" items="${rows}">
						<tr>
							<td>${rows.fw_no}</td>
							<td>${rows.kh02 }</td>
							<td>${rows.fw03 }</td>
							<td>${rows.cs_name}</td>
							<td>${rows.name}</td>
							<td>${rows.fw08}</td>
							<td>${rows.fw09 }</td>
							<td width="50px">
							<a href="/crm/fw_Fw05OneServlet.do?fw_no=${rows.fw_no}"  class="input1 btn btn-sm btn-success">⊙</a>
							</td>
						</tr>
						</c:forEach>
					<tr>
						<td colspan="7" align="center">
							共99条记录,每页<input type="text" size="1" class="input1" />条, 第
						<input type="text" size="1" class="input1" />页/ 共
							<input type="text" size="1" class="input1" />页
							<a href="#">第一页</a>
							<a href="#">上一页</a>
							<a href="#">下一页</a>
							<a href="#">最后一页</a>
							转到<input type="text" size="1" class="input1" /> 页
							<a class="input1 btn btn-sm btn-success" href="#">GO</a>
						</td>
					</tr>
					</tbody>
			</table>
	</div>
</body>
<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js" ></script>
<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js" ></script>
<script type="text/javascript">

function cs(){
	var vr=document.getElementById("type").value;
	var str=vr.substr(0,1);
	if(str!=4){
		window.location.href="/crm/fw_Fw05Servlet.do?type=type&no="+str;
	}else{
		window.location.href="/crm/fw_Fw05SelectServlet.do";
	}
}

</script>
</html>
