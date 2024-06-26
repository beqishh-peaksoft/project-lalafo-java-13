package dao;

import model.Announcement;
import model.Favorite;

import java.util.List;

public interface AnnouncementDao {
    String addUserAnnouncement(Long userId, Announcement announcement);
    String updateAnnouncement(Long announcementId, Announcement newAnnouncement);
    String deleteAnnouncement(Long announcementId);
    List<Announcement> getAllAnnouncements(Long userId);
    List<Announcement> sortByName(String ascOrdesc);
    List<Announcement> sortByPrice(String ascOrdesc);
}
