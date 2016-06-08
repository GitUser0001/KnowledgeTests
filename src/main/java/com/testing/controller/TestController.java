package com.testing.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


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






    @RequestMapping(value = "/addTest", method = RequestMethod.POST , produces = "application/json; charset=\"utf-8\"")
    @ResponseBody
    public String handleRequestAddTestToDB(
            @RequestParam(value = "testName") String testName,
            @RequestParam(value = "testDescription") String testDescription
    ) throws Exception {

        boolean result = testService.addTest(testName, testDescription);

        return "{\"success\":" + result + "}";
    }







    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST , produces = "application/json; charset=\"utf-8\"")
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




    @RequestMapping(value = "/{testId}/questions", method = RequestMethod.POST, produces = "application/json; charset=\"utf-8\"")
    @ResponseBody
    public String handleRequestStartTest(@PathVariable("testId") int id){

        Test test = testService.getById(id);

        if (test != null) {
            JsonArray questionsJsonArray = new JsonArray();

            for (Question question : test.getQuestions()){

                JsonObject questionJsonObject = new JsonObject();
                questionJsonObject.addProperty("name", question.getName());


                JsonArray answersJsonArray = new JsonArray();
                for (String answer : question.getAnswers()){
                    answersJsonArray.add(answer);
                }

                questionJsonObject.add("answers", answersJsonArray);

                questionsJsonArray.add(questionJsonObject);
            }


            String userNick = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.getByNick(userNick);
            Date dateStart = new Date();
            userService.addTest(user, test, dateStart);


            JsonObject result = new JsonObject();

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String reportDate = df.format(dateStart);


            result.addProperty("start", reportDate);
            result.add("questions", questionsJsonArray);

            //String jsonString = new Gson().toJson(result);
            //byte[] utf8JsonString = jsonString.getBytes("UTF8");
            //responseToClient.write(utf8JsonString, 0, utf8JsonString.Length);


            return result.toString();
        }

        return null;
    }


    @RequestMapping(value = "/{testId}/pass", method = RequestMethod.GET)
    public ModelAndView handleRequestTest(@PathVariable("testId") int id){

        ModelAndView model = new ModelAndView("passTest");
        Test test = testService.getById(id);

        model.addObject("test", test);

        return model;
    }

    @RequestMapping(value = "/{testId}/pass", method = RequestMethod.POST, produces = "application/json; charset=\"utf-8\"")
    @ResponseBody
    public String passTest(@PathVariable("testId") int id,
                           @RequestParam(value = "dateTime") String dateTime,
                           @RequestParam(value = "usersAnswers") String usersAnswers){


        try {

            Test test = testService.getById(id);

            Gson gson = new Gson();
            Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType();
            Map<String,String> map = gson.fromJson(usersAnswers, stringStringMap);

            int correctAnswers = 0;

            for(Question question : test.getQuestions()) {
                if(question.isCorrect(map.get(question.getName()))){
                    correctAnswers++;
                }
            }

            int mark = (correctAnswers > 0) ? correctAnswers*100/test.getQuestions().size() : 0;

            String userNick = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.getByNick(userNick);
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(dateTime);

            userService.setTestMark(user,test, date, mark);

            return "{\"mark\":" + mark + "}";


        } catch (Exception e) {
            return null;
        }


    }


    @RequestMapping(value = "/{testId}/edit", method = RequestMethod.GET)
    public ModelAndView handleRequestTestEdit(@PathVariable("testId") int id){

        ModelAndView model = new ModelAndView("editTest");
        Test test = testService.getById(id);

        model.addObject("test", test);

        return model;
    }

    @RequestMapping(value = "/{testId}/edit", method = RequestMethod.POST)
    public String testEdit(@ModelAttribute("test") @Validated Test test, BindingResult result){

        if (result.hasErrors()) {
            return "redirect:/error";
        }
        else {
            Test testFromDB = testService.getById(test.getId());
            testFromDB.setName(test.getName());
            testFromDB.setDescription(test.getDescription());

            testService.updateTest(testFromDB);

            return "redirect:/test/all";
        }

    }



    @RequestMapping(value = "/{testId}/delete", method = RequestMethod.GET)
    public String handleRequestTestDelete(@PathVariable("testId") int id){

        testService.deleteTest(id);

        return "redirect:/test/all";
    }


}
