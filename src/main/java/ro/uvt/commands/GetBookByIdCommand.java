package ro.uvt.commands;

import ro.uvt.Models.Book;
import ro.uvt.Services.BookService;

public class GetBookByIdCommand implements Command<Book> {
    private final BookService bookService;
    private final Long id;

    public GetBookByIdCommand(BookService bookService, Long id) {
        this.bookService = bookService;
        this.id = id;
    }

    @Override
    public Book execute() {
        return bookService.findById(id);
    }

}
