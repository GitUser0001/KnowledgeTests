package com.testing.model;


import com.testing.model.helpers.TestAssociation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "appuser")
@NamedQueries({
        @NamedQuery(name = "User.getAll", query = "SELECT u FROM  User u"),
        @NamedQuery(name = "User.findUser", query = "SELECT u FROM User u WHERE nick = :nickName AND password = :password"),
        @NamedQuery(name = "User.findUserByNickName", query = "SELECT u FROM User u WHERE nick = :nickName")
})


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private int id;

    @Column(name = "nickName", unique = true, updatable = false)
    @NotNull
    //@NotEmpty
    private String nick;

    @Column(name = "firstName")
    @NotNull
    //@NotEmpty
    private String firstName;

    @Column(name = "lastName")
    @NotNull
    //@NotEmpty
    private String lastName;

    @Column(name = "password")
    @NotNull
    //@NotEmpty
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_in_group",
            joinColumns = @JoinColumn(name = "appuser", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "appgroup", referencedColumnName = "id"))
    private List<Group> groups;


    @OneToMany(mappedBy = "user")
    private List<TestAssociation> tests;

    public void addTest(Test test, Date date, int mark) {
        TestAssociation association = new TestAssociation();

        association.setUserId(this.getId());
        association.setTestId(test.getId());
        association.setMark(mark);
        association.setPassedIn(date);
        association.setUser(this);
        association.setTest(test);

        this.tests.add(association);
        test.getUsers().add(association);
    }





    public User(){}

    public User(String nick, String firstName, String lastName, String password) {
        this.nick = nick;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TestAssociation> getTests() {
        return tests;
    }

    public void setTests(List<TestAssociation> tests) {
        this.tests = tests;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Set<GrantedAuthority> getRolesGrantedAuthority() {
        Set<GrantedAuthority> roles = new HashSet();

        for (Group group : groups) {
            roles.add(new SimpleGrantedAuthority(group.getNameRole().name()));
        }

        return roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
