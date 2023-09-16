package controller;

import dao.ProductDAO;
import model.CategoryModel;
import model.ProductModel;

import java.util.List;

public interface IProductController {
    final ProductDAO productDAO = null;

    int update(ProductModel productModel);

    int delete(Integer id);

    List<ProductModel> list();

    void insert(ProductModel product, Integer categoryID);

    List<ProductModel> list(CategoryModel category);

}
