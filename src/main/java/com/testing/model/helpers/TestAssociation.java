package com.testing.model.helpers;


import com.testing.model.Test;
import com.testing.model.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "testAssociation")
@NamedQuery(name = "TestAssociation.getAll", query = "SELECT t FROM  TestAssociation t")
@IdClass(TestAssociationId.class)
public class TestAssociation {

    @Id
    private int userId;
    @Id
    private int testId;

    @Column(name = "passed_in")
    private Date passedIn;

    @Column(name = "percent_correct")
    private int mark;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "appuserID", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "testID", referencedColumnName = "id")
    private Test test;


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setPassedIn(Date passedIn) {
        this.passedIn = passedIn;
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
