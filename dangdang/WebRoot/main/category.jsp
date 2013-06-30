<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<div class="book_l_border1" id="__FenLeiLiuLan">
	<div class="book_sort_tushu">
		<h2>
			分类浏览
		</h2>
<s:iterator value="cats" var="cat">
		<!--1级分类开始-->
			<div class="bg_old" onmouseover="this.className = 'bg_white';"
				onmouseout="this.className = 'bg_old';">
				<h3>
					[<a href='booklist.action?pid=${cat.id }'>${cat.name}</a>]
				</h3>
				<ul class="ul_left_list">
						<!--2级分类开始-->
						<s:iterator value="subCats" var="c">
						<li>
							<a href='booklist.action?pid=${cat.id }&catId=${c.id}'>${c.name }</a>
						</li>
						
						<!--2级分类结束-->
						</s:iterator>
				</ul>
				<div class="empty_left">
				</div>
			</div>

			<div class="more2">
			</div>
			<!--1级分类结束-->
</s:iterator>

		<div class="bg_old">
			<h3>
				&nbsp;
			</h3>
		</div>
	</div>
</div>

