package dao;

import model.CategoryModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {
    private final Connection connection;
    public CategoryDAO(Connection connection){
        this.connection = connection;
    }
    public List<CategoryModel> list(){
        List<CategoryModel> result = new ArrayList<>();

        try{
            String sql = "SELECT ID, NAME FROM CATEGORY";
            final PreparedStatement statement = connection
                    .prepareStatement(sql);

            try(statement){
                final ResultSet resultSet = statement.executeQuery();
                try(resultSet){
                    while(resultSet.next()){
                        result.add(new CategoryModel(
                                resultSet.getInt("ID"),
                                resultSet.getString("NAME")
                        ));
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return result;
    }
    public void insert(CategoryModel categoryModel) {
        String sql = """
                INSERT INTO CATEGORY(NAME) VALUES(?)
                """;

        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            try(statement){
                statement.setString(1, categoryModel.getName());
                statement.execute();
                ResultSet resultSet = statement.getGeneratedKeys();
                try(resultSet){
                    while (resultSet.next()){
                        categoryModel.setId(resultSet.getInt(1));
                        System.out.printf("%s was inserted", categoryModel);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(CategoryModel categoryModel){
        String sql = """
                DELETE FROM CATEGORY WHERE ID = ?
                """;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try(preparedStatement) {
                preparedStatement.setInt(1, categoryModel.getId());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(CategoryModel categoryModel){
        String sql = """
               UPDATE CATEGORY SET NAME = ? WHERE ID = ?
               """;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            try(preparedStatement){
                preparedStatement.setString(1, categoryModel.getName());
                preparedStatement.setInt(2, categoryModel.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
