package semester1.practice01;

import java.util.Arrays;

public class Practice10 {

    public static void main(String[] args) {

    }

    private static void runBlock1() {
        System.out.println("\n--- БЛОК 1: Динамічний поліморфізм та поліморфні масиви (Завдання 1-8) ---");

        PaymentInstrument[] payments = {
            new BankCardInstrument("Іван Петренко"),
            new CryptoTokenInstrument("Олена Коваль", 35.0),
            new IbanDirectInstrument("ТОВ 'ТехноСвіт'")
        };
        double totalPayments = 0;
        for (PaymentInstrument p : payments) {
            totalPayments += p.processPayment(10000.0);
        }
        System.out.printf("[Завдання 1] Сумарні витрати по 3 платіжних інструментах: %.2f грн%n", totalPayments);

        Shape2D[] shapes = {
            new CircleShape(3.0),
            new RectangleShape(4.0, 7.0),
            new EquilateralTriangleShape(6.0)
        };
        Shape2D maxShape = findMaxAreaShape(shapes);
        System.out.printf("[Завдання 2] Фігура з найбільшою площею: %s (S = %.2f)%n",
                maxShape.getClass().getSimpleName(), maxShape.getArea());

        VectorMathOverload overload = new VectorMathOverload();
        double n1 = overload.norm(-5.0);
        double n2 = overload.norm(3.0, 4.0);
        double n3 = overload.norm(new double[]{1.0, 2.0, 2.0});
        BinaryOperation[] ops = {new AddOp(), new MultiplyOp(), new HypotenuseOp()};
        System.out.printf("[Завдання 3] Overloading норми: %.1f, %.1f, %.1f | Overriding (3, 4): ", n1, n2, n3);
        for (BinaryOperation op : ops) {
            System.out.printf("%s=%.1f ", op.getClass().getSimpleName(), op.apply(3.0, 4.0));
        }
        System.out.println();

        NotificationSender[] senders = {
            new EmailSender("admin@company.ua"),
            new SmsSender("+380671234567"),
            new TelegramBotSender("@devops_alert")
        };
        System.out.println("[Завдання 4] Розсилка тривоги:");
        broadcastAlert(senders, "Увага! Критичне навантаження на сервер кластера EU-Central-1 перевищило 95%.");

        ShippingTariff[] tariffs = {
            new RoadTruckTariff(),
            new AirCargoTariff(),
            new RailFreightTariff()
        };
        ShippingTariff bestTariff = tariffs[0];
        for (ShippingTariff t : tariffs) {
            if (t.calculateCost(250, 600) < bestTariff.calculateCost(250, 600)) {
                bestTariff = t;
            }
        }
        System.out.printf("[Завдання 5] Найвигідніший тариф (250 кг, 600 км): %s (%.2f грн)%n",
                bestTariff.getClass().getSimpleName(), bestTariff.calculateCost(250, 600));

        DifferentiableFunction[] funcs = {
            new QuadraticParabola(2.0, -3.0, 1.0),
            new NaturalLogFunction(5.0),
            new ExponentialGrowth(1.5, 0.5)
        };
        System.out.print("[Завдання 6] Функції та похідні в x=2.0: ");
        for (DifferentiableFunction fn : funcs) {
            System.out.printf("%s[f=%.2f, f'=%.2f] ", fn.getClass().getSimpleName(), fn.f(2.0), fn.derivativeAt(2.0));
        }
        System.out.println();

        UiWidget[] widgets = {
            new ButtonWidget("btn-submit", 120, 40, "Відправити"),
            new TextInputWidget("inp-email", 250, 35, "Введіть email..."),
            new ProgressBarWidget("pb-load", 300, 20, 75)
        };
        String html = buildHtmlPage(widgets);
        System.out.println("[Завдання 7] Згенеровано HTML довжиною " + html.length() + " символів.");

        IndustrialAsset[] assets = {
            new LinearDepreciationAsset("ЧПУ Верстат", 500000.0, 10),
            new DecliningBalanceAsset("Промисловий робот", 800000.0, 8, 0.20)
        };
        double totalBookValue = 0;
        for (IndustrialAsset a : assets) {
            totalBookValue += a.getBookValueAfterYears(3);
        }
        System.out.printf("[Завдання 8] Залишкова вартість цеху через 3 роки: %.2f грн%n", totalBookValue);
    }

    static abstract class PaymentInstrument {
        private final String payerName;
        public PaymentInstrument(String payerName) { this.payerName = payerName; }
        public String getPayerName() { return payerName; }
        public abstract double calculateFee(double amount);
        public double processPayment(double amount) { return amount + calculateFee(amount); }
    }
    static class BankCardInstrument extends PaymentInstrument {
        public BankCardInstrument(String payerName) { super(payerName); }
        @Override public double calculateFee(double amount) { return amount * 0.015 + 5.0; }
    }
    static class CryptoTokenInstrument extends PaymentInstrument {
        private final double gasFeeUah;
        public CryptoTokenInstrument(String payerName, double gasFeeUah) { super(payerName); this.gasFeeUah = gasFeeUah; }
        @Override public double calculateFee(double amount) { return gasFeeUah; }
    }
    static class IbanDirectInstrument extends PaymentInstrument {
        public IbanDirectInstrument(String payerName) { super(payerName); }
        @Override public double calculateFee(double amount) { return amount <= 50000.0 ? 0.0 : amount * 0.005; }
    }

    static abstract class Shape2D {
        public abstract double getArea();
        public abstract double getPerimeter();
    }
    static class CircleShape extends Shape2D {
        private final double r;
        public CircleShape(double r) { this.r = r; }
        public double getDiameter() { return 2 * r; }
        @Override public double getArea() { return Math.PI * r * r; }
        @Override public double getPerimeter() { return 2 * Math.PI * r; }
    }
    static class RectangleShape extends Shape2D {
        private final double a, b;
        public RectangleShape(double a, double b) { this.a = a; this.b = b; }
        public double getDiagonal() { return Math.hypot(a, b); }
        public boolean isSquare() { return Math.abs(a - b) < 1e-9; }
        @Override public double getArea() { return a * b; }
        @Override public double getPerimeter() { return 2 * (a + b); }
    }
    static class EquilateralTriangleShape extends Shape2D {
        private final double a;
        public EquilateralTriangleShape(double a) { this.a = a; }
        @Override public double getArea() { return (Math.sqrt(3.0) / 4.0) * a * a; }
        @Override public double getPerimeter() { return 3 * a; }
    }
    public static Shape2D findMaxAreaShape(Shape2D[] shapes) {
        Shape2D best = shapes[0];
        for (Shape2D s : shapes) {
            if (s.getArea() > best.getArea()) best = s;
        }
        return best;
    }

    static class VectorMathOverload {
        public double norm(double x) { return Math.abs(x); }
        public double norm(double x, double y) { return Math.hypot(x, y); }
        public double norm(double[] v) {
            double sum = 0;
            for (double val : v) sum += val * val;
            return Math.sqrt(sum);
        }
    }
    static abstract class BinaryOperation {
        public abstract double apply(double a, double b);
    }
    static class AddOp extends BinaryOperation { @Override public double apply(double a, double b) { return a + b; } }
    static class MultiplyOp extends BinaryOperation { @Override public double apply(double a, double b) { return a * b; } }
    static class HypotenuseOp extends BinaryOperation { @Override public double apply(double a, double b) { return Math.hypot(a, b); } }

    static abstract class NotificationSender {
        private final String recipient;
        public NotificationSender(String recipient) { this.recipient = recipient; }
        public String getRecipient() { return recipient; }
        public abstract String send(String message);
    }
    static class EmailSender extends NotificationSender {
        public EmailSender(String recipient) { super(recipient); }
        @Override public String send(String message) {
            return "[Email -> " + getRecipient() + "] Subject: ALERT | " + message;
        }
    }
    static class SmsSender extends NotificationSender {
        public SmsSender(String recipient) { super(recipient); }
        @Override public String send(String message) {
            String trimmed = message.length() > 70 ? message.substring(0, 67) + "..." : message;
            return "[SMS -> " + getRecipient() + "] " + trimmed;
        }
    }
    static class TelegramBotSender extends NotificationSender {
        public TelegramBotSender(String recipient) { super(recipient); }
        @Override public String send(String message) {
            return "[TG -> " + getRecipient() + "] ALERT: `" + message + "`";
        }
    }
    public static void broadcastAlert(NotificationSender[] senders, String alertText) {
        for (NotificationSender s : senders) {
            System.out.println("   " + s.send(alertText));
        }
    }

    static abstract class ShippingTariff {
        public abstract double calculateCost(double weightKg, double distanceKm);
    }
    static class RoadTruckTariff extends ShippingTariff {
        @Override public double calculateCost(double w, double d) { return 15.0 * d + 2.5 * w; }
    }
    static class AirCargoTariff extends ShippingTariff {
        @Override public double calculateCost(double w, double d) { return 45.0 * d + 18.0 * w + 500.0; }
    }
    static class RailFreightTariff extends ShippingTariff {
        @Override public double calculateCost(double w, double d) { return 1200.0 + 4.0 * d + 0.8 * w; }
    }

    static abstract class DifferentiableFunction {
        public abstract double f(double x);
        public double derivativeAt(double x) {
            double h = 1e-5;
            return (f(x + h) - f(x - h)) / (2.0 * h);
        }
    }
    static class QuadraticParabola extends DifferentiableFunction {
        private final double a, b, c;
        public QuadraticParabola(double a, double b, double c) { this.a = a; this.b = b; this.c = c; }
        @Override public double f(double x) { return a * x * x + b * x + c; }
    }
    static class NaturalLogFunction extends DifferentiableFunction {
        private final double k;
        public NaturalLogFunction(double k) { this.k = k; }
        @Override public double f(double x) { return x > 0 ? k * Math.log(x) : Double.NaN; }
    }
    static class ExponentialGrowth extends DifferentiableFunction {
        private final double a, lambda;
        public ExponentialGrowth(double a, double lambda) { this.a = a; this.lambda = lambda; }
        @Override public double f(double x) { return a * Math.exp(lambda * x); }
    }

    static abstract class UiWidget {
        protected final String id;
        protected final int width, height;
        public UiWidget(String id, int width, int height) { this.id = id; this.width = width; this.height = height; }
        public abstract String renderHtml();
    }
    static class ButtonWidget extends UiWidget {
        private final String label;
        public ButtonWidget(String id, int w, int h, String label) { super(id, w, h); this.label = label; }
        @Override public String renderHtml() {
            return String.format("<button id='%s' style='width:%dpx;height:%dpx'>%s</button>", id, width, height, label);
        }
    }
    static class TextInputWidget extends UiWidget {
        private final String placeholder;
        public TextInputWidget(String id, int w, int h, String placeholder) { super(id, w, h); this.placeholder = placeholder; }
        @Override public String renderHtml() {
            return String.format("<input id='%s' placeholder='%s' style='width:%dpx;height:%dpx'/>", id, placeholder, width, height);
        }
    }
    static class ProgressBarWidget extends UiWidget {
        private final int progressPercent;
        public ProgressBarWidget(String id, int w, int h, int p) { super(id, w, h); this.progressPercent = Math.max(0, Math.min(100, p)); }
        @Override public String renderHtml() {
            return String.format("<progress id='%s' value='%d' max='100' style='width:%dpx;height:%dpx'></progress>", id, progressPercent, width, height);
        }
    }
    public static String buildHtmlPage(UiWidget[] pageElements) {
        StringBuilder sb = new StringBuilder("<html><body>\n");
        for (UiWidget w : pageElements) sb.append("  ").append(w.renderHtml()).append("\n");
        return sb.append("</body></html>").toString();
    }

    static abstract class IndustrialAsset {
        private final String name;
        protected final double initialCost;
        protected final int usefulLifeYears;
        public IndustrialAsset(String name, double initialCost, int usefulLifeYears) {
            this.name = name; this.initialCost = initialCost; this.usefulLifeYears = usefulLifeYears;
        }
        public String getName() { return name; }
        public abstract double getBookValueAfterYears(int years);
    }
    static class LinearDepreciationAsset extends IndustrialAsset {
        public LinearDepreciationAsset(String name, double cost, int life) { super(name, cost, life); }
        @Override public double getBookValueAfterYears(int years) {
            double factor = Math.max(0.0, 1.0 - (double) years / usefulLifeYears);
            return initialCost * factor;
        }
    }
    static class DecliningBalanceAsset extends IndustrialAsset {
        private final double rate;
        public DecliningBalanceAsset(String name, double cost, int life, double rate) { super(name, cost, life); this.rate = rate; }
        @Override public double getBookValueAfterYears(int years) {
            return initialCost * Math.pow(1.0 - rate, years);
        }
    }

    private static void runBlock2() {
        System.out.println("\n--- БЛОК 2: Інтерфейси, константи, default/static та множинна реалізація (Завдання 9-16) ---");

        Measurable[] items = { new BankVault(120.5), new HighwayRoute(480.0), new CpuCore(4.8) };
        System.out.printf("[Завдання 9] Середнє по Measurable[]: %.2f%n", computeAverage(items));

        EnergyConvertible[] energies = { new KineticBody(1000.0, 20.0), new BatteryCell(5000.0) };
        System.out.printf("[Завдання 10] Енергія тіла: %.4f кВт·год | Батареї: %.4f кВт·год%n",
                energies[0].getEnergyKwh(), energies[1].getEnergyKwh());

        AmphibiousRescueDrone drone = new AmphibiousRescueDrone("50.4501 N, 30.5234 E");
        testFlight(drone);
        testDiving(drone);
        logPosition(drone);

        DataValidator emailVal = new EmailValidator();
        DataValidator numVal = new PositiveNumberValidator();
        System.out.printf("[Завдання 12] Email: '%s' | Number: '%s' | Blank check: %b%n",
                emailVal.validateOrDefault("bad-email", "default@domain.ua"),
                numVal.validateOrDefault("42.5", "0"),
                DataValidator.isNotNullOrBlank("Java 21"));

        EmergencyBeacon beacon = new EmergencyBeacon();
        System.out.println("[Завдання 13] Diamond Problem вирішено: " + beacon.emitAlert());

        EscrowSmartContract contract = new EscrowSmartContract("0xSECRET");
        boolean ok = contract.verifySignature("0xSECRET") && contract.executeContract(21000);
        System.out.println("[Завдання 14] SmartContract виконано: " + ok + " | " + contract.getAuditTrail());

        Transformable2D[] scene = { new BoundingBox(0, 0, 10, 5), new CircleRegion(2, 2, 4) };
        applyGlobalTransform(scene, 5.0, -2.0, 2.0);
        System.out.println("[Завдання 15] Глобальна трансформація застосована до " + scene.length + " об'єктів.");

        DataExportable[] records = {
            new StudentGradeRecord("Богдан", 101, 94.5),
            new WeatherObservation("Чернігів", 12.4, 78)
        };
        System.out.println("[Завдання 16] Експорт у JSON / CSV:");
        for (DataExportable r : records) {
            System.out.println("   JSON: " + r.toJson() + " | CSV: " + r.toCsvRow());
        }
    }

    interface Measurable { double getMeasure(); }
    static class BankVault implements Measurable {
        private final double goldKg;
        public BankVault(double goldKg) { this.goldKg = goldKg; }
        @Override public double getMeasure() { return goldKg; }
    }
    static class HighwayRoute implements Measurable {
        private final double km;
        public HighwayRoute(double km) { this.km = km; }
        @Override public double getMeasure() { return km; }
    }
    static class CpuCore implements Measurable {
        private final double ghz;
        public CpuCore(double ghz) { this.ghz = ghz; }
        @Override public double getMeasure() { return ghz; }
    }
    public static double computeAverage(Measurable[] items) {
        double sum = 0;
        for (Measurable m : items) sum += m.getMeasure();
        return items.length == 0 ? 0 : sum / items.length;
    }

    interface EnergyConvertible {
        double SPEED_OF_LIGHT = 299_792_458.0;
        double JOULES_PER_KWH = 3_600_000.0;
        double getEnergyJoules();
        default double getEnergyKwh() { return getEnergyJoules() / JOULES_PER_KWH; }
    }
    static class KineticBody implements EnergyConvertible {
        private final double massKg, velocityMs;
        public KineticBody(double massKg, double velocityMs) { this.massKg = massKg; this.velocityMs = velocityMs; }
        @Override public double getEnergyJoules() { return 0.5 * massKg * velocityMs * velocityMs; }
    }
    static class BatteryCell implements EnergyConvertible {
        private final double wattHours;
        public BatteryCell(double wattHours) { this.wattHours = wattHours; }
        @Override public double getEnergyJoules() { return wattHours * 3600.0; }
    }

    interface Flyable { void takeOff(); double getMaxAltitude(); }
    interface Swimmable { void dive(double depthMeters); double getMaxDepth(); }
    interface GpsTrackable { String getCoordinates(); }
    static class AmphibiousRescueDrone implements Flyable, Swimmable, GpsTrackable {
        private final String coords;
        public AmphibiousRescueDrone(String coords) { this.coords = coords; }
        @Override public void takeOff() {}
        @Override public double getMaxAltitude() { return 1500.0; }
        @Override public void dive(double depthMeters) {}
        @Override public double getMaxDepth() { return 50.0; }
        @Override public String getCoordinates() { return coords; }
    }
    private static void testFlight(Flyable f) { f.takeOff(); }
    private static void testDiving(Swimmable s) { s.dive(10.0); }
    private static void logPosition(GpsTrackable g) {
        System.out.println("[Завдання 11] Координати амфібійного дрона: " + g.getCoordinates());
    }

    interface DataValidator {
        boolean isValid(String input);
        default String validateOrDefault(String input, String fallback) {
            return isValid(input) ? input : fallback;
        }
        static boolean isNotNullOrBlank(String s) {
            return s != null && !s.isBlank();
        }
    }
    static class EmailValidator implements DataValidator {
        @Override public boolean isValid(String input) {
            return DataValidator.isNotNullOrBlank(input) && input.contains("@") && input.contains(".");
        }
    }
    static class PositiveNumberValidator implements DataValidator {
        @Override public boolean isValid(String input) {
            if (!DataValidator.isNotNullOrBlank(input)) return false;
            try { return Double.parseDouble(input) > 0; } catch (NumberFormatException e) { return false; }
        }
    }

    interface AcousticSignal { default String emitAlert() { return "Звукова сирена 110 дБ"; } }
    interface OpticalSignal { default String emitAlert() { return "Стробоскопічний маячок"; } }
    static class EmergencyBeacon implements AcousticSignal, OpticalSignal {
        @Override public String emitAlert() {
            return AcousticSignal.super.emitAlert() + " + " + OpticalSignal.super.emitAlert();
        }
    }

    interface Authenticatable { boolean verifySignature(String key); }
    interface Auditable { String getAuditTrail(); }
    interface BlockchainSmartContract extends Authenticatable, Auditable { boolean executeContract(double gasLimit); }
    static class EscrowSmartContract implements BlockchainSmartContract {
        private final String validKey;
        private String log = "INIT";
        public EscrowSmartContract(String validKey) { this.validKey = validKey; }
        @Override public boolean verifySignature(String key) {
            boolean ok = validKey.equals(key);
            log += " -> AUTH(" + ok + ")";
            return ok;
        }
        @Override public boolean executeContract(double gasLimit) {
            if (gasLimit < 10000) return false;
            log += " -> EXEC(gas=" + gasLimit + ")";
            return true;
        }
        @Override public String getAuditTrail() { return log; }
    }

    interface Transformable2D {
        void translate(double dx, double dy);
        void scale(double factor);
    }
    static class BoundingBox implements Transformable2D {
        private double x, y, w, h;
        public BoundingBox(double x, double y, double w, double h) { this.x = x; this.y = y; this.w = w; this.h = h; }
        @Override public void translate(double dx, double dy) { x += dx; y += dy; }
        @Override public void scale(double factor) { w *= factor; h *= factor; }
    }
    static class CircleRegion implements Transformable2D {
        private double x, y, r;
        public CircleRegion(double x, double y, double r) { this.x = x; this.y = y; this.r = r; }
        @Override public void translate(double dx, double dy) { x += dx; y += dy; }
        @Override public void scale(double factor) { r *= factor; }
    }
    public static void applyGlobalTransform(Transformable2D[] objects, double dx, double dy, double scaleFactor) {
        for (Transformable2D obj : objects) {
            obj.translate(dx, dy);
            obj.scale(scaleFactor);
        }
    }

    interface DataExportable {
        String toJson();
        String toCsvRow();
    }
    static class StudentGradeRecord implements DataExportable {
        private final String name;
        private final int group;
        private final double gpa;
        public StudentGradeRecord(String name, int group, double gpa) { this.name = name; this.group = group; this.gpa = gpa; }
        @Override public String toJson() { return String.format("{\"name\":\"%s\",\"group\":%d,\"gpa\":%.1f}", name, group, gpa); }
        @Override public String toCsvRow() { return String.format("%s,%d,%.1f", name, group, gpa); }
    }
    static class WeatherObservation implements DataExportable {
        private final String city;
        private final double tempC;
        private final int humidity;
        public WeatherObservation(String city, double tempC, int humidity) { this.city = city; this.tempC = tempC; this.humidity = humidity; }
        @Override public String toJson() { return String.format("{\"city\":\"%s\",\"tempC\":%.1f,\"humidity\":%d}", city, tempC, humidity); }
        @Override public String toCsvRow() { return String.format("%s,%.1f,%d", city, tempC, humidity); }
    }

    private static void runBlock3() {
        System.out.println("\n--- БЛОК 3: abstract class vs interface та синергія extends + implements (Завдання 17-24) ---");

        AbstractBird[] birds = { new Falcon(1.2), new EmperorPenguin(30.0) };
        FlyableUnit[] flyers = { (FlyableUnit) birds[0], new CargoPlane(850.0) };
        System.out.printf("[Завдання 17] Птахів: %d, Літаючих одиниць: %d (швидкість літака: %.0f км/год)%n",
                birds.length, flyers.length, flyers[1].getFlightSpeedKmh());

        AbstractSmartAppliance[] appliances = {
            new SmartLedChandelier("Вітальня", "Philips Hue", 60.0),
            new SmartUnderfloorHeating("Ванна", "DeviSmart", 1200.0)
        };
        double totalWatts = 0;
        for (AbstractSmartAppliance app : appliances) totalWatts += app.getCurrentWatts();
        System.out.printf("[Завдання 18] Сумарне споживання розумного будинку: %.1f Вт%n", totalWatts);

        AbstractFinancialInstrument[] portfolio = {
            new TechGrowthStock("NVDA", 10, 5200.0),
            new BlueChipStock("MSFT", 5, 16000.0, 120.0),
            new FixedTermDeposit("UAH-DEP", 100000.0, 0.14)
        };
        double marketVal = 0;
        for (AbstractFinancialInstrument fi : portfolio) marketVal += fi.getMarketValueUah();
        System.out.printf("[Завдання 19] Загальна вартість портфеля: %.2f грн%n", marketVal);

        Vec2D v = new Vec2D(3.0, 4.0);
        Rotation2DOperator rot = new Rotation2DOperator(Math.PI / 2.0);
        Vec2D rotated = rot.transform(v);
        Vec2D restored = rot.inverseTransform(rotated);
        System.out.printf("[Завдання 20] Поворот (3,4) на 90° -> (%.2f, %.2f), назад -> (%.2f, %.2f)%n",
                rotated.x, rotated.y, restored.x, restored.y);

        AbstractVehicle[] fleet = {
            new ElectricCityBus("AA1111EE", 45, 320.0),
            new DieselDumpTruck("BC2222AA", 25.0),
            new CargoPassengerVan("KA3333BB", 1.5, 6)
        };
        System.out.println("[Завдання 21] Автопарк спецтехніки сформовано: " + fleet.length + " од.");

        AbstractMilitaryUnit[] squad = {
            new HeavySniper("Ghost", 120),
            new CombatMedic("Doc", 140),
            new PaladinCaptain("Uther", 250)
        };
        System.out.println("[Завдання 22] Загін готовий: " + squad.length + " юнітів.");

        AbstractLabDevice[] lab = {
            new ElectronicScale("LAB-01"),
            new GlassThermometer("LAB-02")
        };
        for (AbstractLabDevice d : lab) d.performMeasurement();
        System.out.println("[Завдання 23] Лабораторні вимірювання проведено.");

        TextProcessor[] pipeline = {
            new WhitespaceTrimmerProcessor(),
            new CaesarCipherProcessor(3)
        };
        String text = "  Hello   Java  21  ";
        for (TextProcessor tp : pipeline) text = tp.process(text);
        System.out.println("[Завдання 24] Результат конвеєра тексту: '" + text + "'");
    }

    static abstract class AbstractBird {
        protected final String species;
        protected double weightKg;
        public AbstractBird(String species, double weightKg) { this.species = species; this.weightKg = weightKg; }
        public abstract String makeSound();
    }
    interface FlyableUnit { double getFlightSpeedKmh(); }
    interface SwimmingUnit { double getDiveDepthMeters(); }
    static class Falcon extends AbstractBird implements FlyableUnit {
        public Falcon(double weightKg) { super("Сапсан", weightKg); }
        @Override public String makeSound() { return "Кі-кі-кі!"; }
        @Override public double getFlightSpeedKmh() { return 320.0; }
    }
    static class EmperorPenguin extends AbstractBird implements SwimmingUnit {
        public EmperorPenguin(double weightKg) { super("Імператорський пінгвін", weightKg); }
        @Override public String makeSound() { return "Трубний крик"; }
        @Override public double getDiveDepthMeters() { return 500.0; }
    }
    static class CargoPlane implements FlyableUnit {
        private final double speed;
        public CargoPlane(double speed) { this.speed = speed; }
        @Override public double getFlightSpeedKmh() { return speed; }
    }

    static abstract class AbstractSmartAppliance {
        private final String room, model;
        protected boolean poweredOn = true;
        public AbstractSmartAppliance(String room, String model) { this.room = room; this.model = model; }
        public abstract double getCurrentWatts();
    }
    interface Dimmable { void setBrightness(int percent); }
    interface HeatingCapable { void setTargetTemperature(double celsius); }
    static class SmartLedChandelier extends AbstractSmartAppliance implements Dimmable {
        private final double maxWatts;
        private int brightness = 80;
        public SmartLedChandelier(String room, String model, double maxWatts) { super(room, model); this.maxWatts = maxWatts; }
        @Override public void setBrightness(int percent) { this.brightness = percent; }
        @Override public double getCurrentWatts() { return poweredOn ? maxWatts * (brightness / 100.0) : 0; }
    }
    static class SmartUnderfloorHeating extends AbstractSmartAppliance implements HeatingCapable {
        private final double watts;
        private double targetTemp = 24.0;
        public SmartUnderfloorHeating(String room, String model, double watts) { super(room, model); this.watts = watts; }
        @Override public void setTargetTemperature(double celsius) { this.targetTemp = celsius; }
        @Override public double getCurrentWatts() { return poweredOn ? watts : 0; }
    }

    static abstract class AbstractFinancialInstrument {
        private final String ticker;
        protected int quantity;
        public AbstractFinancialInstrument(String ticker, int quantity) { this.ticker = ticker; this.quantity = quantity; }
        public abstract double getMarketValueUah();
    }
    interface Tradable { boolean buy(int qty); boolean sell(int qty); }
    interface DividendPaying { double calculateAnnualDividend(); }
    static class TechGrowthStock extends AbstractFinancialInstrument implements Tradable {
        private final double price;
        public TechGrowthStock(String ticker, int qty, double price) { super(ticker, qty); this.price = price; }
        @Override public double getMarketValueUah() { return quantity * price; }
        @Override public boolean buy(int qty) { quantity += qty; return true; }
        @Override public boolean sell(int qty) { if (qty > quantity) return false; quantity -= qty; return true; }
    }
    static class BlueChipStock extends AbstractFinancialInstrument implements Tradable, DividendPaying {
        private final double price, divPerShare;
        public BlueChipStock(String ticker, int qty, double price, double divPerShare) {
            super(ticker, qty); this.price = price; this.divPerShare = divPerShare;
        }
        @Override public double getMarketValueUah() { return quantity * price; }
        @Override public boolean buy(int qty) { quantity += qty; return true; }
        @Override public boolean sell(int qty) { if (qty > quantity) return false; quantity -= qty; return true; }
        @Override public double calculateAnnualDividend() { return quantity * divPerShare; }
    }
    static class FixedTermDeposit extends AbstractFinancialInstrument implements DividendPaying {
        private final double principal, rate;
        public FixedTermDeposit(String ticker, double principal, double rate) { super(ticker, 1); this.principal = principal; this.rate = rate; }
        @Override public double getMarketValueUah() { return principal; }
        @Override public double calculateAnnualDividend() { return principal * rate; }
    }

    static class Vec2D {
        final double x, y;
        public Vec2D(double x, double y) { this.x = x; this.y = y; }
    }
    static abstract class AbstractVectorOperator {
        private final String name;
        public AbstractVectorOperator(String name) { this.name = name; }
        public abstract Vec2D transform(Vec2D v);
    }
    interface InvertibleOperator { Vec2D inverseTransform(Vec2D v); }
    static class Rotation2DOperator extends AbstractVectorOperator implements InvertibleOperator {
        private final double alpha;
        public Rotation2DOperator(double alpha) { super("Rotation2D"); this.alpha = alpha; }
        @Override public Vec2D transform(Vec2D v) {
            return new Vec2D(v.x * Math.cos(alpha) - v.y * Math.sin(alpha), v.x * Math.sin(alpha) + v.y * Math.cos(alpha));
        }
        @Override public Vec2D inverseTransform(Vec2D v) {
            double a = -alpha;
            return new Vec2D(v.x * Math.cos(a) - v.y * Math.sin(a), v.x * Math.sin(a) + v.y * Math.cos(a));
        }
    }
    static class ProjectionXOperator extends AbstractVectorOperator {
        public ProjectionXOperator() { super("ProjectionX"); }
        @Override public Vec2D transform(Vec2D v) { return new Vec2D(v.x, 0.0); }
    }

    static abstract class AbstractVehicle {
        private final String plateNumber;
        protected double mileageKm;
        public AbstractVehicle(String plateNumber) { this.plateNumber = plateNumber; }
    }
    interface CargoCarrier { double getMaxPayloadTons(); }
    interface PassengerCarrier { int getSeatCount(); }
    interface ElectricPowered { double getBatteryCapacityKwh(); }
    static class ElectricCityBus extends AbstractVehicle implements PassengerCarrier, ElectricPowered {
        private final int seats; private final double kwh;
        public ElectricCityBus(String plate, int seats, double kwh) { super(plate); this.seats = seats; this.kwh = kwh; }
        @Override public int getSeatCount() { return seats; }
        @Override public double getBatteryCapacityKwh() { return kwh; }
    }
    static class DieselDumpTruck extends AbstractVehicle implements CargoCarrier {
        private final double tons;
        public DieselDumpTruck(String plate, double tons) { super(plate); this.tons = tons; }
        @Override public double getMaxPayloadTons() { return tons; }
    }
    static class CargoPassengerVan extends AbstractVehicle implements CargoCarrier, PassengerCarrier {
        private final double tons; private final int seats;
        public CargoPassengerVan(String plate, double tons, int seats) { super(plate); this.tons = tons; this.seats = seats; }
        @Override public double getMaxPayloadTons() { return tons; }
        @Override public int getSeatCount() { return seats; }
    }

    static abstract class AbstractMilitaryUnit {
        private final String unitName;
        protected int hp;
        public AbstractMilitaryUnit(String unitName, int hp) { this.unitName = unitName; this.hp = hp; }
        public abstract int getMoveSpeed();
    }
    interface RangedAttacker { int getAttackRange(); int fireShot(); }
    interface FieldHealer { int healAlly(); }
    static class HeavySniper extends AbstractMilitaryUnit implements RangedAttacker {
        public HeavySniper(String name, int hp) { super(name, hp); }
        @Override public int getMoveSpeed() { return 4; }
        @Override public int getAttackRange() { return 12; }
        @Override public int fireShot() { return 95; }
    }
    static class CombatMedic extends AbstractMilitaryUnit implements FieldHealer {
        public CombatMedic(String name, int hp) { super(name, hp); }
        @Override public int getMoveSpeed() { return 6; }
        @Override public int healAlly() { return 50; }
    }
    static class PaladinCaptain extends AbstractMilitaryUnit implements RangedAttacker, FieldHealer {
        public PaladinCaptain(String name, int hp) { super(name, hp); }
        @Override public int getMoveSpeed() { return 5; }
        @Override public int getAttackRange() { return 3; }
        @Override public int fireShot() { return 45; }
        @Override public int healAlly() { return 40; }
    }

    static abstract class AbstractLabDevice {
        private final String inventoryCode;
        public AbstractLabDevice(String code) { this.inventoryCode = code; }
        public abstract void performMeasurement();
    }
    interface Calibratable { void calibrate(double referenceValue); }
    interface SelfDiagnostic { boolean runSelfCheck(); }
    static class ElectronicScale extends AbstractLabDevice implements Calibratable, SelfDiagnostic {
        public ElectronicScale(String code) { super(code); }
        @Override public void performMeasurement() {}
        @Override public void calibrate(double ref) {}
        @Override public boolean runSelfCheck() { return true; }
    }
    static class GlassThermometer extends AbstractLabDevice {
        public GlassThermometer(String code) { super(code); }
        @Override public void performMeasurement() {}
    }

    interface TextProcessor { String process(String text); }
    interface ReversibleProcessor extends TextProcessor { String reverseProcess(String text); }
    static class WhitespaceTrimmerProcessor implements TextProcessor {
        @Override public String process(String text) { return text.trim().replaceAll("\\s+", " "); }
    }
    static class CaesarCipherProcessor implements ReversibleProcessor {
        private final int shift;
        public CaesarCipherProcessor(int shift) { this.shift = shift; }
        @Override public String process(String text) { return shiftText(text, shift); }
        @Override public String reverseProcess(String text) { return shiftText(text, -shift); }
        private String shiftText(String s, int k) {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) sb.append((char) (c + k));
            return sb.toString();
        }
    }

    private static void runBlock4() {
        System.out.println("\n--- БЛОК 4: Оператор instanceof та Pattern Matching (Завдання 25-32) ---");

        Shape2D[] shapes = { new CircleShape(5.0), new RectangleShape(4.0, 4.0), null };
        for (Shape2D s : shapes) {
            if (s instanceof CircleShape c) {
                System.out.printf("[Завдання 25] Коло: діаметр = %.1f%n", c.getDiameter());
            } else if (s instanceof RectangleShape r) {
                System.out.printf("[Завдання 25] Прямокутник: діагональ = %.2f, квадрат = %b%n", r.getDiagonal(), r.isSquare());
            }
        }
        Shape2D nullShape = null;
        System.out.println("[Завдання 25] null instanceof Shape2D = " + (nullShape instanceof Shape2D));

        Object[] warehouse = { new Laptop(), new DesktopMonitor(), new ElectricScooter(), new SmartWatch() };
        int chargedCount = chargeAllPossible(warehouse);
        System.out.println("[Завдання 26] Заряджено акумуляторних пристроїв: " + chargedCount);

        AbstractWorker[] staff = {
            new FullTimeDeveloper("Олег", 80000.0, 15000.0, 50000.0),
            new FopContractor("Марія", 90000.0),
            new InternStudent("Тарас", 15000.0, 2000.0)
        };
        auditWorkers(staff);

        ShipModule[] modules = { new ShieldGenerator("Aegis-1"), new WarpDrive("Core-X"), new LifeSupportModule("O2-Bay") };
        runShipInspection(modules);

        MediaItem[] media = {
            new VideoFile("Interstellar.mkv", 45000.0, 2160, 10140),
            new AudioFile("Symphony.flac", 420.0, 1411),
            new EBookFile("CleanCode.epub", 12.0, 464)
        };
        double premiumMediaSize = 0;
        for (MediaItem m : media) {
            if (m instanceof VideoFile v && v.is4K()) premiumMediaSize += v.sizeMb;
            else if (m instanceof AudioFile a && a.isLossless()) premiumMediaSize += a.sizeMb;
        }
        System.out.printf("[Завдання 29] Обсяг 4K + Lossless медіа: %.1f МБ%n", premiumMediaSize);

        AbstractCustomer[] customers = { new VipCustomer("Анна"), new StudentCustomer("Ігор"), new RegularCustomer("Петро") };
        double basePrice = 1000.0;
        System.out.print("[Завдання 30] Поліморфні ціни: ");
        for (AbstractCustomer c : customers) {
            double finalPrice = basePrice * (1.0 - c.getDiscountPercent() / 100.0);
            System.out.printf("%s=%.0f ", c.name, finalPrice);
        }
        System.out.println();

        Matrix2x2[] matrices = {
            new RotationMatrix2x2(Math.PI / 3.0),
            new SingularMatrix2x2()
        };
        int invertibleCount = 0;
        for (Matrix2x2 m : matrices) {
            if (m instanceof InvertibleMatrix inv) {
                Matrix2x2 invM = inv.inverse();
                if (invM != null) invertibleCount++;
            }
        }
        System.out.println("[Завдання 31] Знайдено оборотних матриць через instanceof: " + invertibleCount);

        CargoContainer[] containers = {
            new ElectronicsContainer("CNT-01"),
            new ChemicalContainer("CNT-02", 3),
            new FrozenFoodContainer("CNT-03", -18.0)
        };
        int specialHandled = 0;
        for (CargoContainer c : containers) {
            if (c instanceof HazardousCargo) specialHandled++;
            if (c instanceof RefrigeratedCargo) specialHandled++;
        }
        System.out.println("[Завдання 32] Спецобробка митних контейнерів: " + specialHandled);
    }

    interface Rechargeable { void chargeToFull(); }
    static class Laptop implements Rechargeable { @Override public void chargeToFull() {} }
    static class ElectricScooter implements Rechargeable { @Override public void chargeToFull() {} }
    static class SmartWatch implements Rechargeable { @Override public void chargeToFull() {} }
    static class DesktopMonitor {}
    public static int chargeAllPossible(Object[] items) {
        int count = 0;
        for (Object item : items) {
            if (item instanceof Rechargeable r) {
                r.chargeToFull();
                count++;
            }
        }
        return count;
    }

    static abstract class AbstractWorker {
        final String name; final double baseSalary;
        public AbstractWorker(String name, double baseSalary) { this.name = name; this.baseSalary = baseSalary; }
    }
    interface BonusEligible { double calculateQuarterBonus(); }
    interface MedicalInsured { double getInsuranceCoverage(); }
    static class FullTimeDeveloper extends AbstractWorker implements BonusEligible, MedicalInsured {
        private final double bonus, coverage;
        public FullTimeDeveloper(String n, double s, double b, double c) { super(n, s); this.bonus = b; this.coverage = c; }
        @Override public double calculateQuarterBonus() { return bonus; }
        @Override public double getInsuranceCoverage() { return coverage; }
    }
    static class FopContractor extends AbstractWorker {
        public FopContractor(String n, double s) { super(n, s); }
    }
    static class InternStudent extends AbstractWorker implements BonusEligible {
        private final double bonus;
        public InternStudent(String n, double s, double b) { super(n, s); this.bonus = b; }
        @Override public double calculateQuarterBonus() { return bonus; }
    }
    public static void auditWorkers(AbstractWorker[] staff) {
        double salaryFund = 0, bonusFund = 0;
        int insuredCount = 0;
        for (AbstractWorker w : staff) {
            salaryFund += w.baseSalary;
            if (w instanceof BonusEligible b) bonusFund += b.calculateQuarterBonus();
            if (w instanceof MedicalInsured) insuredCount++;
        }
        System.out.printf("[Завдання 27] ФОП: %.0f грн | Премії: %.0f грн | Застраховано: %d осіб%n",
                salaryFund, bonusFund, insuredCount);
    }

    static abstract class ShipModule {
        final String name; boolean damaged;
        public ShipModule(String name) { this.name = name; }
    }
    static class ShieldGenerator extends ShipModule {
        public ShieldGenerator(String name) { super(name); }
        public void rechargeShields(int mj) {}
    }
    static class WarpDrive extends ShipModule {
        public WarpDrive(String name) { super(name); }
        public boolean checkAntimatterContainment() { return true; }
    }
    static class LifeSupportModule extends ShipModule {
        public LifeSupportModule(String name) { super(name); }
        public double getOxygenPercent() { return 20.9; }
    }
    public static void runShipInspection(ShipModule[] modules) {
        for (ShipModule m : modules) {
            if (m instanceof ShieldGenerator sg) sg.rechargeShields(500);
            else if (m instanceof WarpDrive wd) wd.checkAntimatterContainment();
            else if (m instanceof LifeSupportModule ls) ls.getOxygenPercent();
        }
        System.out.println("[Завдання 28] Інспекцію " + modules.length + " модулів корабля завершено.");
    }

    static abstract class MediaItem {
        final String title; final double sizeMb;
        public MediaItem(String title, double sizeMb) { this.title = title; this.sizeMb = sizeMb; }
    }
    static class VideoFile extends MediaItem {
        final int resolutionHeight, durationSec;
        public VideoFile(String t, double s, int h, int d) { super(t, s); this.resolutionHeight = h; this.durationSec = d; }
        public boolean is4K() { return resolutionHeight >= 2160; }
    }
    static class AudioFile extends MediaItem {
        final int bitrateKbps;
        public AudioFile(String t, double s, int b) { super(t, s); this.bitrateKbps = b; }
        public boolean isLossless() { return bitrateKbps >= 1000; }
    }
    static class EBookFile extends MediaItem {
        final int pageCount;
        public EBookFile(String t, double s, int p) { super(t, s); this.pageCount = p; }
    }

    static abstract class AbstractCustomer {
        final String name;
        public AbstractCustomer(String name) { this.name = name; }
        public abstract double getDiscountPercent();
    }
    static class VipCustomer extends AbstractCustomer {
        public VipCustomer(String name) { super(name); }
        @Override public double getDiscountPercent() { return 20.0; }
    }
    static class StudentCustomer extends AbstractCustomer {
        public StudentCustomer(String name) { super(name); }
        @Override public double getDiscountPercent() { return 10.0; }
    }
    static class RegularCustomer extends AbstractCustomer {
        public RegularCustomer(String name) { super(name); }
        @Override public double getDiscountPercent() { return 0.0; }
    }

    static class Matrix2x2 {
        final double a11, a12, a21, a22;
        public Matrix2x2(double a11, double a12, double a21, double a22) {
            this.a11 = a11; this.a12 = a12; this.a21 = a21; this.a22 = a22;
        }
        public double determinant() { return a11 * a22 - a12 * a21; }
    }
    interface InvertibleMatrix { Matrix2x2 inverse(); }
    interface OrthogonalMatrix { boolean checkOrthogonality(); }
    static class RotationMatrix2x2 extends Matrix2x2 implements InvertibleMatrix, OrthogonalMatrix {
        public RotationMatrix2x2(double theta) {
            super(Math.cos(theta), -Math.sin(theta), Math.sin(theta), Math.cos(theta));
        }
        @Override public Matrix2x2 inverse() { return new Matrix2x2(a11, a21, a12, a22); }
        @Override public boolean checkOrthogonality() { return Math.abs(determinant() - 1.0) < 1e-6; }
    }
    static class SingularMatrix2x2 extends Matrix2x2 {
        public SingularMatrix2x2() { super(1.0, 2.0, 2.0, 4.0); }
    }

    static abstract class CargoContainer {
        final String id;
        public CargoContainer(String id) { this.id = id; }
    }
    interface HazardousCargo { int getHazardClass(); }
    interface RefrigeratedCargo { double getRequiredTempC(); }
    static class ElectronicsContainer extends CargoContainer { public ElectronicsContainer(String id) { super(id); } }
    static class ChemicalContainer extends CargoContainer implements HazardousCargo {
        private final int hazard;
        public ChemicalContainer(String id, int hazard) { super(id); this.hazard = hazard; }
        @Override public int getHazardClass() { return hazard; }
    }
    static class FrozenFoodContainer extends CargoContainer implements RefrigeratedCargo {
        private final double temp;
        public FrozenFoodContainer(String id, double temp) { super(id); this.temp = temp; }
        @Override public double getRequiredTempC() { return temp; }
    }

    private static void runBlock5() {
        System.out.println("\n--- БЛОК 5: Модифікатор final та комплексна синергія (Завдання 33-39) ---");

        double[] raw = {1.0, 2.5, 4.0};
        FinalCalibrationTable table = new FinalCalibrationTable("SN-2026-X", raw);
        raw[0] = 999.0;
        System.out.printf("[Завдання 33] Захисне копіювання final масиву: table[0] = %.1f%n", table.getCoefficient(0));

        AbstractSecurityProtocol jwt = new JwtSecurityProtocol();
        System.out.println("[Завдання 34] Template Method (final): " + jwt.runSecureCommand("Bearer_valid", "DROP_CACHE"));

        MetricSpaceElement v1 = new ImmutableVector3D(1.0, 2.0, 2.0);
        MetricSpaceElement v2 = new ImmutableVector3D(4.0, 6.0, 2.0);
        System.out.printf("[Завдання 35] final ImmutableVector3D: norm=%.1f, dist=%.1f%n", v1.getNorm(), v1.distanceTo(v2));

        PrintableDocument[] docs = {
            new FiscalReceipt("FISCAL-9901", 1200.0),
            new WarehouseReport("Звіт залишків №14")
        };
        System.out.println("[Завдання 36] Друк документів: " + docs[0].formatForPrinter());

        PowerProducer[] grid = {
            new NuclearStation("NPP-1", 1000.0),
            new SolarFarmStation("SPP-1", 200.0),
            new HydroDamStation("HPP-1", 350.0)
        };
        double totalMw = 0;
        for (PowerProducer pp : grid) {
            if (pp instanceof WeatherDependent wd) wd.updateWeatherCondition(0.8);
            totalMw += pp.produceMw();
        }
        System.out.printf("[Завдання 37] Сумарна генерація SmartGrid: %.2f МВт%n", totalMw);

        CubicFunction cubic = new CubicFunction();
        double integral = cubic.integrateTrapezoid(0.0, 3.0, 1000);
        double root = cubic.findRootBisection(2.0, 3.0, 1e-6);
        System.out.printf("[Завдання 38] f(x)=x^3-2x-5: інтеграл[0;3]=%.4f | корінь=%.6f%n", integral, root);

        AbstractCombatUnit[] fighters = {
            new BerserkerAssasin("Варг", 200),
            new TrollWarlord("Зул'джин", 320),
            new RoyalPaladin("Артур", 300)
        };
        simulateBattleRound(fighters[0], fighters[2]);
        simulateBattleRound(fighters[1], fighters[0]);
    }

    static class FinalCalibrationTable {
        public static final double GRAVITY_G = 9.80665;
        private final String sensorSerial;
        private final double[] coefficients;
        public FinalCalibrationTable(String sensorSerial, double[] coefficients) {
            this.sensorSerial = sensorSerial;
            this.coefficients = Arrays.copyOf(coefficients, coefficients.length);
        }
        public double getCoefficient(int idx) { return coefficients[idx]; }
    }

    static abstract class AbstractSecurityProtocol {
        protected abstract boolean authenticateUser(String token);
        protected abstract String executePayload(String command);
        public final String runSecureCommand(String token, String command) {
            if (token == null || !authenticateUser(token)) return "403 FORBIDDEN";
            return "[AUDITED] " + executePayload(command);
        }
    }
    static class JwtSecurityProtocol extends AbstractSecurityProtocol {
        @Override protected boolean authenticateUser(String token) { return token.startsWith("Bearer_"); }
        @Override protected String executePayload(String command) { return "JWT_OK:" + command; }
    }

    interface MetricSpaceElement {
        double distanceTo(MetricSpaceElement other);
        double getNorm();
    }
    public static final class ImmutableVector3D implements MetricSpaceElement {
        private final double x, y, z;
        public ImmutableVector3D(double x, double y, double z) { this.x = x; this.y = y; this.z = z; }
        @Override public double getNorm() { return Math.sqrt(x * x + y * y + z * z); }
        @Override public double distanceTo(MetricSpaceElement other) {
            if (other instanceof ImmutableVector3D v) {
                return Math.sqrt(Math.pow(x - v.x, 2) + Math.pow(y - v.y, 2) + Math.pow(z - v.z, 2));
            }
            return Math.abs(this.getNorm() - other.getNorm());
        }
    }

    interface PrintableDocument { String formatForPrinter(); }
    public static final class FiscalReceipt implements PrintableDocument {
        private final String fiscalNumber;
        private final double totalAmount;
        private final double vatAmount;
        public FiscalReceipt(String fiscalNumber, double totalAmount) {
            this.fiscalNumber = fiscalNumber;
            this.totalAmount = totalAmount;
            this.vatAmount = totalAmount - (totalAmount / 1.20);
        }
        @Override public String formatForPrinter() {
            return String.format("ЧЕК #%s | Сума: %.2f грн (у т.ч. ПДВ 20%%: %.2f грн)", fiscalNumber, totalAmount, vatAmount);
        }
    }
    static class WarehouseReport implements PrintableDocument {
        private final String title;
        public WarehouseReport(String title) { this.title = title; }
        @Override public String formatForPrinter() { return "ЗВІТ: " + title; }
    }

    interface PowerProducer { double produceMw(); }
    interface WeatherDependent { void updateWeatherCondition(double factor); }
    static abstract class AbstractPowerStation implements PowerProducer {
        private final String stationId;
        public AbstractPowerStation(String stationId) { this.stationId = stationId; }
        protected abstract double getRawOutputMw();
        public final double getNetOutputMw() { return getRawOutputMw() * 0.95; }
        @Override public double produceMw() { return getNetOutputMw(); }
    }
    static class NuclearStation extends AbstractPowerStation {
        private final double capacity;
        public NuclearStation(String id, double capacity) { super(id); this.capacity = capacity; }
        @Override protected double getRawOutputMw() { return capacity; }
    }
    static class SolarFarmStation extends AbstractPowerStation implements WeatherDependent {
        private final double peakCapacity;
        private double weatherFactor = 1.0;
        public SolarFarmStation(String id, double peak) { super(id); this.peakCapacity = peak; }
        @Override public void updateWeatherCondition(double factor) { this.weatherFactor = factor; }
        @Override protected double getRawOutputMw() { return peakCapacity * weatherFactor; }
    }
    static class HydroDamStation extends AbstractPowerStation implements WeatherDependent {
        private final double nominalCapacity;
        private double waterFactor = 1.0;
        public HydroDamStation(String id, double cap) { super(id); this.nominalCapacity = cap; }
        @Override public void updateWeatherCondition(double factor) { this.waterFactor = factor; }
        @Override protected double getRawOutputMw() { return nominalCapacity * waterFactor; }
    }

    interface Integrable1D {
        double evaluate(double x);
        default double integrateTrapezoid(double a, double b, int n) {
            double h = (b - a) / n;
            double sum = 0.5 * (evaluate(a) + evaluate(b));
            for (int i = 1; i < n; i++) sum += evaluate(a + i * h);
            return sum * h;
        }
    }
    interface RootFindable { double findRootBisection(double left, double right, double eps); }
    static abstract class AbstractAnalyticFunction implements Integrable1D {}
    static class CubicFunction extends AbstractAnalyticFunction implements RootFindable {
        @Override public double evaluate(double x) { return x * x * x - 2.0 * x - 5.0; }
        @Override public double findRootBisection(double left, double right, double eps) {
            double a = left, b = right;
            while ((b - a) / 2.0 > eps) {
                double mid = (a + b) / 2.0;
                if (evaluate(a) * evaluate(mid) <= 0) b = mid;
                else a = mid;
            }
            return (a + b) / 2.0;
        }
    }

    static abstract class AbstractCombatUnit {
        private final String name;
        protected int hp;
        public AbstractCombatUnit(String name, int hp) { this.name = name; this.hp = hp; }
        public String getName() { return name; }
        public final boolean isAlive() { return hp > 0; }
        public abstract int calculateDamage();
    }
    interface CriticalStriker { double getCritMultiplier(); }
    interface SelfRegenerating { int regenerateHp(); }
    interface ShieldBearer { int absorbDamage(int incoming); }
    static class BerserkerAssasin extends AbstractCombatUnit implements CriticalStriker {
        public BerserkerAssasin(String name, int hp) { super(name, hp); }
        @Override public int calculateDamage() { return 60; }
        @Override public double getCritMultiplier() { return 2.0; }
    }
    static class TrollWarlord extends AbstractCombatUnit implements CriticalStriker, SelfRegenerating {
        public TrollWarlord(String name, int hp) { super(name, hp); }
        @Override public int calculateDamage() { return 50; }
        @Override public double getCritMultiplier() { return 1.5; }
        @Override public int regenerateHp() { hp += 20; return 20; }
    }
    static class RoyalPaladin extends AbstractCombatUnit implements ShieldBearer, SelfRegenerating {
        public RoyalPaladin(String name, int hp) { super(name, hp); }
        @Override public int calculateDamage() { return 45; }
        @Override public int absorbDamage(int incoming) { return (int) (incoming * 0.7); }
        @Override public int regenerateHp() { hp += 15; return 15; }
    }
    public static void simulateBattleRound(AbstractCombatUnit attacker, AbstractCombatUnit defender) {
        if (attacker instanceof SelfRegenerating reg) reg.regenerateHp();
        int dmg = attacker.calculateDamage();
        if (attacker instanceof CriticalStriker crit) dmg = (int) (dmg * crit.getCritMultiplier());
        if (defender instanceof ShieldBearer shield) dmg = shield.absorbDamage(dmg);
        defender.hp = Math.max(0, defender.hp - dmg);
        System.out.printf("[Завдання 39] %s атакує %s на %d шкоди (Залишок HP: %d)%n",
                attacker.getName(), defender.getName(), dmg, defender.hp);
    }
}
