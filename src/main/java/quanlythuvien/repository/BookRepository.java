package quanlythuvien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quanlythuvien.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
