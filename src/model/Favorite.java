package model;

public class Favorite {
    private Long id;
    private User user;
    private Announcement announcement;

    public Favorite() {
    }

    public Favorite(Long id, User user, Announcement announcement) {
        this.id = id;
        this.user = user;
        this.announcement = announcement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    @Override
    public String toString() {
        return "\nFavorite{" +
               "id=" + id +
               ", user=" + user +
               ", announcement=" + announcement +
               '}';
    }
}
