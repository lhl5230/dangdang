try  {  document.domain='dangdang.com';  }
catch(err) {}

document.write('<link href="http://static.dangdang.com/css/LoginWin.css" rel="stylesheet" type="text/css" />');
document.write('<div style="clear:both"></div><div id="Divloginwin" style="display:none;position:absolute;z-index:1000;width:328px;"></div>');
//兼容旧程序代码-----------------
document.write('<div id="div_loading" style="position:absolute;z-index:1000;width:0px;height:0px;" class="commend"></div>');
//-------------------------------
var ifremHTML="<iframe id=MsgBoxFrame name=MsgBoxFrame  src='' scrolling=no  frameborder=0 scrolling=no allowtransparency=true></iframe>";


var public_objid="";
var public_posturl="";

String.prototype.trim=function(){return this.replace(/(\s*$)|(^\s*)/g, '');};
function $d(id){return document.getElementById(id);};
function $F(id){var o=$d(id);if(o==null) return null;return o.value.trim();};




function txt_keyDown(e)
{
    var ev = window.event || e;
    if(ev.keyCode == 13)
        sign_check_and_submit();
}

function sign_check_and_submit()
{
    if(!navigator.cookieEnabled)
    {
        $d('liDivErrorMessage').className='notice_li_show';
        divErrorMssage.innerHTML="<div class='notice'>请您打开浏览器的Cookie功能！</div>";
        return;
    }

    if($F('txtUsername') == '')
    {
        $d('liDivErrorMessage').className='notice_li_show';
        check_errorshow();
        $d('divErrorMssage').innerHTML="请输入您的Email地址或昵称！";
        $d('txtUsername').className="input_text_error";
        $d('txtUsername').focus();
        return;
    }

    if($F('txtPassword')=='')
    {
        $d('liDivErrorMessage').className='notice_li_show';
        check_errorshow();
        $d('divErrorMssage').innerHTML="请输入您的登录密码";
        $d('txtPassword').className="input_text_error";
        $d('txtPassword').focus();
        return;
    }
    if($d('txtVerifyCode')){
        txtVerifyCode_check(arguments);
    }
}

function sign_submit(){
    var url="https://login.dangdang.com/SigninScript.aspx?username="+$F('txtUsername')+"&password="+$F('txtPassword')+"&verifycode="+$F('txtVerifyCode')+"&objid="+public_objid+"&posturl="+public_posturl+"&t="+new Date().getTime();

    SubmitData(url);
}

function check_errorshow(){
    if($d('liTopSpace')){
        $d('liTopSpace').style.display="none";
    }

}

function echo_error_message(code)
{
    switch(code)
    {
        case 1:
            $d('divErrorMssage').innerHTML="您需要登录后才能继续此操作！";
            $d('liDivErrorMessage').className='notice_li_show';
            $d('txtVerifyCode').value='';
            check_errorshow();
            show_vcode();
            break;
        case 2:
            $d('divErrorMssage').innerHTML="您输入的登录信息有误，请重新填写。";
            $d('liDivErrorMessage').className='notice_li_show';
            $d('txtPassword').className="input_text_error";
            $d('txtUsername').className="input_text_error";
            $d('txtVerifyCode').value='';
            check_errorshow();
            show_vcode();
            break;
        case 3:
            $d('divErrorMssage').innerHTML="<p class='p_space'>验证码过期，请重新输入。";
            $d('liDivErrorMessage').className='notice_li_show';
            $d('txtVerifyCode').className="input_text_error";
            $d('txtVerifyCode').value='';
            check_errorshow();
            show_vcode();
        case 4:
            $d('divErrorMssage').innerHTML="<span class='red'>您输入的验证码不正确，请重新输入。</span>";
            $d('liDivErrorMessage').className='notice_li_show';
            $d('txtVerifyCode').className="input_text_error";
            $d('txtVerifyCode').value='';
            $d('txtVerifyCode').focus();
            $d('txtVerifyCode').select();
            check_errorshow();
         default:
            break;
    }
}

function create_new_customer()
{
    window.parent.location.href = 'https://login.dangdang.com/Register.aspx?ReturnUrl='+window.parent.location.href
}

function execute_server_message(code,url)
{
    switch(code)
    {
        case 1:
            HideFramInLoginPage();
            break;
        case 2:
            ShowFrame(url);
            break;
        default:
            break;

    }


}


function txtVerifyCode_check(arg)
{
    var vcode = $F('txtVerifyCode');




    if(vcode=='' || !/\w{4}/.test(vcode))
    {
        check_errorshow();
        $d('liDivErrorMessage').className='notice_li_show';
        $d('divErrorMssage').innerHTML="<span class='red'>您输入的验证码不正确，请重新输入。</span>";
        $d('txtVerifyCode').className="input_text_error";
        return false;
    }

    var url="https://login.dangdang.com/p/vcode_checkscript.aspx?vcode="+vcode+"&t="+new Date().getTime();
    CheckData(url);



}

function show_vcode()
{
    $d('imgVcode').src='https://login.dangdang.com/p/show_vcode.aspx?t='+new Date().getTime();
}

function hideLoginWindow()
{
    var mb=$d("Divloginwin");
    if(mb)
    {
        mb.style.display="none";

    }
}


function hideMsgBox()
{
    hideLoginWindow();
}

function BodyInit()
{
 resizeMsgBox();
}

//调整大小
function resizeMsgBox()
{

      var wp=window.parent;

      var cf=wp.document.getElementById("MsgBoxFrame");

			var documentframe=null;

      cf.style.height=null;

      cf.style.width=null;

      if(IsIframeIE()){
      	documentframe=window.frames['MsgBoxFrame'].document;
      }else{
      	for(i=0;i<window.frames.length;i++){

		      		if(window.frames[i].name=='MsgBoxFrame'){
		      		documentframe=window.frames[i].document;
		      	}
      		}
      }


      cf.style.width= documentframe.documentElement.scrollWidth+20+"px";

      if(documentframe.body.scrollHeight==0)

          cf.style.height=documentframe.documentElement.scrollWidth+20+"px";

      else

          cf.style.height=documentframe.documentElement.scrollHeight+20+"px";
}

function ShowLoginWindow(obj){

    var Html='<div class="login_window" id="LoginWindow">'+
'    <div class="win_frame">'+
'      <div class="win_title"><a href="javascript:hideLoginWindow();"><img src="http://images.ddimg.cn/login/win_close.gif" title="关闭窗口" /></a><h2>请先登录</h2></div>'+
'         <ul class="win_cont_ul">'+
'         <li class="top_li" id="liTopSpace"></li>'+
'            <li class="notice_li" id="liDivErrorMessage">'+
'               <div class="notice" id="divErrorMssage"></div>'+
'               <div class="empty_right"></div>'+
'            </li>'+
'            <li><span class="span_li"><span class="span_n"><span class="text_f">Email</span>地址或昵称：</span><input ID="txtUsername" MaxLength="40" Class ="input_text" onfocus="this.className=\'input_text input_text_over\'" onblur="this.className=\'input_text\'" type="text"></span></li>'+
'            <li><span class="span_li"><span class="span_n">密码：</span><input ID="txtPassword" MaxLength="32" Class ="input_text" onfocus="this.className=\'input_text input_text_over\'" onblur="this.className=\'input_text\'" type="Password" type="text">'+
'            </span></li>'+
'            <li id="inputcode"><span class="span_li"><span class="span_n">验证码：</span><input id="txtVerifyCode" Class="input_text" maxlength="4" type="text" onkeydown="txt_keyDown(event)" onfocus="this.className=\'input_text input_text_over\'" onblur="this.className=\'input_text\'">'+
'              </span></li>'+
'            <li class="validate" id="imgcode"><span class="span_li"><img id="imgVcode" title="验证码"/><span class="span_n"><a href="javascript:show_vcode();">换张图</a></span>'+
'            </span></li>'+
'            <li><span class="span_li">'+
'            <input class="butt_login" onclick ="sign_check_and_submit(\'' + public_objid + '\',\'' + public_posturl + '\')" onmouseover="this.className=\'butt_login butt_login_over\'" onmouseout="this.className=\'butt_login\'" type="button" value=" " />'+
'            <span class="span_n"><a href="https://login.dangdang.com/lostpassword.aspx" target="_blank">忘记密码？</a></span>'+
'            </span></li>'+
'          <div class="login_new"><strong>还不是当当网用户？</strong>　<a href="javascript:create_new_customer()">快速注册>></a> </div>'+
'          </ul>'+
'    </div>'+
'    <div class="win_shadow_b"></div>'+
'</div>';
    $d('Divloginwin').innerHTML=Html;
    show_vcode();
    var x=getposOffset_loginwindow($d(obj),'left');
    var y=getposOffset_loginwindow($d(obj),'top');

    if(document.documentElement.clientWidth-x>328){
        $d("Divloginwin").style.left=x+"px";
    }else{
        $d("Divloginwin").style.left=document.documentElement.clientWidth-328+"px";
    }
    $d("Divloginwin").style.display="block";
    if(IsIframeIE()){
    $d("Divloginwin").style.top=y+20+"px";
    }
    else{
    $d("Divloginwin").style.top=y+15+"px";
    }
}


function showMsgBox(Id,parameters,TransferPath){
    $d("div_loading").style.display="block";
    var cf=$d("Divloginwin");
    var numargs = arguments.length;
    if(numargs > 3)
    {
        cf.callback = arguments[3];
        cf.callbackid = arguments[4];
        cf.callbackparameters = arguments[5];
    }
    public_objid=Id;
    if(TransferPath!=''){
        public_posturl=TransferPath+"?par="+parameters;
    }else{
        public_posturl='';
    }
    var url="https://login.dangdang.com/CheckLogin.aspx?objid="+public_objid+"&time="+new Date().getTime()+"&posturl="+public_posturl;
    CheckData(url);
}



function Completeifrem(obj,gotourl){
    $d("Divloginwin").innerHTML=ifremHTML;
    $d("Divloginwin").style.display="block";


    var x=getposOffset_loginwindow($d(obj),'left');
    var y=getposOffset_loginwindow($d(obj),'top');

    if(document.documentElement.clientWidth-x>328){
        $d("Divloginwin").style.left=x+"px";
    }else{
        $d("Divloginwin").style.left=document.documentElement.clientWidth-328+"px";
    }
    $d("Divloginwin").style.display="block";
    if(IsIframeIE()){
    $d("Divloginwin").style.top=y+20+"px";
    }
    else{
    $d("Divloginwin").style.top=y+15+"px";
    }
    if(gotourl==''){
        HideFramInLoginPage();
    }else{
        if($d("MsgBoxFrame")){
            $d("MsgBoxFrame").src=gotourl;
        }
    }
}

function HideFramInLoginPage()
{
    var mb= $d("Divloginwin");
    if(mb)
    {

        mb.style.display="none";
        mb.src="";
        document.onclick=null;

         if ('function' == typeof mb.callback)
          {
          mb.callback(mb.callbackid,mb.callbackparameters);
           }

    }
}


function getposOffset_loginwindow(what, offsettype)
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




function IsIframeIE()
{
    return ( navigator.appName=="Microsoft Internet Explorer" );
}

function IsNav()
{
	return ( navigator.appName=="Netscape" );
}

function CheckData(url){
    var scriptOld=document.getElementById('temp_script');
    if(scriptOld!=null && document.all)
     {
        scriptOld.src = url;
        return;
     }
    var head=document.documentElement.firstChild
    var script=document.createElement('script');
    script.id='temp_script';
    script.type = 'text/javascript';
    script.src = url;
    if(scriptOld!=null)
       head.replaceChild(script,scriptOld);
    else
       head.appendChild(script);
}

function SubmitData(url){
    var scriptOld=document.getElementById('Submit_script');
    if(scriptOld!=null && document.all)
     {
        scriptOld.src = url;
        return;
     }
    var head=document.documentElement.firstChild
    var script=document.createElement('script');
    script.id='temp_script';
    script.type = 'text/javascript';
    script.src = url;
    if(scriptOld!=null)
       head.replaceChild(script,scriptOld);
    else
       head.appendChild(script);
}
