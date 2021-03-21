<%-- 
    Document   : users
    Created on : Mar 9, 2021, 1:26:36 PM
    Author     : 807930
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Week 9 Lab</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <div>
           
            <div>
                <c:if test="${activeUser == null}">
                <h1>Add User</h1>
                    <form action="users" method="post">
                        <input type="email" placeholder="Email" name="email" required>
                        <input type="text" placeholder="First Name" name="firstname" required> 
                        <input type="text" placeholder="Last Name" name="lastname" required>         
                        <input type="password" placeholder="Password" name="password" required>
                        <select name="role">
                            <option value=1>System Admin</option>
                            <option value=2>Regular User</option>
                            <option value=3>Company Admin</option>
                        </select>

                        <input type="hidden" name="action" value="add">
                        <input class="addInput" type="submit" value="Add">
                    </form>
                </c:if>
            </div>
            
            <div>
                <h1>Manage Users</h1>
                <table>
                    <tr>
                        <th>Email</th>
                        <th>Active</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Role</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    
                        <c:forEach items="${users}" var="user">
                        <tr>    
                            <td>${user.email}</td>
                            <td>
                                <input type="checkbox" name="isActive" checked>                          
                            </td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.role.roleName}</td>
                         
                            
                            <td>
                                <form action="users" method="post" >
                                    <input type="submit" name="edit" value="Edit">
                                    <input type="hidden" name="action" value="edit">
                                    <input type="hidden" name="editEmail" value="${user.email}">
                                </form>
                            </td>
                            <td>
                                <form action="users" method="post" >
                                    <input type="submit" name="delete" value="Delete">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="email" value="${user.email}">
                                </form>
                            </td>
                        </tr>    
                        </c:forEach>
                    
                </table>
            </div>
                           
            <div>
                
                <h1>Edit User</h1>
                    <form action="users" method="post">
                        <input type="email" placeholder="Edit Email" name="email" value="${editEmail}" readonly>
                        <input type="text" placeholder="Edit First Name" name="firstname" value="${editFirstname}"> 
                        <input type="text" placeholder="Edit Last Name" name="lastname" value="${editLastname}">     
                        <input type="password" placeholder="Password" name="password" value="${editPassword}">
                        <select name="role" value="${editRole}">
                             <c:if test="${editRole eq 1}">
                                <option value=1 selected>System Admin</option>
                                 <option value=2>Regular User</option>
                                 <option value=3>Company Admin</option>
                            </c:if>
                            <c:if test="${editRole eq 2}">
                                <option value=1>System Admin</option>
                                 <option value=2 selected>Regular User</option>
                                 <option value=3>Company Admin</option>
                            </c:if>
                            <c:if test="${editRole eq 3}">               
                                <option value=1>System Admin</option>
                                 <option value=2>Regular User</option>
                                 <option value=3 selected >Company Admin</option>
                            </c:if>
                        </select>

                        <input type="hidden" name="action" value="save">
                        <input type="submit" value="Save">
                        <input type="hidden" name="action" value="cancel">
                        <input type="submit" value="Cancel">
                    </form>  
            
            </div>

        </div>
    </body>
</html>
