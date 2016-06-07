var counter = 3;
var limit = 6;


function createOrClearQuestionForm(questionNumber) {
    
    document.getElementById('divQuestionForm').children = null;
    
    var newDiv = document.createElement('div');

    newDiv.innerHTML = "\<div>" +
                            "\<!--  Question     -   name \ answers   -->" +
                            "\<label style=\"margin-top: 50px\" class=\"text-uppercase text-sm\">Question " + questionNumber + "\</label>" +
                            "\<!--  Question.Name ------------------------------------------------------------------ -->" +
                            "\<textarea id=\"nameQuestion\" rows=\"2\" name=\"name\" type=\"text\" class=\"form-control\" placeholder=\"Name\" required=\"\" autofocus=\"\">\</textarea>" +
                            "\<div class=\"row\" style=\"width: 100%\">" +
                                "\<div id=\"dynamicInput\" class=\"col-lg-6\" style=\"width: inherit\">" +
                                "\<label style=\"margin-top: 20px\" class=\"text-uppercase text-sm\">Answer\</label>" +
                                "\<div class=\"input-group\">" +
                                "\<span class=\"input-group-addon\">" +
                                "\<!--  Question.checkbox  -->" +
                                "\<input id=\"radio1\" checked=\"checked\" name=\"answer\" value=\"1\" style=\"width: auto\" type=\"radio\" aria-label=\"...\">" +
                                "\</span>" +
                                "\<!--  Question.Answer  -->" +
                                "\<input id=\"answer1\" style=\"width: 85%\" type=\"text\" class=\"form-control\" aria-label=\"...\">" +
                                "\</div>" +
                                "\<label style=\"margin-top: 20px\" class=\"text-uppercase text-sm\">Answer\</label>" +
                                "\<div class=\"input-group\">" +
                                "\<span class=\"input-group-addon\">" +
                                "\<!--  Question.checkbox  -->" +
                                "\<input  id=\"radio2\" name=\"answer\" value=\"2\" style=\"width: auto\" type=\"radio\" aria-label=\"...\">" +
                                "\</span>" +
                                "\<!--  Question.Answer  -->" +
                                "\<input id=\"answer2\" style=\"width: 85%\" type=\"text\" class=\"form-control\" aria-label=\"...\">" +
                                "\</div>" +
                                "\</div>" +
                            "\</div>" +
                            "\<button onClick=\"addInput('dynamicInput');\" style=\"margin-top: 20px\" style=\"width: auto\" class=\"btn btn-info\" type=\"button\">Add new answer\</button>" +
                        "\</div>" +
                        "\<div align=\"center\" style=\"width: 100%\">" +
                        "\<button id=\"prevQuestionBtn\"    style=\"margin: 20px\" style=\"width: 30%\"  class=\"btn btn-lg btn-warning\" type=\"button\">Previous question\</button>" +
                        "\<button id=\"confirmBtn\"         style=\"margin: 20px\" style=\"width: 25%\"  class=\"btn btn-lg btn-success\" type=\"button\">Confirm\</button>" + 
                        "\<button id=\"nextQuestionBtn\"    style=\"margin: 20px\" style=\"width: 30%\" class=\"btn btn-lg btn-warning\" type=\"button\">Next question\</button>" +
                        "\</div>";
    
    
    document.getElementById('divQuestionForm').appendChild(newDiv);
}

function addInput(divName){
    if (counter == limit)  {
        var created = counter - 1;
        alert("You have reached the limit of adding " + created + " inputs");
    }
    else {
        var newdiv = document.createElement('div');       
        newdiv.id = "div" + counter;        
        newdiv.innerHTML = "\<label style=\"margin-top: 20px\" class=\"text-uppercase text-sm\">Answer</label>" +
                            "\<div class=\"input-group\">" +
                            "\<span class=\"input-group-addon\">" +
                            "\<input  id=\"radio" + counter + "\" value=\"" + counter + "\" name=\"answer\" style=\"width: auto\" type=\"radio\" aria-label=\"...\">" +
                            "\</span>" +
                            "\<input  id=\"answer" + counter + "\" style=\"width: 85%\" type=\"text\" class=\"form-control\" aria-label=\"...\">" +
                            "\<button onClick=\"removeElement(\'"+ newdiv.id + "\');\" style=\"width: 15%\" type=\"button\" class=\"btn btn-danger\">Remove\</button>" +
                            "\</div>";

        
        document.getElementById(divName).appendChild(newdiv);
        counter++;
    }
}

function removeElement(divName) {
    document.getElementById(divName).remove();
    counter--;
}

function isNullOrEmptySpace(str) {
    return (!str || str.replace(/\s/g,"") == "");
}

