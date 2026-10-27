package ro.uvt.commands;

import ro.uvt.Models.Book;
import ro.uvt.Services.BookService;

public class CreateBookCommand implements Command<Book> {
    private final BookService bookService;
    private final Book book;

    public CreateBookCommand(BookService bookService, Book book) {
        this.bookService = bookService;
        this.book = book;
    }

    @Override
    public Book execute() {
        return bookService.create(book);
    }
}
