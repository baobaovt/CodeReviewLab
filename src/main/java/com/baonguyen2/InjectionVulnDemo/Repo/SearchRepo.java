package com.baonguyen2.InjectionVulnDemo.Repo;


import org.springframework.stereotype.Repository;

import com.baonguyen2.InjectionVulnDemo.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class SearchRepo {

    public static final String JDBC_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

    public static final String USER = "sa";
    public static final String PASS = "";



    public List<User> findUsersByUsername(String parameter) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT * FROM users WHERE username LIKE '" + parameter + "'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            List<User> foundUsers = createUsersFromResultSet(result);
			/*
			 * Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			 * String query = "SELECT * FROM USERS WHERE username LIKE ?"; PreparedStatement
			 * statement = connection.prepareStatement(query); statement.setString(1,
			 * parameter); ResultSet result = statement.executeQuery(); List<User>
			 * foundUsers = createUsersFromResultSet(result);
			 */
            return foundUsers;
        } catch (SQLException e) {
            return Collections.singletonList(new User("","","",e.getMessage(),""));
        }

    }


    private List<User> createUsersFromResultSet(ResultSet result) throws SQLException{
        List<User> users = new ArrayList<>();
        while (result.next()) {
            User user = new User();
            user.setFirstname(result.getString("firstname"));
            user.setLastname(result.getString("lastname"));
            user.setUsername(result.getString("username"));
            user.setPassword(result.getString("password"));
            user.setComment(result.getString("comment"));
            users.add(user);
        }
        return users;
    }

}
