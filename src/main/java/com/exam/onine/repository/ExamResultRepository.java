package com.exam.onine.repository;

import com.exam.onine.entity.ExamResult;
import com.exam.onine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamResultRepository
        extends JpaRepository<ExamResult, Long> {

    List<ExamResult> findByUser(User user);

    List<ExamResult> findByExamId(Long examId);
}