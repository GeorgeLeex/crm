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
					<li><span>订单管理</span></li>
					<li style="color: #269ABC;"><span>${empty row ? "新建":"修改"}订单</span></li>
				</ol>
			</div>
			<form id="form01" onsubmit="return check()" action="/crm/lx_AddOrders.do" method="post" class="form-horizontal"> 
			<div id="b2">
				<input type="button" value="返回" class="btn btn-sm btn-primary" onclick="javascript:location.href='/crm/lx_FindAllOrders.do?nowPage=${param.nowPage}'"/>
				<input type="submit" value="保存" class="btn btn-sm btn-primary" />
			</div>
			<div id="b3">
					<input type="hidden" id="msg" value="<%=request.getAttribute("msg") %>"/>
					<%-- <input type="hidden" name="sp_no" value="${row.sp_no}"> --%>
					<table border="1" class="table table-bordered table-hover" style="margin-bottom: 10px;">
						<tr>
							<td class="col-md-2" align="center" id="label01">
								名称<b class="xx">☆</b></td>
							<td class="col-md-4">
								<input required="required" value="${row.kh02}" type="text" name="kh02" id="kh02" class="form-control" placeholder="请输入客户名称" />
								<input type="hidden" id="hd_khNo" name="kh_no"/>
							</td>
							<td class="col-md-2" align="center" id="label01">日期
								<b class="xx">☆</b></td>
							<td class="col-md-4">
								<input type="date" name="ls04" class="form-control"/>
								<%-- <input type="hidden" id="hd_splbNo" value="${row.splb_no}"/>
								<e:select value="${sessionScope.lbStr}" id="splb_no" name="splb_no" styleClass="form-control"/> --%>
							</td>
						</tr>
						<tr>
							<td align="center" id="label01">送货地址<b class="xx">☆</b></td>
							<td align="center" id="label01">
								<input name="ls05" type="text" id="hd_usNo" value="${row.ls05}" class="form-control" placeholder="请输入送货地址">
							</td>
							<td align="center" id="label01">
								&nbsp;
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
					<br><br>
					<a style="float: right;" class="btn btn-primary" onclick="add()">添加商品</a>
					<table id="table01" class="table table-bordered table-condensed">
						<tr>
							<th class="col-md-7">商品名</th>
							<th class="col-md-4">数量</th>
							<th class="col-md-1">操作</th>
						</tr>
						<tr>
							<td><input required="required" type="text" class="form-control sp08" placeholder="请输入商品名"/></td>
							<td><input required="required" name="cf04" type="number" class="form-control" placeholder="请输入数量"/></td>
							<td align="center" style="vertical-align: middle;">&nbsp;</td>
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
		jQuery("#kh02").autocomplete({
			source:function(request, response){
				jQuery.ajax({
					url : "/crm/lx_AutoCompleteKh02.do",
					type:"POST",
					data:{kh02:jQuery('#kh02').val()},
					dataType:"json",
					success:function(data){
						response(jQuery.map(data.khs, function(item){
							return{
								label:item.kh02,
								value:item.kh_no
							}	
						}));
					},
					error:function(){
						alert('error');
					}
				});
			},
			mustMatch:true,
			 change: function (event, ui) {
		            if (!ui.item) {
		            	 alert('请输入正确的客户名称');
		                 $(this).val('');
		             }
		        },
			focus:function(event, ui){
				jQuery("#kh02").val(ui.item.label);
				//jQuery("#hd_khNo").val(ui.item.value);
				return false;
			},
			select:function(event, ui) {
				jQuery("#kh02").val(ui.item.label);
				jQuery("#hd_khNo").val(ui.item.value);
				return false;
			}
		});
	</script>
	<script type="text/javascript">
		$(document).on('keydown.autocomplete','.sp08',function(){
			$(this).autocomplete({
				source:function(request, response){
					$.ajax({
						url:'/crm/lx_AutoCompleteSp08.do',
						data:{sp08:request.term},
						dataType:'json',
						type:"POST",
						success:function(data){
							response($.map(data.sps, function(item){
								return{
									label:item.sp08,
									value:item.sp_no
								}
							}));
						},
						error:function(){
							alert('error');
						}
					});
				},
				focus:function(event, ui) {
					$(this).val(ui.item.label);
					return false;
				},
				select:function(event, ui) {
					$(this).val(ui.item.label);
					$(this).empty();
					$(this).append("<input type=\"hidden\" name=\"sp_no\" value=\"" + ui.item.value + "\"/>");
					return false;
				},
			});
		});
	</script>
	<script type="text/javascript">
		function addGoods() {
			document.getElementById("form01").submit();
		}
	</script>
	<script type="text/javascript">
		function updateGoods(){
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
	<script type="text/javascript">
		var num = 0;
		function add(){
			if (++num == 9) {
				num = 8;
				alert('已达添加到的最大上限');
				return;
			}
			var child = "<tr><td><input required=\"required\" type=\"text\" class=\"form-control sp08 ui-autocomplete-input\" autocomplete=\"off\" placeholder=\"请输入商品名\"/></td><td><input required=\"required\" type=\"number\" name=\"cf04\" class=\"form-control\" placeholder=\"请输入数量\"/></td><td align=\"center\" style=\"vertical-align: middle;\"><a  onclick=\"deleteOne(this)\"><span class=\"glyphicon glyphicon-remove\"></span></a></td></tr>"
			$("#table01").append(child);
		}
		
		function deleteOne(obj) {
			num--;
			var tr = obj.parentNode.parentNode;
			tr.parentNode.removeChild(tr);
			
		}
	</script>
	<script type="text/javascript">
		function check(){
			var khNo = document.getElementById("hd_khNo").value;
			var spNos = document.getElementsByName("sp_no");
			if (khNo == null || khNo == '') {
				alert('请重新输入用户名');
				return false;
			}
			for (var i = 0; i < spNos.length; i++) {
				var spNo = spNos[i];
				if (spNo.value == null ||spNo.value == '') {
					alert('请重新输入商品名');
					return false;
				}
			}
			var sp08s = $('.sp08');
			for (var j = 0; j < sp08s.length; j++) {
				var sp08 = sp08s[j];
				var childs = sp08.childNodes;
				if ((childs == null) || (childs.length == 0) || (childs[0].nodeName == '#text')) {
					alert('请重新输入商品名');
					return false;
				}
			}
			var cf04s = document.getElementsByName("cf04");
			for (var k = 0; k < cf04s.length; k++) {
				var cf04 = cf04s[k];
				if (cf04.value <= 0) {
					alert('请输入正确的商品数量');
					return false;
				}
			}
		}
	</script>
	<script type="text/javascript">
		$(document).ready(function(){
			var msg = document.getElementById("msg").value;
			if (msg != null && msg != 'null') {
				alert(msg);
			}
			
		});
	</script>
</html>