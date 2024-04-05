package serivce.serviceImpl;

import dao.AnnouncementDao;
import model.Announcement;
import serivce.AnnouncementService;

import java.util.List;

public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementDao announcementDao;

    public AnnouncementServiceImpl(AnnouncementDao announcementDao) {
        this.announcementDao = announcementDao;
    }

    @Override
    public String addUserAnnouncement(Long userId, Announcement announcement) {
       return announcementDao.addUserAnnouncement(userId,announcement);
    }

    @Override
    public String updateAnnouncement(Long announcementId, Announcement newAnnouncement) {
        return announcementDao.updateAnnouncement(announcementId,newAnnouncement);
    }

    @Override
    public String deleteAnnouncement(Long announcementId) {
        return announcementDao.deleteAnnouncement(announcementId);
    }

    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementDao.getAllAnnouncements();
    }
}
