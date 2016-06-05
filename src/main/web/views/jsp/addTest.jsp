<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Study
  Date: 29.05.2016
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new Test</title>
    <jsp:include page="dlc/head.jsp"/>

    <script src="/KnowledgeTests/resources/js/addTest.js" language="Javascript" type="text/javascript"></script>
    <script type="text/javascript" src="/KnowledgeTests/resources/js/jquery.js"></script>


    <script>

        var ip = window.location.href.replace('http://','').split(':')[0];

        AJAX = {
            sendQuestion: function (question, callback) {
                $.ajax({
                    url : "http://"+ ip +":8080/KnowledgeTests/test/addQuestion",
                    type: "POST",
                    data : question ,
                    success:function(data, textStatus, jqXHR)
                    {
                        if (data.success === true) {
                            callback();
                        } else {
                            alert("Oops");
                        }
                    },
                    error: function(jqXHR, textStatus, errorThrown)
                    {
                        alert(errorThrown);
                    }
                })
            },

            sendTestInfo: function (testData, callback) {
                $.ajax({
                    url : "http://"+ ip +":8080/KnowledgeTests/test/addTest",
                    type: "POST",
                    data : testData,
                    success:function(data, textStatus, jqXHR)
                    {
                        if (data.success === true) {
                            callback();
                        } else {
                            alert("test existed");
                        }
                    },
                    error: function(jqXHR, textStatus, errorThrown)
                    {
                        alert(errorThrown);
                    }
                })
            }
        };


        $(document).ready(function(){
            $("#saveTestInfoBtn").click(function () {
                var testData = saveTestInfo();

                if (testData){
                    AJAX.sendTestInfo(testData, function () {
                        confirmTestInfo();
                    });
                }
            });




          $("#divQuestionForm").on("click", "#nextQuestionBtn",function () {
              var question = getQuestion();

              if (question){
                  AJAX.sendQuestion(question, function () {
                      $("#divQuestionForm").empty();
                      createOrClearQuestionForm();
                  })
              }
          });

            $("#divQuestionForm").on("click", "#confirmBtn",function () {
                var question = getQuestion();

                if (question) {
                    AJAX.sendQuestion(question, function () {
                        window.location = "http://"+ ip +":8080/KnowledgeTests";
                    })
                } else if (!confirm('do you want to save current item?')) {
                    if (questionNumber > 1){
                        window.location = "http://"+ ip +":8080/KnowledgeTests";
                    } else {
                        alert("there is no questions");
                    }
                }
            });
        });



    </script>


    <script>

        var testName;

        function saveTestInfo() {
            testName = document.getElementById("testName").value;
            var testDescription = document.getElementById("testDescription").value;

            if(isNullOrEmptySpace(testName) || isNullOrEmptySpace(testDescription)){
                alert("Check input, should not be empty!!");
                return;
            }

            return {
                testName: testName,
                testDescription : testDescription
            }
        }

        function confirmTestInfo() {
            document.getElementById("testName").readOnly  = true;
            document.getElementById("testDescription").readOnly  = true;
            document.getElementById("saveTestInfoBtn").disabled  = true;

            createOrClearQuestionForm();
        }






        var questionNumber = 1;

        var questions = [];

        function getQuestion() {
            var questionName = document.getElementById("nameQuestion").value;

            if (isNullOrEmptySpace(questionName)) {
                alert("Empty question!!");
                return null;
            }

            var answers = [];

            for (var i = 0; i < limit; i++) {
                var elementAnswerText = document.getElementById("answer" + (i + 1));
                var elementAnswerRadio = document.getElementById("radio" + (i + 1));

                if (elementAnswerText) {

                    var currAnswerValue = elementAnswerText.value;

                    if (isNullOrEmptySpace(currAnswerValue)) {
                        alert("Some of your answers is empty !");
                        return null;
                    }

                    var isRightAnswer = elementAnswerRadio && elementAnswerRadio.checked === true;

                    answers.push({
                        isRightAnswer: isRightAnswer,
                        answer: currAnswerValue
                    });
                }
            }

            var currQuestion = {
                questionNumber: questionNumber,
                questionName: questionName,
                answers: answers
            };

            questions.push(currQuestion);

            questionNumber++;

            currQuestion.answers = JSON.stringify(currQuestion.answers);

            currQuestion.testName = testName;

            return currQuestion;
        }

    </script>

</head>
<body>

    <div class="container">
        <jsp:include page="dlc/manu.jsp">
            <jsp:param name="test" value="active"/>
        </jsp:include>








        <!--  TEST     -   name \ description  -->
        <div id="testForm" style="margin-top: 50px">
            <label style="margin-top: 20px" class="text-uppercase text-sm">Test name</label>
            <!--  test.Name   -->
            <input id="testName" name="name" type="text" class="form-control" placeholder="Name" required="" autofocus="">
            <label style="margin-top: 20px" class="text-uppercase text-sm">Description</label>
            <!--  test.Description   -->
            <textarea id="testDescription" name="description" rows="4" type="text" class="form-control" placeholder="Description" required=""></textarea>

            <button id="saveTestInfoBtn" onClick="saveTestInfo();" type="button" class="btn btn-success">Save</button>
        </div>









        <div id="divQuestionForm">


        </div>





    </div>

</body>
</html>
