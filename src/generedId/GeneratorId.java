package generedId;

public class GeneratorId {
    public static Long announcementId = 0L;
    public static Long favoriteId = 0L;
    public static Long userId = 0L;

    public static Long idAnnouncementId() {
        return ++announcementId;
    }
    public static Long idFavorite() {
        return ++favoriteId;
    }
    public static Long idUser() {
        return ++userId;
    }

}