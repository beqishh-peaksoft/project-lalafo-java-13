package dao;

import model.User;

import java.util.List;

public interface UserDao {
    String addUser (User user);
    User getUserById(Long userId);
    List<User> getAllUser();
    String updateUserById(Long userId,User newUser);
    String deleteUserById(Long userId);
}
