package com.data;


import javakit.date.DateUtils;

import java.util.Date;

public class UserInfo {
    private String id;
    private String Email;
    private String Username;
    private String Password;
    private String Phone;
    private String Wechat;

    private String ActivationCode;
    private Date Register = DateUtils.CurrentDate();
    private Date Countdown =DateUtils.CurrentDate();
    private Boolean Status;

    public UserInfo() {
    }

    public UserInfo(String user, String password) {
        Username = user;
        Password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getWechat() {
        return Wechat;
    }

    public void setWechat(String wechat) {
        Wechat = wechat;
    }

    public String getActivationCode() {
        return ActivationCode;
    }

    public void setActivationCode(String activationCode) {
        ActivationCode = activationCode;
    }

    public Date getRegister() {
        return Register;
    }

    public void setRegister(Date register) {
        Register = register;
    }

    public Date getCountdown() {
        return Countdown;
    }

    public void setCountdown(Date countdown) {
        Countdown = countdown;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }


}
