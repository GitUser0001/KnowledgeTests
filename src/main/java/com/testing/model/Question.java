package com.testing.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "question")
@NamedQuery(name = "Question.getAll", query = "SELECT q FROM  Question q")
public class Question{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "answers", nullable = false, length = 700)
    private String answers;
    @Column(name = "answerNumber")
    private int correctAnswer;

    public Question(){}

    public Question(String name, ArrayList<String> answers, int correctAnswer) {
        this.name = name;
        setAnswers(answers);
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCorrect(String answer) {
        boolean a = answer.equals(getAnswers()[correctAnswer]);
        return answer.equals(getAnswers()[correctAnswer]);
    }

    public String[] getAnswers() {
        return answers.split(";");
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = "";
        for (int i = 0; i < answers.size(); i++) {
            this.answers += answers.get(i) + ";";
        }
    }

}
