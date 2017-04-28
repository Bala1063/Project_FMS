<%-- 
    Document   : TrainerLeave
    Created on : Apr 10, 2017, 7:22:41 PM
    Author     : bala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><title>
            Trainer Leave
        </title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-theme.min.css" rel="stylesheet">
        <link href="css/trainer.css" rel="stylesheet">
        <link href="css/admin.css" rel="stylesheet">
        <script src="jquery/jquery.min.js"></script>
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
    <body onload="checkStatus()">
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <p class="navbar-brand">Welcome! Trainer</p>
                </div>
                <ul class="nav navbar-nav navbar-center">
                    <li><p class="navbar-brand">PRP_Faculty Management System</p></li>
                </ul>
                <form action="LoginServlet" method="post" id="f1">
                    <ul class="nav navbar-nav navbar-right">
                        <li> <a href="javascript:{}" onclick="document.getElementById('f1').submit();"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </ul>
                    <input type="hidden" name="operation" value="logout" />
                </form> 
            </div>
        </nav>
        
        
        <nav class="navbar navbar-inverse navbar-fixed-top down">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="TrainerHomePage.jsp">Home</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Account <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="TrainerProfile.jsp">Profile</a></li>
                            <li><a href="TrainerChangePassword.jsp">Change Password</a></li>
                        </ul>
                    </li>

                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Task <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="TrainerUpdateTask.jsp">Update Task</a></li>
                            <li><a href="TrainerCreateContent.jsp">Create Content</a></li>
                            <li><a href="TrainerAddQuestion.jsp">Add Question</a></li>
                        </ul>
                    </li>
                    <li><a href="TrainerLeave.jsp">Leave</a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <form class="form-horizontal center" action="TrainerServlet" method="post">

                <div class="form-group">
                    <label for="leavedate" class="col-sm-2 control-label">From Date</label>
                    <div class="col-sm-5">
                        <input type="date" class="form-control" id="fromdate" name="fromdate"  required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="leavedate" class="col-sm-2 control-label">To Date</label>
                    <div class="col-sm-5">
                        <input type="date" class="form-control" id="todate" name="todate"  required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="purpose" class="col-sm-2 control-label">Purpose</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="purpose" name="purpose" placeholder="purpose" required>
                    </div>
                </div>
                 <%if (request.getAttribute("status") != null) {%>
                <input type="hidden" name="status" id="status" value="<%=(String) request.getAttribute("status")%>"/>
                <%request.setAttribute("status", null);
                } else {%>
                <input type="hidden" name="status" id="status" />
                <%}%>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit"  value="leave"  class="btn btn-default" name="operation">Submit</button>
                    </div>
                </div>
            </form>
        </div>

    </body>
</html>
