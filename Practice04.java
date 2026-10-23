package semester1.practice01;
import java.util.Scanner;

public class Practice04 {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Номер завдання: ");
        int task = scanner.nextInt();
        scanner.nextLine();

        if (task == 1) {
            for (int i = 0; i < 7; i++) {
                System.out.println("Java - надійна та кросплатформна мова!");
            }
        }

        if (task == 2) {
            System.out.println("Введіть текст: ");
            String word = scanner.nextLine();
            System.out.println("Введіть число: ");
            int numb = scanner.nextInt();
            for (int i = 1; i <= numb; i++) {
                {
                    System.out.println("[" + i + "]" + word);
                }
            }
        }

        if (task == 3) {
            int balance = 500;
            int month = 1;
            while (month <= 12) {
                System.out.println("Місяць № " + month + ":" + balance + " грн");
                balance += 500;
                month++;
            }
        }

        if (task == 4) {
            System.out.print("Введіть курс євро: ");
            double rate = scanner.nextDouble();
            for (int i = 10; i <= 100; i += 10) {
                double result = i * rate;
                System.out.println(i + " євро = " + result + " грн");
            }

        }

        if (task == 5) {
            System.out.println("Введіть кількість символів: ");
            int sym = scanner.nextInt();
            for (int i = 0; i < sym; i++) {
                System.out.print("=");


            }
        }

        if (task == 6) {
            int count = 10;
            while (count >= 1) {
                System.out.println(count);
                count -= 1;
            }
            System.out.println("\"Поїхали! Ракета запущена!\"");
        }

        if (task == 7) {
            int sum = 0;
            int numb = 1;
            System.out.println("Введіть чило: ");
            numb = scanner.nextInt();
            while (numb != 0) {
                sum += numb;
                System.out.print("Введіть число: ");
                numb = scanner.nextInt();
            }
            System.out.println("Сума: " + sum);


        }

        if (task == 8) {
            int numb = 1;
            System.out.println("Введіть число: ");
            int M = scanner.nextInt();
            while (numb <= M) {
                System.out.println(numb);
                numb *= 2;

            }
        }

        if (task == 9) {
            int numb = 0;
            int sum = 0;
            System.out.println("Введіть число: ");
            int N = scanner.nextInt();
            while (N > 0) {
                int digit = N % 10;
                sum += digit;
                numb++;
                N /= 10;
            }
            System.out.println("Кількість цифр: " + numb);
            System.out.println("Сума цифр: " + sum);
        }

        if (task == 10) {
            int digit = 0;
            int result = 0;
            System.out.println("Введіть число: ");
            int N = scanner.nextInt();
            while (N > 0) {
                digit = N % 10;
                result = result * 10 + digit;
                N /= 10;
            }
            System.out.println("Перевернуте число: " + result);

        }

        if (task == 11) {
            System.out.println("Введіть суму: ");
            double sum = scanner.nextDouble();
            System.out.print("Введіть початковий вклад: ");
            double S = scanner.nextDouble();
            System.out.print("Введіть річний відсоток: ");
            double P = scanner.nextDouble();
            double balance = S;
            int years = 0;
            if (S <= 0 || P <= 0) {
                System.out.println("Вклад і відсоток повинні бути більшими за 0");
            } else {
                while (balance < S * 2) {
                    balance += balance * P / 100;
                    years++;
                }

                System.out.println("Кількість років: " + years);
                System.out.println("Підсумкова сума: " + balance + " грн");
            }


        }

        if (task == 12) {
            int divisor = 2;
            System.out.println("Введіть число: ");
            int numb = scanner.nextInt();
            while (numb % divisor != 0) {
                divisor++;
            }
            System.out.println("Найменший дільник: " + divisor);
        }

        if (task == 13) {
            int secret = (int) (Math.random() * 100) + 1;
            int guess = 0;
            int attempts = 0;
            while (guess != secret) {
                System.out.println("Вгадай число: ");
                guess = scanner.nextInt();
                attempts++;
                if (guess < secret) {
                    System.out.println("Більше!");
                } else if (guess > secret) {
                    System.out.println("Менше!");

                }
            }
            System.out.println("Вітаємо! Ви вгадали число!");
            System.out.println("Кількість спроб: " + attempts);
        }

        if (task == 14) { //Сподіваюся що можна в подібних завданнях писати не лише текст з README файлу
            //А можна і свій, вважаю так навіть цікавіше виконувати, я написав пункти
            //На прикладі однієї гри як у її меню
            int choice;
            do {
                System.out.println("1. Multiplayer");
                System.out.println("2. Campaign");
                System.out.println("3. Zombie");
                System.out.println("4. Shooting range");
                System.out.println("0. Exit");

                System.out.println("Оберіть пункт: ");
                choice = scanner.nextInt();
                if (choice == 1) {
                    System.out.println("Loading Multiplayer...");
                } else if (choice == 2) {
                    System.out.println("Switching to Campaign game client...");
                } else if (choice == 3) {
                    System.out.println("Locating Zombie...");
                } else if (choice == 4) {
                    System.out.println("Deploying in Shooting range...");
                } else if (choice == 0) {
                    System.out.println("Closing game...");
                }
            } while (choice != 0);


        }

        if (task == 15) {
            int age;
            do {
                System.out.print("Введіть свій вік: ");
                age = scanner.nextInt();

                if (age < 1 || age > 120) {
                    System.out.println("Некоректний вік! Спробуйте ще раз.");
                } else {
                    System.out.println("Вік підтверджено: " + age);
                }

            } while (age < 1 || age > 120);
        }

        if (task == 16) {
            int pin = 5167;
            int enteredPin;
            int attempts = 0;

            do {
                System.out.println("Введіть PIN-Код");
                enteredPin = scanner.nextInt();
                attempts++;

            } while (enteredPin != pin && attempts < 3);
            if (enteredPin == pin) {
                System.out.println("Доступ дозволено!");
            } else {
                System.out.println("Картку заблоковано!");
            }
        }

        if (task == 17) {
            String answer;

            do {
                System.out.println("Введіть перше число: ");
                int a = scanner.nextInt();

                System.out.println("Введіть друге число: ");
                int b = scanner.nextInt();

                int result = a + b;
                System.out.println("Результат: " + result);

                System.out.println("Бажаєте повторити? (y/n)");
                answer = scanner.next();
            } while (answer.equalsIgnoreCase("y"));
            System.out.println("Програму закінчено");
        }

        if (task == 18) {
            int cube1;
            int cube2;
            int cube3;
            int attempts = 0;

            do {
                cube1 = (int) (Math.random() * 6) + 1;
                cube2 = (int) (Math.random() * 6) + 1;

                attempts++;

                System.out.println("Кидок №" + attempts + ": " + cube1 + " і " + cube2);
            } while (cube1 != cube2);
            System.out.println("Випав дубль!");
            System.out.println("Кількість кидків: " + attempts);

        }

        if (task == 19) {
            System.out.println("Ввведіть перше число: ");
            double a = scanner.nextDouble();
            double b;
            do {
                System.out.println("Введіть другее число: ");
                b = scanner.nextDouble();
                if (b == 0) {
                    System.out.println("Помилка! Ділити на нуль не можна!");
                }
            } while (b == 0);
            double result = a / b;
            System.out.println("Результат: " + result);

        }
        if (task == 20) {
            int grade;
            int sum = 0;
            int count = 0;
            do {
                System.out.print("Введіть оцінку: ");
                grade = scanner.nextInt();
                if (grade > 100) {
                    System.out.println("Некоректна оцінка!");
                } else if (grade >= 0) {
                    sum += grade;
                    count++;
                }

            } while (grade >= 0);
            if (count > 0) {
                double average = (double) sum / count;
                System.out.println("Середній бал: " + average);
            } else {
                System.out.println("Оцінки не введено.");
            }
        }

        if (task == 21) {
            System.out.println("Введіть перше число: ");
            int a = scanner.nextInt();
            System.out.println("Введіть друге число: ");
            int b = scanner.nextInt();
            System.out.println("Парні числа: ");
            for (int i = a; i < b; i++) {
                if (i % 2 ==0) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }

        if (task == 22) {
            System.out.println("Введіть число: ");
            int n = scanner.nextInt();

            if (n < 0 || n > 20) {
                System.out.println("Некоректне число!");
            } else {
                long factorial = 1;

                for (int i = 1; i <= n; i++) {
                    factorial *= i;
                }
                System.out.println("Факторіал числа " + n + ": " + factorial);
            }
        }

        if (task == 23) {
            System.out.printf("%-10s %-10s %-10s%n",
                    "Число", "Квадрат", "Куб");
            for (int i = 1; i <= 10; i++) {
                int square = i * i;
                int cube = i * i * i;

                System.out.printf("%-10d %-10d %-10d%n",
                        i, square, cube);
            }
        }

        if (task == 24) {
            for (int i = 1; i <= 50; i++) {
                if (i % 3 == 0 || i % 5 == 0) {
                    continue;
                }
                System.out.println(i + " ");

            }
            System.out.println();

        }

        if (task == 25) {
            for (int i = 100; i <= 200; i++) {
                if (i % 13 == 0 && i % 7 == 0) {
                    System.out.println("Число знайдено: " + i);
                    break;
                }
            }

        }

        if (task == 26) {
            for (char letter = 'A'; letter <= 'Z'; letter++) {
                System.out.println(letter + "-" + (int) letter);
            }

        }

        if (task == 27) {
            System.out.println("Введіть число: ");
            int n = scanner.nextInt();
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                if (i % 2 != 0) {
                    sum += i;
                } else {
                    sum -= i;
                }
            } System.out.println("Результат: " + sum);
        }

        if (task == 28) {
            System.out.println("Введіть перше число");
            int n1 = scanner.nextInt();
            System.out.println("Введіть різницю: ");
            int n2 = scanner.nextInt();
            System.out.println("Введіть кількість членів: ");
            int n3 = scanner.nextInt();
            int sum = 0;
            int number = n1;
            System.out.println("Прогресія: ");
            for (int i = 1; i <= n3; i++) {
                System.out.print(number + " ");
                sum += number;
                number += n2;
            }

            System.out.println();
            System.out.println("Сума: " + sum);
        }

        if (task == 29) {
            byte b = 125;
            for (int  i = 1; i <= 6; i++) {
                b = (byte)(b + 1);
                System.out.println("Крок " + i + ": " + b);
            }
        }

        if (task == 30) {
            System.out.println("Введіть кількість чисел: ");
            int n = scanner.nextInt();
            if (n <= 0 ) {
                System.out.println("Число повинне бути більше за 0!");
            } else {
                int sum = 0;
                for (int i = 1; i <= n; i++) {
                    System.out.println("Введіть число " + i + ":");
                    int number = scanner.nextInt();
                    sum += number;
                }
                int averageInt = sum / n;
                double averageDouble = (double) sum / n;

                System.out.println("Сума: " + sum);
                System.out.println("Середнє (int): " + averageInt);
                System.out.println("Середнє (double): " + averageDouble);
            }
        }

        if (task == 31) {
            System.out.print("Введіть рядок із 5 символів: ");
            String text = scanner.next();

            if (text.length() != 5) {
                System.out.println("Потрібно ввести рівно 5 символів!");
            } else {
                String result = "";
                        for (int i = 0; i < text.length(); i++) {
                            char letter = text.charAt(i);
                            char shifted = (char)(letter + 1);
                            result += shifted;
                        }
                System.out.println("Зашифрований рядок: " + result);
            }

        }

        if (task == 32) {
            for (int i = 1; i <= 50; i++) {
                int progress = (int) (((double) i / 50) * 100);

                System.out.println("Завантаження: " + progress + "%");
            }
            System.out.println("Завантаження завершено!");
        }


        if (task == 33) {
            long bigNumber = 3_000_000_000L;

            int smallNumber = (int) bigNumber;

            System.out.println("Початкове значення (long): " + bigNumber);
            System.out.println("Після перетворення (int): " + smallNumber);

        }

        if (task == 34) {
            System.out.println("Перший спосіб (double):");

            for (double d = 0; d <= 1.0; d += 0.1) {
                System.out.println(d);
            }

            System.out.println("Другий спосіб (int):");

            for (int i = 0; i <= 10; i++) {
                double d = i / 10.0;
                System.out.println(d);
            }
        }

        if (task == 36) {
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    System.out.printf("%4d", i * j);
                }
                System.out.println();
            }
        }

        if (task == 37) {
            System.out.print("Введіть висоту: ");
            int h = scanner.nextInt();

            System.out.print("Введіть ширину: ");
            int w = scanner.nextInt();

            for (int i = 1; i <= h; i++) {
                for (int j = 1; j <= w; j++) {
                    if (i == 1 || i == h || j == 1 || j == w) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }

        if (task == 38) {
            System.out.print("Введіть висоту трикутника: ");
            int n = scanner.nextInt();

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }

        if (task == 39) {
            System.out.print("Введіть число N: ");
            int n = scanner.nextInt();

            System.out.println("Прості числа:");

            for (int i = 2; i <= n; i++) {
                boolean isPrime = true;

                for (int j = 2; j < i; j++) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }

                if (isPrime) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }

        if (task == 40) {
            System.out.print("Введіть розмір дошки N: ");
            int n = scanner.nextInt();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((i + j) % 2 == 0) {
                        System.out.print("_");
                    } else {
                        System.out.print("#");
                    }
                }
                System.out.println();
            }
        }
    }
}