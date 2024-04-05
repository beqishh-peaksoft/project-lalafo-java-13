package generedId;

public class GeneratorId {
    public static Long announcementId = 0L;
    public static Long favoriteId = 0L;
    public static Long userId = 0L;

    public static Long genAnnouncementId() {
        return ++announcementId;
    }

    public static Long genFavoriteId() {
        return ++favoriteId;
    }

    public static Long genUserId() {
        return ++userId;
    }
}