
package ro.uvt.commands;

import ro.uvt.Models.Book;

public class CreateBookCommand implements Command<Book> {

    private final CommandContext context;
    private final Book book;

    public CreateBookCommand(CommandContext context, Book book) {
        this.context = context;
        this.book = book;
    }

    @Override
    public Book execute() {
        book.setId(null);
        return context.getBooksRepository().save(book);
    }
}
