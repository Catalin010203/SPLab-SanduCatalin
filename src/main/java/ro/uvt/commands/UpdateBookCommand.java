
package ro.uvt.commands;

import ro.uvt.Models.Book;

public class UpdateBookCommand implements Command<Book> {

    private final CommandContext context;
    private final Book book;
    private final Long id;

    public UpdateBookCommand(
            Book book,
            CommandContext context,
            Long id
    ) {
        this.book = book;
        this.context = context;
        this.id = id;
    }

    @Override
    public Book execute() {
        if (!context.getBooksRepository().existsById(id)) {
            return null;
        }

        book.setId(id);
        return context.getBooksRepository().save(book);
    }
}
