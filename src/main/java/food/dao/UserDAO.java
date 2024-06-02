package food.dao;

import java.util.List;

import food.moduels.User;

public interface UserDAO {
	
	int addUser(User user);
	User getUser(int userId);
	int updateUser(User user);
	int deleteUser(int userId);
	List<User> getAllUser();

}
