<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<h2>
	编辑推荐
</h2>
		
<div id=__bianjituijian/danpin>

	<div class="second_c_02">
	<s:iterator value="list" var="b">
		<div class="second_c_02_b1">
	
			<div class="second_c_02_b1_1">
				<a href='productdetail.action?id=${id }' target='_blank'><img src="../productImages/${b.productPic }" width=70 border=0 /> </a>
			</div>
			<div class=second_c_02_b1_2>
				<h3>
					<a href='productdetail.action?id=${id }' target='_blank' title='${b.productName }'><s:property value="#b.productName"/></a>
				</h3>
				<h4>
					作者：<s:property value="#b.author"/>
					<br />
					出版社：<s:property value="#b.publishing"/>&nbsp;&nbsp;&nbsp;&nbsp;出版时间：
					<s:date name="new java.util.Date(#b.publishTime)" format="yyyy-MM-dd"/>
					
				</h4>
				<h5>
					<s:property value="#p.description"/>
				</h5>
				<h6>
					定价：<s>￥<s:property value="#b.fixedPrice"/></s>&nbsp;&nbsp;<span class="red">当当价：￥<s:property value="#b.dangPrice"/></span>
				</h6>
				<div class=line_xx></div>
			</div>
	
		</div>
	</s:iterator>
	</div>
		
</div>
