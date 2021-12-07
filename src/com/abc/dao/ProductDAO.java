package com.abc.dao;

import com.abc.common.ICommonInterface;
import com.abc.model.Category;
import com.abc.model.Product;
import com.abc.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO implements ICommonInterface<Product>{

    Connection con;
    PreparedStatement ps;
    
    @Override
    public int save(Product t) {
        String sql = "insert into product (product_code, product_name, product_color, cat_code, cat_name, branch_code, branch_name, base_price, selling_price, buying_price, discount_percent, vat_percent, uom) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int status = 0;
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getProductCode());
            ps.setString(2, t.getProductName());
            ps.setString(3, t.getProductColor());
            ps.setString(4, t.getCatCode());
            ps.setString(5, t.getCatName());
            ps.setString(6, t.getBranchCode());
            ps.setString(7, t.getBranchName());
            ps.setDouble(8, t.getBasePrice());
            ps.setDouble(9, t.getSellingPrice());
            ps.setDouble(10, t.getBuyingPrice());
            ps.setDouble(11, t.getDiscountPercent());
            ps.setDouble(12, t.getVatPercent());
            ps.setString(13, t.getUom());
            status = ps.executeUpdate();
        } catch (Exception e) {
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(BranchDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    @Override
    public Product edit(Product t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Product t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Product t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> getAll() {
        String sql = "select * from product";
        List<Product> products = new ArrayList<Product>();
        try  {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProductCode(rs.getString("product_code"));
                product.setProductName(rs.getString("product_name"));
                product.setProductColor(rs.getString("product_color"));
                products.add(product);
                i++;
            }
        } catch (Exception e) {
            
        }finally{
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
            }
        }
        return products;
    }
    

    public Product getByCode(String code) {
        String sql = "select * from product where product_code = ?";
        Product product = new Product();
        try  {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
             product.setId(rs.getInt("id"));
                product.setProductCode(rs.getString("product_code"));
                product.setProductName(rs.getString("product_name"));
                product.setBasePrice(rs.getDouble("base_price"));
                product.setSellingPrice(rs.getDouble("selling_price"));
                product.setBuyingPrice(rs.getDouble("buying_price"));
                product.setDiscountPercent(rs.getDouble("discount_percent"));
                product.setVatPercent(rs.getDouble("vat_percent"));
                System.out.println(product.getProductName());
            }
               
        } catch (Exception e) {
        }finally{
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
            }
        }
        return product;
    }

}
