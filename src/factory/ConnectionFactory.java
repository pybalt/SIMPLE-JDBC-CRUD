package factory;

import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.SQLException;

public class ConnectionFactory {
    private final DataSource dataSource;
    public ConnectionFactory(){
        final String databaseName = "products_db";
        var comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(
                String.format("jdbc:mysql://localhost/%s?useTimeZone=true&serverTimeZone=UTC", databaseName)
        );
        System.out.println("comboPooledDataSourceURL = " + comboPooledDataSource.getJdbcUrl());
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("43815146");
        comboPooledDataSource.setMaxPoolSize(10);
        comboPooledDataSource.setAcquireRetryAttempts(5);
        this.dataSource = comboPooledDataSource;
    }

        public java.sql.Connection getConnection(){
            try {
                return this.dataSource.getConnection();
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }

}
