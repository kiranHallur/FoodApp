package food.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import food.dao.OrderDAO;
import food.moduels.Order;

public class OrderDAOImpl implements OrderDAO {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private static final String url = "jdbc:mysql://localhost:3306/food_delivery";
    private static final String username = "root";
    private static final String password = "root";

    public OrderDAOImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrder(Order order) {
        try {
            String sql = "INSERT INTO orders (userId, restaurantId, orderDate, totalAmount, status, paymentMethod) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getRestaurantId());
            statement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            statement.setDouble(4, order.getTotalAmount());
            statement.setString(5, order.getStatus());
            statement.setString(6, order.getPaymentMethod());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Order getOrder(int orderId) {
        try {
            String sql = "SELECT * FROM orders WHERE orderId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return extractOrderFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    

    @Override
    public int updateOrder(Order order) {
        try {
            String sql = "UPDATE orders SET userId = ?, restaurantId = ?, orderDate = ?, " +
                         "totalAmount = ?, status = ?, paymentMethod = ? WHERE orderId = ?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getRestaurantId());
            statement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            statement.setDouble(4, order.getTotalAmount());
            statement.setString(5, order.getStatus());
            statement.setString(6, order.getPaymentMethod());
            statement.setInt(7, order.getOrderId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteOrder(int orderId) {
        try {
            String sql = "DELETE FROM orders WHERE orderId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Order> getAllOrderByUser(int userId) {
        List<Order> orderList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM orders WHERE userId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = extractOrderFromResultSet(resultSet);
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    // Helper method to extract Order object from ResultSet
    private Order extractOrderFromResultSet(ResultSet resultSet) throws SQLException {
        return new Order(
                resultSet.getInt("orderId"),
                resultSet.getInt("userId"),
                resultSet.getInt("restaurantId"),
                resultSet.getDate("orderDate"),
                resultSet.getDouble("totalAmount"),
                resultSet.getString("status"),
                resultSet.getString("paymentMethod")
        );
    }

	
}
