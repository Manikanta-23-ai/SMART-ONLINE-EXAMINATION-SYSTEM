package com.exam.onine.repository;

import com.exam.onine.entity.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionOptionRepository
        extends JpaRepository<QuestionOption, Long> {

    List<QuestionOption> findByQuestionId(Long questionId);
}