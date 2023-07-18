package com.example.demo.SampleCombine.model;

import com.example.demo.SampleCombine.model.Request.MemoRequest;
import com.example.demo.SampleCombine.model.Request.UserRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class User {
    @JsonProperty("sid")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private String pw;
    @JsonIgnore
    private HashMap<String, Memo> memos;

    public User(UserRequest request, HashMap<String, Memo> memos) {
        this.id = request.getId();
        this.name = request.getName();
        this.pw = request.getPw();
        this.memos = memos;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }
    public Collection<Memo> getMemos() {
        return memos.values();
    }
    public Memo getMemo(String id) {
        return memos.get(id);
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPw() {
        return pw;
    }
    public Memo createMemo(MemoRequest request) {
        Memo memo = new Memo();
        UUID uuid = UUID.randomUUID();
        memo.setId(uuid.toString());
        memo.setTitle(request.getTitle());
        memo.setDescription(request.getDescription());
        memos.put(memo.getId(), memo);
        return memo;
    }
    public void deleteMemo(String id) {
        memos.remove(id);
    }
    public Memo update(String id , MemoRequest request) {
        Memo memo = memos.get(id);
        memo.setTitle(request.getTitle());
        memo.setDescription(request.getDescription());
        memos.put(id, memo);
        return memo;
    }
}
