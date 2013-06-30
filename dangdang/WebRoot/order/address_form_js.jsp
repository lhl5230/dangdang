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
		<script type="text/javascript" src="../js/address_form.js">
		
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
									请填写有效的收件人姓名
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

					<a href="order_info.jsp"><input id="btnClientRegister" class="button_1" name="submit"
					type="submit" value="取消" /></a>
			
				<input id="btnClientRegister" class="button_1" name="submit"
					type="submit" value="下一步" />
				</div>

			</s:form>
		</div>
		<%@include file="../common/foot1.jsp"%>
	</body>
</html>

