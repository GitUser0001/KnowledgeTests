<%--
  Created by IntelliJ IDEA.
  User: Study
  Date: 29.05.2016
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Edit Test</title>
    <jsp:include page="dlc/head.jsp"/>


    <script type="text/javascript" src="/KnowledgeTests/resources/js/jquery.js"></script>
</head>
<body>

<div class="container">
    <jsp:include page="dlc/manu.jsp">
        <jsp:param name="user" value="active"/>
    </jsp:include>

    <h1 style="margin-bottom: 60px">User: #${user.nick}</h1>


    <c:forEach var="test" items="${user.getTests()}">



                <div class="col-lg-4">
                    <h4>${test.getTest().name}</h4>
                    <p>Date ${test.getPassedIn()}</p>
                    <p>Mark: ${test.getMark()}\100</p>
                </div>



    </c:forEach>


</div>
</body>
</html>
