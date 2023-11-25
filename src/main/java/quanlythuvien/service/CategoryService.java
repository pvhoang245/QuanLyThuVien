package quanlythuvien.service;

import quanlythuvien.model.Category;

import java.util.List;

public interface CategoryService {
    Category getById(int id);
    List<Category> getAllCategory();
    Category saveCategory(Category category);
    Category deleteCategory(int id);

    int getFinalCategory();

    Category getCategoryByname(String content);
}
