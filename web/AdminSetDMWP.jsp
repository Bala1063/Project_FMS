<%-- 
    Document   : AdminSetMWP
    Created on : Apr 7, 2017, 9:36:12 PM
    Author     : bala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
﻿<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-theme.min.css" rel="stylesheet">
        <link href="css/admin.css" rel="stylesheet">
        <script src="jquery/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>
        <title>Admin Set MWP</title>
        <script>
            var req;
            function checkStatus()
            {
                checksession();
                var x = document.getElementById("status").value;
                if (x != "")
                {
                    document.getElementById("status").value = "";
                    window.alert(x);
                }
            }
            function get()
            {
                if (window.XMLHttpRequest) {
                    return  new XMLHttpRequest( );
                } else if (window.ActiveXObject)
                {
                    return new ActiveXObject("Microsoft.XMLHTTP");
                }
            }
            function getdmwp()
            {

                checkStatus();
                req = get();
                var url = "AdminTrainerServlet?operation=getdmwp";
                req.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {

                        document.getElementById("minimumworkingperiod").value = this.responseText;
                    }
                };
                req.open("POST", url, true);
                req.send(null);
            }
        </script>
    </head>
    <body onload="getdmwp()" id="hidebody">
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <p class="navbar-brand">Welcome! Admin</p>
                </div>
                <ul class="nav navbar-nav navbar-center">
                    <li><p class="navbar-brand">PRP_Faculty Management System</p></li>
                </ul>
                <form action="LoginServlet" method="post">
                    <ul class="nav navbar-nav navbar-right">
                        <li> <button type="submit"  value="logout" name="operation"  class="btn btn-default navbar-btn "><span><i class="glyphicon glyphicon-off"></i> </span>Logout</button></li>
                    </ul>

                </form> 
            </div>
        </nav>
        <nav class="navbar navbar-inverse navbar-fixed-top down">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="AdminHomePage.jsp">Home</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Trainer <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="AdminAddTrainer.jsp">Add Trainer</a></li>
                            <li><a href="AdminUpdateTrainer.jsp">Update Trainer</a></li>
                            <li><a href="AdminRemoveTrainer.jsp">Remove Trainer</a></li>
                            <li><h6 class="dropdown-header">Minimum Working Period</h6></li>
                            <li><a href="AdminSetDMWP.jsp">Default Min Working Period</a></li>
                            <li><a href="AdminSetMWP.jsp">Individual Min Working Period</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Training <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="AdminAllocateTraining.jsp">Allocate Training</a></li>
                            <li><a href="AdminUpdateTraining.jsp">Update Training</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Daily Task <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="AdminAddTask.jsp">Add Task</a></li>

                        </ul>
                    </li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">View <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="AdminViewAllTrainers.jsp">All Trainers</a></li>
                            <li><a href="AdminViewTrainerActivities.jsp">Trainer Activities</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Settings<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="AdminChangePassword.jsp">Change Password</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <form class="form-horizontal center" action="AdminTrainerServlet" method="post">

                <div class="form-group">
                    <label for="minimumworkingperiod" class="col-sm-2 control-label">Minimum Working Period</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="minimumworkingperiod" name="minimumworkingperiod" placeholder="Minimum Working Period" value="">
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
                        <button type="submit" class="btn btn-default" name="operation" value="dmwp">Update </button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>

