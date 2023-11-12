package quanlythuvien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quanlythuvien.model.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, String> {
}
