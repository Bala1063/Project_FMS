<%-- 
    Document   : PMOScheduleTraining
    Created on : Apr 27, 2017, 1:27:08 AM
    Author     : bala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Schedule Training</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/PMO.css">
        <style type="text/css">
            .container-fluid{
                padding-left: 30px;
            }
        </style>
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
            <div class="container-fluid" id=container-fluid>
                <div class="navbar-header">
                    <a class="navbar-brand">Welcome PMO</a>
                </div>
                <div class="navbar-nav navbar-center">
                    <p class="navbar-brand">PRP_Faculty Management System </p>
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
                <li class="active" ><a href="PMOViewTrainerActivities.jsp" data-toggle="tab">Trainer Activities</a></li>
                <li ><a href="PMOChangePassword.jsp" data-toggle="tab">Change Password</a></li>
            </ul>
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