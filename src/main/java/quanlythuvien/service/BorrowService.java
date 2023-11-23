package quanlythuvien.service;

import quanlythuvien.dto.BookBorrowDto;
import quanlythuvien.model.BookBorrow;

public interface BorrowService {

    BookBorrow add(BookBorrowDto p);
}
