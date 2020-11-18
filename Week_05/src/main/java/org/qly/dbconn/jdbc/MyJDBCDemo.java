package org.qly.dbconn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJDBCDemo {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 数据库 IP 是本机虚拟机 IP
            connection = DriverManager.getConnection("jdbc:mysql://192.168.163.128:3306/qlydb", "root", "123456");
            statement = connection.createStatement();

            String add = "insert into tbl_order (ORDER_ID, USER_ID, GOOD_NAME) VALUES ('202011180001', '0091', 'T-shirt')";
            statement = connection.createStatement();
            statement.execute(add);

            String query = "select * from tbl_order";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String orderId = resultSet.getString("ORDER_ID");
                String userId = resultSet.getString("USER_ID");
                String goodName = resultSet.getString("GOOD_NAME");
                System.out.println("User " + userId + " bought a " + goodName + ", the order id is " + orderId);
            }

            String update = "update tbl_order t set t.good_name = 'jacket'";
            statement = connection.createStatement();
            statement.execute(update);

            String delete = "delete from tbl_order where order_id = '202011180001'";
            statement = connection.createStatement();
            statement.executeUpdate(delete);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
