package ru.homework.homework2_2.dao;

import org.springframework.stereotype.Repository;
import ru.homework.homework2_2.domain.GenreEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreDaoImpl implements GenreDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public GenreEntity save(String name) {
        GenreEntity genre = new GenreEntity();
        genre.setName(name);
        em.persist(genre);
        return genre;
    }

    @Override
    public Optional<GenreEntity> findById(long id) {
        return Optional.ofNullable(em.find(GenreEntity.class, id));
    }

    @Override
    public List<GenreEntity> findAll() {
        TypedQuery<GenreEntity> query = em.createQuery("select g from Genre g", GenreEntity.class);
        return query.getResultList();
    }

    @Override
    public GenreEntity update(GenreEntity genre) {
        return em.merge(genre);
    }

    @Override
    public void delete(Long id) {
        GenreEntity genre = em.find(GenreEntity.class, id);
        if (genre != null) {
            em.remove(genre);
        }
    }

    @Override
    public GenreEntity findByName(String name) {
        if (name == null) {
            return null;
        }
        TypedQuery<GenreEntity> query = em.createQuery("select g from Genre g where g.name = :name", GenreEntity.class);
        query.setParameter("name", name);
        List<GenreEntity> results = query.getResultList();
        return results.isEmpty()
                ? save(name)
                : results.get(0);
    }
}
