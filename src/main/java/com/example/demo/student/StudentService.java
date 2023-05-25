package com.example.demo.student;

import com.example.demo.course.Course;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student get(Long Id) {
        return studentRepository.findById(Id)
                .orElseThrow(() -> new IllegalStateException(
                        "Student does not exist"
                ));
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public void add(Student student) {
        studentRepository.save(student);
    }

    public void delete(Long Id) {
        studentRepository.deleteById(Id);
    }

    @Transactional
    public void update(Long Id, Student student) {
        Student existingStudent = studentRepository.findById(Id)
                .orElseThrow(() -> new IllegalStateException(
                        "Student does not exist"
                ));

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
    }

    @Transactional
    public void enroll(Long Id, Course course) {
        Student existingStudent = studentRepository.findById(Id)
                .orElseThrow(() -> new IllegalStateException(
                        "Student does not exist"
                ));

        if (existingStudent.getCourse() == null) {
            existingStudent.setCourse(course);
        } else {
            throw new IllegalStateException(
                    "You are already enrolled"
            );
        }
    }

    @Transactional
    public void drop(Long Id) {
        Student existingStudent = studentRepository.findById(Id)
                .orElseThrow(() -> new IllegalStateException(
                        "Student does not exist"
                ));

        if (existingStudent.getCourse() == null) {
            throw new IllegalStateException(
                    "You are not enrolled"
            );
        } else {
            existingStudent.setCourse(null);
        }
    }
}
