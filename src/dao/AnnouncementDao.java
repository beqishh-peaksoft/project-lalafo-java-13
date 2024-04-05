package dao;

import model.Announcement;
import model.Favorite;

import java.util.List;

public interface AnnouncementDao {
    String addUserAnnouncement(Announcement announcement);
    String updateAnnouncement(Long announcementId, Announcement newAnnouncement);
    String deleteAnnouncement(Long announcementId);
    List<Announcement> getAllAnnouncements();
    List<Favorite> getAllFavorites();
}
