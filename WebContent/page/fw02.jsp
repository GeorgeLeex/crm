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
	/*margin-top: 80px;*/
	/*height: 50px;*/
	padding-left: 82px;
}

#breadcrumb span {
	font-size: large;
}

#table01 {
	width: 1000px;
	margin: 50px auto;
}

#table02 {
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

.timeInput {
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

#table02 tr th {
	text-align: center;
}

#table02 tbody tr td {
	text-align: center;
}
</style>
</head>

<body>
	<div id="right" class="size">
		<ol class="breadcrumb" id="breadcrumb">
			<li><span>服务管理</span></li>
			<li style="color: #269ABC;"><span>服务分配</span></li>
		</ol>
		<form id="form1" action="/crm/FW02AddServlet" method="post">
			<input id="fw_no" type="hidden" name="fw_no"> <input
				id="us_no" type="hidden" name="us_no"> <input id="kh_no"
				type="hidden" name="kh_no">
		</form>
<form action="/crm/dgp_FwLikeServlet.do" method="post">
			<table id="table01" class="table table-bordered">
				<tr>
					<td align="center" id="label01">客户</td>
					<td><input class="form-control" name="khname" /></td>
					<td align="center" id="label01">服务类型</td>
					<td>
						<select class="form-control" name="type" id="type" onchange="init()">
							<option>---请选择---</option>
							<option>1&nbsp;&nbsp;&nbsp;---咨询---</option>
							<option>2&nbsp;&nbsp;&nbsp;---投诉---</option>
							<option>3&nbsp;&nbsp;&nbsp;---服务---</option>
							<option>4&nbsp;&nbsp;&nbsp;---显示全部---</option>
						</select>
					</td>
					<td align="center" id="label01">状态</td>

					<td><select class="form-control" name="states" id="states"
						onchange="state()">
							<option>---请选择---</option>
							<option>1&nbsp;&nbsp;&nbsp;---新创建---</option>
							<option>2&nbsp;&nbsp;&nbsp;---已分配---</option>
							<option>3&nbsp;&nbsp;&nbsp;---显示全部---</option>
					</select></td>
				</tr>
				<tr>
					<td align="center" id="label01">概要</td>
					<td><input class="form-control" name="fw03" /></td>
					<td align="center" id="label01">创建日期</td>
					<td colspan="5" width="300px">
			<input type="date" class="timeInput"name="begin" />&nbsp;&nbsp;-&nbsp;&nbsp;
			<input type="date"  class="timeInput"name="end" /> 
<input id="submit" type="submit" onsubmit="return hitSubmit()" value="查询"
						class="btn btn-primary" /></td>
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
					<th>分配给</th>
					<th>操作</th>
				</tr>
				
				<tbody>
					<c:forEach var="ibm" items="${rows[0]}">
						<form action="/crm/dgp_FwFpServlet.do" method="post">
						<tr id="t_${ibm.fw_no }">
							<!-- 			f.fw_no,k.kh02,f.fw03,c.cs_name,u.name,f.fw08  -->
							<td><input type="hidden" name="fw_no" value="${ibm.fw_no}"/>${ibm.fw_no}</td>
							<td><input type="hidden" name="kh02" value="${ibm.kh02}"/>${ibm.kh02}</td>
							<td><input type="hidden" name="fw02" value="${ibm.fw02}"/>${ibm.fw03}</td>
							<td><input type="hidden" name="cs_name" value="${ibm.cs_name}"/>${ibm.cs_name}</td>
							<td><input type="hidden" name="name" value="${ibm.name}"/>${ibm.name}</td>
							<td><input type="hidden" name="fw08" value="${ibm.fw08}"/>${ibm.fw08}</td>
							<td width="150px"><select class="form-control class1"
								name="us_no" style="width: 62%; display: inline;"
								onchange="fp()">
									<option>请选择</option>
									<c:forEach var="im" items="${rows[1]}">
										<option value="${im.us_no}" name="select">${im.name}</option>
									</c:forEach>
							</select>
								<button type="submit" class="btn btn-primary btn-sm" 
								style="display: inline;">分配</button></td>
							<td width="50px">
							<a href="#"  onclick="dodelete('${ibm.fw_no}')" class="input1 btn btn-sm btn-danger">X</a></td>

							</select> 

							</td>
						</tr>
						</form>
					</c:forEach>
					<tr>
						<td colspan="8" align="center">共99条记录,每页<input type="text"
							size="1" class="input1" />条, 第 <input type="text" size="1"
							class="input1" />页/ 共 <input type="text" size="1" class="input1" />页
							<a href="#">第一页</a> <a href="#">上一页</a> <a href="#">下一页</a> <a
							href="#">最后一页</a> 转到<input type="text" size="1" class="input1" />
							页 <a class="input1 btn btn-sm btn-success" href="#">GO</a>
						</td>
					</tr>
				</tbody>
			</table>
	</div>
</body>
<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
function dodelete(no) {
	var url="/crm/dgp_FwdeleteServlet.do";
	$.ajax({
		url:"/crm/dgp_FwdeleteServlet.do",
		data:{fw_no:no},
		dataType:'json',
		success:function(data){
			if (data.state=='OK') 
			{
				$("#t_"+no).remove();
			}
		},
		error:function(){
			alert("Error!")
		}
	})
}
function state(){
	var vr=document.getElementById("states").value;
	var str=vr.substr(0,1);
	if(str!=3){
		window.location.href="/crm/dgp_StateServlet.do?fw05="+str;
	}else{
		window.location.href="/crm/dgp_Fw02Servlet.do";
	}
}
function init(){
	/* var vr=$('select option:selected').val();  */
	var vr=document.getElementById("type").value;
	var str=vr.substr(0,1);
	if (str!=4) {
		window.location.href="/crm/dgp_CsServlet.do?cs_no="+str;
	}else{
		window.location.href="/crm/dgp_Fw02Servlet.do";
	}
	
}

/* 	function fun() {
		var a = $('select option:selected').val();
		alert(a);
	} */
</script>


</html>