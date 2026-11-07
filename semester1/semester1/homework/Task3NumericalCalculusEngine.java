package semester1.semester1.practice09.homework;

import java.util.Arrays;
import java.util.Locale;

public class Task3NumericalCalculusEngine {
    public static void main(String[] args) {
        System.out.println("================ ЛАБОРАТОРІЯ ЧИСЕЛЬНОГО АНАЛІЗУ ================");

        double[] raw = {1.0, 0.0, 1.0};
        PolynomialFunction g = new PolynomialFunction(raw);
        raw[0] = 999; // перевірка захисного копіювання
        System.out.printf(Locale.US, "1. Многочлен g(x) = %s%n", g);
        System.out.printf(Locale.US, "   Значення g(2.0) = %.2f%n", g.evaluate(2.0));
        System.out.printf(Locale.US, "   Аналітична похідна g'(2.0) = %.6f | Чисельна = %.6f (Похибка: %.2e)%n",
                g.analyticDerivative(2.0), g.numericalDerivative(2.0), g.getDerivativeError(2.0));
        System.out.printf(Locale.US, "   Інтеграл на [0.00; 3.00] (метод трапецій, N=1000): %.4f (Точне значення: 12.0)%n",
                g.integrateTrapezoidal(0, 3, 1000));
        System.out.println("   Захисне копіювання: зовнішній масив змінено, g(0) = " + g.evaluate(0.0) + " (очікується 1.0)");

        SineWaveFunction f = new SineWaveFunction(2.0, 1.0, 0.0);
        System.out.printf(Locale.US, "%n2. Гармонічна функція f(x) = %s%n", f);
        System.out.printf(Locale.US, "   Інтеграл на [0.00; %.4f] (N=1000): %.4f%n",
                Math.PI, f.integrateTrapezoidal(0, Math.PI, 1000));

        CompositeFunction1D h = new CompositeFunction1D(f, g);
        double x = 1.5;
        double u = g.evaluate(x);
        double analytic = h.analyticDerivative(x);
        double numeric = h.numericalDerivative(x);
        double err = h.getDerivativeError(x);
        System.out.printf(Locale.US, "%n3. Суперпозиція (Композиція об'єктів) H(x) = f(g(x)) = 2*sin(x^2 + 1):%n");
        System.out.printf(Locale.US, "   Назва: %s%n", h);
        System.out.printf(Locale.US, "   Точка x = %.2f:%n", x);
        System.out.printf(Locale.US, "   -> H(%.2f) = 2 * sin(%.2f) = %.6f%n", x, u, h.evaluate(x));
        System.out.printf(Locale.US, "   -> Аналітична похідна H'(%.2f) за правилом ланцюга: %.6f%n", x, analytic);
        System.out.printf(Locale.US, "   -> Чисельна похідна H'(%.2f) через кінцеву різницю: %.6f%n", x, numeric);
        System.out.printf(Locale.US, "   -> Розбіжність між формулами: %.2e (%s)%n", err,
                err < 1e-9 ? "ІДЕАЛЬНИЙ ЗБІГ!" : "похибка більша за 1e-9");
        System.out.println("================================================================");
    }

    // ---- допоміжні класи завдання ----

    /** Абстрактна функція однієї змінної з шаблонними методами аналізу. */
    static abstract class AbstractFunction1D {
        private final String expressionName;

        public AbstractFunction1D(String expressionName) {
            this.expressionName = (expressionName == null || expressionName.isBlank()) ? "f(x)" : expressionName;
        }

        public String getExpressionName() {
            return expressionName;
        }

        public abstract double evaluate(double x);

        public abstract double analyticDerivative(double x);

        /** Шаблонний метод: центральна різниця, h = 1e-5. */
        public final double numericalDerivative(double x) {
            double h = 1e-5;
            return (evaluate(x + h) - evaluate(x - h)) / (2 * h);
        }

        public final double getDerivativeError(double x) {
            return Math.abs(analyticDerivative(x) - numericalDerivative(x));
        }

        /** Шаблонний метод: інтеграл методом трапецій. */
        public final double integrateTrapezoidal(double a, double b, int intervals) {
            int n = (intervals <= 0) ? 1000 : intervals;
            double dx = (b - a) / n;
            double sum = (evaluate(a) + evaluate(b)) / 2.0;
            for (int i = 1; i < n; i++) {
                sum += evaluate(a + i * dx);
            }
            return sum * dx;
        }

        @Override
        public String toString() {
            return expressionName;
        }
    }

    static class PolynomialFunction extends AbstractFunction1D {
        private final double[] coeffs;

        public PolynomialFunction(double... coeffs) {
            super(buildName(coeffs));
            // Захисна копія на вході
            this.coeffs = (coeffs == null || coeffs.length == 0) ? new double[]{0.0} : Arrays.copyOf(coeffs, coeffs.length);
        }

        private static String buildName(double[] c) {
            if (c == null || c.length == 0) {
                return "0.00";
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < c.length; i++) {
                if (i > 0) {
                    sb.append(" + ");
                }
                sb.append(String.format(Locale.US, "%.2f", c[i]));
                if (i == 1) {
                    sb.append("*x");
                } else if (i > 1) {
                    sb.append("*x^").append(i);
                }
            }
            return sb.toString();
        }

        // Захисна копія на виході
        public double[] getCoefficients() {
            return Arrays.copyOf(coeffs, coeffs.length);
        }

        @Override
        public double evaluate(double x) {
            double result = 0;
            for (int i = coeffs.length - 1; i >= 0; i--) { // схема Горнера
                result = result * x + coeffs[i];
            }
            return result;
        }

        @Override
        public double analyticDerivative(double x) {
            double result = 0;
            for (int i = coeffs.length - 1; i >= 1; i--) {
                result = result * x + i * coeffs[i];
            }
            return result;
        }
    }

    static class SineWaveFunction extends AbstractFunction1D {
        private final double amplitude;
        private final double omega;
        private final double phase;

        public SineWaveFunction(double amplitude, double omega, double phase) {
            super(String.format(Locale.US, "%.2f*sin(%.2f*x + %.2f)", amplitude, omega, phase));
            this.amplitude = amplitude;
            this.omega = omega;
            this.phase = phase;
        }

        @Override
        public double evaluate(double x) {
            return amplitude * Math.sin(omega * x + phase);
        }

        @Override
        public double analyticDerivative(double x) {
            return amplitude * omega * Math.cos(omega * x + phase);
        }
    }

    /** Суперпозиція H(x) = f(g(x)): IS-A функція і водночас HAS-A outer та inner. */
    static class CompositeFunction1D extends AbstractFunction1D {
        private final AbstractFunction1D outer;
        private final AbstractFunction1D inner;

        public CompositeFunction1D(AbstractFunction1D outer, AbstractFunction1D inner) {
            super("(" + requireNonNull(outer).getExpressionName() + " ∘ " + requireNonNull(inner).getExpressionName() + ")");
            this.outer = outer;
            this.inner = inner;
        }

        private static AbstractFunction1D requireNonNull(AbstractFunction1D fn) {
            if (fn == null) {
                throw new IllegalArgumentException("Функція не може бути null");
            }
            return fn;
        }

        @Override
        public double evaluate(double x) {
            return outer.evaluate(inner.evaluate(x));
        }

        @Override
        public double analyticDerivative(double x) {
            // Правило ланцюга: H'(x) = f'(g(x)) * g'(x)
            return outer.analyticDerivative(inner.evaluate(x)) * inner.analyticDerivative(x);
        }
    }
}
