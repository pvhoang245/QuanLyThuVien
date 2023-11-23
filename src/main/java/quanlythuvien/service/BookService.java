package quanlythuvien.service;

import quanlythuvien.model.Book;

import java.util.List;

public interface BookService {
    Book saveBook(Book book);
    Book findBookById(String id);
    List<Book> getAllBooks();
    Book deleteBook(String id);
    List<Book> search(String content);
}
