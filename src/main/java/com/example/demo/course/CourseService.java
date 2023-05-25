package com.example.demo.course;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course get(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Course does not exist"
                ));
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public void add(Course course) {
        courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Course does not exist"
                ));

        existingCourse.setCourse(course.getCourse());
    }
}
