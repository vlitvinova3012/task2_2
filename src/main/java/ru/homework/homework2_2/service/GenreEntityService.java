package ru.homework.homework2_2.service;

import ru.homework.homework2_2.domain.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface GenreEntityService {
    List<GenreEntity> findAll();

    Optional<GenreEntity> findById(Long id);

    GenreEntity save(String genre);

    void deleteById(Long id);
}
