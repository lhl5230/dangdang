var flag ={"receiveName":false,"fullAddress":false,"postalCode":false,
								"phone":false,"mobile":false};
		//页面载入的时候根据用户的id，查找出对应的地址添加到option
			$(function(){
				$.post(
					"../order/loadAddress.action",
					function(data){
						if(data.length!=0){
							for(var i=0;i<data.length;i++){
								//获取收货人名
								var receiveName = data[i].receiveName;
								var id = data[i].id;//获取收货人ID
								//生成一个option添加到下拉单中
								var $opt = $("<option value='"+id+"'>"+receiveName+"</option>");
								$("#address").append($opt);
							}
						}
					}
				);
				//更改地址后，把对应的地址填写到表格中
				$("#address").change(function(){
					var val = $(this).val();
					if(val!=0){
						$.post(
							"../order/selectAddress.action",
							{"id":val},
							function(data){
								$("#receiveName").val(data.receiveName);
								$("#fullAddress").val(data.fullAddress);
								$("#postalCode").val(data.postalCode);
								$("#phone").val(data.phone);
								$("#mobile").val(data.mobile);
								$("#addressId").val(data.id);
							}
						);
					}else{
						$("input.text_input").val("");
						$("addressId").val("0");
					}
					});
					
					
				});
			//验证表单数据
			$(function(){
				$("#receiveName").blur(function(){
					var receiveName = $(this).val();
					receiveName =$.trim(receiveName);
					if(receiveName==""){
						$("#receiveNameInfo").html("不能为空");
						flag.receiveName=false;
						return;
					}
					var receiveNameCheck = /\b(^[a-z0-9]{4,20}$)\b/;
					if(!receiveNameCheck.test(receiveName)) {
						$("#receiveNameInfo").html("格式不正确");	
						flag.receiveName=false;
						return;
					}
					flag.receiveName=true;
				});
				$("#fullAddress").blur(function(){
					var fullAddress = $(this).val();
					fullAddress =$.trim(fullAddress);
					if(fullAddress==""){
						$("#fullAddressInfo").html("不能为空");
						flag.fullAddress=false;
						return;
					}
					var fullAddressCheck = /\b(^[a-z0-9]{4,20}$)\b/;
					if(!fullAddressCheck.test(fullAddress)) {
						$("#fullAddressInfo").html("格式不正确");	
						flag.fullAddress=false;
						return;
					}
					flag.fullAddress=true;
				});
				$("#postalCode").blur(function(){
					var postalCode = $(this).val();
					postalCode =$.trim(postalCode);
					if(postalCode==""){
						$("#postalCodeInfo").html("不能为空");
						flag.postalCode=false;
						return;
					}
					var postalCodeCheck = /\b(^[a-z0-9]{4,8}$)\b/;
					if(!postalCodeCheck.test(postalCode)) {
						$("#postalCodeInfo").html("格式不正确");	
						flag.postalCode=false;
						return;
					}
					flag.postalCode=true;
				});
				$("#phone").blur(function(){
					var phone = $(this).val();
					phone =$.trim(phone);
					if(phone==""){
						$("#phoneInfo").html("不能为空");
						flag.phone=false;
						return;
					}
					var phoneCheck = /\b(^[a-z0-9]{4,20}$)\b/;
					if(!phoneCheck.test(phone)) {
						$("#phoneInfo").html("格式不正确");	
						flag.phone=false;
						return;
					}
					flag.phone=true;
				});
				$("#mobile").blur(function(){
					var mobile = $(this).val();
					mobile =$.trim(mobile);
					if(mobile==""){
						$("#mobileInfo").html("不能为空");
						flag.mobile=false;
						return;
					}
					//var mobileCheck = /\b(^[a-z0-9]{4,20}$)\b/;
					var mobileCheck = /\b(^[0-9]{4,15}$)\b/;
					if(!mobileCheck.test(mobile)) {
						$("#mobileInfo").html("格式不正确");	
						flag.mobile=false;
						return;
					}
					flag.mobile=true;
				});
				
				//清空
				$("#receiveName").focus(function() {
					$("#receiveNameInfo").empty();
				});
				$("#fullAddress").focus(function() {
					$("#fullAddressInfo").empty();
				});
				$("#postalCode").focus(function() {
					$("#postalCodeInfo").empty();
				});
				$("#phone").focus(function() {
					$("#phoneInfo").empty();
				});
				$("#mobile").focus(function() {
					$("#mobileInfo").empty();
				});
			});
			
			//提交
			$(function() {
				$("#f").submit(function() {
					//一旦按注册就触发所有检查事件

					$("#receiveName").blur();
					$("#fullAddress").blur();
					$("#postalCode").blur();
					$("#phone").blur();
					$("#mobile").blur();

					//都为真时可提交
					var ok = flag.receiveName && flag.fullAddress && flag.postalCode && flag.phone && flag.mobile;
					
					if(ok==false) {
						alert("信息未填完整");
						return false;//不予提交
					}
					return true;//提交
				});
			});