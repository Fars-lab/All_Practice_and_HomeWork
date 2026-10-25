package semester1.practice01;

import java.util.Arrays;
import java.util.Scanner;

public class    Practice05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ==========================================
        // Блок 1: Заповнення, вивід та ручне копіювання
        // ==========================================
        System.out.println("--- Блок 1 ---");

        // 1: Перший масив та значення за замовчуванням
        int[] numbers = new int[5];
        System.out.print("1. Початковий масив (значення за замовчуванням): ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        numbers[0] = 10;
        numbers[2] = 30;
        numbers[4] = 50;
        System.out.println("1. Оновлений масив: " + Arrays.toString(numbers));
        System.out.println();

        // 2: Рядковий масив днів тижня
        String[] days = {
                "Понеділок", "Вівторок", "Середа", "Четверг", "P'ятниця", "Субота", "Неділя"
        };
        System.out.println("2. Загальна кількість днів у тижні: " + days.length);
        System.out.println("2. Перший день (індекс 0): " + days[0]);
        System.out.println("2. Останній день (індекс length - 1): " + days[days.length - 1]);
        days[5] = "Вихідний (Сб)";
        days[6] = "Вихідний (Нд)";
        System.out.println("2. Оновлений список днів: " + Arrays.toString(days));
        System.out.println();

        // 3: Модифікація цін на складі
        double[] prices = {120.50, 45.00, 890.99, 15.30, 420.00};
        System.out.println("3. Початкові ціни: " + Arrays.toString(prices));
        prices[2] = prices[2] * 0.8;
        System.out.println("3. Оновлені ціни: " + Arrays.toString(prices));
        System.out.println();

        // 4: Заповнення масиву числами від 1 до N
        System.out.print("4. Введіть число N: ");
        int n = scanner.nextInt();
        int[] arr4 = new int[n];
        for (int i = 0; i < arr4.length; i++) {
            arr4[i] = i + 1;
        }
        System.out.println("4. Масив від 1 до N: " + Arrays.toString(arr4));
        System.out.println();

        // 5: Зворотний вивід масиву
        int[] arr5 = {4, 8, 15, 16, 23, 42};
        System.out.print("5. Масив у зворотному порядку: ");
        for (int i2 = arr5.length - 1; i2 >= 0; i2--) {
            System.out.print(arr5[i2]);
            if (i2 > 0) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println();

        // 6: Масив степенів двійки
        int[] powersOfTwo = new int[10];
        powersOfTwo[0] = 1;
        for (int i2 = 1; i2 < powersOfTwo.length; i2++) {
            powersOfTwo[i2] = powersOfTwo[i2 - 1] * 2;
        }
        System.out.println("6. Степені двійки: " + Arrays.toString(powersOfTwo));
        System.out.println();

        // 7: Ручне копіювання елементів
        int[] original = {3, 7, 1, 9, 4};
        int[] copy = new int[original.length];
        for (int i1 = 0; i1 < original.length; i1++) {
            copy[i1] = original[i1];
        }
        copy[0] = 999;
        System.out.println("7. Original масив: " + Arrays.toString(original));
        System.out.println("7. Copy масив: " + Arrays.toString(copy));


        // ==========================================
        // Блок 2: Базові обчислення та агрегація
        // ==========================================
        System.out.println("\n--- Блок 2 ---");

        // 8: Загальна сума та середній бал
        int[] grades = {95, 84, 70, 99, 65, 88, 92};
        int sum8 = 0;
        for (int i3 = 0; i3 < grades.length; i3++) {
            sum8 += grades[i3];
        }
        double average8 = (double) sum8 / grades.length;
        System.out.println("8. Сума: " + sum8 + ", Середній бал: " + average8);

        // 9: Пошук мінімальної та maximalної температури
        int[] temps = {18, 21, 15, 24, 13, 27, 20};
        int min = temps[0];
        int max = temps[0];
        int minIndex = 0;
        int maxIndex = 0;
        for (int i5 = 1; i5 < temps.length; i5++) {
            if (temps[i5] < min) {
                min = temps[i5];
                minIndex = i5;
            }
            if (temps[i5] > max) {
                max = temps[i5];
                maxIndex = i5;
            }
        }
        System.out.println("9. Мінімальна температура: " + min + " (Індекс/День: " + minIndex + ")");
        System.out.println("9. Максимальна температура: " + max + " (Індекс/День: " + maxIndex + ")");

        // 10: Лічильник додатних, від'ємних та нульових чисел
        int[] data = {12, -5, 0, 43, -19, 0, -2, 8, 0, 15};
        int positiveCount = 0;
        int negativeCount = 0;
        int zeroCount = 0;
        for (int i8 = 0; i8 < data.length; i8++) {
            if (data[i8] > 0) {
                positiveCount++;
            } else if (data[i8] < 0) {
                negativeCount++;
            } else {
                zeroCount++;
            }
        }
        System.out.println("10. Додатних: " + positiveCount + ", Від'ємних: " + negativeCount + ", Нульових: " + zeroCount);

        // 11: Парні та непарні елементи
        int[] numbers11 = {4, 7, 15, 22, 34, 41, 56, 67, 72, 89};
        int evenCount = 0;
        int oddSum = 0;
        for (int i9 = 0; i9 < numbers11.length; i9++) {
            if (numbers11[i9] % 2 == 0) {
                evenCount++;
            } else {
                oddSum += numbers11[i9];
            }
        }
        System.out.println("11. Кількість парних чисел: " + evenCount + ", Сума непарних чисел: " + oddSum);

        // 12: Лінійний пошук елемента
        int[] students = {1042, 2031, 3055, 4018, 5099};
        System.out.print("12. Введіть шуканий номер залікової книжки: ");
        int searcNum = scanner.nextInt();
        int foundIndex = -1;
        for (int i0 = 0; i0 < students.length; i0++) {
            if (students[i0] == searcNum) {
                foundIndex = i0;
                break;
            }
        }
        if (foundIndex != -1) {
            System.out.println("12. Студента знайдено! Індекс: " + foundIndex);
        } else {
            System.out.println("12. Студента з таким номером не знайдено");
        }

        // 13: Підрахунок кількості входжень
        int[] rolls = {3, 6, 2, 6, 1, 4, 6, 5, 2, 6};
        System.out.print("13. Введіть число від 1 до 6: ");
        int targetRoll = scanner.nextInt();
        int countRolls = 0;
        for (int i1 = 0; i1 < rolls.length; i1++) {
            if (rolls[i1] == targetRoll) {
                countRolls++;
            }
        }
        System.out.println("13. Число " + targetRoll + " випало " + countRolls + " разів.");

        // 14: Фільтрація: елементи, більші за середнє
        int[] salaries = {18000, 25000, 32000, 15000, 41000, 29000};
        int totalSalary = 0;
        for (int i2 = 0; i2 < salaries.length; i2++) {
            totalSalary += salaries[i2];
        }
        double averageSalary = (double) totalSalary / salaries.length;
        System.out.println("14. Середня зарплата: " + averageSalary);
        System.out.print("14. Зарплати, що перевищують середню: ");
        for (int i2 = 0; i2 < salaries.length; i2++) {
            if (salaries[i2] > averageSalary) {
                System.out.print(salaries[i2] + " ");
            }
        }
        System.out.println("\n");

        // 15: Розмах значень (амплітуда)
        double[] prices1 = {42.5, 41.8, 44.1, 40.0, 43.6, 45.0};
        double minPrice = prices1[0];
        double maxPrice = prices1[0];
        for (int i1 = 1; i1 < prices1.length; i1++) {
            if (prices1[i1] < minPrice) minPrice = prices1[i1];
            if (prices1[i1] > maxPrice) maxPrice = prices1[i1];
        }
        double range = maxPrice - minPrice;
        System.out.printf("15. Розмах котирувань акцій: %.2f\n", range);


        // ==========================================
        // Блок 3: Перетворення, for-each та клас Arrays
        // ==========================================
        System.out.println("\n--- Блок 3 ---");

        // 16: Безпечний обхід через for-each
        String[] cities = {"Київ", "Львів", "Харків", "Одеса", "Дніпро"};
        System.out.print("16. Вітання міст: ");
        for (String city : cities) {
            System.out.print("Ласкаво просимо до міста " + city + "! ");
        }
        System.out.println();

        // 17: Індексація значень (збільшення на відсоток)
        double[] saleries17 = {20000.0, 25000.0, 18000.0, 30000.0};
        for (int i2 = 0; i2 < saleries17.length; i2++) {
            saleries17[i2] *= 1.10;
        }
        System.out.println("17. Оновлені зарплати (+10%): " + Arrays.toString(saleries17));

        // 18: Реверс масиву «на місці»
        int[] arr = {1, 2, 3, 4, 5, 6};
        for (int i3 = 0; i3 < arr.length / 2; i3++) {
            int temp = arr[i3];
            arr[i3] = arr[arr.length - 1 - i3];
            arr[arr.length - 1 - i3] = temp;
        }
        System.out.println("18. Перевернутий масив (на місці): " + Arrays.toString(arr));

        // 19: Масив квадратів
        int[] base = {2, 4, 6, 8, 10};
        int[] squares = new int[base.length];
        for (int i5 = 0; i5 < base.length; i5++) {
            squares[i5] = base[i5] * base[i5];
        }
        System.out.println("19. Початковий масив base: " + Arrays.toString(base));
        System.out.println("19. Масив квадратів squares: " + Arrays.toString(squares));

        // 20: Перевірка відсортованості масиву
        int[] arr20 = {1, 3, 5, 8, 7, 10};
        boolean isSorted = true;
        for (int i6 = 0; i6 < arr20.length - 1; i6++) {
            if (arr20[i6] > arr20[i6 + 1]) {
                isSorted = false;
                break;
            }
        }
        System.out.println("20. Масив відсортований? " + isSorted);

        // 21: Об'єднання двох масивів (Concatenation)
        int[] a = {1, 2, 3};
        int[] b = {4, 5, 6};
        int[] c = new int[a.length + b.length];
        for (int i1 = 0; i1 < a.length; i1++) {
            c[i1] = a[i1];
        }
        for (int i2 = 0; i2 < b.length; i2++) {
            c[a.length + i2] = b[i2];
        }
        System.out.println("21. Об'єднаний масив: " + Arrays.toString(c));

        // 22: Тріо утиліт: toString, sort, fill
        int[] test = {45, 12, 89, 3, 27};
        System.out.println("22. Початковий стан: " + Arrays.toString(test));
        Arrays.sort(test);
        System.out.println("22. Відсортований: " + Arrays.toString(test));
        Arrays.fill(test, -1);
        System.out.println("22. Заповнений -1: " + Arrays.toString(test));


        // ==========================================
        // Блок 4: Двовимірні масиви (Матриці): структура та виведення
        // ==========================================
        System.out.println("\n--- Блок 4 ---");

        // 23: Перша таблиця 3x3
        int[][] table = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println("23. Таблиця 3x3:");
        for (int i1 = 0; i1 < table.length; i1++) {
            for (int j = 0; j < table[i1].length; j++) {
                System.out.print(table[i1][j] + "\t");
            }
            System.out.println();
        }

        // 24: Розміри прямокутної матриці
        int[][] matrix = {
                {10, 20, 30, 40},
                {50, 60, 70, 80}
        };
        System.out.println("24. Рядків: " + matrix.length);
        System.out.println("24. Стовпчиків у 1-му рядку: " + matrix[0].length);
        System.out.println("24. Лівий верхній: " + matrix[0][0]);
        System.out.println("24. Правий верхній: " + matrix[0][matrix[0].length - 1]);
        System.out.println("24. Лівий нижній: " + matrix[matrix.length - 1][0]);
        System.out.println("24. Правий нижній: " + matrix[matrix.length - 1][matrix[0].length - 1]);

        // 25: Заповнення матриці порядковими числами
        int[][] grid = new int[3][4];
        int counter = 1;
        System.out.println("25. Заповнена сітка:");
        for (int i1 = 0; i1 < grid.length; i1++) {
            for (int j = 0; j < grid[i1].length; j++) {
                grid[i1][j] = counter++;
                System.out.print(grid[i1][j] + "\t");
            }
            System.out.println();
        }

        // 26: Випадкова матриця та deepToString
        int[][] randomGrid = new int[4][3];
        for (int i1 = 0; i1 < randomGrid.length; i1++) {
            for (int j = 0; j < randomGrid[i1].length; j++) {
                randomGrid[i1][j] = (int) (Math.random() * 90) + 10;
            }
        }
        System.out.println("26. Метод deepToString: " + Arrays.deepToString(randomGrid));
        System.out.println("26. Табличний вигляд:");
        for (int[] row : randomGrid) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }

        // 27: Таблиця множення Піфагора
        int[][] multTable = new int[9][9];
        System.out.println("27. Таблиця Піфагора:");
        for (int i1 = 0; i1 < 9; i1++) {
            for (int j = 0; j < 9; j++) {
                multTable[i1][j] = (i1 + 1) * (j + 1);
                System.out.printf("%4d", multTable[i1][j]);
            }
            System.out.println();
        }

        // 28: Одинична матриця 5x5
        int[][] identity = new int[5][5];
        System.out.println("28. Одинична матриця 5x5:");
        for (int i1 = 0; i1 < 5; i1++) {
            for (int j = 0; j < 5; j++) {
                identity[i1][j] = (i1 == j) ? 1 : 0;
                System.out.print(identity[i1][j] + " ");
            }
            System.out.println();
        }

        // 29: Модифікація рядка та стовпчика
        int[][] modMatrix = new int[4][4];
        for (int i2 = 0; i2 < 4; i2++) {
            Arrays.fill(modMatrix[i2], 1);
        }
        Arrays.fill(modMatrix[1], 0);
        for (int j = 0; j < 4; j++) {
            modMatrix[j][2] = 7;
        }
        System.out.println("29. Модифікована матриця:");
        for (int[] row : modMatrix) {
            System.out.println(Arrays.toString(row));
        }


        // ==========================================
        // Блок 5: Обчислення та алгоритми в двовимірних масивах
        // ==========================================
        System.out.println("\n--- Блок 5 ---");

        // 30: Сума та середнє значення елементів матриці
        int[][] calcMatrix = {
                {3, 5, 2},
                {7, 1, 4},
                {6, 9, 8}
        };
        int totalSum = 0;
        int totalElements = calcMatrix.length * calcMatrix[0].length;
        for (int i2 = 0; i2 < calcMatrix.length; i2++) {
            for (int j = 0; j < calcMatrix[0].length; j++) {
                totalSum += calcMatrix[i2][j];
            }
        }
        double matrixAvg = (double) totalSum / totalElements;
        System.out.println("30. Сума: " + totalSum + ", Середнє: " + matrixAvg);

        // 31: Сума кожного рядка окремо
        int[][] matrix31 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        int[] rowSum = new int[matrix31.length];
        System.out.print("31. ");
        for (int i3 = 0; i3 < matrix31.length; i3++) {
            for (int j1 = 0; j1 < matrix31[i3].length; j1++) {
                rowSum[i3] += matrix31[i3][j1];
            }
            System.out.print("Рядок " + i3 + ": сума = " + rowSum[i3] + (i3 < matrix31.length - 1 ? ", " : ""));
        }
        System.out.println();

        // 32: Сума кожного стовпчика
        int[][] matrix32 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[] colSums = new int[matrix32[0].length];
        for (int col = 0; col < matrix32[0].length; col++) {
            for (int row1 = 0; row1 < matrix32.length; row1++) {
                colSums[col] += matrix32[row1][col];
            }
        }
        System.out.println("32. Матриця:");
        for (int[] row1 : matrix32) {
            System.out.println("    " + Arrays.toString(row1));
        }
        System.out.println("32. Суми стовпчиків: " + Arrays.toString(colSums));

        // 33: Пошук глобального екстремуму в матриці
        int[][] matrix33 = {
                {12, -5, 4},
                {-19, 43, 8},
                {0, -2, 15}
        };
        int maxVal = matrix33[0][0];
        int maxRow = 0;
        int maxCol = 0;
        for (int i4 = 0; i4 < matrix33.length; i4++) {
            for (int j = 0; j < matrix33[i4].length; j++) {
                if (matrix33[i4][j] > maxVal) {
                    maxVal = matrix33[i4][j];
                    maxRow = i4;
                    maxCol = j;
                }
            }
        }
        System.out.println("33. Максимум: " + maxVal + " у комірці [" + maxRow + ", " + maxCol + "]");

        // 34: Сума головної та побічної діагоналей
        int[][] diagMatrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int mainDiagSum = 0;
        int sideDiagSum = 0;
        for (int i3 = 0; i3 < diagMatrix.length; i3++) {
            mainDiagSum += diagMatrix[i3][i3];
            sideDiagSum += diagMatrix[i3][diagMatrix.length - 1 - i3];
        }
        System.out.println("34. Сума головної: " + mainDiagSum + ", побічної: " + sideDiagSum);

        // 35: Транспонування квадратної матриці
        int[][] originalMat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] transposed = new int[3][3];
        for (int i4 = 0; i4 < 3; i4++) {
            for (int j = 0; j < 3; j++) {
                transposed[j][i4] = originalMat[i4][j];
            }
        }
        System.out.println("35. Транспонована matrix:");
        for (int i3 = 0; i3 < 3; i3++) {
            System.out.println("    " + Arrays.toString(originalMat[i3]) + " -> " + Arrays.toString(transposed[i3]));
        }


        // ==========================================
        // Блок 6: Зубчасті та багатовимірні масиви
        // ==========================================
        System.out.println("\n--- Блок 6 ---");

        // 36: Ступінчастий (зубчастий) масив-сходинки
        int[][] ladder = new int[4][];
        System.out.println("36. Масив-сходинки: ");
        for (int i4 = 0; i4 < ladder.length; i4++) {
            ladder[i4] = new int[i4 + 1];
            Arrays.fill(ladder[i4], i4);
            System.out.println("    " + Arrays.toString(ladder[i4]));
        }

        // 37: Розклад навчальних занять на тиждень
        String[][] weekSchedule = new String[5][];
        weekSchedule[0] = new String[]{"Вища математика", "Основи програмування", "Фізика"};
        weekSchedule[1] = new String[]{"Англійська мова", "Історія України"};
        weekSchedule[2] = new String[]{"Основи програмування", "Основи програмування", "Бази даних", "Фізвиховання"};
        weekSchedule[3] = new String[]{"Дискретна математика"};
        weekSchedule[4] = new String[]{"Філософія", "Основи алгоритмізації", "Практикум"};

        String[] dayNames = {"Понеділок", "Вівторок", "Середа", "Четвер", "П'ятниця"};
        System.out.println("37. Розклад:");
        for (int i5 = 0; i5 < weekSchedule.length; i5++) {
            System.out.println("    " + dayNames[i5] + ": пар — " + weekSchedule[i5].length + " " + Arrays.toString(weekSchedule[i5]));
        }

        // 38: Трикутник Паскаля
        int[][] pascal = new int[5][];
        System.out.println("38. Трикутник Паскаля: ");
        for (int i5 = 0; i5 < 5; i5++) {
            pascal[i5] = new int[i5 + 1];
            pascal[i5][0] = 1;
            pascal[i5][i5] = 1;
            for (int j = 1; j < i5; j++) {
                pascal[i5][j] = pascal[i5 - 1][j - 1] + pascal[i5 - 1][j];
            }
            System.out.println("    " + Arrays.toString(pascal[i5]));
        }

        // 39: Тривимірний масив: Просторова сітка 3х3х3
        int[][][] cube = new int[3][3][3];
        System.out.println("39. Зрізи куба:");
        for (int x = 0; x < 3; x++) {
            System.out.println("  --- Шар " + x + " ---");
            for (int y = 0; y < 3; y++) {
                System.out.print("    ");
                for (int z = 0; z < 3; z++) {
                    cube[x][y][z] = x + y + z;
                    System.out.print(cube[x][y][z] + "\t");
                }
                System.out.println();
            }
        }

        // 40: Метеорологічний куб
        double[][][] weather = new double[3][7][2];
        for (int c4 = 0; c4 < 3; c4++) {
            for (int d = 0; d < 7; d++) {
                weather[c4][d][0] = 15.0 + Math.random() * 5; // День
                weather[c4][d][1] = 5.0 + Math.random() * 5;  // Ніч
            }
        }
        String[] cities1 = {"Київ", "Львів", "Одеса"};
        double absoluteMinNight = weather[0][0][1];
        String coldestCity = cities1[0];

        System.out.println("40. Метеостатистика:");
        for (int c4 = 0; c4 < 3; c4++) {
            double cityDaySum = 0;
            for (int d = 0; d < 7; d++) {
                cityDaySum += weather[c4][d][0];
                if (weather[c4][d][1] < absoluteMinNight) {
                    absoluteMinNight = weather[c4][d][1];
                    coldestCity = cities1[c4];
                }
            }
            System.out.printf("  Середня денна температура у місті %s: %.2f\n", cities1[c4], (cityDaySum / 7));
        }
        System.out.printf("  Найхолодніша ніч: %.2f у місті %s\n", absoluteMinNight, coldestCity);
    }
}
