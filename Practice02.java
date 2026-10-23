package semester1.practice01;

import java.sql.SQLOutput;

public class Practice02 {
    static void main() {
int studnetAge = 18;
        byte b = 100;
        short s = 25000;
        int count = 42;
        long stars = 90000000000L;
        float price = 19.99f;
        double pi = 3.14159265;
        char grade = 'A';
        boolean isPassed = true;
        String name = "Роман";
        final double PI = 3.1415926535;
        final int MAX_ATTEMPTS = 3;
        int smallNumber = 10;
        double bigNumber = smallNumber;
        double Pi = 3.99;
        int roundePi = (int) pi;
        System.out.println("Результат: " + 5 + 10);
        System.out.println("Результат: " + (5 + 10));
        System.out.println(5 + 10 + " - це сума");
        int reminder = 17 % 5;
        int evenCheck = 8 % 2;
        int lastDigit = 257 % 10;
        int userAge = 17;
        double heightpi = 1.73;
        char firstLetter = 'Р';
        boolean isStudent = true;
        System.out.println(17);
        System.out.println(1.73);
        System.out.println('P');
        System.out.println(true);
        byte temperature = -15;
        short currentYear = 2026;
        int cityPopulation = 1200000;
        long worldPopulation = 8100000000L;
        System.out.println(cityPopulation);
        System.out.println(worldPopulation);
        float floatValue = 1.1234567f;
        double doubleValue = 1.123456789012345;
        System.out.println(floatValue);
        System.out.println(doubleValue);
        char symbol1 = 'J';
        char symbol2 = 74;
        char symbol3 = '\u004A';;
            System.out.println(symbol1);
            System.out.println(symbol2);
            System.out.println(symbol3);
        boolean isJavaFun = true;
        boolean isFishFly = false;
        boolean isSkyBlue = true;
            System.out.println(isJavaFun);
            System.out.println(isFishFly);
            System.out.println(isSkyBlue);
         final double GRAVITY = 9.81;
         final int DAYS_IN_WEEK = 7;
         final String UNIVERSTY_NAME = "Львівська Політехника";
            System.out.println("GRAVITY: " + GRAVITY);
            System.out.println("DAYS_IN_WEEK: " + DAYS_IN_WEEK);
            System.out.println("UNIVERSTY_NAME: " + UNIVERSTY_NAME );
        // Рядок закоментовано, щоб програма успішно скомпілювалася:
        // GRAVITY = 10.0;
        int intVal = 250;
        double doubleVal = intVal;
        System.out.println("intVal: " + intVal);
        System.out.println("double: " + doubleVal);
       double priceWithPennies = 49.99;
       int roundedPrice = (int) priceWithPennies;
        System.out.println("roundedPrice: " + roundedPrice);
       int firstNumber = 5;
       double userWeight = 72.5;
       String userCity = "Kyiv";
       boolean isValidUser = true;
        System.out.println("firstNumber: " + firstNumber);
        System.out.println("userWeight: " + userWeight);
        System.out.println("userCity " + userCity);
        System.out.println("isValidUser: " + isValidUser);
        String firstName = "Тарас";
        String lastName = "Шевченко";
        int age = 47;
        System.out.println("Мене звати " + firstName + " " + lastName + ", " + "мені " + age + " років." );
        int a = 10;
        int B = 20;
        System.out.println("Сума без дужок: " + a + B);
        System.out.println("Сума з дужками: " + (a + B));
        /*

 1. У першому виразі операції виконуються послідовно зліва направо.
    Спочатку текстовий рядок додається до числа 'a' (10). Через це число перетворюється на текст,
    і утворюється новий рядок: "Сума без дужок: 10". Потім до цього рядка додається число 'b' (20),
    яке також стає текстом. Відбувається звичайна конкатенація (склеювання текстових значень),
    тому результат виходить "1020".

 2. У другому виразі дужки мають найвищий пріоритет.
    Тому спочатку виконується додавання всередині дужок як звичайна математична операція (10 + 20 = 30).
    Вже після цього отримане число 30 склеюється з початковим текстовим рядком,
    тому результат виходить "30".
*/
        String login = "ivan.petrenko";
        String domain = "gmail.com";
        String role = "student";
        String email = login +  "@" + domain;
        System.out.println("Користувач " + login + " (роль: " + role + ") має пошту: " + email);
        String productName = "Ноутбук";
        int quantity = 2;
        double pricePerUnit = 24500.50;
        System.out.println("Чек: \nТовар: " + productName + "\nКількість: " + quantity + "шт.\nЦіна: " + pricePerUnit + "грн");
        String currency = "UAH";
        int hryvnias = 1500;
        int kopecks = 75;
        System.out.println("Поточний баланс: " + hryvnias + "." + kopecks +  " " + currency);
        String protocol = "https://";
        String host = "api.example.com";
        String endpoint = "/users/";
        int userId = 42;
        System.out.println("Запит відправлено на: " + protocol + "" + host + "" + endpoint + "" + userId);
        String language = "Java";
        int level = 1;
        boolean isReady = true;
        System.out.println("Мова: " + language + " | Рівень: " + level + " | Готовність до практики: " + isReady);
        int number = 19;
        int remainder = number % 2;
        System.out.println("Число " + number + " при діленні на 2 дає остачу: " +remainder);
        int totalSeconds = 385;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        System.out.println(totalSeconds + " секунд = " + minutes + " хв " + seconds + "сек");
        int n = 47;
        int tens = n / 10;
        int untis = n % 10;
        int sum = tens + untis;
        System.out.println("Число: " + n + ". Десятки: " + tens + ", Одиниці: " + untis + ". Сума цифр: " + sum);
        int score = 100;
        score += 50;
        System.out.println("Після додаваня: " + score);
        score -= 20;
        System.out.println("Після віднімання: " + score);
        score *= 2;
        System.out.println("Після множення " + score);
        score /= 4;
        System.out.println("Після ділення: " + score);
        int counter = 5;
        System.out.println(counter++);
        System.out.println(counter);
        counter = 5;
        System.out.println(++counter);
        System.out.println(counter);
        int grade1 = 4;
        int grade2 = 5;
        int grade3 = 4;
        double average = (grade1 + grade2 + grade3) / 3.0;
        System.out.println("Середнє значення: " + average );
        double celsius = 25.0;
        double farenheit = celsius * 1.8 + 32;
        double kelvin = celsius + 273.15;
        System.out.println("Очікуваний результат: ");
        System.out.println(celsius + "C = " + farenheit + "F");
        System.out.println(celsius + "C = " + kelvin + "K ");
    }
}