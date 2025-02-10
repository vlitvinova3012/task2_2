package ru.homework.homework2_2.service;

import ru.homework.homework2_2.domain.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentEntityService {
    List<CommentEntity> findAll();
    Optional<CommentEntity> findById(Long id);
    CommentEntity save(String comment);
    void deleteById(Long id);
}
