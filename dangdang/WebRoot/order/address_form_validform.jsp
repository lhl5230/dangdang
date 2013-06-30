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
		<link href="../css/validform.css" rel="stylesheet" type="text/css" />
		<style type="text/css"></style>
		<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
		<script type="text/javascript" src="../js/Validform_v5.3.2_min.js"></script>
		<!--<script type="text/javascript" src="../js/address_form.js"></script>-->
		<script type="text/javascript">
		$(function(){
			//获取以前存储过的地址
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
		//表单验证
			$("#f").Validform({
				tiptype:2,
				datatype:{
				"zh2-6":/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,6}$/
				}
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
						<td style="width:205px;">
							<input type="text" class="text_input" name="receiveName"
								id="receiveName" datatype="zh2-6" errormsg="请填写有效的收件人姓名（2到6位中文字符）！" /><s:token></s:token>
						</td>
						<td><div class="Validform_checktip">收件人至少2个中文,最多6个中文</div></td>

					</tr>
					<tr>
						<td valign="top" class="w1">
							收件人详细地址：
						</td>
						<td>
							<input type="text" name="fullAddress" class="text_input"
								id="fullAddress" datatype="*4-20" errormsg="请填写有效的收件人地址（4-20位字符）！"/><s:token></s:token>
							</td>
						<td><div class="Validform_checktip">地址至少4个字符,最多20个字符</div></td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							邮政编码：
						</td>
						<td>
							<input type="text" class="text_input" name="postalCode"
								id="postalCode" datatype="p" errormsg="请填写有效的邮政编码！"/><s:token></s:token>
							</td>
						<td><div class="Validform_checktip">请填写有效的邮政编码！</div></td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							电话：
						</td>
						<td>
							<input type="text" class="text_input" name="phone"
								id="phone" datatype="n8-14" errormsg="请填写有效的电话！"/><s:token></s:token>
						</td>
						<td><div class="Validform_checktip">请填写有效的收件人的电话！</div></td>		
							
					</tr>
					<tr>
						<td valign="top" class="w1">
							手机：
						</td>
						<td>
							<input type="text" class="text_input" name="mobile"
								id="mobile" datatype="m" errormsg="请填写有效的手机号码！" /><s:token></s:token>
							</td>
						<td><div class="Validform_checktip">请填写有效的收件人的手机号码！</div></td>		
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

