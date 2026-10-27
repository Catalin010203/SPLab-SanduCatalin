package ro.uvt.commands;

import ro.uvt.Models.Book;
import ro.uvt.Services.BookService;

public class DeleteBookCommand implements Command<Boolean> {
    private final BookService booksService;
    private final Long id;

    public DeleteBookCommand(BookService booksService, Long id) {
        this.booksService = booksService;
        this.id = id;
    }

    @Override
    public Boolean execute() {
        return booksService.delete(id);
    }
}
