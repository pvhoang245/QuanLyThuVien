package quanlythuvien.service;

import quanlythuvien.model.ReaderCountDto;
import quanlythuvien.model.Book;

import java.util.List;

public interface BookService {
    Book saveBook(Book book);
    Book findBookById(String id);
    Book checkBook(String id);
    List<Book> getAllBooks();
    Book deleteBook(String id);
    List<Book> search(String content);
    List<String> checkNumberBook(List<String> list);

    List<Book> reportByBorrow();

    List<ReaderCountDto> reportByReader();
}
