package com.abc.dao;

import com.abc.common.ICommonInterface;
import com.abc.model.Category;
import com.abc.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryDAO implements ICommonInterface<Category>{

    Connection con;
    PreparedStatement ps;
    
    @Override
    public int save(Category t) {
        String sql = "insert into category (cat_code, cat_name, cat_type) values (?, ?, ?)";
        int status = 0;
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getCatCode());
            ps.setString(2, t.getCatName());
            ps.setString(3, t.getCatType());
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
    public Category edit(Category t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Category t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Category t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category getByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Category> getAll() {
       String sql = "select * from category";
        List<Category> cats = new ArrayList<Category>();
        try  {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while(rs.next()){
                Category cat = new Category();
                cat.setId(rs.getInt("id"));
                cat.setCatCode(rs.getString("cat_code"));
                cat.setCatName(rs.getString("cat_name"));
                cat.setCatType(rs.getString("cat_type"));
                cats.add(cat);
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
        return cats;
    }

}
