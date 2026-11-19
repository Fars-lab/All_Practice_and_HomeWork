package semester1.semester1.practice11.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Task4ArrayListVsLinkedListPerformance {

    private static final int ELEMENTS_COUNT = 20_000;

    private static String ms(long nanos) {
        return String.format(Locale.US, "%.2f", nanos / 1_000_000.0);
    }

    private static long measureInsertAtStart(List<Integer> list, int n) {
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            list.add(0, i);
        }
        return System.nanoTime() - start;
    }

    private static long measureInsertInMiddle(List<Integer> list, int n) {
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            list.add(list.size() / 2, i);
        }
        return System.nanoTime() - start;
    }


    private static long measureSort(List<Integer> list) {
        long start = System.nanoTime();
        Collections.sort(list);
        return System.nanoTime() - start;
    }

    private static void benchmark() {

        measureInsertAtStart(new ArrayList<>(), 2_000);
        measureInsertAtStart(new LinkedList<>(), 2_000);

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        System.out.println("1. БЕНЧМАРК ВСТАВКИ У ПОЧАТОК СПИСКУ (на " + ELEMENTS_COUNT + " елементів):");
        long arrayStart = measureInsertAtStart(arrayList, ELEMENTS_COUNT);
        long linkedStart = measureInsertAtStart(linkedList, ELEMENTS_COUNT);
        System.out.println("  - Час для ArrayList:  " + ms(arrayStart) + " мс");
        System.out.println("  - Час для LinkedList: " + ms(linkedStart) + " мс");
        if (linkedStart < arrayStart) {
            System.out.println("   (Висновок: LinkedList суттєво виграє при частих вставках на початку: O(1) проти O(N)"
                    + " через System.arraycopy в ArrayList).");
        } else {
            System.out.println("   (На цьому запуску ArrayList не програв; збільште кількість елементів для наочної різниці).");
        }

        List<Integer> arrayMiddle = new ArrayList<>();
        List<Integer> linkedMiddle = new LinkedList<>();
        System.out.println("\n2. БЕНЧМАРК ВСТАВКИ В СЕРЕДИНУ СПИСКУ (на " + ELEMENTS_COUNT + " елементів):");
        long arrayMid = measureInsertInMiddle(arrayMiddle, ELEMENTS_COUNT);
        long linkedMid = measureInsertInMiddle(linkedMiddle, ELEMENTS_COUNT);
        System.out.println("  - Час для ArrayList:  " + ms(arrayMid) + " мс (зсув половини масиву)");
        System.out.println("  - Час для LinkedList: " + ms(linkedMid) + " мс (пошук вузла Node від краю до середини)");
        System.out.println("   (Висновок: " + (arrayMid < linkedMid ? "ArrayList" : "LinkedList")
                + " швидший у цьому тесті; обидві структури мають O(N) при вставці в середину,"
                + " але зсув масиву через System.arraycopy виконується швидко завдяки неперервній пам'яті).");

        System.out.println("\n3. БЕНЧМАРК СОРТУВАННЯ (" + ELEMENTS_COUNT + " випадкових чисел):");
        Random random = new Random(42);
        List<Integer> arraySort = new ArrayList<>();
        List<Integer> linkedSort = new LinkedList<>();
        for (int i = 0; i < ELEMENTS_COUNT; i++) {
            int value = random.nextInt(1_000_000);
            arraySort.add(value);
            linkedSort.add(value);
        }
        long arraySortTime = measureSort(arraySort);
        long linkedSortTime = measureSort(linkedSort);
        System.out.println("  - Час для ArrayList:  " + ms(arraySortTime) + " мс");
        System.out.println("  - Час для LinkedList: " + ms(linkedSortTime) + " мс");
        System.out.println("  - Результати сортування збігаються: " + arraySort.equals(linkedSort));
    }

    private static void sortingDemo() {
        System.out.println("\n4. СОРТУВАННЯ СПИСКУ:");
        List<Integer> numbers = new ArrayList<>(List.of(45, 12, 89, 3, 22, 67));
        System.out.println("   До сортування: " + numbers);
        Collections.sort(numbers);
        System.out.println("   Після Collections.sort(): " + numbers);

        List<String> words = new ArrayList<>(Arrays.asList("Kotlin", "Java", "Python", "C++"));
        System.out.println("   Рядки до сортування: " + words);
        words.sort(null); // природний порядок (метод List.sort)
        System.out.println("   Рядки після words.sort(null): " + words);
    }

    private static void subListDemo() {
        System.out.println("\n5. РОБОТА З subList():");
        List<Integer> original = new ArrayList<>(List.of(3, 12, 22, 45, 67, 89));
        System.out.println("   Оригінальний список: " + original);

        List<Integer> sub = original.subList(1, 4);
        System.out.println("   Виділений subList (від індексу 1 до 4): " + sub);

        System.out.println("   Змінюємо елемент у subList на 999...");
        sub.set(0, 999);
        System.out.println("   subList після зміни: " + sub);
        System.out.println("   Оновлений оригінальний список: " + original);

        sub.clear();
        System.out.println("   Після sub.clear() оригінальний список: " + original);

        List<Integer> linked = new LinkedList<>(List.of(10, 20, 30, 40, 50));
        List<Integer> linkedSub = linked.subList(1, 3);
        linkedSub.set(1, 777);
        System.out.println("   LinkedList: subList " + linkedSub + " -> оригінал " + linked);
    }

    public static void main(String[] args) {
        System.out.println("================ БЕНЧМАРК ТА РОБОТА ЗІ СПИСКАМИ ================");
        benchmark();
        sortingDemo();
        subListDemo();
        System.out.println("================================================================");
    }
}
