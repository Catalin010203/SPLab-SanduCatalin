package ro.uvt.commands;

import ro.uvt.Models.Book;
import ro.uvt.Services.BookService;

public class UpdateBookCommand implements Command<Book> {
    private final Book book;
    private final BookService bookService;
    private final Long id;

    public UpdateBookCommand(Book book, BookService bookService, Long id) {
        this.book = book;
        this.bookService = bookService;
        this.id = id;
    }

    @Override
    public Book execute() {
        return bookService.update(id, book);
    }
}
