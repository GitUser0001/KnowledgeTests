package com.testing.model;

import javax.persistence.*;

/**
 * Created by Study on 24.05.2016.
 */

@Entity
@Table(name = "appgroup")
@NamedQuery(name = "Group.getAll", query = "SELECT g FROM Group g")

public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GroupID_seq")
    @SequenceGenerator(name = "GroupID_seq",
                sequenceName = "group_id_seq",
                allocationSize = 1,
                initialValue = 999999999)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    public Group(){}

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
