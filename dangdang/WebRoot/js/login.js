function check() {

	var email = $("#txtUsername").val();
	$("#errormsg").html("");
	//检查登陆邮箱是否为空
	if (email == "") {
		$("#errormsg").html("邮箱不能为空");
		return;
	}
	//检测格式
	var pattern = /\b(^['_A-Za-z0-9-]+(\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\.[A-Za-z0-9-]+)*((\.[A-Za-z0-9]{2,})|(\.[A-Za-z0-9]{2,}\.[A-Za-z0-9]{2,}))$)\b/;
	if (!pattern.test(email)) {
		$("#errormsg").html("邮箱格式不正确");
		return;
	}

	var pwd = $("#txtPassword").val();
	$("#errormsg").html("");
	if (pwd == "") {
		$("#errormsg").html("密码不能为空");
		return;
	}
	

	var name = $("#txtUsername").val();
	var pwd = $("#txtPassword").val();
	$.post("../user/login.action", {
		"name" : name,
		"password" : pwd
	}, function(data) {
		var val = $("#ctl00").attr("action");		
		if (data == "error") {
			//$("#ctl00").attr("action", "../user/login_form.jsp");
			//$("#ctl00").submit();
			$("#errormsg").html("用户名或密码错误");			
		} else if (data == "verify") {
			$("#ctl00").attr("action", "../user/verify_form.jsp");			
			$("#ctl00").submit();
		} else if (data == "success") {
			$("#ctl00").attr("action", "../main/main.jsp");			
			$("#ctl00").submit();
		} else{
			$("#ctl00").submit();
		}
	});
}


