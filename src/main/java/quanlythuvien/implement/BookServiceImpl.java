package quanlythuvien.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quanlythuvien.model.ReaderCountDto;
import quanlythuvien.model.Book;
import quanlythuvien.repository.BookRepository;
import quanlythuvien.repository.BorrowRepository;
import quanlythuvien.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findBookById(String id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Book checkBook(String id) {
        return bookRepository.checkBook(id);
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

    @Override
    public List<Book> search(String content) {
        return bookRepository.search(content);
    }

    @Override
    public List<String> checkNumberBook(List<String> list) {
        List<String> list1 = new ArrayList<>();
        for (String id: list) {
            int number = bookRepository.findById(id).get().getTotalNumber();
            if(number <= 0) {
                list1.add(id);
            }
        }
        return list1;
    }

    @Override
    public List<Book> reportByBorrow() {
        return bookRepository.reportByBorrow();
    }

    @Override
    public List<ReaderCountDto> reportByReader() {
        return borrowRepository.reportByReader();
    }


}
