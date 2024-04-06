package dao.daoImpl;

import dao.UserDao;
import database.Database;
import enums.Role;
import exception.StackOverflowException;
import model.User;

import java.util.*;

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

    @Override
    public List<User> sortByFirstName(String ascOrdesc) {
        try {
            if (ascOrdesc.equalsIgnoreCase("asc".toLowerCase())) {
                Database.users.sort(Comparator.comparing(User::getFirstName));
                return Database.users;
            } else if (ascOrdesc.equalsIgnoreCase("desc".toLowerCase())) {
                Database.users.sort(Comparator.comparing(User::getFirstName).reversed());
                return Database.users;
            } else
                throw new StackOverflowException("вы должны вести asc/desc а не ето:"+ascOrdesc);
        }catch (StackOverflowException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<User> sortByRole(String role) {
        try {
            List<User> users = new ArrayList<>();
            List<User> vendor = new ArrayList<>();
            if (role.equalsIgnoreCase("USER".toUpperCase())) {
                for (User user : Database.users) {
                    if (user.getRole().equals(Role.USER)) {
                        users.add(user);
                    }
                }
                return users;
            } else if (role.equalsIgnoreCase("VENDOR".toUpperCase())) {
                for (User user : Database.users) {
                    if (user.getRole().equals(Role.VENDOR)){
                        vendor.add(user);
                    }
                }
                return vendor;
            }else
                throw new StackOverflowException(role+": Такого роля нету уком!❌");
        }catch (StackOverflowException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
