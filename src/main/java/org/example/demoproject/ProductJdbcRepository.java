package org.example.demoproject;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductJdbcRepository {

    private final DataSource dataSource;

    public ProductJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // ---------------- SAVE ----------------

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

    // ---------------- FIND BY ID ----------------

    public Product findById(String id) throws SQLException {

        String sql = """
                SELECT *
                FROM products
                WHERE id = ?
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
            }

            return null;
        }
    }

    // ---------------- FIND ALL ----------------

    public List<Product> findAll() throws SQLException {

        String sql = "SELECT * FROM products";

        List<Product> products = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Product product = new Product(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );

                products.add(product);
            }
        }

        return products;
    }

    // ---------------- DELETE ----------------

    public void deleteById(String id) throws SQLException {

        String sql = """
                DELETE FROM products
                WHERE id = ?
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);

            ps.executeUpdate();
        }
    }

    // ---------------- TRANSFER ----------------

    public void transfer(String fromId,
                         String toId,
                         int quantity) throws SQLException {

        Connection connection = dataSource.getConnection();

        try {

            connection.setAutoCommit(false);

            String deduct = """
                    UPDATE products
                    SET stock = stock - ?
                    WHERE id = ?
                    """;

            PreparedStatement ps1 =
                    connection.prepareStatement(deduct);

            ps1.setInt(1, quantity);
            ps1.setString(2, fromId);

            ps1.executeUpdate();

            String add = """
                    UPDATE products
                    SET stock = stock + ?
                    WHERE id = ?
                    """;

            PreparedStatement ps2 =
                    connection.prepareStatement(add);

            ps2.setInt(1, quantity);
            ps2.setString(2, toId);

            ps2.executeUpdate();

            connection.commit();

        } catch (Exception e) {

            connection.rollback();

            throw e;

        } finally {

            connection.setAutoCommit(true);

            connection.close();
        }
    }

}