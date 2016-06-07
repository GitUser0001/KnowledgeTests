package com.testing.model.helpers;


import com.testing.model.Test;
import com.testing.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "testAssociation")
@NamedQueries({
        @NamedQuery(name = "TestAssociation.getAll", query = "SELECT t FROM  TestAssociation t"),
        @NamedQuery(name = "TestAssociation.getByUserTestDate", query = "SELECT t FROM TestAssociation t WHERE user=:user AND test=:test AND passedIn=:passedIn")
})


//@IdClass(TestAssociationId.class)
public class TestAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //@Id
    //private int testId;

    @Column(name = "passed_in")
    private Date passedIn;

    @Column(name = "percent_correct")
    private int mark;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "appuserID", referencedColumnName = "id")
    @NotNull
    private User user;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "testID", referencedColumnName = "id")
    @NotNull
    private Test test;

    public void setPassedIn(Date passedIn) {
        this.passedIn = passedIn;
    }

    public int getMark() {
        return mark;
    }

    public Date getPassedIn() {
        return passedIn;
    }

    public User getUser() {
        return user;
    }

    public Test getTest() {
        return test;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
