package semester1.practice08;

/**
 * Завдання 3: Vector2D -> Vector3D -> ForceVector3D.
 */
public class Task3Vector3DHierarchy {

    static class Vector2D {
        private double x;
        private double y;

        public Vector2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Vector2D() {
            this(0.0, 0.0);
        }

        public double getX() { return x; }
        public double getY() { return y; }
        public void setX(double x) { this.x = x; }
        public void setY(double y) { this.y = y; }

        public double getLength() {
            return Math.sqrt(x * x + y * y);
        }

        public double dotProduct(Vector2D other) {
            return x * other.x + y * other.y;
        }

        @Override
        public String toString() {
            return String.format("Vector2D(%.2f; %.2f) |L|=%.2f", x, y, getLength());
        }
    }

    static class Vector3D extends Vector2D {
        private double z;

        public Vector3D(double x, double y, double z) {
            super(x, y);
            this.z = z;
        }

        public double getZ() { return z; }
        public void setZ(double z) { this.z = z; }

        @Override
        public double getLength() {
            return Math.hypot(super.getLength(), z);
        }

        public double dotProduct(Vector3D other) {
            return super.dotProduct(other) + this.z * other.z;
        }

        public Vector3D crossProduct(Vector3D other) {
            double x1 = getX(), y1 = getY(), z1 = z;
            double x2 = other.getX(), y2 = other.getY(), z2 = other.z;
            return new Vector3D(y1 * z2 - z1 * y2, z1 * x2 - x1 * z2, x1 * y2 - y1 * x2);
        }

        @Override
        public String toString() {
            return String.format("Vector3D(%.2f; %.2f; %.2f) |L|=%.2f", getX(), getY(), z, getLength());
        }
    }

    static class ForceVector3D extends Vector3D {
        private final String forceName;

        public ForceVector3D(String forceName, double fx, double fy, double fz) {
            super(fx, fy, fz);
            this.forceName = forceName;
        }

        public double calculateWork(Vector3D displacement) {
            return super.dotProduct(displacement);
        }

        public double calculateAcceleration(double massKg) {
            if (massKg <= 0) {
                return 0.0;
            }
            return getLength() / massKg;
        }

        @Override
        public String toString() {
            return String.format("Force[name='%s', %s |F|=%.2f Н]", forceName, super.toString(), getLength());
        }
    }

    public static void main(String[] args) {
        System.out.println("================ ІЄРАРХІЯ ВЕКТОРІВ (2D -> 3D -> FORCE) ================");
        Vector2D v2 = new Vector2D(3, 4);
        System.out.println("1. Рівень 1 (2D): " + v2);

        Vector3D v3 = new Vector3D(3, 4, 12);
        Vector3D s = new Vector3D(2, 0, 5);
        System.out.println("2. Рівень 2 (3D): " + v3);
        System.out.println("   Вектор переміщення S: " + s);
        System.out.println("   Векторний добуток V x S: " + v3.crossProduct(s));

        ForceVector3D f = new ForceVector3D("Тяга ракети", 3, 4, 12);
        System.out.println("\n3. Рівень 3 (Фізична сила):");
        System.out.println("   " + f);
        System.out.printf("   -> Виконана механічна робота A = F · S = %.2f Дж%n", f.calculateWork(s));
        System.out.printf("   -> Прискорення тіла масою 2.5 кг: a = %.2f м/с²%n", f.calculateAcceleration(2.5));
        System.out.println("=======================================================================");
    }
}
