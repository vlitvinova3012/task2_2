package ru.homework.homework2_2.dao;

import ru.homework.homework2_2.domain.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    BookEntity save(String title, String genre, String author, String comment);


    Optional<BookEntity> findById(long id);


    List<BookEntity> findAll();

    BookEntity update(String title, String genre, String author, String comment);

    void delete(Long id);

    BookEntity findByTitle(String title);
}
