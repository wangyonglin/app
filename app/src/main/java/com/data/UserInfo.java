package com.merlin.webflux.Entity;


import javakit.date.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Indexed;

import java.util.Date;
@Data
@AllArgsConstructor
@Document
public class UserInfo {
    @Id
    private String id;
  //  @Indexed(unique = true) // 注解属性username为索引，并且不能重复
    private String Email;
    private String Username;
    private String Password;
    private String Phone;
    private String Wechat;
    private String ActivationCode;
    @NonNull
    private Date Register = DateUtils.CurrentDate();
    @NonNull
    private Date Countdown =DateUtils.CurrentDate();
    private Boolean Status;

    public UserInfo() {
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
