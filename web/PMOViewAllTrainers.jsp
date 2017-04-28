<%-- 
    Document   : PMOAllScheduleTraining
    Created on : Apr 27, 2017, 1:17:19 AM
    Author     : bala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>All Schedule Training</title>
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
            function institutionlist()
            {
                document.getElementById('hidediv').style.display = "none";
                req = get();
                var url = "AdminTrainerServlet?operation=institutionlist";
                req.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("institutionlist").innerHTML = "<option>All</option>" + this.responseText;

                    }
                };
                req.open("POST", url, true);
                req.send(null);
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
            function trainingDetails(institution)
            {
                document.getElementById("details").innerHTML = "";
                req = get();
                var url = "AdminTrainerServlet?operation=trainingDetails&&institution=" + institution;
                req.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("details").innerHTML =
                                this.responseText;
                        document.getElementById('hidediv').style.display = "block";
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
                <li class="active"><a href="PMOViewAllTrainers.jsp" data-toggle="tab">Trainers</a></li>
                <li  ><a href="PMOViewTrainerActivities.jsp" data-toggle="tab">Trainer Activities</a></li>
                <li ><a href="PMOChangePassword.jsp" data-toggle="tab">Change Password</a></li>
            </ul>
        </nav>
        <div class="container">
            <form class="form-horizontal center">
                <div class="form-group">
                    <label for="institution" class="col-sm-2 control-label">Institution</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="institution" list="institutionlist" name="institution" placeholder="Institution" oninput="namelist(this.value)" value="">
                    </div>
                    <datalist id="institutionlist">

                    </datalist>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" class="btn btn-default" name="operation" onclick="trainingDetails(document.getElementById('institution').value)">View</button>
                    </div>
                </div>
            </form>
            <div id="hidediv">
                <table class="table table-striped table-hover table-center1">
                    <caption class="col-md-offset-4"><h2>Trainer Details</h2></caption>
                    <thead>
                    <th scope="row">Trainer Name</th>
                    <th >Institution</th>
                    <th >Minimum Working Period</th>
                    <th>Scheduled Training</th>
                    <th>Commencing Date</th>
                    <th>Ending Date</th>
                    </thead>
                    <tbody id="details">

                    </tbody>
                </table>
            </div>

        </div>

    </body>
</html>
