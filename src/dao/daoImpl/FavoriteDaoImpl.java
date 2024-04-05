package dao.daoImpl;

import dao.FavoriteDao;
import database.Database;
import exception.StackOverflowException;
import model.Favorite;
import model.User;

import java.util.List;
import java.util.Optional;

public class FavoriteDaoImpl implements FavoriteDao {
    @Override
    public String addFavorite(Long userId, Favorite favorite) throws StackOverflowException {
        Optional<User> userToUpdate = Database.users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
        if (userToUpdate.isPresent()) {
            User user = userToUpdate.get();
            user.getFavorites().add(favorite);
            return "Успешно добавлено! ✅";
        } else {
            throw new StackOverflowException("Андай ID менен user табылган жок! ❌");
        }
    }

    @Override
    public String deleteFavorite(Long favoriteId) throws StackOverflowException {
        Optional<User> userWithFavorite = Database.users.stream()
                .filter(user -> user.getFavorites().stream()
                        .anyMatch(favorite -> favorite.getId().equals(favoriteId)))
                .findFirst();
        if (userWithFavorite.isPresent()) {
            User user = userWithFavorite.get();
            user.getFavorites().removeIf(favorite -> favorite.getId().equals(favoriteId));
            return "Успешно удалено! ✅";
        } else {
            throw new StackOverflowException("Фаворит с таким ID не существует! ❌");
        }
    }

    @Override
    public Favorite getFavoriteById(Long favoriteId) throws StackOverflowException {
        Optional<Favorite> favorite = Database.users.stream()
                .flatMap(user -> user.getFavorites().stream())
                .filter(f -> f.getId().equals(favoriteId))
                .findFirst();

        if (favorite.isPresent()) {
            return favorite.get();
        } else {
            throw new StackOverflowException("Фаворит с таким ID не найден! ❌");
        }
    }

    @Override
    public List<Favorite> getAllFavoritesByUserId(Long userId) throws StackOverflowException {
        Optional<User> user = Database.users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst();

        if (user.isPresent()) {
            return user.get().getFavorites();
        } else {
            throw new StackOverflowException("Андай ID деги user жок! ❌");
        }
    }
}
