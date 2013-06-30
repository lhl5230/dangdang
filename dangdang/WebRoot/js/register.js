			var flagemail = {"email":false};
			var flagnickname = {"nickname":false};
			var flagpwd = {"pwd":false};
			var flagpwd1 = {"pwd1":false};
			var flagcode = {"code":false};
			$(function(){				
				//检测邮箱
				$("#txtEmail").blur(function(){
					//flagemail.email = false;
					//$("#emailinfo").html("");
					//检测非空
					var val = $(this).val();
					if(val == ""){
						$("#emailinfo").css("color","red");
						$("#emailinfo").html('<img src="../images/error.png" alt="" />邮箱不能为空');						
						return;
					}
					//检测格式
					var pattern=/\b(^['_A-Za-z0-9-]+(\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\.[A-Za-z0-9-]+)*((\.[A-Za-z0-9]{2,})|(\.[A-Za-z0-9]{2,}\.[A-Za-z0-9]{2,}))$)\b/;
					if(!pattern.test(val)){
						$("#emailinfo").css("color","red");
						$("#emailinfo").html("<img src='../images/error.png' alt='' />邮箱格式不正确");
						return;
					}
					//检测是否可用
					$.post(
						"checkemail.action",
						{"email":val},
						function(data){
							if(data){	
								$("#emailinfo").html("");
								flagemail.email = true;
							}else{
								$("#emailinfo").css("color","red");
								$("#emailinfo").html("<img src='../images/error.png' alt='' />邮箱已经被占用");
							}
						},'json'
					);
				});
				//定义一个判断中文为两个字符,英文为一个字符的函数方法 tLength
				//匹配中文字符的正则表达式： [\u4e00-\u9fa5]
				//匹配双字节字符(包括汉字在内)：[^\x00-\xff]
				String.prototype.tLength=function(){
						temp=this.replace(/([^\x00-\xff])/g,"$1$1");
						return temp.length;
				};
				//检测nickname
				$("#txtNickName").blur(function(){
					var reg = /^[\u4e00-\u9fa5a-z0-9]+$/ig;	
					var val = $(this).val();					
					if(reg.test(val)){						
						if(val.tLength()<=20 && val.tLength()>=4){
							flagnickname.nickname = true;
							$("#nameinfo").html("恭喜您，此昵称可以注册！");
						}else{
							$("#nameinfo").css("color","red");
							$("#nameinfo").html("<img src='../images/error.png' alt='' />您的昵称不符合规则，请重新输入！");
						}
					}else{
						$("#nameinfo").css("color","red");
						$("#nameinfo").html("<img src='../images/error.png' alt='' />您的昵称不符合规则，请重新输入！");
						
					}					
				});
				 				
				//检测密码				
				$("#txtPassword").blur(
					function(){
					$("#passwordinfo").html("");
					var pwd = $("#txtPassword").val();
					var pwd1 = $("#txtRepeatPass").val();
					//您的密码可以由大小写英文字母、数字组成，长度6－20位。
					var pattern = /^[a-zA-Z0-9]{6,20}$/;
					if(!pattern.test(pwd)){
						$("#passwordinfo").css("color","red");
						$("#passwordinfo").html("<img src='../images/error.png' alt='' />密码不符合规则，请重新输入");
					}else if(pwd1 == "" || pwd != pwd1 ){
						$("#password1info").html("<img src='../images/error.png' alt='' />两次密码不一样");
						flagpwd1.pwd1 = false;
						flagpwd.pwd = true;
					}else{
						$("#password1info").html("");
						flagpwd1.pwd1 = true;
						flagpwd.pwd = true;
					}
				});
				
				//检测确认密码
				$("#txtRepeatPass").blur(function(){
					var pwd = $("#txtPassword").val();
					var pwd1 = $("#txtRepeatPass").val();
					if(pwd1 == "" || pwd != pwd1 ){
						$("#password1info").html("<img src='../images/error.png' alt='' />两次密码不一样");
					}else{
						$("#password1info").html("");
						flagpwd1.pwd1 = true;	
					}
				});
				//检测验证码
				$("#txtVerifyCode").blur(function(){
					var imageString = $(this).val();
					$.post("imageVerify.action",
						{"imageString":imageString},
						function(data){
							if(data){
								$("#vcodeValidMsg").html("");
								flagcode.code = true;
							}else{
								$("#vcodeValidMsg").css("color","red");
								$("#vcodeValidMsg").html("<img src='../images/error.png' alt='' />验证码输入错误");
							}
						},'json');
				});
				
				//点击验证码图片更换图片
				$("#imgVcode").click(function(){
					$(this).attr("src","imageCode.action?rand=" + (new Date()).getTime());
				});
				//点击更换图片链接更换图片
				$("#changImage").click(function(){
					$("#imgVcode").attr("src","imageCode.action?rand=" + (new Date()).getTime());
				});
			});
			//输入框获得焦点后事件
			$(function(){
			//email
				$("#txtEmail").focus(function(){
					$("#emailinfo").css("color","#414141");
					$("#emailinfo").html("请填写有效的Email地址，在下一步中您将用此邮箱接收验证邮件。");
				});
			//nickname
				$("#txtNickName").focus(function(){
					$("#nameinfo").css("color","#414141");
					$("#nameinfo").html("昵称可以由小写英文字母、中文、数字组成，长度4－20个字符");
				});
				
			//pwd
				$("#txtPassword").focus(function(){
					$("#passwordinfo").css("color","#414141");
					$("#passwordinfo").html("您的密码可以由大小写英文字母、数字组成，长度6－20位。");
				});
			//pwd1
				$("#txtRepeatPass").focus(function(){
					$("#password1info").html("");
				});					
			});
			//提交表单
			$(function(){
				$("#f").submit(function(){
					$("#txtEmail").blur();
					$("#txtNickName").blur();
					$("#txtPassword").blur();
					$("#txtRepeatPass").blur();
					$("#txtVerifyCode").blur();
	//alert("email"+flagemail.email);alert("nickname"+flagnickname.nickname);
	//alert("pwd"+flagpwd.pwd);  alert("pwd1"+flagpwd1.pwd1);alert("code"+flagcode.code);
					//判断各项输入是否有错，没错才允许提交
					return flagemail.email && flagnickname.nickname && flagpwd.pwd && flagpwd1.pwd1 && flagcode.code;
				});
			});
			