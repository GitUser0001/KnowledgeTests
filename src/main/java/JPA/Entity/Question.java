package JPA.Entity;

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
    @Column(name = "answers", nullable = false)
    private String answers;
    @Column(name = "answerNumber")
    private int correctAnswer;


    public Question(String name, String answers, int correctAnswer) {
        this.name = name;
        this.answers = answers;
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

    public boolean isCorrect(Integer answerNumber) {
        return answerNumber.equals(correctAnswer);
    }

    public String[] getAnswers() {
        return answers.split(";");
    }

    public void setAnswers(String[] answers) {
        this.answers = "";
        for (int i = 0; i < answers.length; i++) {
            this.answers += answers[i] + ";";
        }
    }

}
