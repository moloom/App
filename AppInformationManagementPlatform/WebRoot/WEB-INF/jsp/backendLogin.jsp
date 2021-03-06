<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台管理系统</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
 
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/statics/css/bootstraps/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath}/statics/css/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="https://colorlib.com/polygon/gentelella/css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath}/statics/css/custom.min.css" rel="stylesheet">
  </head>

  <body class="login">
    	<!-- 用户登录时的表单 -->
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form 
            action="${pageContext.request.contextPath }/manager/submit" 
            name="actionForm" id="actionForm"  method="post">
              <h1>后台管理系统</h1>
              <div>
              
                <input type="text" class="form-control" name="userCode" id="userCode" placeholder="用户名" required="" />
              </div>
              <div>
                <input type="password" class="form-control" name="userPassword" id="userPassword" placeholder="密码" required="" />
              </div>
              <span>${error }</span>
              <div>
               <input class="btn btn-default submit" type="submit"  value="登录"/>
                    <input class="btn btn-default submit" type="reset" value="重置"/> 
                
               <!--  <a class="reset_pass" href="#">忘记密码啦?</a> -->
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">新网站?
                  <a href="#signup" class="to_register"> 注册账号 </a>
                </p>

                <div class="clearfix"></div>
                <br />

               
              </div>
            </form>
          </section>
        </div>
		
		<!-- 注册账号时的表单 -->
        <!-- <div id="register" class="animate form registration_form">
          <section class="login_content">
            <form>
              <h1>注册账号</h1>
              <div>
                <input type="text" class="form-control" placeholder="Username" required="" />
              </div>
              <div>
                <input type="email" class="form-control" placeholder="Email" required="" />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" required="" />
              </div>
              <div>
                <a class="btn btn-default submit" href="index.html">Submit</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">Already a member ?
                  <a href="#signin" class="to_register"> Log in </a>
                </p>

                <div class="clearfix"></div>
                <br />
              </div>
            </form> -->
          </section>
        </div>
      </div>
    </div>
  </body>
</html>
