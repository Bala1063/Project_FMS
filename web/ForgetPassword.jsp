<%-- 
    Document   : ForgetPassword
    Created on : Apr 27, 2017, 9:52:25 PM
    Author     : bala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Forget Password</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/Login.css">
        <script src="js/bootstrap.min.js"></script>
        <script>
            var req; var otp;
            function get()
            {
                if (window.XMLHttpRequest) {
                    return  new XMLHttpRequest( );
                } else if (window.ActiveXObject)
                {
                    return new ActiveXObject("Microsoft.XMLHTTP");
                }
            }
             function getOTP()
            {
                checkStatus();
                req = get();
                var url = "TrainerServlet?operation=getOTP";
                req.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        otp=this.responseText;
                    }
                };
                req.open("POST", url, true);
                req.send(null);
            }
             function checkmail(mail)
            {
                checkStatus();
                req = get();
                var url = "TrainerServlet?operation=checkmail&&mail="+mail;
                req.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                       var result=this.responseText;
                       if(result==false)
                       {
                           document.getElementById('mailid').value="";
                           alert("Please Enter Valid Mail Id!");
                       }
                    }
                };
                req.open("POST", url, true);
                req.send(null);
            }
            function change()
            {
                document.getElementById('sendotp').innerHTML = "Re-Send OTP";
            }
        </script>
    </head>
    <body class="bg-color">
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
                            <div class=" panel-body">
                                <div class="form-group input-group space">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span> 
                                    <input class="col form-control" type="email" id="mailid" name='mailid' placeholder="Enter registered mail" onchange="check(this.vlaue)"/>
                                </div>
                                <div class="form-group space">
                                    <button type="button"  id="sendotp" onclick="change()" class="btn btn-def btn-block">Send OTP 
                                    </button>
                                </div>
                                <div class="form-group input-group space">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
                                    <input class="col form-control" type="text" name='otp' placeholder="Enter OTP"/>
                                </div>
                                <div class="form-group space">
                                    <button type="submit" value="getpassword" name="operation" class="btn btn-def btn-block">Get Password 
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
