package quanlythuvien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quanlythuvien.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {
}
