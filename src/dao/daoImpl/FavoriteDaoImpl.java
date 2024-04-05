package dao.daoImpl;

import dao.FavoriteDao;
import database.Database;
import exception.StackOverflowException;
import model.Favorite;
import model.User;

import java.util.List;

public class FavoriteDaoImpl implements FavoriteDao {
    @Override
    public String addFavorite(Long userId, Favorite favorite) {
        try {
            for (User user : Database.users) {
                if (user.getId().equals(userId)) {
                    user.setFavorite(favorite);
                    return "Успешно добавлено! ✅";
                }
            }
            throw new StackOverflowException("Андай ID менен user табылган жок! ❌");
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteFavorite(Long favoriteId) {
        try {
            for (User user : Database.users) {
                for (Favorite favorite : user.getFavorites()) {
                    if (favorite.getId().equals(favoriteId)) {
                        user.removeFavorite(favorite);
                        return "Успешно удалено! ✅";
                    }
                }
            }
            throw new StackOverflowException("Фаворит с таким id не существует! ❌");
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Favorite getFavoriteById(Long favoriteId) {
        try {
            for (User user : Database.users) {
                for (Favorite favorite : user.getFavorites()) {
                    if (favorite.getId().equals(favoriteId)) {
                        return favorite;
                    }
                }
            }
            throw new StackOverflowException("Фаворит с таким ID не найден! ❌");
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Favorite> getAllFavoritesByUserId(Long userId) {
        try {
            for (User user : Database.users) {
                if (user.getId().equals(userId)) {
                    return user.getFavorites();
                }
            }
            throw new StackOverflowException("Андай ID деги user жок! ❌");
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
