package quanlythuvien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import quanlythuvien.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query("SELECT p FROM Book p WHERE "
            + "LOWER(p.id) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(p.author) LIKE LOWER(CONCAT('%', :keyword, '%'))"
    )
    List<Book> search(@Param("keyword") String keyword);
    @Query("select p from Book p where p.id = :id")
    Book checkBook(String id);

    @Query("select p from Book p order by p.borrowNumber desc")
    List<Book> reportByBorrow();
}
