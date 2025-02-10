package ru.homework.homework2_2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.homework2_2.dao.BookDao;
import ru.homework.homework2_2.domain.BookEntity;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookEntityServiceImpl implements BookEntityService {
    private final BookDao dao;

    @Transactional(readOnly = true)
    @Override
    public List<BookEntity> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BookEntity> findById(Long id) {
        return dao.findById(id);
    }

    @Transactional
    @Override
    public BookEntity save(String title, String genre, String author, String comment) {
        return dao.save(title, genre, author, comment);
    }

    @Transactional
    @Override
    public BookEntity update(String title, String genre, String author, String comment) {
        return dao.update(title, genre, author, comment);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        dao.delete(id);
    }

}
