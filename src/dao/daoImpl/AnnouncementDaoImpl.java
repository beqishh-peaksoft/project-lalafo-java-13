package dao.daoImpl;

import dao.AnnouncementDao;
import database.Database;
import exception.StackOverflowException;
import model.Announcement;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AnnouncementDaoImpl implements AnnouncementDao {
    @Override
    public String addUserAnnouncement(Long userId, Announcement announcement) {
        try {
            for (User user : Database.users) {
                if (user.getId().equals(userId)) {
                    user.setAnnouncement(announcement);
                    return "Announcement success added!";
                }
            }
            return "User не найден!";
        } catch (Exception e) {
            return "Произошла ошибка при добавлении объявления: " + announcement;
        }
    }

    @Override
    public String updateAnnouncement(Long announcementId, Announcement newAnnouncement) {
        try {
            for (User user : Database.users) {
                for (Announcement announcement : user.getAnnouncements()) {
                    if (announcement.getId().equals(announcementId)) {
                        announcement.setName(newAnnouncement.getName());
                        announcement.setDescription(newAnnouncement.getDescription());
                        announcement.setPrice(newAnnouncement.getPrice());
                        announcement.setOwner(newAnnouncement.getOwner());
                        return "Announcement success updated!";
                    }
                }
            }
            return "Announcement not found!";
        } catch (Exception e) {
            return "Произошла ошибка при обновлении объявления: " + announcementId;
        }
    }

    @Override
    public String deleteAnnouncement(Long announcementId) {
        try {
            for (User user : Database.users) {
                for (Announcement announcement : user.getAnnouncements()) {
                    if (announcement.getId().equals(announcementId)) {
                        user.getAnnouncements().remove(announcement);
                        return "Announcement success deleted!";
                    }
                }
            }
            throw new StackOverflowException("Произошла ошибка при удалении объявления: " + announcementId);
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
    @Override
    public List<Announcement> getAllAnnouncements(Long userId) {
        try {
            Optional<User> userOptional = Database.users.stream()
                    .filter(user -> user.getId().equals(userId))
                    .findFirst();
            if (userOptional.isPresent()) {
                return userOptional.get().getAnnouncements();
            } else
                throw new StackOverflowException("User по id:" + userId + "не найдено");
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
