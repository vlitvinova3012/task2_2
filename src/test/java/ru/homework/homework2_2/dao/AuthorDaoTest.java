package ru.homework.homework2_2.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.homework.homework2_2.domain.AuthorEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({AuthorDaoImpl.class})
class AuthorDaoTest {
    @Autowired
    private AuthorDaoImpl authorDao;

    @Autowired
    private TestEntityManager em;

    private AuthorEntity author;

    @BeforeEach
    void setUp() {
        author = new AuthorEntity(1L, "Pushkin A.S.");
    }

    @Test
    void testSaveAuthor() {
        author = authorDao.save("Test author");

        assertThat(author.getId()).isPositive();
    }

    @Test
    void testFindById() {
        Optional<AuthorEntity> authorEntity = authorDao.findById(1L);

        assertTrue(authorEntity.isPresent());
    }

    @Test
    void testFindAll() {
        List<AuthorEntity> resultList = authorDao.findAll();
        assertFalse(resultList.isEmpty());
    }

    @Test
    void testUpdate() {
        AuthorEntity result = authorDao.update(new AuthorEntity(1L, "test Author"));
        assertEquals("test Author", result.getName());
    }

    @Test
    void testDelete() {
        authorDao.delete(1L);
        Optional<AuthorEntity> checkingAuthor = authorDao.findById(1L);
        assertFalse(checkingAuthor.isPresent());
    }
}
