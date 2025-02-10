package ru.homework.homework2_2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 10)
    private List<BookEntity> books;

    public CommentEntity(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public CommentEntity(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", comment='" + comment +
                '}';
    }
}
