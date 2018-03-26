function adminCheck(){
	$("#account").click(function(){
		$("#errorMessage").text("");
	});
	$("#password").click(function(){
		$("#errorMessage").text("");
	});
	var username = $("#account").val();
	var password = $("#password").val();
	$.ajax({
			url:"/crm/lx_UserLogin.do",
			data:{us_name:username,us_pwd:password},
			dataType:"json",
			success:function(message) {
				if (message.loginInfo == 'OK') {
					location.href = '/crm/page/main.jsp';
				} else {
					$("#errorMessage").text('用户名或密码错误');
				}
			},
			error:function(){
				alert('网络异常');
			}
	});
	/*$.getJSON("/crm/lx_UserLogin.html",{us_name:username,us_pwd:password},function(message){
		alert(message);
		if(message.header.code==000){
			window.location.href="/shop/jsp/bookManage.jsp";
		}else if(message.header.code=="NO"){
			$("#errorMessage").text("用户名或密码错误!");
		}else{
			$("#errorMessage").text(message.header.message);
		}
	});*/
	
}