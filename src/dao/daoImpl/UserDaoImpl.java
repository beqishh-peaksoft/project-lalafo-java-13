package dao.daoImpl;

import dao.UserDao;
import database.Database;
import exception.StackOverflowException;
import model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @Override
    public String addUser(User user) {
        Database.users.add(user);
        return "Успешно добавлено! ✅";
    }

    @Override
    public User getUserById(Long userId) {
        try {
            Optional<User> userOptional = Database.users.stream()
                    .filter(user -> user.getId().equals(userId))
                    .findFirst();
            if (userOptional.isPresent()) {
                return userOptional.get();
            } else
                throw new StackOverflowException("User: " + userId + " Не найдено! ❌");
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return Database.users;
    }

    @Override
    public String updateUserById(Long userId, User newUser) {
        try {
            Optional<User> userOptional = Database.users.stream()
                    .filter(user -> user.getId().equals(userId))
                    .findFirst();
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setFirstName(newUser.getFirstName());
                user.setEmail(newUser.getEmail());
                user.setPassword(newUser.getPassword());
                user.setPhoneNumber(newUser.getPhoneNumber());
                return "User успешно обновлено!: "+user;
            } else
                throw new StackOverflowException("User: " + userId + " Не найдено! ❌");
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());

        }
        return "";
    }

    @Override
    public String deleteUserById(Long userId) {
        try {
            Optional<User> userOptional = Database.users.stream()
                    .filter(user -> user.getId().equals(userId))
                    .findFirst();
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Database.users.remove(user);
                return "Успешно удалено! ✅";
            } else
                throw new StackOverflowException(userId + ": Не найдено! ❌");
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
