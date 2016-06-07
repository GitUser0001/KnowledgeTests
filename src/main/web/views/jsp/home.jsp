<%--
  Created by IntelliJ IDEA.
  User: Study
  Date: 23.05.2016
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <title>Home page</title>
    <jsp:include page="dlc/head.jsp"/>

</head>

<body>

<div class="container">

  <jsp:include page="dlc/manu.jsp">
    <jsp:param name="home" value="active"/>
  </jsp:include>

  <!-- Jumbotron -->
  <div class="jumbotron">
    <h1>Hi there!</h1>
    <p class="lead">Press green button to see your results!</p>
    <p class="lead">Good luck!</p>
    <p><a class="btn btn-lg btn-success" href="/KnowledgeTests/user/results" role="button">Get results</a></p>
  </div>

  <%--
  <!-- Example row of columns -->
  <div class="row">
    <div class="col-lg-4">
      <h2>Safari bug warning!</h2>
      <p class="text-danger">Some Test name 1</p>
      <p>Some descriptions</p>
      <p><a class="btn btn-primary" href="#" role="button">Start the test »</a></p>
    </div>
    <div class="col-lg-4">
      <h2>Some Test name 2</h2>
      <p>Some descriptions</p>
      <p><a class="btn btn-primary" href="#" role="button">Start the test »</a></p>
    </div>
    <div class="col-lg-4">
      <h2>Some Test name 3</h2>
      <p>Some descriptions</p>
      <p><a class="btn btn-primary" href="#" role="button">Start the test »</a></p>
    </div>
  </div>
  --%>

  <!-- Site footer -->
  <div class="footer">
    <p>© KPI 2016</p>
  </div>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

</body></html>