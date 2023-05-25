package com.example.demo.course;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String course;

    public Course() {
    }

    public Course(String course) {
        this.course = course;
    }

    public Course(Long id, String course) {
        this.id = id;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
