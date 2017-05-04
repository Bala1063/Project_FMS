<%-- 
    Document   : AdminViewAllTrainers
    Created on : Apr 20, 2017, 11:53:41 AM
    Author     : PRP_FMS:- Aruna M, Aswini A, Balaji S K, Sushmitha S.
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
        <script src="js/custom.js"></script>
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
            { document.getElementById('hidediv').style.display = "none";
               checksession();
                req = get();
                var url = "AdminTrainerServlet?operation=institutionlist";
                req.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        alert();
                        document.getElementById("institutionlist").innerHTML ="<option>ALL</option>"+this.responseText;
                    }
                };
                req.open("POST", url, true);
                req.send(null);
            }
            function trainingDetails(institution)
            { document.getElementById('hidediv').style.display = "none";
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
    <body onload="institutionlist()" id="hidebody">
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

