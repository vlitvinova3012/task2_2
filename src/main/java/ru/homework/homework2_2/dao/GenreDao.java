package ru.homework.homework2_2.dao;

import ru.homework.homework2_2.domain.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    GenreEntity save(String genre);

    Optional<GenreEntity> findById(long id);

    List<GenreEntity> findAll();

    GenreEntity update(GenreEntity genre);

    GenreEntity findByName(String name);

    void delete(Long id);
}
