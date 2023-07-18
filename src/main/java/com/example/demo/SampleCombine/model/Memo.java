package com.example.demo.SampleCombine.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Memo {
    @JsonProperty("mid")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
