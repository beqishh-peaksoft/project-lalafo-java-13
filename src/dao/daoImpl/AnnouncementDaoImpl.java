package dao.daoImpl;

import dao.AnnouncementDao;
import database.Database;
import exception.StackOverflowException;
import model.Announcement;
import model.User;

import java.util.*;

public class AnnouncementDaoImpl implements AnnouncementDao {
    @Override
    public String addUserAnnouncement(Long userId, Announcement announcement) {
        try {
            for (User user : Database.users) {
                if (user.getId().equals(userId)) {
                    user.setAnnouncement(announcement);
                    return "Объявление упешно добавлено!";
                }
            }
            throw new StackOverflowException("Произошла ошибка при добавлении объявления: " + announcement);
        } catch (StackOverflowException e) {
            return "";
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
                        return "Объявление успешно обновлено!";
                    }
                }
            }
            throw new StackOverflowException("Произошла ошибка при обновлении объявления: " + announcementId);
        } catch (StackOverflowException e) {
            return "";
        }
    }

    @Override
    public String deleteAnnouncement(Long announcementId) {
        try {
            for (User user : Database.users) {
                for (Announcement announcement : user.getAnnouncements()) {
                    if (announcement.getId().equals(announcementId)) {
                        user.getAnnouncements().remove(announcement);
                        return "Объявление успешно удалено!";
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
                throw new StackOverflowException("Пользователь по id: " + userId + " не найдено! ❌");
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Announcement> sortByName(String ascOrdesc) {
        try {
            for (User user : Database.users) {
                if (ascOrdesc.equalsIgnoreCase("asc".toLowerCase())) {
                    user.getAnnouncements().sort(Comparator.comparing(Announcement::getName));
                    return user.getAnnouncements();
                } else if (ascOrdesc.equalsIgnoreCase("desc".toLowerCase())) {
                    user.getAnnouncements().sort(Comparator.comparing(Announcement::getName).reversed());
                    return user.getAnnouncements();
                } else
                    throw new StackOverflowException("Вы должны ввести asc/desc а не это: " + ascOrdesc+"! ❌");
            }
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
            return null;
    }

    @Override
    public List<Announcement> sortByPrice(String ascOrdesc) {
        try {
            for (User user : Database.users) {
                if (ascOrdesc.equalsIgnoreCase("asc".toLowerCase())) {
                    user.getAnnouncements().sort(Comparator.comparing(Announcement::getPrice));
                    return user.getAnnouncements();
                } else if (ascOrdesc.equalsIgnoreCase("desc".toLowerCase())) {
                    user.getAnnouncements().sort(Comparator.comparing(Announcement::getPrice).reversed());
                    return user.getAnnouncements();
                } else
                    throw new StackOverflowException("Вы должны ввести asc/desc а не это: " + ascOrdesc+"! ❌");
            }
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
