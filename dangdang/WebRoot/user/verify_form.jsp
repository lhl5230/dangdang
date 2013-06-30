<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户注册 - 当当网</title>
		<link href="../css/login.css" rel="stylesheet" type="text/css" />
		<link href="../css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
		<script type="text/javascript">
			var flag = {"verify":false};
			$(function(){
				$("#f").submit(function(){
					var val = $("#validatecode").val();
					$.post("verifycode.action",
						{"verifycode":val},
						function(data){
							if(data){
								//验证码正确，进入register_ok.jsp
								window.location = "register_ok.jsp";
								flag.verify = true;
							}else{
								//验证码错误，在本页面给出提示
								$("#errorMsg").html("验证码错误，请重新查阅邮箱后输入！");								
							}
						},
						"json"
					);
					return flag.verify;
				});
			});
		</script>
	</head>
	<body>
		<%@include file="../common/head1.jsp"%>

		<div class="login_step">
			注册步骤: 1.填写信息 &gt;
			<span class="red_bold">2.验证邮箱</span> &gt; 3.注册成功
		</div>
		<form action="verifycode.action" method="post" id="f">
			<div class="validate_email">
				<h2>
					感谢您注册当当网！现在请按以下步骤完成您的注册!
				</h2>
				<div class="look_email">
					<h4>
						第一步：查看您的电子邮箱
					</h4>
					<div class="mess reduce_h">
						我们给您邮箱:<span class="red"><span id="lblEmail">${user.email }</span>发送了验证邮件，验证码为：
						<s:property value="#session.user.emailVerifyCode"/>
						
						</span>
						<span class="t1"> 
						请登录您的邮箱收信。
						</span>
					</div>
					<h4>
						第二步：输入验证码
					</h4>
					<div class="mess">
						<span class="write_in">输入您收到邮件中的验证码：</span>
						<input name="code" type="text" id="validatecode" class="yzm_text" />
						<input class="finsh" type="submit" value="完 成" id="Button1" />
						<span id="errorMsg" class="no_right"></span>
					</div>
				</div>
			</div>
		</form>
		<%@include file="../common/foot1.jsp"%>
		
	</body>
</html>

