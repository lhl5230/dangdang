var suggest_j=-1,tempArr=[],keytempArr=[],tempSize=10,tempLast,allwordsview='',suggest_div_width,search_input_id,sugTO,sugTO2,sugIsDown=false,sugX,sugY,tempSearchkey;


var sug_gid=function(node){
	return document.getElementById(node);
}
var sug_gname=function(node){
	return sug_gid("suggest_key").getElementsByTagName(node);
}

function Suggest_Initialize(id,width,x,y)
{
    search_input_id = id;
    if(sug_gid(search_input_id))
    {
       sug_gid(search_input_id).onkeyup =keyup;
       sug_gid(search_input_id).onkeydown = keydown;
       sug_gid(search_input_id).onpaste = onpaste_search;
       sug_gid(search_input_id).autocomplete = "off";
       document.onclick =hide_suggest;
       window.onresize=suggest_keywords_view;
       suggest_div_width = width;
       sugX=(x=="undefined"||x==""?0:x);
       sugY=(y=="undefined"||y==""?0:y);
    }
    Create_Suggest_Div();
}
function Create_Suggest_Div()
{
    document.write('<div id="suggest_key" class="suggest_key" style="position:absolute;left:0px;top:0px;z-index:10000;background-color:White;" ></div>');
}

function header_trim(str)
{
 return str.replace(/(\s*$)|(^\s*)/g, '');
}

function getkeyword()
{

    var objfrm=document.searchform;
    var selectguan="";
    if(objfrm.catalog.value!=""){
        if(objfrm.catalog.options[1].value>4000000){
            selectguan=objfrm.catalog.options[1].value;
        }else{
            selectguan="";
        }
    }

    

	if(sugTO2) clearTimeout(sugTO2);
	if(escape(sug_gid(search_input_id).value)==""){            
        hide_suggest();
    }
	else{
	    var tempI=tempCheck(sug_gid(search_input_id).value+objfrm.catalog.value);
	    if(tempI==-1){
            tempSearchkey=(sug_gid(search_input_id).value);
            var myurl="";
    if(objfrm.catalog.value==selectguan){
             myurl="http://schprompt.dangdang.com/suggest.php?"+"keyword="+(sug_gid(search_input_id).value)+"&catalog=" + "&guanid=" + selectguan + "&"+Math.random();
             }else{
                 if(selectguan=="")
                     if(objfrm.catalog.value>4000000)
                        myurl="http://schprompt.dangdang.com/suggest.php?"+"keyword="+(sug_gid(search_input_id).value)+"&catalog="+ "&guanid=" + objfrm.catalog.value +  "&"+Math.random();
                     else
                         myurl="http://schprompt.dangdang.com/suggest.php?"+"keyword="+(sug_gid(search_input_id).value)+"&catalog="+ objfrm.catalog.value +"&guanid=" +   "&"+Math.random();

                 else
                    myurl="http://schprompt.dangdang.com/suggest.php?"+"keyword="+(sug_gid(search_input_id).value)+"&catalog="+ objfrm.catalog.value + "&guanid=" +  "&"+Math.random();
             }
            invokeServer(myurl);
	        viewkeywords(tempI);
                sugIsDown=false;
            }
        else if(tempI==-2){            
             hide_suggest();
         }
        else if(tempLast!=sug_gid(search_input_id).value || sug_gid("suggest_key").innerHTML=="" ){
                tempSearchkey=unescape(keytempArr[tempI][1]);
                sug_gid("suggest_key").innerHTML=unescape(tempArr[tempI][1]);
	            viewkeywords(tempI);
                    sugIsDown=false;
        }
   }
   if(sugIsDown) {

        tempLast=sug_gid(search_input_id).value;
        sugTO2=window.setTimeout(getkeyword,3000);
   }
}

function tempFill(n,v){
    if(tempArr.length>=tempSize) tempArr.shift();
    tempArr.push([escape(n),escape(v)])
}
function keytempFill(n,v){
    if(keytempArr.length>=tempSize) keytempArr.shift();
    keytempArr.push([escape(n),escape(v)])
}
function tempCheck(n){
    for(var i=0;i<tempArr.length;i++) {
        var t0=unescape(tempArr[i][0]),t1=unescape(tempArr[i][1]);
        if(t0==n)
            return i;
        else if(t1=="" && n.indexOf(t0)==0 && n.length>t0.length)
            return -2;
    }
    return -1;
}

function invokeServer(url)
{
     var scriptOld=document.getElementById('temp_script');
     if(scriptOld!=null && document.all)
     {
        scriptOld.src = url;
        return;
     }
    var head=document.documentElement.firstChild,script=document.createElement('script');
    script.id='temp_script';
    script.type = 'text/javascript';
    script.src = url;
    if(scriptOld!=null)
       head.replaceChild(script,scriptOld);
    else
       head.appendChild(script);
}

function viewkeywords(shownum)
{
    var x=getposOffset_top(sug_gid(search_input_id),'left');
    var y=getposOffset_top(sug_gid(search_input_id),'top');
    var div_obj=sug_gid("suggest_key");
    if(isIE_c())
        div_obj.style.left=(x+1+sugX)+'px';
    else
        div_obj.style.left=(x+sugX)+'px';
        div_obj.style.top=(y+sugY)+'px';
        if(shownum>-1)
        div_obj.style.display="inline";
}

function suggest_keywords_view(){
     if(sug_gid("suggest_key").style.display=="none")return;
     viewkeywords(-1);
}

function set_style(num){

	for(var i=0;i<sug_gname("li").length;i++){
		var li_node=sug_gname("li")[i];
		li_node.className="";
	}
	if(suggest_j>-1 && suggest_j<=sug_gname("li").length){
		var i_node=sug_gname("li")[suggest_j];
		sug_gname("li")[suggest_j].className="select_key";
		//sug_gname("li")[suggest_j].style.width=suggest_div_width+"px";
		}
	}
function mo(nodevalue){
    	suggest_j=nodevalue;
	set_style(suggest_j);
}
function form_submit(){
	if(suggest_j>=0 && suggest_j<sug_gname("li").length){
	    sug_gid(search_input_id).value=sug_gname("li")[suggest_j].childNodes[1].nodeValue;
        }
	//searchsubmit();
	sug_gid('search_btn').click()
}

function goto_class(classstr,key){

	if(classstr>=4000000)
	{
		location.href="http://search.dangdang.com/mall/search_mall.php?q=" + key + "&cat="+classstr + "&ref=suggest-1-1";

	}
	if(classstr.indexOf("01")===0)
		location.href="http://search.dangdang.com/book/search_pub.php?key=" + key + "&catalog=" + classstr + "&SearchFromTop=1&ref=suggest-1-1";
	if(classstr.indexOf("03")===0)
		location.href="http://search.dangdang.com/music/search_pub.php?key=" + key + "&catalog=" + classstr + "&SearchFromTop=1&ref=suggest-1-1";
	if(classstr.indexOf("05")===0)
		location.href="http://search.dangdang.com/movie/search_pub.php?key=" + key + "&catalog=" + classstr + "&SearchFromTop=1&ref=suggest-1-1";

}

function goto_product(product_id){
         var obj = document.createElement('a');
     obj.href   = "http://product.dangdang.com/product.aspx?product_id="+product_id+"&ref=suggest-1-0";
     window.open(obj.href);
}
function hide_suggest(num){
	var nodes=document.body.childNodes
	for(var i=0;i<nodes.length;i++){
		if(nodes[i]!=sug_gid(search_input_id))
                    suggest_j=-1;
                    sug_gid("suggest_key").style.display="none";
                    //sug_gid("suggest_key").innerHTML="";
		}
	}



function keyup(e)
{
    	var keyc;

	if(window.event){
		keyc=event.keyCode;
		}
	else if(e.which){
		keyc=e.which;
		}
        if(keyc==27){hide_suggest();return;}
	if(keyc!=40&&keyc!=38){


	if(sugTO) clearTimeout(sugTO);
        if(escape(header_trim(sug_gid(search_input_id).value))!=""){
            sugTO = setTimeout(getkeyword,300);
            sugIsDown=true;
        }else{
            hide_suggest(2);
        }
        
	}
}

function onpaste_search(){

sugTO = setTimeout(getkeyword,300);
}
function keydown(e){
	var keyc;

	if(window.event){
		keyc=event.keyCode;
		}
	else if(e.which){
		keyc=e.which;
		}

	if(keyc==40 || keyc==38){
            if(sug_gid("suggest_key").style.display=="none")return;

        if(keyc==40){
		if(suggest_j<sug_gname("li").length){
			suggest_j++;
			if(suggest_j>=sug_gname("li").length) {suggest_j=-1;
                            sug_gid(search_input_id).value=tempSearchkey;
                        }

		}else{
                    suggest_j=0;
                }
		if(suggest_j>=sug_gname("li").length) suggest_j=-1;


	}
	if(keyc==38){

		if(suggest_j>=0){
			suggest_j--;
			if(suggest_j<=-1) {
                            suggest_j=sug_gname("li").length;
                            sug_gid(search_input_id).value=tempSearchkey;
                        }
		}
		else
			suggest_j=sug_gname("li").length-1;

	}
	set_style(suggest_j);
	if(suggest_j>=0 && suggest_j<sug_gname("li").length){
            if(sug_gname("li")[suggest_j].getAttribute("type")=="search")
		sug_gid(search_input_id).value=sug_gname("li")[suggest_j].childNodes[1].nodeValue;
        }
	else{
		//sug_gid(search_input_id).value=temp_str;
		}
	}
}
//



function getposOffset_top(what, offsettype)
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


function getCookie_one(name,type){
  var search;
  search = name + "=";
  var cookies=document.cookie.split(";")  ;

  if(type=="dangdang"){
    var dangdangcookie="";
     for(i=0;i<cookies.length;i++){
        if(cookies[i].indexOf("dangdang.com=")>-1){
            dangdangcookie=cookies[i].split("&");
            for(x=0;x<dangdangcookie.length;x++){
                if(dangdangcookie[x].indexOf(search)>-1){
                    return unescape(dangdangcookie[x].substring(search.length,dangdangcookie[x].length));
                }
            }
        }
     }

  }else{
       for(i=0;i<cookies.length;i++){
        if(cookies[i].indexOf("dangdang.com=")<0){
            if(cookies[i].indexOf(search)>-1){
                return unescape(cookies[i].substring(search.length+1,cookies[i].length));
            }
        }
      }
  }
  return "";
}

function initHeaderOperate()
{
    var usernick=getCookie_one("show_name","dangdang");
    var marks="";
    var chakanlk="";

    var validatedflag=getCookie_one("validatedflag","");
    if(validatedflag=="1")
        marks = "（<a name=\"ddnav_verifymail\" href=\"https://login.dangdang.com/VerifyEmail.aspx\">邮箱未验证</a>）";
    if (validatedflag == "2" || validatedflag == "3")
        marks = "（<a name=\"ddnav_verifymail\" href=\"https://login.dangdang.com/VerifyEmail.aspx\">邮箱未验证</a>）";

    if(usernick=="")
        xinshou = " [<a name=\"ddnav_login\" href=\"https://login.dangdang.com/Signin.aspx\" target=\"_self\">登录</a> | <a name=\"ddnav_register\" href=\"https://login.dangdang.com/Register.aspx\" target=\"_self\" >免费注册</a>]";
    else
    {
        chakanlk = " [<a name=\"ddnav_signout\" href=\"java"+"scr"+"ipt:PageTopSignOut();\" target=\"_self\">退出登录</a>]";
        xinshou = "";
        var cartItemsCount=getCookie_one("cart_items_count","");
        if(cartItemsCount!=null&&cartItemsCount.length>0)
        {
            var cic=document.getElementById("cart_items_count");
            if(cic!='')
                cic.innerHTML="("+cartItemsCount+")";
        }

    }

    if(usernick.length>10)
        usernick=usernick.substr(0,10)+"…";

    var nkname=document.getElementById("nickname");
    if(usernick!='')
        nkname.innerHTML='您好，'+usernick+'，'+marks+chakanlk;
    else
        nkname.innerHTML='欢迎光临当当网，'+xinshou;
}


function PageTopSignOut(){
    location.href="https://login.dangdang.com/SignOut.aspx?returnurl="+escape(location.href);
}

//关于div显示
function showwindownewtop(obj,objdiv,addx,addy){

	var x=getposOffset(obj,'left');
    var y=getposOffset(obj,'top');
    var div_obj=document.getElementById(objdiv);
		div_obj.style.left=(x+addx)+'px';
		div_obj.style.top=(y+addy)+'px';
		if(div_obj.style.display=="none"){
		div_obj.style.display="inline";}
		else{
			div_obj.style.display="none";
		}
	}

function SignOut(){
    var ifru=document.getElementById("usernameifr");
    ifru.src="http://login.dangdang.com/SignOut.aspx?returnurl=http://www.dangdang.com/customer/signout.asp";
   changeuser();
}

function changeuser(){
var nkname=parent.document.getElementById("nickname");
nkname.innerHTML='您好，欢迎光临当当网。<a href="http://reco.dangdang.com/">当当为您推荐！</a>[请<a href="http://login.dangdang.com/Signin.aspx" name="sign_in" target="_self" class="blue12a">登录</a>/<a href="http://login.dangdang.com/Register.aspx" target="_self" name="Register" class="blue12a">注册</a>]';
}



//--------------------header---------
//操作条菜单
function showgaoji(obj){
    var x=getposOffset_top(obj,'left');
    var y=getposOffset_top(obj,'top');
	var divotherChannel=document.getElementById("otherChannel");
	

    var frm=document.searchadvformnewbook;
    var d=new Date();
    var yearn=d.getFullYear();
	if(frm){
	if(frm.catalog.value.substring(0,2)=="01"){
		if(frm.sy.options.length<2&&frm.ey.options.length<2){
			for(i=2;i<7;i++){
				var option = new Option(yearn,yearn);
				frm.sy.options.add(option,i);
				var option1 = new Option(yearn,yearn);
				frm.ey.options.add(option1,i);
				yearn--;
			}
		}
	}
	}
	obj.className="mydd_hover";
	divotherChannel.style.left=(x-5)+'px';
	if(!isIE_c())
	    divotherChannel.style.top=(y+16)+'px';
	else
	    divotherChannel.style.top=(y+16)+'px';
	divotherChannel.style.display="inline";

}



function hideotherchannel(){
		var divotherChannel=document.getElementById("otherChannel");
	var mydd=document.getElementById("a_otherchannel");
    if(divotherChannel.style.display!="none"){
    divotherChannel.style.display="none";
		
		mydd.className="mydd";
		
    }
	}



//ff兼容
function isIE_c(){
   return window.navigator.userAgent.toLowerCase().indexOf("msie")>=1?true:false;
}
//通用窗口管理
function showwindow(obj,objdiv,addx,addy){
	var x=getposOffset_top(obj,'left');
    var y=getposOffset_top(obj,'top');    
    var div_obj=document.getElementById(objdiv);
		div_obj.style.left=(x+addx)+'px';
                if(addy==12)addy+=10;
		div_obj.style.top=(y+addy)+'px';
		div_obj.style.display="inline";
}

function Hidewindow(objdiv){
    var div_obj=document.getElementById(objdiv);
		if(div_obj){
			div_obj.style.display="none";
		}
}	

//搜索
function searchsubmit()
{
    var objfrm=document.searchform;
    if(sug_gname("li").length>0){
        for(sugnum=0;sugnum<sug_gname("li").length;sugnum++){            
            if(sug_gname("li")[sugnum].className=="select_key"&&sug_gname("li")[sugnum].getAttribute("type")!="search"){
                sug_gname("li")[sugnum].onclick();
                return false;
            }
        }
    }
    
    if(cnlength(objfrm.key.value)<2)
    {
        if(objfrm.key.value.length==0)
        {
	        if(objfrm.catalog.value>4000000 && objfrm.catalog.value<=4000008)
	        {

		        window.location="http://category.dangdang.com/";
		        return false;
	        }
	        else if(objfrm.catalog.value>4000008)
	        {

		        window.location="http://category.dangdang.com/list?cid="+objfrm.catalog.value;
		        return false;
	        }
	        else if(objfrm.catalog.value.indexOf("01")===0)
	        {
		        window.location="http://book.dangdang.com/";
		        return false;
	        }
	        else if(objfrm.catalog.value.indexOf("03")===0)
	        {
		        window.location="http://music.dangdang.com/";
		        return false;
	        }
	        else if(objfrm.catalog.value.indexOf("05")===0)
	        {
		        window.location="http://movie.dangdang.com/";
		        return false;
	        }
        }

        alert("搜索词过短，请重新填写！");
        objfrm.key.focus();
        return false;
    }
    else
    {
           if(cnlength(objfrm.key.value)>70)
           {
                alert(cnlength(objfrm.key.value)+"搜索词过长，请重新填写！");
                objfrm.key.focus();
                return false;
            }
     }
    //objfrm.key.value=escape(objfrm.key.value)
	if(objfrm.catalog.value=="readonline")
	{
		window.location="http://search.read.dangdang.com/search.php?"+objfrm.readSearchSelect.value+"="+objfrm.key.value;
		return false;
	}
	if(objfrm.catalog.value>=4000000)
	{
		window.location="http://search.dangdang.com/mall/search_mall.php?q="+objfrm.key.value+"&guan="+objfrm.catalog.value;
		return false;
	}
	if(objfrm.catalog.value.indexOf("01")===0)
		objfrm.action="http://search.dangdang.com/book/search_pub.php"
	if(objfrm.catalog.value.indexOf("03")===0)
		objfrm.action="http://search.dangdang.com/music/search_pub.php"
	if(objfrm.catalog.value.indexOf("05")===0)
		objfrm.action="http://search.dangdang.com/movie/search_pub.php"
	if(objfrm.catalog.value=="comm")
		objfrm.action="http://commu.dangdang.com/searchlist.php"
	objfrm.submit();
}

function cnlength(str){
    return str.replace(/[^\x00-\xff]/gi,'oo').length;
}

function AddToShoppingCart(product_id)

{

         var url=null;



         if(product_id==null || product_id<1)

                   url="http://shopping.dangdang.com/shoppingcart/shopping_cart.aspx";

         else

                  url="http://shopping.dangdang.com/shoppingcart/shopping_cart_add.aspx?product_ids="+product_id+"&reference_url="+escape(window.location.href);



         var popup=window.open(url,"shoppingcart");

         popup.focus()

}


function AddToFavorlist(product_id)

{

         var url="http://customer.dangdang.com/wishlist/cust_wish_add.aspx?productid="+product_id;

         var popup=window.open(url,"favorlist");

         popup.focus()

}



