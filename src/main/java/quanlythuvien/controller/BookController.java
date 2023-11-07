package quanlythuvien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import quanlythuvien.model.Book;
import quanlythuvien.service.BookService;

@Controller
public class BookController {
    private BookService bookService;
    @GetMapping("/books")
    public String book() {
        return "book";
    }

    @GetMapping("/books/new")
    public String createBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "create_book";
    }

    @PostMapping("/books/new")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/book";
    }
}
