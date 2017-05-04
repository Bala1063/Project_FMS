<%-- 
    Document   : TrainerAddQuestion
    Created on : Apr 11, 2017, 7:53:43 AM
    Author     : PRP_FMS:- Aruna M, Aswini A, Balaji S K, Sushmitha S.
--%>

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
        <script>
            var count = 2;
            function addElement() {

                if (count < 6) {
                    count++;
                    var newTextArea = document.createElement('div');
                    newTextArea.setAttribute('id', count);
                    newTextArea.setAttribute('class', 'form-group');
                    newTextArea.innerHTML = ' <label for="option' + count + '" class="col-sm-2 control-label">Option' + count + '</label> <div class="col-sm-9"><textarea class="form-control" id="option' + count + '" name="option' + count + '" placeholder="option' + count + '" required/></textarea></div>';
                    document.getElementById('eg').appendChild(newTextArea);
                } else
                {
                    alert("can't add more than four");
                }
            }
            function removeElement() {
                if (count > 2) {
                    document.getElementById('eg').removeChild(document.getElementById(count));
                    count--;
                } else {
                    alert("Can't Remove ");
                }
            }
            function setCount()
            {
                document.getElementById('count').value = count;
            }
            function checkStatus()
            {checksession();
                var x = document.getElementById("status").value;
                if (x != "")
                {
                    document.getElementById("status").value = "";
                    window.alert(x);
                }
            }
        </script>
    </head>
    <body onload="checkStatus()" id="hidebody">
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
            <form class="form-horizontal center" action="TrainerServlet" method="post">
                <div class="form-group">
                    <label for="question" class="col-sm-2 control-label">Question</label>
                    <div class="col-sm-9">
                        <textarea class="form-control" id="question" name="question" placeholder="Question" required></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="option1" class="col-sm-2 control-label">Option1</label>
                    <div class="col-sm-9">
                        <textarea class="form-control" id="firstOption" name="option1" placeholder="Option1" required></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="option2" class="col-sm-2 control-label">Option2</label>
                    <div class="col-sm-9">
                        <textarea class="form-control" id="option2" name="option2" placeholder="Option2" required></textarea>
                    </div>
                </div>
                <div  id="eg">
                </div>
                <div class="form-group">
                    <div class="form-inline">
                        <div class="col-sm-offset-2  col-sm-9">
                            <button type="button" class="btn btn-default" onclick="addElement()">Add Option</button>
                            <button type="button" class="btn btn-default" onclick="removeElement()">Remove Option</button>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="answer" class="col-sm-2 control-label">Answer</label>
                    <div class="col-sm-9">
                        <textarea class="form-control" id="answer" name="answer" placeholder="Answer" required></textarea>
                    </div>
                </div>
                <input type="hidden" name="count" id="count" value=""/>
                <% if (request.getAttribute("status") != null) {%>
                <input type="hidden" name="status" id="status" value="<%=(String) request.getAttribute("status")%>">
                <% request.setAttribute("status", null);
                } else {%>
                <input type="hidden" name="status" id="status" >
                <%}%>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" name="operation" value="question" onclick="setCount()">Add</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
