package org.example;
import java.sql.*;

public class App {
    public static void main(String[] args) {
        System.out.println("MySQL JDBC Example.");
        Connection conn ;
        String url = "jdbc:mysql://127.0.0.1:3306/ionic";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "yan021008";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver" + driver);  // 抛出java.lang.ClassNotFoundException异常
            // 解决方法：forName中加上包名
            conn = DriverManager.getConnection(url, userName, password);
            stmt = conn.createStatement();
            String sql = "select * from EMPLOYEES";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("emp_id");
                String name = rs.getString("name");
                System.out.println("id = " + id + ", name = " + name);
            }
            // 关闭资源
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) { } // ignore
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ignored) { } // ignore
            }
        }
    }
}
