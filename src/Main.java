import database.Database;
import enums.Role;
import exception.StackOverflowException;
import generedId.GeneratorId;
import model.Announcement;
import model.Favorite;
import model.User;
import serivce.AnnouncementService;
import serivce.FavoriteService;
import serivce.UserService;
import serivce.serviceImpl.AnnouncementServiceImpl;
import serivce.serviceImpl.FavoriteServiceImpl;
import serivce.serviceImpl.UserServiceImpl;

import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scannerNum = new Scanner(System.in);
        Scanner scannerLn = new Scanner(System.in);

        AnnouncementService announcementService = new AnnouncementServiceImpl();
        UserService userService = new UserServiceImpl();
        FavoriteService favoriteService = new FavoriteServiceImpl();

        User admin = new User(0L, "admin", "admin@gmail.com", "Admin123", "+996700303787", Role.ADMIN);

        while (true) {
            boolean isAuth = false;
            User currentUser = null;
            try {
                System.out.println("""
                        1) Вход
                        2) Регистрация
                        """);
                switch (scannerNum.nextInt()) {
                    case 1 -> {
                        System.out.println("Введите адрес эл-почты: ");
                        String authEmail = scannerLn.nextLine();

                        System.out.println("Введите пароль: ");
                        String authPassword = scannerLn.nextLine();

                        boolean isUserExists = false;
                        boolean isPasswordCorrect = false;

                        if (authEmail.equals(admin.getEmail()) && authPassword.equals(admin.getPassword())) {
                            while (true) {
                                try {
                                    System.out.println("""
                                            1) Добавить пользователя
                                            2) Получить пользователя по id
                                            3) Получить всех пользователей
                                            4) Обновить пользователя по id
                                            5) Удалить пользователя по id
                                            0) Выйти
                                            """);
                                    int command = scannerNum.nextInt();

                                    switch (command) {
                                        case 1 -> {
                                            User user = new User();

                                            System.out.println("Введите имя пользователя: ");
                                            String name = scannerLn.nextLine();
                                            user.setFirstName(name);

                                            System.out.println("Введите адрес эл-почты пользователя (->> @gmail.com <<-): ");
                                            String email = scannerLn.nextLine();

                                            if (email.contains("@gmail.com")) {
                                                user.setEmail(email);

                                                System.out.println("Введите пароль пользователя: ");
                                                String password = scannerLn.nextLine();
                                                if (password.length() >= 8) {
                                                    user.setPassword(password);
                                                    System.out.println("Введите номер телефона: (->> +996 <<-)");
                                                    String phoneNumber = scannerLn.nextLine();
                                                    if (phoneNumber.startsWith("+996")) {
                                                        user.setPhoneNumber(phoneNumber);
                                                        System.out.println("Введите роль пользователя (user/vendor): ");
                                                        Role role = Role.valueOf(scannerLn.nextLine().toUpperCase());
                                                        if (role == Role.USER || role == Role.VENDOR) {
                                                            user.setRole(role);
                                                            user.setId(GeneratorId.genUserId());
                                                            System.out.println(userService.addUser(user));
                                                        } else
                                                            throw new StackOverflowException("Извините но у нас есть только эти роли: user/vendor! ❌");
                                                    } else
                                                        throw new StackOverflowException("Ваш номер телефона не начинается: '+996'! ❌");
                                                } else
                                                    throw new StackOverflowException("Ваш пароль должен содержать не менее 8 символов! ❌");
                                            } else
                                                throw new StackOverflowException("Ваш адрес эл-почты не содержит: '@gmail.com'! ❌");
                                        }
                                        case 2 -> {
                                            System.out.println("Введите id пользователя которого хотите получить: ");
                                            System.out.println(userService.getUserById(scannerNum.nextLong()));
                                        }
                                        case 3 -> System.out.println(userService.getAllUser());
                                        case 4 -> {
                                            User user = new User();

                                            System.out.println("Введите id пользователя которого хотите обновить: ");
                                            Long id = scannerNum.nextLong();

                                            System.out.println("Введите новое имя пользователя: ");
                                            String name = scannerLn.nextLine();
                                            user.setFirstName(name);

                                            System.out.println("Введите новый адрес эл-почты (->> @gmail.com <<-): ");
                                            String email = scannerLn.nextLine();

                                            if (email.contains("@gmail.com")) {
                                                user.setEmail(email);
                                                System.out.println("Введите новый пароль: ");
                                                String password = scannerLn.nextLine();
                                                if (password.length() >= 8) {
                                                    user.setPassword(password);
                                                    System.out.println("Введите новый номер телефона (->> +996 <<-): ");
                                                    String phoneNumber = scannerLn.nextLine();
                                                    if (phoneNumber.startsWith("+996")) {
                                                        user.setPhoneNumber(phoneNumber);
                                                        System.out.println("Выберите новый роль (user/vendor): ");
                                                        Role role = Role.valueOf(scannerLn.nextLine().toUpperCase());
                                                        if (role == Role.USER || role == Role.VENDOR) {
                                                            user.setRole(role);
                                                            System.out.println(userService.updateUserById(id, user));
                                                        } else
                                                            throw new StackOverflowException("Извините но у нас есть только эти роли: user/vendor! ❌");
                                                    } else
                                                        throw new StackOverflowException("Ваш номер телефона не начинается: '+996'! ❌");
                                                } else
                                                    throw new StackOverflowException("Ваш пароль должен содержать не менее 8 символов! ❌");
                                            } else
                                                throw new StackOverflowException("Ваш адрес эл-почты не содержит: '@gmail.com'! ❌");
                                        }
                                        case 5 -> {
                                            System.out.println("Введите id пользователя которого хотите удалить: ");
                                            System.out.println(userService.deleteUserById(scannerNum.nextLong()));
                                        }
                                        case 0 -> System.out.println("Вы успешно вышли из аккаунта! ✅");
                                        default -> System.out.println("Такой команды не сушествует! ❌");
                                    }
                                    if (command == 0) {
                                        break;
                                    }
                                } catch (StackOverflowException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }

                        for (User user : Database.users) {
                            if (user.getEmail().equals(authEmail)) {
                                isUserExists = true;
                            } else {
                                break;
                            }
                            if (user.getPassword().equals(authPassword)) {
                                isPasswordCorrect = true;
                                currentUser = user;
                            }
                        }

                        if (!isUserExists) {
                            throw new StackOverflowException("Пользователь с указанным адресом эл-почты не найден! ❌");
                        } else if (!isPasswordCorrect) {
                            throw new StackOverflowException("Не правильный пароль! ❌");
                        }

                        isAuth = true;
                    }
                    case 2 -> {
                        System.out.println("Введите ваше имя: ");
                        String firstName = scannerLn.nextLine();

                        System.out.println("Введите ваш адрес эл-почты: ");
                        String authEmail = scannerLn.nextLine();

                        System.out.println("Придумайте пароль не менее 8 символов: ");
                        String authPassword = scannerLn.nextLine();

                        System.out.println("Повторите пароль: ");
                        String repeatPassword = scannerLn.nextLine();

                        System.out.println("Введите ваш номер телефона с '996': ");
                        String phoneNumber = scannerLn.nextLine();

                        System.out.println("Введите роль пользователя (user/vendor): ");
                        String role = scannerLn.nextLine();

                        Role newRole = Role.USER;

                        if (role.equalsIgnoreCase("VENDOR")) {
                            newRole = Role.VENDOR;
                        }

                        if (!role.equalsIgnoreCase("VENDOR") && !role.equalsIgnoreCase("USER")) {
                            throw new StackOverflowException("Такого роля не существует! ❌");
                        }

                        currentUser = new User(GeneratorId.genUserId(), firstName, authEmail, authPassword, phoneNumber, newRole);
                        userService.addUser(currentUser);
                        isAuth = true;
                    }
                }
                if (isAuth) {
                    switch (Objects.requireNonNull(currentUser).getRole()) {
//                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        case Role.USER -> {
                            while (true) {
                                try {
                                    System.out.println("""
                                            1) Добавить объявление в избранные
                                            2) Удалить объявление с избранных
                                            3) Получить объявление из избранных по id
                                            4) Получить все избранные пользователя
                                            5) Получить все объявления
                                            0) Выйти
                                            """);
                                    int command = scannerNum.nextInt();

                                    switch (command) {
                                        case 1 -> {
                                            System.out.println("Введите id владельца объявления: ");
                                            Long id = scannerNum.nextLong();

                                            User user = Database.users.stream()
                                                    .filter(x -> x.getId().equals(id))
                                                    .findFirst()
                                                    .orElseThrow();

                                            System.out.println("Введите id объявления для добавления в избранные: ");
                                            Long announcementId = scannerNum.nextLong();

                                            Announcement announcement = Database.users.stream()
                                                    .flatMap(user1 -> user1.getAnnouncements().stream())
                                                    .filter(announcement1 -> announcement1.getId().equals(announcementId))
                                                    .findFirst()
                                                    .orElseThrow(() -> new StackOverflowException("Объявление по id: " + announcementId + " не найден! ❌"));

                                            System.out.println(favoriteService.addFavorite(currentUser.getId(), new Favorite(GeneratorId.genFavoriteId(), user, announcement)));
                                        }
                                        case 2 -> {
                                            System.out.println("Введите id избранного которого хотите удалить: ");
                                            System.out.println(favoriteService.deleteFavorite(scannerNum.nextLong()));
                                        }
                                        case 3 -> {
                                            System.out.println("Введите id избранного которого хотите получить: ");
                                            Long id = scannerNum.nextLong();
                                            System.out.println(favoriteService.getFavoriteById(id));
                                        }
                                        case 4 -> {
                                            System.out.println("Введите id пользователя что бы получить его избранные: ");
                                            System.out.println(favoriteService.getAllFavoritesByUserId(scannerNum.nextLong()));
                                        }
                                        case 0 -> System.out.println("Вы успешно вышли из аккаунта! ✅");
                                        default -> System.out.println("Такой команды не сушествует! ❌");
                                    }
                                    if (command == 0) {
                                        break;
                                    }
                                } catch (StackOverflowException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                        case Role.VENDOR -> {
//                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            while (true) {
                                System.out.println("""
                                        1) Выложить объявление
                                        2) Обновить объявление по id
                                        3) Удалить объявление по id
                                        4) Получить все мои объявления
                                        5) Получить все объявления
                                        0) Выйти
                                            """);
                                int command = scannerNum.nextInt();

                                switch (command) {
                                    case 1 -> {
                                        System.out.println("Введите название объявления: ");
                                        String name = scannerLn.nextLine();

                                        System.out.println("Введите описание объявления: ");
                                        String description = scannerLn.nextLine();

                                        System.out.println("Укажите цену: ");
                                        int price = scannerNum.nextInt();

                                        System.out.println("Введите имя владельца объявления: ");
                                        String owner = scannerLn.nextLine();

                                        Announcement announcement = new Announcement(GeneratorId.genAnnouncementId(), name, description, price, owner);

                                        System.out.println(announcementService.addUserAnnouncement(currentUser.getId(), announcement));
                                    }
                                    case 2 -> {
                                        System.out.println("Введите id объявления которого хотите обновить: ");
                                        Long id = scannerNum.nextLong();

                                        System.out.println("Введите новое название объявления: ");
                                        String name = scannerLn.nextLine();

                                        System.out.println("Введите новое описание объявления: ");
                                        String description = scannerLn.nextLine();

                                        System.out.println("Укажите новую цену: ");
                                        int price = scannerNum.nextInt();

                                        System.out.println("Введите имя нового владельца объявления: ");
                                        String owner = scannerLn.nextLine();

                                        Announcement newAnnouncement = new Announcement(id, name, description, price, owner);

                                        System.out.println(announcementService.updateAnnouncement(id, newAnnouncement));
                                    }
                                    case 3 -> {
                                        System.out.println("Введите id объвления которого хотите удалить: ");
                                        System.out.println(announcementService.deleteAnnouncement(scannerNum.nextLong()));
                                    }
                                    case 4 -> {
                                        System.out.println(announcementService.getAllAnnouncements(currentUser.getId()));
                                    }
                                    case 0 -> System.out.println("Вы успешно вышли из аккаунта! ✅");
                                    default -> System.out.println("Такой команды не сушествует! ❌");
                                }
                                if (command == 0) {
                                    break;
                                }
                            }
                        }
                    }
                }
                throw new StackOverflowException("");
            } catch (StackOverflowException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}