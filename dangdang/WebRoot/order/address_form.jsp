<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>生成订单 - 当当网</title>
		<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" /> 
		<link href="../css/login.css" rel="stylesheet" type="text/css" />
		<link href="../css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
		<script type="text/javascript">
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
					
					///\b(^[a-z0-9]{4,20}$)\b/
					var receiveNameCheck = /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,6}$/;
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
					var postalCodeCheck = /\b(^[a-z0-9]{6}$)\b/;
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
					var mobileCheck = /^1[3|5][0-9]\d{4,8}$/;
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
		</script>
	</head>
	<body>
		<%@include file="../common/head1.jsp"%>
		<div class="login_step">
			生成订单骤: 1.确认订单 &gt;
			<span class="red_bold"> 2.填写送货地址</span> &gt; 3.订单成功
		</div>
		<div class="fill_message">
			<p>
				选择地址：
				<select id="address">
					<option value="0">
						填写新地址
					</option>
				</select>
			</p>
			<s:form name="ctl00" method="post" action="affirmOrder" id="f">

				<input type="hidden" name="id" id="addressId" value="0"/>

				<table class="tab_login">
					<tr>
						<td valign="top" class="w1">
							收件人姓名：
						</td>
						<td>
							<input type="text" class="text_input" name="receiveName"
								id="receiveName" /><s:token></s:token>
							<div class="text_left" id="nameValidMsg">
								<p>
									请填写有效的收件人姓名(2-6位中文)
								</p>
								<span id="receiveNameInfo" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							收件人详细地址：
						</td>
						<td>
							<input type="text" name="fullAddress" class="text_input"
								id="fullAddress" /><s:token></s:token>
							<div class="text_left" id="addressValidMsg">
								<p>
									请填写有效的收件人的详细地址
								</p>
								<span id="fullAddressInfo" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							邮政编码
						</td>
						<td>
							<input type="text" class="text_input" name="postalCode"
								id="postalCode" /><s:token></s:token>
							<div class="text_left" id="codeValidMsg">
								<p>
									请填写有效的收件人的邮政编码
								</p>
								<span id="postalCodeInfo" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							电话
						</td>
						<td>
							<input type="text" class="text_input" name="phone"
								id="phone" /><s:token></s:token>
							<div class="text_left" id="phoneValidMsg">
								<p>
									请填写有效的收件人的电话
								</p>
								<span id="phoneInfo" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							手机
						</td>
						<td>
							<input type="text" class="text_input" name="mobile"
								id="mobile" /><s:token></s:token>
							<div class="text_left" id="mobileValidMsg">
								<p>
									请填写有效的收件人的手机
								</p>
								<span id="mobileInfo" style="color:red"></span>
							</div>
						</td>
					</tr>
				</table>

				<div class="login_in">

					<a href="../order/order.action"><input id="btnClientRegister" class="button_1" name="submit"
					type="button" value="取消" /></a>
			
				<input id="btnClientRegister" class="button_1" name="submit"
					type="submit" value="下一步" />
				</div>

			</s:form>
		</div>
		<%@include file="../common/foot1.jsp"%>
	</body>
</html>

