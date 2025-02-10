package ru.homework.homework2_2.service;

import ru.homework.homework2_2.domain.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookEntityService {
    List<BookEntity> findAll();

    Optional<BookEntity> findById(Long id);

    BookEntity save(String title, String genre, String author, String comment);

    void deleteById(Long id);

    BookEntity update(String title, String genre, String author, String comment);
}
