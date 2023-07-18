package com.example.demo.SampleCombine.model.Request;

public class UserRequest {
    private String id;
    private String name;
    private String pw;

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getPw() {
        return pw;
    }
}
