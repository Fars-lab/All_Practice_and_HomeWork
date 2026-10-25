package semester1.semester1.practice06.homework;

import java.util.Locale;

class Vector2D {
    private double x;
    private double y;

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public void multiplyByScalar(double k) {
        x = x * k;
        y = y * k;
    }

    public double dotProduct(double otherX, double otherY) {
        return x * otherX + y * otherY;
    }

    public boolean isPerpendicularTo(double otherX, double otherY) {
        return Math.abs(dotProduct(otherX, otherY)) < 1e-9;
    }

    public void normalize() {
        double length = getLength();
        if (length > 0) {
            x /= length;
            y /= length;
        }
    }

    public void printVector(String label) {
        System.out.printf(Locale.US, "Вектор %s: (%.2f; %.2f), Довжина |%s| = %.2f%n",
                label, x, y, label, getLength());
    }
}

public class Task3Vector2D {
    public static void main(String[] args) {
        System.out.println("=== ВЕКТОРНИЙ АНАЛІЗАТОР (2D GEOMETRY) ===");

        Vector2D a = new Vector2D();
        a.set(3, 4);
        Vector2D b = new Vector2D();
        b.set(-4, 3);

        a.printVector("A");
        b.printVector("B");

        double dot = a.dotProduct(b.getX(), b.getY());
        System.out.printf(Locale.US, "%nСкалярний добуток A · B: %.2f%n", dot);
        boolean perpendicular = a.isPerpendicularTo(b.getX(), b.getY());
        System.out.println("Чи є вектори перпендикулярними (кут 90°)? -> " + (perpendicular ? "ТАК (true)" : "НІ (false)"));

        System.out.println("\nНормалізація вектора A...");
        a.normalize();
        System.out.printf(Locale.US, "Одиничний вектор A: (%.2f; %.2f)%n", a.getX(), a.getY());
        System.out.printf(Locale.US, "Нова довжина вектора A: %.2f%n", a.getLength());
        System.out.println("==========================================");
    }
}
