package ru.homework.homework2_2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Genre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 10)
    private List<BookEntity> books;

    public GenreEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenreEntity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GenreEntity{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
