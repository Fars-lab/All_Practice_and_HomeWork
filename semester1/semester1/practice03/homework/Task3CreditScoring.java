package semester1.semester1.practice03.homework;

import java.util.Scanner;
import java.util.Locale;

public class Task3CreditScoring {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== СИСТЕМА БАНКІВСКЬОГО СКОРИНГУ ===");
        System.out.println("Введіть вік позичальника: ");
        int age  = scanner.nextInt();
        System.out.println("Введать офіційний місячний дохід (грн): ");
        double monthlyIncome = scanner.nextDouble();
        System.out.println("Чи є негативне кредитна історія (true/false): ");
        boolean hasBadCreditHouse = scanner.nextBoolean();
        System.out.println("Введіть запитувану суму кредиту (грн): ");
        double loanAmount = scanner.nextDouble();
        System.out.println("Введіть бажаний термін (місяців): ");
        int loanMonths = scanner.nextInt();


        boolean isAgeOk = (age >= 21 && age <= 65);
        boolean isHistoryOk = !hasBadCreditHouse;
        boolean isPaymentAffordable = (loanMonths > 0) && ((loanAmount / loanMonths) <= (monthlyIncome * 0.5));

        double rate = (monthlyIncome >= 35000) ? 14.5 : 21.0;


        System.out.println();
        System.out.println("------------- РІШЕННЯ СКОРИНГУ -------------");
        if (isAgeOk && isHistoryOk && isPaymentAffordable) {
            double payment = loanAmount / loanMonths;
            double percent = payment / monthlyIncome * 100;
            System.out.println("Статус заявки:               СХВАЛЕНО ✅");
            System.out.printf(Locale.US, "Орієнтовний платіж/міс:      %.2f грн (%.1f%% від доходу)%n", payment, percent);
            System.out.printf(Locale.US, "Персональна ставка:          %.1f%% річних%n", rate);
            System.out.printf(Locale.US, "Максимально допустимий платіж: %.2f грн/міс%n", monthlyIncome * 0.5);
        } else {
            System.out.println("Статус заявки:               ВІДХИЛЕНО ❌");
            if (!isAgeOk) {
                System.out.println("Відмова: вік не відповідає критеріям (21-65 років)");
            } else if (!isHistoryOk) {
                System.out.println("Відмова: виявлено негативну кредитну історію");
            } else {
                System.out.println("Відмова: термін кредиту некоректний або щомісячний платіж перевищує 50% доходу");
            }
        }
        System.out.println("=============================================");

        scanner.close();
    }
}
