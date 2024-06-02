package food.dao;

import java.util.List;

import food.moduels.Order;

public interface OrderDAO {

	int addOrder(Order order);
	Order getOrder(int orderId);
	int updateOrder(Order order);
	int deleteOrder(int orderId);
	List<Order> getAllOrderByUser(int userId);
	
}
