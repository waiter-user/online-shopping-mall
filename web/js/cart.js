/* 
 *  此文件封装了购物车页面上对购物车操作的js函数
 * */  
//修改商品数量的函数
	        function modifyNum(goodsId,quantity)
	        {
	            var r1= /^[0-9]*[1-9][0-9]*$/;//正整数 
	            var val=r1.test(quantity); //str为你要判断的字符 执行返回结果 true 或 false
	            if(val==false)
	            {
	                alert("数量必须是正数,请重新输入");
	            }
	            else
	            {
	                 //发请求给后台Servlet,修改该商品所在的购物项的数量
	                 location.href=webPath+"/goods.action?method=updateCartItem&goodsId="+goodsId+"&quantity="+quantity;
	            }
	        }
	        
	     
	        
	        //删除购物项
	        function deleteCartItem(goodsId)
	        {
	            var flag= confirm("真的要删除此购物项吗？");
	             if(flag){
	            	  //发请求给后台Servlet,删除购物项	 
	                 location.href=webPath+"/goods.action?method=deleteCartItem&goodsId="+goodsId; 
	             }
	        }
	         //清空购物车
	        function clearCart()
	        {
	        	 var flag= confirm("真的要清空购物车吗？");
	        	  if(flag){
	            	  //发请求给后台Servlet,清空购物车	 
	                 location.href=webPath+"/goods.action?method=clearCart"; 
	             }
	        }