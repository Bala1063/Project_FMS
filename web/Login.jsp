<%-- 
    Document   : Login.jsp
    Created on : May 2, 2017, 11:37:39 AM
    Author     : bala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/Login.css">
        <script src="js/bootstrap.min.js"></script>
        <script>
            function checkStatus()
            {
                var x = document.getElementById("status").value;
                if (x != "")
                {
                    document.getElementById("status").value = "";
                    window.alert(x);
                }
            }
        </script>
    </head>
    <body class="bg-color" onload="checkStatus()">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <ul class="nav navbar-nav navbar-center">
                        <li class="navbar-brand">PRP Faculty Management System</li>
                    </ul>
                </div> 
            </div>
        </nav>
        <div class="container">
            <div class="row">
                <div class="Absolute-Center is-Responsive">
                    <div id="logo-container">
                    </div>
                    <form action="LoginServlet" method="post">
                        <div class="panel panel-default">
                            <div class="panel-heading"><div class="col-sm-offset-3"><h3>PRP_FMS</h3></div></div>
                            <div class="panel-body">
                                <div class="form-group input-group space">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
                                    <input class="col form-control" type="text" name='username' placeholder="username"/>
                                </div>
                                <div class="form-group input-group space">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
                                    <input class="form-control" type="password" name='password' placeholder="password"/>
                                </div>   
                                <%if (request.getAttribute("status") != null) {%>
                                <input type="hidden" name="status" id="status" value="<%=(String) request.getAttribute("status")%>"/>
                                <%request.setAttribute("status", null);
                                } else {%>
                                <input type="hidden" name="status" id="status" />
                                <%}%>
                                <div class="form-group space">
                                    <button type="submit" value="login" name="operation" class="btn btn-def btn-block"> Login
                                    </button>
                                </div>

                                <div class="form-group col-sm-offset-4">
                                    <a href="ForgetPassword.jsp">Forget Password?</a>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
