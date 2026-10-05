package com.exam.onine.controller;

import com.exam.onine.repository.ExamRepository;
import com.exam.onine.repository.ExamResultRepository;
import com.exam.onine.repository.SubjectRepository;
import com.exam.onine.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final ExamRepository examRepository;
    private final ExamResultRepository examResultRepository;

    public AuthController(
            UserRepository userRepository,
            SubjectRepository subjectRepository,
            ExamRepository examRepository,
            ExamResultRepository examResultRepository) {

        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.examRepository = examRepository;
        this.examResultRepository = examResultRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        long students = userRepository.findAll()
                .stream()
                .filter(user -> user.getRole().name().equals("STUDENT"))
                .count();

        long subjects = subjectRepository.count();
        long exams = examRepository.count();
        long results = examResultRepository.count();

        model.addAttribute("studentCount", students);
        model.addAttribute("subjectCount", subjects);
        model.addAttribute("examCount", exams);
        model.addAttribute("resultCount", results);

        return "dashboard";
    }
}