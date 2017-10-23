package com.cmg.dashboard.users.passwords;

import com.cmg.dashboard.users.User;
import com.cmg.dashboard.users.UserRegistrator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "passwords")
public class Password {

    @Id
    @GeneratedValue
    private long passwordId;

    @OneToOne
    @JoinColumn(name = "id")
    private User userId;

    @Column(name = "password")
    private String password;

    public Password() {}

    public Password(User user, String password){
        this.userId = user;
        this.password = password;
    }

    public long getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(long passwordId) {
        this.passwordId = passwordId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
