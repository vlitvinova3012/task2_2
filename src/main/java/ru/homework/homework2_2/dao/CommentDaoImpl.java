package ru.homework.homework2_2.dao;

import org.springframework.stereotype.Repository;
import ru.homework.homework2_2.domain.CommentEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentDaoImpl implements CommentDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public CommentEntity save(String comment) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment(comment);
        em.persist(commentEntity);
        return commentEntity;
    }

    @Override
    public Optional<CommentEntity> findById(long id) {
        return Optional.ofNullable(em.find(CommentEntity.class, id));
    }

    @Override
    public List<CommentEntity> findAll() {
        TypedQuery<CommentEntity> query = em.createQuery("select c from Comment c", CommentEntity.class);
        return query.getResultList();
    }

    @Override
    public CommentEntity update(CommentEntity comment) {
        return em.merge(comment);
    }

    @Override
    public void delete(Long id) {
        CommentEntity comment = em.find(CommentEntity.class, id);
        if (comment != null) {
            em.remove(comment);
        }
    }

    @Override
    public CommentEntity findByComment(String comment) {
        if (comment == null) {
            return null;
        }
        TypedQuery<CommentEntity> query = em.createQuery("select c from Comment c where c.comment = :comment", CommentEntity.class);
        query.setParameter("comment", comment);
        List<CommentEntity> results = query.getResultList();
        return results.isEmpty()
                ? save(comment)
                : results.get(0);
    }
}
