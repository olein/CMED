package com.cmed.dto;

public class FormData {
    private int id;
    private String name;
    private long age;
    private String movie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "FormData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", movie='" + movie + '\'' +
                '}';
    }
}
