package controller;

import dao.IProductDAO;
import dao.ProductDAO;
import factory.ConnectionFactory;
import model.CategoryModel;
import model.ProductModel;

import java.util.List;

public class ProductController implements IProductController {
    private final IProductDAO productDAO;
    public ProductController(){
        ConnectionFactory connection = new ConnectionFactory();
        this.productDAO = new ProductDAO(connection.getConnection());
    }

    @Override
    public int update(Integer id,
                      String name,
                      String description,
                      Integer categoryID,
                      Integer quantity
                    ) {
        return this.productDAO.update(id, name, description, categoryID, quantity);
    }
    @Override
    public int delete(Integer id) {
        return this.productDAO.delete(id);
    }

    @Override
    public List<ProductModel> list() {
        return this.productDAO.list();
    }

    @Override
    public void insert(ProductModel product, Integer categoryID) {
        product.setCategoryID(categoryID);
        this.productDAO.insert(product);
    }

    @Override
    public List<ProductModel> list(CategoryModel category) {
        return this.productDAO.list(category);
    }


}
