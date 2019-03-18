package com.acc.soa.movies.app.Entities;

import javax.persistence.*;

@Entity
public class MovieMeta {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer tnmb_id;

    @ManyToOne
    private UserMeta owner;

    public MovieMeta(Integer tnmb_id, UserMeta owner) {
        this.tnmb_id = tnmb_id;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTnmb_id() {
        return tnmb_id;
    }

    public void setTnmb_id(Integer tnmb_id) {
        this.tnmb_id = tnmb_id;
    }

    public UserMeta getOwner() {
        return owner;
    }

    public void setOwner(UserMeta owner) {
        this.owner = owner;
    }
}
