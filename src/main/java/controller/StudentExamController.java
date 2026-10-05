package com.exam.onine.controller;

import com.exam.onine.entity.Exam;
import com.exam.onine.entity.ExamResult;
import com.exam.onine.entity.Question;
import com.exam.onine.entity.QuestionOption;
import com.exam.onine.repository.QuestionOptionRepository;
import com.exam.onine.service.ExamResultService;
import com.exam.onine.service.ExamService;
import com.exam.onine.service.QuestionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentExamController {

    private final ExamService examService;
    private final QuestionService questionService;
    private final QuestionOptionRepository questionOptionRepository;
    private final ExamResultService examResultService;

    public StudentExamController(
            ExamService examService,
            QuestionService questionService,
            QuestionOptionRepository questionOptionRepository,
            ExamResultService examResultService) {

        this.examService = examService;
        this.questionService = questionService;
        this.questionOptionRepository = questionOptionRepository;
        this.examResultService = examResultService;
    }

    @GetMapping("/exams")
    public String availableExams(Model model) {

        List<Exam> exams = examService.getAllExams()
                .stream()
                .filter(Exam::isActive)
                .toList();

        model.addAttribute("exams", exams);

        return "student/exams";
    }

    @GetMapping("/exam/{id}")
    public String takeExam(
            @PathVariable Long id,
            Model model) {

        Exam exam = examService.getAllExams()
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Exam not found"));

        List<Question> questions =
                questionService.getQuestionsByExam(id);

        Map<Long, List<QuestionOption>> questionOptions =
                new HashMap<>();

        for (Question question : questions) {

            questionOptions.put(
                    question.getId(),
                    questionOptionRepository
                            .findByQuestionId(question.getId())
            );
        }

        model.addAttribute("exam", exam);
        model.addAttribute("questions", questions);
        model.addAttribute(
                "questionOptions",
                questionOptions
        );

        return "student/take-exam";
    }

    @PostMapping("/exam/{id}/submit")
    public String submitExam(
            @PathVariable Long id,
            @RequestParam Map<String, String> answers,
            Authentication authentication) {

        String username = authentication.getName();

        ExamResult result =
                examResultService.submitExam(
                        id,
                        username,
                        answers
                );

        return "redirect:/student/result/" + result.getId();
    }

    @GetMapping("/result/{id}")
    public String result(
            @PathVariable Long id,
            Model model) {

        ExamResult result =
                examResultService.getResult(id);

        model.addAttribute(
                "result",
                result
        );

        return "student/result";
    }
}