package com.example.demo.student;

import com.example.demo.course.Course;
import com.example.demo.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/students/{id}")
    public Student getById(@PathVariable("id") Long id) {
        return studentService.get(id);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAll();
    }

    @PostMapping("/students")
    public void addStudent(@RequestBody Student student) {
        studentService.add(student);
    }

    @DeleteMapping("/students/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        studentService.delete(id);
    }

    @PutMapping("/students/{id}")
    public void updateById(@PathVariable("id") Long id, @RequestBody Student student) {
        studentService.update(id, student);
    }

    @PutMapping("/students/{id}/enroll")
    public void enroll(@PathVariable("id") Long id, @RequestBody Course course) {
        List<Course> courses = courseService.getAll();
        for (int index = 0; index < courses.size(); index++) {
            if (courses.get(index).getCourse().equalsIgnoreCase(course.getCourse())) {
                studentService.enroll(id, courses.get(index));
                return;
            }
        }

        throw new IllegalStateException(
                "Course not available"
        );
    }

    @PutMapping("/students/{id}/drop")
    public void drop(@PathVariable("id") Long id) {
        studentService.drop(id);
    }
}
