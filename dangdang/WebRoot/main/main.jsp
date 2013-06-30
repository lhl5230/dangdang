<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>
		<link href="../css/book.css" rel="stylesheet" type="text/css" />
		<link href="../css/second.css" rel="stylesheet" type="text/css" />
		<link href="../css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="../css/skin.css" rel="stylesheet" type="text/css" />
		
		<style type="text/css">
		*{margin: 0px;padding: 0px;}
		#d1{border:1px solid #aaaaaa;width: 500px;
			height: 130px;overflow: hidden;position: relative;}
		#adv,#num{position: absolute;}
		ul li{ list-style: none;display: inline; }
		ul img{
			width: 500px; height: 130px;display: block;
		}
		#num{
			right:5px;
			bottom:5px;
		}
		#num li{
			float: left;
			color: #000000;
			text-align: center;
			line-height: 16px;
			width: 16px;
			height: 16px;
			font-family: Arial;
			font-size: 12px;
			cursor: pointer;
			overflow: hidden;
			margin: -8px 1px;
			border: 1px solid #FF7300;		
		}
		.on{
		line-height: 21px;
		width: 21px;
		height: 21px;
		font-size: 16px;
		margin: 0 1px;
		border: 0;
		background-color:red;
		font-weight: bold;
	}
		</style>
		
	<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.jcarousel.js"></script>
	<script type="text/javascript">
		$(function(){
			//$("#recommend").load("recommend.action",{"size":2});
			$("#new").load("../main/new.action",{"size":8});
			//$("#left").load("category.action");
			//$('#mycarousel').jcarousel();
			//实现光标经过li,滚动对应的图片
		$('#num li').mouseenter(function(){
			//获得光标指向的下标
			var index = $('#num li').index(this);
			//滚动图片
			showImage(index);
		}).eq(0).mouseenter();
		 
		//实现光标离开，自动滚动，光标进入，停止滚动
		var i = 0;
		var taskId;
		$('#d1').hover(function(){
			//光标进入,停止滚动
			clearInterval(taskId);
		},function(){
			//光标离开，自动滚动
			taskId = setInterval(function(){
				showImage(i);
				i++;
				if(i == 5){
					i = 0;
				}
			},2000);
		}).mouseleave();
		});
//滚动图片
	function showImage(index){
		//stop(true): 先清空累积的动画
		$('#adv').stop(true).animate({'top':-130 * index},1000);
		//正指向的那个li加亮
		$('#num li').eq(index)
		.addClass('on').siblings().removeClass('on');
	}
	</script>

	</head>
	<body>
		&nbsp;
		<!-- 头部开始 -->
		<%@include file="../common/head.jsp"%>
		<!-- 头部结束 -->
		<div style="width: 962px; margin: auto;">
			<a href="#" target="_blank"><img
					src="../images/default/book_banner_081203.jpg" border="0" /> </a>
		</div>

		<div class="book">

			<!--左栏开始-->
			<div id="left" class="book_left">
				<s:action name="category" namespace="/main" executeResult="true">
				</s:action>
			</div>
			<!--左栏结束-->

			<!--中栏开始-->
			<div class="book_center">
				<!-- 图片浏览开始 -->
			<div id="d1"> 
				<ul id="adv" class="jcarousel-skin-tango">
			    <li><img src="../images/default/lax090105_48634_500x130.jpg" width="208" height="104" alt="" /></li>
			    <li><img src="../images/default/ancz081216_button05.gif" width="208" height="104" alt="" /></li>
			    <li><img src="../images/default/dqd_yf081226_e_2..jpg" width="208" height="104" alt="" /></li>
			    <li><img src="../images/default/lzh404.jpg" width="208" height="104" alt="" /></li>
			    <li><img src="../images/default/ncz081223_button03.jpg" width="208" height="104" alt="" /></li>
 				</ul>
 				<ul id="num">
					<li>1</li>
					<li>2</li>
					<li>3</li>
					<li>4</li>
					<li>5</li>
				</ul>
			</div>	 
				<!--推荐图书开始-->
				<div class=second_c_border1 id="recommend">
					<s:action name="recommend" namespace="/main" executeResult="true"></s:action>
				</div>
				
				<div class="book_c_border2" id="hot">
					<!--推荐图书结束-->
					<s:action name="hot" namespace="/main" executeResult="true"></s:action>
				<!--热销图书开始-->
				</div>
				<!--热销图书结束-->

				<!--最新上架图书开始-->
				<div class="book_c_border2" id="new">
				<img src="../images/window_loading.gif" alt="" />正在努力加载中......
				</div>

				<!--最新上架图书结束-->

				<div class="clear">
				</div>
			</div>
			<!--中栏结束-->

			<!--右栏开始-->
			<div class="book_right">
				<s:action name="newhot" namespace="/main" executeResult="true"></s:action>
			</div>
			<!--右栏结束-->
			<div class="clear"></div>
		</div>

		<!--页尾开始 -->
		<%@include file="../common/foot.jsp"%>
		<!--页尾结束 -->

	</body>
</html>
