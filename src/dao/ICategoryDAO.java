package dao;

import model.CategoryModel;

import java.util.List;

public interface ICategoryDAO {
    List<CategoryModel> list();
    void insert(CategoryModel category);
    void delete(CategoryModel category);
    void update(CategoryModel category);
}
