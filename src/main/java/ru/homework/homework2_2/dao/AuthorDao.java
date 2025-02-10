package ru.homework.homework2_2.dao;

import ru.homework.homework2_2.domain.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    AuthorEntity save(String name);

    Optional<AuthorEntity> findById(long id);

    List<AuthorEntity> findAll();

    AuthorEntity update(AuthorEntity author);
    AuthorEntity findByName(String name);

    void delete(Long id);
}
