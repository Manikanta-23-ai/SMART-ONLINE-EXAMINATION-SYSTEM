package com.exam.onine.service;

import com.exam.onine.entity.Exam;
import com.exam.onine.entity.ExamResult;
import com.exam.onine.entity.Question;
import com.exam.onine.entity.QuestionOption;
import com.exam.onine.entity.User;
import com.exam.onine.repository.ExamRepository;
import com.exam.onine.repository.ExamResultRepository;
import com.exam.onine.repository.QuestionOptionRepository;
import com.exam.onine.repository.QuestionRepository;
import com.exam.onine.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamResultService {

    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository questionOptionRepository;
    private final ExamResultRepository examResultRepository;
    private final UserRepository userRepository;

    public ExamResultService(
            ExamRepository examRepository,
            QuestionRepository questionRepository,
            QuestionOptionRepository questionOptionRepository,
            ExamResultRepository examResultRepository,
            UserRepository userRepository) {

        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
        this.questionOptionRepository = questionOptionRepository;
        this.examResultRepository = examResultRepository;
        this.userRepository = userRepository;
    }

    public ExamResult submitExam(
            Long examId,
            String username,
            Map<String, String> answers) {

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Exam not found"));

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "User not found"));

        List<Question> questions =
                questionRepository.findByExamId(examId);

        double score = 0;

        int correctAnswers = 0;
        int wrongAnswers = 0;
        int unanswered = 0;

        for (Question question : questions) {

            String answer =
                    answers.get("question_" + question.getId());

            if (answer == null || answer.isBlank()) {

                unanswered++;

                continue;
            }

            Long selectedOptionId =
                    Long.parseLong(answer);

            QuestionOption selectedOption =
                    questionOptionRepository
                            .findById(selectedOptionId)
                            .orElse(null);

            if (selectedOption != null
                    && selectedOption.isCorrect()) {

                score += question.getMarks();

                correctAnswers++;

            } else {

                score -= question.getNegativeMarks();

                wrongAnswers++;
            }
        }

        if (score < 0) {
            score = 0;
        }

        ExamResult result = new ExamResult();

        result.setExam(exam);
        result.setUser(user);
        result.setScore(score);
        result.setTotalMarks(exam.getTotalMarks());
        result.setCorrectAnswers(correctAnswers);
        result.setWrongAnswers(wrongAnswers);
        result.setUnanswered(unanswered);

        return examResultRepository.save(result);
    }

    public ExamResult getResult(Long id) {

        return examResultRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Result not found"));
    }
}