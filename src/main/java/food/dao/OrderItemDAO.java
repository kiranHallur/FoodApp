package food.dao;

import java.util.List;

import food.moduels.OrderItem;

public interface OrderItemDAO {
	
	int addOrderItem(OrderItem orderItem);
	OrderItem getOrderItem(int orderItemId); 
	int updateOrderItem(OrderItem orderItem);
	int deleteOrderItem(int orderItemId);
	List<OrderItem> getOrderItemsByOrder(int orderId);
	
}
