package com.acc.soa.movies.app.Entities;


import javax.persistence.*;
import java.util.UUID;

@Entity
public class UserMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String token;

    public UserMeta() {

    }

    public UserMeta(String username, String password) {
        this.username = username;
        this.password = UserMeta.encodePassword(password);
        this.token = UUID.randomUUID().toString();
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
        this.password = UserMeta.encodePassword(password);
    }

    private static String encodePassword(String password) {
        //implement hashing algorithm
        return password;
    }

    public String getToken() {
        return token;
    }
}
