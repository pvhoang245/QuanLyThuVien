package quanlythuvien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import quanlythuvien.model.User;
import quanlythuvien.service.AccountService;
import quanlythuvien.service.UserService;
import quanlythuvien.service.CategoryService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/users")
    public String user(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user";
    }

    @GetMapping("/users/new")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        model.addAttribute("accounts", accountService.getALlAccounts());
        return "create_user";
    }

    @PostMapping("/users/new")
    public String saveUser(@ModelAttribute("newUser") User user) {
        User user1 = new User();
        return mapUser(user, user1);
    }

    @GetMapping("/users/update/{id}")
    public String updateUser(Model model, @PathVariable int id) {
        User user = userService.findUserById(id);
        model.addAttribute("updateUser", user);
        model.addAttribute("accounts", accountService.getALlAccounts());
        return "update_user";
    }

    @PostMapping("/users/update/{id}")
    public String saveUpdateUser(@ModelAttribute("updateUser") User user, @PathVariable int id) {
        User user1 = userService.findUserById(id);
        return mapUser(user, user1);
    }

    private String mapUser(@ModelAttribute("updateUser") User user, User user1) {
        user1.setId(user.getId());
        user1.setName(user.getName());
        user1.setJob(user.getJob());
        user1.setPhone(user.getPhone());
        user1.setEmail(user.getEmail());
        user1.setOther(user.getOther());
        user1.setAccount(accountService.getById(user.getAccount().getId()));
        userService.saveUser(user1);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
