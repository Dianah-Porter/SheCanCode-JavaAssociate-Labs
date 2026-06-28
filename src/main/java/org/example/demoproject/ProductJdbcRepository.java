package org.example.demoproject;

import org.example.demoproject.Product;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ProductJdbcRepository {

    private final DataSource dataSource;

    public ProductJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public void save(Product product) throws SQLException {

        String sql = """
            INSERT INTO products(id, name, price, stock)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, product.getId());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());

            ps.executeUpdate();
        }
    }

}
