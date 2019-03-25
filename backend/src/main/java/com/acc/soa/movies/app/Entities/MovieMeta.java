package com.acc.soa.movies.app.Entities;

import javax.persistence.*;

@Entity
public class MovieMeta {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer tnmb;

    private String name;

    @ManyToOne
    private UserMeta owner;

    public MovieMeta(){

    }

    public MovieMeta(Integer tnmb, UserMeta owner, String name) {
        this.tnmb = tnmb;
        this.owner = owner;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTnmb() {
        return tnmb;
    }

    public void setTnmb(Integer tnmb) {
        this.tnmb = tnmb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserMeta getOwner() {
        return owner;
    }

    public void setOwner(UserMeta owner) {
        this.owner = owner;
    }
}
