package food.dao;

import java.util.List;

import food.moduels.Restaurant;

public interface RestaurantDAO {

	int addRestaurant(Restaurant restaurant);
	Restaurant getRestaurant(int restaurantId);
	int updateRestaurant(Restaurant restaurant);
	int deleteRestaurant(int restaurantId);
	List<Restaurant> getAllRestaurants();
	
}
