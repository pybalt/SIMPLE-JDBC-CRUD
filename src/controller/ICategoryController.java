package controller;

import model.CategoryModel;

import java.util.List;

public interface ICategoryController {
    List<CategoryModel> list();

    void insert(CategoryModel tempProduct);

    void update(CategoryModel categorySelected);

    void delete(CategoryModel categoryModel);
}
