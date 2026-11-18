package ro.uvt.Services;

import org.springframework.stereotype.Service;
import ro.uvt.composite.Book;
import ro.uvt.persistence.CompositeBooksRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompositeBookService {

    private final CompositeBooksRepository repository;

    public CompositeBookService(CompositeBooksRepository repository) {
        this.repository = repository;
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}