package ru.homework.homework2_2.dao;

import org.springframework.stereotype.Repository;
import ru.homework.homework2_2.domain.AuthorEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDaoImpl implements AuthorDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public AuthorEntity save(String name) {
        AuthorEntity author = new AuthorEntity();
        author.setName(name);
        em.persist(author);
        return author;
    }

    @Override
    public Optional<AuthorEntity> findById(long id) {
        return Optional.ofNullable(em.find(AuthorEntity.class, id));
    }

    @Override
    public List<AuthorEntity> findAll() {
        TypedQuery<AuthorEntity> query = em.createQuery("select a from Author a", AuthorEntity.class);
        return query.getResultList();
    }

    @Override
    public AuthorEntity update(AuthorEntity author) {
        return em.merge(author);
    }

    @Override
    public void delete(Long id) {
        AuthorEntity author = em.find(AuthorEntity.class, id);
        if (author != null) {
            em.remove(author);
        }
    }

    @Override
    public AuthorEntity findByName(String name) {
        if (name == null) {
            return null;
        }
        TypedQuery<AuthorEntity> query = em.createQuery("select a from Author a where a.name = :name", AuthorEntity.class);
        query.setParameter("name", name);
        List<AuthorEntity> results = query.getResultList();
        return results.isEmpty()
                ? save(name)
                : results.get(0);
    }
}
