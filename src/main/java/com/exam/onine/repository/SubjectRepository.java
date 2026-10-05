package com.exam.onine.repository;

import com.exam.onine.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository
        extends JpaRepository<Subject, Long> {
}