<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Study
  Date: 29.05.2016
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>


    <div class="masthead">
        <h3 class="text-muted">Testing of knowledge</h3>
        <ul class="nav nav-justified">
            <li class="${param.getOrDefault("home","")}"><a href="/KnowledgeTests">Home</a></li>


            <li class="${param.getOrDefault("test","")}"><a href="/KnowledgeTests/test/all">Tests</a></li>



            <sec:authorize ifAnyGranted="ADMIN">
                <li class="${param.getOrDefault("addTest","")}"><a href="/KnowledgeTests/test/addTest">Add tests</a></li>
            </sec:authorize>




            <li class="${param.getOrDefault("user","")}"><a href="/KnowledgeTests/user/results">User</a></li>
            <li class="${param.getOrDefault("...","")}"><a href="#">...</a></li>
            <li class="${param.getOrDefault("about","")}"><a href="#">About</a></li>
            <li class="${param.getOrDefault("contact","")}"><a href="#">Contact</a></li>
            <sec:authorize access="!isAuthenticated()">
                <li><a href="/KnowledgeTests/account/login">Login</a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li><a href="#">Hi <sec:authentication property="principal.username" /> !</a></li>
                <li><a href="/KnowledgeTests/account/logout">Logout</a></li>
            </sec:authorize>
        </ul>
    </div>


