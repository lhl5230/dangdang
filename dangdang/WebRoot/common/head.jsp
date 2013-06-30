<%@page import="com.opensymphony.xwork2.util.location.Location"%>
<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link href="../css/book_head090107.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
<script type="text/javascript">
$(function(){
	$("#login").click(function(){
		var href = window.location.pathname + window.location.search;//请求参数
		var val = $(this).attr("href");
		var newhref = val + "?" + href;
		$(this).attr("href",newhref);
	});
	
	
});
	//搜索
		$(function(){
			$('#search').click(function(){
				var key = $('#key').val();
				if(key==""){
					return;
				}
					//发送请求
					$.post('../main/search.action',
					'key=' + $('#key').val(),function(data){
						
						$("body").html(data);
					});
				});
				
		});
</script>
<div class="head_container">
	<div class="head_welcome">
		<div class="head_welcome_right">
			<span class="head_welcome_text"> </span>
			<span class="head_welcome_text"> <span class="little_n">
					| <a href="#" id="mydangdang" class="head_black12a">我的当当</a> | <a
					href="#" id="helpcenter" class="head_black12a" target="_blank">帮助</a>
					| </span> </span>
			<div class="cart gray4012">
				<a href="../cart/cartlist.action">购物车</a>
				  
			</div>
		</div>
		<span class="head_toutext" id="logininfo">
		<s:if test="#session.user==null">
		<b>您好，欢迎光临当当网</b>
		[&nbsp;<a href="../user/login2.action"  class="b" id="login">登录</a>|<a
			href="../user/register_form.jsp" class="b">注册</a>&nbsp;]
		</s:if>
		<s:else>
		<b>您好${user.nickname}，欢迎光临当当网</b>
		[&nbsp;<a href="../main/logout.action" class="b">登出</a>&nbsp;]
		</s:else>
		
		
		</span>
	</div>
	<div class="head_head_list">
		<div class="head_head_list_left">
			<span class="head_logo"><a href="/dangdang/main/main.jsp" id="backtobook"><img
						src="../images/booksaleimg/book_logo.gif" /> </a> </span>
		</div>
		<div class="head_head_list_right">

			<div class="head_head_list_right_b">
			</div>
		</div>
	</div>
	<!-- 搜索开始 -->
	<div class="head_search_div" style="">
	
	<input id="key" style="margin-top: 5px;margin-left: 740px;" size="10" value="${key }">
	
	<input id="search" type="button" value="搜索">
	</div>
	
	<div class="head_search_bg"></div>
	<!-- 搜索结束 -->
</div>
