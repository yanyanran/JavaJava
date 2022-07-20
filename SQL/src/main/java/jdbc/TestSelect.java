package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestSelect {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/testmysql?EMPLOYEES=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        Statement statement = connection.createStatement();

        // 查询
        String sql = "select EMP_ID, NAME from EMPLOYEES";
        ResultSet resultSet = statement.executeQuery(sql);
        // 处理结果集
        while (resultSet.next()) {
            // 可以直接填字段名称
            System.out.print(resultSet.getObject("EMP_ID"));
            System.out.print(" ");
            System.out.println(resultSet.getObject("NAME"));
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}