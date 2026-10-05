package com.exam.onine.controller;

import com.exam.onine.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/subjects")
public class AdminSubjectController {

    private final SubjectService subjectService;

    public AdminSubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public String subjects(Model model) {

        model.addAttribute(
                "subjects",
                subjectService.getAllSubjects()
        );

        return "admin/subjects";
    }

    @GetMapping("/new")
    public String newSubjectForm() {
        return "admin/create-subject";
    }

    @PostMapping
    public String createSubject(
            @RequestParam String name,
            @RequestParam(required = false) String description) {

        subjectService.createSubject(name, description);

        return "redirect:/admin/subjects";
    }

    @PostMapping("/delete/{id}")
    public String deleteSubject(@PathVariable Long id) {

        subjectService.deleteSubject(id);

        return "redirect:/admin/subjects";
    }
}
