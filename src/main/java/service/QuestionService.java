package com.exam.onine.service;

import com.exam.onine.entity.Exam;
import com.exam.onine.entity.Question;
import com.exam.onine.entity.QuestionOption;
import com.exam.onine.repository.ExamRepository;
import com.exam.onine.repository.QuestionOptionRepository;
import com.exam.onine.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionOptionRepository questionOptionRepository;
    private final ExamRepository examRepository;

    public QuestionService(
            QuestionRepository questionRepository,
            QuestionOptionRepository questionOptionRepository,
            ExamRepository examRepository) {

        this.questionRepository = questionRepository;
        this.questionOptionRepository = questionOptionRepository;
        this.examRepository = examRepository;
    }

    public Question createQuestion(
            Long examId,
            String questionText,
            double marks,
            double negativeMarks,
            String option1,
            String option2,
            String option3,
            String option4,
            int correctOption) {

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Exam not found"));

        if (questionText == null || questionText.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Question text is required");
        }

        if (marks <= 0) {
            throw new IllegalArgumentException(
                    "Marks must be greater than zero");
        }

        if (correctOption < 1 || correctOption > 4) {
            throw new IllegalArgumentException(
                    "Correct option must be between 1 and 4");
        }

        Question question = new Question();

        question.setExam(exam);
        question.setQuestionText(questionText.trim());
        question.setMarks(marks);
        question.setNegativeMarks(negativeMarks);

        Question savedQuestion =
                questionRepository.save(question);

        createOption(savedQuestion, option1, correctOption == 1);
        createOption(savedQuestion, option2, correctOption == 2);
        createOption(savedQuestion, option3, correctOption == 3);
        createOption(savedQuestion, option4, correctOption == 4);

        return savedQuestion;
    }

    private void createOption(
            Question question,
            String optionText,
            boolean correct) {

        QuestionOption option = new QuestionOption();

        option.setQuestion(question);
        option.setOptionText(optionText);
        option.setCorrect(correct);

        questionOptionRepository.save(option);
    }

    public List<Question> getQuestionsByExam(Long examId) {

        return questionRepository.findByExamId(examId);
    }

    public void deleteQuestion(Long id) {

        List<QuestionOption> options =
                questionOptionRepository.findAll()
                        .stream()
                        .filter(option ->
                                option.getQuestion()
                                        .getId()
                                        .equals(id))
                        .toList();

        questionOptionRepository.deleteAll(options);

        questionRepository.deleteById(id);
    }
}