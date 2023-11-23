package quanlythuvien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quanlythuvien.model.Reader;

import java.util.List;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, String> {
    @Query("SELECT p FROM Reader p WHERE "
            + "LOWER(p.id) LIKE LOWER(CONCAT('%', :content, '%')) OR "
            + "LOWER(p.name) LIKE LOWER(CONCAT('%', :content, '%')) OR "
            + "LOWER(p.phone) LIKE LOWER(CONCAT('%', :content, '%')) OR "
            + "LOWER(p.email) LIKE LOWER(CONCAT('%', :content, '%'))"
    )
    List<Reader> search(String content);
}
