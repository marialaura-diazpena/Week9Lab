
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Role;
/**
 *
 * @author 807930
 */
public class RoleDB {
    
    public List<Role> getAll(Role role) throws Exception {
        List<Role> roles = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM role";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int roleID = rs.getInt(1);
                String roleName = rs.getString(2);
                role = new Role(roleID, roleName);
                roles.add(role);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return roles;
    }
    
    public Role get(int roleID) throws Exception {
        Role role = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM role WHERE role_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, roleID);
            rs = ps.executeQuery();
            if (rs.next()) {
                String roleName = rs.getString(2);
                role = new Role(roleID, roleName);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return role;
    }
    
    public void insert(Role role) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO role (role_id, role_name) VALUES (?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, role.getRoleID());
            ps.setString(2, role.getRoleName());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void update(Role role) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE role SET role_name=? WHERE role_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, role.getRoleName());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void delete(Role role) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM role WHERE role_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, role.getRoleID());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}
