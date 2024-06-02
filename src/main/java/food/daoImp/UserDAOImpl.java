package food.daoImp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import food.dao.UserDAO;
import food.moduels.User;
import food.utility.PasswordHashing;

public class UserDAOImpl implements UserDAO {

	private Connection connection = null;
	private PreparedStatement statement = null;
	private static final String url = "jdbc:mysql://localhost:3306/food_delivery";
	private static final String username = "root";
	private static final String password = "root";
	private static final String sql1 = "INSERT INTO Users " + "(userName, password, email, address, role) "
			+ "VALUES (?, ?, ?, ?, ?)";
	private static final String sql2 = "SELECT * FROM Users WHERE userName = ? AND password = ?";
	private static final String sql3 = "SELECT * FROM Users WHERE userid = ? ";
	private static final String sql4 = "UPDATE Users " +
            "SET  userName = ?, password = ?, email = ?, address = ?, role = ? " +
            "WHERE userId = ?";
	private static final String sql5 = "Delete  FROM Users WHERE userid = ? ";
	private static final String sql6 = "SELECT * FROM users";
	
	public UserDAOImpl() {
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
	public int addUser(User user) {
	    try {
	        statement = connection.prepareStatement(sql1);
	        statement.setString(1, user.getUserName());
	        
	        // Hash the password before storing it in the database
	        String hashedPassword = PasswordHashing.doHashing(user.getPassword());
	        statement.setString(2, hashedPassword);
	        
	        statement.setString(3, user.getEmail());
	        statement.setString(4, user.getAddress());
	        statement.setString(5, user.getRole());

	        int i = statement.executeUpdate();
	        return i;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}

	// Method for user authentication (login)
	public User loginUser(String userName, String password) {
	    try {
	        statement = connection.prepareStatement(sql2);
	        statement.setString(1, userName);
	        statement.setString(2, PasswordHashing.doHashing(password)); // Hash the provided password
	        
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            // User found, return the User object
	            User user = new User();
	            user.setUserId(resultSet.getInt("userId"));
	            user.setUserName(resultSet.getString("userName"));
	            user.setEmail(resultSet.getString("email"));
	            user.setAddress(resultSet.getString("address"));
	            user.setRole(resultSet.getString("role"));
	            return user;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public User getUser(int userId) {
		try {
			statement = connection.prepareStatement(sql3);
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				// User found, return the User object
				User user = new User();
				user.setUserId(resultSet.getInt("userId"));
				user.setUserName(resultSet.getString("userName"));
				user.setEmail(resultSet.getString("email"));
				user.setAddress(resultSet.getString("address"));
				user.setRole(resultSet.getString("role"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int updatePassword(String emailid, String newPassword) {
	    try {
	        // Hash the new password before updating
	        String hashedPassword = PasswordHashing.doHashing(newPassword);

	        // Assuming you have the user's ID in the session
	        // Retrieve the existing user details for the given userId
	        

	        // Update only the password field
	        String sql = "UPDATE Users SET password = ? WHERE email  = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, hashedPassword);
            statement.setString(2, emailid);

            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }



	@Override
	public int updateUser(User user) {
		try {
			statement = connection.prepareStatement(sql4);
			
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user. getEmail());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getRole());
			statement.setInt(6, user.getUserId());
			
			
			return  statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		 

	}

	@Override
	public int deleteUser(int userId) {
	    try (PreparedStatement statement = connection.prepareStatement(sql5)) {
	        statement.setInt(1, userId);
	        return statement.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
	    return 0;
	}

	@Override
	public List<User> getAllUser() {
	    List<User> userList = new ArrayList<>();

	    try {
	      
	        statement = connection.prepareStatement(sql6);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            User user = new User();
	            user.setUserId(resultSet.getInt("userId"));
	            user.setUserName(resultSet.getString("userName"));
	            user.setPassword(resultSet.getString("password"));
	            user.setEmail(resultSet.getString("email"));
	            user.setAddress(resultSet.getString("address"));
	            user.setRole(resultSet.getString("role"));

	            userList.add(user);
	        }
	    } catch (SQLException e) {
	        // Log or handle the exception appropriately
	        e.printStackTrace();
	    }

	    return userList;
	}


}
