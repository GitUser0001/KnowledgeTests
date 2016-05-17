package JPA.Entity;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "test")
@NamedQuery(name = "Test.getAll", query = "SELECT t FROM  Test t")

public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<Question>();

    @OneToMany(mappedBy = "test")
    private List<TestAssociation> users;


    public Test(){}

    public Test(String name, Set<Question> questions, List<TestAssociation> users) {
        this.name = name;
        this.questions = questions;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public List<TestAssociation> getUsers() {
        return users;
    }

    public void setUsers(List<TestAssociation> users) {
        this.users = users;
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
