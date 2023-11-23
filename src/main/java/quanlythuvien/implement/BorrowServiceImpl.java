package quanlythuvien.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quanlythuvien.dto.BookBorrowDto;
import quanlythuvien.model.BookBorrow;
import quanlythuvien.repository.BorrowRepository;
import quanlythuvien.service.BookService;
import quanlythuvien.service.BorrowService;
import quanlythuvien.service.ReaderService;
import quanlythuvien.service.UserService;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

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
}
