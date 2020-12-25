package com.pavelzzzzz.task_control.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class UserPassword {

    @Id
    private Integer userId;

    @NotBlank
    private String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static UserPasswordBuilder builder(){
        return new UserPasswordBuilder();
    }


    public static final class UserPasswordBuilder {
        private UserPassword userPassword;

        private UserPasswordBuilder() {
            userPassword = new UserPassword();
        }

        public UserPasswordBuilder userId(Integer userId) {
            userPassword.setUserId(userId);
            return this;
        }

        public UserPasswordBuilder password(String password) {
            userPassword.setPassword(password);
            return this;
        }

        public UserPassword build() {
            return userPassword;
        }
    }
}
