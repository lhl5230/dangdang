
try  {  
	document.domain='dangdang.com';
}
catch(err) {}

String.prototype.trim=function()
{
	return this.replace(/(\s*$)|(^\s*)/g, '');
}

//window.onerror=function()
//{
//    return true;
//}
function JSLoad(url, container, type, defer, language, title)
{

	if(container == undefined || container == null) container = this;

	// setup container
	if(typeof container.write == "undefined")
		if(typeof container.document != "undefined")
			container = container.document;
		else throw "Invalid container. Unable to load [" + url + "]";


	if(type == undefined || type == null)
	{
		type = '';


		if(language == undefined || language == null)
		{
			language = undefined;
			type = "text/javascript";
		}
	}


	if(language == undefined || language == null) language = "JavaScript";

	// set title
	if(title == undefined || title == null) title = '';

	// set defer
	if(defer == undefined) defer = false;


	var script = container.createElement("script");
	script.defer = defer;
	script.language = language;
	script.title = title;
	script.type = type;
	script.src = url;


	var head = container.getElementsByTagName("head")[0];
	head.appendChild(script);
}

function $(element) {
	if (typeof element == 'string')
		element = document.getElementById(element);
	return element;
}


var PosHelper = {
	getPosition : function(o){

		var nLt=0;
		var nTp=0;
		var offsetParent = o;

		while (offsetParent!=null && offsetParent!=document.body) {

			nLt+=offsetParent.offsetLeft;
			nTp+=offsetParent.offsetTop;

			if(!this.IsNav()){
				parseInt(offsetParent.currentStyle.borderLeftWidth)>0?nLt+=parseInt(offsetParent.currentStyle.borderLeftWidth):"";
				parseInt(offsetParent.currentStyle.borderTopWidth)>0?nTp+=parseInt(offsetParent.currentStyle.borderTopWidth):"";
			}
			offsetParent=offsetParent.offsetParent;
		}

		return {
			x:nLt,
			y:nTp
		};
	},


	IsIE:function(){
		return ( navigator.appName=="Microsoft Internet Explorer" );
	},

	IsNav:function (){
		return ( navigator.appName=="Netscape" );
	},

	SetOffset:function(sobj,tobj,x,y )
	{

		if (typeof sobj == 'string') sobj = document.getElementById(sobj);
		if (typeof tobj == 'string') tobj = document.getElementById(tobj);

		var pos = this.getPosition(sobj);
		if(tobj)
		{
			tobj.style.left= pos.x + x + "px";
			tobj.style.top = pos.y + y +  "px";
			tobj.style.display="block";
		}
	}

}

// JScript File
function Ajax(url)
{
	this.m_xmlReq=false;
	this.Url=url;

	if(window.XMLHttpRequest)
	{
		this.m_xmlReq = new XMLHttpRequest();
		if(this.m_xmlReq.overrideMimeType)  this.m_xmlReq.overrideMimeType('text/xml');
	}
	else if(window.ActiveXObject)
	{
		try
		{
			this.m_xmlReq = new ActiveXObject('Msxml2.XMLHTTP');
		}
		catch(e)
		{
			try
			{
				this.m_xmlReq = new ActiveXObject('Microsoft.XMLHTTP');
			}
			catch(e){}
		}
	}

	this.invokeServer=function(send_data,method)
	{
		if(!this.m_xmlReq)  return;

		this.m_xmlReq.open(method,this.Url,false);

		if(method=='POST')
			//this.m_xmlReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			this.m_xmlReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		// this.m_xmlReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gb2312");

		this.m_xmlReq.send(send_data.toString());
		var result=null;
		if(this.m_xmlReq.status==200 && this.m_xmlReq.readyState == 4)
			eval("result="+this.m_xmlReq.responseText);

		return result;

	}
}


var StringHelper = {
	filter : function(str)
	{
		var re1 = /(\<.[^\<]*\>)/g;
		return str.replace(/"/g,"").replace(/'/g,"").replace(re1,"");
	}

}

String.prototype.lenB   =   function(){
	return   this.replace(/[^\x00-\xff]/g,"**").length;
}

var StringBuilder = function(){
	this.cache = [];
	if(arguments.length)this.append.apply(this,arguments);
}
StringBuilder.prototype = {
	prepend:function(){
		this.cache.splice.apply(this.cache,[].concat.apply([0,0],arguments));
		return this;
	},
	append:function(){
		this.cache = this.cache.concat.apply(this.cache,arguments);
		return this;
	},
	toString:function(){
		return this.getString();
	},
	getString:function(){
		return this.cache.join('');
	}
}



var reInnerHTML = function(ele,innerHtml) {
	var nA=navigator.appVersion;
	if(nA.indexOf('MSIE')>=0)var curIE=nA.substr(nA.indexOf('MSIE')+5,3);
	if (curIE){
		innerHtml=innerHtml.replace(/<script([^>]*)>/gi,"<script$1 defer=\"true\">");
		var reStartScript=/^(\s*<script)/gi;
		if (reStartScript.test(innerHtml)){
			innerHtml="<span style=\"display:none;\">Hack IE</span>"+innerHtml;
			ele.innerHTML=innerHtml;
			ele.removeChild(ele.firstChild);
		}else{
			ele.innerHTML=innerHtml;
		}

	}else{
		var newObj=document.createElement(ele.tagName);
		newObj.id=ele.id;
		newObj.className=ele.className;
		newObj.innerHTML = innerHtml;
		ele.parentNode.replaceChild(newObj,ele);
	}
}



var XMLHttp = {
	_createXMLHttpRequest : function()
	{
		if (window.XMLHttpRequest)  {
			var objXMLHttp = new XMLHttpRequest();
		}
		else
		{
			var MSXML = ['MSXML2.XMLHTTP', 'Microsoft.XMLHTTP'];
			var Count = MSXML.length;
			for(var i = 0; i < Count; i ++)
			{
				try
				{
					var objXMLHttp = new ActiveXObject(MSXML[i]);
					break;
				}
				catch(e)
				{
				}
			}
		}

		// mozilla某些版本没有readyState属性
		if (objXMLHttp.readyState == null) {
			objXMLHttp.readyState = 0;
			objXMLHttp.addEventListener("load", function ()
			{
				objXMLHttp.readyState = 4;
				if (typeof objXMLHttp.onreadystatechange == "function") {
					objXMLHttp.onreadystatechange();
				}
			},   false);
		}

		return objXMLHttp;
	},

	_sendRequest : function(method, url, data, sendtype, datatype, callback, backpar)
	{
		var objXMLHttp = this._createXMLHttpRequest();
		with(objXMLHttp)
		{
			try
			{
				// 加随机数防止缓存
				if (url.indexOf("?") > 0){
					url += "&randnum=" + Math.random();
				}
				else{
					url += "?randnum=" + Math.random();
				}


				open(method, url, sendtype);
				//setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");
				setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");


				send(data);

				if(callback == null)
				{
					if (objXMLHttp.readyState == 4 && (objXMLHttp.status == 200 || objXMLHttp.status == 304))
					{
						return objXMLHttp.responseText;
					}
				}

				onreadystatechange = function ()
				{
					if (objXMLHttp.readyState == 4 && (objXMLHttp.status == 200 || objXMLHttp.status == 304))
					{
						if(callback != null){
							if(backpar != null) callback(objXMLHttp,backpar); else callback(objXMLHttp);
						} else {
							return objXMLHttp.responseText;
						}
					}
				}


			}
			catch(e)
			{
				alert('你的浏览器版本太低,建议你升级浏览器');
			}
			}
	}


}


var currTId = null;
var currDId = null;
function mouseOver(obj,tag0,tag1,id)
{
	if( currTId!=null)
	{
		if($(currTId)) $(currTId).style.display="block";
		if($(currDId)) $(currDId).style.display="none";
	}

	var Tid = tag0 + id;
	var Did = tag1 + id;

	if($(Tid)) $(Tid).style.display="none";
	if($(Did)) $(Did).style.display="block";

	currTId = Tid;
	currDId = Did;
}

function mouseOver2(obj,tag0,tag1,id){
	if(RTId!=null){
		if($(RTId)) $(RTId).style.display="block";
		if($(RDId)) $(RDId).style.display="none";
	}

	var Tid = tag0+id;
	var Did = tag1+id;

	if($(Tid)) $(Tid).style.display = "none";
	if($(Did)) $(Did).style.display = "block";

	RTId = Tid;
	RDId = Did;
}


function openWin(url,name,w,h)
{
	var x=(window.screen.width-w)/2;
	var y=(window.screen.height-h)/2;
	var win=window.open(url,name,'toolbar=0,location=0,status=0,top='+y+',left='+x+',width='+w+',height='+h );
	win.focus();

	return win;
}
function openSuitWin(url,name)
{
	var sw=window.screen.width;
	var sh=window.screen.height;
	var w=sw-300;
	var h=sh-300;
	var x=(sw-w)/2;
	var y=0;
	var win=window.open(url,name,'toolbar=0,scrollbars=1,location=1,menubar=1,resizable=1,status=1,top='+y+',left='+x+',width='+w+',height='+h );
	win.focus();
}

var last_img_btn_chg_prd=null;

function ImgBtnChgPrd_Mouseover(obj,mainpictureurl,largepictureurl)
{

	if(last_img_btn_chg_prd!=null)
		last_img_btn_chg_prd.className='img_btn_chg_prd';
	last_img_btn_chg_prd=obj;
	obj.className='img_btn_chg_prd_active';



	document.getElementById('img_show_prd').src=mainpictureurl;
	document.getElementById('img_show_prd').onclick=function(){
		ImgBtnChgPrd_Click(this,largepictureurl);
	}
	document.getElementById('spn_tobig').onclick=function(){
		ImgBtnChgPrd_Click(this,largepictureurl);
	}

}

function ImgBtnChgPrd_Click(obj,largepictureurl)
{

	if(arguments.length>1)
	{
		document.getElementById('hid_largepictureurl').value=largepictureurl;
	}
	var win=openWin('productexp.php','preview',700,580);
	win.opener=this;

}

function suitImage(img,w,h)
{
	var image=new Image();
	image.src=img.src;


	var iw=image.width;
	var ih=image.height;
	var iratio=iw/ih;

	if(iw>w)
	{
		iw=w;
		ih=w/iratio;
	}
	if(ih>h)
	{
		ih=h;
		iw=h*iratio;
	}

	if(iw > 0 && ih > 0)
	{
		img.width=iw;
		img.height=ih;
	}
}

function ImageOver(Mainimage,LargeImage)
{

	var img = document.getElementById('largePic');
	document.getElementById('hid_largepictureurl').value=LargeImage;
	img.src=Mainimage;


}
function AddToMallShoppingCart(product_id)
{
	var url=null;

	if(product_id==null || product_id<1)
		url="http://shopping.dangdang.com/shoppingcart/shopping_cart.aspx";
	else
		url="http://shopping.dangdang.com/shoppingcart/shopping_cart_add.aspx?product_ids="+product_id+"&reference_url="+escape(window.location.href);

	var popup=window.open(url,"shoppingcart");
	popup.focus()
}

function AddToWishList(product_id)
{


	showMsgBox('favor',product_id,'http://customer.dangdang.com/wishlist/remote_addtofavorlist.aspx');

}

function addlike(product_id)
{
	if(confirm(unescape("%u786E%u5B9A%u5C06%u6B64%u4EA7%u54C1%u52A0%u5165%u60A8%u611F%u5174%u8DA3%u7684%u5546%u54C1%u5417%uFF1F")))
		document.getElementById("likepd").src="http://www.dangdang.com/usercut/savelikepd.asp?productid="+product_id+"&like=1";
}

function AddToOutOfStock(product_id)
{

	var url='http://misc.dangdang.com/lackproduct/pdnothing.aspx?pdname='+product_id;

	var width=448;
	var height=227;
	var w = 1024;
	var h = 768;
	if (document.all || document.layers)
	{
		w = screen.availWidth;
		h = screen.availHeight;
	}
	var leftPos = (w/2-width/2);
	var topPos = (h/2.3-height/2.3);
	var ew=window.open(url,"","width="+width+",height="+height+",top="+topPos+",left="+leftPos);

}

function set_review_summary(product_type,total_review_count,total_question_count)
{
	//var str_total_count=product_type=='GM' || product_type=='UnKnow'?total_question_count:total_review_count;
	var str_total_count= product_type=='UnKnow'?total_question_count:total_review_count;
	if(str_total_count>'0')
	{
		var reviewTag = document.getElementById("review_summary");
		var revTag = document.getElementById("spn_rev_cnt");

		if(reviewTag)reviewTag.style.display='inline';
		if(revTag) revTag.innerHTML=str_total_count;
	}
	else
	{
		var respan = document.getElementById("reviewscount");
		if(respan) respan.style.display="none";
	}
}


var red_array=["顾客评分：","<img src='images/star_gray.gif' />","<img src='images/star_gray.gif' />","<img src='images/star_gray.gif' />","<img src='images/star_gray.gif' />","<img src='images/star_gray.gif' />"];
function set_average_score(average_score)
{
	if(average_score=='' || average_score < 0.5)
		return;

	var int_average_score = parseInt(average_score);
	for(var i=0;i<int_average_score;i++)
		red_array[i+1]="<img src='images/star_red.gif' />";

	if(int_average_score<average_score)
		red_array[i+1]="<img src='images/star_red2.gif' />";

	if( document.getElementById('spn_average_score'))
		document.getElementById('spn_average_score').innerHTML=red_array.join('');
}

//function AddToShoppingCart(product_id)
//{
//	var url=null;

//	if(product_id==null || product_id<1)
//		url="http://shopping.dangdang.com/shoppingcart/shopping_cart.aspx";
//	else
//		url="http://shopping.dangdang.com/shoppingcart/shopping_cart_add.aspx?product_ids="+product_id+"&reference_url="+escape(window.location.href);

//	var popup=window.open(url,"shoppingcart");
//	popup.focus()
//}

function AddToDonateShoppingCart(product_id)
{
	var url=null;

	if(product_id==null || product_id<1)
		url="http://shopping.dangdang.com/shoppingcart_donate/shopping_cart.aspx";
	else
		url="http://shopping.dangdang.com/shoppingcart_donate/shopping_cart_add.aspx?product_ids="+product_id+"&reference_url="+escape(window.location.href);

	var popup=window.open(url,"shoppingcart");
	popup.focus()
}


function getposOffset(what, offsettype)
{
	var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
	var parentEl=what.offsetParent;
	while (parentEl!=null)
	{
		totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
		parentEl=parentEl.offsetParent;
	}
	return totaloffset;
}

function showLocSelect(objid,divId,x_offset,y_offset)
{

	var locdiv=document.getElementById(divId);
	var obj=document.getElementById(objid);

	locdiv.style.top=(getposOffset(obj,"top")-y_offset)+"px";
	locdiv.style.left=(getposOffset(obj,"left")-x_offset)+"px";
	locdiv.style.display="block";
}

function hideLocSelect(divId)
{
	document.getElementById(divId).style.display="none";
}



var cat_link =
{
	'作者':'http://search.dangdang.com/book/search_pub.php?category=01&key2=',
	'出版社':'http://search.dangdang.com/book/search_pub.php?category=01&key3=',
	'歌手':'http://search.dangdang.com/music/search_pub.php?catalog=03&key2=',
	'演奏者':'http://search.dangdang.com/music/search_pub.php?catalog=03&key2=',
	'导演':'http://search.dangdang.com/movie/search_pub.php?catalog=05&key2=',
	'主演':'http://search.dangdang.com/movie/search_pub.php?catalog=05&key4='
};



var li_ids=
{
	'作者':'author_',
	'出版社':'publisher_',
	'歌手':'singer_',
	'演奏者':'singer_',
	'导演':'director_',
	'主演':'star_'
};



var click_99_name=
{
	'作者':'author',
	'出版社':'publisher',
	'歌手':'singer',
	'演奏者':'singer',
	'导演':'director',
	'主演':'star'
};

function key_link(cat,key,searchkey)
{
	var click_name = null;
	click_name = click_99_name;

	var normal_html= "";
	var link =cat_link[cat] + searchkey;
	var link_name=click_name[cat];
	var rep_id=li_ids[cat];
	var link_html="<a  class='blue12a'  href='"+link+"' name='"+link_name+"' target='_blank'>"+key+"</a>";
	if(document.getElementById(rep_id))normal_html = document.getElementById(rep_id).innerHTML;
	if(document.getElementById(rep_id)) document.getElementById(rep_id).innerHTML=normal_html.replace(key,link_html);
}



//present

function hidden(id)
{
	if($(id))$(id).style.display="none";
	if($("div_shield"))$("div_shield").style.display="none";

}

var result;
function OpenPresentWin(Id,parameters)
{
	var lin =  $("tag_box");
	var sta = parameters.split(":");

	if(result ==null)
	{

		var aAjax= new Ajax("callback.php");
		var send_data = "Dtype=present" + "&Par="+parameters
		result = aAjax.invokeServer(send_data,'POST');

	}
	if(result.errorCode != 1)
	{
		lin.innerHTML = presentWin(result);
		PosHelper.SetOffset(Id,lin,0,20);
	}
}


function presentWin(preData){

	var sb = new StringBuilder('<div class="wind_present" onmouseover=javascript:showLocSelect("giftlink","tag_box",0,-20); onmouseout=javascript:hidden("tag_box");>');
	sb.append('<div class="pres_c">');
	sb.append('<a target="_blank" href="http://product.dangdang.com/product.aspx?product_id=',preData.pid,'"><img src="',preData.image,'" border="0" \/></a>');
	sb.append('<h3><a class="blue_w" target="_blank" href="http://product.dangdang.com/product.aspx?product_id=',preData.pid,'">',preData.pname,'<\/a><\/h3>');
	sb.append('<p>市场价：',preData.price,'<\/p>');
	sb.append('<\/div>');
	sb.append('<\/div>');
	return sb.getString();

}




// stock

var isIE =!!(window.attachEvent && !window.opera);
function setDimension(obj,w,h)
{
	if(!obj) return;
	if(!isIE)
	{
		w+='px';
		h+='px';
	}
	obj.style.width=w;
	obj.style.height=h;
};

function shield()
{

	var shi = $("div_shield")
	if(shi){
		//        setDimension(shi,window.screen.width,document.body.clientHeight);
		setDimension(shi,document.body.clientWidth,document.body.clientHeight);
		shi.style.display="block";
	}

}

document.write("<iframe id=MsgBoxFrame1 name=MsgBoxFrame1  src='' frameborder=0 width='806px' scrolling=no style='display:none;position:absolute;z-index:90;'></iframe>");
function OpenAll2handWin(url)
{     
	var cf=document.getElementById("MsgBoxFrame1");			
	if(cf.style.display=="block"){cf.style.display="none";return;}		
	cf.src=url+'&t='+Math.random();
	cf.style.top=(document.documentElement.clientHeight-544)/2+document.documentElement.scrollTop+"px";		
	cf.style.left=(document.body.clientWidth-838)/2+"px";			
	cf.style.display="block";	
           
}
/*
function OpenAll2handWin2(url){
	var lin = $("tag_box");
	lin.innerHTML = all2handWin(url);
	lin.style.left = (document.body.clientWidth-838)/2+"px";
	lin.style.top = (document.documentElement.clientHeight-544)/2+document.documentElement.scrollTop+"px";
	lin.style.display = 'block';
}

function all2handWin(url){
	var sb = new StringBuilder('<div class="wind_city">');
         sb.append('<div class="cont_wind">');
	sb.append('<a href="#" onclick="javascript:hidden(\'tag_box\');return false;"><img src="images/window_close02.gif" title="关闭" /></a>');
	sb.append('<div class="city_cont">');
	sb.append('<iframe id="all2handWin" name="all2handWin" width=806 height=544 FRAMEBORDER=0 SCROLLING=NO src="'+url+'&t='+Math.random()+'"></iframe>');
	sb.append('</div>');
	sb.append('</div>');
	return sb.getString();
}
*/


function OpenStockWin(Id,parameters,mall,title){
	var lin =  $("tag_box");
	var send_data = "Dtype=stock" + "&Par="+parameters+"&mall="+mall+"&t="+Math.random();
	var result = XMLHttp._sendRequest('POST', "callback.php", send_data, false);
	if(title){
		lin.innerHTML = stockWinMall(result,title);
		PosHelper.SetOffset(Id,lin,-1,1);
	}else{
		lin.innerHTML = stockWin(result);
		PosHelper.SetOffset(Id,lin,0,20);
	}
}

function stockWin(content){
	var sb = new StringBuilder('<div class="location_over_main2">');
	sb.append('<p>此商品目前可送至以下地区：</p>');
	sb.append(content);
	sb.append('<div class="clear"></div>');
	sb.append('</div>');
	return sb.getString();
}

function stockWinMall(content,title){
	var sb = new StringBuilder('<div class="location_over">');
	sb.append('<div class="location_over_title" onMouseOut="javascript:hidden(\'tag_box\');"><a href="#">'+title+'</a></div>');
	sb.append('<div class="location_over_main" onMouseOut="javascript:hidden(\'tag_box\');">');
	sb.append('<p>送货范围覆盖以下地区：</p>');
	sb.append(content);
	sb.append('<div class="clear"></div>');
	sb.append('</div>');
	sb.append('</div>');
	return sb.getString();
}

try {
	(1).toFixed(1);
}
catch(e) {
	Number.prototype.toFixed = function(dot) {
		with(Math){
			var m=pow(10,Number(dot))
			var s = (round(this*m)/m).toString();
			}
		if(s.indexOf('.') < 0)
			s += ".";
		s += "00000000000000000000000000";
		return s.substr(0,s.indexOf('.')+dot+1);
	}
}

function getPrdData(pid)
{

	if ( typeof probJson != 'undefined' )
	{

		var len = probJson.prod.length;

		for(var i=0 ;i < len ;i ++)
		{
			if(probJson.prod[i].pid == pid) return probJson.prod[i];
		}

	}
}


function checkall()
{
	if ( typeof probJson != 'undefined' )
	{
		//20090615修改，要求默认选择最前面的商品组合
		var len = probJson.prod.length;
		for(var i=1 ;i < len ;i ++)
		{
			if(probJson.prod[i])
			{
				var id = probJson.prod[i].pid;
				var ock = $("ck"+id);
				if(ock && i!=1)
				{
					ock.checked = false;
				}
				else
				{
					ock.checked = true;
				}
			}
		}
	}
}

function addprd(obj,prdid)
{

	$("packMess").style.display="none";

	var prdData = getPrdData(prdid);
	var pobj =$("pr"+prdid);
	var cobj =$("ck"+prdid);

	if(cobj.checked)
	{
		pobj.className = "group_product_b";

	}
	else
	{
		pobj.className = "group_product_c";
	}

	rcaltPrice(prdid);

}


function rcaltPrice(prdid)
{

	var count =1;
	var sumPrice = probJson.prod[0].price;
	;
	var osumPrice = probJson.prod[0].oprice;
	;

	var len = probJson.prod.length;
	for(var i=0 ;i < len ;i ++)
	{
		if(probJson.prod[i])
		{
			var id = probJson.prod[i].pid;
			if($("ck"+id) &&$("ck"+id).checked)
			{
				sumPrice += probJson.prod[i].price;
				osumPrice += probJson.prod[i].oprice;
				count++;
			}
		}
	}



	if(count <=1){
		PosHelper.SetOffset("ck"+prdid,"packMess",0,47);
		$("ck"+prdid).checked = true;
		$("pr"+prdid).className = "group_product_b";
		return ;
	}

	$("packCount").innerHTML = count -1;

	var priceobj = $("sumpriceDiv");
	var opriceobj = $("sumopriceDiv");

	if(priceobj)priceobj.innerHTML = "￥" + sumPrice.toFixed(2);
	if(opriceobj)opriceobj.innerHTML = "￥" + osumPrice.toFixed(2);




}


function AddPackage()
{

	var ischeck = false;
	var product_id =  probJson.prod[0].pid;
	var len = probJson.prod.length;
	for(var i=0 ;i < len ;i ++)
	{
		if(probJson.prod[i])
		{
			var id = probJson.prod[i].pid;
			if($("ck"+id)&&$("ck"+id).checked)
			{
				product_id += "," + id;
				ischeck = true;
			}
		}
	}


	if(!ischeck) {
		$("packMess").style.display="block";
		return ;
	}

	var url=null;
	if(product_id==null || product_id<1)
		url="http://shopping.dangdang.com/shoppingcart/shopping_cart.aspx";
	else
		url="http://shopping.dangdang.com/shoppingcart/shopping_cart_add.aspx?product_ids="+product_id+"&reference_url="+escape(window.location.href);

	var popup=window.open(url,"shoppingcart");
	popup.focus()
}

//限时抢购
function view_time(){
	//var nowtime = now.getYear()+'-'+(now.getMonth()+1)+"-"+now.getDate()+" "+now.getHours()+":"+now.getMinutes()+":"+now.getSeconds();
	//var daynum=(parseInt(basetime.match(/-(\d+)\s/)[1])>=parseInt(nowtime.match(/-(\d+)\s/)[1]))?(parseInt(basetime.match(/-(\d+)\s/)[1])-parseInt(nowtime.match(/-(\d+)\s/)[1])):0;
	//var now_s = parseInt(nowtime.match(/\s(\d+)\D/)[1]*3600)+parseInt(nowtime.split(":")[1]*60)+parseInt(nowtime.split(":")[2]);
	//var base_s = daynum*24*3600+parseInt(basetime.match(/\s(\d+)\D/)[1]*3600)+parseInt(basetime.split(":")[1]*60)+parseInt(basetime.split(":")[2]);
	//var the_s = base_s-now_s;
	if(the_s>=0){
		var the_D=Math.floor((the_s/3600)/24)
		var the_H=Math.floor((the_s-the_D*24*3600)/3600);
		var the_M=Math.floor((the_s-the_D*24*3600-the_H*3600)/60);
		var the_S=(the_s-the_H*3600)%60;
		html = "还剩 ";
		if(the_D!=0) html += the_D+"天";
		if(the_D!=0 || the_H!=0) html += the_H+"小时";
		if(the_D!=0 || the_H!=0 || the_M!=0) html += the_M+"分";
		html += the_S+"秒 结束";
		$("limit_time").innerHTML = html;
		the_s--;
	}else{
		$("limit_time").innerHTML = "已结束";
		clearInterval(limit_time);
	}
}

//购买数量判断
function checkBuyNum(obj){
	if(isNaN(obj.value) || obj.value<=0){
		$("buy_num_text").style.display = "";
		return false;
	}else{
		$("buy_num_text").style.display = "none";
		return true;
	}
}