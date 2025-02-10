package ru.homework.homework2_2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.homework.homework2_2.dao.CommentDao;
import ru.homework.homework2_2.domain.CommentEntity;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentEntityServiceImpl implements CommentEntityService{
    private final CommentDao dao;

    @Transactional(readOnly = true)
    @Override
    public List<CommentEntity> findAll() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<CommentEntity> findById(Long id) {
        return dao.findById(id);
    }

    @Transactional
    @Override
    public CommentEntity save(String comment) {
        return dao.save(comment);
    }
    @Override
    public void deleteById(Long id) {
        dao.delete(id);
    }
}
