package ru.homework.homework2_2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.homework2_2.dao.AuthorDao;
import ru.homework.homework2_2.domain.AuthorEntity;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorEntityServiceIml implements AuthorEntityService {
    private final AuthorDao dao;

    @Transactional(readOnly = true)
    @Override
    public List<AuthorEntity> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AuthorEntity> findById(Long id) {
        return dao.findById(id);
    }

    @Transactional
    @Override
    public AuthorEntity save(String name) {
        return dao.save(name);
    }

    @Override
    public void deleteById(Long id) {
        dao.delete(id);
    }
}
