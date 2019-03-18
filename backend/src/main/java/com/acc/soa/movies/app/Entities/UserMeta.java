package com.acc.soa.movies.app.Entities;


import javax.persistence.*;

@Entity
public class UserMeta {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public UserMeta(){

    }

    public UserMeta(String username, String password) {
        this.username = username;
        //hashpassword
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        //hash password
        this.password = password;
    }

    private String encodePassword(String password){
        //implement later
        return password;
    }
}
