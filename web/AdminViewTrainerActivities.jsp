<%-- 
    Document   : AdminViewTrainerActivities
    Created on : Apr 23, 2017, 11:28:12 AM
    Author     : bala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <script>
            var req;
            function get()
            {
                if (window.XMLHttpRequest) {
                    return  new XMLHttpRequest( );
                } else if (window.ActiveXObject)
                {
                    return new ActiveXObject("Microsoft.XMLHTTP");
                }
            }
            function institutionlist()
            {
                document.getElementById('schedule').style.display = "none";
                document.getElementById('task').style.display = "none";
                document.getElementById('content').style.display = "none";
                document.getElementById('question').style.display = "none";
                document.getElementById('nodata').style.display = "none";
                req = get();
                var url = "AdminTrainerServlet?operation=institutionlist";
                req.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("institutionlist").innerHTML =
                                this.responseText;
                    }
                };
                req.open("POST", url, true);
                req.send(null);
            }
            function namelist(institution)
            {

                req = get();
                var url = "AdminTrainerServlet?operation=namelist&&institution=" + institution;
                req.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("namelist").innerHTML =
                                this.responseText;
                    }
                };
                req.open("POST", url, true);
                req.send(null);
            }
            function traineractivity(trainername, institution)
            {

                document.getElementById('schedule').style.display = "none";
                document.getElementById('task').style.display = "none";
                document.getElementById('content').style.display = "none";
                document.getElementById('question').style.display = "none";
                document.getElementById('nodata').style.display = "none";
                req = get();
                var url = "AdminTrainerServlet?operation=traineractivity&&trainername=" + trainername + "&&institution=" + institution;
                req.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {

                        document.getElementById('trainingstream').value = "";
                        document.getElementById('commencingdate').value = "";
                        document.getElementById('endingdate').value = "";
                        document.getElementById('noofdays').value = ""
                        document.getElementById('content').innerHTML = "";
                        document.getElementById('question').innerHTML = "";

                        var details = this.responseText.split(",");
                        if (details.length > 1) {
                            document.getElementById('trainingstream').innerHTML = details[0];
                            document.getElementById('commencingdate').innerHTML = details[1];
                            document.getElementById('endingdate').innerHTML = details[2];
                            document.getElementById('noofdays').innerHTML = details[3];
                            document.getElementById('schedule').style.display = "block";
                            document.getElementById('tasklist').innerHTML = details[4];
                            document.getElementById('task').style.display = "block";
                            if (details[5] != "nocontent") {
                                document.getElementById('content').innerHTML = " <table class='table table-striped table-hover '><caption class='col-md-offset-4'><h2>Content</h2></caption></table>" + details[5];
                                document.getElementById('content').style.display = "block";
                            }

                            if (details[6].trim() != 'noquestion') {
                                document.getElementById('question').innerHTML = " <table class='table table-striped table-hover '><caption class='col-md-offset-4'><h2>Question</h2></caption></table>" + details[6];
                                document.getElementById('question').style.display = "block";
                            }
                        } else
                        {
                            document.getElementById('nodata').style.display = "block";
                        }
                    }
                };
                req.open("POST", url, true);
                req.send(null);
            }
        </script>
    </head>
    <body onload="institutionlist()">
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
            <form class="form-horizontal center" >
                <div class="form-group">
                    <label for="institution" class="col-sm-2 control-label">Institution</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="institution" list="institutionlist" name="institution" placeholder="Institution" oninput="namelist(this.value)" value="">
                    </div>
                    <datalist id="institutionlist">

                    </datalist>
                </div>
                <div class="form-group">
                    <label for="trainername" class="col-sm-2 control-label">Trainer Name</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="trainername" name="trainername" list="namelist" placeholder="Trainer Name" value="">
                    </div>
                    <datalist id="namelist">

                    </datalist>
                </div> 
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" class="btn btn-default" name="operation" onclick="traineractivity(document.getElementById('trainername').value, document.getElementById('institution').value)">View</button>
                    </div>
                </div>
            </form>
            <div id="schedule">
                <table class="table table-striped table-hover table-center1">
                    <caption class="col-md-offset-4"><h2>Schedule Details</h2></caption>
                    <tbody>
                        <tr>
                            <th scope="row">Training Stream</th>
                            <td>:</td>
                            <td> <div id="trainingstream"></div></td>
                        </tr>
                        <tr>
                            <th scope="row">Commencing Date</th>
                            <td>:</td>
                            <td>  <div id="commencingdate"></div></td>
                        </tr>
                        <tr>
                            <th scope="row">Ending Date</th>
                            <td>:</td>
                            <td><div id="endingdate"></div></td>
                        </tr>
                        <tr>
                            <th scope="row">No Of Days</th>
                            <td>:</td>
                            <td>    <div id="noofdays"></div></td>
                        </tr>
                    </tbody>                     
                </table>   
            </div>
            <div id="task">
                <table class="table table-striped table-hover table-center1">
                    <caption class="col-md-offset-4"><h2>Tasks</h2></caption>
                    <thead>
                    <th scope="norecords">Task</th>
                    <th>Alloted date</th>
                    </thead>
                    <tbody id="tasklist">
                    </tbody>
                </table>
            </div>
            <div id="content" class="table-center1">

            </div>
            <div id="question" class="table-center1">
            </div>
            <div id="nodata">
                <table class="table table-striped table-hover table-center1">
                    <caption class="col-md-offset-4"><h2>Not Scheduled</h2></caption>
                </table>
            </div>
        </div>
    </body>
</html>

