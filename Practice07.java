package semester1.practice01;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Practice07 {

    private static Scanner in;

    // ---------- Налаштування UTF-8 для консолі ----------
    private static void setupConsole() {
        try {
            if (System.getProperty("os.name", "").toLowerCase().contains("win")) {
                // переключаємо кодову сторінку консолі Windows на UTF-8
                new ProcessBuilder("cmd", "/c", "chcp 65001 > nul").inheritIO().start().waitFor();
            }
        } catch (Exception ignored) {
        }
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err), true, StandardCharsets.UTF_8));
        in = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    // ---------- Головне меню ----------
    private static final String[] NAMES = {
        "Book", "Student", "BankAccount", "Point2D", "Product", "Time", "Rectangle", "Employee",
        "User", "Fraction", "Car", "SmartBulb", "QueryBuilder", "CoffeeOrder", "Box", "FlightTicket",
        "MathOperations", "DocumentPrinter", "AreaCalculator", "Logger", "ArraySearcher", "DateService",
        "ArrayFilter", "UserAccount", "ServerConfig", "MathUtils", "CurrencyExchage (ввід)", "FleetCar",
        "StringUtils (ввід)", "GameSession", "PhysicsEngine", "LineSegment", "ClientAccount",
        "CarWithEngine", "Computer", "Hero (бій)", "Order", "Vector3D", "LibraryCatalog"
    };

    public static void main(String[] args) {
        setupConsole();
        Runnable[] demos = {
            Practice07::demoBook, Practice07::demoStudent, Practice07::demoBankAccount, Practice07::demoPoint2D,
            Practice07::demoProduct, Practice07::demoTime, Practice07::demoRectangle, Practice07::demoEmployee,
            Practice07::demoUser, Practice07::demoFraction, Practice07::demoCar, Practice07::demoSmartBulb,
            Practice07::demoQueryBuilder, Practice07::demoCoffee, Practice07::demoBox, Practice07::demoFlightTicket,
            Practice07::demoMathOperations, Practice07::demoDocumentPrinter, Practice07::demoAreaCalculator,
            Practice07::demoLogger, Practice07::demoArraySearcher, Practice07::demoDateService,
            Practice07::demoArrayFilter, Practice07::demoUserAccount, Practice07::demoServerConfig,
            Practice07::demoMathUtils, Practice07::demoCurrency, Practice07::demoFleetCar,
            Practice07::demoStringUtils, Practice07::demoGameSession, Practice07::demoPhysics,
            Practice07::demoLineSegment, Practice07::demoClientAccount, Practice07::demoCarWithEngine,
            Practice07::demoComputer, Practice07::demoHero, Practice07::demoOrder, Practice07::demoVector3D,
            Practice07::demoLibrary
        };

        while (true) {
            System.out.println("\n===== Practice07: оберіть завдання =====");
            for (int i = 0; i < NAMES.length; i++) {
                System.out.printf("%2d. %-26s", i + 1, NAMES[i]);
                if ((i + 1) % 2 == 0) System.out.println();
            }
            System.out.println("\n 0. Вихід");
            System.out.print("Ваш вибір: ");
            if (!in.hasNextLine()) return;
            String line = in.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Введіть число від 0 до " + NAMES.length);
                continue;
            }
            if (choice == 0) {
                System.out.println("До побачення!");
                return;
            }
            if (choice < 1 || choice > NAMES.length) {
                System.out.println("Немає такого пункту.");
                continue;
            }
            System.out.println("\n--- " + NAMES[choice - 1] + " ---");
            demos[choice - 1].run();
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(in.nextLine().trim().replace(',', '.'));
            } catch (Exception e) {
                System.out.println("Некоректне число, спробуйте ще раз.");
            }
        }
    }

    // ---------- Демонстрації ----------
    private static void demoBook() {
        new Book().printInfo();
        new Book("Кобзар", "Тарас Шевченко", 1840, 250.5).printInfo();
        new Book("Тест", "Автор", -5, -10).printInfo();
    }

    private static void demoStudent() {
        new Student("Іван Петренко", 20, 4.8).printStudent();
        new Student("Марія", 10, 3.9).printStudent();
    }

    private static void demoBankAccount() {
        BankAccount acc = new BankAccount("UA001", "Олег", 1000);
        acc.printAccount();
        acc.deposit(500);
        System.out.println("Зняття 300: " + acc.withdraw(300));
        System.out.println("Зняття 5000: " + acc.withdraw(5000));
        acc.printAccount();
    }

    private static void demoPoint2D() {
        Point2D p = new Point2D(3, 4);
        p.printPoint();
        System.out.println("До початку координат: " + p.distanceToOrigin());
        System.out.println("До (0; 0): " + p.distanceTo(0, 0) + ", до (6; 8): " + p.distanceTo(6, 8));
    }

    private static void demoProduct() {
        Product p = new Product("Ноутбук", 30000, 2, "Електроніка");
        p.printProduct();
        p.applyDiscount(10);
        p.printProduct();
    }

    private static void demoTime() {
        Time t = new Time(23, 59, 58);
        System.out.println(t.toUniversalString() + " | " + t.toStandardString());
        t.tick();
        t.tick();
        System.out.println(t.toUniversalString() + " | " + t.toStandardString());
    }

    private static void demoRectangle() {
        Rectangle[] rs = { new Rectangle(), new Rectangle(5), new Rectangle(4, 6) };
        for (Rectangle r : rs)
            System.out.println("Площа: " + r.getArea() + ", периметр: " + r.getPerimeter() + ", квадрат: " + r.isSquare());
    }

    private static void demoEmployee() {
        Employee e = new Employee(1, "Андрій Коваль", "Розробник", 40000);
        e.printBadge();
        e.raiseSalary(10);
        e.printBadge();
        System.out.println("Річна з/п: " + e.getAnnualSalary());
    }

    private static void demoUser() {
        new User("admin", "admin@site.com", "ADMIN", true).printProfile();
        new User("john", "john@mail.com").printProfile();
        new User("kate").printProfile();
        new User().printProfile();
    }

    private static void demoFraction() {
        Fraction f1 = new Fraction(6, 8);
        f1.print();
        System.out.println("Як double: " + f1.toDouble());
        new Fraction(5).print();
        new Fraction().print();
        new Fraction(1, 0).print();
        new Fraction(3, -6).print();
    }

    private static void demoCar() {
        Car c = new Car("Toyota", "Camry", 2020);
        c.drive(100);
        c.startEngine();
        c.drive(150.5);
        System.out.println("Пробіг: " + c.getMileage());
        c.stopEngine();
    }

    private static void demoSmartBulb() {
        new SmartBulb(150, 1000, true).printStatus();
        new SmartBulb(70).printStatus();
        new SmartBulb().printStatus();
    }

    private static void demoQueryBuilder() {
        System.out.println(new QueryBuilder("users").build());
        System.out.println(new QueryBuilder("users").select("name, email").where("age > 18").build());
    }

    private static void demoCoffee() {
        new CoffeeOrder("Капучино", "Large", 2, true).printReceipt();
        new CoffeeOrder("Лате", "Small").printReceipt();
        new CoffeeOrder("Еспресо").printReceipt();
    }

    private static void demoBox() {
        Box b = new Box(2, 3, 4, "Картон");
        b.printBox();
        System.out.println("Площа поверхні: " + b.getSurfaceArea());
        new Box(3, "Дерево").printBox();
        new Box(b).printBox();
    }

    private static void demoFlightTicket() {
        FlightTicket t = new FlightTicket("T123", "Іван Іванов", "PS101", "Київ", "Львів");
        t.printBoardingPass();
        t.assignSeat("12A");
        t.printBoardingPass();
    }

    private static void demoMathOperations() {
        MathOperations m = new MathOperations();
        System.out.println(m.add(2, 3) + " | " + m.add(2.5, 3.5) + " | " + m.add(1, 2, 3) + " | " + m.add("Привіт", "світ"));
        System.out.println(m.multiply(4, 5) + " | " + m.multiply(1.5, 2.0) + " | " + m.multiply("ха", 3));
    }

    private static void demoDocumentPrinter() {
        DocumentPrinter d = new DocumentPrinter();
        d.print("Привіт, світе!");
        d.print(42);
        d.print(3.14159, 2);
        d.print(new String[] { "перший", "другий", "третій" });
        d.print("великі літери", true);
    }

    private static void demoAreaCalculator() {
        AreaCalculator a = new AreaCalculator();
        System.out.println("Квадрат: " + a.calculateArea(4));
        System.out.println("Прямокутник: " + a.calculateArea(3, 5));
        System.out.println("Коло: " + a.calculateArea(2, true));
        System.out.println("Трикутник: " + a.calculateArea(3, 4, 5));
        System.out.println("Трапеція: " + a.calculateArea(3, 5, 4, true));
    }

    private static void demoLogger() {
        Logger l = new Logger();
        l.log("Програму запущено");
        l.log("WARN", "Низький заряд");
        l.log("ERROR", 404, "Не знайдено");
        l.log("DEBUG", "З часовою міткою", true);
        l.log("DEBUG", "Без мітки", false);
    }

    private static void demoArraySearcher() {
        ArraySearcher s = new ArraySearcher();
        int[] arr = { 5, 3, 8, 3, 9 };
        System.out.println("find 3: " + s.find(arr, 3) + ", з індексу 2: " + s.find(arr, 3, 2) + ", find 7: " + s.find(arr, 7));
        String[] names = { "Анна", "Борис", "Віктор" };
        System.out.println("find 'борис': " + s.find(names, "борис") + ", ignoreCase: " + s.find(names, "борис", true));
    }

    private static void demoDateService() {
        DateService d = new DateService(25, 12, 2025);
        d.printDate();
        d.add(10);
        d.printDate();
        d.add(5, 2);
        d.printDate();
        d.add(1, 1, 1);
        d.printDate();
    }

    private static void demoArrayFilter() {
        ArrayFilter f = new ArrayFilter();
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println("> 5: " + Arrays.toString(f.filter(arr, 5)));
        System.out.println("[3; 7]: " + Arrays.toString(f.filter(arr, 3, 7)));
        System.out.println("Парні: " + Arrays.toString(f.filter(arr, true)));
        System.out.println("Непарні: " + Arrays.toString(f.filter(arr, false)));
    }

    private static void demoUserAccount() {
        UserAccount a = new UserAccount("alice", "a@mail.com");
        UserAccount b = new UserAccount("bob", "b@mail.com");
        System.out.println("ID: " + a.getId() + ", " + b.getId() + ", наступний: " + UserAccount.getNextAvailableId());
        UserAccount.resetIdSequence(5000);
        System.out.println("Після скидання: " + new UserAccount("carl", "c@mail.com").getId());
        UserAccount.resetIdSequence(1001);
    }

    private static void demoServerConfig() {
        for (int i = 0; i < 7; i++)
            System.out.println("Підключення " + (i + 1) + ": " + ServerConfig.connect());
        ServerConfig.printServerStatus();
        ServerConfig.disconnect();
        ServerConfig.printServerStatus();
        while (ServerConfig.connect() ) { /* повертаємо стан */ }
        for (int i = 0; i < ServerConfig.MAX_CONNECTIONS; i++) ServerConfig.disconnect();
    }

    private static void demoMathUtils() {
        System.out.println("5! = " + MathUtils.factorial(5));
        System.out.println("2^10 = " + MathUtils.power(2, 10) + ", 2^-2 = " + MathUtils.power(2, -2));
        System.out.println("НСД(48, 18) = " + MathUtils.gcd(48, 18));
        System.out.println("clamp(15, 0, 10) = " + MathUtils.clamp(15, 0, 10));
    }

    private static void demoCurrency() {
        double uah = readDouble("Сума в гривнях: ");
        System.out.printf("%.2f грн = %.2f USD%n", uah, CurrencyExchage.uahToUsd(uah));
        double usd = readDouble("Сума в доларах: ");
        System.out.printf("%.2f USD = %.2f грн%n", usd, CurrencyExchage.usdToUah(usd));
        double eur = readDouble("Сума в євро: ");
        System.out.printf("%.2f EUR = %.2f грн%n", eur, CurrencyExchage.eurToUah(eur));
    }

    private static void demoFleetCar() {
        FleetCar a = new FleetCar("VIN001", 1000);
        FleetCar b = new FleetCar("VIN002", 500);
        a.addTrip(200);
        b.addTrip(-50);
        FleetCar.printFleetSummary();
    }

    private static void demoStringUtils() {
        System.out.print("Введіть рядок: ");
        String s = in.nextLine();
        System.out.println("Паліндром: " + StringUtils.isPalindrome(s));
        System.out.println("Навпаки: " + StringUtils.reverse(s));
        System.out.println("Голосних: " + StringUtils.countVowels(s));
        System.out.print("Введіть номер картки: ");
        System.out.println("Маска: " + StringUtils.maskCardNumber(in.nextLine()));
    }

    private static void demoGameSession() {
        GameSession[] ss = new GameSession[4];
        for (int i = 0; i < ss.length; i++) {
            ss[i] = GameSession.createSession("Гравець" + (i + 1));
            System.out.println("Сесія " + (i + 1) + ": " + (ss[i] != null ? "створено" : "відмовлено"));
        }
        for (GameSession s : ss) if (s != null) s.terminate();
    }

    private static void demoPhysics() {
        System.out.println("Падіння за 3 с: " + PhysicsEngine.freeFallDistance(3) + " м");
        System.out.println("Кінетична енергія (2 кг, 10 м/с): " + PhysicsEngine.kineticEnergy(2, 10) + " Дж");
        System.out.println("Енергія спокою 1 кг: " + PhysicsEngine.restEnergy(1) + " Дж");
    }

    private static void demoLineSegment() {
        LineSegment s = new LineSegment(0, 0, 6, 8);
        s.printSegment();
        System.out.println("Довжина: " + s.getLength());
        Point m = s.getMidPoint();
        System.out.println("Середина: (" + m.getX() + "; " + m.getY() + ")");
        System.out.println("Через Point: " + new LineSegment(new Point(1, 1), new Point(4, 5)).getLength());
    }

    private static void demoClientAccount() {
        ClientAccount a = new ClientAccount("UA01", "Олена", 1000);
        ClientAccount b = new ClientAccount("UA02", "Максим", 200);
        System.out.println("Переказ 300: " + a.transferTo(b, 300));
        System.out.println("Переказ 5000: " + a.transferTo(b, 5000));
        System.out.println("Переказ самому собі: " + a.transferTo(a, 10));
        a.printAccount();
        b.printAccount();
    }

    private static void demoCarWithEngine() {
        CarWithEngine c = new CarWithEngine("BMW", "X5", new Engine("SN-1", 340));
        c.printSpecs();
        c.startCar();
        c.turnOff();
    }

    private static void demoComputer() {
        Computer pc = new Computer("Ігровий ПК", new Processor("Ryzen 7", 4.2, 12000), new Memory(16, 3200, 2500), 3000);
        System.out.println("Ціна: " + pc.calculateTotalPrice());
        pc.upgradeMemory(new Memory(32, 3600, 5000));
        System.out.println("Ціна після апгрейду: " + pc.calculateTotalPrice());
    }

    private static void demoHero() {
        Hero a = new Hero("Лицар", 100, 25, 10);
        Hero b = new Hero("Орк", 120, 20, 5);
        int round = 1;
        while (a.isAlive() && b.isAlive()) {
            System.out.println("Раунд " + round++);
            a.attack(b);
            if (b.isAlive()) b.attack(a);
        }
        System.out.println("Переміг: " + (a.isAlive() ? a.getName() : b.getName()));
    }

    private static void demoOrder() {
        Customer rich = new Customer("Богдан", 50000);
        Order o = new Order(rich, 5);
        o.addProduct(new Product("Телефон", 20000, 1, "Електроніка"));
        o.addProduct(new Product("Навушники", 3000, 1, "Аксесуари"));
        System.out.println("Разом: " + o.calculateTotal());
        o.checkout();
        Order poor = new Order(new Customer("Тарас", 100), 2);
        poor.addProduct(new Product("Планшет", 15000, 1, "Електроніка"));
        poor.checkout();
    }

    private static void demoVector3D() {
        Vector3D a = new Vector3D(1, 0, 0);
        Vector3D b = new Vector3D(0, 1, 0);
        System.out.println("a + b = " + a.add(b));
        System.out.println("a · b = " + a.dotProduct(b));
        System.out.println("a × b = " + a.crossProduct(b));
        System.out.println("Ортогональні: " + a.isOrthogonalTo(b) + ", рівні: " + a.isEqualTo(b));
    }

    private static void demoLibrary() {
        LibraryCatalog lib = new LibraryCatalog(5);
        lib.addBook(new BookItem("111", "Кобзар", "Шевченко"));
        lib.addBook(new BookItem("222", "Лісова пісня", "Леся Українка"));
        lib.printCatalog();
        System.out.println("Взяти 'кобзар': " + lib.borrowBook("кобзар"));
        System.out.println("Взяти 'кобзар' ще раз: " + lib.borrowBook("кобзар"));
        System.out.println("Взяти 'Невідома': " + lib.borrowBook("Невідома"));
        lib.printCatalog();
    }


    // ================= Класи =================

    static class Book {
        private String title;
        private String author;
        private int year;
        private double price;
        public Book() {
            this.title = "Невідомо";
            this.author = "Невідомий";
            this.year = 2000;
            this.price = 0.0;
        }
        public Book(String title, String author, int year, double price) {
            this.title = title;
            this.author = author;
            this.year = (year < 0) ? 2000 : year;
            this.price = (price < 0.0) ? 0.0 : price;
        }
        public void printInfo() {
            System.out.println("Книга: \"" + title + "\", Автор: " + author + ", Рік: " + year + ", Ціна: " + price + " грн");
        }
    }

    static class Student {
        private String name;
        private int age;
        private double gpa;
        public Student(String name, int age, double gpa) {
            this.name = name;
            this.age = (age < 15 || age > 80) ? 18 : age;
            this.gpa = (gpa < 0.0 || gpa > 5.0) ? 0.0 : gpa;
        }
        public boolean isHonorStudent() {
            return this.gpa >= 4.5;
        }
        public void printStudent() {
            String status = isHonorStudent() ? "Відмінник" : "Звичайний студент";
            System.out.println("Студент: " + name + ", Вік: " + age + ", GPA: " + gpa + " [" + status + "]");
        }
    }

    static class BankAccount {
        private String accountNumber;
        private String ownerName;
        private double balance;
        public BankAccount(String accountNumber, String ownerName, double initialBalance) {
            this.accountNumber = accountNumber;
            this.ownerName = ownerName;
            this.balance = (initialBalance < 0) ? 0.0 : initialBalance;
        }
        public void deposit(double amount) {
            if (amount > 0) {
                this.balance += amount;
            }
        }
        public boolean withdraw(double amount) {
            if (amount <= 0 || this.balance < amount) {
                return false;
            }
            this.balance -= amount;
            return true;
        }
        public void printAccount() {
            System.out.println("Рахунок: " + accountNumber + " | Власник: " + ownerName + " | Баланс: " + balance + " грн");
        }
    }

    static class Point2D {
        private double x;
        private double y;
        public Point2D() {
            this.x = 0.0;
            this.y = 0.0;
        }
        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }
        public double distanceToOrigin() {
            return Math.sqrt(x * x + y * y);
        }
        public double distanceTo(double targetX, double targetY) {
            return Math.sqrt(Math.pow(this.x - targetX, 2) + Math.pow(this.y - targetY, 2));
        }
        public void printPoint() {
            System.out.println("Point(" + x + "; " + y + ")");
        }
    }

    static class Product {
        private String name;
        private double price;
        private int quantity;
        private String category;
        public Product(String name, double price, int quantity, String category) {
            this.name = name;
            this.price = (price < 0) ? 0 : price;
            this.quantity = (quantity < 0) ? 0 : quantity;
            this.category = category;
        }
        public double getTotalValue() {
            return this.price * this.quantity;
        }
        public double getPrice() {
            return this.price;
        }

        public void applyDiscount(double percent) {
            if (percent >= 0 && percent <= 100) {
                this.price -= this.price * (percent / 100.0);
            }
        }
        public void printProduct() {
            System.out.println("Товар: " + name + " | Категорія: " + category + " | Ціна: " + price + " | К-сть: " + quantity + " | Загальна вартість: " + getTotalValue());
        }
    }

    static class Time {
        private int hours;
        private int minutes;
        private int seconds;
        public Time(int hours, int minutes, int seconds) {
            this.hours = (hours < 0 || hours > 23) ? 0 : hours;
            this.minutes = (minutes < 0 || minutes > 59) ? 0 : minutes;
            this.seconds = (seconds < 0 || seconds > 59) ? 0 : seconds;
        }
        public void tick() {
            this.seconds++;
            if (this.seconds >= 60) {
                this.seconds = 0;
                this.minutes++;
                if (this.minutes >= 60) {
                    this.minutes = 0;
                    this.hours++;
                    if (this.hours >= 24) {
                        this.hours = 0;
                    }
                }
            }
        }
        public String toUniversalString() {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
        public String toStandardString() {
            int standardHour = (hours == 0 || hours == 12) ? 12 : hours % 12;
            String period = (hours < 12) ? "AM" : "PM";
            return String.format("%d:%02d:%02d %s", standardHour, minutes, seconds, period);
        }
    }

    static class Rectangle {
        private double width;
        private double height;
        public Rectangle() {
            this.width = 1.0;
            this.height = 1.0;
        }
        public Rectangle(double size) {
            this.width = size;
            this.height = size;
        }
        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        public double getArea() {
            return this.width * this.height;
        }
        public double getPerimeter() {
            return 2 * (this.width + this.height);
        }
        public boolean isSquare() {
            return this.width == this.height;
        }
    }

    static class Employee {
        private int id;
        private String fullName;
        private String position;
        private double monthlySalary;
        public Employee(int id, String fullName, String position, double monthlySalary) {
            this.id = id;
            this.fullName = fullName;
            this.position = position;
            this.monthlySalary = monthlySalary;
        }
        public void raiseSalary(double percent) {
            if (percent > 0) {
                this.monthlySalary += this.monthlySalary * (percent / 100.0);
            }
        }
        public double getAnnualSalary() {
            return this.monthlySalary * 12;
        }
        public void printBadge() {
            System.out.println("ID: " + id + " | " + fullName + " | Посада: " + position + " | Місячна з/п: " + monthlySalary);
        }
    }

    static class User {
        private String login;
        private String email;
        private String role;
        private boolean isActive;
        public User(String login, String email, String role, boolean isActive) {
            this.login = login;
            this.email = email;
            this.role = role;
            this.isActive = isActive;
        }
        public User(String login, String email) {
            this(login, email, "USER", true);
        }
        public User(String login) {
            this(login, login + "@domain.com");
        }
        public User() {
            this("guest");
        }
        public void printProfile() {
            System.out.println("User: " + login + " | Email: " + email + " | Role: " + role + " | Active: " + isActive);
        }
    }

    static class Fraction {
        private int numerator;
        private int denominator;
        public Fraction(int numerator, int denominator) {
            if (denominator == 0) {
                System.out.println("Помилка: знаменник не може бути 0. Встановлено 1.");
                denominator = 1;
            }
            if (denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }
            int gcdVal = gcd(Math.abs(numerator), Math.abs(denominator));
            this.numerator = numerator / gcdVal;
            this.denominator = denominator / gcdVal;
        }
        public Fraction(int wholeNumber) {
            this(wholeNumber, 1);
        }
        public Fraction() {
            this(0, 1);
        }
        private int gcd(int a, int b) {
            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }
            return a;
        }
        public void print() {
            if (denominator == 1) {
                System.out.println(numerator);
            } else {
                System.out.println(numerator + "/" + denominator);
            }
        }
        public double toDouble() {
            return (double) numerator / denominator;
        }
    }

    static class Car {
        private String brand;
        private String model;
        private int year;
        private double mileage;
        private boolean engineStarted;
        public Car(String brand, String model, int year, double mileage) {
            this.brand = brand;
            this.model = model;
            this.year = year;
            this.mileage = mileage;
            this.engineStarted = false;
        }
        public Car(String brand, String model, int year) {
            this(brand, model, year, 0.0);
        }
        public Car() {
            this("Unknown", "Standard", 2024);
        }
        public void startEngine() {
            this.engineStarted = true;
        }
        public void stopEngine() {
            this.engineStarted = false;
        }
        public void drive(double distance) {
            if (!engineStarted) {
                System.out.println("Спершу заведіть двигун!");
                return;
            }
            if (distance > 0) {
                this.mileage += distance;
            }
        }
        public double getMileage() {
            return this.mileage;
        }
    }

    static class SmartBulb {
        private int brightness;
        private int colorTemp;
        private boolean isOn;
        public SmartBulb(int brightness, int colorTemp, boolean isOn) {
            this.brightness = (brightness < 0) ? 0 : (brightness > 100) ? 100 : brightness;
            this.colorTemp = (colorTemp < 2700) ? 2700 : (colorTemp > 6500) ? 6500 : colorTemp;
            this.isOn = isOn;
        }
        public SmartBulb(int brightness) {
            this(brightness, 4000, true);
        }
        public SmartBulb() {
            this(50);
        }
        public void printStatus() {
            System.out.println("Лампочка: " + (isOn ? "Увімкнена" : "Вимкнена") + " | Яскравість: " + brightness + "% | Колір: " + colorTemp + "K");
        }
    }

    static class QueryBuilder {
        private String table;
        private String fields = "*";
        private String whereClause = "";
        public QueryBuilder(String table) {
            this.table = table;
        }
        public QueryBuilder select(String fields) {
            this.fields = fields;
            return this;
        }
        public QueryBuilder where(String condition) {
            this.whereClause = condition;
            return this;
        }
        public String build() {
            return "SELECT " + fields + " FROM " + table + (whereClause.isEmpty() ? "" : " WHERE " + whereClause) + ";";
        }
    }

    static class CoffeeOrder {
        private String type;
        private String size;
        private int sugarCount;
        private boolean withSyrup;
        private double finalPrice;
        public CoffeeOrder(String type, String size, int sugarCount, boolean withSyrup) {
            this.type = type;
            this.size = size;
            this.sugarCount = sugarCount;
            this.withSyrup = withSyrup;
            this.finalPrice = calculatePrice();
        }
        public CoffeeOrder(String type, String size) {
            this(type, size, 0, false);
        }
        public CoffeeOrder(String type) {
            this(type, "Medium");
        }
        private double calculatePrice() {
            double base = 0;
            if (type.equalsIgnoreCase("Еспресо")) base = 40;
            else if (type.equalsIgnoreCase("Капучино")) base = 55;
            else if (type.equalsIgnoreCase("Лате")) base = 60;
            else base = 45;
            double coeff = 1.0;
            if (size.equalsIgnoreCase("Small")) coeff = 1.0;
            else if (size.equalsIgnoreCase("Medium")) coeff = 1.3;
            else if (size.equalsIgnoreCase("Large")) coeff = 1.6;
            double price = base * coeff;
            if (withSyrup) price += 15;
            return price;
        }
        public void printReceipt() {
            System.out.println("Кава: " + type + " (" + size + ") | Цукор: " + sugarCount + " | Сироп: " + withSyrup + " | Ціна: " + finalPrice + " грн");
        }
    }

    static class Box {
        private double width;
        private double height;
        private double depth;
        private String material;
        public Box(double width, double height, double depth, String material) {
            this.width = width;
            this.height = height;
            this.depth = depth;
            this.material = material;
        }
        public Box(double size, String material) {
            this(size, size, size, material);
        }
        public Box(Box other) {
            this(other.width, other.height, other.depth, other.material);
        }
        public double getVolume() {
            return width * height * depth;
        }
        public double getSurfaceArea() {
            return 2 * (width * height + width * depth + height * depth);
        }
        public void printBox() {
            System.out.println("Коробка [" + material + "]: " + width + "x" + height + "x" + depth + " | Об'єм: " + getVolume());
        }
    }

    static class FlightTicket {
        private String ticketNumber;
        private String passengerName;
        private String flightNumber;
        private String departureCity;
        private String arrivalCity;
        private String seatNumber;
        private boolean hasBaggage;
        public FlightTicket(String ticketNumber, String passengerName, String flightNumber, String departureCity, String arrivalCity, String seatNumber, boolean hasBaggage) {
            this.ticketNumber = ticketNumber;
            this.passengerName = passengerName;
            this.flightNumber = flightNumber;
            this.departureCity = departureCity;
            this.arrivalCity = arrivalCity;
            this.seatNumber = seatNumber;
            this.hasBaggage = hasBaggage;
        }
        public FlightTicket(String ticketNumber, String passengerName, String flightNumber, String departureCity, String arrivalCity) {
            this(ticketNumber, passengerName, flightNumber, departureCity, arrivalCity, "UNASSIGNED", false);
        }
        public void assignSeat(String seat) {
            this.seatNumber = seat;
        }
        public void printBoardingPass() {
            System.out.println("=== ПОСАДКОВИЙ ТАЛОН ===");
            System.out.println("Квиток: " + ticketNumber + " | Рейс: " + flightNumber);
            System.out.println("Пасажир: " + passengerName);
            System.out.println("Маршрут: " + departureCity + " -> " + arrivalCity);
            System.out.println("Місце: " + seatNumber + " | Багаж: " + hasBaggage);
        }
    }

    static class MathOperations {
        public int add(int a, int b) { return a + b; }
        public double add(double a, double b) { return a + b; }
        public int add(int a, int b, int c) { return a + b + c; }
        public String add(String a, String b) { return a + " " + b; }
        public int multiply(int a, int b) { return a * b; }
        public double multiply(double a, double b) { return a * b; }
        public String multiply(String text, int times) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < times; i++) {
                sb.append(text);
            }
            return sb.toString();
        }
    }

    static class DocumentPrinter {
        public void print(String message) {
            System.out.println(message);
        }
        public void print(int number) {
            System.out.println("[INT]: " + number);
        }
        public void print(double number, int decimals) {
            System.out.println(String.format("[DOUBLE]: %." + decimals + "f", number));
        }
        public void print(String[] lines) {
            for (int i = 0; i < lines.length; i++) {
                System.out.println((i + 1) + ": " + lines[i]);
            }
        }
        public void print(String message, boolean uppercase) {
            System.out.println(uppercase ? message.toUpperCase() : message);
        }
    }

    static class AreaCalculator {
        public double calculateArea(double side) {
            return side * side;
        }
        public double calculateArea(double width, double height) {
            return width * height;
        }
        public double calculateArea(double radius, boolean isCircle) {
            return Math.PI * radius * radius;
        }
        public double calculateArea(double a, double b, double c) {
            double p = (a + b + c) / 2.0;
            return Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }
        public double calculateArea(double base1, double base2, double height, boolean isTrapezoid) {
            return ((base1 + base2) / 2.0) * height;
        }
    }

    static class Logger {
        public void log(String message) {
            System.out.println("[INFO] " + message);
        }
        public void log(String level, String message) {
            System.out.println("[" + level + "] " + message);
        }
        public void log(String level, int errorCode, String message) {
            System.out.println("[" + level + "] (Code: " + errorCode + "): " + message);
        }
        public void log(String tag, String message, boolean timestamp) {
            if (timestamp) {
                System.out.println("[" + tag + "] (" + System.currentTimeMillis() + "): " + message);
            } else {
                System.out.println("[" + tag + "]: " + message);
            }
        }
    }

    static class ArraySearcher {
        public int find(int[] arr, int target) {
            return find(arr, target, 0);
        }
        public int find(int[] arr, int target, int startIndex) {
            for (int i = startIndex; i < arr.length; i++) {
                if (arr[i] == target) return i;
            }
            return -1;
        }
        public int find(String[] arr, String target) {
            return find(arr, target, false);
        }
        public int find(String[] arr, String target, boolean ignoreCase) {
            for (int i = 0; i < arr.length; i++) {
                if (ignoreCase ? arr[i].equalsIgnoreCase(target) : arr[i].equals(target)) {
                    return i;
                }
            }
            return -1;
        }
    }

    static class DateService {
        private int day;
        private int month;
        private int year;
        public DateService(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }
        public void add(int days) {
            this.day += days;
            while (this.day > 30) {
                this.day -= 30;
                this.month++;
                if (this.month > 12) {
                    this.month = 1;
                    this.year++;
                }
            }
        }
        public void add(int days, int months) {
            add(days);
            this.month += months;
            while (this.month > 12) {
                this.month -= 12;
                this.year++;
            }
        }
        public void add(int days, int months, int years) {
            add(days, months);
            this.year += years;
        }
        public void printDate() {
            System.out.format("%02d.%02d.%4d\n", day, month, year);
        }
    }

    static class ArrayFilter {
        public int[] filter(int[] arr, int threshold) {
            int count = 0;
            for (int x : arr) if (x > threshold) count++;
            int[] res = new int[count];
            int idx = 0;
            for (int x : arr) if (x > threshold) res[idx++] = x;
            return res;
        }
        public int[] filter(int[] arr, int min, int max) {
            int count = 0;
            for (int x : arr) if (x >= min && x <= max) count++;
            int[] res = new int[count];
            int idx = 0;
            for (int x : arr) if (x >= min && x <= max) res[idx++] = x;
            return res;
        }
        public int[] filter(int[] arr, boolean keepEvens) {
            int count = 0;
            for (int x : arr) if ((x % 2 == 0) == keepEvens) count++;
            int[] res = new int[count];
            int idx = 0;
            for (int x : arr) if ((x % 2 == 0) == keepEvens) res[idx++] = x;
            return res;
        }
    }

    static class UserAccount {
        private int id;
        private String username;
        private String email;
        private static int nextId = 1001;
        public UserAccount(String username, String email) {
            this.username = username;
            this.email = email;
            this.id = nextId++;
        }
        public static int getNextAvailableId() {
            return nextId;
        }
        public static void resetIdSequence(int startId) {
            nextId = startId;
        }
        public int getId() { return this.id; }
    }

    static class ServerConfig {
        public static final String SERVER_NAME = "AlphaEnterprise";
        public static final int MAX_CONNECTIONS = 5;
        private static int activeConnections = 0;
        public static boolean connect() {
            if (activeConnections < MAX_CONNECTIONS) {
                activeConnections++;
                return true;
            }
            return false;
        }
        public static void disconnect() {
            if (activeConnections > 0) {
                activeConnections--;
            }
        }
        public static void printServerStatus() {
            System.out.println("Сервер: " + SERVER_NAME + " | Активні сесії: " + activeConnections + " | Вільні слоти: " + (MAX_CONNECTIONS - activeConnections));
        }
    }

    static class MathUtils {
        public static final double PI = 3.1415926535;
        public static final double E = 2.7182818284;
        private MathUtils() {}
        public static long factorial(int n) {
            long res = 1;
            for (int i = 1; i <= n; i++) res *= i;
            return res;
        }
        public static double power(double base, int exp) {
            if (exp == 0) return 1;
            if (exp < 0) return 1.0 / power(base, -exp);
            double res = 1;
            for (int i = 0; i < exp; i++) res *= base;
            return res;
        }
        public static int gcd(int a, int b) {
            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }
            return Math.abs(a);
        }
        public static int clamp(int val, int min, int max) {
            return (val < min) ? min : (val > max) ? max : val;
        }
    }

    static class CurrencyExchage {
        private static double usdToUahRate = 41.5;
        private static double eurToUahRate = 45.2;
        public static void updateRaters(double newUsd, double newEur) {
            if (newUsd > 0) usdToUahRate = newUsd;
            if (newEur > 0) eurToUahRate = newEur;
        }
        public static double uahToUsd(double uah) { return uah / usdToUahRate; }
        public static double usdToUah(double usd) { return usd * usdToUahRate; }
        public static double eurToUah(double eur) { return eur * eurToUahRate; }
    }

    static class FleetCar {
        private String vinCode;
        private double currentMileage;
        private static int totalCars = 0;
        private static double totalCompanyMileage = 0.0;
        public FleetCar(String vinCode, double initialMileage) {
            this.vinCode = vinCode;
            this.currentMileage = initialMileage;
            totalCars++;
            totalCompanyMileage += initialMileage;
        }
        public void addTrip(double km) {
            if (km > 0) {
                this.currentMileage += km;
                totalCompanyMileage += km;
            }
        }
        public static void printFleetSummary() {
            System.out.println("Автопарк: " + totalCars + " авто | Сумарний компанійський пробіг: " + totalCompanyMileage + " км");
        }
    }

    static class StringUtils {
        private StringUtils() {}
        public static boolean isPalindrome(String s) {
            String clean = s.replaceAll("\\s+", "").toLowerCase();
            int n = clean.length();
            for (int i = 0; i < n / 2; i++) {
                if (clean.charAt(i) != clean.charAt(n - 1 - i)) return false;
            }
            return true;
        }
        public static String reverse(String s) {
            return new StringBuilder(s).reverse().toString();
        }
        public static int countVowels(String s) {
            int count = 0;
            String vowels = "aeiouyаеєиіоуюяёыэ";
            for (char c : s.toLowerCase().toCharArray()) {
                if (vowels.indexOf(c) != -1) count++;
            }
            return count;
        }
        public static String maskCardNumber(String cardNumber) {
            String clean = cardNumber.replaceAll("\\s+", "");
            if (clean.length() < 4) return cardNumber;
            String last4 = clean.substring(clean.length() - 4);
            return "**** **** **** " + last4;
        }
    }

    static class GameSession {
        private String sessionToken;
        private String hostPlayer;
        private boolean isActive;
        private static int activeSessionsCount = 0;
        public static final int MAX_SESSIONS = 3;
        private GameSession(String host) {
            this.hostPlayer = host;
            this.sessionToken = "TOKEN-" + Math.random();
            this.isActive = true;
        }
        public static GameSession createSession(String host) {
            if (activeSessionsCount >= MAX_SESSIONS) {
                System.out.println("Помилка: усі сервера перевантажені!");
                return null;
            }
            activeSessionsCount++;
            return new GameSession(host);
        }
        public void terminate() {
            if (this.isActive) {
                this.isActive = false;
                activeSessionsCount--;
            }
        }
    }

    static class PhysicsEngine {
        public static final double G = 9.80665;
        public static final double SPEED_OF_LIGHT = 299792458.0;
        private PhysicsEngine() {}
        public static double freeFallDistance(double seconds) {
            return (G * seconds * seconds) / 2.0;
        }
        public static double kineticEnergy(double massKg, double velocityMs) {
            return (massKg * velocityMs * velocityMs) / 2.0;
        }
        public static double restEnergy(double massKg) {
            return massKg * SPEED_OF_LIGHT * SPEED_OF_LIGHT;
        }
    }

    static class Point {
        private double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
        public double getX() { return x; }
        public double getY() { return y; }
    }

    static class LineSegment {
        private Point start;
        private Point end;
        public LineSegment(Point start, Point end) {
            this.start = start;
            this.end = end;
        }
        public LineSegment(double x1, double y1, double x2, double y2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        }
        public double getLength() {
            return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
        }
        public Point getMidPoint() {
            return new Point((start.getX() + end.getX()) / 2.0, (start.getY() + end.getY()) / 2.0);
        }
        public void printSegment() {
            System.out.println("Відрізок: (" + start.getX() + ";" + start.getY() + ") -> (" + end.getX() + ";" + end.getY() + ")");
        }
    }

    static class ClientAccount {
        private String iban;
        private String owner;
        private double balance;
        public ClientAccount(String iban, String owner, double balance) {
            this.iban = iban;
            this.owner = owner;
            this.balance = balance;
        }
        public boolean transferTo(ClientAccount recipient, double amount) {
            if (recipient == null || recipient == this) return false;
            if (amount <= 0 || this.balance < amount) return false;
            this.balance -= amount;
            recipient.balance += amount;
            return true;
        }
        public void printAccount() {
            System.out.println("Клієнт: " + owner + " | Баланс: " + balance + " грн");
        }
    }

    static class Engine {
        private String serialNumber;
        private int horsePower;
        private boolean isRunning;
        public Engine(String serialNumber, int horsePower) {
            this.serialNumber = serialNumber;
            this.horsePower = horsePower;
            this.isRunning = false;
        }
        public void start() { this.isRunning = true; }
        public void stop() { this.isRunning = false; }
        public boolean isRunning() { return isRunning; }
        public int getHorsePower() { return horsePower; }
    }

    static class CarWithEngine {
        private String brand;
        private String model;
        private Engine engine;
        public CarWithEngine(String brand, String model, Engine engine) {
            this.brand = brand;
            this.model = model;
            this.engine = engine;
        }
        public void startCar() {
            this.engine.start();
            System.out.println(brand + " " + model + " готовий до руху!");
        }
        public void turnOff() {
            this.engine.stop();
        }
        public void printSpecs() {
            System.out.println("Авто: " + brand + " | Потужність двигуна: " + engine.getHorsePower() + " к.с.");
        }
    }

    static class Processor {
        private String model;
        private double frequencyGHz;
        private double price;
        public Processor(String model, double frequencyGHz, double price) {
            this.model = model;
            this.frequencyGHz = frequencyGHz;
            this.price = price;
        }
        public double getPrice() { return price; }
    }

    static class Memory {
        private int sizeGB;
        private int frequencyMHz;
        private double price;
        public Memory(int sizeGB, int frequencyMHz, double price) {
            this.sizeGB = sizeGB;
            this.frequencyMHz = frequencyMHz;
            this.price = price;
        }
        public double getPrice() { return price; }
    }

    static class Computer {
        private String title;
        private Processor cpu;
        private Memory ram;
        private double casePrice;
        public Computer(String title, Processor cpu, Memory ram, double casePrice) {
            this.title = title;
            this.cpu = cpu;
            this.ram = ram;
            this.casePrice = casePrice;
        }
        public double calculateTotalPrice() {
            return cpu.getPrice() + ram.getPrice() + casePrice;
        }
        public void upgradeMemory(Memory newRam) {
            this.ram = newRam;
            System.out.println("Оперативну пам'ять успішно оновлено!");
        }
    }

    static class Hero {
        private String name;
        private int health;
        private int attackPower;
        private int defense;
        public Hero(String name, int health, int attackPower, int defense) {
            this.name = name;
            this.health = health;
            this.attackPower = attackPower;
            this.defense = defense;
        }
        public void attack(Hero target) {
            int damage = this.attackPower - target.defense;
            if (damage <= 0) damage = 1;
            target.health -= damage;
            if (target.health < 0) target.health = 0;
            System.out.println(this.name + " наносить " + damage + " шкоди " + target.name + ". Залишилось HP: " + target.health);
        }
        public boolean isAlive() {
            return this.health > 0;
        }
        public String getName() { return name; }
    }

    static class Customer {
        private String name;
        private double walletBalance;
        public Customer(String name, double walletBalance) {
            this.name = name;
            this.walletBalance = walletBalance;
        }
        public boolean pay(double amount) {
            if (walletBalance >= amount) {
                walletBalance -= amount;
                return true;
            }
            return false;
        }
    }

    static class Order {
        private Customer customer;
        private Product[] items;
        private int itemCount;
        public Order(Customer customer, int maxItems) {
            this.customer = customer;
            this.items = new Product[maxItems];
            this.itemCount = 0;
        }
        public void addProduct(Product p) {
            if (itemCount < items.length) {
                items[itemCount++] = p;
            }
        }
        public double calculateTotal() {
            double total = 0;
            for (int i = 0; i < itemCount; i++) {
                total += items[i].getPrice();
            }
            return total;
        }
        public boolean checkout() {
            double total = calculateTotal();
            if (customer.pay(total)) {
                System.out.println("Чек успішно оплачено на суму: " + total + " грн.");
                return true;
            }
            System.out.println("Недостатньо коштів на балансі клієнта!");
            return false;
        }
    }

    static class Vector3D {
        private double x, y, z;
        public Vector3D(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        public Vector3D() {
            this(0, 0, 0);
        }
        public Vector3D add(Vector3D other) {
            return new Vector3D(this.x + other.x, this.y + other.y, this.z + other.z);
        }
        public double dotProduct(Vector3D other) {
            return this.x * other.x + this.y * other.y + this.z * other.z;
        }
        public Vector3D crossProduct(Vector3D other) {
            return new Vector3D(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
            );
        }
        public String toString() {
            return "(" + x + "; " + y + "; " + z + ")";
        }

        public boolean isOrthogonalTo(Vector3D other) {
            return dotProduct(other) == 0;
        }
        public boolean isEqualTo(Vector3D other) {
            return this.x == other.x && this.y == other.y && this.z == other.z;
        }
    }

    static class BookItem {
        private String isbn;
        private String title;
        private String author;
        private boolean isAvailable;
        public BookItem(String isbn, String title, String author) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.isAvailable = true;
        }
        public boolean borrowBook() {
            if (isAvailable) {
                isAvailable = false;
                return true;
            }
            return false;
        }
        public void returnBook() { this.isAvailable = true; }
        public String getTitle() { return title; }
        public boolean isAvailable() { return isAvailable; }
        public void printBook() {
            System.out.println("Книга: " + title + " | Доступна: " + isAvailable);
        }
    }

    static class LibraryCatalog {
        private BookItem[] books;
        private int size;
        public LibraryCatalog(int capacity) {
            this.books = new BookItem[capacity];
            this.size = 0;
        }
        public void addBook(BookItem book) {
            if (size < books.length) {
                books[size++] = book;
            }
        }
        public BookItem findBookByTitle(String title) {
            for (int i = 0; i < size; i++) {
                if (books[i].getTitle().equalsIgnoreCase(title)) {
                    return books[i];
                }
            }
            return null;
        }
        public boolean borrowBook(String title) {
            BookItem book = findBookByTitle(title);
            if (book != null && book.isAvailable()) {
                return book.borrowBook();
            }
            return false;
        }
        public void printCatalog() {
            for (int i = 0; i < size; i++) {
                books[i].printBook();
            }
        }
    }
}
