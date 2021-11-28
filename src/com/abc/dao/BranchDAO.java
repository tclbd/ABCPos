package com.abc.dao;

import com.abc.common.ICommonInterface;
import com.abc.model.Branch;
import com.abc.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BranchDAO implements ICommonInterface<Branch>{
    Connection con;
    PreparedStatement ps;
    
    @Override
    public int save(Branch t) {
        String sql = "insert into branch (branch_code, branch_name, branch_location) values (?, ?, ?)";
        int status = 0;
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, t.getBranchCode());
            ps.setString(2, t.getBranchName());
            ps.setString(3, t.getBranchLocation());
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
    public Branch edit(Branch t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Branch t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Branch t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Branch getByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Branch> getAll() {
        String sql = "select * from branch";
        List<Branch> branches = new ArrayList<Branch>();
        try  {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while(rs.next()){
                System.out.println(rs.getString("branch_code"));
                Branch branch = new Branch();
                branch.setId(rs.getInt("id"));
                branch.setBranchCode(rs.getString("branch_code"));
                branch.setBranchName(rs.getString("branch_name"));
                branch.setBranchLocation(rs.getString("branch_location"));
                branches.add(branch);
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
        System.out.println(branches);
        return branches;
    }

}
