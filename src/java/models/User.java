
package models;

import java.io.Serializable;

/**
 *
 * @author 807930
 */
public class User implements Serializable {
    private String email;
    private boolean active;
    private String firstName;
    private String lastName;
    private String password;
    private int roleID;
    
    
    public User() {
        
    }
    
    public User(String email, boolean active, String firstName, String lastName, String password, int roleID){
        this.email = email;
        this.active = active;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        
        this.roleID = roleID;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public boolean isActive(){
        return active;
    }
    
    public void setActive(boolean Active) {
        this.active = active;
    }
    
    public int getRoleID() {
        return roleID;
    }
    
    public void setRoleID() {
        this.roleID = roleID;
    }
}
