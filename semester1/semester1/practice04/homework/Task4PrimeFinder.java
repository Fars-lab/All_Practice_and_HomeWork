package semester1.semester1.practice04.homework;

    import java.util.Scanner;
    import java.util.Locale;

public class Task4PrimeFinder {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ДЕТЕКТОР ПРОСТИХ ЧИСЕЛ ===");
        int n;
        do {
            System.out.println("Введіть верхню межу N (N >= 2): ");
            n = scanner.nextInt();
        } while (n < 2);

        int count = 0;
        long sum = 0;
        StringBuilder primes = new StringBuilder();
        for (int num = 2; num <= n; num++) {
            boolean isPrime = true;
            for (int d = 2; d * d <= num; d++) {
                if (num % d == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                if (count > 0) {
                    primes.append(", ");
                }
                primes.append(num);
                count++;
                sum += num;
            }
        }

        int total = n - 1;
        double percent = (double) count / (double) total * 100;

        System.out.println("\nЗнайдені прості числа у проміжку від 2 до " + n + ":");
        System.out.println(primes);
        System.out.println("\n--- СТАТИСТИКА ---");
        System.out.println("Усього чисел у діапазоні:    " + total);
        System.out.println("Знайдено простих чисел:      " + count + " шт.");
        System.out.println("Сума всіх простих чисел:     " + sum);
        System.out.printf(Locale.US, "Частка простих чисел:        %.2f%%%n", percent);
        System.out.println("==============================");

        scanner.close();
    }
}
