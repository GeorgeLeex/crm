<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
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
		.importantInput{
			display: inline-block;
			width: 97% !important;
		}
		.importantTag{
			color: red;
			vertical-align: middle;
		}
	</style>

	<body>
		<div id="body" class="size">
			<div id="b1">
				<!--<h3>&nbsp;&nbsp;&nbsp;客户信息管理 >客户信息>交往记录>新建交往记录</h3>-->
				<ol class="breadcrumb" style="margin-bottom: 0px;padding-left: 72px;" id="breadcrumb">
				<li><span>客户管理</span></li>
				<li><span>客户信息管理</span></li>
				<li><span>客户信息</span></li>
				<li><span>交往记录</span></li>
				<li style="color: #269ABC;"><span>${empty row ? "新建" : "修改"}交往记录</span></li>
				</ol>
			</div>
			<div id="b2">
    			<a class="btn btn-xs btn-primary"href="/crm/lx_FindAllCommuRecord.do?kh_no=${param.kh_no}&nowPage=${empty param.nowPage? nowPage:param.nowPage}">返回</a>
    			<a class="btn btn-xs btn-primary" onclick="${empty row ? "add" : "update"}CommuRecord()">保存</a>
			</div>
			<input type="hidden" id="kh_no" name="kh_no" value="${row.kh_no}"/>
			<div id="b3">
				<form action="/crm/lx_AddCommuRecord.do?kh_no=${param.kh_no}" method="post" class="form-horizontal">
					<input type="hidden" name="jw_no" value="${row.jw_no}"/>
					<%-- <input type="hidden" name="kh_no" value="${param.kh_no}"/> --%>
					<%-- <input type="hidden" name="kh_no" value="${row.kh_no}"/> --%>
					<table class="table table-bordered table-hover">
						<tr>
							<td align="center" id="label01">时间</td>
							<td><input value="${row.jw03}" type="text" name="jw03" id="no"
								onclick="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1970-01-01 00:00:00',maxDate:'2018-12-31 23:59:59'})" 
								class="form-control form-group-sm  col-lg-1 importantInput Wdate" />
								<span class="importantTag">*</span>
							</td>
							<td align="center" id="label01">地点</td>
							<td>
								<input value="${row.jw04}" type="text" name="jw04" id="no" onblur="onblurs()" 
									class="form-control form-group-sm col-lg-1 importantInput" placeholder="请输入地点" />
								<span class="importantTag">*</span>
							</td>
						</tr>
						<tr>
							<td align="center" id="label01">概要</td>
							<td>
								<input value="${row.jw05}" type="text" name="jw05" id="no" onblur="onblurs()" 
									class="form-control form-group-sm  col-lg-1 importantInput" placeholder="请输入概要" />
								<span class="importantTag">*</span>
							</td>
							<td align="center" id="label01">备注</td>
							<td>
								<input value="${row.jw06}" type="text" name="jw06" id="no" onblur="onblurs()" 
									class="form-control form-group-sm  col-lg-1 importantInput" placeholder="请输入备注" />
							</td>
						</tr>
						<tr>
							<td align="center" id="label01">详细信息</td>
							<td colspan="3">
								<textarea  name="jw07" class="form-control" style="width: 99%;" rows="5">${row.jw07}</textarea>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/crm/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function addCommuRecord(){
			document.forms[0].submit();
		}
	</script>
	<script type="text/javascript">
		var kh_no = document.getElementById("kh_no").value;
		function updateCommuRecord(){
			with(document.forms[0]) {
				action = '/crm/lx_UpdateCommuRecord.do?kh_no=' + kh_no;
				submit();
			}
		}
	</script>
</html>