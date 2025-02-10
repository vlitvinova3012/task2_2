package ru.homework.homework2_2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.homework2_2.dao.GenreDao;
import ru.homework.homework2_2.domain.GenreEntity;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreEntityServiceImpl implements GenreEntityService {
    private final GenreDao dao;

    @Transactional(readOnly = true)
    @Override
    public List<GenreEntity> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<GenreEntity> findById(Long id) {
        return dao.findById(id);
    }

    @Transactional
    @Override
    public GenreEntity save(String genre) {
        return dao.save(genre);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        dao.delete(id);
    }
}
