<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <link rel="stylesheet" href="/crm/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/crm/css/jquery-ui.css"/>
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
		.input1{
			border-radius: 4px;
		}
    </style>
    <body>
    	<div id="body" class="size">
    	<div id="b1">
    		<!--<h3>&nbsp;&nbsp;&nbsp;数据字典管理 > 新建数据字典条目</h3>-->
    		<ol class="breadcrumb" style="margin-bottom: 0px;padding-left: 72px;" id="breadcrumb">
    			<li><span>基础数据</span></li>
    			<li><span>数据字典管理</span></li>
				<li style="color: #269ABC;"><span>${empty row? "新建":"修改"}数据字典条目</span></li>
			</ol>
    	</div>
    	<div id="b2">
				<a class="btn btn-xs btn-primary" href="/crm/lx_FindAllDictionary.do?nowPage=${empty nowPage?param.nowPage:nowPage}">返回</a>
				<a class="btn btn-xs btn-primary" onclick="${empty row? 'save':'update'}()">保存</a>
    	</div>
    	<div id="b3">
    		<form id="form01" action="/crm/lx_AddDictionary.do" method="post" class="form-horizontal">
    		<input type="hidden" name="zd_no" value="${row.zd_no}"/>
    		<table border="1" class="table table-bordered table-hover">
    			<tr >
				<td align="center" id="label01">条目
				<b class="xx">☆</td>
    				<td><input value="${row.zd_name}" type="text" name="zd_name" id="no" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入条目内容" /></td>
    				<td align="center" id="label01">值
    				<b class="xx">☆</td>
    				<td><input value="${row.zd_value}" type="text" name="zd_value" id="no" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入值" /></td>
    			</tr>
    			<tr>
	    			<td align="center" id="label01">类别<b class="xx">☆</td>
	    			<td align="center" id="label01">
	    				<input value="${row.zd_class}" type="text" name="zd_class" id="zdClass" onblur="onblurs()" class="form-control form-group-sm  col-lg-1" placeholder="请输入类别" />
	    			</td>
    				<td align="center" id="label01">是否可编辑</td>
    				<td colspan="3"><input checked="checked" id="box1" type="checkbox" name="zd_bj" id="no" style="float: left;"/></td>
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
 			form01.action = '/crm/lx_UpdateDictionary.do';
 			form01.submit();
 		}
 	</script>
 	<script type="text/javascript">
 		$("#zdClass").autocomplete({
 			source:function(request, response){
 				$.ajax({
 					url:'lx_AutoCompleteZdClass.do',
 					data:{zd_class:request.term},
 					dataType:'json',
 					type:'POST',
 					success:function(data){
 						response($.map(data.zds,function(item){
 							return{
 								label:item.zd_class,
 							}
 						}));
 					},
 					error:function(){
 						alert('网络异常!');
 					}
 				});
 			},
 			focus:function(event, ui){
 				$("#zdClass").val(ui.item.label);
 			},
 			select:function(event, ui){
 				$("#zdClass").val(ui.item.label);
 			},
 		});
 	</script>
</html>