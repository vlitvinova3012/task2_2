package ru.homework.homework2_2.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.homework.homework2_2.domain.CommentEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({CommentDaoImpl.class})
class CommentDaoTest {
    @Autowired
    private CommentDaoImpl commentDao;

    @Autowired
    private TestEntityManager em;

    private CommentEntity comment;

    @BeforeEach
    void setUp() {
        comment = new CommentEntity(1L, "No publish");
    }

    @Test
    void testSaveComment() {
        comment = commentDao.save("Test comment");

        assertThat(comment.getId()).isPositive();
    }

    @Test
    void testFindById() {
        Optional<CommentEntity> commentEntity = commentDao.findById(1L);

        assertTrue(commentEntity.isPresent());
    }

    @Test
    void testFindAll() {
        List<CommentEntity> resultList = commentDao.findAll();
        assertFalse(resultList.isEmpty());
    }

    @Test
    void testUpdate() {
        CommentEntity result = commentDao.update(new CommentEntity(1L, "test Comment"));
        assertEquals("test Comment", result.getComment());
    }

    @Test
    void testDelete() {
        commentDao.delete(1L);
        Optional<CommentEntity> checkingComment = commentDao.findById(1L);
        assertFalse(checkingComment.isPresent());
    }
}
