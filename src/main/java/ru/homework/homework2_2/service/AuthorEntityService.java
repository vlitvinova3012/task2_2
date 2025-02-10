package ru.homework.homework2_2.service;

import ru.homework.homework2_2.domain.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorEntityService {
    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findById(Long id);

    AuthorEntity save(String name);

    void deleteById(Long id);
}
