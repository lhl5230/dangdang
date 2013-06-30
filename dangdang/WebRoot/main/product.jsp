<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<HTML>
<HEAD>
<TITLE>${product.productName }(${product.author }) - 图书 - 当当网</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK href="../product_files/dangdang.css" type=text/css rel=Stylesheet>
<LINK href="../product_files/book.css" type=text/css rel=stylesheet>
<style type="text/css">

</style>
<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
<script type="text/javascript">
	$(function(){
		$(".goumai").click(function(){
					var id = $(this).attr("id");
					
					//发送ajax发送购买请求
					$.post(
						"../cart/buy.action",
						{"pid":id},
						function(data){
							if(data){
								$("#cartInfo_"+id).html("添加到购物车");
							}else{
								$("#cartInfo_"+id).html("该商品已经添加到购物车,");
							}
						}
					);
					return false;
		});
		$(".goumai img").hover(
					function(){
						$(this).attr("src","../images/buttom_goumai_over.gif");
					},function(){
						$(this).attr("src","../images/buttom_goumai.gif");
					}
		);
		
	});
</script>
</HEAD>
<BODY>

	<DIV id=tag_box style="DISPLAY: none; Z-INDEX: 10; POSITION: absolute"></DIV>
	<DIV id=div_shield></DIV>
	<DIV id=main></DIV>
	<DIV class=mainsearch></DIV>
	<DIV class=wrap>
		<!--left start-->
	</DIV>
	<!--left end-->
	<DIV class=right>
		<DIV class=right_wai>
			<DIV class=shuming>
				<DIV class=shuming_left>
					<SPAN class=black000><A id=top_bk></A>${product.productName}</SPAN>
				</DIV>
				<DIV class=book_case>
					<SPAN class=seriesname>丛书名： ${product.productName }</SPAN>
				</DIV>
				<DIV class=empty_box></DIV>
			</DIV>
			<DIV class=book_left>
				<DIV class=book_pic>
					<A
						href="javascript:ImgBtnChgPrd_Click(this,'http://img39.ddimg.cn/93/5/20867709-1_o.jpg')"
						id=bigpicture_bk><IMG id=img_show_prd
						src="../productImages/${product.productPic }">
					</A>
				</DIV>
				<INPUT id=hid_largepictureurl type=hidden>
			</DIV>
			<DIV class=book_right>
				<DIV id=author_>作 者： ${product.author }</DIV>
				<DIV id=publisher_>出 版 社： ${product.publishing }</DIV>
				<UL>
					<LI>出版时间：
						<s:date name="new java.util.Date(product.publishTime)" format="yyyy-MM-dd"/>
					</LI>
					<LI>字 数： ${product.wordNumber }</LI>
					<LI>版 次： ${product.whichEdtion }</LI>
					<LI>页 数： ${product.totalPage }</LI>
					<LI>印刷时间： ${product.printTime }</LI>
					<LI>开 本： 16开</LI>
					<LI>印 次： ${product.printNumber }</LI>
					<LI>纸 张： 胶版纸</LI>
					<LI>I S B N ： ${product.isbn }</LI>
					<LI>包 装： 平装</LI>
				</UL>
				<DIV id=__categroy_bk>
				所属分类：
				<s:iterator value="cats" var="cat" status="i">					 	
				   <A class=blue12a href="main.jsp" target=_blank>${cat.name }</A>
				    
				</s:iterator>
				
				</DIV>
				<DIV class=jiage>
					<SPAN class=gray87>定价：<SPAN class=del id=originalPriceTag>￥${product.fixedPrice}</SPAN>
					</SPAN> <SPAN class=redc30>当当价：￥<B>${product.dangPrice }</B>
					</SPAN> 节省：￥${product.fixedPrice-product.dangPrice }
					<DIV class=pd_buy_num>
						<DIV class=clear></DIV>
					</DIV>
					<DIV>
						<A title=购买 id="${product.id }"  class=goumai> 
							<IMG id="buy" src="../product_files/booksale.gif">
							
						</A>
						<span id="cartInfo_${product.id}"></span>
						
						<a href="../cart/cartlist.action">查看购物车</a>
						<!--  <A id=favor title=收藏 href="#" name=wishlist_book>
<IMG id=imgfavor src="../product_files/bookz_save.gif"></A>
-->
					</DIV>

				</DIV>
			</DIV>
			<DIV id=dvTPUrls></DIV>
			<DIV id=__zhinengbiaozhu_bk>
				<DIV class=dashed></DIV>
				<H2 class=black14>
					<IMG src="../product_files/bg_point1.gif">
					编辑推荐
				</H2>
				<DIV class=zhengwen>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					在这本书里，我力图说明儿童早期教育中的某些问题，尤其要让读者认识到一个事实：我们的工作成就不仅仅是创建了一种新的教育模式。我们得出的结论已经显现于这本书名之中――发现孩子！<BR>――玛丽娅&#8226;蒙台梭利（本书作者）<BR>
					<BR>今天，人类社会迫切需要重建教育方法；为此而奋斗，也就是为人类复兴而奋斗！<BR>
					――塞吉（玛丽娅&#8226;蒙台梭利博士的老师）<BR>
					<BR>蒙台梭利理论体系的精华是她对下面这个真理的有力论断或再论断：除非在自由的气氛中，儿童即不可能发展自己，也不可能受到有益的研究。<BR>
					――E&#8226;G&#8226;霍尔姆斯（哈佛大学教育学院教授）
				</DIV>
				<DIV class=dashed></DIV>
				<H2 class=black14>
					<IMG src="../product_files/bg_point1.gif" align=absMiddle>
					内容简介
				</H2>
				<DIV class=zhengwen>${product.description }</DIV>
				<DIV class=dashed></DIV>
				<H2 class=black14>
					<IMG src="../product_files/bg_point1.gif" align=absMiddle>
					作者简介
				</H2>
				<DIV class=zhengwen>${product.author} ${product.authorSummary
					}</DIV>
				<DIV class=dashed></DIV>
				<H2 class=black14>
					<IMG src="../product_files/bg_point1.gif" align=absMiddle> 目录
				</H2>
				<DIV class=zhengwen>${product.catalogue }</DIV>
				<DIV class=dashed></DIV>
				<H2 class=black14>
					<IMG src="../product_files/bg_point1.gif" align=absMiddle>
					媒体评论
				</H2>
				<DIV class=zhengwen>好，非常畅销!难得的一本好书</DIV>
				<DIV class=dashed></DIV>
				<H2 class=black14>
					<IMG src="../product_files/bg_point1.gif" align=absMiddle>
					书摘插图
				</H2>
				<DIV class=zhengwen>
					第2章 对教育方法的历史回顾<BR>
					儿童之家使用的教育体系实际上已经向前迈出了一大步。假如人们认为我和正常儿童相处所得的经验相对短暂，那么此经验建立在以往对非正常儿童的教育经验基础之上，因此也代表着一段相当长期的思想。<BR>
					假如我们想开发出科学的教育体系，就必须开辟出一条前所未有的新路。教师们必须接受专业培训，同时而学校也必须进行转变。假如教师们都接受了观察与实验的培训，那他们必须在学校里执行这些活动。<BR>
					因此，科学教育体系的基本需求是有一个能允许儿童自由发展个性的学校。如果某种教育体系是基于对学生个体的研究，那么其研究方式应该是对行动自由的儿童进行观察和研究，而不是对一个受压制的学生进行观察和研究。<BR>
					在人类学和实验心理学的帮助下，在一个人应试教育为主的学校里，开发新型教育方法，是最愚蠢的想法。<BR>
					每个领域的实验科学，都是由使用自己独特的方法中发展而来。细菌学起源于分离并培育微生物。犯罪学、医学和教育学也都分别在不同类型的个体上使用过最初的人体测量方法，如在罪犯上、精神病人、医院里的临床病人、学生身上等。实验心理学在开始阶段就要指出执行实验的精确技术。<BR>
					……
				</DIV>
			</DIV>
			<A name=review_point></A>
		</DIV>
	</DIV>
	<DIV id=tag_box style="DISPLAY: none; Z-INDEX: 2; POSITION: absolute"></DIV>
	<DIV id=tag_box_pay
		style="DISPLAY: none; Z-INDEX: 2; POSITION: absolute"></DIV>
	<DIV id=div_shield></DIV>
	<!--页尾 开始 -->
	<DIV class=public_footer_add_s></DIV>
	<!--09.3.10new-->
	<!--页尾 end -->
	<!--页尾开始 -->
	<%@include file="../common/foot.jsp"%>
	<!--页尾结束 -->
	<s:debug></s:debug>
</BODY>
</HTML>
