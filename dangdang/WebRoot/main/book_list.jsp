<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>

		<link href="../css/book.css" rel="stylesheet" type="text/css" />
		<link href="../css/second.css" rel="stylesheet" type="text/css" />
		<link href="../css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="../css/list.css" rel="stylesheet" type="text/css" />
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
								$(".cartinfo_"+id).html("添加到购物车");
							}else{
								$(".cartinfo_"+id).html("该商品已经添加,请到购物车查看");
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
				
				$(".list_r_title_ml").change(function(){
					var val = $(this).val();//排序方式					
					//var catid = $(".hiddencatid").val();//当前排序的catId,用隐藏域方式
					var catid = "${catId}";
					//var pid = $(".hiddenpid").val();//当前排序的pid
					var pid = "${pid}";
					$.post(
						"booklist.action",
						{"pid":pid,"catId":catid,"sort":val},
						function(data){
							$("body").html(data);
						}
					);				
				});
				//点击上一页  
					$("#link_page_pre").click(function(){
						var val = "${sort}";//排序方式
						
						//var catid = $(".hiddencatid").val();//当前排序的catId,用隐藏域方式
						var catid = "${catId}";
						//var pid = $(".hiddenpid").val();//当前排序的pid
						var pid = "${pid}";
						var currentPage = "${currentPage}";
						$.post(
							"booklist.action",
							{"pid":pid,"catId":catid,"currentPage":parseInt(currentPage)-1,"sort":val},
							function(data){
								$("body").html(data);
							}
						);				
				});	
				//ajax点击下一页(链接传递方式:booklist.action?pid=${pid }&catId=${catId}&currentPage=${currentPage+1}&sort=${sessionScope.sort})
					$("#link_page_next").click(function(){
						var val = "${sort}";//排序方式
						//var catid = $(".hiddencatid").val();//当前排序的catId,用隐藏域方式
						var catid = "${catId}";
						//var pid = $(".hiddenpid").val();//当前排序的pid
						var pid = "${pid}";
						var currentPage = "${currentPage}";//取出来为string类型，传参的时候要进行转换
						$.post(
							"booklist.action",
							{"pid":pid,"catId":catid,"currentPage":parseInt(currentPage)+1,"sort":val},
							function(data){
								$("body").html(data);
							}
						);				
				});				
			});
		
		</script>
	</head>
	<body>
		&nbsp;

		<!-- 头部开始 -->
		<%@include file="../common/head.jsp"%>
		<!-- 头部结束 -->

		<div style="width: 962px; margin: auto;">
			<a href="#"><img src="../images/default/book_banner_081203.jpg" border="0" /> </a>
		</div>
		<div class='your_position'>
			您现在的位置:&nbsp;
			<a href='main.action'>当当图书</a> &gt;&gt;
			<font style='color: #cc3300'><strong>${category.name}</strong> </font>
		</div>

		<div class="book">

			<!--左栏开始-->
			<div id="left" class="book_left">
				<div id="__fenleiliulan">
					<div class=second_l_border2>
						<h2>
							分类浏览
						</h2>
						<ul>
							<li>
								<div>
								<!-- 点击的是父类别，分类导航中的全部 高亮显示 -->
								<s:if test="category.parentId == 1">
									<div class="second_fenlei3">
										&middot;全部&nbsp;(${totalNum}) 
									</div>
								</s:if>
								<s:else>
									<div class="second_fenlei">
										&middot;全部&nbsp;(${totalNum}) 
									</div>
								</s:else>
								</div>
								
							</li>
							<div class="clear"></div>
						
							<!--2级分类开始-->
						<s:iterator value="cats">
							<li>
								<div>
									<div class="second_fenlei">
										&middot;
									</div>
								<!-- 选中的类目加样式 -->
								<s:if test="id == catId">
									<div>
										<a href="booklist.action?pid=${parentId }&catId=${id}" class="second_fenlei3">${name }&nbsp;(${pnum})</a>
									</div>
								</s:if>
								<s:else>
										<div class="second_fenlei">
										<a href="booklist.action?pid=${parentId }&catId=${id}">${name }&nbsp;(${pnum})</a>
									</div>
								</s:else>
								</div>
							</li>
						    <div class="clear"></div>
							
							<!--2级分类结束-->
						</s:iterator>
						
							<li>
								<div></div>
							</li>
						</ul>
						<input class="hiddencatid" type="hidden" value="${catId }"/>
						<input class="hiddenpid" type="hidden" value="${pid }"/>
					</div>
				</div>
			</div>

			<!--左栏结束-->

			<!--中栏开始-->
			<div class="book_center">

				<!--图书列表开始-->
				<div id="divRight" class="list_right">

					<div id="book_list" class="list_r_title">
						<div class="list_r_title_text">
							排序方式
						</div>
						<select onchange='' name='select_order' size='1'
							class='list_r_title_ml' id="list_r_title_ml">
						<s:if test="sort == 'addTime'">	
							<option value="addTime" selected="selected">
								按上架时间 降序
							</option>
							<option value="dangPrice">
								按当当价格 降序
							</option>
						</s:if>
						<s:elseif test="sort == 'dangPrice'">
							<option value="addTime">
								按上架时间 降序
							</option>
							<option value="dangPrice" selected="selected">
								按当当价格 降序
							</option>
						</s:elseif>	
						</select>
						<div id="divTopPageNavi" class="list_r_title_text3">

							<!--分页导航开始-->
							<!-- 判断是否为第一页 -->
						<s:if test="currentPage>1">
							<div class='list_r_title_text3a'>
								<a name=link_page_pre id="link_page_pre"
									href="javascript:void(0);">
								<img src='../images/page_up.gif' /> </a>
							</div>
						</s:if>
						<!-- 上一页按钮不可用 -->
						<s:else>
							<div class='list_r_title_text3a'>
								<img src='../images/page_up_gray.gif' />
							</div>
						</s:else>
						
							<div class='list_r_title_text3b'>
								第${currentPage}页/共${tatolPage }页
							</div>
							<!-- 判断是否为最后一页 -->
						<s:if test="currentPage<tatolPage">
							<div class='list_r_title_text3a'>
								<a name=link_page_next id="link_page_next"
									href="javascript:void(0);">
									<img src='../images/page_down.gif' /> </a>
							</div>
						</s:if>
						<!-- 下一页按钮不可用 -->
						<s:else>	
							<div class='list_r_title_text3a'>
								<img src='../images/page_down_gray.gif' />
							</div>
						</s:else>
							<!--分页导航结束-->
						</div>
					</div>
					
					<!--商品条目开始-->
					<s:iterator value="pros">
						<div class="list_r_line"></div>
						<div class="clear"></div>

						<div class="list_r_list">
							<span class="list_r_list_book"><a name="link_prd_img" href='productdetail.action?id=${id }'>
								<img src="../productImages/${productPic }" /> </a> </span>
							<h2>
								<a name="link_prd_name" href='productdetail.action?id=${id }'>${productName }</a>
							</h2>
						<h3></h3>
							<h4 class="list_r_list_h4">
								作 者:
								<a href='#' name='作者'>${author }</a>
							</h4>
							<h4>
								出版社：
								<a href='#' name='出版社'>${publishing }</a>
							</h4>
							<h4>
								出版时间：<s:date name="new java.util.Date(publishTime)" format="yyyy-MM-dd" />
							</h4>
							<h4>
								上架时间：<s:date name="new java.util.Date(addTime)" format="yyyy-MM-dd HH:mm:ss" />
							</h4>
							<h5>
								${description }
							</h5>
							<div class="clear"></div>
							<h6>
								<span class="del">定价：￥${fixedPrice }</span>
								<span class="red">当当价：￥${dangPrice }</span>
								节省：￥${fixedPrice-dangPrice }
							</h6>
							
							<span class="list_r_list_button"></span> 
							<a href="#" id="${id }" class="goumai"> 
							<img src='../images/buttom_goumai.gif' /></a>
							<span class="cartinfo_${id }"></span>
						</div>
						<div class="clear"></div>
					</s:iterator>	

						<!--商品条目结束-->

					<div class="clear"></div>
					<div id="divBottomPageNavi" class="fanye_bottom">
					</div>

				</div>
				<!--图书列表结束-->

			</div>
			<!--中栏结束-->
			<div class="clear"></div>
		</div>

		<!--页尾开始 -->
		<%@include file="../common/foot.jsp"%>
		<!--页尾结束 -->
		<s:debug></s:debug>
	</body>
</html>
