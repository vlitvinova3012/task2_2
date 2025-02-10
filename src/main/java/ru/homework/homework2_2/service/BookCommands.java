package ru.homework.homework2_2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.homework.homework2_2.domain.BookEntity;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookEntityService service;

    @ShellMethod(value = "Get book by id", key = "get-by-id")
    public BookEntity getBookById(@ShellOption Long id) {
        return service.findById(id).orElse(null);
    }


    @ShellMethod(value = "Delete book", key = "delete")
    public String deleteBookById(@ShellOption Long id) {
        service.deleteById(id);
        return "Book delete successfully";
    }

    @ShellMethod(value = "List all books", key = "get-all")
    public List<BookEntity> listBooks() {
        return service.findAll();
    }

    @ShellMethod(value = "Add book", key = "add")
    public String addBook(@ShellOption String title,
                          @ShellOption String genre,
                          @ShellOption String author,
                          @ShellOption String comment) {
        service.save(title, genre, author, comment);
        return "Book added";
    }

    @ShellMethod(value = "Update book", key = "update")
    public String updateBook(@ShellOption String title,
                             @ShellOption String genre,
                             @ShellOption String author,
                             @ShellOption String comment) {

        service.update(title, genre, author, comment);
        return "Book updated successfully";
    }
}
