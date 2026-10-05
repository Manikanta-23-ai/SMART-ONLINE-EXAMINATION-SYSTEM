package com.exam.onine.controller;

import com.exam.onine.entity.ExamResult;
import com.exam.onine.entity.User;
import com.exam.onine.repository.ExamResultRepository;
import com.exam.onine.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StudentResultController {

    private final ExamResultRepository examResultRepository;
    private final UserRepository userRepository;

    public StudentResultController(
            ExamResultRepository examResultRepository,
            UserRepository userRepository) {

        this.examResultRepository = examResultRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/student/results")
    public String myResults(
            Authentication authentication,
            Model model) {

        String username = authentication.getName();

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "User not found"));

        List<ExamResult> results =
                examResultRepository.findByUser(user);

        model.addAttribute("results", results);

        return "student/results";
    }
}