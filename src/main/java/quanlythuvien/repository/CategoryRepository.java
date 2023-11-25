package quanlythuvien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quanlythuvien.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c order by c.id desc limit 1")
    Category getFinalCategory();
    @Query("select c from Category c where c.name = :content")
    Category getCategoryByName(String content);
}
