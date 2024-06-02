package food.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import food.dao.OrderItemDAO;
import food.moduels.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private static final String url = "jdbc:mysql://localhost:3306/food_delivery";
    private static final String username = "root";
    private static final String password = "root";

    public OrderItemDAOImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrderItem(OrderItem orderItem) {
        try {
            String sql = "INSERT INTO orderitems ( menuId, quantity, itemTotal) " +
                         "VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, orderItem.getMenuId());
            statement.setInt(2, orderItem.getQuantity());
            statement.setDouble(3, orderItem.getItemTotal());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        try {
            String sql = "SELECT * FROM orderitems WHERE orderItemId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderItemId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return extractOrderItemFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateOrderItem(OrderItem orderItem) {
        try {
            String sql = "UPDATE orderitems SET  menuId = ?, quantity = ?, " +
                         "itemTotal = ? WHERE orderItemId = ?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, orderItem.getMenuId());
            statement.setInt(2, orderItem.getQuantity());
            statement.setDouble(3, orderItem.getItemTotal());
            statement.setInt(4, orderItem.getOrderItemId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteOrderItem(int orderItemId) {
        try {
            String sql = "DELETE FROM order_items WHERE orderItemId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderItemId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrder(int orderId) {
        List<OrderItem> orderItemList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM order_items WHERE orderId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderItem orderItem = extractOrderItemFromResultSet(resultSet);
                orderItemList.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderItemList;
    }

    // Helper method to extract OrderItem object from ResultSet
    private OrderItem extractOrderItemFromResultSet(ResultSet resultSet) throws SQLException {
        return new OrderItem(
                resultSet.getInt("orderItemId"),
                resultSet.getInt("orderId"),
                resultSet.getInt("menuId"),
                resultSet.getInt("quantity"),
                resultSet.getDouble("itemTotal")
        );
    }
}
