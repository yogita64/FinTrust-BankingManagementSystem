package com.fintrust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.zkoss.zk.ui.util.Clients;

import com.fintrust.model.Users;
import com.fintrust.model.Users.Role;
import com.fintrust.dao.DBConnection;

public class UserCRUD {

	/**
	 * 
	 * @param username
	 * @param password_hash
	 * @param email
	 * @param mobile
	 * @param full_name
	 * @param role
	 * @param is_active
	 * @return
	 */
	public static boolean insertUser(String username,String password_hash,String email,String mobile,String full_name,Role role,Boolean is_active) {
        
        createUserTable();

        String sql = "INSERT INTO users(username, password_hash, email, mobile, full_name, role, is_active, created_at, last_login) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NULL)";

        try  {
        	
        	Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        	
        	if (conn == null) return false;
        	
            ps.setString(1, username);
            ps.setString(2, password_hash);
            ps.setString(3, email);
            ps.setString(4, mobile);
            ps.setString(5, full_name);
            ps.setString(6, role.name()); 
            ps.setBoolean(7, is_active);

            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println(" User inserted successfully: ");
                return true;
            }

        } catch (Exception e) {
            System.out.println(" Error inserting user record.");
            e.printStackTrace();
            Clients.showNotification("Error inserting user: " + e.getMessage(), true);
        }

        return false;
    }
	
	
	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	
	public static boolean isUserPresent(String email, String password) {
		try {
		Connection con = DBConnection.getConnection();
			if (con == null) throw new SQLException();
			PreparedStatement pstm = con.prepareStatement("Select name from Users where email = ? and password_hash = ?");
			pstm.setString(1, email);
			pstm.setString(2, password);
			ResultSet rs = pstm.executeQuery();
			
			return rs.next();
		} catch(SQLException e) {
			Clients.showNotification("Failed to fetch user.. Error: " + e.getMessage(), true);
			e.printStackTrace();
			return false;
		}
	}
    
    /**
     * 
     * @param userId
     */
    public static void updateLastLogin(int userId) {
        String sql = "UPDATE users SET last_login = NOW() WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
            System.out.println(" Updated last login for user_id: " + userId);
        } catch (Exception e) {
            System.out.println(" Failed to update last login for user_id: " + userId);
            e.printStackTrace();
        }
    }

    /**
     *  Creates the users table if it doesn't already exist
     */
    /**
     * 
     * @return
     */
    public static boolean createUserTable() {
        String createQuery = """
            CREATE TABLE IF NOT EXISTS users (
                user_id INT PRIMARY KEY AUTO_INCREMENT,
                username VARCHAR(50) NOT NULL UNIQUE,
                password_hash VARCHAR(100) NOT NULL,
                email VARCHAR(100),
                mobile VARCHAR(15),
                full_name VARCHAR(100),
                role ENUM('ADMIN', 'EMPLOYEE', 'CUSTOMER') DEFAULT 'CUSTOMER',
                is_active BOOLEAN DEFAULT TRUE,
                created_at DATETIME DEFW(),
                last_login DATETIME NULL
            )
        """;

        try  {
        	Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(createQuery);
            System.out.println(" Users table verified/created successfully.");
            return true;

        } catch (SQLException e) {
            Clients.showNotification(" Failed to create users table: " + e.getMessage(), true);
            e.printStackTrace();
            return false;
        }
    }
    
    public static void main(String args[]) {
    //Users u = new Users();
   

    String username = "yogita";
    String password_hash = "12345"; 
    String email = "yogita@gmail.com";
    String mobile = "9876543210";
    String full_name = "Yogita Sarawagi";
    Role role = Users.Role.CUSTOMER;
    Boolean is_active = true;

    boolean result = insertUser(username, password_hash, email, mobile, full_name, role, is_active);
    if (result) {
        System.out.println(" User inserted successfully!");
    } else {
        System.out.println(" Failed to insert user.");
    }
}
}

