package ro.uvt.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uvt.Models.Author;
import ro.uvt.Services.CompositeBookService;
import ro.uvt.composite.Book;
import ro.uvt.composite.Paragraph;
import ro.uvt.composite.Section;

import java.util.List;

@RestController
@RequestMapping("/composite-books")
public class CompositeBooksController {

    private final CompositeBookService service;

    public CompositeBooksController(CompositeBookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Long> create(
            @RequestBody CompositeBookRequest request
    ) {
        Book book = new Book(request.title());
        book.addAuthor(new Author(
                request.authorName(),
                request.authorSurname()
        ));

        Section section = new Section(request.chapterTitle());
        section.add(new Paragraph(request.paragraphText()));
        book.addContent(section);

        Book savedBook = service.save(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedBook.getId());
    }

    public record CompositeBookRequest(
            String title,
            String authorName,
            String authorSurname,
            String chapterTitle,
            String paragraphText
    ) {
    }

    @GetMapping
    public List<Book> getAllCompositeBooks() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getCompositeBookById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompositeBook(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCompositeBook(
            @PathVariable Long id,
            @RequestBody CompositeBookRequest request
    ) {
        Book book = service.findById(id).orElse(null);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        book.setTitle(request.title());

        book.getAuthors().clear();
        book.addAuthor(new Author(request.authorName(), request.authorSurname()));

        book.getChildren().clear();
        Section section = new Section(request.chapterTitle());
        section.add(new Paragraph(request.paragraphText()));
        book.addContent(section);

        Book savedBook = service.save(book);
        return ResponseEntity.ok(savedBook.getId());
    }
}