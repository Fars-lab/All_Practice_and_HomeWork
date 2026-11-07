package semester1.practice09;

import java.util.Arrays;
import java.util.Locale;

/** Практична робота №9: інкапсуляція, композиція, абстрактні класи (40 завдань). */
public class Practice09 {

    static String f(String fmt, Object... args) {
        return String.format(Locale.US, fmt, args);
    }

    static void title(String text) {
        System.out.println();
        System.out.println("────────── " + text + " ──────────");
    }

    public static void main(String[] args) {
        System.out.println("========== ПРАКТИЧНА РОБОТА №9 ==========");
        title("БЛОК 1: Інкапсуляція та захисне копіювання");
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        title("БЛОК 2: Композиція та агрегація (HAS-A)");
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        title("БЛОК 3: Композиція з масивами об'єктів (HAS-MANY)");
        task17();
        task18();
        task19();
        task20();
        task21();
        task22();
        task23();
        task24();
        title("БЛОК 4: Абстрактні класи та Шаблонний метод");
        task25();
        task26();
        task27();
        task28();
        task29();
        task30();
        task31();
        task32();
        title("БЛОК 5: Інкапсуляція + Композиція + Абстрактні класи");
        task33();
        task34();
        task35();
        task36();
        task37();
        task38();
        task39();
        task40();
    }

    // ======================== ЗАВДАННЯ ========================

    private static void task1() {
        ImmutableGeoPoint kyiv = new ImmutableGeoPoint(50.4501, 30.5234);
        System.out.println("[1] " + kyiv + " -> зсув: " + kyiv.shift(0.5, -0.5) + " | оригінал: " + kyiv
                + " | кламп: " + new ImmutableGeoPoint(120, -300));
    }

    private static void task2() {
        double[] ext = {1.1, 2.2, 3.3};
        LabMeasurement lab = new LabMeasurement("Титрування", ext);
        ext[0] = 999;
        lab.getSamples()[1] = -999;
        System.out.println("[2] " + lab + " (зовнішній масив: " + Arrays.toString(ext) + ")");
    }

    private static void task3() {
        CryptoVault vault = new CryptoVault("bc1qxyz", "alpha beta gamma", 1.5);
        System.out.println("[3] send у замкненому: " + vault.sendBtc(0.5, "bc1dest")
                + " | unlock(невірна): " + vault.unlock("x")
                + " | unlock(вірна): " + vault.unlock("alpha beta gamma")
                + " | send 0.5: " + vault.sendBtc(0.5, "bc1dest") + " | " + vault);
    }

    private static void task4() {
        ClosedInterval ci = new ClosedInterval(10, 2);
        System.out.println("[4] " + ci + " | перетин з [9; 20]: " + ci.overlaps(new ClosedInterval(9, 20))
                + " | перетин з [11; 20]: " + ci.overlaps(new ClosedInterval(11, 20)));
    }

    private static void task5() {
        RgbColor orange = new RgbColor(255, 140, 300);
        System.out.println("[5] " + orange + " | інверсія: " + orange.invert() + " | мікс із синім: "
                + orange.mixWith(new RgbColor(0, 0, 255)));
    }

    private static void task6() {
        HighSecuritySafe safe = new HighSecuritySafe(1234, 1000);
        System.out.println("[6] take(1234,300): " + safe.takeCash(1234, 300)
                + " | 3 хибні PIN: " + safe.takeCash(1, 10) + safe.takeCash(2, 10) + safe.takeCash(3, 10)
                + " | правильний PIN після блокування: " + safe.takeCash(1234, 10) + " | " + safe);
    }

    private static void task7() {
        EncapsulatedPolynomial poly = new EncapsulatedPolynomial(1, -2, 0, 3);
        System.out.println("[7] " + poly + " | степінь: " + poly.getDegree() + " | P(2) = " + f("%.2f", poly.evaluate(2))
                + " | c1 = " + poly.getCoefficient(1) + " | c9 = " + poly.getCoefficient(9));
    }

    private static void task8() {
        String[] allergies = {"Пеніцилін", "Арахіс"};
        MedicalRecord record = new MedicalRecord("P-001", "Іваненко Іван", 2, true, allergies);
        allergies[0] = "ВИТІК";
        record.getAllergies()[1] = "ВИТІК";
        System.out.println("[8] " + record + " | алергія на 'пеніцилін': " + record.hasAllergyTo("пеніцилін"));
    }

    private static void task9() {
        CompositeCircle circle = new CompositeCircle(0, 0, 5);
        System.out.println("[9] " + circle + " | (3;4) всередині: " + circle.containsPoint(new Point2D(3, 4))
                + " | (4;4) всередині: " + circle.containsPoint(new Point2D(4, 4)));
    }

    private static void task10() {
        Automobile car = new Automobile("Tesla-like", "електро", 1500);
        car.accelerate(20);
        car.startEngine();
        car.accelerate(60);
        System.out.println("[10] " + car);
    }

    private static void task11() {
        SmartDevice phone = new SmartDevice(new Battery(5000, 10), new Display(6.7, 90));
        phone.watchVideo(30);
        System.out.println("[11] після 30 хв: " + phone);
        phone.watchVideo(60);
        System.out.println("[11] після ще 60 хв: " + phone);
    }

    private static void task12() {
        Point2D a = new Point2D(0, 0);
        Point2D b = new Point2D(6, 8);
        LineSegment seg = new LineSegment(a, b);
        a.translate(100, 100);
        System.out.println("[12] " + seg + " | середина: " + seg.getMidpoint() + " (зовнішня a змінена: " + a + ")");
    }

    private static void task13() {
        BankAccount family = new BankAccount("UA00FAMILY", 1000);
        PlasticCard dad = new PlasticCard("4000-0001", "12/28", family);
        PlasticCard mom = new PlasticCard("4000-0002", "01/29", family);
        System.out.println("[13] тато платить 400: " + dad.payAtTerminal(400) + " | мама платить 700: " + mom.payAtTerminal(700)
                + " | мама платить 500: " + mom.payAtTerminal(500) + " | " + family);
        dad.block();
        System.out.println("[13] заблокована картка тата платить 10: " + dad.payAtTerminal(10));
    }

    private static void task14() {
        HeroCharacter hero = new HeroCharacter("Артас", 20);
        hero.equip(new WeaponItem("Frostmourne", 30, 20));
        System.out.println("[14] удари: " + hero.attack() + ", " + hero.attack() + ", " + hero.attack()
                + " (міцність впала до 0) | " + hero);
        hero.unequip();
        System.out.println("[14] без зброї: " + hero.attack());
    }

    private static void task15() {
        ParcelOrder order = new ParcelOrder("NP-1001", 2.5, new DeliveryAddress("Київ", "Хрещатик", 12));
        System.out.println("[15] зміна адреси до відправки: " + order.changeDestination(new DeliveryAddress("Львів", "Городоцька", 3)));
        order.markAsShipped();
        System.out.println("[15] зміна адреси після відправки: " + order.changeDestination(new DeliveryAddress("Одеса", "Дерибасівська", 7))
                + " | " + order);
    }

    private static void task16() {
        TemperatureSensor sensor = new TemperatureSensor(18);
        ClimateController climate = new ClimateController(sensor, new HeatingUnit(2.0), 22);
        climate.regulate();
        System.out.println("[16] 18°C: " + climate);
        sensor.updateReading(22.2);
        climate.regulate();
        System.out.println("[16] 22.2°C: " + climate);
    }

    private static void task17() {
        Polyline line = new Polyline(5);
        line.addVertex(0, 0);
        line.addVertex(3, 0);
        line.addVertex(3, 4);
        System.out.println("[17] " + line + " | довжина: " + f("%.2f", line.getTotalLength())
                + " | замкнений периметр: " + f("%.2f", line.getClosedPerimeter()));
    }

    private static void task18() {
        ShoppingOrder order = new ShoppingOrder();
        order.addItem("Ноутбук", 32000, 1);
        order.addItem("Миша", 650, 2);
        order.addItem("миша", 650, 1);
        System.out.println("[18]\n" + order);
    }

    private static void task19() {
        Bookshelf shelf = new Bookshelf("Фантастика", 3);
        shelf.putBook(new BookItem("Дюна", "Ф. Герберт", 704));
        shelf.putBook(new BookItem("Соляріс", "С. Лем", 224));
        System.out.println("[19] " + shelf + " | найтовща: " + shelf.findLongestBook() + " | сторінок: " + shelf.getTotalPagesOnShelf()
                + " | додати null: " + shelf.putBook(null));
    }

    private static void task20() {
        Playlist playlist = new Playlist("Фокус", 5);
        playlist.addTrack(new AudioTrack("Intro", "Artist A", 185));
        playlist.addTrack(new AudioTrack("Flow", "Artist B", 242));
        System.out.println("[20] " + playlist);
    }

    private static void task21() {
        DataCenterCluster cluster = new DataCenterCluster("Frankfurt", 3);
        cluster.addNode(new ServerNode("10.0.0.1", 64, 60, true));
        cluster.addNode(new ServerNode("10.0.0.2", 128, 0, false));
        cluster.addNode(new ServerNode("10.0.0.3", 32, 8, true));
        System.out.println("[21] " + cluster);
        cluster.deployContainer(16);
        cluster.deployContainer(100);
    }

    private static void task22() {
        UniversityCourse course = new UniversityCourse("ООП на Java", 3);
        course.addModule(new CourseModule("Інкапсуляція", 10, 20, 30));
        course.addModule(new CourseModule("Композиція", 10, 20, 30));
        course.addModule(new CourseModule("Абстракція", 10, 20, 40));
        System.out.println("[22] " + course);
    }

    private static void task23() {
        PassengerTrain train = new PassengerTrain("Київ — Львів", 3);
        train.attachCarriage(new TrainCarriage(1, "Інтерсіті", 60, 50));
        train.attachCarriage(new TrainCarriage(2, "Купе", 36, 10));
        System.out.println("[23] " + train + " | вільних: " + train.getTotalFreeSeats()
                + " | бронь 5 у вагоні 1: " + train.bookTicketsInCarriage(1, 5)
                + " | бронь 20 у вагоні 1: " + train.bookTicketsInCarriage(1, 20)
                + " | вільних тепер: " + train.getTotalFreeSeats());
    }

    private static void task24() {
        EquationSystem system = new EquationSystem(2);
        system.addEquation(new LinearEquation(1, 1, 5));
        system.addEquation(new LinearEquation(1, -1, 1));
        System.out.println("[24] (3; 2): " + system.checkSolution(3, 2) + " | (2; 3): " + system.checkSolution(2, 3));
    }

    private static void task25() {
        AbstractShape[] shapes = {new CircleShape("Червоний", 2), new RectangleShape(null, 3, 4)};
        for (AbstractShape s : shapes) {
            System.out.println("[25] " + s);
        }
    }

    private static void task26() {
        AbstractPowerPlant[] plants = {new SolarPowerPlant("Сонячна-1", 100, 0.6), new WindPowerPlant("Вітрова-1", 50, 9),
                new WindPowerPlant("Вітрова-штиль", 50, 1.5)};
        for (AbstractPowerPlant p : plants) {
            System.out.println("[26] " + p.getStationName() + ": " + f("%.2f МВт, прибуток за добу: %.2f", p.generateMegawatts(),
                    p.calculateDailyProfit(80)));
        }
    }

    private static void task27() {
        AbstractEmployee[] staff = {new SalariedEmployee(1, "Олена", 40000, 5000), new HourlyContractor(2, "Максим", 180, 300)};
        for (AbstractEmployee e : staff) {
            System.out.println("[27] " + e);
        }
    }

    private static void task28() {
        AbstractSequence[] seqs = {new HarmonicSequence(), new SquareNumbersSequence()};
        for (AbstractSequence s : seqs) {
            System.out.println("[28] " + s.getTitle() + ": сума перших 10 = " + f("%.4f", s.sumFirstN(10)));
        }
    }

    private static void task29() {
        AbstractTransaction[] txs = {new DomesticTransfer("TX-1", 500), new DomesticTransfer("TX-2", 10000),
                new CryptoTransfer("TX-3", 2000, 35)};
        for (AbstractTransaction t : txs) {
            System.out.println("[29] " + t);
        }
    }

    private static void task30() {
        Solid3D[] solids = {new Sphere3D("Сталь", 7850, 0.1), new Cylinder3D("Золото", 19320, 0.05, 0.2)};
        for (Solid3D s : solids) {
            System.out.println("[30] " + s);
        }
    }

    private static void task31() {
        AbstractSensor[] sensors = {new PressureSensor("Реактор-1", 1.0, 5.0, 4.0, 1.5), new RadiationSensor("Зал-3", 0, 0.5, 0.12)};
        for (AbstractSensor s : sensors) {
            System.out.println("[31] " + s + " | тривога: " + s.isAlarmTriggered());
        }
    }

    private static void task32() {
        AbstractCompressor[] compressors = {new ZipCompressor(), new UltraLzmaCompressor()};
        for (AbstractCompressor c : compressors) {
            System.out.println("[32] " + c.getAlgorithmName() + ": 10000 -> " + c.estimateCompressedSize(10000) + " байт, стиснення "
                    + f("%.1f%%", c.getCompressionRatio(10000)));
        }
    }

    private static void task33() {
        DesktopPC pc = new DesktopPC(750, 4);
        pc.addPart(new ProcessorPart("Ryzen 9 7950X", 22000, 16, 170));
        pc.addPart(new VideoCardPart("RTX 4070", 25000, 12, 200));
        System.out.println("[33] " + pc);
        pc.addPart(new VideoCardPart("RTX 4090", 90000, 24, 450));
        System.out.println("[33] після додавання RTX 4090: " + pc);
    }

    private static void task34() {
        Spacecraft ship = new Spacecraft("Odyssey", 20, 5);
        ship.addThruster(new ChemicalThruster("C-1"));
        ship.addThruster(new ChemicalThruster("C-2"));
        ship.addThruster(new IonThruster("I-1"));
        System.out.println("[34] " + ship);
    }

    private static void task35() {
        LandPlot plot = new LandPlot(1000, 5);
        System.out.println("[35] будинок 20x15: " + plot.addStructure(new RectangleShape("Сірий", 20, 15))
                + " | клумба r=5: " + plot.addStructure(new CircleShape("Зелений", 5))
                + " | гараж 20x15: " + plot.addStructure(new RectangleShape("Сірий", 20, 15))
                + " | вільний газон: " + f("%.2f", plot.getFreeGreenArea()) + " м²");
    }

    private static void task36() {
        InvestmentPortfolio portfolio = new InvestmentPortfolio("Олег", 5);
        portfolio.addAsset(new GovernmentBond("UA-BOND", 100000));
        portfolio.addAsset(new DividendStock("DIV-1", 50000, 0.08, 0.05));
        System.out.println("[36] " + portfolio);
    }

    private static void task37() {
        RaidParty party = new RaidParty("Фенікс", 5);
        party.addMember(new ArcaneMage("Мерлін", 600, 80, 60));
        System.out.println("[37] без танка: готові до рейду (складність 300)? " + party.isReadyForRaid(300));
        party.addMember(new TankWarrior("Брутус", 2000, 120));
        System.out.println("[37] " + party + " | готові (300)? " + party.isReadyForRaid(300) + " | готові (2000)? "
                + party.isReadyForRaid(2000));
    }

    private static void task38() {
        SmartLab lab = new SmartLab(3);
        PressureSensor pressure = new PressureSensor("Камера А", 1.0, 5.0, 2.0, 1.0);
        RadiationSensor radiation = new RadiationSensor("Склад", 0, 0.5, 0.1);
        lab.addSensor(pressure);
        lab.addSensor(radiation);
        System.out.println("[38] Діагностика №1:");
        lab.runDiagnostics();
        radiation.updateBackground(2.3);
        System.out.println("[38] Діагностика №2 (підвищений фон):");
        lab.runDiagnostics();
        System.out.println("[38] Сирена вмикнена: " + lab.isSirenSounding());
    }

    private static void task39() {
        SignalProcessor processor = new SignalProcessor(3);
        processor.addFilter(new AmplifierFilter(3.0));
        processor.addFilter(new ClippingFilter(5.0));
        double[] input = {0.5, -1.0, 2.5, -4.0};
        double[] output = processor.process(input);
        System.out.println("[39] вхід: " + Arrays.toString(input) + " -> вихід: " + Arrays.toString(output));
    }

    // Завдання 40: підсумок та команди для Git
    private static void task40() {
        System.out.println("[40] Усі завдання 1–39 виконано. Далі виконайте у терміналі в корені проєкту:");
        System.out.println("     git status");
        System.out.println("     git add src/main/java/semester1/practice09/");
        System.out.println("     git commit -m \"feat(practice09): complete 40 tasks on encapsulation, composition and abstract classes\"");
        System.out.println("     git push origin main");
    }

    // ======================== КЛАСИ ЗАВДАНЬ ========================

    // ===== Завдання 1 =====
    static final class ImmutableGeoPoint {
        private final double latitude;
        private final double longitude;

        public ImmutableGeoPoint(double lat, double lon) {
            this.latitude = Math.max(-90.0, Math.min(90.0, lat));
            this.longitude = Math.max(-180.0, Math.min(180.0, lon));
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public ImmutableGeoPoint shift(double dLat, double dLon) {
            return new ImmutableGeoPoint(latitude + dLat, longitude + dLon);
        }

        @Override
        public String toString() {
            return f("GeoPoint(%.4f°, %.4f°)", latitude, longitude);
        }
    }

    // ===== Завдання 2 =====
    static class LabMeasurement {
        private final String experimentName;
        private final double[] samples;

        public LabMeasurement(String experimentName, double[] samples) {
            this.experimentName = (experimentName == null || experimentName.isBlank()) ? "Без назви" : experimentName;
            this.samples = (samples == null) ? new double[0] : Arrays.copyOf(samples, samples.length);
        }

        public double[] getSamples() {
            return Arrays.copyOf(samples, samples.length);
        }

        @Override
        public String toString() {
            return "LabMeasurement{" + experimentName + ", samples=" + Arrays.toString(samples) + "}";
        }
    }

    // ===== Завдання 3 =====
    static class CryptoVault {
        private final String walletAddress;
        private final String secretRecoveryPhrase;
        private double btcBalance;
        private boolean locked;

        public CryptoVault(String walletAddress, String secretRecoveryPhrase, double initialBalance) {
            this.walletAddress = walletAddress;
            this.secretRecoveryPhrase = secretRecoveryPhrase;
            this.btcBalance = Math.max(0, initialBalance);
            this.locked = true;
        }

        public boolean unlock(String phrase) {
            if (secretRecoveryPhrase != null && secretRecoveryPhrase.equals(phrase)) {
                locked = false;
                return true;
            }
            return false;
        }

        public void lock() {
            locked = true;
        }

        public boolean sendBtc(double amount, String destination) {
            if (locked || amount <= 0 || btcBalance < amount || destination == null || destination.isBlank()) {
                return false;
            }
            btcBalance -= amount;
            return true;
        }

        @Override
        public String toString() {
            return f("CryptoVault{address='%s', %s, balance=%.4f BTC}", walletAddress,
                    locked ? "ЗАБЛОКОВАНО" : "розблоковано", btcBalance);
        }
    }

    // ===== Завдання 4 =====
    static class ClosedInterval {
        private double start;
        private double end;

        public ClosedInterval(double a, double b) {
            resize(a, b);
        }

        public void resize(double newStart, double newEnd) {
            if (newStart > newEnd) {
                this.start = newEnd;
                this.end = newStart;
            } else {
                this.start = newStart;
                this.end = newEnd;
            }
        }

        public double getLength() {
            return end - start;
        }

        public double getMidpoint() {
            return (start + end) / 2.0;
        }

        public boolean overlaps(ClosedInterval other) {
            return other != null && this.start <= other.end && other.start <= this.end;
        }

        @Override
        public String toString() {
            return f("[%.2f; %.2f] (центр: %.2f, довжина: %.2f)", start, end, getMidpoint(), getLength());
        }
    }

    // ===== Завдання 5 =====
    static final class RgbColor {
        private final int red;
        private final int green;
        private final int blue;

        public RgbColor(int red, int green, int blue) {
            this.red = clamp(red);
            this.green = clamp(green);
            this.blue = clamp(blue);
        }

        private static int clamp(int val) {
            return Math.max(0, Math.min(255, val));
        }

        public String toHex() {
            return String.format("#%02X%02X%02X", red, green, blue);
        }

        public RgbColor invert() {
            return new RgbColor(255 - red, 255 - green, 255 - blue);
        }

        public RgbColor mixWith(RgbColor other) {
            if (other == null) {
                return this;
            }
            return new RgbColor((red + other.red) / 2, (green + other.green) / 2, (blue + other.blue) / 2);
        }

        @Override
        public String toString() {
            return "RgbColor(" + red + ", " + green + ", " + blue + ") " + toHex();
        }
    }

    // ===== Завдання 6 =====
    static class HighSecuritySafe {
        private final int pinCode;
        private double storedCash;
        private int failedAttempts;
        private boolean permanentlyBlocked;

        public HighSecuritySafe(int pinCode, double initialCash) {
            this.pinCode = pinCode;
            this.storedCash = Math.max(0, initialCash);
        }

        private boolean checkAccess(int enteredPin) {
            if (permanentlyBlocked) {
                return false;
            }
            if (enteredPin != pinCode) {
                failedAttempts++;
                if (failedAttempts >= 3) {
                    permanentlyBlocked = true;
                }
                return false;
            }
            failedAttempts = 0;
            return true;
        }

        public boolean putCash(int pin, double amount) {
            if (!checkAccess(pin) || amount <= 0) {
                return false;
            }
            storedCash += amount;
            return true;
        }

        public boolean takeCash(int pin, double amount) {
            if (!checkAccess(pin) || amount <= 0 || amount > storedCash) {
                return false;
            }
            storedCash -= amount;
            return true;
        }

        public boolean isPermanentlyBlocked() {
            return permanentlyBlocked;
        }

        @Override
        public String toString() {
            return f("HighSecuritySafe{%s, невдалих спроб: %d}", permanentlyBlocked ? "НАЗАВЖДИ ЗАБЛОКОВАНО" : "активний",
                    failedAttempts);
        }
    }

    // ===== Завдання 7 =====
    static class EncapsulatedPolynomial {
        private final double[] coeffs;

        public EncapsulatedPolynomial(double... coeffs) {
            this.coeffs = (coeffs == null || coeffs.length == 0) ? new double[]{0.0} : Arrays.copyOf(coeffs, coeffs.length);
        }

        public int getDegree() {
            for (int i = coeffs.length - 1; i > 0; i--) {
                if (coeffs[i] != 0) {
                    return i;
                }
            }
            return 0;
        }

        public double getCoefficient(int power) {
            return (power >= 0 && power < coeffs.length) ? coeffs[power] : 0.0;
        }

        public double evaluate(double x) {
            double result = 0;
            for (int i = coeffs.length - 1; i >= 0; i--) {
                result = result * x + coeffs[i];
            }
            return result;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("P(x) = ");
            boolean first = true;
            for (int i = coeffs.length - 1; i >= 0; i--) {
                double c = coeffs[i];
                if (c == 0) {
                    continue;
                }
                if (!first) {
                    sb.append(c > 0 ? " + " : " - ");
                } else if (c < 0) {
                    sb.append("-");
                }
                double abs = Math.abs(c);
                if (i == 0) {
                    sb.append(f("%.2f", abs));
                } else {
                    sb.append(f("%.2f", abs)).append("x");
                    if (i > 1) {
                        sb.append("^").append(i);
                    }
                }
                first = false;
            }
            return first ? "P(x) = 0" : sb.toString();
        }
    }

    // ===== Завдання 8 =====
    static class MedicalRecord {
        private final String patientId;
        private String fullName;
        private int bloodGroup;
        private boolean rhPositive;
        private String[] allergies;

        public MedicalRecord(String patientId, String fullName, int bloodGroup, boolean rhPositive, String[] allergies) {
            this.patientId = patientId;
            this.fullName = fullName;
            this.bloodGroup = Math.max(1, Math.min(4, bloodGroup));
            this.rhPositive = rhPositive;
            this.allergies = (allergies == null) ? new String[0] : Arrays.copyOf(allergies, allergies.length);
        }

        public String[] getAllergies() {
            return Arrays.copyOf(allergies, allergies.length);
        }

        public boolean hasAllergyTo(String substance) {
            if (substance == null) {
                return false;
            }
            for (String a : allergies) {
                if (a != null && a.equalsIgnoreCase(substance)) {
                    return true;
                }
            }
            return false;
        }

        private String bloodToString() {
            String[] names = {"I (O)", "II (A)", "III (B)", "IV (AB)"};
            return names[bloodGroup - 1] + (rhPositive ? " Rh+" : " Rh-");
        }

        @Override
        public String toString() {
            return "MedicalRecord{id=" + patientId + ", " + fullName + ", кров: " + bloodToString()
                    + ", алергії: " + Arrays.toString(allergies) + "}";
        }
    }

    // ===== Завдання 9 =====
    static class Point2D {
        private double x;
        private double y;

        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public void translate(double dx, double dy) {
            x += dx;
            y += dy;
        }

        public double distanceTo(Point2D other) {
            return Math.hypot(x - other.x, y - other.y);
        }

        @Override
        public String toString() {
            return f("(%.2f; %.2f)", x, y);
        }
    }

    static class CompositeCircle {
        private double radius;
        private final Point2D center;

        public CompositeCircle(double centerX, double centerY, double radius) {
            this.center = new Point2D(centerX, centerY);
            this.radius = (radius > 0) ? radius : 1.0;
        }

        public boolean containsPoint(Point2D p) {
            return p != null && center.distanceTo(p) <= radius;
        }

        @Override
        public String toString() {
            return "CompositeCircle{center=" + center + ", r=" + f("%.2f", radius) + "}";
        }
    }

    // ===== Завдання 10 =====
    static class Engine {
        private final String fuelType;
        private final int volumeCc;
        private int rpm;
        private boolean started;

        public Engine(String fuelType, int volumeCc) {
            this.fuelType = fuelType;
            this.volumeCc = Math.max(500, volumeCc);
        }

        public void start() {
            started = true;
            rpm = 800;
        }

        public void stop() {
            started = false;
            rpm = 0;
        }

        public void setRpm(int rpm) {
            this.rpm = started ? Math.max(800, Math.min(7000, rpm)) : 0;
        }

        public boolean isStarted() {
            return started;
        }

        public int getRpm() {
            return rpm;
        }

        @Override
        public String toString() {
            return "Engine[" + fuelType + ", " + volumeCc + " cc, rpm=" + rpm + ", " + (started ? "ON" : "OFF") + "]";
        }
    }

    static class Automobile {
        private final String model;
        private double speed;
        private final Engine engine;

        public Automobile(String model, String fuelType, int volumeCc) {
            this.model = model;
            this.engine = new Engine(fuelType, volumeCc);
        }

        public void startEngine() {
            engine.start();
        }

        public void accelerate(double deltaSpeed) {
            if (!engine.isStarted()) {
                System.out.println("    ❌ " + model + ": двигун не заведено, розгін неможливий!");
                return;
            }
            if (deltaSpeed <= 0) {
                return;
            }
            speed += deltaSpeed;
            engine.setRpm(800 + (int) (speed * 40));
        }

        @Override
        public String toString() {
            return f("Automobile{%s, %.0f км/год, %s}", model, speed, engine);
        }
    }

    // ===== Завдання 11 =====
    static class Battery {
        private final int capacityMah;
        private int chargePercent;

        public Battery(int capacityMah, int chargePercent) {
            this.capacityMah = Math.max(1000, capacityMah);
            this.chargePercent = Math.max(0, Math.min(100, chargePercent));
        }

        public void drain(int percent) {
            chargePercent = Math.max(0, chargePercent - Math.max(0, percent));
        }

        public void charge(int percent) {
            chargePercent = Math.min(100, chargePercent + Math.max(0, percent));
        }

        public int getChargePercent() {
            return chargePercent;
        }

        @Override
        public String toString() {
            return capacityMah + " mAh, " + chargePercent + "%";
        }
    }

    static class Display {
        private final double diagonalInches;
        private int brightnessPercent;

        public Display(double diagonalInches, int brightnessPercent) {
            this.diagonalInches = diagonalInches;
            this.brightnessPercent = Math.max(10, Math.min(100, brightnessPercent));
        }

        public int getBrightnessPercent() {
            return brightnessPercent;
        }

        @Override
        public String toString() {
            return f("%.1f\", яскравість %d%%", diagonalInches, brightnessPercent);
        }
    }

    static class SmartDevice {
        private final Battery battery;
        private final Display display;
        private boolean poweredOn = true;

        public SmartDevice(Battery battery, Display display) {
            if (battery == null || display == null) {
                throw new IllegalArgumentException("Компоненти не можуть бути null");
            }
            this.battery = battery;
            this.display = display;
        }

        public void watchVideo(int minutes) {
            if (!poweredOn || minutes <= 0) {
                return;
            }
            int perTen = display.getBrightnessPercent() > 70 ? 2 : 1;
            battery.drain((minutes / 10) * perTen);
            if (battery.getChargePercent() == 0) {
                poweredOn = false;
            }
        }

        @Override
        public String toString() {
            return "SmartDevice{" + (poweredOn ? "ON" : "OFF") + ", батарея: " + battery + ", дисплей: " + display + "}";
        }
    }

    // ===== Завдання 12 =====
    static class LineSegment {
        private final Point2D start;
        private final Point2D end;

        public LineSegment(Point2D start, Point2D end) {
            if (start == null || end == null) {
                throw new IllegalArgumentException("Кінці відрізка не можуть бути null");
            }
            this.start = new Point2D(start.getX(), start.getY());
            this.end = new Point2D(end.getX(), end.getY());
        }

        public double getLength() {
            return start.distanceTo(end);
        }

        public Point2D getMidpoint() {
            return new Point2D((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
        }

        @Override
        public String toString() {
            return "LineSegment{" + start + " -> " + end + ", довжина=" + f("%.2f", getLength()) + "}";
        }
    }

    // ===== Завдання 13 =====
    static class BankAccount {
        private final String iban;
        private double balance;

        public BankAccount(String iban, double balance) {
            this.iban = iban;
            this.balance = Math.max(0, balance);
        }

        public boolean deposit(double amount) {
            if (amount <= 0) {
                return false;
            }
            balance += amount;
            return true;
        }

        public boolean withdraw(double amount) {
            if (amount <= 0 || amount > balance) {
                return false;
            }
            balance -= amount;
            return true;
        }

        @Override
        public String toString() {
            return f("BankAccount{%s, баланс=%.2f}", iban, balance);
        }
    }

    static class PlasticCard {
        private final String cardNumber;
        private final String expiryDate;
        private boolean blocked;
        private final BankAccount linkedAccount; // агрегація

        public PlasticCard(String cardNumber, String expiryDate, BankAccount linkedAccount) {
            if (linkedAccount == null) {
                throw new IllegalArgumentException("Рахунок не може бути null");
            }
            this.cardNumber = cardNumber;
            this.expiryDate = expiryDate;
            this.linkedAccount = linkedAccount;
        }

        public void block() {
            blocked = true;
        }

        public boolean payAtTerminal(double amount) {
            return !blocked && linkedAccount.withdraw(amount);
        }

        @Override
        public String toString() {
            return "Card{" + cardNumber + ", до " + expiryDate + (blocked ? ", ЗАБЛОКОВАНА" : "") + "}";
        }
    }

    // ===== Завдання 14 =====
    static class WeaponItem {
        private final String name;
        private final int bonusDamage;
        private int durability;

        public WeaponItem(String name, int bonusDamage, int durability) {
            this.name = name;
            this.bonusDamage = Math.max(0, bonusDamage);
            this.durability = Math.max(0, Math.min(100, durability));
        }

        public int getBonusDamage() {
            return durability == 0 ? 0 : bonusDamage;
        }

        public void useWeapon() {
            durability = Math.max(0, durability - 10);
        }

        @Override
        public String toString() {
            return name + "(+" + getBonusDamage() + " dmg, міцність " + durability + ")";
        }
    }

    static class HeroCharacter {
        private final String nickname;
        private int baseStrength;
        private WeaponItem equippedWeapon;

        public HeroCharacter(String nickname, int baseStrength) {
            this.nickname = nickname;
            this.baseStrength = Math.max(1, baseStrength);
        }

        public void equip(WeaponItem newWeapon) {
            this.equippedWeapon = newWeapon;
        }

        public void unequip() {
            this.equippedWeapon = null;
        }

        public int attack() {
            int damage = baseStrength;
            if (equippedWeapon != null) {
                damage += equippedWeapon.getBonusDamage();
                equippedWeapon.useWeapon();
            }
            return damage;
        }

        @Override
        public String toString() {
            return "Hero{" + nickname + ", сила=" + baseStrength + ", зброя=" + (equippedWeapon == null ? "немає" : equippedWeapon) + "}";
        }
    }

    // ===== Завдання 15 =====
    static class DeliveryAddress {
        private final String city;
        private final String street;
        private final int postOfficeNumber;

        public DeliveryAddress(String city, String street, int postOfficeNumber) {
            this.city = city;
            this.street = street;
            this.postOfficeNumber = postOfficeNumber;
        }

        public boolean isValid() {
            return city != null && !city.isBlank() && street != null && !street.isBlank() && postOfficeNumber > 0;
        }

        @Override
        public String toString() {
            return city + ", " + street + ", відділення №" + postOfficeNumber;
        }
    }

    static class ParcelOrder {
        private final String orderId;
        private double weightKg;
        private DeliveryAddress destination;
        private boolean shipped;

        public ParcelOrder(String orderId, double weightKg, DeliveryAddress destination) {
            this.orderId = orderId;
            this.weightKg = Math.max(0.1, weightKg);
            this.destination = destination;
        }

        public boolean changeDestination(DeliveryAddress newAddress) {
            if (shipped || newAddress == null || !newAddress.isValid()) {
                return false;
            }
            this.destination = newAddress;
            return true;
        }

        public void markAsShipped() {
            shipped = true;
        }

        @Override
        public String toString() {
            return f("ParcelOrder{%s, %.1f кг, %s, %s}", orderId, weightKg, destination, shipped ? "відправлено" : "очікує");
        }
    }

    // ===== Завдання 16 =====
    static class TemperatureSensor {
        private double currentTemp;

        public TemperatureSensor(double currentTemp) {
            this.currentTemp = currentTemp;
        }

        public double getCurrentTemp() {
            return currentTemp;
        }

        public void updateReading(double temp) {
            this.currentTemp = temp;
        }
    }

    static class HeatingUnit {
        private boolean heatingOn;
        private final double powerKw;

        public HeatingUnit(double powerKw) {
            this.powerKw = Math.max(0.1, powerKw);
        }

        public void turnOn() {
            heatingOn = true;
        }

        public void turnOff() {
            heatingOn = false;
        }

        public boolean isHeatingOn() {
            return heatingOn;
        }

        @Override
        public String toString() {
            return f("Heater{%.1f кВт, %s}", powerKw, heatingOn ? "ON" : "OFF");
        }
    }

    static class ClimateController {
        private final TemperatureSensor sensor;
        private final HeatingUnit heatingUnit;
        private double targetTemp;

        public ClimateController(TemperatureSensor sensor, HeatingUnit heatingUnit, double targetTemp) {
            this.sensor = sensor;
            this.heatingUnit = heatingUnit;
            this.targetTemp = targetTemp;
        }

        public void regulate() {
            double t = sensor.getCurrentTemp();
            if (t < targetTemp - 0.5) {
                heatingUnit.turnOn();
            } else if (t >= targetTemp) {
                heatingUnit.turnOff();
            }
        }

        @Override
        public String toString() {
            return f("ClimateController{ціль=%.1f°C, зараз=%.1f°C, %s}", targetTemp, sensor.getCurrentTemp(), heatingUnit);
        }
    }

    // ===== Завдання 17 =====
    static class Polyline {
        private final Point2D[] vertices;
        private int count;

        public Polyline(int capacity) {
            this.vertices = new Point2D[Math.max(2, capacity)];
        }

        public boolean addVertex(double x, double y) {
            if (count >= vertices.length) {
                return false;
            }
            vertices[count++] = new Point2D(x, y);
            return true;
        }

        public double getTotalLength() {
            double sum = 0;
            for (int i = 1; i < count; i++) {
                sum += vertices[i - 1].distanceTo(vertices[i]);
            }
            return sum;
        }

        public double getClosedPerimeter() {
            if (count < 3) {
                return getTotalLength();
            }
            return getTotalLength() + vertices[count - 1].distanceTo(vertices[0]);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Polyline{");
            for (int i = 0; i < count; i++) {
                sb.append(i == 0 ? "" : " -> ").append(vertices[i]);
            }
            return sb.append("}").toString();
        }
    }

    // ===== Завдання 18 =====
    static class OrderItem {
        private final String productName;
        private final double unitPrice;
        private int quantity;

        public OrderItem(String productName, double unitPrice, int quantity) {
            this.productName = productName;
            this.unitPrice = Math.max(0, unitPrice);
            this.quantity = Math.max(1, quantity);
        }

        public String getProductName() {
            return productName;
        }

        public void addQuantity(int extra) {
            if (extra > 0) {
                quantity += extra;
            }
        }

        public double getItemTotal() {
            return unitPrice * quantity;
        }

        @Override
        public String toString() {
            return f("%-14s %2d x %8.2f = %9.2f", productName, quantity, unitPrice, getItemTotal());
        }
    }

    static class ShoppingOrder {
        private final OrderItem[] items = new OrderItem[10];
        private int itemCount;

        public boolean addItem(String name, double price, int qty) {
            if (name == null || name.isBlank() || qty <= 0) {
                return false;
            }
            for (int i = 0; i < itemCount; i++) {
                if (items[i].getProductName().equalsIgnoreCase(name)) {
                    items[i].addQuantity(qty);
                    return true;
                }
            }
            if (itemCount >= items.length) {
                return false;
            }
            items[itemCount++] = new OrderItem(name, price, qty);
            return true;
        }

        public double calculateGrandTotal() {
            double sum = 0;
            for (int i = 0; i < itemCount; i++) {
                sum += items[i].getItemTotal();
            }
            return sum;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("======= ЧЕК =======").append(System.lineSeparator());
            for (int i = 0; i < itemCount; i++) {
                sb.append("   ").append(items[i]).append(System.lineSeparator());
            }
            sb.append(f("   РАЗОМ: %.2f грн", calculateGrandTotal()));
            return sb.toString();
        }
    }

    // ===== Завдання 19 =====
    static class BookItem {
        private final String title;
        private final String author;
        private final int pages;

        public BookItem(String title, String author, int pages) {
            this.title = title;
            this.author = author;
            this.pages = Math.max(1, pages);
        }

        public int getPages() {
            return pages;
        }

        @Override
        public String toString() {
            return "«" + title + "» (" + author + ", " + pages + " с.)";
        }
    }

    static class Bookshelf {
        private final String genre;
        private final BookItem[] books;
        private int bookCount;

        public Bookshelf(String genre, int capacity) {
            this.genre = genre;
            this.books = new BookItem[Math.max(1, capacity)];
        }

        public boolean putBook(BookItem book) {
            if (book == null || bookCount >= books.length) {
                return false;
            }
            books[bookCount++] = book;
            return true;
        }

        public BookItem findLongestBook() {
            BookItem best = null;
            for (int i = 0; i < bookCount; i++) {
                if (best == null || books[i].getPages() > best.getPages()) {
                    best = books[i];
                }
            }
            return best;
        }

        public int getTotalPagesOnShelf() {
            int sum = 0;
            for (int i = 0; i < bookCount; i++) {
                sum += books[i].getPages();
            }
            return sum;
        }

        @Override
        public String toString() {
            return "Bookshelf{" + genre + ", книг: " + bookCount + "/" + books.length + "}";
        }
    }

    // ===== Завдання 20 =====
    static class AudioTrack {
        private final String title;
        private final String artist;
        private final int durationSeconds;

        public AudioTrack(String title, String artist, int durationSeconds) {
            this.title = title;
            this.artist = artist;
            this.durationSeconds = Math.max(1, durationSeconds);
        }

        public int getDurationSeconds() {
            return durationSeconds;
        }

        public String formatDuration() {
            return String.format("%02d:%02d", durationSeconds / 60, durationSeconds % 60);
        }

        @Override
        public String toString() {
            return artist + " — " + title + " [" + formatDuration() + "]";
        }
    }

    static class Playlist {
        private String name;
        private final AudioTrack[] tracks;
        private int trackCount;

        public Playlist(String name, int capacity) {
            this.name = name;
            this.tracks = new AudioTrack[Math.max(1, capacity)];
        }

        public boolean addTrack(AudioTrack track) {
            if (track == null || trackCount >= tracks.length) {
                return false;
            }
            tracks[trackCount++] = track;
            return true;
        }

        public int getTotalDurationSeconds() {
            int sum = 0;
            for (int i = 0; i < trackCount; i++) {
                sum += tracks[i].getDurationSeconds();
            }
            return sum;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Плейлист '" + name + "':");
            for (int i = 0; i < trackCount; i++) {
                sb.append(System.lineSeparator()).append("     ").append(i + 1).append(". ").append(tracks[i]);
            }
            int total = getTotalDurationSeconds();
            return sb.append(System.lineSeparator()).append("     Загалом: ").append(total / 60).append(" хв ")
                    .append(total % 60).append(" сек").toString();
        }
    }

    // ===== Завдання 21 =====
    static class ServerNode {
        private final String ipAddress;
        private final int totalRamGb;
        private int usedRamGb;
        private boolean online;

        public ServerNode(String ipAddress, int totalRamGb, int usedRamGb, boolean online) {
            this.ipAddress = ipAddress;
            this.totalRamGb = Math.max(1, totalRamGb);
            this.usedRamGb = Math.max(0, Math.min(this.totalRamGb, usedRamGb));
            this.online = online;
        }

        public int getFreeRamGb() {
            return totalRamGb - usedRamGb;
        }

        public boolean isOnline() {
            return online;
        }

        public void allocate(int ramGb) {
            usedRamGb += ramGb;
        }

        public String getIpAddress() {
            return ipAddress;
        }

        @Override
        public String toString() {
            return ipAddress + (online ? "" : " [OFFLINE]") + " (" + usedRamGb + "/" + totalRamGb + " ГБ)";
        }
    }

    static class DataCenterCluster {
        private final String clusterLocation;
        private final ServerNode[] nodes;
        private int nodeCount;

        public DataCenterCluster(String clusterLocation, int capacity) {
            this.clusterLocation = clusterLocation;
            this.nodes = new ServerNode[Math.max(1, capacity)];
        }

        public boolean addNode(ServerNode node) {
            if (node == null || nodeCount >= nodes.length) {
                return false;
            }
            nodes[nodeCount++] = node;
            return true;
        }

        public boolean deployContainer(int requiredRamGb) {
            if (requiredRamGb <= 0) {
                return false;
            }
            for (int i = 0; i < nodeCount; i++) {
                if (nodes[i].isOnline() && nodes[i].getFreeRamGb() >= requiredRamGb) {
                    nodes[i].allocate(requiredRamGb);
                    System.out.println("    ✅ Контейнер на " + requiredRamGb + " ГБ розміщено на " + nodes[i].getIpAddress());
                    return true;
                }
            }
            System.out.println("    ❌ Немає ресурсів для контейнера на " + requiredRamGb + " ГБ");
            return false;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Cluster '" + clusterLocation + "':");
            for (int i = 0; i < nodeCount; i++) {
                sb.append(" ").append(nodes[i]).append(";");
            }
            return sb.toString();
        }
    }

    // ===== Завдання 22 =====
    static class CourseModule {
        private final String topic;
        private final int lectureHours;
        private final int practiceHours;
        private double maxScore;

        public CourseModule(String topic, int lectureHours, int practiceHours, double maxScore) {
            this.topic = topic;
            this.lectureHours = Math.max(0, lectureHours);
            this.practiceHours = Math.max(0, practiceHours);
            this.maxScore = Math.max(0, maxScore);
        }

        public int getTotalHours() {
            return lectureHours + practiceHours;
        }

        public double getMaxScore() {
            return maxScore;
        }
    }

    static class UniversityCourse {
        private final String courseTitle;
        private final CourseModule[] modules;
        private int moduleCount;

        public UniversityCourse(String courseTitle, int capacity) {
            this.courseTitle = courseTitle;
            this.modules = new CourseModule[Math.max(1, capacity)];
        }

        public boolean addModule(CourseModule m) {
            if (m == null || moduleCount >= modules.length) {
                return false;
            }
            modules[moduleCount++] = m;
            return true;
        }

        public double getEctsCredits() {
            int hours = 0;
            for (int i = 0; i < moduleCount; i++) {
                hours += modules[i].getTotalHours();
            }
            return hours / 30.0;
        }

        public boolean isScoreValid() {
            double sum = 0;
            for (int i = 0; i < moduleCount; i++) {
                sum += modules[i].getMaxScore();
            }
            return Math.abs(sum - 100.0) < 1e-9;
        }

        @Override
        public String toString() {
            return f("Курс '%s': модулів %d, ЄКТС %.1f, сума балів = 100: %s", courseTitle, moduleCount, getEctsCredits(),
                    isScoreValid() ? "так" : "ні");
        }
    }

    // ===== Завдання 23 =====
    static class TrainCarriage {
        private final int carriageNumber;
        private final String type;
        private final int totalSeats;
        private int occupiedSeats;

        public TrainCarriage(int carriageNumber, String type, int totalSeats, int occupiedSeats) {
            this.carriageNumber = carriageNumber;
            this.type = type;
            this.totalSeats = Math.max(1, totalSeats);
            this.occupiedSeats = Math.max(0, Math.min(this.totalSeats, occupiedSeats));
        }

        public int getCarriageNumber() {
            return carriageNumber;
        }

        public int getFreeSeats() {
            return totalSeats - occupiedSeats;
        }

        public boolean book(int seats) {
            if (seats <= 0 || seats > getFreeSeats()) {
                return false;
            }
            occupiedSeats += seats;
            return true;
        }

        @Override
        public String toString() {
            return "№" + carriageNumber + " " + type + " (" + occupiedSeats + "/" + totalSeats + ")";
        }
    }

    static class PassengerTrain {
        private final String route;
        private final TrainCarriage[] carriages;
        private int carriageCount;

        public PassengerTrain(String route, int capacity) {
            this.route = route;
            this.carriages = new TrainCarriage[Math.max(1, capacity)];
        }

        public boolean attachCarriage(TrainCarriage c) {
            if (c == null || carriageCount >= carriages.length) {
                return false;
            }
            carriages[carriageCount++] = c;
            return true;
        }

        public int getTotalFreeSeats() {
            int sum = 0;
            for (int i = 0; i < carriageCount; i++) {
                sum += carriages[i].getFreeSeats();
            }
            return sum;
        }

        public boolean bookTicketsInCarriage(int carriageNumber, int seatsToBook) {
            for (int i = 0; i < carriageCount; i++) {
                if (carriages[i].getCarriageNumber() == carriageNumber) {
                    return carriages[i].book(seatsToBook);
                }
            }
            return false;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Потяг " + route + ":");
            for (int i = 0; i < carriageCount; i++) {
                sb.append(" ").append(carriages[i]).append(";");
            }
            return sb.toString();
        }
    }

    // ===== Завдання 24 =====
    static class LinearEquation {
        private final double a;
        private final double b;
        private final double c;

        public LinearEquation(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public boolean isSatisfiedBy(double x, double y) {
            return Math.abs(a * x + b * y - c) < 1e-6;
        }

        @Override
        public String toString() {
            return f("%.1fx + %.1fy = %.1f", a, b, c);
        }
    }

    static class EquationSystem {
        private final LinearEquation[] equations;
        private int count;

        public EquationSystem(int capacity) {
            this.equations = new LinearEquation[Math.max(1, capacity)];
        }

        public boolean addEquation(LinearEquation e) {
            if (e == null || count >= equations.length) {
                return false;
            }
            equations[count++] = e;
            return true;
        }

        public boolean checkSolution(double x, double y) {
            if (count == 0) {
                return false;
            }
            for (int i = 0; i < count; i++) {
                if (!equations[i].isSatisfiedBy(x, y)) {
                    return false;
                }
            }
            return true;
        }
    }

    // ===== Завдання 25 =====
    static abstract class AbstractShape {
        private final String color;

        public AbstractShape(String color) {
            this.color = (color == null || color.isBlank()) ? "Білий" : color;
        }

        public String getColor() {
            return color;
        }

        public abstract double getArea();

        public abstract double getPerimeter();

        @Override
        public String toString() {
            return f("%s[color='%s', S=%.2f, P=%.2f]", getClass().getSimpleName(), color, getArea(), getPerimeter());
        }
    }

    static class CircleShape extends AbstractShape {
        private final double radius;

        public CircleShape(String color, double radius) {
            super(color);
            this.radius = (radius > 0) ? radius : 1.0;
        }

        @Override
        public double getArea() {
            return Math.PI * radius * radius;
        }

        @Override
        public double getPerimeter() {
            return 2 * Math.PI * radius;
        }
    }

    static class RectangleShape extends AbstractShape {
        private final double width;
        private final double height;

        public RectangleShape(String color, double width, double height) {
            super(color);
            this.width = (width > 0) ? width : 1.0;
            this.height = (height > 0) ? height : 1.0;
        }

        @Override
        public double getArea() {
            return width * height;
        }

        @Override
        public double getPerimeter() {
            return 2 * (width + height);
        }
    }

    // ===== Завдання 26 =====
    static abstract class AbstractPowerPlant {
        private final String stationName;
        protected double baseCapacityMw;

        public AbstractPowerPlant(String stationName, double baseCapacityMw) {
            this.stationName = stationName;
            this.baseCapacityMw = Math.max(0, baseCapacityMw);
        }

        public String getStationName() {
            return stationName;
        }

        public abstract double generateMegawatts();

        public double calculateDailyProfit(double tariffPerMwh) {
            return generateMegawatts() * 24.0 * tariffPerMwh;
        }
    }

    static class SolarPowerPlant extends AbstractPowerPlant {
        private final double sunIntensityFactor;

        public SolarPowerPlant(String name, double baseCapacityMw, double sunIntensityFactor) {
            super(name, baseCapacityMw);
            this.sunIntensityFactor = Math.max(0.0, Math.min(1.0, sunIntensityFactor));
        }

        @Override
        public double generateMegawatts() {
            return baseCapacityMw * sunIntensityFactor;
        }
    }

    static class WindPowerPlant extends AbstractPowerPlant {
        private final double windSpeedMs;

        public WindPowerPlant(String name, double baseCapacityMw, double windSpeedMs) {
            super(name, baseCapacityMw);
            this.windSpeedMs = Math.max(0, windSpeedMs);
        }

        @Override
        public double generateMegawatts() {
            if (windSpeedMs < 3 || windSpeedMs > 25) {
                return 0.0;
            }
            double fraction = Math.min(1.0, Math.pow(windSpeedMs / 12.0, 3));
            return baseCapacityMw * fraction;
        }
    }

    // ===== Завдання 27 =====
    static abstract class AbstractEmployee {
        private final int id;
        private final String name;
        protected double taxRate = 0.195;

        public AbstractEmployee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public abstract double calculateGrossSalary();

        public final double calculateNetSalary() {
            return calculateGrossSalary() * (1.0 - taxRate);
        }

        @Override
        public String toString() {
            return f("%s#%d %s: брутто=%.2f, нетто=%.2f", getClass().getSimpleName(), id, name,
                    calculateGrossSalary(), calculateNetSalary());
        }
    }

    static class SalariedEmployee extends AbstractEmployee {
        private final double monthlySalary;
        private final double bonus;

        public SalariedEmployee(int id, String name, double monthlySalary, double bonus) {
            super(id, name);
            this.monthlySalary = Math.max(0, monthlySalary);
            this.bonus = Math.max(0, bonus);
        }

        @Override
        public double calculateGrossSalary() {
            return monthlySalary + bonus;
        }
    }

    static class HourlyContractor extends AbstractEmployee {
        private final int hoursWorked;
        private final double hourlyRate;

        public HourlyContractor(int id, String name, int hoursWorked, double hourlyRate) {
            super(id, name);
            this.hoursWorked = Math.max(0, hoursWorked);
            this.hourlyRate = Math.max(0, hourlyRate);
        }

        @Override
        public double calculateGrossSalary() {
            int regular = Math.min(160, hoursWorked);
            int overtime = Math.max(0, hoursWorked - 160);
            return regular * hourlyRate + overtime * hourlyRate * 1.5;
        }
    }

    // ===== Завдання 28 =====
    static abstract class AbstractSequence {
        private final String title;

        public AbstractSequence(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public abstract double getTerm(int index);

        public double sumFirstN(int n) {
            double sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += getTerm(i);
            }
            return sum;
        }
    }

    static class HarmonicSequence extends AbstractSequence {
        public HarmonicSequence() {
            super("Гармонічна: 1/n");
        }

        @Override
        public double getTerm(int index) {
            return 1.0 / index;
        }
    }

    static class SquareNumbersSequence extends AbstractSequence {
        public SquareNumbersSequence() {
            super("Квадрати: n^2");
        }

        @Override
        public double getTerm(int index) {
            return (double) index * index;
        }
    }

    // ===== Завдання 29 =====
    static abstract class AbstractTransaction {
        private final String txId;
        protected final double amount;

        public AbstractTransaction(String txId, double amount) {
            this.txId = txId;
            this.amount = Math.max(0, amount);
        }

        public String getTxId() {
            return txId;
        }

        public abstract double calculateCommission();

        public final double getTotalDebit() {
            return amount + calculateCommission();
        }

        @Override
        public String toString() {
            return f("%s[%s: сума=%.2f, комісія=%.2f, до списання=%.2f]", getClass().getSimpleName(), txId, amount,
                    calculateCommission(), getTotalDebit());
        }
    }

    static class DomesticTransfer extends AbstractTransaction {
        public DomesticTransfer(String txId, double amount) {
            super(txId, amount);
        }

        @Override
        public double calculateCommission() {
            return Math.max(5.0, amount * 0.005);
        }
    }

    static class CryptoTransfer extends AbstractTransaction {
        private final double networkGasFee;

        public CryptoTransfer(String txId, double amount, double networkGasFee) {
            super(txId, amount);
            this.networkGasFee = Math.max(0, networkGasFee);
        }

        @Override
        public double calculateCommission() {
            return networkGasFee;
        }
    }

    // ===== Завдання 30 =====
    static abstract class Solid3D {
        private final String materialName;
        private final double densityKgPerM3;

        public Solid3D(String materialName, double densityKgPerM3) {
            this.materialName = materialName;
            this.densityKgPerM3 = Math.max(0, densityKgPerM3);
        }

        public abstract double getVolume();

        public abstract double getSurfaceArea();

        public final double getMassKg() {
            return densityKgPerM3 * getVolume();
        }

        @Override
        public String toString() {
            return f("%s[%s: V=%.5f м³, S=%.4f м², m=%.2f кг]", getClass().getSimpleName(), materialName, getVolume(),
                    getSurfaceArea(), getMassKg());
        }
    }

    static class Sphere3D extends Solid3D {
        private final double radius;

        public Sphere3D(String material, double density, double radius) {
            super(material, density);
            this.radius = (radius > 0) ? radius : 1.0;
        }

        @Override
        public double getVolume() {
            return 4.0 / 3.0 * Math.PI * Math.pow(radius, 3);
        }

        @Override
        public double getSurfaceArea() {
            return 4 * Math.PI * radius * radius;
        }
    }

    static class Cylinder3D extends Solid3D {
        private final double radius;
        private final double height;

        public Cylinder3D(String material, double density, double radius, double height) {
            super(material, density);
            this.radius = (radius > 0) ? radius : 1.0;
            this.height = (height > 0) ? height : 1.0;
        }

        @Override
        public double getVolume() {
            return Math.PI * radius * radius * height;
        }

        @Override
        public double getSurfaceArea() {
            return 2 * Math.PI * radius * (radius + height);
        }
    }

    // ===== Завдання 31 =====
    static abstract class AbstractSensor {
        private final String location;
        private final double minSafe;
        private final double maxSafe;

        public AbstractSensor(String location, double minSafe, double maxSafe) {
            this.location = location;
            this.minSafe = Math.min(minSafe, maxSafe);
            this.maxSafe = Math.max(minSafe, maxSafe);
        }

        public String getLocation() {
            return location;
        }

        public abstract double readValue();

        public abstract String getUnit();

        public final boolean isAlarmTriggered() {
            double v = readValue();
            return v < minSafe || v > maxSafe;
        }

        @Override
        public String toString() {
            return f("%s[%s: %.2f %s, норма %.1f..%.1f]", getClass().getSimpleName(), location, readValue(), getUnit(),
                    minSafe, maxSafe);
        }
    }

    static class PressureSensor extends AbstractSensor {
        private double rawBar;
        private final double calibrationFactor;

        public PressureSensor(String location, double minSafe, double maxSafe, double rawBar, double calibrationFactor) {
            super(location, minSafe, maxSafe);
            this.rawBar = rawBar;
            this.calibrationFactor = calibrationFactor;
        }

        public void updateRaw(double rawBar) {
            this.rawBar = rawBar;
        }

        @Override
        public double readValue() {
            return rawBar * calibrationFactor;
        }

        @Override
        public String getUnit() {
            return "бар";
        }
    }

    static class RadiationSensor extends AbstractSensor {
        private double microSvPerHour;

        public RadiationSensor(String location, double minSafe, double maxSafe, double microSvPerHour) {
            super(location, minSafe, maxSafe);
            this.microSvPerHour = Math.max(0, microSvPerHour);
        }

        public void updateBackground(double value) {
            this.microSvPerHour = Math.max(0, value);
        }

        @Override
        public double readValue() {
            return microSvPerHour;
        }

        @Override
        public String getUnit() {
            return "мкЗв/год";
        }
    }

    // ===== Завдання 32 =====
    static abstract class AbstractCompressor {
        private final String algorithmName;

        public AbstractCompressor(String algorithmName) {
            this.algorithmName = algorithmName;
        }

        public String getAlgorithmName() {
            return algorithmName;
        }

        public abstract int estimateCompressedSize(int originalSizeBytes);

        public double getCompressionRatio(int originalSizeBytes) {
            if (originalSizeBytes <= 0) {
                return 0.0;
            }
            return (1.0 - (double) estimateCompressedSize(originalSizeBytes) / originalSizeBytes) * 100.0;
        }
    }

    static class ZipCompressor extends AbstractCompressor {
        public ZipCompressor() {
            super("ZIP");
        }

        @Override
        public int estimateCompressedSize(int originalSizeBytes) {
            return (int) Math.round(originalSizeBytes * 0.55);
        }
    }

    static class UltraLzmaCompressor extends AbstractCompressor {
        public UltraLzmaCompressor() {
            super("Ultra LZMA");
        }

        @Override
        public int estimateCompressedSize(int originalSizeBytes) {
            return (int) Math.round(originalSizeBytes * 0.30) + 128;
        }
    }

    // ===== Завдання 33 =====
    static abstract class AbstractPcPart {
        private final String model;
        private final double priceUah;

        public AbstractPcPart(String model, double priceUah) {
            this.model = model;
            this.priceUah = Math.max(0, priceUah);
        }

        public String getModel() {
            return model;
        }

        public double getPriceUah() {
            return priceUah;
        }

        public abstract int getPowerConsumptionWatts();

        @Override
        public String toString() {
            return getClass().getSimpleName() + "[" + model + ", " + getPowerConsumptionWatts() + " Вт]";
        }
    }

    static class ProcessorPart extends AbstractPcPart {
        private final int cores;
        private final int watts;

        public ProcessorPart(String model, double price, int cores, int watts) {
            super(model, price);
            this.cores = Math.max(1, cores);
            this.watts = Math.max(0, watts);
        }

        @Override
        public int getPowerConsumptionWatts() {
            return watts;
        }
    }

    static class VideoCardPart extends AbstractPcPart {
        private final int vramGb;
        private final int watts;

        public VideoCardPart(String model, double price, int vramGb, int watts) {
            super(model, price);
            this.vramGb = Math.max(1, vramGb);
            this.watts = Math.max(0, watts);
        }

        @Override
        public int getPowerConsumptionWatts() {
            return watts;
        }
    }

    static class DesktopPC {
        private final int psuMaxWatts;
        private final AbstractPcPart[] parts;
        private int count;

        public DesktopPC(int psuMaxWatts, int capacity) {
            this.psuMaxWatts = Math.max(0, psuMaxWatts);
            this.parts = new AbstractPcPart[Math.max(1, capacity)];
        }

        public boolean addPart(AbstractPcPart part) {
            if (part == null || count >= parts.length) {
                return false;
            }
            parts[count++] = part;
            return true;
        }

        public int getTotalPowerWatts() {
            int sum = 0;
            for (int i = 0; i < count; i++) {
                sum += parts[i].getPowerConsumptionWatts();
            }
            return sum;
        }

        /** Чи вистачає БЖ із запасом 20%. */
        public boolean isStable() {
            return getTotalPowerWatts() * 1.2 <= psuMaxWatts;
        }

        @Override
        public String toString() {
            return "DesktopPC{БЖ " + psuMaxWatts + " Вт, споживання " + getTotalPowerWatts() + " Вт, "
                    + (isStable() ? "СТАБІЛЬНО" : "НЕСТАБІЛЬНО") + "}";
        }
    }

    // ===== Завдання 34 =====
    static abstract class AbstractThruster {
        private final String thrusterId;

        public AbstractThruster(String thrusterId) {
            this.thrusterId = thrusterId;
        }

        public String getThrusterId() {
            return thrusterId;
        }

        public abstract double getThrustKn();

        public abstract double getFuelBurnRate();
    }

    static class ChemicalThruster extends AbstractThruster {
        public ChemicalThruster(String id) {
            super(id);
        }

        @Override
        public double getThrustKn() {
            return 500.0;
        }

        @Override
        public double getFuelBurnRate() {
            return 180.0;
        }
    }

    static class IonThruster extends AbstractThruster {
        public IonThruster(String id) {
            super(id);
        }

        @Override
        public double getThrustKn() {
            return 0.25;
        }

        @Override
        public double getFuelBurnRate() {
            return 0.005;
        }
    }

    static class Spacecraft {
        private final String shipName;
        private final double dryMassTons;
        private final AbstractThruster[] thrusters;
        private int count;

        public Spacecraft(String shipName, double dryMassTons, int capacity) {
            this.shipName = shipName;
            this.dryMassTons = Math.max(0.1, dryMassTons);
            this.thrusters = new AbstractThruster[Math.max(1, capacity)];
        }

        public boolean addThruster(AbstractThruster t) {
            if (t == null || count >= thrusters.length) {
                return false;
            }
            thrusters[count++] = t;
            return true;
        }

        public double getTotalThrustKn() {
            double sum = 0;
            for (int i = 0; i < count; i++) {
                sum += thrusters[i].getThrustKn();
            }
            return sum;
        }

        /** a = F / m, де F у кН, m у тоннах => м/с². */
        public double getInitialAcceleration() {
            return getTotalThrustKn() / dryMassTons;
        }

        @Override
        public String toString() {
            return f("Spacecraft{%s, маса %.1f т, двигунів %d, тяга %.2f кН, a=%.3f м/с²}", shipName, dryMassTons, count,
                    getTotalThrustKn(), getInitialAcceleration());
        }
    }

    // ===== Завдання 35 =====
    static class LandPlot {
        private final double totalPlotAreaM2;
        private final AbstractShape[] structures;
        private int count;

        public LandPlot(double totalPlotAreaM2, int capacity) {
            this.totalPlotAreaM2 = Math.max(1, totalPlotAreaM2);
            this.structures = new AbstractShape[Math.max(1, capacity)];
        }

        public double getBuiltArea() {
            double sum = 0;
            for (int i = 0; i < count; i++) {
                sum += structures[i].getArea();
            }
            return sum;
        }

        public boolean addStructure(AbstractShape s) {
            if (s == null || count >= structures.length) {
                return false;
            }
            if (getBuiltArea() + s.getArea() > totalPlotAreaM2 * 0.6) {
                return false;
            }
            structures[count++] = s;
            return true;
        }

        public double getFreeGreenArea() {
            return totalPlotAreaM2 - getBuiltArea();
        }
    }

    // ===== Завдання 36 =====
    static abstract class AbstractAsset {
        private final String ticker;
        protected double investedAmount;

        public AbstractAsset(String ticker, double investedAmount) {
            this.ticker = ticker;
            this.investedAmount = Math.max(0, investedAmount);
        }

        public String getTicker() {
            return ticker;
        }

        public double getInvestedAmount() {
            return investedAmount;
        }

        public abstract double calculateAnnualReturn();
    }

    static class GovernmentBond extends AbstractAsset {
        public GovernmentBond(String ticker, double invested) {
            super(ticker, invested);
        }

        @Override
        public double calculateAnnualReturn() {
            return investedAmount * 0.155;
        }
    }

    static class DividendStock extends AbstractAsset {
        private final double expectedGrowth;
        private final double dividendYield;

        public DividendStock(String ticker, double invested, double expectedGrowth, double dividendYield) {
            super(ticker, invested);
            this.expectedGrowth = expectedGrowth;
            this.dividendYield = Math.max(0, dividendYield);
        }

        @Override
        public double calculateAnnualReturn() {
            double dividends = investedAmount * dividendYield * (1.0 - 0.195);
            return investedAmount * expectedGrowth + dividends;
        }
    }

    static class InvestmentPortfolio {
        private final String investorName;
        private final AbstractAsset[] assets;
        private int count;

        public InvestmentPortfolio(String investorName, int capacity) {
            this.investorName = investorName;
            this.assets = new AbstractAsset[Math.max(1, capacity)];
        }

        public boolean addAsset(AbstractAsset a) {
            if (a == null || count >= assets.length) {
                return false;
            }
            assets[count++] = a;
            return true;
        }

        public double getTotalValue() {
            double sum = 0;
            for (int i = 0; i < count; i++) {
                sum += assets[i].getInvestedAmount();
            }
            return sum;
        }

        public double getExpectedAnnualReturn() {
            double sum = 0;
            for (int i = 0; i < count; i++) {
                sum += assets[i].calculateAnnualReturn();
            }
            return sum;
        }

        @Override
        public String toString() {
            return f("Портфель %s: вартість %.2f грн, очікуваний річний прибуток %.2f грн", investorName, getTotalValue(),
                    getExpectedAnnualReturn());
        }
    }

    // ===== Завдання 37 =====
    static abstract class AbstractHeroUnit {
        private final String name;
        protected int hp;

        public AbstractHeroUnit(String name, int hp) {
            this.name = name;
            this.hp = Math.max(1, hp);
        }

        public String getName() {
            return name;
        }

        public abstract int getCombatPower();

        public abstract String getRole();

        @Override
        public String toString() {
            return getRole() + " " + name + " (сила " + getCombatPower() + ")";
        }
    }

    static class TankWarrior extends AbstractHeroUnit {
        private final int armor;

        public TankWarrior(String name, int hp, int armor) {
            super(name, hp);
            this.armor = Math.max(0, armor);
        }

        @Override
        public int getCombatPower() {
            return hp / 10 + armor * 2;
        }

        @Override
        public String getRole() {
            return "Tank";
        }
    }

    static class ArcaneMage extends AbstractHeroUnit {
        private final int intellect;
        private final int spellPower;

        public ArcaneMage(String name, int hp, int intellect, int spellPower) {
            super(name, hp);
            this.intellect = Math.max(0, intellect);
            this.spellPower = Math.max(0, spellPower);
        }

        @Override
        public int getCombatPower() {
            return intellect * 3 + spellPower * 5;
        }

        @Override
        public String getRole() {
            return "Mage";
        }
    }

    static class RaidParty {
        private final String guildName;
        private final AbstractHeroUnit[] members;
        private int memberCount;

        public RaidParty(String guildName, int capacity) {
            this.guildName = guildName;
            this.members = new AbstractHeroUnit[Math.max(1, capacity)];
        }

        public boolean addMember(AbstractHeroUnit h) {
            if (h == null || memberCount >= members.length) {
                return false;
            }
            members[memberCount++] = h;
            return true;
        }

        public int getTotalPower() {
            int sum = 0;
            for (int i = 0; i < memberCount; i++) {
                sum += members[i].getCombatPower();
            }
            return sum;
        }

        public boolean isReadyForRaid(int dungeonDifficulty) {
            boolean hasTank = false;
            for (int i = 0; i < memberCount; i++) {
                if ("Tank".equals(members[i].getRole())) {
                    hasTank = true;
                    break;
                }
            }
            return hasTank && getTotalPower() > dungeonDifficulty;
        }

        @Override
        public String toString() {
            return "RaidParty{" + guildName + ", бійців: " + memberCount + ", сумарна сила: " + getTotalPower() + "}";
        }
    }

    // ===== Завдання 38 =====
    static class EmergencySiren {
        private boolean sounding;

        public void activate() {
            sounding = true;
        }

        public void silence() {
            sounding = false;
        }

        public boolean isSounding() {
            return sounding;
        }
    }

    static class SmartLab {
        private final EmergencySiren siren = new EmergencySiren();
        private final AbstractSensor[] sensors;
        private int count;

        public SmartLab(int capacity) {
            this.sensors = new AbstractSensor[Math.max(1, capacity)];
        }

        public boolean addSensor(AbstractSensor s) {
            if (s == null || count >= sensors.length) {
                return false;
            }
            sensors[count++] = s;
            return true;
        }

        public boolean isSirenSounding() {
            return siren.isSounding();
        }

        public void runDiagnostics() {
            boolean alarm = false;
            for (int i = 0; i < count; i++) {
                boolean bad = sensors[i].isAlarmTriggered();
                System.out.println("    " + (bad ? "🚨 " : "✅ ") + sensors[i]);
                alarm |= bad;
            }
            if (alarm) {
                siren.activate();
                System.out.println("    ⚠️ ТРИВОГА! Сирену увімкнено.");
            } else {
                System.out.println("    Усі показники в нормі.");
            }
        }
    }

    // ===== Завдання 39 =====
    static abstract class AbstractFilter {
        private final String filterName;

        public AbstractFilter(String filterName) {
            this.filterName = filterName;
        }

        public String getFilterName() {
            return filterName;
        }

        public abstract double processSample(double x);
    }

    static class AmplifierFilter extends AbstractFilter {
        private final double gain;

        public AmplifierFilter(double gain) {
            super("Amplifier(x" + gain + ")");
            this.gain = gain;
        }

        @Override
        public double processSample(double x) {
            return x * gain;
        }
    }

    static class ClippingFilter extends AbstractFilter {
        private final double threshold;

        public ClippingFilter(double threshold) {
            super("Clipping(±" + Math.abs(threshold) + ")");
            this.threshold = Math.abs(threshold);
        }

        @Override
        public double processSample(double x) {
            return Math.max(-threshold, Math.min(threshold, x));
        }
    }

    static class SignalProcessor {
        private final AbstractFilter[] pipeline;
        private int count;

        public SignalProcessor(int capacity) {
            this.pipeline = new AbstractFilter[Math.max(1, capacity)];
        }

        public boolean addFilter(AbstractFilter f) {
            if (f == null || count >= pipeline.length) {
                return false;
            }
            pipeline[count++] = f;
            return true;
        }

        /** Повертає новий масив; вхідний сигнал копіюється, тож не змінюється. */
        public double[] process(double[] signal) {
            double[] result = (signal == null) ? new double[0] : Arrays.copyOf(signal, signal.length);
            for (int k = 0; k < count; k++) {
                for (int i = 0; i < result.length; i++) {
                    result[i] = pipeline[k].processSample(result[i]);
                }
            }
            return result;
        }
    }
}
