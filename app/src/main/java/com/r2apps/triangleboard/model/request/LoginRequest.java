package com.r2apps.triangleboard.model.request;

/**
 * Created by user on 7/12/2016.
 */
public class LoginRequest {
    private String userEmail;
    private String password;

    public LoginRequest(String username, String password){
        this.userEmail = username;
        this.password = password;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
