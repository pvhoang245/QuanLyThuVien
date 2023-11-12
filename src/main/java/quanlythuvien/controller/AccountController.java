package quanlythuvien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import quanlythuvien.exception.AccountException;
import quanlythuvien.model.Account;
import quanlythuvien.service.AccountService;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/home")
    public String home(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "home";
    }

    @PostMapping("/home")
    public String login(@ModelAttribute("account") Account account) throws AccountException {
        Account account1 = accountService.login(account.getUsername());
        if (account1.getPassword().equals(account.getPassword()))
        {
            return "redirect:/books";
        }
        else {
            System.out.println("Sai password");
            return "home";
        }
    }
}