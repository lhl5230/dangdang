<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<h2>
	<span class="more"><a href="#" target="_blank">更多&gt;&gt;</a> </span>热销图书
</h2>
<div class="book_c_04">
<s:iterator value="pros">
	<!--热销图书A开始-->
	<div class="second_d_wai">
		<div class="img">
			<a href="productdetail.action?id=${id }" target='_blank'><img
					src="../productImages/${productPic }" border=0 /> </a>
		</div>
		<div class="shuming">
			<a href="productdetail.action?id=${id }" target="_blank">${productName }</a><a href="#" target="_blank"></a>
		</div>
		<div class="price">
			定价：￥${fixedPrice }
		</div>
		<div class="price">
			当当价：￥${dangPrice }
		</div>
	</div>
	<div class="book_c_xy_long"></div>
	<!--热销图书A结束-->
</s:iterator>
</div>
<div class="clear"></div>