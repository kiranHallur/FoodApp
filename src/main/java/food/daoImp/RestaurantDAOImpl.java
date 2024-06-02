package food.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import food.dao.RestaurantDAO;
import food.dao.UserDAO;
import food.moduels.Restaurant;
import food.moduels.User;

public class RestaurantDAOImpl implements RestaurantDAO {
			 
	private Connection connection = null;
	private PreparedStatement statement = null;
	
	private static final String url = "jdbc:mysql://localhost:3306/food_delivery";
	private static final String username = "root";
	private static final String password = "root";
	private static final String sql5 = "select * from restaurants";


	public RestaurantDAOImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connection successful");
		} catch (ClassNotFoundException  | SQLException e) {
			e.printStackTrace();
			System.out.println("Database connection failed");
		}
	}

	

	@Override
	public int addRestaurant(Restaurant restaurant) {
	    try {
	        String sqlAddRestaurant = "INSERT INTO restaurants (name, cuisineType, deliveryTime, address, adminUserId, rating, isActive, imagePath) " +
	                                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	        statement = connection.prepareStatement(sqlAddRestaurant);
	        statement.setString(1, restaurant.getName());
	        statement.setString(2, restaurant.getCuisineType());
	        statement.setInt(3, restaurant.getDeliveryTime());
	        statement.setString(4, restaurant.getAddress());
	        statement.setInt(5, restaurant.getAdminUserId());
	        statement.setDouble(6, restaurant.getRating());
	        statement.setBoolean(7, restaurant.isIsactive());
	        statement.setString(8, restaurant.getImagePath());

	        return statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
	    try {
	        String sqlGetRestaurant = "SELECT * FROM restaurants WHERE restaurantId = ?";
	        statement = connection.prepareStatement(sqlGetRestaurant);
	        statement.setInt(1, restaurantId);

	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            // Map the result set to a Restaurant object
	            return new Restaurant(
	                    rs.getInt("restaurantId"),
	                    rs.getString("name"),
	                    rs.getString("cuisineType"),
	                    rs.getInt("deliveryTime"),
	                    rs.getString("address"),
	                    rs.getInt("adminUserId"),
	                    rs.getDouble("rating"),
	                    rs.getBoolean("isActive"),
	                    rs.getString("imagePath")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public int updateRestaurant(Restaurant restaurant) {
	    try {
	        String sqlUpdateRestaurant = "UPDATE restaurants SET name = ?, cuisineType = ?, deliveryTime = ?, " +
	                                     "address = ?, adminUserId = ?, rating = ?, isActive = ?, imagePath = ? " +
	                                     "WHERE restaurantId = ?";

	        statement = connection.prepareStatement(sqlUpdateRestaurant);
	        statement.setString(1, restaurant.getName());
	        statement.setString(2, restaurant.getCuisineType());
	        statement.setInt(3, restaurant.getDeliveryTime());
	        statement.setString(4, restaurant.getAddress());
	        statement.setInt(5, restaurant.getAdminUserId());
	        statement.setDouble(6, restaurant.getRating());
	        statement.setBoolean(7, restaurant.isIsactive());
	        statement.setString(8, restaurant.getImagePath());
	        statement.setInt(9, restaurant.getRestaurantid()); // Assuming getRestaurantid() is correctly named

	        return statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}


	@Override
	public int deleteRestaurant(int restaurantId) {
	    try {
	        String sqlDeleteRestaurant = "DELETE FROM restaurants WHERE restaurantId = ?";
	        statement = connection.prepareStatement(sqlDeleteRestaurant);
	        statement.setInt(1, restaurantId);

	        return statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return 0;
	}
	
	@Override
	public List<Restaurant> getAllRestaurants() {
	    List<Restaurant> restaurants = new ArrayList<>();
	    System.out.println(restaurants);

//	    String sql = "SELECT * FROM Restaurants";
	    try {
	    	statement = connection.prepareStatement(sql5);
	         ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            int restaurantId = rs.getInt("restaurantId");
	            String name = rs.getString("name");
	            String cuisineType = rs.getString("cuisineType");
	            int deliveryTime = rs.getInt("deliveryTime");
	            String address = rs.getString("address");
	            int adminUserId = rs.getInt("adminUserId");
	            double rating = rs.getDouble("rating");
	            boolean isActive = rs.getBoolean("isActive");
	            String imagePath = rs.getString("imagePath");

	            Restaurant restaurant = new Restaurant(
	                    restaurantId, name, cuisineType, deliveryTime, address,
	                    adminUserId, rating, isActive, imagePath
	            );

	            restaurants.add(restaurant);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Replace with more robust error handling/logging
	    }
	    
	    return restaurants;
	}

	

	

}
