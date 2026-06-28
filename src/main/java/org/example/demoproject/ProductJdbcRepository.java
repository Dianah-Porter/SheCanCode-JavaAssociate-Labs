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

    // SAVE
    public void save(Product product) throws SQLException {

        String sql = """
                INSERT INTO products(id,name,price,stock)
                VALUES(?,?,?,?)
                """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, product.getId());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());

            ps.executeUpdate();
        }
    }

    // FIND BY ID
    public Product findById(String id) throws SQLException {

        String sql = "SELECT * FROM products WHERE id=?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1,id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

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

    // FIND ALL
    public List<Product> findAll() throws SQLException{

        List<Product> products = new ArrayList<>();

        String sql="SELECT * FROM products";

        try(Connection connection=dataSource.getConnection();
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet rs=ps.executeQuery()){

            while(rs.next()){

                products.add(

                        new Product(
                                rs.getString("id"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getInt("stock")
                        )

                );
            }

        }

        return products;
    }

    // DELETE
    public void deleteById(String id)throws SQLException{

        String sql="DELETE FROM products WHERE id=?";

        try(Connection connection=dataSource.getConnection();
            PreparedStatement ps=connection.prepareStatement(sql)){

            ps.setString(1,id);

            ps.executeUpdate();
        }

    }

    // TRANSFER (TRANSACTION)
    public void transfer(String fromId,String toId,int quantity)throws SQLException{

        Connection connection=dataSource.getConnection();

        try{

            connection.setAutoCommit(false);

            String deductSql="""
                    UPDATE products
                    SET stock=stock-?
                    WHERE id=?
                    """;

            PreparedStatement deduct=connection.prepareStatement(deductSql);

            deduct.setInt(1,quantity);
            deduct.setString(2,fromId);

            deduct.executeUpdate();

            String addSql="""
                    UPDATE products
                    SET stock=stock+?
                    WHERE id=?
                    """;

            PreparedStatement add=connection.prepareStatement(addSql);

            add.setInt(1,quantity);
            add.setString(2,toId);

            add.executeUpdate();

            connection.commit();

            System.out.println("Transfer Successful");

        }

        catch(Exception e){

            connection.rollback();

            throw e;

        }

        finally{

            connection.setAutoCommit(true);

            connection.close();

        }

    }

}