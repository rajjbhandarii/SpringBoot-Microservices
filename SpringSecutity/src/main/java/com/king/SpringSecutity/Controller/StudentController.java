package com.king.SpringSecutity.Controller;

import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.king.SpringSecutity.Model.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {

    private List<Student> students = List.of(
            new Student(1, "Alice", 85),
            new Student(2, "Bob", 90));

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return students;
    }

    @PostMapping("/student")
    public Student addStudent(@RequestBody Student entity) {
        students.add(new Student(entity.getId(), entity.getName(), entity.getMarks()));
        return entity;
    }

    @GetMapping("/csrf")
    public CsrfToken getString(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

}
