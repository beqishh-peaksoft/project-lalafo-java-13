package serivce.serviceImpl;

import dao.UserDao;
import model.User;
import serivce.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public String updateUserById(Long userId, User newUser) {
        return userDao.updateUserById(userId,newUser);
    }

    @Override
    public String deleteUserById(Long userId) {
        return userDao.deleteUserById(userId);
    }
}
