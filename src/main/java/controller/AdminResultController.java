package com.exam.onine.controller;

import com.exam.onine.entity.ExamResult;
import com.exam.onine.repository.ExamResultRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/results")
public class AdminResultController {

    private final ExamResultRepository examResultRepository;

    public AdminResultController(
            ExamResultRepository examResultRepository) {

        this.examResultRepository = examResultRepository;
    }

    @GetMapping
    public String results(Model model) {

        List<ExamResult> results =
                examResultRepository.findAll();

        model.addAttribute("results", results);

        return "admin/results";
    }
}