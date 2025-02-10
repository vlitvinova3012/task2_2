package ru.homework.homework2_2.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.homework.homework2_2.domain.AuthorEntity;
import ru.homework.homework2_2.domain.BookEntity;
import ru.homework.homework2_2.domain.CommentEntity;
import ru.homework.homework2_2.domain.GenreEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {
    private final GenreDao genreDao;
    private final AuthorDao authorDao;
    private final CommentDao commentDao;
    @PersistenceContext
    private EntityManager em;

    @Override
    public BookEntity save(String title, String genre, String author, String comment) {
        GenreEntity genreEntity = genreDao.findByName(genre);
        AuthorEntity authorEntity = authorDao.findByName(author);
        CommentEntity commentEntity = commentDao.findByComment(comment);
        BookEntity book = BookEntity.builder()
                .title(title)
                .genre(genreEntity)
                .author(authorEntity)
                .comment(commentEntity)
                .build();
        return em.merge(book);
    }

    @Override
    public Optional<BookEntity> findById(long id) {
        return Optional.ofNullable(em.find(BookEntity.class, id));
    }

    @Override
    public List<BookEntity> findAll() {
        TypedQuery<BookEntity> query = em.createQuery("select b from Book b", BookEntity.class);
        return query.getResultList();
    }

    @Override
    public BookEntity update(String title, String genre, String author, String comment) {
            BookEntity bookFromDb = findByTitle(title);
            updateGenre(genre, bookFromDb);
            updateAuthor(author, bookFromDb);
            updateComment(comment, bookFromDb);
            return em.merge(bookFromDb);
    }

    private void updateComment(String comment, BookEntity bookFromDb) {
        if (comment == null) {
            return;
        }
        String commentFromBook = Optional.ofNullable(bookFromDb.getComment()).map(CommentEntity::getComment).orElse(null);
        CommentEntity commentFromDb = commentDao.findByComment(comment);
        if (!Objects.equals(commentFromDb.getComment(), commentFromBook)) {
            bookFromDb.setComment(commentFromDb);
        }
    }

    private void updateAuthor(String author, BookEntity bookFromDb) {
        if (author == null) {
            return;
        }
        String authorFromBook = Optional.ofNullable(bookFromDb.getAuthor()).map(AuthorEntity::getName).orElse(null);
        AuthorEntity authorFromDb = authorDao.findByName(author);
        if (!Objects.equals(authorFromDb.getName(), authorFromBook)) {
            bookFromDb.setAuthor(authorFromDb);
        }
    }

    private void updateGenre(String genre, BookEntity bookFromDb) {
        if (genre == null) {
            return;
        }
        String genreFromBook = Optional.ofNullable(bookFromDb.getGenre()).map(GenreEntity::getName).orElse(null);
        GenreEntity genreFromDb = genreDao.findByName(genre);
        if (!Objects.equals(genreFromDb.getName(), genreFromBook)) {
            bookFromDb.setGenre(genreFromDb);
        }
    }

    @Override
    public void delete(Long id) {
        BookEntity book = em.find(BookEntity.class, id);
        if (book != null) {
            em.remove(book);
        }
    }

    @Override
    public BookEntity findByTitle(String title) {
        if (title == null) {
            return null;
        }
        TypedQuery<BookEntity> query = em.createQuery("select b from Book b where b.title = :title", BookEntity.class);
        query.setParameter("title", title);
        List<BookEntity> results = query.getResultList();
        return results.isEmpty()
                ? crateNewBookEntity(title)
                : results.get(0);
    }

    private BookEntity crateNewBookEntity(String title) {
        BookEntity book = new BookEntity();
        book.setTitle(title);
        em.persist(book);
        return book;
    }
}
