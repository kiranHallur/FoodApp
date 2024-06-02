package food.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import food.dao.MenuDAO;
import food.moduels.Menu;

public class MenuDAOImpl implements MenuDAO{

	private Connection connection = null;
	private PreparedStatement statement = null;
	private static final String url = "jdbc:mysql://localhost:3306/food_delivery";
	private static final String username = "root";
	private static final String password = "root";
	
	public MenuDAOImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	 @Override
	    public int addMenu(Menu menu) {
	        // Implement logic to add a menu item to the database
	        try {
	            String sql = "INSERT INTO MenuItems (restaurantId, itemName, description, price, isAvailable) VALUES (?, ?, ?, ?, ?)";
	            statement = connection.prepareStatement(sql);

	            statement.setInt(1, menu.getRestaurantId());
	            statement.setString(2, menu.getItemName());
	            statement.setString(3, menu.getDescription());
	            statement.setDouble(4, menu.getPrice());
	            statement.setBoolean(5, menu.getisAvailable());

	            return statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0;
	    }

	    @Override
	    public Menu getMenu(int menuId) {
	        // Implement logic to retrieve a menu item by menuId from the database
	        try {
	            String sql = "SELECT * FROM MenuItems WHERE menuId = ?";
	            statement = connection.prepareStatement(sql);
	            statement.setInt(1, menuId);

	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                return extractMenuFromResultSet(resultSet);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	   
	    @Override
	    public int upadteMenu(Menu menu) {
	        // Implement logic to update a menu item in the database
	        try {
	            String sql = "UPDATE MenuItems SET restaurantId = ?, itemName = ?, description = ?, price = ?, isAvailable = ? WHERE menuId = ?";
	            statement = connection.prepareStatement(sql);

	            statement.setInt(1, menu.getRestaurantId());
	            statement.setString(2, menu.getItemName());
	            statement.setString(3, menu.getDescription());
	            statement.setDouble(4, menu.getPrice());
	            statement.setBoolean(5, menu.getisAvailable());
	            statement.setInt(6, menu.getMenuId());

	            return statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0;
	    }

	    @Override
	    public int deleteMenu(int menuId) {
	        // Implement logic to delete a menu item from the database
	        try {
	            String sql = "DELETE FROM MenuItems WHERE menuId = ?";
	            statement = connection.prepareStatement(sql);
	            statement.setInt(1, menuId);

	            return statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0;
	    }

	    @Override
	    public List<Menu> getAllMenuByRestaurant(int restaurantId) {
	        // Implement logic to retrieve all menu items for a specific restaurant from the database
	        List<Menu> menuList = new ArrayList<>();

	        try {
	            String sql = "SELECT * FROM MenuItems WHERE restaurantId = ?";
	            statement = connection.prepareStatement(sql);
	            statement.setInt(1, restaurantId);

	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                Menu menu = extractMenuFromResultSet(resultSet);
	                menuList.add(menu);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return menuList;
	    }

	    // Helper method to extract Menu object from ResultSet
	    private Menu extractMenuFromResultSet(ResultSet resultSet) throws SQLException {
	        return new Menu(
	                resultSet.getInt("menuId"),
	                resultSet.getInt("restaurantId"),
	                resultSet.getString("itemName"),
	                resultSet.getString("description"),
	                resultSet.getDouble("price"),
	                resultSet.getBoolean("isAvailable")
	        );
	    }
	}