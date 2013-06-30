<%@page contentType="text/html;charset=utf-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="../css/book.css" rel="stylesheet" type="text/css" />
<link href="../css/second.css" rel="stylesheet" type="text/css" />
<link href="../css/secBook_Show.css" rel="stylesheet" type="text/css" />
<div class="book_right">
				<div class="book_r_border2" id="__XinShuReMai">
					<div class="book_r_b2_1x" id="new_bang">
		<h2 class="t_xsrm">
			新书热卖榜 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span class="dot_r"> </span><a href="#" target="_blank">更多&gt;&gt;</a>
		</h2>
		<div id="NewProduct_1_o_t" onmouseover="">
															
		<s:iterator value="pros" status="i">
				
			<div class="bg_old" onmouseover="this.className = 'bg_white';"
				onmouseout="this.className = 'bg_old';">
				
				<s:if test="#i.index<3">
				<h3>
					<img alt="" src="../images/book_no0_${i.index+1 }.gif">
					<a href='productdetail.action?id=${id }' target="_blank">
					${productName}(内容:${description })</a>
				</h3>
				</s:if>
				<s:else>
				<h3>
					<a href='productdetail.action?id=${id }' target="_blank">
					${productName}(内容:${description })</a>
				</h3>
				</s:else>
				<ul class="ul_left_list">				
				</ul>
				<div class="empty_left">
				</div>
			</div>

			<div class="more2">
			</div>
			
		</s:iterator>
				
		</div>

		<div class="bg_old">
			<h3>
				&nbsp;
			</h3>
		</div>
	
	</div>

	</div>
</div>

