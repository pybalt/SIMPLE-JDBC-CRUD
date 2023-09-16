package controller;

import dao.ProductDAO;
import factory.ConnectionFactory;
import model.CategoryModel;
import model.ProductModel;

import java.util.List;

interface IProductController {
   int edit(Integer id,
            String name,
            String description,
            Integer categoryID,
            Integer quantity);
   int eliminate(Integer id);
   List<ProductModel> list();
   void save(ProductModel product, Integer categoryID);
   List<ProductModel> list(CategoryModel category);

}
public class ProductController implements IProductController {
    private final ProductDAO productDAO;
    public ProductController(){
        ConnectionFactory connection = new ConnectionFactory();
        this.productDAO = new ProductDAO(connection.getConnection());
    }

    @Override
    public int edit(Integer id,
                    String name,
                    String description,
                    Integer categoryID,
                    Integer quantity
                    ) {
        return this.productDAO.edit(id, name, description, categoryID, quantity);
    }
    @Override
    public int eliminate(Integer id) {
        return this.productDAO.delete(id);
    }

    @Override
    public List<ProductModel> list() {
        return this.productDAO.list();
    }

    @Override
    public void save(ProductModel product, Integer categoryID) {
        product.setCategoryID(categoryID);
        this.productDAO.insert(product);
    }

    @Override
    public List<ProductModel> list(CategoryModel category) {
        return this.productDAO.list(category);
    }


}
