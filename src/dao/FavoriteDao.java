package dao;

import model.Favorite;

import java.util.List;

public interface FavoriteDao {
    String addFavorite(Long userId, Favorite favorite);
    String deleteFavorite(Long favoriteId);
    Favorite getFavoriteById(Long favoriteId);
    List<Favorite> getAllFavoritesByUserId(Long userId);
}
