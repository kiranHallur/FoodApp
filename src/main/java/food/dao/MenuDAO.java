package food.dao;

import java.util.List;

import food.moduels.Menu;

public interface MenuDAO {

	int addMenu(Menu menu);
	Menu getMenu(int menuId);
	int upadteMenu(Menu menu);
	int deleteMenu(int menuId);
	List<Menu> getAllMenuByRestaurant(int restaurantId);

}

