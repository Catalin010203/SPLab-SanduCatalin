
package ro.uvt.Services;

import org.springframework.stereotype.Service;
import ro.uvt.Models.Book;
import ro.uvt.persistence.BooksRepositoryAdapter;
import ro.uvt.persistence.CrudRepository;

import java.util.List;

@Service
public class BookService {

    private final CrudRepository<Book, Long> booksRepository;

    public BookService(BooksRepositoryAdapter booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById(Long id) {
        return booksRepository.findById(id).orElse(null);
    }

    public Book create(Book book) {
        book.setId(null);
        return booksRepository.save(book);
    }

    public Book update(Long id, Book book) {
        if (!booksRepository.existsById(id)) {
            return null;
        }

        book.setId(id);
        return booksRepository.save(book);
    }

    public boolean delete(Long id) {
        if (!booksRepository.existsById(id)) {
            return false;
        }

        booksRepository.deleteById(id);
        return true;
    }
}
