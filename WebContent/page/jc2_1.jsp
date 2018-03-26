<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="e" uri="http://org.wangxg/jsp/extl" %>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
		<link rel="stylesheet" href="/crm/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="/crm/css/jquery-ui.css">
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
				padding-left: 74%;
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
					<li><span>基础数据</span></li>
					<li><span>产品信息管理</span></li>
					<li style="color: #269ABC;"><span>${empty row ? "新建":"修改"}商品</span></li>
				</ol>
			</div> 
			<div id="b2">
				<a class="btn btn-xs btn-primary" href="/crm/lx_FindAllGoods.do?nowPage=${empty param.nowPage?nowPage:param.nowPage}">返     回</a>
				<a class="btn btn-xs btn-primary" onclick="${empty row? 'add' : 'update'}Goods()">保     存</a>
			</div>
			<div id="b3">
				<form id="form01" action="/crm/lx_AddGoods.do" method="post" class="form-horizontal">
					<input type="hidden" name="sp_no" value="${row.sp_no}">
					<table border="1" class="table table-bordered table-hover table-condensed" style="margin-bottom: 10px;">
						<tr>
							<td class="col-md-2" align="center" id="label01">
								名称<b class="xx">☆</b></td>
							<td class="col-md-4">
								<input value="${row.sp08}" type="text" name="sp08" id="kh02" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入商品名称" />
							</td>
							<td class="col-md-2" align="center" id="label01">产品类别
								<b class="xx">☆</b></td>
							<td class="col-md-4">
								<input type="hidden" id="hd_splbNo" value="${row.splb_no}"/>
								<e:select value="${sessionScope.lbStr}" id="splb_no" name="splb_no" styleClass="form-control"/>
							</td>
						</tr>
						<tr>
							<td align="center" id="label01">型号<b class="xx">☆</b></td>
							<td align="center" id="label01">
								<input name="sp10" type="text" id="hd_usNo" value="${row.sp10}" class="form-control" placeholder="请输入产品型号">
							</td>
							<td align="center" id="label01">等级/批次
								<b class="xx">☆</b></td>
							<td>
								<input name="sp09" type="text" id="hd_djNo" value="${row.sp09}" class="form-control" placeholder="请输入产品批次"/>
							</td>
						</tr>
						<tr>
							<td align="center" id="label01">单位</td>
							<td>
								<input name="sp03" type="text" id="hd_pxNo" value="${row.sp03}" class="form-control" placeholder="请输入产品单位"/>
							</td>
							<td align="center" id="label01">单价</td>
							<td>
								<input name="sp05" type="number" id="hd_xyNo" value="${row.sp05}" class="form-control" placeholder="请输入产品单价"/>
							</td>
						</tr>
						<tr>
							<td align="center" id="label01">货位</td>
							<td>
								<input required="required" type="text" id="hw02" value="${row.hw02}"  class="form-control"/>
								<input type="hidden" id="hw_no" name="hw_no" value="${row.hw_no}"/>
							</td>
							<td align="center" id="label01">数量</td>
							<td>
								<input name="cf04" type="text" id="hd_xyNo" value="${row.cf04}"  class="form-control"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/crm/js/jquery-ui.min.js"></script>
	<script type="text/javascript">
		jQuery("#hw02").autocomplete({
			source:function(request, response){
				jQuery.ajax({
					url : "/crm/lx_FindAllGoodsLocation.do",
					data:{term:jQuery('#hw02').val()},
					dataType:"json",
					success:function(data){
						response(jQuery.map(data.hws, function(item){
							return{
								label:item.hw02,
								value:item.hw_no
							}	
						}));
					},
					error:function(){
						alert('error');
					}
				});
			},
			focus:function(event, ui){
				jQuery("#hw02").val(ui.item.label);
				jQuery("#hw_no").val(ui.item.value);
				return false;
			},
			select:function(event, ui) {
				jQuery("#hw02").val(ui.item.label);
				jQuery("#hw_no").val(ui.item.value);
				return false;
			},
			change: function (event, ui) {
	            if (!ui.item) {
	            	 alert('请重新输入货位名');
	                 $(this).val('');
	                 $("#hw_no").val('');
	             }
	        }
		});
	</script>
	<script type="text/javascript">
		function addGoods() {
			document.getElementById("form01").submit();
		}
	</script>
	<script type="text/javascript">
		function updateGoods(){
			var hwNo = $("#hw_no").val();
			if (hwNo == null || hwNo == '') {
				alert('请重新输入货位名');
				return;
			}
			var form = document.getElementById("form01");
			form.action = '/crm/lx_UpdateGoods.do';
			form.submit();
		}
	</script>
	<script type="text/javascript">
		/* 将查询出的用户数据与下拉框的值相同的项勾选上 */
		window.onload = function(){
			var splbNo = $("#hd_splbNo").val();
			$("#splb_no option[value='"+ splbNo +"']").attr('selected','selected');
		}
	</script>
</html>