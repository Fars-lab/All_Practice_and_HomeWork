package semester1.practice01;

import java.util.Scanner;

public class Practice03 {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть вік: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введіть повне ім'я: ");
        String fullName = scanner.next();
        System.out.print("Введіть температуру: ");
        int temperature = scanner.nextInt();

        if (temperature < 0) {
            System.out.println("На вулиці мороз!");
        }
        int score = scanner.nextInt();
        if (score >= 60) {
            System.out.println("Іспит складено!");
        } else {
            System.out.println("Іспит не складено.");
        }
        if (score >= 90) {
            System.out.println("Відмінно");
        } else if (score >= 75) {
            System.out.println("Добре");
        } else if (score >= 60) {
            System.out.println("Задовільно");
        } else {
            System.out.println("Незадовільно");
        }
        int isAuthorized = scanner.nextInt();
        if (isAuthorized == 1)
            System.out.println("Доступ надано");
        System.out.println("Ласкаво просимо!");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if (a > 0)
            if (b > 0)
                System.out.println("a > 0 та b > 0");
            else
                System.out.println("Хто викликав цей else?");
        boolean condition = false;
        if (condition) {
            int localDiscount = 10;
        }
        int discount = 0;
        int isPremium = scanner.nextInt();
        if (isPremium == 1) {
            discount = 15;
        }
        System.out.println("Знижка: " + discount);
        int number = 7;
        int isOnline = scanner.nextInt();
        int max = (a > b) ? a : b;
        String parity = (number % 2 == 0) ? "Парне" : "Непарне";
        System.out.println("Статус: " + (isOnline == 1 ? "В мережі" : "Офлайн"));
        int n = scanner.nextInt();
        String sign = (n > 0) ? "Додатне" : (n < 0) ? "Від'ємне" : "Нуль";
        a = 10;
        b = 0;
        if (b != 0 && (a / b > 2)) {
            System.out.println("Умовна виконана");
            int count = 0;
            boolean condition1 = false;

            if(condition && (+count > 0)){

            }
            System.out.println(count);
            int dayNumber = 3;
            String dayName;

            switch (dayNumber) {
                case 1:
                    dayName = "Понеділок";
                    break;
                    case 2:
                        dayName = "Вівторок";
                        break;
                        case 3:
                            dayName = "Середа";
                            break;
                         default:
                             dayName = "Невідомий день";
                             break;
            }
            switch (dayNumber) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    System.out.println("Робочий день");
                    break;
                    case 6:
                    case 7:
                        System.out.println("Вихідний день");
                        break;
                default:
                    System.out.println("Некоректний день");
                    break;
            }
            switch (dayNumber) {
                case 1, 2, 3, 4, 5 -> System.out.println("Робочий день");
                case 6, 7          -> System.out.println("Вихідний день");
                default            -> System.out.println("Помилка");
            }
            String dayType = switch (dayNumber) {
                case 1, 2, 3, 4, 5 -> "Будній";
                case 6, 7          -> "Вихідний";
                default            -> "Невідомий";
            };
            int day = 5;
            String schedule = switch (day) {
                case 1, 2, 3, 4 -> "Звичайний навчальний день";
                case 5 -> {
                    System.out.println("П'ятниця: скорочені пари!:");
                    yield "Короткий день (до 14:00)";
                }
                case 6, 7 -> "Вихідний! Час відпочинку";
                default -> throw new IllegalArgumentException("Некоректний день" + day);
            };
            Scanner sc = new Scanner(System.in);
            System.out.println("Введіть роль: ");
            String role = sc.nextLine();

            if (role == "admin") {
                System.out.println("Ласкаво просимо, Адміністраторе!");
            } else {
                System.out.println("Доступ заборонено!");
            }
            String command = scanner.nextLine();

            switch (command) {
                case "start" -> System.out.println("Запуск системи...");
                case "stop"  -> System.out.println("Зупинка системи...");
                case "exit"  -> System.out.println("Вихід із програми.");
                default      -> System.out.println("Невідома команда!");
            }
        }
    }
}
