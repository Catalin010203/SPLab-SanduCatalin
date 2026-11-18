
package ro.uvt.commands;

import ro.uvt.Models.Book;
import ro.uvt.persistence.CrudRepository;

public class CommandContext {

    private final CrudRepository<Book, Long> booksRepository;

    public CommandContext(CrudRepository<Book, Long> booksRepository) {
        this.booksRepository = booksRepository;
    }

    public CrudRepository<Book, Long> getBooksRepository() {
        return booksRepository;
    }
}
