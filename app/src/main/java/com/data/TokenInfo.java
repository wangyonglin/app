package com.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class TokenInfo {
    @JsonProperty("user")
    private String user;
    @JsonProperty("token")
    private String token;
//@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    public TokenInfo() {
    }

    public TokenInfo(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
