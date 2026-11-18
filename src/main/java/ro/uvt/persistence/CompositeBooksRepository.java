package ro.uvt.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.uvt.composite.Book;

@Repository
public interface CompositeBooksRepository
        extends JpaRepository<Book, Long> {
}