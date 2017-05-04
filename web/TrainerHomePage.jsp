<%-- 
    Document   : TrainerHomePage
    Created on : Apr 11, 2017, 10:06:00 PM
    Author     : bala
--%>

<%@page import="ProjectFMS.DAO.AdminDAO"%>
<%@page import="ProjectFMS.Bean.TaskBean"%>
<%@page import="java.util.List"%>
<%@page import="ProjectFMS.DAO.TrainerDAO"%>
<%@page import="ProjectFMS.Bean.TrainingScheduleBean"%>
<%@page import="ProjectFMS.Util.Util"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
﻿<!DOCTYPE html>
<html>
    <head><title>
            Trainer Home Page
        </title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-theme.min.css" rel="stylesheet">
        <link href="css/trainer.css" rel="stylesheet">
        <script src="jquery/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
     <script src="js/custom.js"></script>
    </head>
    <body onload="checksession()" id="hidebody">
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

            <% AdminDAO adminDAO = new AdminDAO();
                TrainingScheduleBean trainingScheduleBean = null;
                String userId = (String) request.getSession(false).getAttribute("userId");
                if (userId != null) {
                    trainingScheduleBean = adminDAO.viewTrainingScheduleByTrainerId(userId);
                }
            %>
            <% if (trainingScheduleBean != null) {%>

            <table class="table table-striped table-hover table-center">
                <caption class="col-md-offset-4"><h2>Training Schedule</h2></caption>
                <tbody>
                    <tr>
                        <th scope="row">Training Stream</th>
                        <td>:</td>
                        <td><%= trainingScheduleBean.getTrainingStream()%></td>
                    </tr>
                    <tr>
                        <th scope="row">Commencing Date</th>
                        <td>:</td>
                        <td><%= trainingScheduleBean.getFromDate()%></td>
                    </tr>
                    <tr>
                        <th scope="row">Ending Date</th>
                        <td>:</td>
                        <td><%=trainingScheduleBean.getToDate()%></td>
                    </tr>
                </tbody>
                <% if (!trainingScheduleBean.getTaskBeans().isEmpty()) {%>
            </table>
            <table class="table table-striped table-hover table-center1">
                <caption class="col-md-offset-4"><h2>Allocated Tasks</h2></caption>
                <tbody>
                    <tr>
                        <th scope="row">Task</th>
                        <th>Alloted Date</th>
                    </tr>
                    <% List<TaskBean> taskBeans = trainingScheduleBean.getTaskBeans();
                        for (TaskBean taskBean : taskBeans) {
                            if (taskBean.getTaskStatus().equalsIgnoreCase("no")) {%>
                    <tr class="danger">
                        <td scope="row"><%=taskBean.getTask()%></td>
                        <td><%=taskBean.getTaskDate()%></td>
                    </tr>
                    <%} else {%>
                    <tr class="success">
                        <td scope="row"><%=taskBean.getTask()%></td>
                        <td><%=taskBean.getTaskDate()%></td>
                    </tr>
                    <%}
                        }%>
                </tbody>
            </table>
            <%} else {%>
            <table class="table table-striped table-hover table-center">
                <caption class="col-md-offset-4"><h2>no task are added</h2></caption>
            </table>
            <%}%>
            <%} else {%>
            <table class="table table-striped table-hover table-center">
                <caption class="col-md-offset-4"><h2>No Schedule</h2></caption>
            </table>
            <%}%>
        </div>
    </body>
</html>
