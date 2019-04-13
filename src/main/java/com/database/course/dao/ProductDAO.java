package com.database.course.dao;

import com.database.course.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

    @Autowired
    private JdbcTemplate template;
    private RowMapper<Product> productMapper = (rs, rowNum) -> new Product(
            rs.getString("product_name"),
            rs.getString("product_category"),
            rs.getString("price"),
            rs.getString("shop_name"));

    public List<Product> getProducts(String shopname, String category, String price) {
        // todo 2x request
        return template.query("SELECT * FROM product_shop p " +
                "INNER JOIN product_prices pp " +
                "ON p.product_name = pp.product_name " +
                "WHERE p.shop_name = ? AND p.product_category = ? " +
                "AND pp.price > ?", productMapper, shopname, category, price);
    }

    public List<Product> findAll() {
        return template.query("SELECT * FROM product_shop p " +
                "INNER JOIN product_prices pp " +
                "ON p.product_name = pp.product_name", productMapper);
    }

    public void update(String oldProductName, String oldCategory, String productName,
                       String category, String price, String shopName) {
        template.update("UPDATE product_shop ps " +
                        "INNER JOIN product_prices pp ON pp.product_name = ps.product_name " +
                        "INNER JOIN product_score psc ON pp.product_name = psc.product_name " +
                        "INNER JOIN product_exists pe ON pp.product_name = pe.product_name " +
                        "INNER JOIN product_discount_type pdt ON pp.product_name = pdt.product_name " +
                        "INNER JOIN product_discount pd ON pp.product_name = pd.product_name " +
                        "INNER JOIN products_promo ppr ON pp.product_name = ppr.product_name " +
                        "SET ps.product_name = ?, pp.product_name = ?, psc.product_name = ?, pe.product_name = ?, " +
                        "pdt.product_name = ?, pd.product_name = ?, ppr.product_name = ?, " +
                        "ps.product_category = ?, pp.price = ?, " +
                        "ps.shop_name = ?, psc.shopname = ?, ppr.shopname = ? " +
                        "WHERE ps.product_name = ? AND ps.product_category = ?",
                productName, productName, productName, productName, productName, productName, productName,
                category, price, shopName, shopName, shopName, oldProductName, oldCategory);
    }

    public void remove(String productName, String category) {
        template.update("DELETE FROM product_shop WHERE product_name = ? AND product_category = ?", productName, category);
        template.update("DELETE FROM product_prices WHERE product_name = ?", productName);
        template.update("DELETE FROM product_score WHERE product_name = ?", productName);
        template.update("DELETE FROM product_exists WHERE product_name = ?", productName);
        template.update("DELETE FROM product_discount_type WHERE product_name = ?", productName);
        template.update("DELETE FROM product_discount WHERE product_name = ?", productName);
        template.update("DELETE FROM products_promo WHERE product_name = ?", productName);
    }

    public Product findByNameAndCategory(String productName, String category) {
        return template.query("SELECT * FROM product_shop p " +
                        "INNER JOIN product_prices pp " +
                        "ON p.product_name = pp.product_name " +
                        "WHERE p.product_name = ? AND p.product_category = ? ",
                productMapper, productName, category).get(0);
    }

    public void addProduct(String productName, String category, String price, String shopName) {
        template.update("INSERT INTO product_shop VALUES (?,?,?)", productName, category, shopName);
        template.update("INSERT INTO product_prices VALUES (?,?)", productName, price);
    }
}