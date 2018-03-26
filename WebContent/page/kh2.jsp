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
		.importantInput{
			display: inline-block;
			width: 97% !important;
		}
		.importantTag{
			color: red;
			vertical-align: middle;
		}
		#sexRadio{
			margin-top: 7px;
		}
		#sexRadio span{
		}
	</style>

	<body>
		<div id="body" class="size">
			<div id="b1">
				<!--<h3>&nbsp;&nbsp;&nbsp;客户信息管理 >客户信息>联系人>新建联系人</h3>-->
				<ol class="breadcrumb" style="margin-bottom: 0px;padding-left: 72px;" id="breadcrumb">
				<li><span>客户管理</span></li>
				<li><span>客户信息管理</span></li>
				<li><span>客户信息</span></li>
				<li><span>联系人</span></li>
				<li style="color: #269ABC;"><span>${empty row ? "新建" : "修改"}联系人</span></li>
			</ol>
			</div>
			<div id="b2">
    			<a class="btn btn-xs btn-primary" onclick="javascript:location.href='/crm/lx_FindAllContacts.do?kh_no=${empty param.kh_no? kh_no:param.kh_no}'">返回</a>
    			<a class="btn btn-xs btn-primary" onclick="${empty row ? "add" : "update"}Contact()">保存</a>
			</div>
			<div id="b3">
				<form action="#" method="post" class="form-horizontal">
					<input type="hidden" id="hd_khNo" value="${param.kh_no}"/>
					<input type="hidden" id="hd_khNo2" value="${row.kh_no}"/>
					<input type="hidden" id="hd_lxrNo" value="${row.lxr_no}"/>
					<table class="table table-bordered table-hover">
						<tr>
							<td align="center" id="label01">姓名</td>
							<td>
								<input type="text" name="lxr03" id="no" onblur="onblurs()" 
								class="form-control form-group-sm  col-lg-1 importantInput" 
								placeholder="请输入联系人姓名" value="${row.lxr03}"/>
								<span class="importantTag">*</span>
							</td>
							<td align="center" id="label01">性别</td>
							<td>
								<input type="hidden" id="hd_lxr04" value="${row.lxr04}"/>
								<div id="sexRadio">
									<input type="radio" name="lxr04" value="男" checked="checked"/>&nbsp;<span>男</span>
									&nbsp;&nbsp;&nbsp;
									<input type="radio" name="lxr04" value="女"/>&nbsp;<span>女</span>									
								</div>
								<!--<select class="form-control">
									<option>- -请选择- -</option>
									<option >男</option>
									<option >女</option>
								</select>-->
							</td>
						</tr>
						<tr>
							<td align="center" id="label01">职位</td>
							<td>
								<input type="text" name="lxr05" id="no" onblur="onblurs()" value="${row.lxr05}"
									class="form-control form-group-sm  col-lg-1 importantInput" placeholder="请输入联系人职位" />
								<span class="importantTag">*</span>
							</td>
							<td align="center" id="label01">办公电话</td>
							<td>
								<input type="text" name="lxr06" id="no" onblur="onblurs()"  value="${row.lxr06}"
									class="form-control form-group-sm  col-lg-1 importantInput" placeholder="请输入联系人办公电话" />
								<span class="importantTag">*</span>
							</td>
						</tr>
						<tr>
							<td align="center" id="label01">手机</td>
							<td><input value="${row.lxr07}" type="text" name="lxr07" id="no" onblur="onblurs()" class="form-control form-group-sm  col-lg-1 importantInput" placeholder="请输入联系人手机号码" /></td>
							<td align="center" id="label01">备注</td>
							<td><input value="${row.lxr08}" type="text" name="lxr08" id="no" onblur="onblurs()" class="form-control form-group-sm  col-lg-1 importantInput" placeholder="请输入备注" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		function addContact(){
			var khNo = document.getElementById("hd_khNo").value;
			with(document.forms[0]){
				action = '/crm/lx_AddContact.do?kh_no=' + khNo;
				submit();
			}
		}
	</script>
	<script type="text/javascript">
		function updateContact(){
			var khNo = document.getElementById("hd_khNo2").value;
			var lxrNo = document.getElementById("hd_lxrNo").value;
			with(document.forms[0]){
				action = '/crm/lx_UpdateContact.do?lxr_no=' + lxrNo + '&kh_no=' + khNo;
				submit();
			}
		}
	</script>
	<script type="text/javascript">
		var lxr04 = document.getElementById("hd_lxr04").value;
		var sexs = document.getElementsByName("lxr04");
		window.onload = function (){
			for (i in sexs) {
				var sex = sexs[i];
				if (sex.value == lxr04) {
					sex.checked = 'checked';
				}
			}
		};
	</script>
</html>