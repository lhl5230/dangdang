document.write("<link href='http://tuijian.dangdang.com/css/browse_frame2.css' rel='stylesheet' type='text/css' />");
try  {  document.domain='dangdang.com';  }
catch(err) {} 
var POST_URL="http://tuijian.dangdang.com/Recomend.ashx";
function loadRecomend(agentHandler,o,ps,encode,param)
{
     if(!ps) ps="10";
     if(!encode) encode="gb2312";
     if(!param) param="";
	 var http=null;
	 if(window.XMLHttpRequest) // Mozilla
	 { 
		http=new XMLHttpRequest();
	  	if(http.overrideMimeType)
			http.overrideMimeType("text/html;");
	  }
	  else if(window.ActiveXObject) // IE
	  {
		try{
			http=new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch(e)
		{
			try{
				http=new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e){}
		}
	}
	if(!http) return false;
	http.onreadystatechange = function(){
		if(http.readyState==4 && http.status==200)
           setHTML(http.responseText,o,param);
	}	
	
	var pids=getCookie("producthistoryid");
	http.open("POST", agentHandler, true);
    http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8"); 
    try
    {
        http.send("request_url="+POST_URL+"&pagesize="+ps+"&pids="+pids+"&encode="+encode);
    } catch (e){}
}

var p_url="http://product.dangdang.com/product.aspx?product_id=";
var html_top='<div class="browse_comm"><h2>根据浏览历史为您推荐</h2><ul class="browse_ul">';
var html_item='<li><div class="book_pic_x"><a id="recomend_browse_p_img"" href="$P_URL$" target="_blank" ><img src="$P_IMG_URL$" title="$P_TITLE$"/></a></div><h3><a id="recomend_browse_p" href="$P_URL$" target="_blank">$P_NAME$</a></h3><p class="price_old">￥$P_H_PRICE$</p><p class="price_d">￥$P_L_PRICE$</p></li>';
var html_bottom='</ul><div class="browse_b"></div></div>';
function setHTML(text,o,param)
{
    if(text=="") return;
    var o=document.getElementById(o);   
    if(o!=null)
    {
        var json = parseJson(text);
        var html=html_top;
        if(json.P!=null){
            var j=0;
            for(var i=0;i<json.P.length;i++)
            {
                if(json.P[i].pid!=null && json.P[i].pid!="" && json.P[i].pid!="0")
                {
                    html+=html_item.replace("$P_IMG_URL$",json.P[i].thumbnail_image_url);
                    html=html.replace("$P_URL$", p_url+json.P[i].pid+param).replace("$P_URL$", p_url+json.P[i].pid+param);
                    html=html.replace("$P_TITLE$", json.P[i].pname);
                    if(strlength(json.P[i].pname)>30)
                        html=html.replace("$P_NAME$", substr(json.P[i].pname, 26)+'<span class="dot">...</span>');
                    else
                        html=html.replace("$P_NAME$", json.P[i].pname);
                    html=html.replace("$P_H_PRICE$", json.P[i].market_price).replace("$P_L_PRICE$", json.P[i].dd_price);
                    j++;
                }
            }
            html+=html_bottom;
            if(j==0) html="";
            o.innerHTML= html;
        }
    }
}

function getCookie(name) {
  var search;
  search = name + "="
  offset = document.cookie.indexOf(search) 
  if (offset != -1) {
    offset += search.length ;
    end = document.cookie.indexOf(";", offset) ;
    if (end == -1)
      end = document.cookie.length;
    return unescape(document.cookie.substring(offset, end));
  }
  else
    return "";
}

function strlength(str){
    return str.replace(/[^\x00-\xff]/gi,'oo').length;
}

function substr(str,len){
    var strlen=0;
    var s="";
    for(var k=0; k<str.length; k++)
    {
        if(str.charCodeAt(k)>128)
            strlen += 2;
        else
            strlen++;
        s += str.charAt(k);
        if(strlen >= len)
            return s;
    }
    return s;
}

function parseJson(jsonString){
    try 
    {
        jsonObj = eval('(' + jsonString + ')');
    } 
    catch (ex)
    {
        return null;
    }
    return jsonObj;
}
