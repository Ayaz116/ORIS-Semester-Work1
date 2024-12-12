package ru.itis.repository;

import ru.itis.model.Category;
import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
    void save(Category category);
    Category findById(int id);
}
