package ru.homework.homework2_2.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.homework.homework2_2.domain.AuthorEntity;
import ru.homework.homework2_2.domain.GenreEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@Import({GenreDaoImpl.class})
class GenreDaoTest {
    @Autowired
    private GenreDaoImpl genreDao;

    @Autowired
    private TestEntityManager em;

    private GenreEntity genre;

    @BeforeEach
    void setUp() {
        genre = new GenreEntity(1L, "Pushkin A.S.");
    }

    @Test
    void testSaveGenre() {
        genre = genreDao.save("Test genre");

        assertThat(genre.getId()).isPositive();
    }

    @Test
    void testFindById() {
        Optional<GenreEntity> genreEntity = genreDao.findById(1L);

        assertTrue(genreEntity.isPresent());
    }

    @Test
    void testFindAll() {
        List<GenreEntity> resultList = genreDao.findAll();
        assertFalse(resultList.isEmpty());
    }

    @Test
    void testUpdate() {
        GenreEntity result = genreDao.update(new GenreEntity(1L, "test Genre"));
        assertEquals("test Genre", result.getName());
    }

    @Test
    void testDelete() {
        genreDao.delete(1L);
        Optional<GenreEntity> checkingGenre = genreDao.findById(1L);
        assertFalse(checkingGenre.isPresent());
    }
}
