package serivce;

import model.Announcement;

import java.util.List;

public interface AnnouncementService {


    String addUserAnnouncement(Long userId, Announcement announcement);
    String updateAnnouncement(Long announcementId, Announcement newAnnouncement);
    String deleteAnnouncement(Long announcementId);
    List<Announcement> getAllAnnouncements(Long userId);
    List<Announcement> sortByName(String ascOrdesc);
    List<Announcement> sortByPrice(String ascOrdesc);

}
