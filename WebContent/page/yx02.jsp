<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/crm/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/crm/css/right_page.css" />
<link rel="stylesheet" href="/crm/css/size.css" />
</head>
<body>
	<div id="right" class="size">
		<ol class="breadcrumb" id="breadcrumb">
			<li><span>营销管理</span></li>
			<li><span>销售机会管理</span></li>
			<li style="color: #269ABC;"><span>新建销售机会</span></li>
			<li><span>${msg }</span></li>
		</ol>
		
		<form action="/crm/dgp_YxAddServlet.do" method="post">
			<table id="table01" class="table table-bordered">
				<tr>
					<td align="center" id="label01">客户名称</td>
					<td><input class="form-control importantInput"  name="xs03" /><span class="importantTag">*</span></td>
					<!--<td><input onblur="feikong()" id="a" required="required"  class="form-control form-inline " style="float: left ;"  />
						<span id="s" class="required" style="float: right;">*</span>
					</td>-->
					<td align="center" id="label01">机会来源</td>
					<td><input class="form-control" name="xs02"/></td>
				</tr>
				<tr>
					<td align="center" id="label01">概要</td>
					<td colspan="3"><input class="form-control"  name="xs05" style="display: inline-block;width: 99%;"><span class="importantTag">*</span></td>
				</tr>
				<tr>
					<td align="center" id="label01">联系人</td>
					<td><input class="form-control"  name="xs06"/></td>
					<td align="center" id="label01">联系电话</td>
					<td><input class="form-control"  name="xs07" /></td>
				</tr>
				<tr>
					<td align="center" id="label01">机会描述</td>
					<td colspan="3">
						<textarea class="form-control"  name="xs08"  style="display: inline-block;width: 98%;" rows="5"></textarea>
						<span class="importantTag">*</span>
					</td>
				</tr>
				<tr>
					<td align="center" id="label01">创建人</td>
					<td><input  class="form-control" readonly="readonly"  name="us_no" value="${userInfoMap.name}"/></td>
					<td align="center" id="label01">创建时间</td>
					<td><input class="form-control" readonly="readonly" value="当前时间"/></td>
				</tr>
				<tr>
					<td align="center" id="label01">成功几率</td>
					<td><input class="form-control"  name="xs04"/></td>
					<td colspan="4">
						<div>
							<a href="javascript:history.back()"><button type="button" class="btn btn-default" style="margin-left: 75%;">返回</button></a>
							<button onclick="tijiao('p2')" id="submitButton" type="submit" value="新建" class="btn btn-primary"/>新建</button>
							<p id="p2" style="display: none;">请完善信息</p>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js" ></script>
<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js" ></script>
<script type="text/javascript">
	var m=document.getElementById('a');
	var n=document.getElementById('s');
	function feikong()
	{
		
		if(m.value!=""){
			n.style.visibility='hidden';
		}else{
			n.style.visibility='visible';
		}
	}
	function tijiao(p2){
		var c=document.getElementById('p2');
		if(m.value==""){
			c.style.display='block';
			return false;
		}
		c.style.display='none';
	}
</script>
</html>
