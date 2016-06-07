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
            <jsp:param name="test" value="active"/>
        </jsp:include>

        <h1 style="margin-bottom: 60px">Test: #${test.id}</h1>

        <form:form action="" method="post" modelAttribute="test">



            <div class="form-group">
                <label for="title" class="col-xs-3 control-label">Test name:</label>
                <form:input path="name" type="text" class="form-control" id="name" name="title" value="${test.name}" cssStyle="width: 100%"/>
            </div>


            <div class="form-group">
                <label class="col-xs-3 control-label">Description:</label>
                <form:textarea path="description" value="${test.description}"  cssStyle="width: 100%" rows="4"/>
            </div>

            <form:hidden path="id" value="${test.id}"/>

            <input type="submit" class="btn btn-primary" value="Save"/>
            <a href="back" class="btn btn-default" role="button">Back</a>
        </form:form>


    </div>
</body>
</html>
