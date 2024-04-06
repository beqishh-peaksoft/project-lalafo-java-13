package serivce.serviceImpl;

import dao.AnnouncementDao;
import dao.daoImpl.AnnouncementDaoImpl;
import model.Announcement;
import serivce.AnnouncementService;

import java.util.List;

public class AnnouncementServiceImpl implements AnnouncementService {

    AnnouncementDao announcementDao = new AnnouncementDaoImpl();

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
    public List<Announcement> getAllAnnouncements(Long userId) {
        return announcementDao.getAllAnnouncements(userId);
    }

    @Override
    public List<Announcement> sortByName(String ascOrdesc) {
        return announcementDao.sortByName(ascOrdesc);
    }

    @Override
    public List<Announcement> sortByPrice(String ascOrdesc) {
        return announcementDao.sortByPrice(ascOrdesc);
    }
}
