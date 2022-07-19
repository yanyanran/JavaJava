package client.login;

import java.sql.*;
import java.util.Scanner;

public class Client {
    private static String username;
    private static String password;
    private static String url = "jdbc:mysql://localhost:3306/ChatRoomClient?client=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static String user = "root";
    private static String pass = "123456";
    private static Connection con;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception{
        // loading....
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url,user,pass);

        System.out.println("********用户界面********");
        System.out.println("请选择：\n 1:用户登录\n 2：用户注册\n 3：注销用户");
        System.out.println("**********************");

        int i = input.nextInt();
        switch (i) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                logout();
            default:
                System.out.println("!!!Error input!!!");
                System.exit(0);
        }
    }

    public static void register() throws Exception{
        System.out.println("Please inout your name: ");
        username = input.next();
        System.out.println("Please input your password: ");
        String p1 = input.next();
        System.out.println("Please input your password again to confirm the password: ");
        String p2 = input.next();

        if((p1).equals(p2)) {
            // YES
            password = p1;
            String sql = "insert into client (username,password) values(?,?)";
            PreparedStatement ptmt = con.prepareStatement(sql);
            ptmt.setString(1, username);
            ptmt.setString(2, password);
            ptmt.execute();
            System.out.println("------Registration is successful, please log in-------");
            login();
        }
    }

    public static void login() throws Exception {
        System.out.println("Please input your name: ");
        username = input.next();
        System.out.println("Please input your password: ");
        password = input.next();

        String sql = "select id,username,password from client where username=? and password=?";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ptmt.setString(1, username);
        ptmt.setString(2, password);
        ResultSet rs = ptmt.executeQuery();

        if(rs.next()){
            System.out.println("-------Account login successful--------");

            String sql2 = "select id,username,password from client";
            // 展开数据库结果集
            ResultSet m = ptmt.executeQuery(sql2);
            while(m.next()){
                // 通过字段检索
                int ID  = m.getInt("id");
                String NAME = m.getString("username");
                String PASSWORD = m.getString("password");

                // 输出数据
                System.out.print("ID: " + ID);
                System.out.print(", username: " + NAME);
                System.out.print(", password: " + PASSWORD);
                System.out.print("\n");
            }
        }else{
            System.out.println("-------Incorrect name or password!---------\n" + "please login again:");
            login();
        }
    }


    //?????
    public static void logout() throws Exception {
        System.out.println("Please enter the name you want to log out: ");
        username = input.next();
        System.out.println("Please enter the password: ");
        password = input.next();

        Statement stmt = con.createStatement();
        String query = "ALTER TABLE username Drop password";
            System.out.println("Are you sure you want to cancel this account？\n yes enter 1 and no enter 2");
            int result = stmt.executeUpdate(query);
        if(result > 0){
            int i = input.nextInt();
            switch (i) {
                case 1:
                    System.out.println("-------删除完成--------");
                    break;
                case 2:
                    break;
        }

    }
}