function AddToShoppingCart(product_id)
{
    var url=null;

    if(product_id==null || product_id<1)
        url="http://shopping.dangdang.com/shoppingcart/shopping_cart.aspx";
    else{
        if($("buy_num")!=null){
            if(checkBuyNum($("buy_num"))){
                url="http://shopping.dangdang.com/shoppingcart/shopping_cart_add.aspx?product_ids="+product_id+"."+parseInt($("buy_num").value.replace(/(\s*$)|(^\s*)/g, ''))+"&reference_url="+escape(window.location.href);
            }else{
                return;
            }
        }else{
            url="http://shopping.dangdang.com/shoppingcart/shopping_cart_add.aspx?product_ids="+product_id+"&reference_url="+escape(window.location.href);
        }
    }
    var popup=window.open(url,"shoppingcart");
    popup.focus()
}

//var Cart = function()
//{

//    var a ="http://shopping.dangdang.com/shoppingcart/";
//	var b = a+"shopping_cart_add.aspx?opt=pls&product_ids=";
//	var c = a+"shopping_cart.aspx";
//	var d = function(m){return document.getElementById(m);};
//	var e = function(n){return document.createElement(n);};
//	var f='ZxuGPh';
//	var g = function(){return Math.random();};
//	var k = function(h,s,o){h.replaceChild(s,o);};
//	var l = function(p,q){p.appendChild(q);};
//	var j = function(){return document.documentElement.firstChild;};
//	var i = function(u){var v = e('sc'+'ript');v.id=f;v.type = 'text/javasc'+'ript';v.src = u+'&r='+g();return v;};


//    this.show=function(){var w=window.open(c,"shoppingcart");w.focus();};
//    this.addItem=function(y){var o = d(f),h = j(),s = i(b+y);(o == null)?l(h,s):k(h,s,o);};
//
//
//
//    };

//var cart = new Cart();
//function AddToShoppingCart(product_id)
//{
//    cart.addItem(product_id);
//};

//function onSucceed(result)
//{
//    if(result!=null && result.errorCode==0)
//    {
//        cart.show();
//    }
//    else if(result.errorCode==5)
//    {
//         window.showModalDialog("http://shopping.dangdang.com/shoppingcart/limit_buy.htm",this,"dialogWidth=300px;dialogHeight=180px;");
//    }
//    else
//    {
//       window.showModalDialog("http://shopping.dangdang.com/shoppingcart/noStock.html",this,"dialogWidth=300px;dialogHeight=180px;");
//    }
//};


