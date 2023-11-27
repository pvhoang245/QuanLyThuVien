package quanlythuvien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import quanlythuvien.service.AccountService;
import quanlythuvien.service.BookService;
import quanlythuvien.service.BorrowService;
import quanlythuvien.service.UserService;

@Controller
public class ReportController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @GetMapping("/report")
    public String report() {
        return "report";
    }

    @GetMapping("/report/reportbyborrow")
    public String reportBookByBorrow(Model model) {
        model.addAttribute("listBook", bookService.reportByBorrow());
        return "report_1";
    }

    @GetMapping("/report/reportbyreader")
    public String reportBookByReader(Model model) {
        model.addAttribute("listReader", bookService.reportByReader());
        return "report_2";
    }
}
