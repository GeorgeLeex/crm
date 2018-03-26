<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/crm/css/size.css" />
<style type="text/css">
* {
	padding: 0;
	margin: 0;
}
/*#left{
			width: 200px;
			height: 635px;
			background-color: #3955A3;
			display: inline-block;
			margin: 0;
		}*/
#right {
	background-color: #F1F1F1;
	display: inline-block;
	margin: 0;
	position: fixed;
}

#breadcrumb {
	/*margin-top: 50px;*/
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

#left_header {
	margin-top: 30px;
}

.importantInput {
	display: inline-block;
	width: 97% !important;
}

.importantTag {
	color: red;
	vertical-align: middle;
}
</style>
</head>

<body>
	<div id="right" class="size">
		<ol class="breadcrumb" id="breadcrumb">
			<li><span>服务管理</span></li>
			<li style="color: #269ABC;"><span>服务创建</span></li>
			<li><span>${msg }</span></li>
		</ol>
		<form action="/crm/fw_FwAddServlet.do" method="post">
			<table id="table01" class="table table-bordered">
				<tr>
					<td align="center" id="label01">客户</td>
					<td><select class="form-control importantInput" name="kh_no">
							<c:forEach var="ibm" items="${rows[0]}">
								<option value="${ibm.kh_no}">${ibm.kh02}</option>
							</c:forEach>
					</select> <span
						class="importantTag">*</span></td>
					<td align="center" id="label01">服务类型</td>
					<td><select class="form-control importantInput" name="cs_no">
							<c:forEach var="im" items="${rows[1]}">
								<option value="${im.cs_no}">${im.cs_name}</option>
							</c:forEach>
					</select> <span class="importantTag">*</span></td>
				</tr>
				<tr>
					<td align="center" id="label01">概要</td>
					<td>
					<input class="form-control importantInput" name="fw03" /><span
						class="importantTag">*</span></td>
					<td align="center" id="label01">状态</td>
					<td align="center" id="label01" name="zt">新创建</td>
				</tr>
				<tr>
					<td align="center" id="label01">服务请求</td>
					<td colspan="3">
					<textarea rows="10" class="form-control"
							style="width: 99%; display: inline-block;" name="fw06"></textarea><span
						class="importantTag">*</span></td>
				</tr>
				<tr>
					<td align="center" id="label01">创建人</td>
					<td><input class="form-control importantInput" name="name" value="${userInfoMap.name}"
						readonly="readonly" name="ren" /></td>
						<input type="hidden" name="us03" value="${userInfoMap.us03}"/>
					<td align="center" id="label01">创建时间</td>
					<td><input class="form-control importantInput"
						readonly="readonly" name="date"  value="当前时间"/></td>
				</tr>
				<tr>
					<td colspan="4">
						<!--<button type="reset" class="btn btn-default" style="display: inline;">重置</button>-->
						<div>
							<input id="resetButton" type="reset" value="重置"
								class="btn btn-default" /> <input id="submitButton"
								type="submit" value="创建" class="btn btn-primary" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script>
	function changeDisplay(obj) {
		var childs = obj.childNodes;
		for (x in childs) {
			var child = childs[x];
			if (child.nodeName == 'P') {
				child.style.backgroundColor = '#192D64';
				child.childNodes[1].className = 'glyphicon glyphicon-menu-down';
				break;
			}
		}
		changeLiStatus(obj, 'block', '');
		var menus = document.getElementsByClassName('menu');
		for (i in menus) {
			var menu = menus[i];
			if (menu != obj) {
				changeLiStatus(menu, 'none', 'showColor');
			}
		}
	}
	
	function changeLiStatus(parentOfUl, status, showColor) {
		var childs = parentOfUl.childNodes;
		for (i in childs) {
			var child = childs[i];
			if (showColor == 'showColor') {
				if (child.nodeName == 'P') {
					child.style.backgroundColor = '#3955A3';
					child.childNodes[1].className = 'glyphicon glyphicon-menu-right';
					continue;
				}
			}
			if (child.nodeName == 'UL') {
				var lis = child.childNodes;
				for (j in lis) {
					var li = lis[j];
					if (li.nodeName == 'LI') {
						li.style.display = status;
					}
				}
				break;
			}
		}
	}
</script>
</html>
