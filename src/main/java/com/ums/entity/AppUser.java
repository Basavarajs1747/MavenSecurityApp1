package com.ums.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private java.lang.Long id;

    @Column(name = "name", nullable = false, length = 50)
    private java.lang.String name;

    @Column(name = "user_name", nullable = false, unique = true, length = 100)
    private java.lang.String userName;

    @Column(name = "email_id", nullable = false, unique = true, length = 100)
    private java.lang.String emailId;

    @Column(name = "password", nullable = false, length = 400)
    private java.lang.String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password, String gensalt) {
        this.password = password;
    }
}