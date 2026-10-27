package ro.uvt.commands;

import ro.uvt.Models.Book;
import ro.uvt.Services.BookService;

import java.util.List;

public class GetAllBooksCommand implements Command<List<Book>> {
    private final BookService bookService;

    public GetAllBooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public List<Book> execute() {
        return bookService.findAll();
    }

}
