package Fasilistis;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private String sql;
    private static Connection connect = null;
    private static String url = "jdbc:sqlite:fasilistis.db";
    
    private Database() {
    }
    
    public static Connection connectDB() { 
        try {
            Class.forName("org.sqlite.JDBC"); 
            connect = DriverManager.getConnection(url);
            System.out.println("Connection Succesfull");
        } catch (Exception e) {
            System.out.println("Connection Failed" +e);
        }
        return connect;
    }
    public static void main(String[] args){
        connectDB();
    }
}
