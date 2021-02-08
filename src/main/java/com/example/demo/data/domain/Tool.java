package com.example.demo.data.domain;

public class Tool {
    private Integer id;

    private String name;

    private String description;

    private String imagekey;

    private String aikey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImagekey() {
        return imagekey;
    }

    public void setImagekey(String imagekey) {
        this.imagekey = imagekey == null ? null : imagekey.trim();
    }

    public String getAikey() {
        return aikey;
    }

    public void setAikey(String aikey) {
        this.aikey = aikey == null ? null : aikey.trim();
    }
}