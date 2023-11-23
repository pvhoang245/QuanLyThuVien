package quanlythuvien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import quanlythuvien.dto.Keyword;
import quanlythuvien.model.Book;
import quanlythuvien.service.BookService;
import quanlythuvien.service.CategoryService;

import java.util.List;
@Controller
@CrossOrigin
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/books")
    public String book(@RequestParam("userId") String userId, Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("userId", userId);
        return "book";
    }

    @GetMapping("books/{id}")
    @ResponseBody
    public Book getOne(@PathVariable String id) {
        return bookService.findBookById(id);
    }

    @GetMapping("/books/new")
    public String createBook(Model model) {
        Book book = new Book();
        model.addAttribute("newBook", book);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "create_book";
    }

    @PostMapping("/books/new")
    public String saveBook(@ModelAttribute("newBook") Book book) {
        Book book1 = new Book();
        return mapBook(book, book1);
    }

    @GetMapping("/books/update/{id}")
    public String updateBook(Model model, @PathVariable String id) {
        Book book = bookService.findBookById(id);
        model.addAttribute("updateBook", book);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "update_book";
    }

    @PostMapping("/books/update/{id}")
    public String saveUpdateBook(@ModelAttribute("updateBook") Book book, @PathVariable String id) {
        Book book1 = bookService.findBookById(id);
        return mapBook(book, book1);
    }

    private String mapBook(@ModelAttribute("updateBook") Book book, Book book1) {
        book1.setId(book.getId());
        book1.setName(book.getName());
        book1.setAuthor(book.getAuthor());
        book1.setTotalNumber(book.getTotalNumber());
        book1.setBorrowNumber(book.getBorrowNumber());
        book1.setLocation(book.getLocation());
        book1.setCategory(categoryService.getById(book.getCategory().getId()));
        bookService.saveBook(book1);
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @PostMapping("/search/{content}")
    @ResponseBody
    public List<Book> searchProduct(@PathVariable String content) {
        if (content.equals(null) || content.equals("")) {
            return bookService.getAllBooks();
        }
        else return bookService.search(content);
    }
}
