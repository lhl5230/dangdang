function rand()
{
	return Math.ceil(100000+Math.random()*900000);
}
function uid()
{
	var n = new Date();
	var y=n.getFullYear()+'';
	var m=n.getMonth()+1;
	if(m<10)
		m="0"+m;
	var d=n.getDate();
	if(d<10)
		d="0"+d;
	var H=n.getHours();
	if(H<10)
		H="0"+H;

	var M=n.getMinutes();
	if(M<10)
		M="0"+M;

	var S=n.getSeconds();
	if(S<10)
		S="0"+S;

	var MS = "00"+n.getMilliseconds();
	MS=MS.substr(MS.length-3,3);

	return y+m+d+H+M+S+MS+rand()+rand()+rand();
}


var strCookie=document.cookie;
if(strCookie.indexOf('__trace_id')<0)
{
    var expires=new Date();
    expires.setMinutes(expires.getMinutes()+30);
    document.cookie='__trace_id='+uid()+';expires='+expires.toUTCString()+';domain=dangdang.com;path=/';
}

if(strCookie.indexOf('__new_p_id')<0){
    var expires=new Date(2020,1,1);
    document.cookie='__permanent_id='+uid()+';expires='+expires.toUTCString()+';domain=dangdang.com;path=/';
    document.cookie='__new_p_id=1;expires='+expires.toUTCString()+';domain=dangdang.com;path=/';
}



