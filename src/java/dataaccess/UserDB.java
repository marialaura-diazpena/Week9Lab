/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.User;

/**
 *
 * @author 807930
 */
public class UserDB {
    public List<User> getAll(User user) throws Exception {
        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM user";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString(1);
                boolean active = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4); 
                String password = rs.getString(5); 
                int roleID = rs.getInt(6);
                user = new User(email, active, firstName, lastName, password, roleID);
                users.add(user);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return users;
    }
    
    public User get(String email)  throws Exception {
        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM user WHERE email=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                boolean active = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4); 
                String password = rs.getString(5); 
                int roleID = rs.getInt(6);
                user = new User(email, active, firstName, lastName, password, roleID);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return user;
    }
    
    public void insert(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO user (email, active, first_name, last_name, password, role) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setBoolean(2, user.isActive());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getRoleID());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void update(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE user SET first_name=?, last_name=?, password=?, active=?, role=? WHERE email=?";
        
        try {
            ps = con.prepareStatement(sql);
            System.out.println("user: "+user);
            System.out.println("user id: " + user.getRoleID());
        
//            System.out.println("user: "+user.getFirstName() + ", "+ );
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassword());
            ps.setBoolean(4, user.isActive());
            ps.setInt(5, user.getRoleID());
//            ps.setString(5, user.getEmail());
            ps.setString(6, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void delete(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM user WHERE email=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
