package com.exam.onine.service;

import com.exam.onine.entity.Subject;
import com.exam.onine.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject createSubject(String name, String description) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject name is required");
        }

        if (subjectRepository.findAll()
                .stream()
                .anyMatch(subject ->
                        subject.getName().equalsIgnoreCase(name.trim()))) {

            throw new IllegalArgumentException(
                    "Subject already exists"
            );
        }

        Subject subject = new Subject();

        subject.setName(name.trim());
        subject.setDescription(description);

        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Subject not found"
                        ));
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}