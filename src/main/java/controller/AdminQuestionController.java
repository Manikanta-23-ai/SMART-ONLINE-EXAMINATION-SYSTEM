package com.exam.onine.controller;

import com.exam.onine.entity.Exam;
import com.exam.onine.entity.Question;
import com.exam.onine.entity.QuestionOption;
import com.exam.onine.service.ExamService;
import com.exam.onine.service.QuestionService;
import com.exam.onine.repository.QuestionOptionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/questions")
public class AdminQuestionController {

    private final QuestionService questionService;
    private final ExamService examService;
    private final QuestionOptionRepository questionOptionRepository;

    public AdminQuestionController(
            QuestionService questionService,
            ExamService examService,
            QuestionOptionRepository questionOptionRepository) {

        this.questionService = questionService;
        this.examService = examService;
        this.questionOptionRepository = questionOptionRepository;
    }

    @GetMapping
    public String questions(
            @RequestParam Long examId,
            Model model) {

        Exam exam = examService.getAllExams()
                .stream()
                .filter(e -> e.getId().equals(examId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Exam not found"));

        List<Question> questions =
                questionService.getQuestionsByExam(examId);

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

        model.addAttribute(
                "questions",
                questions
        );

        model.addAttribute(
                "questionOptions",
                questionOptions
        );

        return "admin/questions";
    }

    @GetMapping("/new")
    public String createQuestionForm(
            @RequestParam Long examId,
            Model model) {

        Exam exam = examService.getAllExams()
                .stream()
                .filter(e -> e.getId().equals(examId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Exam not found"));

        model.addAttribute(
                "exam",
                exam
        );

        return "admin/create-question";
    }

    @PostMapping
    public String createQuestion(
            @RequestParam Long examId,
            @RequestParam String questionText,
            @RequestParam double marks,
            @RequestParam double negativeMarks,
            @RequestParam String option1,
            @RequestParam String option2,
            @RequestParam String option3,
            @RequestParam String option4,
            @RequestParam int correctOption) {

        questionService.createQuestion(
                examId,
                questionText,
                marks,
                negativeMarks,
                option1,
                option2,
                option3,
                option4,
                correctOption
        );

        return "redirect:/admin/questions?examId=" + examId;
    }

    @PostMapping("/delete/{id}")
    public String deleteQuestion(
            @PathVariable Long id,
            @RequestParam Long examId) {

        questionService.deleteQuestion(id);

        return "redirect:/admin/questions?examId=" + examId;
    }
}