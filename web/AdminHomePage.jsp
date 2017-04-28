<%-- 
    Document   : AdminHomePage
    Created on : Apr 15, 2017, 9:47:38 AM
    Author     : bala
--%>
<%@page import="ProjectFMS.Bean.LeaveBean"%>
<%@page import="ProjectFMS.DAO.TrainerDAO"%>
<%@page import="ProjectFMS.Bean.TrainerBean"%>
<%@page import="java.util.List"%>
<%@page import="ProjectFMS.DAO.AdminDAO"%>
<%@page import="ProjectFMS.Bean.TrainingScheduleBean"%>
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
        <title>Admin Home Page</title>
    </head>
    <body>
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
                            <li><a href="AdminSetDMWP.jsp">Set Default Minimum Working Period</a></li>
                            <li><a href="AdminSetMWP.jsp">Update Individual Minimum Working Period</a></li>
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
            <% AdminDAO adminDAO = new AdminDAO();
                TrainerDAO trainerDAO = new TrainerDAO();
                List<TrainingScheduleBean> trainingScheduleBeans = adminDAO.getIncorrectScheduleDetails();%>
            <% if (trainingScheduleBeans != null && !trainingScheduleBeans.isEmpty()) {%>
            <table class="table table-striped table-hover table-center">
                <caption class="col-md-offset-4"><h2>Notification</h2></caption>
                <thead>
                <th scope="row">Trainer Name</th>
                <th >Institution</th>
                <th >Training Stream</th>
                <th >Minimum Working period</th>
                <th >No of days Allocated</th>
                </thead>                     
                <tbody>
                    <%  for (TrainingScheduleBean trainingScheduleBean : trainingScheduleBeans) {
                            TrainerBean trainerBean = trainerDAO.viewTrainerDetails(trainingScheduleBean.getTrainerId());
                    %>
                    <tr> 
                        <td scope="row"><%=trainerBean.getTrainerName()%></td>
                        <td><%=trainerBean.getInstitution()%></td>
                        <td><%=trainingScheduleBean.getTrainingStream()%></td>
                        <td><%=trainerBean.getMinimumWorkingPeriodBean().getMinimumWorkingPeriod()%></td>
                        <td><%=((trainingScheduleBean.getToDate().getTime() - trainingScheduleBean.getFromDate().getTime()) / (1000 * 60 * 60 * 24)) + 1%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <%} else {%>
            <table class="table table-striped table-hover table-center">
                <caption class="col-md-offset-4"><h2>Notification</h2></caption>

                <thead>
                <th scope="row">Trainer Name</th>
                <th >Institution</th>
                <th >Training Stream</th>
                <th >Minimum Working period</th>
                <th >No of days Allocated</th>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="5"> <div class="col-sm-offset-4"> No RECORDS</div></td>
                    </tr>
                </tbody>
            </table>
            <%}%>
            <% List<LeaveBean> leaveBeans = adminDAO.getLeaveDetails();
                if (leaveBeans != null && !leaveBeans.isEmpty()) {%>
            <table class="table table-striped table-hover table-center1">
                <caption class="col-md-offset-4"><h2>Leave Status</h2></caption>
                <thead>
                <th scope="row">Trainer Name</th>
                <th >Institution</th>
                <th >Purpose Of Leave</th>
                <th >From Date</th>
                <th >To Date</th>
                <th >No of days</th>
                </thead>
                <tbody>
                    <%for (LeaveBean leaveBean : leaveBeans) {
                            TrainerBean trainerBean = trainerDAO.viewTrainerDetails(leaveBean.getTrainerId());%>
                    <tr>
                        <td scope="row"><%=trainerBean.getTrainerName()%> </td>
                        <td ><%=trainerBean.getInstitution()%></td>
                        <td ><%=leaveBean.getPurpose()%></td>
                        <td ><%=leaveBean.getFromDate()%></td>
                        <td ><%=leaveBean.getToDate()%></td>
                        <td ><%=((leaveBean.getToDate().getTime() - leaveBean.getFromDate().getTime()) / (1000 * 60 * 60 * 24)) + 1%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <%} else {%>
            <table class="table table-striped table-hover table-center1">
                <caption class="col-md-offset-4"><h2>Leave Status</h2></caption>
                <thead>
                <th scope="row">Trainer Name</th>
                <th >Institution</th>
                <th >Purpose Of Leave</th>
                <th >From Date</th>
                <th >To Date</th>
                <th >No of days</th>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="6"> <div class="col-sm-offset-4"> No RECORDS</div></td>
                    </tr>
                </tbody>
            </table>
            <%}%>
            <% List<TrainerBean> trainerBeans = adminDAO.getNonScheduleTrainers();
                if (trainerBeans != null && !trainerBeans.isEmpty()) {%>
            <table class="table table-striped table-hover table-center1">
                <caption class="col-md-offset-4"><h2>Non Scheduled Trainers</h2></caption>
                <thead>
                <th scope="row">Trainer Name</th>
                <th >Institution</th>
                <th >Minimum Working Period</th>
                </thead>
                <tbody>
                    <%for (TrainerBean trainerBean : trainerBeans) {%>
                    <tr>
                        <td scope="row"><%=trainerBean.getTrainerName()%> </td>
                        <td ><%=trainerBean.getInstitution()%></td>
                        <td><%=trainerBean.getMinimumWorkingPeriodBean().getMinimumWorkingPeriod()%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <%} else {%>
            <table class="table table-striped table-hover table-center1">
                <caption class="col-md-offset-4"><h2>Non Scheduled Trainers</h2></caption>
                <thead>
                <th scope="row">Trainer Name</th>
                <th >Institution</th>
                <th >Minimum Working Period</th>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="3"> <div class="col-sm-offset-4"> No RECORDS</div></td>
                    </tr>
                </tbody>
            </table>

            <%}%>
        </div>
    </body>
</html>
