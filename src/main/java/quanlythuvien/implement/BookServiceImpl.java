package quanlythuvien.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quanlythuvien.model.Book;
import quanlythuvien.repository.BookRepository;
import quanlythuvien.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findBookById(String id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book deleteBook(String id) {
        bookRepository.deleteById(id);
        return null;
    }


}
