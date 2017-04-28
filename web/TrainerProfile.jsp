<%-- 
    Document   : TrainerProfile
    Created on : 8 Apr, 2017, 1:11:32 PM
    Author     : AswiniAnjappan
--%>
<%@page import="ProjectFMS.Util.Util"%>
<%@page import="org.hibernate.Session"%>
<%@page import="ProjectFMS.Bean.TrainerBean" %>
?<!DOCTYPE html>
<html>
    <head><title>
            Trainer Profile
        </title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap-theme.min.css" rel="stylesheet">
        <link href="css/trainer.css" rel="stylesheet">
        <script src="jquery/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <p class="navbar-brand">Welcome! Trainer</p>
                </div>
                <ul class="nav navbar-nav navbar-center">
                    <li><p class="navbar-brand">PRP_Faculty Management System</p></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="Login.html"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
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
                TrainerBean trainerBean = (TrainerBean) session1.get(TrainerBean.class, (String) request.getSession(false).getAttribute("userId"));
                session1.close();%>
            <% if (trainerBean != null) {%>
            
            <table class="table table-striped table-hover table-center">
                <caption class="col-md-offset-4"><h2>PROFILE</h2></caption>
                <tbody>
                    
                    <tr>
                        <th scope="row">Trainer Name</th>
                        <td>:</td>
                        <td><%=trainerBean.getTrainerName()%></td>

                    </tr>
                    <tr>
                        <th scope="row">Area of Specialization</th>
                        <td>:</td>
                        <td><%=trainerBean.getAreaOfSpecialization()%></td>

                    </tr>
                    <tr>
                        <th scope="row">Institution</th>
                        <td>:</td>
                        <td><%=trainerBean.getInstitution()%></td>

                    </tr>
                    <tr>
                        <th scope="row">Qualifications</th>
                        <td>:</td>
                        <td><%=trainerBean.getQualifications()%></td>
                    </tr><tr>
                        <th scope="row">Mail Id</th>
                        <td>:</td>
                        <td><%=trainerBean.getMailId()%></td>
                    </tr>
                    <tr>
                        <th scope="row">Phone no</th>
                        <td>:</td>
                        <td><%=trainerBean.getPhoneNo()%></td>
                    </tr>
                    <tr>
                        <th scope="row">Date of Birth</th>
                        <td>:</td>
                        <td><%=trainerBean.getDateOfBirth()%></td>
                    </tr>
                    <tr>
                        <th scope="row">Minimum Working Period</th>
                        <td>:</td>
                        <td><%=trainerBean.getMinimumWorkingPeriodBean().getMinimumWorkingPeriod() %></td>
                    </tr>
                </tbody>
            </table>
            <%} else {%>
            <h1>Server Problem</h1>
            <%}%>
        </div>
    </body>
</html>

