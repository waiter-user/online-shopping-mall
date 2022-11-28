String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


	<script  src="${pageContext.request.contextPath}/js/public.js" type="text/javascript"></script>
		<script  src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	        function reg()
	        {
	                var url="${pageContext.request.contextPath}/qiantai/userinfo/userReg.jsp";
	                window.open(url,"_blank");
	        }
	         //点登录按钮后调用login函数
	        function login()
	        {
	           //验证用户名是否为空，为空就return
	            if($('#userName').val()==''){
	            	alert("用户名不能为空");
	            	return;
	            }
	            //验证密码是否为空，为空就return
	            if($('#userPw').val()==''){
	            	alert("密码不能为空");
	            	return;
	            }
	            //提交表单
	            $('#userLogin').submit();
	        }
	         //安全退出
	         function logOut(){
	        	      //弹出一个是否确认退出的对话框
	        	  var flag=    confirm("是否真的要退出");
	        	     if(flag){
	        	    	  //请求后台logout.action，实现安全退出 
	        	    	  location.href="${pageContext.request.contextPath}/logout.action";
	        	     }
	         }
	     
	</script>
        <c:choose>
             <c:when test="${empty user }">
                  	<form action="${pageContext.request.contextPath}/userLogin.action" name="userLogin" id="userLogin" method="post">
			      <table cellspacing="0" cellpadding="0" width="98%" align="center" border="0">
			          <tr>
			            <td align="center" colspan="2" height="10"></td>
			          </tr>
			          <tr>
			            <td align="right" width="30%" height="30" style="font-size: 11px;">用户名：</td>
			            <td align="left" width="69%"><input class="input" id="userName" title="用户名不能为空" size="16" name="userName" type="text" /></td>
			          </tr>
			          <tr>
			            <td align="right" height="30" style="font-size: 11px;">密　码：</td>
			            <td align="left"><input class="input" title="密码不能为空" type="password" size="16" id="userPw" name="userPw"/></td>
			          </tr>
			          <tr>
			            <td align="center" colspan="2" height="10"><font color="red"><s:property value="#request.error"/></font></td>
			          </tr>
			          <tr>
			            <td align="center" colspan="2" height="30">
			               
			               <input type="button" value="登  录" onclick="login()" style="border:#ccc 1px solid; background-color:#FFFFFF; font-size:12px; padding-top:3px;" />
						   &nbsp;
						   <input type="button" value="注  册" onclick="reg()" style="border:#ccc 1px solid; background-color:#FFFFFF; font-size:12px; padding-top:3px;" />
						   <!-- 回显错误信息 -->  
						    <span style="color:red;font-size:13px">${errorMsg }</span>
			            </td>
			          </tr>
			      </table>
		    </form>
             </c:when>
             <c:otherwise>
                     欢迎您：${user.user_name } &nbsp;&nbsp;&nbsp;&nbsp;
			    <a href="javascript:void(0)" onclick="logOut()">安全退出</a> &nbsp;&nbsp;&nbsp;&nbsp;
			    <br/><br/><br/>
             </c:otherwise>
        </c:choose>
     
			
		