package food.dao;

import java.util.List;

import food.moduels.Order;
import food.moduels.OrderHistory;

public interface OrderHistoryDAO {
	int addOrderHistory (OrderHistory orderHistory);
	OrderHistory getOrderHistory(int orderHistoryId);
	int updateOrderHistory (OrderHistory orderHistory); 
	int deleteOrderHistory(int orderHistoryId);
	List<OrderHistory> getOrderHistoriesByUser(int userId);

}
