package serivce;

import exception.StackOverflowException;
import model.Favorite;

import java.util.List;

public interface FavoriteService {

    String addFavorite(Long userId, Favorite favorite) throws StackOverflowException;
    String deleteFavorite(Long favoriteId) throws StackOverflowException;
    Favorite getFavoriteById(Long favoriteId) throws StackOverflowException;
    List<Favorite> getAllFavoritesByUserId(Long userId) throws StackOverflowException;
}
