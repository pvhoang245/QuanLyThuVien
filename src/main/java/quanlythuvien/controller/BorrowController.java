package quanlythuvien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import quanlythuvien.dto.BookBorrowDto;
import quanlythuvien.dto.Keyword;
import quanlythuvien.model.BookBorrow;
import quanlythuvien.service.BookService;
import quanlythuvien.service.BorrowService;
import quanlythuvien.service.ReaderService;

@Controller
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private BookService bookService;

    @GetMapping("/bookborrow")
    public String borrow(Model model) {
        Keyword keyword = new Keyword();
        model.addAttribute("content", keyword);
        return "borrow_1";
    }

    @PostMapping("/bookborrow")
    public String resultReader(@ModelAttribute("content") Keyword keyword, Model model) {
        model.addAttribute("readers", readerService.searchReader(keyword.getName()));
        return "borrow_2";
    }

    @PostMapping("/bookborrow/add")
    @ResponseBody
    public ResponseEntity<BookBorrow> addBorrowBook(@RequestBody BookBorrowDto p){
        BookBorrow bookBorrow = borrowService.add(p);
        return new ResponseEntity<BookBorrow>(bookBorrow, HttpStatus.OK);
    }

    @GetMapping("/bookborrow/book/{readerId}")
    public String borrowSearchBook(@PathVariable String readerId, Model model) {
        model.addAttribute("reader", readerService.findReaderById(readerId));
        return "borrow_3";
    }

    @GetMapping("/bookreturn")
    public String returnSearchBook() {
        return "return_1";
    }



}
