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
            Database.users.stream()
                    .filter(x -> x.getId().equals(userId))
                    .findFirst()
                    .orElseThrow(() -> new StackOverflowException("Произошла ошибка при добавлении объявления: " + announcement.getName()))
                    .setAnnouncement(announcement);
            return "Объявление успешно добавлено!";
        } catch (StackOverflowException e) {
            return e.getMessage();
        }
    }

    @Override
    public String updateAnnouncement(Long announcementId, Announcement newAnnouncement) {
        try {
            for (User user : Database.users) {
                for (int i = 0; i < user.getAnnouncements().size(); i++) {
                    if (user.getAnnouncements().get(i).getId().equals(announcementId)) {
                        user.getAnnouncements().set(i, newAnnouncement);
                        return "Объявление успешно обновлено!";
                    }
                }
            }
            throw new StackOverflowException("Объявление с id: " + announcementId + " не найден!");
        } catch (StackOverflowException e) {
            return e.getMessage();
        }
    }

    @Override
    public String deleteAnnouncement(Long announcementId) {
        try {
            boolean isRemoved = false;

            for (User user : Database.users) {
                isRemoved = user.getAnnouncements().removeIf(x -> x.getId().equals(announcementId));
            }

            if (isRemoved) {
                return "Объявление успешно удалено!";
            } else {
                throw new StackOverflowException("Объявление с id: " + announcementId + " не найден!");
            }
        } catch (StackOverflowException e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Announcement> getAllAnnouncements(Long userId) {
        try {
            return Database.users.stream()
                    .filter(x -> x.getId().equals(userId))
                    .findFirst()
                    .orElseThrow(() -> new StackOverflowException("Пользователь c id: " + userId + " не найден! ❌"))
                    .getAnnouncements();
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Announcement> sortByName(String ascOrDesc) {
        try {
            for (User user : Database.users) {
                if (ascOrDesc.equalsIgnoreCase("asc")) {
                    user.getAnnouncements().sort(Comparator.comparing(Announcement::getName));
                    return user.getAnnouncements();
                } else if (ascOrDesc.equalsIgnoreCase("desc")) {
                    user.getAnnouncements().sort(Comparator.comparing(Announcement::getName).reversed());
                    return user.getAnnouncements();
                } else
                    throw new StackOverflowException("Вы можете ввести только asc/desc! ❌");
            }
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Announcement> sortByPrice(String ascOrDesc) {
        try {
            for (User user : Database.users) {
                if (ascOrDesc.equalsIgnoreCase("asc".toLowerCase())) {
                    user.getAnnouncements().sort(Comparator.comparing(Announcement::getPrice));
                    return user.getAnnouncements();
                } else if (ascOrDesc.equalsIgnoreCase("desc".toLowerCase())) {
                    user.getAnnouncements().sort(Comparator.comparing(Announcement::getPrice).reversed());
                    return user.getAnnouncements();
                } else
                    throw new StackOverflowException("Вы должны ввести asc/desc а не это: " + ascOrDesc + "! ❌");
            }
        } catch (StackOverflowException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
