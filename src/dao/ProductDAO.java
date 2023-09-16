package dao;

import model.CategoryModel;
import model.ProductModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private final Connection connection;
    public ProductDAO(Connection connection){
        this.connection = connection;
    }
    public void insert(ProductModel product){
        try{
            String sql = """
                    INSERT INTO PRODUCT(NAME, DESCRIPTION, QUANTITY, CATEGORY_ID)
                    VALUES(?, ?, ?, ?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            try(preparedStatement){
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setInt(3, product.getQuantity());
                preparedStatement.setInt(4, product.getCategoryID());
                preparedStatement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ProductModel> list(){
        List<ProductModel> result = new ArrayList<>();
        try{
            String sql = "SELECT ID, NAME, DESCRIPTION, QUANTITY, CATEGORY_ID FROM PRODUCT";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeQuery();
            try(preparedStatement){
                final ResultSet resultSet = preparedStatement.getResultSet();
                while(resultSet.next()){
                    result.add(new ProductModel(
                            resultSet.getInt("ID"),
                            resultSet.getString("NAME"),
                            resultSet.getString("DESCRIPTION"),
                            resultSet.getInt("QUANTITY"),
                            resultSet.getInt("CATEGORY_ID")
                    ));
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }
    public int delete(Integer id){
        try{
            String sql = "DELETE FROM PRODUCTO WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            try(preparedStatement){
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                return preparedStatement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int update(
            Integer id,
            String name,
            String description,
            Integer categoryID,
            Integer quantity){
        try{
            String sql = """
                    UPDATE PRODUCT SET NAME = ?, DESCRIPTION = ?,
                    QUANTITY = ?, CATEGORY_ID = ? WHERE ID = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try(preparedStatement){
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, description);
                preparedStatement.setInt(3, quantity);
                preparedStatement.setInt(4, categoryID);
                preparedStatement.setInt(5, id);
                preparedStatement.executeUpdate();
                return preparedStatement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ProductModel> list(CategoryModel category){
        List<ProductModel> result = new ArrayList<>();
        try{
            String sql= """
                    SELECT ID, NAME, DESCRIPTION, QUANTITY, CATEGORY_ID FROM PRODUCT
                    WHERE CATEGORY_ID = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try(preparedStatement){
                preparedStatement.setInt(1, category.getId());
                ResultSet resultSet = preparedStatement.executeQuery();
                try(resultSet){
                    while(resultSet.next()){
                        result.add(new ProductModel(
                                resultSet.getInt("ID"),
                                resultSet.getString("NAME"),
                                resultSet.getString("DESCRIPTION"),
                                resultSet.getInt("QUANTITY"),
                                resultSet.getInt("CATEGORY_ID")
                                )
                        );
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
