package dao;

import model.CategoryModel;
import model.ProductModel;
import java.util.List;
public interface IProductDAO {
    void insert(ProductModel product);
    List<ProductModel> list();
    int delete(Integer id);
    int update(Integer id, String name, String description,
               Integer categoryID, Integer quantity);
    List<ProductModel> list(CategoryModel category);
}
