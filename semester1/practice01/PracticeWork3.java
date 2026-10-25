package semester1.practice01;
import java.util.Scanner;

public class PracticeWork3 {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ім'я: ");
        String fullName = sc.nextLine();
        System.out.println("Прізвище: ");
        String lastName = sc.nextLine();
        System.out.println("Вік: ");
        String age = sc.nextLine();
        System.out.println("Місто: ");
        String city = sc.nextLine();
        System.out.println("=== ВХІДНІ ДАНІ ===");
        System.out.println("Ім'я: " + fullName);
        System.out.println("Прізвище: " + lastName);
        System.out.println("Вік: " + age);
        System.out.println("Місто: " + city);
        System.out.println("-----------------");
        System.out.println("Привіт, " + fullName + " " + lastName + " з міста " + city + "! Твій вік: " + age + (" р."));

        Scanner sc2 = new Scanner(System.in);
        System.out.println("Введіть перше число: ");
        int a = sc2.nextInt();
        System.out.println("Введіть друге число: ");
        int b = sc2.nextInt();
        int sum = a + b;
        int difference = a - b;
        int product = a * b;
        int remainder = a % b;
        System.out.println("Сума: " + sum);
        System.out.println("Різниця: " + difference);
        System.out.println("Добуток: " + product);
        System.out.println("Остача від ділення: " + remainder);

        Scanner sc3 = new Scanner(System.in);
        System.out.println("Введіть суму в гривнях (UAH): ");
        double uah = sc3.nextDouble();
        System.out.println("Введіть курс USD: ");
        double usd = sc3.nextDouble();
        double rawUSD = usd * uah;
        double roundsedUSD = Math.round(rawUSD * 100) / 100;
        System.out.println("Сума до видачі: " + roundsedUSD + "USD");

        Scanner sc4 = new Scanner(System.in);
        System.out.println("Введіть номера квитка: ");
        int check = sc4.nextInt();
        sc4.nextLine();
        System.out.println("Введіть повне ім'я студента: ");
        String firstName = sc4.nextLine();
        System.out.println("Введіть спеціальність: ");
        String speciality = sc4.nextLine();
        System.out.println("Студент: " + firstName + ", квиток №" + check + ", спеціальність: " + speciality);

        Scanner sc5 = new Scanner(System.in);
        System.out.println("Ваш зріст (м): ");
        double metr = sc5.nextDouble();
        sc5.nextLine();
        System.out.println("Ваша вага (кг): ");
        double kg = sc5.nextDouble();
        sc5.nextLine();
        System.out.println("Розрахований ВМІ: ");
        double BMI = sc5.nextDouble();
        sc5.nextLine();

    }
}
