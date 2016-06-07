package com.testing.model;

import com.testing.model.helpers.TestAssociation;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "test")
@NamedQueries({
        @NamedQuery(name = "Test.getAll", query = "SELECT t FROM  Test t"),
        @NamedQuery(name = "Test.findTestByName", query = "SELECT t FROM Test t WHERE name = :name")
})


public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "test")
    private List<TestAssociation> users;


    public Test(){}

    public Test(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Test(String name, String description, Set<Question> questions, List<TestAssociation> users) {
        this.name = name;
        this.description = description;
        this.questions = questions;
        this.users = users;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void addTestAssociation(TestAssociation testAssociation) { users.add(testAssociation); }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public List<TestAssociation> getUsers() {
        return users;
    }

    public void setUsers(List<TestAssociation> users) {
        this.users = users;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questions=" + questions +
                '}';
    }
}
