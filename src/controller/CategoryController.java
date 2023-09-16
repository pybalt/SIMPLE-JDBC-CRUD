package controller;
import java.util.List;

import dao.CategoryDAO;
import factory.ConnectionFactory;
import model.CategoryModel;
interface ICategoryController {
    List<CategoryModel> list();

    void insert(CategoryModel tempProduct);

    void update(CategoryModel categorySelected);
    void delete(CategoryModel categoryModel);
}

public class CategoryController implements  ICategoryController{
    private final CategoryDAO categoryDAO;
    public CategoryController(){
        ConnectionFactory connection = new ConnectionFactory();
        this.categoryDAO = new CategoryDAO(connection.getConnection());
    }

    @Override
    public List<CategoryModel> list() {
        return this.categoryDAO.list();
    }

    @Override
    public void insert(CategoryModel category) {
        this.categoryDAO.insert(category);
    }

    @Override
    public void update(CategoryModel category) {
        this.categoryDAO.update(category);
    }

    @Override
    public void delete(CategoryModel category) {
        this.categoryDAO.delete(category);
    }
}
