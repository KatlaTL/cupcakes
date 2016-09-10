package data;

import entity.bottom;
import entity.invoice;
import entity.topping;
import entity.user;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class DBHandler {
    
    private static DBHandler instance;
    private static DBConnector DBC;
    private static Connection conn;
    
    private DBHandler() {
        DBC = DBConnector.getInstance();
        conn = DBC.getConnection();
    }
    
    public static DBHandler getInstance() {
        if(instance == null) {
            instance = new DBHandler();
        }
        return instance;
    }
    
    public List<user> loginCheck(String username, String password){
        List<user> user = new ArrayList();
        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                user.add(new user(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4)));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return user;
        }        
    }
    
    public List<topping> getToppings() {
        List<topping> toppingList = new ArrayList();
        
        String sql = "SELECT * FROM toppings";
        try {
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            while(rs.next()) {
                toppingList.add(new topping(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return toppingList;
        }
    }
    
    public List<bottom> getBottoms() {
        List<bottom> bottmomList = new ArrayList();
        
        String sql = "SELECT * FROM bottoms";
        try {
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            while(rs.next()) {
                bottmomList.add(new bottom(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return bottmomList;
        }
    }
    
    public void buyOrder(List<invoice> inv, user u, double price){
        try {
            
            String sqlGetBalance = "SELECT balance FROM cupcakes.users WHERE userId=?";
            PreparedStatement ps = conn.prepareStatement(sqlGetBalance);
            ps.setInt(1, u.getId());
            ResultSet rs = ps.executeQuery();
            double userBalance = 0;
            if(rs.next()) {
                userBalance = rs.getDouble(1);
            }
            double userNewBalance = userBalance - price;
            String firstSql = "INSERT INTO cupcakes.invoice (bottom, topping, totalPrice) VALUES (?, ?, ?); INSERT INTO cupcakes.orders (userId) VALUES (?); INSERT INTO cupcakes.orderdetails (invoiceID, ordersId) VALUES ((SELECT invoiceId FROM cupcakes.invoice ORDER BY invoiceId DESC LIMIT 1), (SELECT ordersId FROM cupcakes.orders ORDER BY ordersId DESC LIMIT 1)); UPDATE cupcakes.users SET balance=? WHERE userId=?";
            String sql = "INSERT INTO cupcakes.invoice (bottom, topping, totalPrice) VALUES (?, ?, ?); INSERT INTO cupcakes.orderdetails (invoiceID, ordersId) VALUES ((SELECT invoiceId FROM cupcakes.invoice ORDER BY invoiceId DESC LIMIT 1), (SELECT ordersId FROM cupcakes.orders ORDER BY ordersId DESC LIMIT 1));";
            int counter = 0;
            for (invoice in : inv) {
                if(counter == 0) {
                    String topping = in.getTopping();
                    String bottom = in.getBottom();
                    Double totalPrice = in.getTotalPrice();
                    PreparedStatement pstmt = conn.prepareStatement(firstSql);
                    pstmt.setString(1, bottom);
                    pstmt.setString(2, topping);
                    pstmt.setDouble(3, totalPrice);
                    pstmt.setInt(4, u.getId());
                    pstmt.setDouble(5, userNewBalance);
                    pstmt.setInt(6, u.getId());
                    pstmt.executeUpdate();
                } else {
                    String topping = in.getTopping();
                    String bottom = in.getBottom();
                    Double totalPrice = in.getTotalPrice();
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, bottom);
                    pstmt.setString(2, topping);
                    pstmt.setDouble(3, totalPrice);
                    pstmt.executeUpdate();   
                }
                counter++;
            }
        } catch(SQLException e) {
            e.getStackTrace();
        }
    }
    
    public List<user> getLoggedInUser(user u){
        List<user> user = new ArrayList();
        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM users WHERE userId = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, u.getId());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                user.add(new user(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4)));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return user;
        }        
    }
}
