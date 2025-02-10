package ru.homework.homework2_2.dao;

import ru.homework.homework2_2.domain.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    CommentEntity save(String comment);

    Optional<CommentEntity> findById(long id);

    List<CommentEntity> findAll();

    CommentEntity update(CommentEntity comment);
    CommentEntity findByComment(String comment);

    void delete(Long id);
}
