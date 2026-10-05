package com.exam.onine.service;

import com.exam.onine.entity.Exam;
import com.exam.onine.entity.Subject;
import com.exam.onine.repository.ExamRepository;
import com.exam.onine.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final SubjectRepository subjectRepository;

    public ExamService(
            ExamRepository examRepository,
            SubjectRepository subjectRepository) {

        this.examRepository = examRepository;
        this.subjectRepository = subjectRepository;
    }

    public Exam createExam(
            String title,
            Long subjectId,
            int durationMinutes,
            double totalMarks,
            boolean active) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Exam title is required");
        }

        if (durationMinutes <= 0) {
            throw new IllegalArgumentException(
                    "Duration must be greater than zero"
            );
        }

        if (totalMarks <= 0) {
            throw new IllegalArgumentException(
                    "Total marks must be greater than zero"
            );
        }

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Subject not found"
                        ));

        Exam exam = new Exam();

        exam.setTitle(title.trim());
        exam.setSubject(subject);
        exam.setDurationMinutes(durationMinutes);
        exam.setTotalMarks(totalMarks);
        exam.setActive(active);

        return examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }
}