<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
		<link rel="stylesheet" href="/crm/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="/crm/css/size.css"/>
		<style type="text/css">
			*{
				margin: 0;
				padding: 0;
			}
			#body {
				background-color: #F1F1F1;
				display: inline-block;
			}
			#b1{
				margin-left: 42px;
				display: inline-block;
			}
			#b2 a {
				font-size: 16px;
				font-family: "微软雅黑";
				font-weight: 100;
			}
			
			#b2 {
				padding-left: 73%;
				display: inline-block;
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
		</style>
	</head>
	

	<body>
		<div id="body" class="size">
			<div id="b1">
				<!--<h3 style="margin-top: 10px;">&nbsp;&nbsp;&nbsp;客户信息管理 > 客户信息</h3>-->
				<ol class="breadcrumb" style="margin-bottom: 0px;margin-left: 32px;" id="breadcrumb">
					<li><span>客户管理</span></li>
					<li><span>客户信息管理</span></li>
					<li style="color: #269ABC;"><span>${empty row.kh_no ? "新建":"修改"}客户信息</span></li>
				</ol>
			</div> 
			<form id="khForm" action="#" method="post" class="form-horizontal">
			<c:choose>
				<c:when test="${empty row.kh_no}">
					<div id="b2">
						<a class="btn btn-xs btn-primary" href="/crm/lx_FindAllCustomer.do?nowPage=${param.nowPage}">返     回</a>
						<a class="btn btn-xs btn-primary" onclick="addCustomer()">保     存</a>
					</div>
				</c:when>
				<c:otherwise>
					<div id="b2" style="padding-left:60%;">
						<a class="btn btn-xs btn-primary" href="/crm/lx_FindAllContacts.do?kh_no=${row.kh_no}">联系人</a>
						<a class="btn btn-xs btn-primary" href="/crm/lx_FindAllCommuRecord.do?kh_no=${row.kh_no}">交往记录</a>
						<a class="btn btn-xs btn-primary" href="/crm/lx_FindAllOrder.do?kh_no=${row.kh_no}">历史订单</a>
						<a class="btn btn-xs btn-primary" href="/crm/lx_FindAllCustomer.do?nowPage=${nowPage}">返     回</a>
						<a class="btn btn-xs btn-primary" onclick="updateCustomer()">保     存</a>
					</div>
				</c:otherwise>
			</c:choose>
			<div id="b3">
				<!-- <form action="#" method="post" class="form-horizontal"> -->
					<table border="1" class="table table-bordered table-hover table-condensed" style="margin-bottom: 10px;">
						<tr>
							<input type="hidden" id="hd_khNo" value="${row.kh_no}"/>
							<td class="col-md-2" align="center" id="label01">
								名称<b class="xx">☆</b></td>
							<td class="col-md-4">
								<input value="${row.kh02}" type="text" name="kh02" id="kh02" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入名称" />
							</td>
							<td class="col-md-2" align="center" id="label01">地区
								<b class="xx">☆</b></td>
							<td class="col-md-4">
								<input value="${row.kh03}" type="text" name="kh03" id="kh03" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入地区" />
							</td>
						</tr>
						<tr>
							<td align="center" id="label01">客户经理<b class="xx">☆</b></td>
							<td align="center" id="label01">
								<input type="hidden" id="hd_usNo" value="${row.us_no}">
								<select class="form-control" name="us_no" id="us_no">
									<option value="">-- 无 --</option>
									<c:forEach var="manager" items="${managers}">
										<option value="${manager.us_no}">${manager.name}</option>
									</c:forEach>
								</select>
							</td>
							<td align="center" id="label01">客户等级
								<b class="xx">☆</b></td>
							<td>
								<input type="hidden" id="hd_djNo" value="${row.dj_no}"/>
								<select class="form-control" name="dj_no" id="dj_no">
									<option value="">-- 无 --</option>
									<c:forEach var="dj" items="${djs}">
										<option value="${dj.zd_value}">${dj.zd_name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="center" id="label01">客户满意度</td>
							<td>
								<input type="hidden" id="hd_pxNo" value="${row.px_no}"/>
								<select class="form-control" name="px_no" id="px_no">
									<option value="">-- 无 --</option>
									<c:forEach var="px" items="${pxs}">
										<option value="${px.px_no}">${px.px_name}</option>
									</c:forEach>
								</select>
							</td>
							<td align="center" id="label01">客户信用度</td>
							<td>
								<input type="hidden" id="hd_xyNo" value="${row.xy_no}"/>
								<select class="form-control" name="xy_no" id="xy_no">
									<option value="">-- 无 --</option>
									<c:forEach var="xy" items="${xys}">
										<option value="${xy.xy_no}">${xy.xy_name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<table border="1" class="table table-bordered table-hover table-condensed" style="margin-bottom: 10px;">
						<tr>
							<td align="center" id="label01" class="td01 col-md-2">
								地址</td>
							<td class="col-md-4">
								<input value="${row.kh08 }" type="text" name="kh08" id="kh08" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入编号" />
							</td>
							<td  class="col-md-2" align="center" id="label01">邮政编码<b class="xx">☆</b></td>
							<td class="col-md-4"><input value="${row.kh09}" type="text" name="kh09" id="kh09" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入类别" />
							</td>
						</tr>
						<tr>
							<td align="center" id="label01">电话
								<b class="xx">☆</b></td>
							<td><input value="${row.kh10 }" type="text" name="kh10" id="kh10" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入条目内容" /></td>
							<td align="center" id="label01">传真
								<b class="xx">☆</b></td>
							<td><input value="${row.kh11 }" type="text" name="kh11" id="kh11" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入值" /></td>
						</tr>
						<tr>
							<td align="center" id="label01">网址</td>
							<td><input value="${row.kh12 }" type="text" name="kh12" id="kh12" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入值" /></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<table id="table04" border="1" class="table table-bordered table-hover table-condensed">
						<tr>
							<td class="col-md-2" align="center" id="label01" width="143px">
								营业执照注册号</td>
							<td class="col-md-4">
								<input value="${row.kh13 }" type="text" name="kh13" id="kh13" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入编号" />
							</td>
							<td class="col-md-2" align="center" id="label01" width="143px">法人<b class="xx">☆</b></td>
							<td class="col-md-4"><input value="${row.kh14 }" type="text" name="kh14" id="kh14" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入类别" />
							</td>
						</tr>
						<tr>
							<td align="center" id="label01" width="143px">注册资金(万)
								<b class="xx">☆</b></td>
							<td><input value="${row.kh15 }" type="text" name="kh15" id="kh15" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入条目内容" /></td>
							<td align="center" id="label01" width="143px">年营业额
								<b class="xx">☆</b></td>
							<td><input value="${row.kh16 }" type="text" name="kh16" id="kh16" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入值" /></td>
						</tr>
						<tr>
							<td align="center" id="label01" width="143px">开户银行</td>
							<td><input value="${row.kh17 }" type="text" name="kh17" id="kh17" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入值" /></td>
							<td align="center" id="label01" width="143px">银行账户</td>
							<td><input value="${row.kh18 }" type="text" name="kh18" id="kh18" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入值" /></td>
						</tr>
						<tr>
							<td align="center" id="label01" width="143px">地税登记号</td>
							<td><input value="${row.kh19 }" type="text" name="kh19" id="kh19" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入值" /></td>
							<td align="center" id="label01" width="143px">国税登记号</td>
							<td><input value="${row.kh20 }" type="text" name="kh20" id="kh20" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入值" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		function addCustomer() {
			var form = document.getElementById("khForm");
			form.action = '/crm/lx_AddCustomer.do';
			form.submit();
		}
	</script>
	<script type="text/javascript">
		function updateCustomer(){
			var khNo = document.getElementById("hd_khNo").value;
			var form = document.getElementById("khForm");
			form.action = '/crm/lx_UpdateCustomer.do?kh_no=' + khNo;
			form.submit();
		}
	</script>
	<script type="text/javascript">
		/* 将查询出的用户数据与下拉框的值相同的项勾选上 */
		window.onload = function(){
			var usNo = $("#hd_usNo").val();
			var djNo = $("#hd_djNo").val();
			var pxNo = $("#hd_pxNo").val();
			var xyNo = $("#hd_xyNo").val();
			$("#us_no option[value='"+ usNo +"']").attr('selected','selected');
			$("#dj_no option[value='"+ djNo +"']").attr('selected','selected');
			$("#px_no option[value='"+ pxNo +"']").attr('selected','selected');
			$("#xy_no option[value='"+ xyNo +"']").attr('selected','selected');
		}
	</script>
</html>