<%-- 
    Document   : TrainerUpdateTask
    Created on : Apr 12, 2017, 3:20:53 PM
    Author     : bala
--%>

<%@page import="ProjectFMS.Bean.TaskBean"%>
<%@page import="java.util.List"%>
<%@page import="ProjectFMS.DAO.TrainerDAO"%>
<%@page import="ProjectFMS.Bean.TrainingScheduleBean"%>
<%@page import="ProjectFMS.Util.Util"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
﻿<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-theme.min.css" rel="stylesheet">
        <link href="css/trainer.css" rel="stylesheet">
        <script src="jquery/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <title>
            Trainer Update Task
        </title>
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

            <% Session session1 = Util.getSessionFactory().openSession();
                String trainingId = new TrainerDAO().getTrainingId((String) request.getSession(false).getAttribute("userId"));
                TrainingScheduleBean trainingScheduleBean = null;
                if (trainingId != null) {
                    trainingScheduleBean = (TrainingScheduleBean) session1.get(TrainingScheduleBean.class, trainingId);
                }
                session1.close();
                List<TaskBean> taskBeans = trainingScheduleBean.getTaskBeans();
                int count = 0;
                for (TaskBean taskBean : taskBeans) {
                    if (taskBean.getTaskStatus().equalsIgnoreCase("no")) {
                        count++;
                    }
                }
            %>
            <% if (trainingScheduleBean != null && !trainingScheduleBean.getTaskBeans().isEmpty() && count != 0) {%>

            <table class="table table-striped table-hover table-center">
                <caption class="col-md-offset-4"><h2>Update Tasks</h2></caption>
                <tbody>
                    <tr>
                        <th scope="row">Task</th>
                        <th>Alloted Date</th>
                        <th>Action</th>
                    </tr>
                    <%for (TaskBean taskBean : taskBeans) {
                            if (taskBean.getTaskStatus().equalsIgnoreCase("no")) {%>
                    <tr>
                        <td><%=taskBean.getTask()%></td>
                        <td><%=taskBean.getTaskDate()%></td>
                        <td>
                            <form  action="TrainerServlet" method="post">
                                <input type="hidden" name="taskid" id="taskid" value="<%=taskBean.getTaskId()%>" />
                                <button class="btn btn-default" type="submit" value="updatetask" name="operation">Update</button>
                            </form>
                        </td>
                    </tr>
                    <%}
                        }%>
                </tbody>
            </table>
            <%} else {%>
            <table class="table table-striped table-hover table-center">
                <caption class="col-md-offset-4"><h2>There is no task to update</h2></caption>
            </table>
            <%}%>
            <% if (request.getAttribute("status") != null) {%>
            <input type="hidden" name="status" id="status" value="<%=(String) request.getAttribute("status")%>">
            <% request.setAttribute("status", null);
            } else {%>
            <input type="hidden" name="status" id="status" >
            <%}%>
        </div>
    </body>
</html>
