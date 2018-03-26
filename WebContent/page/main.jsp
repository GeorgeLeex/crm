<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/crm/bootstrap/css/bootstrap.min.css" />
<title></title>
<style type="text/css">
@import
url("/crm/css/main.css")
</style>
</head>
<body>
<div id="container" style="height: 974px;">
	<div id="left">
		<div id="left_header">
			<span id="span1">CRM</span> <br /> <span id="span2">系统</span>
		 </div>
		<div id="left_body">
		<c:forEach var="menu" items="${menus}">
			<div class="menu" onmouseover="changeDisplay(this)">
				<p>
					${menu.key}<span id="menu_right" class="glyphicon glyphicon-menu-right"></span>
				</p>
				<ul>
					<c:forEach var="m" items="${menu.value}">
						<c:forEach var="map" items="${m}">
							<li><a href="${map.value}" target="view">${map.key}</a></li>
						</c:forEach>
					</c:forEach>
				</ul>
			</div>
		</c:forEach>
		<%-- <div id="left_header">
				<span id="span1">CRM</span> <br /> <span id="span2">系统</span>
			 </div>
		<div id="left_body">
			<div class="menu" onmouseover="changeDisplay(this)">
				<p>
					营销管理<span id="menu_right" class="glyphicon glyphicon-menu-right"></span>
				</p>
				<ul>
					<li><a href="/crm/dgp_YxSelectServlet.do" target="view">销售机会管理</a></li>
					<li><a href="/crm/dgp_KfSelectServlet.do?us_no=${userInfoMap.us_no}" target="view">客户开发计划</a></li>
				</ul>
				?name= ${userInfoMap.name}&us03=${userInfoMap.us03}"
			</div>
			<div class="menu" onmouseover="changeDisplay(this)">
				<p>
					客户管理<span id="menu_right" class="glyphicon glyphicon-menu-right"></span>
				</p>
				<ul>
					<li><a href="/crm/lx_FindAllCustomer.do"   target="view" >客户信息管理</a></li>
					<li><a href="/crm/lx_FindAllCustomerLoss.do"  target="view" >客户流失管理</a></li>
				</ul>
			</div>
			<div class="menu" onmouseover="changeDisplay(this)">
				<p>
					服务管理<span id="menu_right" class="glyphicon glyphicon-menu-right"></span>
				</p>
				<ul>
					<li><a href="/crm/fw_TypeServlet.do" target="view">服务创建</a></li>
					<li><a href="/crm/dgp_Fw02Servlet.do" target="view">服务分配</a></li>
					<li><a href="/crm/fw_Fw03Servlet.do" target="view">服务处理</a></li>
					<li><a href="/crm/fw_Fw04Servlet.do" target="view">服务反馈</a></li>
					<li><a href="fw08.jsp" target="view">服务归档</a></li>
				</ul>
			</div>
			<div class="menu" onmouseover="changeDisplay(this)">
				<p>
					统计报表<span id="menu_right" class="glyphicon glyphicon-menu-right"></span>
				</p>
				<ul>
					<li><a href="/crm/dgp_BbServlet.do" target="view">客户贡献分析</a></li>
					<li><a href="/crm/dgp_DjServlet.do" target="view">客户组成分析</a></li>
					<li><a href="/crm/dgp_FwServlet.do" target="view">客户服务分析</a></li>
					<li><a href="/crm/dgp_LsServlet.do" target="view">客户流失分析</a></li>
				</ul>
			</div>
			<div class="menu" onmouseover="changeDisplay(this)">
				<p>
					基础数据<span id="menu_right" class="glyphicon glyphicon-menu-right"></span>
				</p>
				<ul>
					<li><a href="/crm/lx_FindAllDictionary.do" class="href" target="view">数据字典管理</a></li>
					<li><a href="/crm/lx_FindAllGoods.do" class="href" target="view">产品信息管理</a></li>
					<li><a href="/crm/lx_FindAllStock.do" class="href" target="view">查询库存</a></li>
					<li><a href="/crm/lx_FindAllOrders.do" target="view">订单管理</a></li>
				</ul>
			</div>--%>
		</div> 
		
	</div>
	<div id="right1">
		<div id="time">
			<span class="glyphicon glyphicon-time"></span>
			<div id="clock">
				<p class="time">{{ time }}</p>
			</div>
		</div>
		<div id="right1_body">
			<img src="/crm/img/u=1290280613,2049723008&fm=27&gp=0.jpg" width="34"
				height="34" class="img-circle" /> &nbsp; <span>${userInfoMap.us03}
				[ ${userInfoMap.name} ]</span> &nbsp;
		</div>
		<div id="logout">
			<a onclick="validate()" style="text-decoration: none"><span
				class="glyphicon glyphicon-off"></span></a>
		</div>
	</div>
	<div id="right2" style="overflow: hidden;">
		<iframe width="100%" height="100%" frameborder="0" border="0"
			name="view" marginheight="0" marginwidth="0" scrolling="no"
			src="/crm/page/welcome.jsp"> </iframe>
	</div>
</div>
</body>
<script type="text/javascript" src="/crm/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/crm/js/vue.min.js"></script>
<script type="text/javascript" src="/crm/bootstrap/js/bootstrap.min.js"></script>
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
<script>
	function validate() {
		$.get("/crm/lx_UserLogoff.do", function() {
			location.href = '/crm/page/login2.jsp';
		});
	}
</script>
<script>
	var clock = new Vue({
		el : '#clock',
		data : {
			time : ''
		}
	});

	/*var week = ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];*/
	var timerID = setInterval(updateTime, 1000);
	updateTime();
	function updateTime() {
		var cd = new Date();
		clock.time = zeroPadding(cd.getHours(), 2) + ':'
				+ zeroPadding(cd.getMinutes(), 2) + ':'
				+ zeroPadding(cd.getSeconds(), 2);
		/*clock.date = zeroPadding(cd.getFullYear(), 4) + '-' + zeroPadding(cd.getMonth()+1, 2) + '-' + zeroPadding(cd.getDate(), 2) + ' ' + week[cd.getDay()];*/
	};

	function zeroPadding(num, digit) {
		var zero = '';
		for (var i = 0; i < digit; i++) {
			zero += '0';
		}
		return (zero + num).slice(-digit);
	}
</script>
</html>
