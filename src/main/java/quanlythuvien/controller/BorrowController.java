package quanlythuvien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import quanlythuvien.dto.BookBorrowDto;
import quanlythuvien.dto.Keyword;
import quanlythuvien.model.Book;
import quanlythuvien.model.BookBorrow;
import quanlythuvien.service.BookService;
import quanlythuvien.service.BorrowService;
import quanlythuvien.service.ReaderService;

import java.util.List;

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
    public BookBorrow addBorrowBook(@RequestBody BookBorrowDto p){
        borrowService.add(p);
        return null;
    }

    @GetMapping("/bookborrow/noti")
    public String notiborrow() {
        return "noti_borrow";
    }

    @GetMapping("/bookreturn/noti")
    public String notireturn() {
        return "noti_return";
    }

    @PostMapping("/bookborrow/noti")
    @ResponseBody
    public List<Book> notiBorrowBook(@RequestBody BookBorrowDto p){
        return borrowService.notiBorrowBook(p.getBook());
    }

    @GetMapping("/bookborrow/book/{readerId}")
    public String borrowSearchBook(@PathVariable String readerId, Model model) {
        model.addAttribute("reader", readerService.findReaderById(readerId));
        model.addAttribute("books", bookService.getAllBooks());
        return "borrow_3";
    }

    @GetMapping("/bookreturn")
    public String bookReturn(Model model) {
        Keyword keyword = new Keyword();
        model.addAttribute("content", keyword);
        return "return_1";
    }

    @PostMapping("/bookreturn")
    public String resultReaderReturn(@ModelAttribute("content") Keyword keyword, Model model) {
        model.addAttribute("readers", readerService.searchReader(keyword.getName()));
        return "return_2";
    }

    @PostMapping("/bookreturn/add")
    @ResponseBody
    public BookBorrow returnBook(@RequestBody BookBorrowDto p){
        borrowService.update(p);
        return null;
    }
    @PostMapping("/bookreturn/find")
    @ResponseBody
    public BookBorrow findBookBorrow(@RequestBody BookBorrowDto p){
        return borrowService.getByUserReaderId(p);
    }

    @GetMapping("/bookreturn/book/{readerId}")
    public String returnSearchBook(@PathVariable String readerId, Model model) {
        model.addAttribute("reader", readerService.findReaderById(readerId));
        List<Book> list = borrowService.findReturnBook(readerId);
        model.addAttribute("listBorrowBooks", list);
        return "return_3";
    }



}
