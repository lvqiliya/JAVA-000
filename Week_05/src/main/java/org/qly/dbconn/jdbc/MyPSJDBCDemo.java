package org.qly.dbconn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyPSJDBCDemo {
    private static final String[] ORDERIDS = {"202011180002", "202011180003"};
    private static final String[] USERIDS = {"4466", "9998"};
    private static final String[] GOODS = {"MacBook", "iPhone"};

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql;
        try {
            // 数据库 IP 是本机虚拟机 IP
            connection = DriverManager.getConnection("jdbc:mysql://192.168.163.128:3306/qlydb", "root", "123456");
            sql = "select * from tbl_order";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String orderId = resultSet.getString("ORDER_ID");
                String userId = resultSet.getString("USER_ID");
                String goodName = resultSet.getString("GOOD_NAME");
                System.out.println("User " + userId + " bought a " + goodName + ", the order id is " + orderId);
            }

            sql = "insert into tbl_order (ORDER_ID, USER_ID, GOOD_NAME) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "202011180001");
            statement.setString(2, "0091");
            statement.setString(3, "T-shirt");
            statement.execute();

            sql = "update tbl_order t set t.good_name = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "jacket");
            statement.execute();

            sql = "delete from tbl_order where order_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "202011180001");
            statement.executeUpdate();

            statement = connection.prepareStatement("insert into tbl_order (ORDER_ID, USER_ID, GOOD_NAME) VALUES (?, ?, ?)");
            for (int i = 0; i < 2; i++) {
                statement.setString(1, ORDERIDS[i]);
                statement.setString(2, USERIDS[i]);
                statement.setString(3, GOODS[i]);
                statement.addBatch();
            }
            statement.executeBatch();
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
