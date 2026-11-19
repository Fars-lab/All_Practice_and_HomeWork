package semester1.practice01;

import java.util.*;

public class Practice11 {

    public static void demonstrateWrapperTwins() {
        Byte b = Byte.valueOf((byte) 1);
        Short s = Short.valueOf((short) 2);
        Integer i = Integer.valueOf(3);
        Long l = Long.valueOf(4L);
        Float f = Float.valueOf(5.5f);
        Double d = Double.valueOf(6.6);
        Character c = Character.valueOf('Z');
        Boolean bool = Boolean.valueOf(true);

        // ArrayList<int> не компілюється: узагальнення (generics) працюють лише з посилальними типами,
        // тому для примітива int потрібен клас-близнюк Integer.
        // ArrayList<int> bad = new ArrayList<>(); // ПОМИЛКА КОМПІЛЯЦІЇ
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        System.out.println("ArrayList<Integer>: " + list);

        Object[] all = {b, s, i, l, f, d, c, bool};
        for (Object o : all) {
            System.out.println(o + " -> " + o.getClass().getSimpleName());
        }
    }

    // Завдання 2
    public static void showCompilerUnderTheHood() {
        List<Integer> list = new ArrayList<>();

        // Синтаксичний цукор
        int a = 42;
        Integer boxed = a;
        list.add(100);
        int unboxed = list.get(0);
        System.out.println("Цукор: boxed=" + boxed + ", unboxed=" + unboxed);

        // Те, що бачить компілятор
        List<Integer> list2 = new ArrayList<>();
        int a2 = 42;
        Integer boxed2 = Integer.valueOf(a2);
        list2.add(Integer.valueOf(100));
        int unboxed2 = list2.get(0).intValue();
        System.out.println("Явно: boxed=" + boxed2 + ", unboxed=" + unboxed2);

        Double dBoxed = Double.valueOf(3.14);
        double dPrim = dBoxed.doubleValue();
        Boolean bBoxed = Boolean.valueOf(true);
        boolean bPrim = bBoxed.booleanValue();
        System.out.println("Double: " + dBoxed + " -> " + dPrim);
        System.out.println("Boolean: " + bBoxed + " -> " + bPrim);
    }

    // Завдання 3
    public static List<Integer> parseNumbersFromStrings(String[] inputs) {
        List<Integer> result = new ArrayList<>();
        int sum = 0;
        for (String str : inputs) {
            try {
                int prim = Integer.parseInt(str);
                sum += prim;
                Integer obj = Integer.valueOf(str);
                result.add(obj);
            } catch (NumberFormatException e) {
                System.out.println("Попередження: некоректний рядок \"" + str + "\"");
            }
        }
        System.out.println("Сума коректних чисел: " + sum);
        return result;
    }

    // Завдання 4
    public static void findMinAndMax(List<Integer> numbers) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int n : numbers) {
            if (n < min) min = n;
            if (n > max) max = n;
        }
        System.out.println("min = " + min + ", max = " + max);
    }

    public static void task4() {
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);
        System.out.println("Byte: " + Byte.MIN_VALUE + " .. " + Byte.MAX_VALUE);
        System.out.println("Short: " + Short.MIN_VALUE + " .. " + Short.MAX_VALUE);
        System.out.println("Long: " + Long.MIN_VALUE + " .. " + Long.MAX_VALUE);

        findMinAndMax(List.of(5, -12, 77, 0, 31, -3));

        int over = Integer.MAX_VALUE + 1;
        System.out.println("MAX_VALUE + 1 == MIN_VALUE ? " + (over == Integer.MIN_VALUE));

        int current = Integer.MAX_VALUE;
        int add = 1;
        if (current > Integer.MAX_VALUE - add) {
            System.out.println("Додавання небезпечне: буде переповнення");
        } else {
            System.out.println("Результат: " + (current + add));
        }
    }

    // Завдання 5
    public static void testIntegerPool() {
        Integer a = 100, b = 100;
        Integer x = 250, y = 250;
        System.out.println("100: a == b  -> " + (a == b));
        System.out.println("100: a.equals(b) -> " + a.equals(b));
        System.out.println("250: x == y  -> " + (x == y));
        System.out.println("250: x.equals(y) -> " + x.equals(y));
        System.out.println("Пояснення: Integer.valueOf кешує об'єкти в діапазоні [-128; 127], тому для 100 обидва "
                + "посилання вказують на один об'єкт. Для 250 створюються два різні об'єкти, "
                + "== порівнює посилання (false), а equals порівнює значення (true).");
    }

    // Завдання 6
    public static int sumIgnoringNulls(List<Integer> list) {
        int sum = 0;
        for (Integer element : list) {
            if (element != null) {
                sum += element;
            }
        }
        return sum;
    }

    public static void task6() {
        ArrayList<Integer> temperatures = new ArrayList<>();
        temperatures.add(18);
        temperatures.add(22);
        temperatures.add(null);
        temperatures.add(15);
        temperatures.add(null);
        temperatures.add(20);
        try {
            int t = temperatures.get(2);
            System.out.println(t);
        } catch (NullPointerException e) {
            System.out.println("NPE: авторозпакування викликає null.intValue()");
        }
        System.out.println("Сума без null: " + sumIgnoringNulls(temperatures));
    }

    // Завдання 7
    public static List<Integer> extractDigitsFromText(String text) {
        List<Integer> digits = new ArrayList<>();
        for (char ch : text.toCharArray()) {
            if (Character.isDigit(ch)) {
                digits.add(Character.getNumericValue(ch));
            }
        }
        return digits;
    }

    public static void task7() {
        System.out.println("Цифри: " + extractDigitsFromText("Server-04: CPU 85%, RAM 16GB"));
        double d1 = Double.parseDouble("36.6");
        Double d2 = Double.valueOf("99.95");
        System.out.println("parseDouble: " + d1 + ", valueOf: " + d2);
        double nan = 0.0 / 0.0;
        double inf = 1.0 / 0.0;
        System.out.println("isNaN(nan) = " + Double.isNaN(nan) + ", isInfinite(inf) = " + Double.isInfinite(inf));
        System.out.println("isNaN(36.6) = " + Double.isNaN(d1) + ", isInfinite(36.6) = " + Double.isInfinite(d1));
    }

    // Завдання 8
    public static void convertNumberSystems(int number) {
        System.out.println(number + " у двійковій: " + Integer.toBinaryString(number));
        System.out.println(number + " у вісімковій: " + Integer.toOctalString(number));
        System.out.println(number + " у шістнадцятковій: " + Integer.toHexString(number));
        System.out.println("\"101010\" (2) -> " + Integer.parseInt("101010", 2));
        System.out.println("\"FF\" (16) -> " + Integer.parseInt("FF", 16));
    }

    // ===================== Блок 2 =====================

    // Завдання 9
    public static void task9() {
        String[] fixedArray = new String[3];
        fixedArray[0] = "Київ";
        fixedArray[1] = "Львів";
        fixedArray[2] = "Одеса";
        try {
            fixedArray[3] = "Харків";
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Масив фіксованої довжини: " + e.getMessage());
        }
        ArrayList<String> dynamicList = new ArrayList<>();
        dynamicList.add("Київ");
        dynamicList.add("Львів");
        dynamicList.add("Одеса");
        dynamicList.add("Харків");
        dynamicList.add("Дніпро");
        dynamicList.add(1, "Вінниця");
        dynamicList.remove(0);
        System.out.println("Масив: " + Arrays.toString(fixedArray));
        System.out.println("ArrayList: " + dynamicList);
    }

    // Завдання 10
    public static List<Integer> traceArrayListCapacity(int elementsCount) {
        List<Integer> growthPoints = new ArrayList<>();
        int capacity = 0;
        int size = 0;
        for (int i = 1; i <= elementsCount; i++) {
            if (capacity == 0) {
                capacity = 10;
                System.out.println("Елемент " + i + ": створено масив ємністю 10");
            } else if (size + 1 > capacity) {
                int newCapacity = capacity + (capacity >> 1);
                System.out.println("Елемент " + i + ": розширення " + capacity + " -> " + newCapacity);
                capacity = newCapacity;
                growthPoints.add(i);
            }
            size++;
        }
        return growthPoints;
    }

    // Завдання 11
    public static Object[] growArrayManually(Object[] oldData) {
        int newLength = oldData.length + (oldData.length >> 1);
        Object[] newData = new Object[newLength];
        System.arraycopy(oldData, 0, newData, 0, oldData.length);
        newData[10] = 11;
        System.out.println("oldData: " + Arrays.toString(oldData));
        System.out.println("newData: " + Arrays.toString(newData));
        return newData;
    }

    public static void task11() {
        Object[] oldData = new Object[10];
        for (int i = 0; i < 10; i++) oldData[i] = i + 1;
        Object[] newData = growArrayManually(oldData);

        // Вставка на індекс 2: зсув елементів вправо
        int index = 2;
        int size = 11;
        System.arraycopy(newData, index, newData, index + 1, size - index);
        newData[index] = 99;
        System.out.println("Після вставки 99 на індекс 2: " + Arrays.toString(newData));
    }

    // Завдання 12
    public static void task12() {
        ArrayList<String> pipeline = new ArrayList<>();
        pipeline.add("Compile");
        pipeline.add("Package");
        pipeline.add("Deploy");
        System.out.println(pipeline + " size=" + pipeline.size());
        pipeline.add(0, "Checkout Git");
        System.out.println(pipeline + " size=" + pipeline.size());
        pipeline.add(pipeline.indexOf("Package"), "Run Unit Tests");
        System.out.println(pipeline + " size=" + pipeline.size());
    }

    // Завдання 13
    public static void task13() {
        ArrayList<Integer> speeds = new ArrayList<>(List.of(60, 85, 110, 95, 130));
        System.out.println("Перший: " + speeds.get(0));
        System.out.println("Центральний: " + speeds.get(speeds.size() / 2));
        System.out.println("Останній: " + speeds.get(speeds.size() - 1));
        for (int i = 0; i < speeds.size(); i++) {
            if (speeds.get(i) > 100) {
                Integer old = speeds.set(i, 100);
                System.out.println("Індекс " + i + ": старе значення " + old + " -> 100");
            }
        }
        System.out.println("Результат: " + speeds);
    }

    // Завдання 14
    static class SimpleArrayList {
        private Object[] data = new Object[10];
        private int size = 0;

        public void add(Object element) {
            if (size == data.length) {
                int newCapacity = data.length + (data.length >> 1);
                Object[] newData = new Object[newCapacity];
                System.arraycopy(data, 0, newData, 0, data.length);
                data = newData;
            }
            data[size++] = element;
        }

        public Object get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
            }
            return data[index];
        }

        public int size() {
            return size;
        }

        public int getCapacity() {
            return data.length;
        }
    }

    public static void task14() {
        SimpleArrayList list = new SimpleArrayList();
        System.out.println("Початкова ємність: " + list.getCapacity());
        for (int i = 1; i <= 12; i++) {
            list.add(i);
        }
        System.out.println("size = " + list.size() + ", capacity = " + list.getCapacity());
        System.out.println("get(11) = " + list.get(11));
    }

    // Завдання 15
    public static void task15() {
        // Відома кількість елементів: масив одразу потрібного розміру, зайвих копіювань System.arraycopy немає
        ArrayList<String> logs = new ArrayList<>(500);
        for (int i = 0; i < 300; i++) logs.add("log " + i);
        System.out.println("size = " + logs.size());
        logs.ensureCapacity(1000); // гарантує місце під 1000 елементів
        System.out.println("ensureCapacity(1000) викликано, розмір списку: " + logs.size());
        logs.trimToSize(); // обрізає внутрішній масив рівно до size
        System.out.println("trimToSize() викликано, size = " + logs.size());
    }

    // Завдання 16
    public static ArrayList<Integer> extractPassedScores(List<Integer> allScores, int minPassScore) {
        ArrayList<Integer> passed = new ArrayList<>();
        for (int i = 0; i < allScores.size(); i++) {
            if (allScores.get(i) >= minPassScore) {
                passed.add(allScores.get(i));
            }
        }
        return passed;
    }

    public static void task16() {
        ArrayList<Integer> scores = new ArrayList<>(List.of(45, 88, 92, 59, 74, 100, 38, 65));
        ArrayList<Integer> passed = extractPassedScores(scores, 60);
        System.out.println("Початковий size: " + scores.size());
        System.out.println("Відраховано: " + (scores.size() - passed.size()));
        System.out.println("Зараховані: " + passed);
    }

    // ===================== Блок 3 =====================

    // Завдання 17
    public static void task17() {
        ArrayList<Integer> numbers = new ArrayList<>(List.of(10, 20, 30, 1, 40, 2));
        numbers.remove(1);
        System.out.println("remove(1): " + numbers + " (видалено елемент за індексом 1, тобто 20)");
        numbers.remove(Integer.valueOf(1));
        numbers.remove(Integer.valueOf(2));
        System.out.println("remove(Integer.valueOf(..)): " + numbers + " (видалено значення 1 та 2)");
    }

    // Завдання 18
    public static int countOccurrences(List<String> list, String target) {
        int count = 0;
        for (String s : list) {
            if (Objects.equals(s, target)) count++;
        }
        return count;
    }

    public static void task18() {
        ArrayList<String> langs = new ArrayList<>(List.of("Java", "Python", "C++", "Java", "Kotlin", "Java"));
        System.out.println("contains Kotlin: " + langs.contains("Kotlin"));
        System.out.println("contains Rust: " + langs.contains("Rust"));
        System.out.println("indexOf Java: " + langs.indexOf("Java"));
        System.out.println("lastIndexOf Java: " + langs.lastIndexOf("Java"));
        System.out.println("Java зустрічається " + countOccurrences(langs, "Java") + " раз(и)");
    }

    // Завдання 19
    public static void task19() {
        ArrayList<String> cart = new ArrayList<>();
        System.out.println("isEmpty: " + cart.isEmpty() + ", size: " + cart.size());
        cart.add("Хліб");
        cart.add("Молоко");
        cart.add("Сир");
        cart.add("Кава");
        System.out.println("isEmpty: " + cart.isEmpty() + ", size: " + cart.size());
        cart.clear();
        System.out.println("isEmpty: " + cart.isEmpty() + ", size: " + cart.size());
        System.out.println("clear() обнуляє всі комірки внутрішнього Object[] (null) та ставить size = 0; "
                + "ємність масиву при цьому не зменшується.");
    }

    // Завдання 20
    public static void task20() {
        ArrayList<String> cities = new ArrayList<>(List.of("Київ", "Львів", "Одеса", "Харків", "Дніпро"));
        Object[] objArray = cities.toArray();
        String[] strArray = cities.toArray(new String[0]);
        System.out.println("Object[]: " + Arrays.toString(objArray));
        System.out.println("String[]: " + Arrays.toString(strArray));
        ArrayList<String> back = new ArrayList<>(Arrays.asList(strArray));
        System.out.println("Назад у ArrayList: " + back);
    }

    // Завдання 21
    public static void manageTaskList() {
        ArrayList<String> tasks = new ArrayList<>();
        System.out.println("isEmpty: " + tasks.isEmpty());
        tasks.add("Написати код");
        tasks.add("Протестувати");
        tasks.add("Зробити ревʼю");
        tasks.add("Задеплоїти");
        tasks.add("Написати документацію");
        tasks.add(0, "ТЕРМІНОВО: виправити баг");
        System.out.println(tasks);
        System.out.println("get(2): " + tasks.get(2));
        tasks.set(2, "Протестувати (оновлено)");
        System.out.println("contains \"Задеплоїти\": " + tasks.contains("Задеплоїти"));
        tasks.remove("Задеплоїти");
        tasks.remove(0);
        System.out.println(tasks);
        System.out.println("size: " + tasks.size());
        Object[] arr = tasks.toArray();
        System.out.println("toArray: " + Arrays.toString(arr));
        tasks.clear();
        System.out.println("Після clear: size = " + tasks.size());
    }

    // Завдання 22
    public static <T> ArrayList<T> deduplicate(List<T> source) {
        ArrayList<T> unique = new ArrayList<>();
        for (T item : source) {
            if (!unique.contains(item)) {
                unique.add(item);
            }
        }
        return unique;
    }

    public static void task22() {
        ArrayList<Integer> ints = new ArrayList<>(List.of(5, 12, 5, 8, 12, 20, 5));
        ArrayList<String> strs = new ArrayList<>(List.of("a", "b", "a", "c", "b"));
        System.out.println(ints + " -> " + deduplicate(ints));
        System.out.println(strs + " -> " + deduplicate(strs));
    }

    // Завдання 23
    public static List<String> intersection(List<String> listA, List<String> listB) {
        List<String> result = new ArrayList<>();
        for (String s : listA) {
            if (listB.contains(s)) result.add(s);
        }
        return result;
    }

    public static List<String> onlyFirst(List<String> listA, List<String> listB) {
        List<String> result = new ArrayList<>(listA);
        result.removeAll(listB);
        return result;
    }

    public static void task23() {
        List<String> listA = new ArrayList<>(List.of("Анна", "Богдан", "Віка", "Гліб", "Дмитро"));
        List<String> listB = new ArrayList<>(List.of("Богдан", "Гліб", "Єва", "Жанна"));
        System.out.println("Склали обидва (contains): " + intersection(listA, listB));
        List<String> viaRetain = new ArrayList<>(listA);
        viaRetain.retainAll(listB);
        System.out.println("Склали обидва (retainAll): " + viaRetain);
        System.out.println("Лише Java (removeAll): " + onlyFirst(listA, listB));
    }

    // Завдання 24
    public static int replaceAllValues(List<String> list, String oldValue, String newValue) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i), oldValue)) {
                list.set(i, newValue);
                count++;
            }
        }
        return count;
    }

    public static void task24() {
        List<String> list = new ArrayList<>(List.of("a", "b", "a", "c", "a"));
        int n = replaceAllValues(list, "a", "X");
        System.out.println(list + ", замін: " + n);
    }

    // ===================== Блок 4 =====================

    // Завдання 25
    public static void demonstrateTypeInference() {
        List<Integer> list1 = new ArrayList<>();
        var list2 = new ArrayList<Integer>();
        var names = new ArrayList<String>(List.of("Анна", "Богдан"));
        list1.add(1);
        list2.add(2);
        names.add("Віка");
        // list1.add("текст");  // помилка компіляції: очікується Integer
        // names.add(5);        // помилка компіляції: очікується String
        System.out.println(list1 + " " + list2 + " " + names);
    }

    // Завдання 26
    public static void demonstrateVarDiamondDanger() {
        var unsafeList = new ArrayList<>(); // ArrayList<Object>
        unsafeList.add(42);
        unsafeList.add("Java");
        unsafeList.add(true);
        try {
            for (int i = 0; i < unsafeList.size(); i++) {
                Integer value = (Integer) unsafeList.get(i);
                System.out.println("Прочитано: " + value);
            }
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: " + e.getMessage());
        }
        var safeList = new ArrayList<Integer>();
        safeList.add(42);
        // safeList.add("Java"); // помилка компіляції
        System.out.println("safeList: " + safeList);
    }

    // Завдання 27
    private static void checkHierarchy(String name, Object o) {
        System.out.println(name + ": Iterable=" + (o instanceof Iterable)
                + ", Collection=" + (o instanceof Collection)
                + ", List=" + (o instanceof List)
                + ", Set=" + (o instanceof Set)
                + ", Queue=" + (o instanceof Queue));
    }

    public static void exploreCollectionHierarchy() {
        checkHierarchy("ArrayList", new ArrayList<String>());
        checkHierarchy("LinkedList", new LinkedList<String>());
        checkHierarchy("Vector", new Vector<String>());
        checkHierarchy("Stack", new Stack<String>());
        checkHierarchy("HashSet", new HashSet<String>());
        checkHierarchy("ArrayDeque", new ArrayDeque<String>());
        checkHierarchy("PriorityQueue", new PriorityQueue<String>());
    }

    // Завдання 28
    public static <E> void printElements(Iterable<E> iterable) {
        Iterator<E> it = iterable.iterator();
        StringBuilder sb = new StringBuilder("[");
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) sb.append(", ");
        }
        sb.append("]");
        System.out.println("Elements: " + sb);
    }

    public static <E> void printSummary(Collection<E> collection) {
        System.out.println("size=" + collection.size() + ", isEmpty=" + collection.isEmpty());
    }

    public static <E> E getFirstAndLast(List<E> list) {
        E first = list.get(0);
        E last = list.get(list.size() - 1);
        System.out.println("first=" + first + ", last=" + last);
        return first;
    }

    public static void task28() {
        List<List<String>> lists = new ArrayList<>();
        lists.add(new ArrayList<>(List.of("a", "b", "c")));
        lists.add(new LinkedList<>(List.of("d", "e", "f")));
        lists.add(new Vector<>(List.of("g", "h", "i")));
        Stack<String> stack = new Stack<>();
        stack.push("j");
        stack.push("k");
        stack.push("l");
        lists.add(stack);
        for (List<String> l : lists) {
            System.out.println("--- " + l.getClass().getSimpleName());
            printElements(l);
            printSummary(l);
            getFirstAndLast(l);
        }
    }

    // Завдання 29
    public static void task29() {
        ArrayList<String> arrayList = new ArrayList<>(List.of("A", "B", "C", "D"));
        System.out.println("ArrayList get(2): " + arrayList.get(2));

        LinkedList<String> linked = new LinkedList<>(List.of("B", "C"));
        linked.addFirst("A");
        linked.addLast("D");
        System.out.println("LinkedList: " + linked);

        Vector<String> vector = new Vector<>();
        vector.addElement("X");
        vector.addElement("Y");
        System.out.println("Vector elementAt(1): " + vector.elementAt(1));

        Stack<String> stack = new Stack<>();
        stack.push("Книга 1");
        stack.push("Книга 2");
        stack.push("Книга 3");
        System.out.println("peek: " + stack.peek());
        while (!stack.isEmpty()) {
            System.out.println("pop: " + stack.pop());
        }
    }

    // Завдання 30
    public static void task30() {
        ArrayList<String> modules = new ArrayList<>(List.of("Core Java", "OOP", "Collections", "Streams", "Multithreading"));
        Iterator<String> it = modules.iterator();
        int totalChars = 0;
        while (it.hasNext()) {
            String m = it.next();
            System.out.println(m);
            totalChars += m.length();
        }
        System.out.println("Сумарна кількість символів: " + totalChars);
    }

    // Завдання 31
    public static void removeNegativesWithIterator(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            if (it.next() < 0) {
                it.remove();
            }
        }
        System.out.println("Очищений список: " + list);
    }

    public static void task31() {
        ArrayList<Integer> data = new ArrayList<>(List.of(15, -3, 42, -10, 8, -1, 25));
        try {
            for (Integer x : data) {
                if (x < 0) {
                    data.remove(x);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException: список змінено напряму під час обходу "
                    + "через for-each, тому ітератор помітив зміну modCount.");
        }
        ArrayList<Integer> data2 = new ArrayList<>(List.of(15, -3, 42, -10, 8, -1, 25));
        removeNegativesWithIterator(data2);
    }

    // Завдання 32
    public static void task32() {
        ArrayList<String> logs = new ArrayList<>(List.of("INFO: Start", "DEBUG: Init", "ERROR: Timeout", "DEBUG: Retry", "INFO: Done"));
        Iterator<String> it = logs.iterator();
        while (it.hasNext()) {
            if (it.next().startsWith("DEBUG:")) {
                it.remove();
            }
        }
        System.out.println("Без DEBUG: " + logs);

        ListIterator<String> listIt = logs.listIterator();
        while (listIt.hasNext()) {
            String s = listIt.next();
            System.out.println("Вперед: " + s);
            if (s.contains("ERROR")) {
                listIt.set("CRITICAL ERROR: Timeout");
            }
        }
        while (listIt.hasPrevious()) {
            System.out.println("Назад: " + listIt.previous());
        }
        System.out.println("Результат: " + logs);
    }

    // ===================== Блок 5 =====================

    // Завдання 33
    public static void splitEvenAndOdd(List<Integer> source, List<Integer> evens, List<Integer> odds) {
        Iterator<Integer> it = source.iterator();
        while (it.hasNext()) {
            int n = it.next();
            if (n % 2 == 0) {
                evens.add(n);
            } else {
                odds.add(n);
            }
        }
        System.out.println("Парні: " + evens + ", size=" + evens.size() + ", max=" + maxOf(evens));
        System.out.println("Непарні: " + odds + ", size=" + odds.size() + ", max=" + maxOf(odds));
    }

    private static int maxOf(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        for (int n : list) {
            if (n > max) max = n;
        }
        return max;
    }

    public static void task33() {
        List<Integer> source = new ArrayList<>(List.of(3, 8, 15, 22, 7, 10, 41, 6));
        splitEvenAndOdd(source, new ArrayList<>(), new ArrayList<>());
    }

    // Завдання 34
    public static int removeInRange(List<Integer> list, int min, int max) {
        int removed = 0;
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int n = it.next();
            if (n >= min && n <= max) {
                it.remove();
                removed++;
            }
        }
        return removed;
    }

    public static void task34() {
        List<Integer> list = new ArrayList<>(List.of(5, 12, 18, 25, 30, 7, 14, 40, 3, 22));
        System.out.println("До: " + list);
        int removed = removeInRange(list, 10, 25);
        System.out.println("Після: " + list + ", видалено: " + removed);
    }

    // Завдання 35
    public static <T> void reverseInPlace(List<T> list) {
        for (int i = 0; i < list.size() / 2; i++) {
            int j = list.size() - 1 - i;
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }

    public static void task35() {
        List<String> list = new ArrayList<>(List.of("a", "b", "c", "d", "e"));
        reverseInPlace(list);
        System.out.println(list);
    }

    // Завдання 36
    public static String[] mergeUniqueToArray(List<String> first, List<String> second) {
        var merged = new ArrayList<String>();
        for (String s : first) {
            if (!merged.contains(s)) merged.add(s);
        }
        for (String s : second) {
            if (!merged.contains(s)) merged.add(s);
        }
        return merged.toArray(new String[0]);
    }

    public static void task36() {
        String[] result = mergeUniqueToArray(List.of("a", "b", "c"), List.of("b", "d", "a", "e"));
        System.out.println(Arrays.toString(result));
    }

    // Завдання 37
    public static <T> void rotateRight(List<T> list, int k) {
        for (int i = 0; i < k; i++) {
            if (!list.isEmpty()) {
                T last = list.remove(list.size() - 1);
                list.add(0, last);
            }
        }
    }

    public static void task37() {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        rotateRight(list, 2);
        System.out.println(list);
    }

    // Завдання 38
    public static void task38() {
        List<String> receiptLines = List.of("Хліб: 35", "Молоко: 48", "Помилка сканера: N/A", "Кава: 240", "Пакет: 5", "Сир: 185");
        var prices = new ArrayList<Integer>();
        for (String line : receiptLines) {
            try {
                String part = line.substring(line.indexOf(':') + 1).trim();
                prices.add(Integer.parseInt(part));
            } catch (NumberFormatException e) {
                System.out.println("Пропущено некоректний рядок: " + line);
            }
        }
        System.out.println("Усі ціни: " + prices);
        Iterator<Integer> it = prices.iterator();
        while (it.hasNext()) {
            if (it.next() < 40) {
                it.remove();
            }
        }
        int total = 0;
        for (int p : prices) total += p;
        System.out.println("Після фільтрації: " + prices + ", до сплати: " + total + " грн");
    }

    // Завдання 39
    public static void task39() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        int n = 20_000;

        long start = System.nanoTime();
        for (int i = 0; i < n; i++) arrayList.add(0, i);
        long arrayInsert = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < n; i++) linkedList.add(0, i);
        long linkedInsert = System.nanoTime() - start;

        System.out.println("Вставка на початок: ArrayList = " + arrayInsert / 1_000 + " мкс, LinkedList = "
                + linkedInsert / 1_000 + " мкс");
        System.out.println("ArrayList повільніший, бо при кожній вставці System.arraycopy зсуває всі елементи.");

        long sum = 0;
        start = System.nanoTime();
        for (int i = 0; i < n; i++) sum += arrayList.get(arrayList.size() / 2);
        long arrayGet = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < n; i++) sum += linkedList.get(linkedList.size() / 2);
        long linkedGet = System.nanoTime() - start;

        System.out.println("Читання з середини: ArrayList = " + arrayGet / 1_000 + " мкс, LinkedList = "
                + linkedGet / 1_000 + " мкс (sum=" + sum + ")");
        System.out.println("ArrayList виконує get майже миттєво завдяки доступу за індексом у внутрішньому Object[]; "
                + "LinkedList змушений проходити вузли від краю до середини.");
    }

    // ===================== Приклади з лекції =====================

    public static void lectureExamples() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Integer objNumber = 150;
        int primitiveNumber = objNumber;
        Integer result = objNumber + 50;

        ArrayList<Integer> list = new ArrayList<>();
        list.add(25);
        int first = list.get(0);

        Integer objNumber1 = Integer.valueOf(150);
        int primitiveNumber1 = objNumber1.intValue();
        Integer result2 = Integer.valueOf(objNumber1.intValue() + 50);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(Integer.valueOf(25));
        int first2 = list2.get(0).intValue();

        Integer a = 100;
        Integer b = 100;
        System.out.println(a == b);

        Integer x = 500;
        Integer y = 500;
        System.out.println(x == y);
        System.out.println(x.equals(y));

        Integer emptyBox = null;

        int parsedPrimitive = Integer.parseInt("4096");

        Integer fromInt = Integer.valueOf(4096);
        Integer fromStr = Integer.valueOf("4096");

        int maxInt = Integer.MAX_VALUE;
        int minInt = Integer.MIN_VALUE;

        String binary = Integer.toBinaryString(42);
        String hex = Integer.toHexString(255);
        double price = Double.parseDouble("199.95");
        boolean isNum = Character.isDigit('7');
        boolean isLet = Character.isLetter('Ї');

        ArrayList<Integer> oldStyle = new ArrayList<Integer>();
        ArrayList<Integer> numbers1 = new ArrayList<>();
        var list4 = new ArrayList<Integer>();
        list4.add(42);

        ArrayList<Integer> numbers2 = new ArrayList<>(List.of(10, -5, 20, -8, 30));
        Iterator<Integer> it = numbers2.iterator();
        while (it.hasNext()) {
            Integer current = it.next();
            if (current < 0) {
                it.remove();
            }
        }
        System.out.println(numbers2);

        // Вивід решти значень, щоб побачити результат
        System.out.println(primitiveNumber + " " + result + " " + first + " " + primitiveNumber1 + " "
                + result2 + " " + first2 + " " + emptyBox + " " + parsedPrimitive + " " + fromInt + " " + fromStr);
        System.out.println(maxInt + " " + minInt + " " + binary + " " + hex + " " + price + " " + isNum + " " + isLet);
        System.out.println(numbers + " " + oldStyle + " " + numbers1 + " " + list4);
    }

    // ===================== main =====================

    private static void header(int n) {
        System.out.println("\n=== Завдання " + n + " ===");
    }

    public static void main(String[] args) {
        header(1); demonstrateWrapperTwins();
        header(2); showCompilerUnderTheHood();
        header(3);
        System.out.println(parseNumbersFromStrings(new String[]{"120", "-45", "300", "abc", "1024", ""}));
        header(4); task4();
        header(5); testIntegerPool();
        header(6); task6();
        header(7); task7();
        header(8); convertNumberSystems(42);
        header(9); task9();
        header(10);
        System.out.println("Точки розширення: " + traceArrayListCapacity(100));
        header(11); task11();
        header(12); task12();
        header(13); task13();
        header(14); task14();
        header(15); task15();
        header(16); task16();
        header(17); task17();
        header(18); task18();
        header(19); task19();
        header(20); task20();
        header(21); manageTaskList();
        header(22); task22();
        header(23); task23();
        header(24); task24();
        header(25); demonstrateTypeInference();
        header(26); demonstrateVarDiamondDanger();
        header(27); exploreCollectionHierarchy();
        header(28); task28();
        header(29); task29();
        header(30); task30();
        header(31); task31();
        header(32); task32();
        header(33); task33();
        header(34); task34();
        header(35); task35();
        header(36); task36();
        header(37); task37();
        header(38); task38();
        header(39); task39();
        System.out.println("\n=== Приклади з лекції ===");
        lectureExamples();
    }
}
