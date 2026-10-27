
package ro.uvt.Services;

import org.springframework.stereotype.Service;
import ro.uvt.Models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {

    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public BookService() {
        Book first = new Book(
                nextId.getAndIncrement(),
                "Clean Code",
                "Robert C. Martin"
        );

        Book second = new Book(
                nextId.getAndIncrement(),
                "Design Patterns",
                "Gang of Four"
        );

        books.put(first.getId(), first);
        books.put(second.getId(), second);
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    public Book findById(Long id) {
        return books.get(id);
    }

    public Book create(Book book) {
        Long id = nextId.getAndIncrement();
        Book created = new Book(id, book.getTitle(), book.getAuthor());
        books.put(id, created);
        return created;
    }

    public Book update(Long id, Book book) {
        if (!books.containsKey(id)) {
            return null;
        }

        Book updated = new Book(id, book.getTitle(), book.getAuthor());
        books.put(id, updated);
        return updated;
    }

    public boolean delete(Long id) {
        return books.remove(id) != null;
    }
}
