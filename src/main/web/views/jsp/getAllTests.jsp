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
        <jsp:param name="test" value="active"/>
    </jsp:include>



    <c:forEach var="testSet" items="${testsTripleSet}">

        <div class="row" style="margin-top: 40px">

        <c:forEach var="test" items="${testSet}">
            <div class="col-lg-4">
                <h2>${test.name}</h2>
                <p>${test.description}</p>
                <p>
                    <a class="btn btn-primary" href="/KnowledgeTests/test/${test.getId()}/pass" role="button">Start the test »</a>



                        <%--<sec:authorize ifAnyGranted="ADMIN">--%>
                    <a class="btn btn-warning" href="#" role="button">Edit</a>
                    <a class="btn btn-danger" href="#" role="button">Delete</a>
                        <%--</sec:authorize>--%>



                </p>
            </div>
        </c:forEach>

        </div>

    </c:forEach>

    <!-- Example row of columns
    <div class="row" style="margin-top: 40px">

    </div>
    -->


    <!-- Site footer -->
    <div class="footer">
        <p>© KPI 2016</p>
    </div>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

</body></html>