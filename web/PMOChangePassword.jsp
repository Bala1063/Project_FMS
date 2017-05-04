<%-- 
    Document   : PMOChangePassword
    Created on : Apr 27, 2017, 9:09:56 PM
    Author     :PRP_FMS:- Aruna M, Aswini A, Balaji S K, Sushmitha S.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Report</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/PMO.css">
        <script src="js/custom.js"></script>
        <style type="text/css">
            .container-fluid{
                padding-left: 30px;
            }
        </style>
        <script>
            function check()
            {
                var x = document.getElementById("newpassword").value;
                var y = document.getElementById("confirmpassword").value;


                if (x !== y)
                {
                    window.alert("Confirmpassword is not Equal");
                    return false;
                }
                return true;
            }
            function checkStatus()
            { checksession();
                var x = document.getElementById("status").value;
                if (x != "")
                {
                    document.getElementById("status").value = "";
                    window.alert(x);
                }
            }
        </script>
    </head>
    <body onload="checkStatus()" id="hidebody">
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid" id=container-fluid>
                <div class="navbar-header">
                    <a class="navbar-brand">Welcome PMO</a>
                </div>
                <div class="navbar-nav navbar-center">
                    <p class="navbar-brand">PRP_Faculty Management System <p/>
                </div>
                <form action="LoginServlet" method="post" id="f1">
                    <ul class="nav navbar-nav navbar-right">
                        <li> <a href="javascript:{}" onclick="document.getElementById('f1').submit();"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </ul>
                    <input type="hidden" name="operation" value="logout" />
                </form> 
            </div>
        </nav>
        <nav class="navbar-inverse navbar-fixed-top down">
            <ul class="nav nav-tabs">
                <li ><a href="PMOHomePage.jsp" data-toggle="tab">Home</a></li>
                <li ><a href="PMOViewAllTrainers.jsp" data-toggle="tab">Trainers</a></li>
                <li ><a href="PMOViewTrainerActivities.jsp" data-toggle="tab">Trainer Activities</a></li>
                <li class="active"><a href="PMOChangePassword.jsp" data-toggle="tab">Change Password</a></li>
            </ul>
        </nav>
        <div class="container">
            <form class="form-horizontal center" action="LoginServlet" method="post">
                <div class="form-group">
                    <label for="newpassword" class="col-sm-2 control-label">New Password</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" id="newpassword" name="newpassword" placeholder="newpassword" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirmpassword" class="col-sm-2 control-label">Confirm Password</label>
                    <div class="col-sm-5">
                        <input type="password" class="form-control" id="confirmpassword" name="confirmpassword" placeholder="confirmpassword" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit"  value="submit"  class="btn btn-default" onclick="return check()" name="operation">Submit</button>
                    </div>
                </div>
                <% if (request.getAttribute("status") != null) {%>
                <input type="hidden" name="status" id="status" value="<%=(String) request.getAttribute("status")%>">
                <% request.setAttribute("status", null);
                } else {%>
                <input type="hidden" name="status" id="status" >
                <%}%>
            </form>
        </div>
    </body>
</html>
