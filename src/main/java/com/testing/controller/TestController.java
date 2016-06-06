package com.testing.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.testing.model.Question;
import com.testing.model.Test;
import com.testing.model.User;
import com.testing.model.helpers.StringEncoder;
import com.testing.service.QuestionService;
import com.testing.service.TestService;
import com.testing.service.UserService;
import jdk.nashorn.internal.codegen.types.BooleanType;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Study on 29.05.2016.
 */

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addTest", method = RequestMethod.GET)
    public ModelAndView handleRequestAddTest() throws Exception {
        ModelAndView model = new ModelAndView();
        model.setViewName("addTest");

        return model;
    }






    @RequestMapping(value = "/addTest", method = RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public String handleRequestAddTestToDB(
            @RequestParam(value = "testName") String testName,
            @RequestParam(value = "testDescription") String testDescription
    ) throws Exception {

        boolean result = testService.addTest(testName, testDescription);

        return "{\"success\":" + result + "}";
    }







    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public String handleRequestAddQuestionToDB(
            @RequestParam(value = "testName") String testName,
            @RequestParam(value = "questionNumber") String questionNumber,
            @RequestParam(value = "questionName") String questionName,
            @RequestParam(value = "answers") String questionAnswers
    ) throws Exception {

        //boolean result = testService.addTest(testName, testDescription);


        int currectAnswerNumber = -1;
        ArrayList<String> answers = new ArrayList<>();

        try {
            JSONArray arr = new JSONArray(questionAnswers);

            boolean isOneAnswer = false;

            //loop through each object
            for (int i=0; i<arr.length(); i++){

                JSONObject jsonProductObject = arr.getJSONObject(i);
                String nameAnswer = jsonProductObject.getString("answer");
                boolean isRight = jsonProductObject.getBoolean("isRightAnswer");

                answers.add(i,nameAnswer);



                if (isRight && !isOneAnswer) {
                    isOneAnswer = true;
                    currectAnswerNumber = i;
                } else if (isRight && isOneAnswer){
                    throw new Exception("Invalid Json");
                }
            }

            if (currectAnswerNumber == -1) throw new Exception("There is no answer in JSON");

        } catch (Exception e) {
            return "{\"error\":" + "bad JSON" + "}";
        }


        Question question = new Question(questionName, answers, currectAnswerNumber);

        Test test = testService.getByName(testName);
        question = questionService.addQuestion(question);
        test.addQuestion(question);
        testService.updateTest(test);



        return "{\"success\":" + true + "}";
    }





    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView handleRequestGetTest() throws Exception {
        ModelAndView model = new ModelAndView();
        model.setViewName("getAllTests");

        ArrayList<ArrayList<Test>> resTest = new ArrayList<>();
        ArrayList<Test> temp = new ArrayList<>();

        for (Test test : testService.getAllTests()) {
            if (temp.size() == 3){
                resTest.add(temp);
                temp = new ArrayList<>();
            }

            temp.add(test);
        }

        if (temp.size() > 0) resTest.add(temp);


        model.addObject("testsTripleSet", resTest);
        return model;
    }



    @RequestMapping(value = "/{testId}/pass", method = RequestMethod.GET)
    public ModelAndView updateMovie(@PathVariable("testId") int id){

        ModelAndView model = new ModelAndView("passTest");
        Test test = testService.getById(id);

        String userNick = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByNick(userNick);

        userService.addTest(user, test, new Date(), -1);

        userService.updateUser(user);


        model.addObject("test", test);
        return model;
    }
}
