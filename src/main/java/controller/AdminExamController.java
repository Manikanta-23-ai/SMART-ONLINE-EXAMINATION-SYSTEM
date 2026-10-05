package com.exam.onine.controller;

import com.exam.onine.service.ExamService;
import com.exam.onine.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/exams")
public class AdminExamController {

    private final ExamService examService;
    private final SubjectService subjectService;

    public AdminExamController(
            ExamService examService,
            SubjectService subjectService) {

        this.examService = examService;
        this.subjectService = subjectService;
    }

    @GetMapping
    public String exams(Model model) {

        model.addAttribute(
                "exams",
                examService.getAllExams()
        );

        return "admin/exams";
    }

    @GetMapping("/new")
    public String createExamForm(Model model) {

        model.addAttribute(
                "subjects",
                subjectService.getAllSubjects()
        );

        return "admin/create-exam";
    }

    @PostMapping
    public String createExam(
            @RequestParam String title,
            @RequestParam Long subjectId,
            @RequestParam int durationMinutes,
            @RequestParam double totalMarks,
            @RequestParam(defaultValue = "false") boolean active) {

        examService.createExam(
                title,
                subjectId,
                durationMinutes,
                totalMarks,
                active
        );

        return "redirect:/admin/exams";
    }

    @PostMapping("/delete/{id}")
    public String deleteExam(
            @PathVariable Long id) {

        examService.deleteExam(id);

        return "redirect:/admin/exams";
    }
}