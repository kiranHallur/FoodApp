package food.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import food.dao.OrderHistoryDAO;
import food.moduels.OrderHistory;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private static final String url = "jdbc:mysql://localhost:3306/food_delivery";
    private static final String username = "root";
    private static final String password = "root";

    public OrderHistoryDAOImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrderHistory(OrderHistory orderHistory) {
        try {
            String sqlAddOrderHistory = "INSERT INTO orderhistory (userId, orderId, orderDate, totalAmount, status) " +
                    "VALUES (?, ?, ?, ?, ?)";

            statement = connection.prepareStatement(sqlAddOrderHistory);
            statement.setInt(1, orderHistory.getUserId());
            statement.setInt(2, orderHistory.getOrderId());
            statement.setTimestamp(3, new java.sql.Timestamp(orderHistory.getOrderDate().getTime()));
            statement.setDouble(4, orderHistory.getTotalAmount());
            statement.setString(5, orderHistory.getStatus());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public OrderHistory getOrderHistory(int orderHistoryId) {
        try {
            String sqlGetOrderHistory = "SELECT * FROM orderhistory WHERE orderHistoryId = ?";
            statement = connection.prepareStatement(sqlGetOrderHistory);
            statement.setInt(1, orderHistoryId);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setOrderHistoryId(rs.getInt("orderHistoryId"));
                orderHistory.setUserId(rs.getInt("userId"));
                orderHistory.setOrderId(rs.getInt("orderId"));
                orderHistory.setOrderDate(rs.getTimestamp("orderDate"));
                orderHistory.setTotalAmount(rs.getDouble("totalAmount"));
                orderHistory.setStatus(rs.getString("status"));

                return orderHistory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateOrderHistory(OrderHistory orderHistory) {
        try {
            String sqlUpdateOrderHistory = "UPDATE orderhistory SET userId = ?, orderId = ?, orderDate = ?, " +
                    "totalAmount = ?, status = ? WHERE orderHistoryId = ?";

            statement = connection.prepareStatement(sqlUpdateOrderHistory);
            statement.setInt(1, orderHistory.getUserId());
            statement.setInt(2, orderHistory.getOrderId());
            statement.setTimestamp(3, new java.sql.Timestamp(orderHistory.getOrderDate().getTime()));
            statement.setDouble(4, orderHistory.getTotalAmount());
            statement.setString(5, orderHistory.getStatus());
            statement.setInt(6, orderHistory.getOrderHistoryId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteOrderHistory(int orderHistoryId) {
        try {
            String sqlDeleteOrderHistory = "DELETE FROM orderhistory WHERE orderHistoryId = ?";
            statement = connection.prepareStatement(sqlDeleteOrderHistory);
            statement.setInt(1, orderHistoryId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OrderHistory> getOrderHistoriesByUser(int userId) {
        List<OrderHistory> orderHistories = new ArrayList<>();

        try {
            String sqlGetOrderHistories = "SELECT * FROM orderhistory WHERE userId = ?";
            statement = connection.prepareStatement(sqlGetOrderHistories);
            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setOrderHistoryId(rs.getInt("orderHistoryId"));
                orderHistory.setUserId(rs.getInt("userId"));
                orderHistory.setOrderId(rs.getInt("orderId"));
                orderHistory.setOrderDate(rs.getTimestamp("orderDate"));
                orderHistory.setTotalAmount(rs.getDouble("totalAmount"));
                orderHistory.setStatus(rs.getString("status"));

                orderHistories.add(orderHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderHistories;
    }
}
