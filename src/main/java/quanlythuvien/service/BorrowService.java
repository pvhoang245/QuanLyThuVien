package quanlythuvien.service;

import quanlythuvien.dto.BookBorrowDto;
import quanlythuvien.model.Book;
import quanlythuvien.model.BookBorrow;

import java.util.List;

public interface BorrowService {

    BookBorrow add(BookBorrowDto p);

    List<Book> findReturnBook(String readerId);

    BookBorrow update(BookBorrowDto p);

    BookBorrow getByUserReaderId(BookBorrowDto p);

    List<Book> notiBorrowBook(List<String> p);
}
