package org.qly.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.pool.HikariPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyHikariDemo {
    private static final String[] ORDERIDS = {"202011180002", "202011180003"};
    private static final String[] USERIDS = {"4466", "9998"};
    private static final String[] GOODS = {"MacBook", "iPhone"};

    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        // 数据库 IP 是本机虚拟机 IP
        config.setJdbcUrl("jdbc:mysql://192.168.163.128:3306/qlydb");
        config.setUsername("root");
        config.setPassword("123456");
        config.setMinimumIdle(2);
        config.setMaximumPoolSize(2);
        config.setPoolName("myTestPool");

        HikariPool pool = new HikariPool(config);

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql;
        try {
            sql = "select * from tbl_order";
            statement = pool.getConnection().prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String orderId = resultSet.getString("ORDER_ID");
                String userId = resultSet.getString("USER_ID");
                String goodName = resultSet.getString("GOOD_NAME");
                System.out.println("User " + userId + " bought a " + goodName + ", the order id is " + orderId);
            }

            sql = "insert into tbl_order (ORDER_ID, USER_ID, GOOD_NAME) VALUES (?, ?, ?)";
            statement = pool.getConnection().prepareStatement(sql);
            statement.setString(1, "202011180001");
            statement.setString(2, "0091");
            statement.setString(3, "T-shirt");
            statement.execute();

            sql = "update tbl_order t set t.good_name = ? where t.order_id = ?";
            statement = pool.getConnection().prepareStatement(sql);
            statement.setString(1, "iPad");
            statement.setString(2, "202011180001");
            statement.execute();

            sql = "delete from tbl_order where order_id = ?";
            statement = pool.getConnection().prepareStatement(sql);
            statement.setString(1, "202011180001");
            statement.executeUpdate();

            statement = pool.getConnection().prepareStatement("insert into tbl_order (ORDER_ID, USER_ID, GOOD_NAME) VALUES (?, ?, ?)");
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
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                pool.shutdown();
            } catch (SQLException | InterruptedException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
