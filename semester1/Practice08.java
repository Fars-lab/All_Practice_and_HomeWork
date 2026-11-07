package semester1.practice01;

import java.util.Arrays;

public class Practice08 {

    // ======================= БЛОК 1: Інкапсуляція та toString() (1–8) =======================

    static class Student {
        private String name;
        private int course;
        private double gpa;

        public Student(String name, int course, double gpa) {
            this.name = name;
            setCourse(course);
            setGpa(gpa);
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getCourse() { return course; }
        public void setCourse(int course) { this.course = (course >= 1 && course <= 6) ? course : 1; }
        public double getGpa() { return gpa; }
        public void setGpa(double gpa) { this.gpa = (gpa >= 0.0 && gpa <= 100.0) ? gpa : 60.0; }

        @Override
        public String toString() {
            return "Student{name='" + name + "', course=" + course + ", gpa=" + gpa + "}";
        }
    }

    static class Product {
        private String sku;
        private String title;
        private double price;
        private int stock;

        public Product(String sku, String title, double price, int stock) {
            this.sku = sku;
            this.title = title;
            setPrice(price);
            setStock(stock);
        }

        public String getSku() { return sku; }
        public String getTitle() { return title; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price >= 0.0 ? price : 0.0; }
        public int getStock() { return stock; }
        public void setStock(int stock) { this.stock = stock >= 0 ? stock : 0; }

        public double getTotalCost() { return price * stock; }

        @Override
        public String toString() {
            return String.format("[SKU: %s] %s | Ціна: %.2f грн | Залишок: %d шт. | Разом: %.2f грн",
                    sku, title, price, stock, getTotalCost());
        }
    }

    static class Circle {
        private double radius;

        public Circle(double radius) { setRadius(radius); }

        public double getRadius() { return radius; }
        public void setRadius(double radius) { this.radius = radius > 0 ? radius : 1.0; }
        public double getArea() { return Math.PI * radius * radius; }
        public double getCircumference() { return 2 * Math.PI * radius; }

        @Override
        public String toString() {
            return String.format("Circle{r=%.2f, area=%.2f, circumference=%.2f}", radius, getArea(), getCircumference());
        }
    }

    static class CardWallet {
        private final String cardNumber;
        private String pinCode;
        private double balance;

        public CardWallet(String cardNumber, String pinCode, double balance) {
            this.cardNumber = cardNumber;
            this.pinCode = pinCode;
            this.balance = Math.max(0.0, balance);
        }

        public double getBalance() { return balance; }

        public boolean changePin(String oldPin, String newPin) {
            if (pinCode.equals(oldPin) && newPin != null && newPin.matches("\\d{4}")) {
                pinCode = newPin;
                return true;
            }
            return false;
        }

        private String maskedNumber() {
            String last4 = cardNumber.substring(cardNumber.length() - 4);
            return "**** **** **** " + last4;
        }

        @Override
        public String toString() {
            return String.format("CardWallet{card='%s', balance=%.2f UAH}", maskedNumber(), balance);
        }
    }

    static class SensorNode {
        private final String sensorId;
        protected double calibrationOffset;
        private double rawValue;

        public SensorNode(String sensorId, double rawValue, double calibrationOffset) {
            this.sensorId = sensorId;
            this.rawValue = rawValue;
            this.calibrationOffset = calibrationOffset;
        }

        public String getSensorId() { return sensorId; }
        public double getCalibratedValue() { return rawValue + calibrationOffset; }

        @Override
        public String toString() {
            return String.format("SensorNode{id='%s', raw=%.2f, calibrated=%.2f}", sensorId, rawValue, getCalibratedValue());
        }
    }

    static class WeatherDay {
        private final String date;
        private final double[] hourlyTemps;

        public WeatherDay(String date, double[] hourlyTemps) {
            this.date = date;
            this.hourlyTemps = new double[hourlyTemps.length];
            for (int i = 0; i < hourlyTemps.length; i++) {
                this.hourlyTemps[i] = hourlyTemps[i];
            }
        }

        public double getMinTemp() {
            double m = hourlyTemps[0];
            for (double t : hourlyTemps) m = Math.min(m, t);
            return m;
        }

        public double getMaxTemp() {
            double m = hourlyTemps[0];
            for (double t : hourlyTemps) m = Math.max(m, t);
            return m;
        }

        public double getAverageTemp() {
            double s = 0;
            for (double t : hourlyTemps) s += t;
            return s / hourlyTemps.length;
        }

        @Override
        public String toString() {
            return String.format("WeatherDay{date=%s, temps=%s, avg=%.2f}", date, Arrays.toString(hourlyTemps), getAverageTemp());
        }
    }

    static class IntRange {
        private int min;
        private int max;

        public IntRange(int min, int max) { set(min, max); }

        public void set(int min, int max) {
            if (min > max) {
                int t = min; min = max; max = t;
            }
            this.min = min;
            this.max = max;
        }

        public boolean contains(int value) { return value >= min && value <= max; }
        public int getLength() { return max - min; }

        @Override
        public String toString() { return "[" + min + " .. " + max + "] (довжина: " + getLength() + ")"; }
    }

    static class InvoiceItem {
        private String description;
        private int quantity;
        private double unitPrice;
        private double discountPercent;

        public InvoiceItem(String description, int quantity, double unitPrice, double discountPercent) {
            this.description = description;
            this.quantity = Math.max(1, quantity);
            this.unitPrice = Math.max(0.0, unitPrice);
            this.discountPercent = Math.min(50.0, Math.max(0.0, discountPercent));
        }

        public double getFinalAmount() {
            return quantity * unitPrice * (1 - discountPercent / 100.0);
        }

        @Override
        public String toString() {
            return String.format("%s x%d по %.2f грн (знижка %.0f%%) = %.2f грн",
                    description, quantity, unitPrice, discountPercent, getFinalAmount());
        }
    }

    // ======================= БЛОК 2: Однорівневе наслідування (9–16) =======================

    static class Employee {
        private String name;
        protected double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = Math.max(0, salary);
        }

        public String getName() { return name; }

        @Override
        public String toString() { return "Employee{name='" + name + "', salary=" + salary + "}"; }
    }

    static class Developer extends Employee {
        private String programmingLanguage;

        public Developer(String name, double salary, String programmingLanguage) {
            super(name, salary);
            this.programmingLanguage = programmingLanguage;
        }

        @Override
        public String toString() {
            return String.format("Developer{%s, language='%s'}", super.toString(), programmingLanguage);
        }
    }

    static class Animal {
        private String name;
        private int age;
        private double weight;

        public Animal(String name, int age, double weight) {
            this.name = name;
            this.age = Math.max(0, age);
            this.weight = Math.max(0.0, weight);
        }

        public String getName() { return name; }

        @Override
        public String toString() { return "Animal{name='" + name + "', age=" + age + ", weight=" + weight + "}"; }
    }

    static class Dog extends Animal {
        private String breed;

        public Dog(String name, int age, double weight, String breed) {
            super(name, age, weight);
            this.breed = breed;
        }

        public String fetchStick() { return getName() + " приносить палицю!"; }

        @Override
        public String toString() { return "Dog{" + super.toString() + ", breed='" + breed + "'}"; }
    }

    static class Cat extends Animal {
        private boolean isIndoor;

        public Cat(String name, int age, double weight, boolean isIndoor) {
            super(name, age, weight);
            this.isIndoor = isIndoor;
        }

        public String purr() { return getName() + ": мур-мур"; }

        @Override
        public String toString() { return "Cat{" + super.toString() + ", indoor=" + isIndoor + "}"; }
    }

    static class Vehicle {
        protected String brand;
        protected int maxSpeed;
        protected double fuelTankCapacity;

        public Vehicle(String brand, int maxSpeed, double fuelTankCapacity) {
            this.brand = brand;
            this.maxSpeed = maxSpeed;
            this.fuelTankCapacity = fuelTankCapacity;
        }

        @Override
        public String toString() {
            return "Vehicle{brand='" + brand + "', maxSpeed=" + maxSpeed + ", tank=" + fuelTankCapacity + " л}";
        }
    }

    static class PassengerCar extends Vehicle {
        private int passengerSeats;

        public PassengerCar(String brand, int maxSpeed, double tank, int passengerSeats) {
            super(brand, maxSpeed, tank);
            this.passengerSeats = passengerSeats;
        }

        @Override
        public String toString() { return "PassengerCar{" + super.toString() + ", seats=" + passengerSeats + "}"; }
    }

    static class CargoTruck extends Vehicle {
        private double maxLoadTons;

        public CargoTruck(String brand, int maxSpeed, double tank, double maxLoadTons) {
            super(brand, maxSpeed, tank);
            this.maxLoadTons = maxLoadTons;
        }

        /** Витрата пального на 100 км при повному завантаженні: базова 20 л + 2.5 л на кожну тонну. */
        public double fuelConsumptionPer100kmFullLoad() { return 20.0 + 2.5 * maxLoadTons; }

        @Override
        public String toString() {
            return String.format("CargoTruck{%s, maxLoad=%.1f т, витрата=%.1f л/100км}",
                    super.toString(), maxLoadTons, fuelConsumptionPer100kmFullLoad());
        }
    }

    static class Rectangle {
        private double width;
        private double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        public double getWidth() { return width; }
        public double getHeight() { return height; }
        public double getArea() { return width * height; }

        @Override
        public String toString() { return "Rectangle{" + width + " x " + height + ", area=" + getArea() + "}"; }
    }

    static class Box3D extends Rectangle {
        private double depth;

        public Box3D(double width, double height, double depth) {
            super(width, height);
            this.depth = depth;
        }

        public double getVolume() { return super.getArea() * depth; }

        @Override
        public String toString() { return "Box3D{" + super.toString() + ", depth=" + depth + ", volume=" + getVolume() + "}"; }
    }

    static class Person {
        private String fullName;
        private int birthYear;

        public Person(String fullName, int birthYear) {
            this.fullName = fullName;
            this.birthYear = birthYear;
        }

        public String getFullName() { return fullName; }
        public int getAge(int currentYear) { return currentYear - birthYear; }

        @Override
        public String toString() { return "Person{" + fullName + ", " + birthYear + "}"; }
    }

    static class UniversityStudent extends Person {
        private String studentId;
        private String major;
        private int scholarship;

        public UniversityStudent(String fullName, int birthYear, String studentId, String major, int scholarship) {
            super(fullName, birthYear);
            this.studentId = studentId;
            this.major = major;
            this.scholarship = scholarship;
        }

        @Override
        public String toString() {
            return "UniversityStudent{" + super.toString() + ", id=" + studentId + ", major=" + major + ", scholarship=" + scholarship + "}";
        }
    }

    static class Professor extends Person {
        private String academicTitle;
        private int publicationsCount;

        public Professor(String fullName, int birthYear, String academicTitle, int publicationsCount) {
            super(fullName, birthYear);
            this.academicTitle = academicTitle;
            this.publicationsCount = publicationsCount;
        }

        @Override
        public String toString() {
            return "Professor{" + super.toString() + ", title=" + academicTitle + ", publications=" + publicationsCount + "}";
        }
    }

    static class Gadget {
        private String brand;
        private double price;

        public Gadget(String brand, double price) {
            this.brand = brand;
            this.price = price;
        }

        @Override
        public String toString() { return "Gadget{brand='" + brand + "', price=" + price + "}"; }
    }

    static class Smartphone extends Gadget {
        private int memoryGb;
        private boolean has5G;

        public Smartphone(String brand, double price, int memoryGb, boolean has5G) {
            super(brand, price);
            this.memoryGb = memoryGb;
            this.has5G = has5G;
        }

        public Smartphone(String brand, double price, int memoryGb) {
            this(brand, price, memoryGb, true);
        }

        public Smartphone(String brand) {
            this(brand, 9999.0, 128, false);
        }

        @Override
        public String toString() { return "Smartphone{" + super.toString() + ", memory=" + memoryGb + "GB, 5G=" + has5G + "}"; }
    }

    static class Publication {
        private String title;
        private String author;
        private int releaseYear;

        public Publication(String title, String author, int releaseYear) {
            this.title = title;
            this.author = author;
            this.releaseYear = releaseYear;
        }

        @Override
        public String toString() { return "'" + title + "' — " + author + " (" + releaseYear + ")"; }
    }

    static class PrintedBook extends Publication {
        private int pageCount;
        private String coverType;

        public PrintedBook(String title, String author, int year, int pageCount, String coverType) {
            super(title, author, year);
            this.pageCount = pageCount;
            this.coverType = coverType;
        }

        @Override
        public String toString() { return "PrintedBook{" + super.toString() + ", " + pageCount + " стор., " + coverType + "}"; }
    }

    static class AudioBook extends Publication {
        private int durationMinutes;
        private String narrator;

        public AudioBook(String title, String author, int year, int durationMinutes, String narrator) {
            super(title, author, year);
            this.durationMinutes = durationMinutes;
            this.narrator = narrator;
        }

        public String formatDuration() { return (durationMinutes / 60) + " год " + (durationMinutes % 60) + " хв"; }

        @Override
        public String toString() { return "AudioBook{" + super.toString() + ", тривалість: " + formatDuration() + ", читає: " + narrator + "}"; }
    }

    static class Gamer {
        private String nickname;
        private int hoursPlayed;

        public Gamer(String nickname, int hoursPlayed) {
            this.nickname = nickname;
            this.hoursPlayed = hoursPlayed;
        }

        @Override
        public String toString() { return "Gamer{nickname='" + nickname + "', hours=" + hoursPlayed + "}"; }
    }

    static class ProEsportsPlayer extends Gamer {
        private String teamName;
        private double prizeMoneyUSD;
        private int tournamentsWon;

        public ProEsportsPlayer(String nickname, int hoursPlayed, String teamName, double prizeMoneyUSD, int tournamentsWon) {
            super(nickname, hoursPlayed);
            this.teamName = teamName;
            this.prizeMoneyUSD = prizeMoneyUSD;
            this.tournamentsWon = tournamentsWon;
        }

        public double getAveragePrizePerWin() {
            return tournamentsWon == 0 ? 0.0 : prizeMoneyUSD / tournamentsWon;
        }

        @Override
        public String toString() {
            return String.format("ProEsportsPlayer{%s, team='%s', prize=$%.2f, wins=%d, avg/win=$%.2f}",
                    super.toString(), teamName, prizeMoneyUSD, tournamentsWon, getAveragePrizePerWin());
        }
    }

    // ======================= БЛОК 3: Перевизначення методів (17–24) =======================

    static class StaffMember {
        private String name;
        private double baseSalary;

        public StaffMember(String name, double baseSalary) {
            this.name = name;
            this.baseSalary = baseSalary;
        }

        public String getName() { return name; }
        public double getBaseSalary() { return baseSalary; }
        public double calculateMonthlyPay() { return baseSalary; }

        @Override
        public String toString() {
            return String.format("StaffMember{name='%s', base=%.2f, pay=%.2f}", name, baseSalary, calculateMonthlyPay());
        }
    }

    static class Manager extends StaffMember {
        private double teamBonus;

        public Manager(String name, double baseSalary, double teamBonus) {
            super(name, baseSalary);
            this.teamBonus = teamBonus;
        }

        @Override
        public double calculateMonthlyPay() { return super.calculateMonthlyPay() + teamBonus; }

        @Override
        public String toString() {
            return String.format("Manager{name='%s', base=%.2f, bonus=%.2f, pay=%.2f}",
                    getName(), getBaseSalary(), teamBonus, calculateMonthlyPay());
        }
    }

    static class SalesPerson extends StaffMember {
        private double salesVolume;
        private double commissionRate;

        public SalesPerson(String name, double baseSalary, double salesVolume, double commissionRate) {
            super(name, baseSalary);
            this.salesVolume = salesVolume;
            this.commissionRate = commissionRate;
        }

        @Override
        public double calculateMonthlyPay() { return super.calculateMonthlyPay() + salesVolume * (commissionRate / 100.0); }

        @Override
        public String toString() {
            return String.format("SalesPerson{name='%s', base=%.2f, sales=%.2f, rate=%.1f%%, pay=%.2f}",
                    getName(), getBaseSalary(), salesVolume, commissionRate, calculateMonthlyPay());
        }
    }

    static class StandardTransfer {
        protected double amount;

        public StandardTransfer(double amount) { this.amount = amount; }

        public double getFee() { return amount * 0.01; }
        public double getTotalWithFee() { return amount + getFee(); }

        @Override
        public String toString() {
            return String.format("StandardTransfer{amount=%.2f, fee=%.2f, total=%.2f}", amount, getFee(), getTotalWithFee());
        }
    }

    static class InternationalTransfer extends StandardTransfer {
        private String countryCode;

        public InternationalTransfer(double amount, String countryCode) {
            super(amount);
            this.countryCode = countryCode;
        }

        @Override
        public double getFee() { return super.getFee() * 2 + 15.0; }

        @Override
        public String toString() {
            return String.format("InternationalTransfer{country=%s, amount=%.2f, fee=%.2f (2%% + 15 SWIFT), total=%.2f}",
                    countryCode, amount, getFee(), getTotalWithFee());
        }
    }

    static class Ticket {
        private String eventName;
        private double basePrice;

        public Ticket(String eventName, double basePrice) {
            this.eventName = eventName;
            this.basePrice = basePrice;
        }

        public double getFinalPrice() { return basePrice; }

        @Override
        public String toString() { return String.format("Ticket{event='%s', base=%.2f}", eventName, basePrice); }
    }

    static class StudentTicket extends Ticket {
        private String studentCardNumber;

        public StudentTicket(String eventName, double basePrice, String studentCardNumber) {
            super(eventName, basePrice);
            this.studentCardNumber = studentCardNumber;
        }

        @Override
        public double getFinalPrice() { return super.getFinalPrice() * 0.5; }

        @Override
        public String toString() {
            return String.format("StudentTicket{%s, card=%s, final=%.2f}", super.toString(), studentCardNumber, getFinalPrice());
        }
    }

    static class VipTicket extends Ticket {
        private boolean includesBackstage;
        private double loungeFee;

        public VipTicket(String eventName, double basePrice, boolean includesBackstage, double loungeFee) {
            super(eventName, basePrice);
            this.includesBackstage = includesBackstage;
            this.loungeFee = loungeFee;
        }

        @Override
        public double getFinalPrice() { return super.getFinalPrice() + loungeFee; }

        @Override
        public String toString() {
            return String.format("VipTicket{%s, backstage=%b, lounge=%.2f, final=%.2f}",
                    super.toString(), includesBackstage, loungeFee, getFinalPrice());
        }
    }

    static class Weapon {
        private String title;
        private int baseDamage;

        public Weapon(String title, int baseDamage) {
            this.title = title;
            this.baseDamage = baseDamage;
        }

        public String getTitle() { return title; }
        public int getBaseDamage() { return baseDamage; }
        public int calculateDamage() { return baseDamage; }

        @Override
        public String toString() { return "Weapon{title='" + title + "', damage=" + calculateDamage() + "}"; }
    }

    static class EnchantedSword extends Weapon {
        private String element;
        private int magicPower;
        private int enchantmentLevel;

        public EnchantedSword(String title, int baseDamage, String element, int magicPower, int enchantmentLevel) {
            super(title, baseDamage);
            this.element = element;
            this.magicPower = magicPower;
            this.enchantmentLevel = enchantmentLevel;
        }

        @Override
        public int calculateDamage() { return super.calculateDamage() + magicPower * enchantmentLevel; }

        @Override
        public String toString() {
            return String.format("EnchantedSword{'%s', фізична=%d + магічна=%d (%s, %d×%d) = %d}",
                    getTitle(), getBaseDamage(), magicPower * enchantmentLevel, element,
                    magicPower, enchantmentLevel, calculateDamage());
        }
    }

    static class Parcel {
        private String trackingCode;
        private double weightKg;

        public Parcel(String trackingCode, double weightKg) {
            this.trackingCode = trackingCode;
            this.weightKg = weightKg;
        }

        public String getTrackingCode() { return trackingCode; }
        public double getWeightKg() { return weightKg; }
        public double calculateShippingCost() { return 50.0 + weightKg * 12.0; }

        @Override
        public String toString() {
            return String.format("Parcel{code=%s, weight=%.1f кг, cost=%.2f грн}", trackingCode, weightKg, calculateShippingCost());
        }
    }

    static class FragileParcel extends Parcel {
        private boolean requiresWoodenBox;
        private double insuranceValue;

        public FragileParcel(String trackingCode, double weightKg, boolean requiresWoodenBox, double insuranceValue) {
            super(trackingCode, weightKg);
            this.requiresWoodenBox = requiresWoodenBox;
            this.insuranceValue = insuranceValue;
        }

        @Override
        public double calculateShippingCost() {
            double cost = super.calculateShippingCost() + insuranceValue * 0.015;
            if (requiresWoodenBox) cost += 120.0;
            return cost;
        }

        @Override
        public String toString() {
            return String.format("FragileParcel{code=%s, weight=%.1f кг, insured=%.2f, box=%b, cost=%.2f грн}",
                    getTrackingCode(), getWeightKg(), insuranceValue, requiresWoodenBox, calculateShippingCost());
        }
    }

    static class Appliance {
        private String model;
        private double powerWatts;

        public Appliance(String model, double powerWatts) {
            this.model = model;
            this.powerWatts = powerWatts;
        }

        public String getModel() { return model; }
        public double getPowerWatts() { return powerWatts; }
        public double getDailyConsumptionKWh(int hours) { return (powerWatts * hours) / 1000.0; }

        @Override
        public String toString() { return "Appliance{model='" + model + "', power=" + powerWatts + " Вт}"; }
    }

    static class EcoHeater extends Appliance {
        private boolean ecoModeActive;

        public EcoHeater(String model, double powerWatts, boolean ecoModeActive) {
            super(model, powerWatts);
            this.ecoModeActive = ecoModeActive;
        }

        @Override
        public double getDailyConsumptionKWh(int hours) {
            double base = super.getDailyConsumptionKWh(hours);
            return ecoModeActive ? base * 0.75 : base;
        }

        @Override
        public String toString() {
            return String.format("EcoHeater{%s, eco=%b, 8 год = %.2f кВт·год}", super.toString(), ecoModeActive, getDailyConsumptionKWh(8));
        }
    }

    static class Point2D {
        private double x;
        private double y;

        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() { return x; }
        public double getY() { return y; }
        public double distanceToOrigin() { return Math.sqrt(x * x + y * y); }

        @Override
        public String toString() { return "(" + x + "; " + y + ")"; }
    }

    static class Point3D extends Point2D {
        private double z;

        public Point3D(double x, double y, double z) {
            super(x, y);
            this.z = z;
        }

        @Override
        public double distanceToOrigin() {
            return Math.sqrt(Math.pow(super.distanceToOrigin(), 2) + z * z);
        }

        @Override
        public String toString() { return "(" + getX() + "; " + getY() + "; " + z + ")"; }
    }

    static class Coffee {
        private String roastType;
        private int volumeMl;
        private double basePrice;

        public Coffee(String roastType, int volumeMl, double basePrice) {
            this.roastType = roastType;
            this.volumeMl = volumeMl;
            this.basePrice = basePrice;
        }

        public String getRoastType() { return roastType; }
        public int getVolumeMl() { return volumeMl; }
        public double calculatePrice() { return basePrice; }
        public int calculateCalories() { return volumeMl / 50; } // чорна кава — майже без калорій

        @Override
        public String toString() {
            return String.format("Coffee{roast=%s, %d мл, ціна=%.2f грн, %d ккал}", roastType, volumeMl, calculatePrice(), calculateCalories());
        }
    }

    static class LatteWithAddons extends Coffee {
        private String milkType;
        private int syrupPumps;

        public LatteWithAddons(String roastType, int volumeMl, double basePrice, String milkType, int syrupPumps) {
            super(roastType, volumeMl, basePrice);
            this.milkType = milkType;
            this.syrupPumps = syrupPumps;
        }

        private boolean isPlantMilk() {
            return milkType.equalsIgnoreCase("вівсяне")
                    || milkType.equalsIgnoreCase("мигдальне") || milkType.equalsIgnoreCase("соєве");
        }

        @Override
        public double calculatePrice() {
            return super.calculatePrice() + (isPlantMilk() ? 20.0 : 0.0) + syrupPumps * 12.0;
        }

        @Override
        public int calculateCalories() { return super.calculateCalories() + getVolumeMl() / 4 + syrupPumps * 20; }

        @Override
        public String toString() {
            return String.format("☕ ЧЕК: Латте (%s), %d мл, молоко: %s, сироп: %d пд. | %.2f грн | %d ккал",
                    getRoastType(), getVolumeMl(), milkType, syrupPumps, calculatePrice(), calculateCalories());
        }
    }

    // ======================= БЛОК 4: Багаторівневе наслідування, final (25–32) =======================

    static class DotaHero {
        private String name;
        private String primaryAttribute;
        private int baseHp;

        public DotaHero(String name, String primaryAttribute, int baseHp) {
            this.name = name;
            this.primaryAttribute = primaryAttribute;
            this.baseHp = baseHp;
        }

        public String getName() { return name; }
        public int getBaseHp() { return baseHp; }
        public int getMaxHp() { return baseHp; }

        @Override
        public String toString() { return "DotaHero{name='" + name + "', attr=" + primaryAttribute + ", maxHp=" + getMaxHp() + "}"; }
    }

    static class StrengthHero extends DotaHero {
        private int strength;

        public StrengthHero(String name, int baseHp, int strength) {
            super(name, "STR", baseHp);
            this.strength = strength;
        }

        @Override
        public int getMaxHp() { return super.getMaxHp() + strength * 22; }

        @Override
        public String toString() { return "StrengthHero{" + super.toString() + ", strength=" + strength + "}"; }
    }

    static class AlchemistHero extends StrengthHero {
        private int greevilsGreedBonusGold;

        public AlchemistHero(int baseHp, int strength, int greevilsGreedBonusGold) {
            super("Alchemist", baseHp, strength);
            this.greevilsGreedBonusGold = greevilsGreedBonusGold;
        }

        public String chemicalRage() { return getName() + " активує Chemical Rage! Макс. HP: " + getMaxHp(); }

        @Override
        public String toString() { return "AlchemistHero{" + super.toString() + ", bonusGold=" + greevilsGreedBonusGold + "}"; }
    }

    static class BaseVehicle {
        private String brand;
        private int year;

        public BaseVehicle(String brand, int year) {
            this.brand = brand;
            this.year = year;
        }

        @Override
        public String toString() { return "BaseVehicle{brand='" + brand + "', year=" + year + "}"; }
    }

    static class MotorCar extends BaseVehicle {
        private int horsePower;
        private int doorsCount;

        public MotorCar(String brand, int year, int horsePower, int doorsCount) {
            super(brand, year);
            this.horsePower = horsePower;
            this.doorsCount = doorsCount;
        }

        @Override
        public String toString() { return "MotorCar{" + super.toString() + ", hp=" + horsePower + ", doors=" + doorsCount + "}"; }
    }

    static class ElectricCar extends MotorCar {
        private double batteryCapacityKWh;
        private int rangeKm;

        public ElectricCar(String brand, int year, int horsePower, int doorsCount, double batteryCapacityKWh, int rangeKm) {
            super(brand, year, horsePower, doorsCount);
            this.batteryCapacityKWh = batteryCapacityKWh;
            this.rangeKm = rangeKm;
        }

        @Override
        public String toString() { return "ElectricCar{" + super.toString() + ", battery=" + batteryCapacityKWh + " кВт·год, range=" + rangeKm + " км}"; }
    }

    static class PaymentCard {
        private String cardHolder;
        protected double balance;

        public PaymentCard(String cardHolder, double balance) {
            this.cardHolder = cardHolder;
            this.balance = balance;
        }

        public String getCardHolder() { return cardHolder; }

        public boolean pay(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            }
            return false;
        }

        @Override
        public String toString() { return String.format("PaymentCard{holder='%s', balance=%.2f}", cardHolder, balance); }
    }

    static class DebitCard extends PaymentCard {
        private double dailyLimit;

        public DebitCard(String cardHolder, double balance, double dailyLimit) {
            super(cardHolder, balance);
            this.dailyLimit = dailyLimit;
        }

        @Override
        public boolean pay(double amount) {
            if (amount > dailyLimit) return false;
            return super.pay(amount);
        }

        @Override
        public String toString() { return String.format("DebitCard{%s, dailyLimit=%.2f}", super.toString(), dailyLimit); }
    }

    static class CashbackPlatinumCard extends DebitCard {
        private double cashbackPercent;
        private double accumulatedCashback;

        public CashbackPlatinumCard(String cardHolder, double balance, double dailyLimit, double cashbackPercent) {
            super(cardHolder, balance, dailyLimit);
            this.cashbackPercent = cashbackPercent;
        }

        @Override
        public boolean pay(double amount) {
            boolean ok = super.pay(amount);
            if (ok) accumulatedCashback += amount * cashbackPercent / 100.0;
            return ok;
        }

        @Override
        public String toString() {
            return String.format("CashbackPlatinumCard{%s, cashback=%.1f%%, accumulated=%.2f}", super.toString(), cashbackPercent, accumulatedCashback);
        }
    }

    static class SecurityTerminal {
        /** final-метод: підкласи не можуть його перевизначити. */
        public final String generateAuthHash(String user) { return "AUTH-SHA-" + user.toUpperCase(); }

        public String getWelcomeBanner() { return "Welcome to SecurityTerminal"; }
    }

    static class CustomTerminal extends SecurityTerminal {
        @Override
        public String getWelcomeBanner() { return "Custom terminal | " + generateAuthHash("admin"); }

        // Спроба написати тут @Override public String generateAuthHash(String user) не скомпілюється:
        // метод оголошено як final у SecurityTerminal, а final-методи заборонено перевизначати.
    }

    static final class ImmutableServerCredentials {
        private final String host;
        private final int port;
        private final String apiKey;

        public ImmutableServerCredentials(String host, int port, String apiKey) {
            this.host = host;
            this.port = port;
            this.apiKey = apiKey;
        }

        public String getHost() { return host; }
        public int getPort() { return port; }
        public String getApiKey() { return apiKey; }

        @Override
        public String toString() { return "ImmutableServerCredentials{host='" + host + "', port=" + port + ", apiKey='***SECRET***'}"; }
        // class X extends ImmutableServerCredentials — помилка компіляції: клас final.
    }

    static class Segment1D {
        protected double length;

        public Segment1D(double length) { this.length = length > 0 ? length : 1.0; }

        @Override
        public String toString() { return "Segment1D{length=" + length + "}"; }
    }

    static class Square2D extends Segment1D {
        public Square2D(double side) { super(side); }

        public double getPerimeter() { return 4 * length; }
        public double getArea() { return length * length; }

        @Override
        public String toString() { return String.format("Square2D{%s, P=%.2f, S=%.2f}", super.toString(), getPerimeter(), getArea()); }
    }

    static class Cube3D extends Square2D {
        public Cube3D(double side) { super(side); }

        @Override
        public double getArea() { return 6 * super.getArea(); }
        public double getVolume() { return length * length * length; }

        @Override
        public String toString() { return String.format("Cube3D{%s, V=%.2f}", super.toString(), getVolume()); }
    }

    static class User {
        private int id;
        private String email;

        public User(int id, String email) {
            this.id = id;
            this.email = email;
        }

        @Override
        public String toString() { return "User{id=" + id + ", email='" + email + "'}"; }
    }

    static class StudentUser extends User {
        private String groupCode;
        protected double gpa;

        public StudentUser(int id, String email, String groupCode, double gpa) {
            super(id, email);
            this.groupCode = groupCode;
            this.gpa = gpa;
        }

        @Override
        public String toString() { return "StudentUser{" + super.toString() + ", group=" + groupCode + ", gpa=" + gpa + "}"; }
    }

    static class PostgraduateStudent extends StudentUser {
        private String thesisTopic;
        private String supervisorName;

        public PostgraduateStudent(int id, String email, String groupCode, double gpa, String thesisTopic, String supervisorName) {
            super(id, email, groupCode, gpa);
            this.thesisTopic = thesisTopic;
            this.supervisorName = supervisorName;
        }

        public boolean canDefendThesis() {
            return gpa >= 85.0 && thesisTopic != null && !thesisTopic.isBlank();
        }

        @Override
        public String toString() {
            return "PostgraduateStudent{" + super.toString() + ", thesis='" + thesisTopic + "', supervisor='" + supervisorName
                    + "', canDefend=" + canDefendThesis() + "}";
        }
    }

    static class Medicine {
        private String name;
        private double price;
        private boolean isExpired;

        public Medicine(String name, double price, boolean isExpired) {
            this.name = name;
            this.price = price;
            this.isExpired = isExpired;
        }

        public boolean isExpired() { return isExpired; }

        @Override
        public String toString() { return "Medicine{name='" + name + "', price=" + price + ", expired=" + isExpired + "}"; }
    }

    static class PrescriptionMedicine extends Medicine {
        private String doctorLicense;

        public PrescriptionMedicine(String name, double price, boolean isExpired, String doctorLicense) {
            super(name, price, isExpired);
            this.doctorLicense = doctorLicense;
        }

        public boolean canSell(boolean hasPrescription) { return !isExpired() && hasPrescription; }

        @Override
        public String toString() { return "PrescriptionMedicine{" + super.toString() + ", license=" + doctorLicense + "}"; }
    }

    static class ControlledSubstance extends PrescriptionMedicine {
        private int patientAge;

        public ControlledSubstance(String name, double price, boolean isExpired, String doctorLicense, int patientAge) {
            super(name, price, isExpired, doctorLicense);
            this.patientAge = patientAge;
        }

        @Override
        public boolean canSell(boolean hasPrescription) { return super.canSell(hasPrescription) && patientAge >= 18; }

        @Override
        public String toString() { return "ControlledSubstance{" + super.toString() + ", patientAge=" + patientAge + "}"; }
    }

    // ======================= БЛОК 5: Комплексні кейси (33–40) =======================

    static class Progression {
        protected double firstTerm;
        protected double step;

        public Progression(double firstTerm, double step) {
            this.firstTerm = firstTerm;
            this.step = step;
        }

        public double getTerm(int n) { return firstTerm; }

        public double[] generateFirstN(int n) {
            double[] result = new double[Math.max(0, n)];
            for (int i = 1; i <= result.length; i++) {
                result[i - 1] = getTerm(i);
            }
            return result;
        }

        @Override
        public String toString() { return "Progression{a1=" + firstTerm + ", step=" + step + ", first5=" + Arrays.toString(generateFirstN(5)) + "}"; }
    }

    static class ArithmeticProgression extends Progression {
        public ArithmeticProgression(double a1, double d) { super(a1, d); }

        @Override
        public double getTerm(int n) { return firstTerm + (n - 1) * step; }

        @Override
        public String toString() {
            return "ArithmeticProgression{a_n = " + firstTerm + " + (n-1)*" + step + ", first5=" + Arrays.toString(generateFirstN(5)) + "}";
        }
    }

    static class GeometricProgression extends Progression {
        public GeometricProgression(double b1, double q) { super(b1, q); }

        @Override
        public double getTerm(int n) { return firstTerm * Math.pow(step, n - 1); }

        @Override
        public String toString() {
            return "GeometricProgression{b_n = " + firstTerm + " * " + step + "^(n-1), first5=" + Arrays.toString(generateFirstN(5)) + "}";
        }
    }

    static class GradeSheet {
        private String studentName;
        protected int[] grades;

        public GradeSheet(String studentName, int[] grades) {
            this.studentName = studentName;
            this.grades = Arrays.copyOf(grades, grades.length);
        }

        public String getStudentName() { return studentName; }

        public double calculateScore() {
            if (grades.length == 0) return 0.0;
            double s = 0;
            for (int g : grades) s += g;
            return s / grades.length;
        }

        @Override
        public String toString() {
            return String.format("GradeSheet{student='%s', grades=%s, average=%.2f}", studentName, Arrays.toString(grades), calculateScore());
        }
    }

    static class WeightedGradeSheet extends GradeSheet {
        private double[] weights;

        public WeightedGradeSheet(String studentName, int[] grades, double[] weights) {
            super(studentName, grades);
            this.weights = Arrays.copyOf(weights, weights.length);
        }

        @Override
        public double calculateScore() {
            double sum = 0, wSum = 0;
            for (int i = 0; i < grades.length && i < weights.length; i++) {
                sum += grades[i] * weights[i];
                wSum += weights[i];
            }
            return wSum == 0 ? 0.0 : sum / wSum;
        }

        @Override
        public String toString() {
            return String.format("WeightedGradeSheet{student='%s', grades=%s, weights=%s, weighted=%.2f}",
                    getStudentName(), Arrays.toString(grades), Arrays.toString(weights), calculateScore());
        }
    }

    static class LinearFunction {
        protected double k;
        protected double b;

        public LinearFunction(double k, double b) {
            this.k = k;
            this.b = b;
        }

        public double evaluate(double x) { return k * x + b; }

        @Override
        public String toString() { return "f(x) = " + k + "x + " + b; }
    }

    static class QuadraticFunction extends LinearFunction {
        private double a;

        public QuadraticFunction(double a, double k, double b) {
            super(k, b);
            this.a = a == 0 ? 1.0 : a;
        }

        @Override
        public double evaluate(double x) { return a * x * x + super.evaluate(x); }

        public double getVertexX() { return -k / (2 * a); }

        @Override
        public String toString() { return "f(x) = " + a + "x^2 + " + k + "x + " + b + " (вершина x0=" + getVertexX() + ")"; }
    }

    static class WarehouseItem {
        private String name;
        private int quantity;
        private double[] priceHistory;

        public WarehouseItem(String name, int quantity, double[] priceHistory) {
            this.name = name;
            this.quantity = quantity;
            this.priceHistory = Arrays.copyOf(priceHistory, priceHistory.length);
        }

        public String getName() { return name; }
        public int getQuantity() { return quantity; }

        public double getAveragePurchasePrice() {
            if (priceHistory.length == 0) return 0.0;
            double s = 0;
            for (double p : priceHistory) s += p;
            return s / priceHistory.length;
        }

        @Override
        public String toString() {
            return String.format("WarehouseItem{name='%s', qty=%d, avgPrice=%.2f}", name, quantity, getAveragePurchasePrice());
        }
    }

    static class DiscountedWarehouseItem extends WarehouseItem {
        private String markdownReason;
        private double discountPercent;

        public DiscountedWarehouseItem(String name, int quantity, double[] priceHistory, String markdownReason, double discountPercent) {
            super(name, quantity, priceHistory);
            this.markdownReason = markdownReason;
            this.discountPercent = discountPercent;
        }

        public double getSaleValue() {
            return getAveragePurchasePrice() * (1 - discountPercent / 100.0) * getQuantity();
        }

        @Override
        public String toString() {
            return String.format("DiscountedWarehouseItem{%s, reason='%s', -%.0f%%, saleValue=%.2f}",
                    super.toString(), markdownReason, discountPercent, getSaleValue());
        }
    }

    static class CinemaHall {
        private String hallName;
        protected boolean[][] seats;
        protected double baseTicketPrice;

        public CinemaHall(String hallName, int rows, int cols, double baseTicketPrice) {
            this.hallName = hallName;
            this.seats = new boolean[rows][cols];
            this.baseTicketPrice = baseTicketPrice;
        }

        public boolean bookSeat(int row, int col) {
            if (row < 0 || row >= seats.length || col < 0 || col >= seats[0].length || seats[row][col]) return false;
            seats[row][col] = true;
            return true;
        }

        public int getOccupiedCount() {
            int c = 0;
            for (boolean[] r : seats) for (boolean s : r) if (s) c++;
            return c;
        }

        public double calculateRevenue() { return getOccupiedCount() * baseTicketPrice; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("CinemaHall '" + hallName + "' (зайнято " + getOccupiedCount() + ", виручка "
                    + String.format("%.2f", calculateRevenue()) + ")\n");
            for (boolean[] row : seats) {
                for (boolean s : row) sb.append(s ? "[X]" : "[O]");
                sb.append("\n");
            }
            return sb.toString().stripTrailing();
        }
    }

    static class VipCinemaHall extends CinemaHall {
        private double complimentaryBarCost;

        public VipCinemaHall(String hallName, int rows, int cols, double baseTicketPrice, double complimentaryBarCost) {
            super(hallName, rows, cols, baseTicketPrice);
            this.complimentaryBarCost = complimentaryBarCost;
        }

        @Override
        public double calculateRevenue() {
            double revenue = super.calculateRevenue();
            boolean[] last = seats[seats.length - 1];
            for (boolean s : last) if (s) revenue += complimentaryBarCost;
            return revenue;
        }
    }

    static class TeamLead extends Developer {
        private Developer[] teamMembers = new Developer[10];
        private int memberCount = 0;

        public TeamLead(String name, double salary, String language) { super(name, salary, language); }

        public void addTeamMember(Developer dev) {
            if (dev == null) return;
            if (memberCount == teamMembers.length) teamMembers = Arrays.copyOf(teamMembers, teamMembers.length * 2);
            teamMembers[memberCount++] = dev;
        }

        public double getTotalTeamBudget() {
            double total = salary;
            for (int i = 0; i < memberCount; i++) total += teamMembers[i].salary;
            return total;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("TeamLead{" + super.toString() + ", budget=" + getTotalTeamBudget() + ", team=[");
            for (int i = 0; i < memberCount; i++) {
                sb.append(teamMembers[i].getName());
                if (i < memberCount - 1) sb.append(", ");
            }
            return sb.append("]}").toString();
        }
    }

    static class CharacterUnit {
        private String name;
        protected int hp;
        protected int maxHp;
        protected int armor;

        public CharacterUnit(String name, int maxHp, int armor) {
            this.name = name;
            this.maxHp = maxHp;
            this.hp = maxHp;
            this.armor = armor;
        }

        public String getName() { return name; }

        public void takeDamage(int rawDamage) { hp = Math.max(0, hp - Math.max(1, rawDamage - armor)); }

        protected String hpBar() {
            int filled = (int) Math.round(10.0 * hp / maxHp);
            return "[" + "█".repeat(filled) + "░".repeat(10 - filled) + "]";
        }

        @Override
        public String toString() { return String.format("%s %s %d/%d (armor %d)", name, hpBar(), hp, maxHp, armor); }
    }

    static class ShieldedPaladin extends CharacterUnit {
        private int shieldPoints;

        public ShieldedPaladin(String name, int maxHp, int armor, int shieldPoints) {
            super(name, maxHp, armor);
            this.shieldPoints = shieldPoints;
        }

        @Override
        public void takeDamage(int rawDamage) {
            if (shieldPoints >= rawDamage) {
                shieldPoints -= rawDamage;
                return;
            }
            int remaining = rawDamage - shieldPoints;
            shieldPoints = 0;
            super.takeDamage(remaining);
        }

        @Override
        public String toString() { return "Paladin " + super.toString() + " | щит: " + shieldPoints; }
    }

    // ============================== ЗАПУСК ==============================
    private static void title(int n, String text) {
        System.out.println("\n--- Завдання " + n + ": " + text + " ---");
    }

    public static void main(String[] args) {
        // Блок 1
        title(1, "Student");
        Student st = new Student("Олена", 9, 150);
        System.out.println(st);

        title(2, "Product");
        System.out.println(new Product("A-101", "Монітор", 6499.50, 4));
        System.out.println(new Product("B-202", "Миша", -5, -1));

        title(3, "Circle");
        Circle c = new Circle(-3);
        System.out.println(c);
        c.setRadius(2);
        System.out.println(c);

        title(4, "CardWallet");
        CardWallet w = new CardWallet("1234567812344589", "1111", 12500);
        System.out.println(w);
        System.out.println("PIN змінено: " + w.changePin("1111", "2222") + ", з хибним: " + w.changePin("0000", "3333"));

        title(5, "SensorNode");
        System.out.println(new SensorNode("T-1", 20.5, 0.7));

        title(6, "WeatherDay");
        double[] temps = {10.5, 12.0, 15.5, 14.0};
        WeatherDay day = new WeatherDay("2026-11-07", temps);
        temps[0] = 99; // не впливає на об'єкт
        System.out.println(day + " | min=" + day.getMinTemp() + ", max=" + day.getMaxTemp());

        title(7, "IntRange");
        IntRange r = new IntRange(10, 3);
        System.out.println(r + ", contains(5)=" + r.contains(5));

        title(8, "InvoiceItem");
        System.out.println("Позиція чека: " + new InvoiceItem("Ноутбук", 2, 25000, 10));

        // Блок 2
        title(9, "Employee -> Developer");
        System.out.println(new Employee("Anna", 15000));
        System.out.println(new Developer("Polina", 20000, "Java"));

        title(10, "Animal -> Dog, Cat");
        Dog dog = new Dog("Рекс", 3, 25.5, "Лабрадор");
        Cat cat = new Cat("Мурка", 2, 4.2, true);
        System.out.println(dog + "\n" + dog.fetchStick());
        System.out.println(cat + "\n" + cat.purr());

        title(11, "Vehicle -> PassengerCar, CargoTruck");
        System.out.println(new PassengerCar("Toyota", 200, 55, 5));
        System.out.println(new CargoTruck("MAN", 110, 400, 12));

        title(12, "Rectangle -> Box3D");
        System.out.println(new Rectangle(3, 4));
        System.out.println(new Box3D(3, 4, 5));

        title(13, "Person -> UniversityStudent, Professor");
        Person p1 = new UniversityStudent("Іван Петренко", 2005, "ST-1", "CS", 2000);
        Person p2 = new Professor("Марія Іваненко", 1975, "Доцент", 42);
        System.out.println(p1 + " | вік: " + p1.getAge(2026));
        System.out.println(p2 + " | вік: " + p2.getAge(2026));

        title(14, "Gadget -> Smartphone");
        System.out.println(new Smartphone("Samsung", 25000, 256, true));
        System.out.println(new Smartphone("Xiaomi", 12000, 128));
        System.out.println(new Smartphone("Nokia"));

        title(15, "Publication -> PrintedBook, AudioBook");
        System.out.println(new PrintedBook("Кобзар", "Т. Шевченко", 1840, 320, "тверда"));
        System.out.println(new AudioBook("Тіні забутих предків", "М. Коцюбинський", 1911, 135, "А. Хостікоєв"));

        title(16, "Gamer -> ProEsportsPlayer");
        System.out.println(new Gamer("Newbie", 120));
        System.out.println(new ProEsportsPlayer("s1mple", 15000, "NAVI", 1500000, 20));
        System.out.println(new ProEsportsPlayer("Rookie", 500, "Academy", 0, 0));

        // Блок 3
        title(17, "StaffMember -> Manager, SalesPerson");
        System.out.println(new StaffMember("Олег", 20000));
        System.out.println(new Manager("Ірина", 30000, 8000));
        System.out.println(new SalesPerson("Петро", 15000, 200000, 5));

        title(18, "StandardTransfer -> InternationalTransfer");
        System.out.println(new StandardTransfer(1000));
        System.out.println(new InternationalTransfer(1000, "PL"));

        title(19, "Ticket -> StudentTicket, VipTicket");
        System.out.println(new Ticket("Концерт", 800));
        System.out.println(new StudentTicket("Концерт", 800, "KV12345"));
        System.out.println(new VipTicket("Концерт", 800, true, 500));

        title(20, "Weapon -> EnchantedSword");
        System.out.println(new Weapon("Кинджал", 20));
        System.out.println(new EnchantedSword("Фростморн", 50, "Лід", 12, 3));

        title(21, "Parcel -> FragileParcel");
        System.out.println(new Parcel("UA001", 2.5));
        System.out.println(new FragileParcel("UA002", 2.5, true, 4000));

        title(22, "Appliance -> EcoHeater");
        System.out.println(new Appliance("Лампа", 100));
        System.out.println(new EcoHeater("Обігрівач X", 2000, true));
        System.out.println(new EcoHeater("Обігрівач Y", 2000, false));

        title(23, "Point2D -> Point3D");
        Point2D pt2 = new Point2D(3, 4);
        Point3D pt3 = new Point3D(2, 3, 6);
        System.out.println(pt2 + " -> " + pt2.distanceToOrigin());
        System.out.println(pt3 + " -> " + pt3.distanceToOrigin());

        title(24, "Coffee -> LatteWithAddons");
        System.out.println(new Coffee("темна", 200, 55));
        System.out.println(new LatteWithAddons("середня", 300, 70, "вівсяне", 2));

        // Блок 4
        title(25, "DotaHero -> StrengthHero -> AlchemistHero");
        AlchemistHero alch = new AlchemistHero(200, 25, 1000);
        System.out.println(alch);
        System.out.println(alch.chemicalRage());

        title(26, "BaseVehicle -> MotorCar -> ElectricCar");
        System.out.println(new ElectricCar("Tesla", 2024, 670, 4, 100, 600));

        title(27, "PaymentCard -> DebitCard -> CashbackPlatinumCard");
        CashbackPlatinumCard card = new CashbackPlatinumCard("Олег", 10000, 5000, 3);
        System.out.println("Оплата 2000: " + card.pay(2000));
        System.out.println("Оплата 6000 (ліміт): " + card.pay(6000));
        System.out.println(card);

        title(28, "final-метод SecurityTerminal");
        System.out.println(new CustomTerminal().getWelcomeBanner());

        title(29, "final-клас ImmutableServerCredentials");
        System.out.println(new ImmutableServerCredentials("api.example.com", 443, "abc123"));

        title(30, "Segment1D -> Square2D -> Cube3D");
        System.out.println(new Segment1D(3));
        System.out.println(new Square2D(3));
        System.out.println(new Cube3D(3));

        title(31, "User -> StudentUser -> PostgraduateStudent");
        System.out.println(new PostgraduateStudent(1, "asp@uni.ua", "AS-21", 90, "Нейромережі", "проф. Коваль"));
        System.out.println(new PostgraduateStudent(2, "asp2@uni.ua", "AS-22", 70, "", "проф. Мельник"));

        title(32, "Medicine -> PrescriptionMedicine -> ControlledSubstance");
        System.out.println(new Medicine("Аспірин", 50, false));
        PrescriptionMedicine pm = new PrescriptionMedicine("Антибіотик", 200, false, "LIC-1");
        System.out.println(pm.canSell(true) + " / " + pm.canSell(false));
        ControlledSubstance cs = new ControlledSubstance("Морфін", 900, false, "LIC-2", 17);
        System.out.println(cs + "\ncanSell(рецепт, 17 років) = " + cs.canSell(true));
        System.out.println("canSell(рецепт, 30 років) = "
                + new ControlledSubstance("Морфін", 900, false, "LIC-2", 30).canSell(true));

        // Блок 5
        title(33, "Progression");
        System.out.println(new ArithmeticProgression(2, 3));
        System.out.println(new GeometricProgression(1, 2));

        title(34, "GradeSheet -> WeightedGradeSheet");
        int[] grades = {90, 70, 100};
        System.out.println(new GradeSheet("Олена", grades));
        System.out.println(new WeightedGradeSheet("Олена", grades, new double[]{1, 2, 3}));

        title(35, "LinearFunction -> QuadraticFunction");
        LinearFunction lf = new LinearFunction(2, 1);
        QuadraticFunction qf = new QuadraticFunction(1, -4, 3);
        System.out.println(lf + ", f(3) = " + lf.evaluate(3));
        System.out.println(qf + ", f(3) = " + qf.evaluate(3));

        title(36, "WarehouseItem -> DiscountedWarehouseItem");
        double[] hist = {100, 110, 120};
        System.out.println(new WarehouseItem("Кабель", 10, hist));
        System.out.println(new DiscountedWarehouseItem("Кабель", 10, hist, "Пошкоджена упаковка", 20));

        title(37, "CinemaHall -> VipCinemaHall");
        CinemaHall hall = new CinemaHall("Зал 1", 3, 4, 150);
        hall.bookSeat(0, 1);
        hall.bookSeat(2, 3);
        System.out.println(hall);
        VipCinemaHall vip = new VipCinemaHall("VIP", 3, 4, 300, 100);
        vip.bookSeat(0, 0);
        vip.bookSeat(2, 1);
        vip.bookSeat(2, 2);
        System.out.println(vip);
        System.out.println("Виручка VIP (з націнкою останнього ряду): " + vip.calculateRevenue());

        title(38, "Developer -> TeamLead");
        TeamLead lead = new TeamLead("Андрій", 40000, "Java");
        lead.addTeamMember(new Developer("Polina", 20000, "Java"));
        lead.addTeamMember(new Developer("Ihor", 25000, "Kotlin"));
        System.out.println(lead);

        title(39, "CharacterUnit -> ShieldedPaladin");
        CharacterUnit warrior = new CharacterUnit("Воїн", 100, 5);
        ShieldedPaladin paladin = new ShieldedPaladin("Артас", 100, 5, 30);
        warrior.takeDamage(40);
        paladin.takeDamage(20);
        System.out.println(warrior + "\n" + paladin);
        paladin.takeDamage(40); // щит 10 поглинає, 30 -> super (30-5=25)
        System.out.println(paladin);

        title(40, "Git");
        System.out.println("Виконайте у терміналі:\n  git status\n  git add src/main/java/semester1/practice08/\n"
                + "  git commit -m \"Complete Practice 08 tasks on encapsulation, inheritance and toString\"\n  git push");
    }
}
