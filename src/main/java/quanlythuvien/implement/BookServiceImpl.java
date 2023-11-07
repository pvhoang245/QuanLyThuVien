package quanlythuvien.implement;

import org.springframework.stereotype.Service;
import quanlythuvien.model.Book;
import quanlythuvien.repository.BookRepository;
import quanlythuvien.service.BookService;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        super();
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
