<%-- 
    Document   : AdminUpdateTrainer
    Created on : Apr 7, 2017, 5:33:33 AM
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
        <title>Admin Update Trainer</title>
        <script>
            var req;
            function checkStatus()
            {
                checksession();
                var x = document.getElementById("status").value;
                if (x != "")
                {
                    document.getElementById("status").value = "";
                    window.alert(x);
                }
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
            function institutionlist()
            {
                checkStatus();
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

            function getDetails(institution, trainername)
            {
                req = get();
                var url = "AdminTrainerServlet?operation=getDetails&&institution=" + institution + "&&trainername=" + trainername;
                req.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {

                        var details = this.responseText.split(",");
                        if (details.length > 1)
                        {
                            document.getElementById("userid").value = details[0];
                            document.getElementById("areaofspecialization").value = details[2];
                            document.getElementById("qualifications").value = details[4];
                            document.getElementById("mailid").value = details[5];
                            document.getElementById("phoneno").value = details[6];
                            var date = new Date(details[7]);
                            var year = date.getFullYear();
                            var month = "";
                            var day = "";
                            if (date.getMonth() + 1 < 10)
                            {
                                month = "0" + date.getMonth();
                            } else
                            {
                                month = date.getMonth() + 1 + "";
                            }
                            if (date.getDate() < 10)
                            {
                                day = "" + date.getDate();
                            } else
                            {
                                day = date.getDate() + "";
                            }


                            document.getElementById("dateofbirth").value = year + "-" + month + "-" + day;
                            ;
                        } else
                        {
                            document.getElementById("userid").value = "";
                            document.getElementById("areaofspecialization").value = "";
                            document.getElementById("selfskilling").value = "";
                            document.getElementById("qualifications").value = "";
                            document.getElementById("mailid").value = "";
                            document.getElementById("phoneno").value = "";
                            document.getElementById("dateofbirth").value = "";

                        }
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
            <form class="form-horizontal center" action="AdminTrainerServlet" method="post">
                <div class="form-group">
                    <label for="institution" class="col-sm-2 control-label">Institution</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="institution" name="institution" list="institutionlist" placeholder="Institution" oninput="namelist(this.value)" value="">
                    </div>
                    <datalist id="institutionlist">

                    </datalist>
                </div>
                <div class="form-group">
                    <label for="trainername" class="col-sm-2 control-label">Trainer Name</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="trainername" list="namelist" name="trainername" placeholder="Trainer Name" oninput="getDetails(document.getElementById('institution').value,this.value)" value="">
                    </div>
                    <datalist id="namelist">

                    </datalist>
                </div>
                <div class="form-group">
                    <label for="areaofspecialization" class="col-sm-2 control-label">Area of Specialization</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="areaofspecialization" name="areaofspecialization" placeholder="Area of Specialization" value="">
                    </div>
                </div>

                <div class="form-group">
                    <label for="qualifications" class="col-sm-2 control-label">Qualifications</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="qualifications" name="qualifications" placeholder="Qualifications" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="mailid" class="col-sm-2 control-label">Mail Id</label>
                    <div class="col-sm-5">
                        <input type="email" class="form-control" id="mailid" name="mailid" placeholder="Mail Id" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="phoneno" class="col-sm-2 control-label">Phone no</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="phoneno" placeholder="Phone no" name="phoneno" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label for="dateofbirth" class="col-sm-2 control-label">Date of Birth</label>
                    <div class="col-sm-5">
                        <input type="date" class="form-control" id="dateofbirth" name="dateofbirth" value="">
                    </div>
                </div>
                <input type="hidden"  id="userid" name="userid" value="" />
                <%if (request.getAttribute("status") != null) {%>
                <input type="hidden" name="status" id="status" value="<%=(String) request.getAttribute("status")%>"/>
                <%request.setAttribute("status", null);
                } else {%>
                <input type="hidden" name="status" id="status" />
                <%}%>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" value="update" name="operation">Update</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>