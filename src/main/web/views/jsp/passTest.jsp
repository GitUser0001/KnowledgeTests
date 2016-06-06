<%--
  Created by IntelliJ IDEA.
  User: Study
  Date: 05.06.2016
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
    <title>Pass Test</title>
    <jsp:include page="dlc/head.jsp"/>

    <script src="/KnowledgeTests/resources/js/addTest.js" language="Javascript" type="text/javascript"></script>
    <script type="text/javascript" src="/KnowledgeTests/resources/js/jquery.js"></script>


    <script>
        var ip = window.location.href.replace('http://','').split(':')[0];

        var questions = [
            <c:forEach var="question" items="${test.getQuestions()}">
            {
                question:"${question.name}",
                answers:"
                <c:forEach var="answer" items="${question.getAnswers()}">
                    ${answer},
                </c:forEach>
                "}
            </c:forEach>
        ];
    </script>

</head>
<body>


<div class="container">
    <jsp:include page="dlc/manu.jsp">
        <jsp:param name="addTest" value="active"/>
    </jsp:include>









    <!--  TEST     -   name \ description  -->
    <div id="testForm" style="margin-top: 50px">
        <label style="margin-top: 20px" class="text-uppercase text-sm">Test name:</label>
        <label class="text-uppercase text-sm">${test.name}</label>

        <label style="margin-top: 20px" class="text-uppercase text-sm">Description</label>
        <label class="text-uppercase text-sm">${test.description}</label>


    </div>









    <div id="divAnswerForm">


    </div>





</div>

</body>
</html>
