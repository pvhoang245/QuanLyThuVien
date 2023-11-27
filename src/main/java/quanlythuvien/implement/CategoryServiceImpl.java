package quanlythuvien.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quanlythuvien.model.Category;
import quanlythuvien.repository.CategoryRepository;
import quanlythuvien.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category getById(int id) {
        return categoryRepository.getById(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category deleteCategory(int id) {
        categoryRepository.deleteById(id);
        return null;
    }

    @Override
    public int getFinalCategory() {
        Category category = categoryRepository.getFinalCategory();
        if(category == null) {
            return 1;
        }
        else return categoryRepository.getFinalCategory().getId()+1;
    }

    @Override
    public Category getCategoryByname(String content) {
        return categoryRepository.getCategoryByName(content);
    }
}
