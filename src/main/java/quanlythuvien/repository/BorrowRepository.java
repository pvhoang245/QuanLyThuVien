package quanlythuvien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quanlythuvien.model.ReaderCountDto;
import quanlythuvien.model.Book;
import quanlythuvien.model.BookBorrow;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<BookBorrow, Integer> {
    @Query("SELECT DISTINCT b.book FROM BookBorrow b WHERE b.reader.id = :id AND b.status = 'Borrowing'")
    List<Book> findBookReturn(String id);
    @Query("SELECT b FROM BookBorrow b WHERE b.reader.id = :readerId AND b.book.id = :bookId ORDER BY b.id DESC LIMIT 1")
    BookBorrow findToReturn(String readerId, String bookId);
    @Query("SELECT new quanlythuvien.model.ReaderCountDto(b.reader, COUNT(b.reader.id)) FROM BookBorrow b group by b.reader.id order by COUNT(b.reader.id) desc")
    List<ReaderCountDto> reportByReader();
}
