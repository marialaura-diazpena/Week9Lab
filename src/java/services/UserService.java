/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author 807930
 */
public class UserService {

    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }
    
    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }
    
    public void insert(String email, boolean active, String firstName, String lastName, String password, int roleID) throws Exception {
        User user = new User(email, active, firstName, lastName, password);
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }
    
    public void update(String email, boolean active, String firstName, String lastName, String password) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setActive(active);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        
        userDB.update(user); 
    }
    
    public void delete(String email) throws Exception {
        UserDB userDB = new UserDB();   
        User user = userDB.get(email);
        userDB.delete(user);
    }
    
}
