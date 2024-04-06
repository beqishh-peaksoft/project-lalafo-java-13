package serivce.serviceImpl;

import dao.FavoriteDao;
import dao.daoImpl.FavoriteDaoImpl;
import exception.StackOverflowException;
import model.Favorite;
import serivce.FavoriteService;

import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {

    FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public String addFavorite(Long userId, Favorite favorite) throws StackOverflowException {
        return favoriteDao.addFavorite(userId,favorite);
    }

    @Override
    public String deleteFavorite(Long favoriteId) throws StackOverflowException {
        return favoriteDao.deleteFavorite(favoriteId);
    }

    @Override
    public Favorite getFavoriteById(Long favoriteId) throws StackOverflowException {
        return favoriteDao.getFavoriteById(favoriteId);
    }

    @Override
    public List<Favorite> getAllFavoritesByUserId(Long userId) throws StackOverflowException {
        return favoriteDao.getAllFavoritesByUserId(userId);
    }
}
