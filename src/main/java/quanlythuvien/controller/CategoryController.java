package quanlythuvien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import quanlythuvien.model.Book;
import quanlythuvien.model.Category;
import quanlythuvien.service.CategoryService;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String category(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "category";
    }
    @GetMapping("/categories/new")
    public String createCategory(Model model) {
        Category category = new Category();
        category.setId(categoryService.getFinalCategory());
        model.addAttribute("newCategory", category);
        return "create_category";
    }

    @PostMapping("/categories/new")
    public String saveCategory(@ModelAttribute("newCategory") Category category) {
        Category category1 = new Category();
        return mapCategory(category, category1);
    }

    @GetMapping("/categories/update/{id}")
    public String updateCategory(Model model, @PathVariable int id) {
        Category category = categoryService.getById(id);
        model.addAttribute("updateCategory", category);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "update_category";
    }

    @PostMapping("/categories/update/{id}")
    public String saveUpdateCategory(@ModelAttribute("updateCategory") Category category, @PathVariable int id) {
        Category category1 = categoryService.getById(id);
        return mapCategory(category, category1);
    }

    private String mapCategory(@ModelAttribute("updateCategory") Category category, Category category1) {
        category1.setId(category.getId());
        category1.setName(category.getName());
        categoryService.saveCategory(category1);
        return "redirect:/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    @GetMapping("/categories/getfinal")
    public int countFinalCategory() {
        return categoryService.getFinalCategory();
    }

    @PostMapping("/categories/search/{content}")
    @ResponseBody
    public String searchCategory(@PathVariable String content) {
        Category category = categoryService.getCategoryByname(content);
        if (category!=null) return "true";
        else return "false";
    }

}
