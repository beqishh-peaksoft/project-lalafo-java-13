package model;

import enums.Role;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String firstName;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;
    private List<Announcement> announcements = new ArrayList<>();
    private List<Favorite> favorites = new ArrayList<>();

    public User() {
    }

    public User(Long id, String firstName, String email, String password, String phoneNumber, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncement(Announcement announcement) {
        announcements.add(announcement);
    }

    public void removeAnnouncement(Announcement announcement) {
        announcements.remove(announcement);
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorite(Favorite favorite) {
        favorites.add(favorite);
    }

    public void removeFavorite(Favorite favorite) {
        favorites.remove(favorite);
    }


    public  void addAnnouncement(Announcement announcement){
        announcements.add(announcement);
        System.out.println("Announcement success added!");
    }

    @Override
    public String toString() {
        return "\nUser{" +
               "id=" + id +
               ", firstName='" + firstName + '\'' +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", role=" + role +
               ", announcements=" + announcements +
               ", favorites=" + favorites +
               '}';
    }
}
