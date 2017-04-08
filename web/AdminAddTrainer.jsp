<%-- 
    Document   : AdminAddTrainer
    Created on : Apr 6, 2017, 10:15:14 PM
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
        <title>Admin Add Trainer</title>
        <script>
            function checkStatus()
            {
                var x=document.getElementById("status").value;
                if(x!="")
                {
                    document.getElementById("status").value="";
                    window.alert(x);
                }
            }
            </script>
    </head>
    <body onload="checkStatus()">
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand">Welcome Admin</a>
                    <form class="nav navbar-nav right"  action="LoginServlet" method="post">
                        <button type="submit"  value="logout" name="operation"  class="btn btn-default navbar-btn "><span><i class="glyphicon glyphicon-off"></i> </span>Logout</button>
                    </form>
                </div>

            </div>
        </nav>
        <nav class="navbar navbar-inverse navbar-fixed-top down">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="AdminHomePage.html">Home</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Trainer <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="AdminAddTrainer.jsp">Add Trainer</a></li>
                            <li><a href="AdminUpdateTrainer.jsp">Update Trainer</a></li>
                            <li><a href="AdminRemoveTrainer.jsp">Remove Trainer</a></li>
                             <li><h6 class="dropdown-header">Minimum Working Period</h6></li>
                              <li><a href="AdminSetDMWP.jsp">Set Default Minimum Working Period</a></li>
                            <li><a href="AdminSetMWP.jsp">Update Individual Minimum Working Period</a></li>
                            <li><a href="AdminAllocateTraining.html">Allocate Training</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Training <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="AdminAddTraining.html">Add Training</a></li>
                            <li><a href="AdminUpdateTraining.html">Update Training</a></li>
                            <li><a href="AdminRemoveTraining.html">Remove Training</a></li>
                            <li><h6 class="dropdown-header">Question</h6></li>
                            <li><a href="AdminAddQuestion.html">Add Question</a></li>
                            <li><a href="AdminUpdateQuestion.html">Update Question</a></li>
                            <li><a href="AdminRemoveQuestion.html">Remove Question</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Daily Task <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="AdminAddTask.html">Add Task</a></li>
                            <li><a href="AdminUpdateTask.html">Update Task</a></li>
                            <li><a href="AdminRemoveTask.html">Remove Task</a></li>
                        </ul>
                    </li>

                </ul>
            </div>
        </nav>
        <div class="container">
            <form class="form-horizontal center" action="AdminTrainerServlet" method="post">
                <div class="form-group">
                    <label for="trainername" class="col-sm-2 control-label">Trainer Name</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="trainername" name="trainername" placeholder="Trainer Name" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="areaofspecialization" class="col-sm-2 control-label">Area of Specialization</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="areaofspecialization" name="areaofspecialization" placeholder="Area of Specialization" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="institution" class="col-sm-2 control-label">Institution</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="institution" name="institution" placeholder="Institution" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="selfskilling" class="col-sm-2 control-label">Self Skilling</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="selfskilling" name="selfskilling" placeholder="Self Skilling" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="qualifications" class="col-sm-2 control-label">Qualifications</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="qualifications" name="qualifications" placeholder="Qualifications" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="mailid" class="col-sm-2 control-label">Mail Id</label>
                    <div class="col-sm-5">
                        <input type="email" class="form-control" id="mailid" name="mailid" placeholder="Mail Id" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="phoneno" class="col-sm-2 control-label">Phone no</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="phoneno" name="phoneno" placeholder="Phone no" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="dateofbirth" class="col-sm-2 control-label">Date of Birth</label>
                    <div class="col-sm-5">
                        <input type="date" class="form-control" id="dateofbirth" name="dateofbirth"  required>
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
                        <button type="submit"  value="add"  class="btn btn-default" name="operation">Add</button>
                    </div>
                </div>
            </form>
        </div>

    </body>
</html>

