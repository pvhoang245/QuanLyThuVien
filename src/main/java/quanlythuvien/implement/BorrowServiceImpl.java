package quanlythuvien.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quanlythuvien.dto.BookBorrowDto;
import quanlythuvien.model.Book;
import quanlythuvien.model.BookBorrow;
import quanlythuvien.repository.BookRepository;
import quanlythuvien.repository.BorrowRepository;
import quanlythuvien.service.BookService;
import quanlythuvien.service.BorrowService;
import quanlythuvien.service.ReaderService;
import quanlythuvien.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReaderService readerService;

    @Override
    public BookBorrow add(BookBorrowDto p) {
        List<String> list = p.getBook();
        for (String id: list) {
            Book book = bookService.findBookById(id);
            book.setTotalNumber(book.getTotalNumber()-1);
            book.setBorrowNumber(book.getBorrowNumber()+1);
            bookRepository.save(book);
            BookBorrow bookBorrow = new BookBorrow();
            bookBorrow.setDateBorrow(p.getDateBorrow());
            bookBorrow.setStatus("Borrowing");
            bookBorrow.setBook(bookService.findBookById(id));
            bookBorrow.setReader(readerService.findReaderById(p.getReaderId()));
            bookBorrow.setUser(userService.findUserById(p.getUserId()));
            borrowRepository.save(bookBorrow);
        }
        return null;
    }

    @Override
    public List<Book> findReturnBook(String readerId) {
        return borrowRepository.findBookReturn(readerId);
    }

    @Override
    public BookBorrow update(BookBorrowDto p) {
        List<String> list = p.getBook();
        for (String id: list) {
            BookBorrow bookBorrow = borrowRepository.findToReturn(p.getReaderId(), id);
            BookBorrow bookBorrow1 = borrowRepository.getById(bookBorrow.getId());
            bookBorrow1.setStatus("Returned");
            System.out.println(bookBorrow1.getId());
            borrowRepository.save(bookBorrow1);
        }
        return null;
    }

    @Override
    public BookBorrow getByUserReaderId(BookBorrowDto p) {
        String readerId = p.getReaderId();
        String bookId = p.getStatus();
        return borrowRepository.findToReturn(readerId, bookId);
    }

    @Override
    public List<Book> notiBorrowBook(List<String> p) {
        List<Book> list = new ArrayList<>();
        for(String id: p) {
            list.add(bookService.findBookById(id));
        }
        return list;
    }
}
