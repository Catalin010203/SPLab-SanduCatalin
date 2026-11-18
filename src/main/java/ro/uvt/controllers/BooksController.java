
package ro.uvt.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ro.uvt.commands.*;
import ro.uvt.Models.Book;
import ro.uvt.Services.BookService;
import ro.uvt.persistence.BooksRepositoryAdapter;
import ro.uvt.execution.AsyncRequestService;
import ro.uvt.execution.CommandExecutor;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final CommandContext commandContext;
    private final BookService booksService;
    private final AsyncRequestService asyncRequestService;
    private final CommandExecutor commandExecutor;

    public BooksController(
            BookService booksService,
            AsyncRequestService asyncRequestService,
            CommandExecutor commandExecutor,
            BooksRepositoryAdapter booksRepositoryAdapter
    ) {
        this.booksService = booksService;
        this.asyncRequestService = asyncRequestService;
        this.commandExecutor = commandExecutor;
        this.commandContext = new CommandContext(booksRepositoryAdapter);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return commandExecutor.executeSync(
                new GetAllBooksCommand(commandContext)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = commandExecutor.executeSync(
                new GetBookByIdCommand(commandContext, id)
        );

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createBook(
            @RequestBody Book book
    ) {
        String requestId = asyncRequestService.submit(
                new CreateBookCommand(commandContext, book)
        );

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                Map.of(
                        "requestId", requestId,
                        "status", "PENDING"
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            @RequestBody Book book
    ) {
        Book updatedBook = commandExecutor.executeSync(
                new UpdateBookCommand(book, commandContext, id)
        );

        if (updatedBook == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        boolean deleted = commandExecutor.executeSync(
                new DeleteBookCommand(commandContext, id)
        );

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
