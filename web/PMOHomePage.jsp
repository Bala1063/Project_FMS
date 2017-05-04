<%-- 
    Document   : PMOHomePage
    Created on : Apr 27, 2017, 12:54:29 AM
    Author     : PRP_FMS:- Aruna M, Aswini A, Balaji S K, Sushmitha S.
--%>

<%@page import="ProjectFMS.Bean.LeaveBean"%>
<%@page import="ProjectFMS.Bean.TrainerBean"%>
<%@page import="ProjectFMS.Bean.TrainingScheduleBean"%>
<%@page import="java.util.List"%>
<%@page import="ProjectFMS.DAO.TrainerDAO"%>
<%@page import="ProjectFMS.DAO.AdminDAO"%>
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
    </head>
    <body onload="checksession()" id="hidebody">
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
                <li class="active"><a href="PMOHomePage.jsp" data-toggle="tab">Home</a></li>
                <li ><a href="PMOViewAllTrainers.jsp" data-toggle="tab">Trainers</a></li>
                <li  ><a href="PMOViewTrainerActivities.jsp" data-toggle="tab">Trainer Activities</a></li>
                <li ><a href="PMOChangePassword.jsp" data-toggle="tab">Change Password</a></li>
            </ul>
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
