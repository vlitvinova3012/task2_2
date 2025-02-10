package ru.homework.homework2_2.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.homework.homework2_2.domain.AuthorEntity;
import ru.homework.homework2_2.domain.BookEntity;
import ru.homework.homework2_2.domain.CommentEntity;
import ru.homework.homework2_2.domain.GenreEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({BookDaoImpl.class, CommentDaoImpl.class, AuthorDaoImpl.class, GenreDaoImpl.class})
class BookDaoTest {
    @Autowired
    private BookDaoImpl bookDaoImpl;

    @Autowired
    private CommentDaoImpl commentDao;
    @Autowired
    private TestEntityManager em;

    private BookEntity book;

    @BeforeEach
    void setUp() {
        AuthorEntity author = new AuthorEntity(1L, "Pushkin A.S.");
        GenreEntity genre = new GenreEntity(1L, "Fairy tale");
        CommentEntity comment = new CommentEntity(1L, "For publish");
        book = new BookEntity(1L, "Anna Karenina", author, genre, comment);
    }

    @Test
    void testSaveBook() {
        book = bookDaoImpl.save("Anna Karenina", null, null, null);

        assertThat(book.getId()).isPositive();
    }

    @Test
    void testSaveBook2() {
        book.setId(null);
        book = bookDaoImpl.save("Anna Karenina", null, null, null);

        assertThat(book.getId()).isPositive();
    }

    @Test
    void testFindById() {
        Optional<BookEntity> bookEntity = bookDaoImpl.findById(1L);

        assertTrue(bookEntity.isPresent());
    }

    @Test
    void testFindAll() {
        List<BookEntity> resultList = bookDaoImpl.findAll();
        assertFalse(resultList.isEmpty());
    }

    @Test
    void testUpdate() {
        BookEntity resultBook = bookDaoImpl.update("No info", null, null, null);
        assertEquals("No info", resultBook.getTitle());
    }

    @Test
    void testUpdateWhenCommentAuthorGenreNotNull() {
        BookEntity resultBook = bookDaoImpl.update("No info", "Test genre", "test Author", "test comment");
        assertNotNull(resultBook.getAuthor());
    }

    @Test
    void testDelete() {
        bookDaoImpl.delete(1L);
        Optional<BookEntity> checkingBook = bookDaoImpl.findById(1L);
        assertFalse(checkingBook.isPresent());
    }
}
