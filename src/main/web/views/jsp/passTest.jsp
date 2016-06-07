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

    <script type="text/javascript" src="/KnowledgeTests/resources/js/jquery.js"></script>


    <script>
        const ip = window.location.href.replace('http://','').split(':')[0];
        const testId = ${test.getId()};
        var dateTime;

        var questions;
        var usersAnswers = {};

        var AJAX = {
            getQuestions: function (callback) {
                $.ajax({
                    url: "http://" + ip + ":8080/KnowledgeTests/test/" + testId + "/questions",
                    type: "POST",
                    success: function (data, textStatus, jqXHR) {
                        if (textStatus === "success") {
                            callback(data);
                        } else {
                            alert("Oops");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(errorThrown || textStatus);
                    }
                })
            },

            sendAnswers: function (callback) {
                $.ajax({
                    url: "http://" + ip + ":8080/KnowledgeTests/test/" + testId + "/pass",
                    type: "POST",
                    data: {
                        dateTime: dateTime,
                        usersAnswers: JSON.stringify(usersAnswers)
                    },
                    success: function (data, textStatus, jqXHR) {
                        if (textStatus === "success") {
                            callback(data);
                        } else {
                            alert("Oops");
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(errorThrown || textStatus);
                    }
                })
            }
        };

        var answersInteraction = {
            setAnswers: function (question, userAnswers) {
                $("#nameQuestion").text(question.name);
                $("#dynamicInput").empty();

                var id = 1;

                $.each(question.answers, function(arrayID,answersData) {

                    var isChecked = "";
                    if (userAnswers[question.name] && userAnswers[question.name] === answersData)
                        isChecked = "checked";

                    var txt = "\<div class=\"input-group\" style=\"width: 100%\">" +
                                "\<span class=\"input-group-addon\">"+
                                "\<input id=" + id + "\ name=\"answer\" value=\"" + answersData + "\" " + isChecked + "\ style=\"width: auto\" type=\"radio\" aria-label=\"...\">"+
                                "\</span>"+
                                "\<label style=\"width: 100%\" type=\"text\" class=\"form-control\" aria-label=\"...\">" + answersData + "\</label>"+
                                "\</div>" +
                                "\<p></p>";

                    $("#dynamicInput").append(txt);
                    id++;
                });
            },
            createUserAnswersJson: function (questions) {
                $.each(questions, function (arrayID, questionData) {
                    usersAnswers[questionData.name] = undefined;
                })
            },
            saveAnswer: function () {
                var answer =  $("input[name=answer]:checked", "#dynamicInput").val();
                usersAnswers[$("#nameQuestion").text()] = answer;
                return answer;
            }
        };


        $(document).ready(function(){

            $("#startTestBtn").click(function () {
               AJAX.getQuestions(function (data) {
                   dateTime = data.start;
                   questions = data.questions;
                   answersInteraction.createUserAnswersJson(questions);
                   answersInteraction.setAnswers(questions[questionNumber], usersAnswers);
                   $("#startTestBtn").hide();
                   $("#divAnswerForm").show();
               })
            });



            $("#nextQuestionBtn").click(function () {
                if (questionNumber < questions.length - 1) {
                    questionNumber++;

                    answersInteraction.saveAnswer();
                    answersInteraction.setAnswers(questions[questionNumber], usersAnswers);
                } else alert("This is last question! Try to confirm ;D");
            });


            $("#prevQuestionBtn").click(function () {
                if (questionNumber > 0) {
                    questionNumber--;

                    answersInteraction.saveAnswer();
                    answersInteraction.setAnswers(questions[questionNumber], usersAnswers);
                } else alert("This is first question!");
            });


            $("#confirmBtn").click(function () {
                answersInteraction.saveAnswer();

                var isAllAnswers = true;

                for (var item in usersAnswers) {
                    if (!usersAnswers[item])
                        isAllAnswers = false;
                }

                if (!isAllAnswers){
                    alert("You do not answer on all questions!");
                }
                else {
                    AJAX.sendAnswers(function (data) {
                        if (data){
                            alert("Your mark = " + data.mark);
                            window.location = "http://"+ ip +":8080/KnowledgeTests";
                        }
                        else alert("Oops, try later. Mb server is down");

                    });
                }
            })


        });

        var questionNumber = 0;




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

        <label class="text-uppercase text-sm">${test.getName()}</label>
        <p/>
        <label style="margin-top: 20px" class="text-uppercase text-sm">Description:</label>

        <label class="text-uppercase text-sm">${test.getDescription()}</label>
        <p/>
        <button id="startTestBtn" type="button" class="btn btn-success">Start the Test</button>

    </div>









    <div hidden id="divAnswerForm">

        <div>
            <div>
                <!--  Question     -   name  answers   -->
                <label style="margin-top: 50px" class="text-uppercase text-sm">Question</label>
                <!--  Question.Name ------------------------------------------------------------------ -->
                <label id="nameQuestion" class="form-control"></label>
                <div class="row" style="width: 100%">
                    <div class="col-lg-6" style="width: inherit">
                        <label style="margin-top: 20px" class="text-uppercase text-sm">Choose correct answer:</label>



                        <div id="dynamicInput" class="input-group">
                            <%--
                            <span class="input-group-addon">
                                <!--  Question.checkbox  -->
                                <input id="radio1" checked="checked" name="answer" value="1" style="width: auto" type="radio" aria-label="...">
                            </span>
                            <!--  Question.Answer  -->
                            <input id="answer1" style="width: 85%" type="text" class="form-control" aria-label="...">
                            --%>
                        </div>



                    </div>
                </div>
            </div>
        </div>

        <div  align="center" style="width: 100%">

            <button id="prevQuestionBtn" style="margin: 20px" class="btn btn-lg btn-warning" type="button">Previous question</button>

            <button id="confirmBtn" style="margin: 20px" class="btn btn-lg btn-success" type="button">Confirm</button>

            <button id="nextQuestionBtn" style="margin: 20px" class="btn btn-lg btn-warning" type="button">Next question</button>

        </div>

    </div>


</div>





</div>

</body>
</html>
