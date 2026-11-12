package semester1.semester1.practice10.homework;

public class Task4PolymorphicMatrixSpectralEngine {

    public static final class ImmutableMatrix2D {
        private final int rows;
        private final int cols;
        private final double[][] data;

        public ImmutableMatrix2D(double[][] source) {
            if (source == null || source.length == 0 || source[0].length == 0) {
                throw new IllegalArgumentException("Матриця не може бути порожньою");
            }
            this.rows = source.length;
            this.cols = source[0].length;
            this.data = new double[rows][cols];
            for (int i = 0; i < rows; i++) {
                if (source[i].length != cols) {
                    throw new IllegalArgumentException("Нерівна кількість стовпців");
                }
                System.arraycopy(source[i], 0, this.data[i], 0, cols);
            }
        }

        public int getRows() { return rows; }
        public int getCols() { return cols; }
        public double get(int r, int c) { return data[r][c]; }

        public double[][] getDataCopy() {
            double[][] copy = new double[rows][cols];
            for (int i = 0; i < rows; i++) {
                System.arraycopy(data[i], 0, copy[i], 0, cols);
            }
            return copy;
        }

        public double getTrace() {
            int n = Math.min(rows, cols);
            double trace = 0.0;
            for (int i = 0; i < n; i++) trace += data[i][i];
            return trace;
        }

        public double getFrobeniusNorm() {
            double sumSq = 0.0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    sumSq += data[i][j] * data[i][j];
                }
            }
            return Math.sqrt(sumSq);
        }

        public double getDeterminant() {
            if (rows != cols) throw new IllegalStateException("Визначник існує лише для квадратних матриць");
            if (rows == 2) {
                return data[0][0] * data[1][1] - data[0][1] * data[1][0];
            } else if (rows == 3) {
                return data[0][0] * (data[1][1] * data[2][2] - data[1][2] * data[2][1])
                     - data[0][1] * (data[1][0] * data[2][2] - data[1][2] * data[2][0])
                     + data[0][2] * (data[1][0] * data[2][1] - data[1][1] * data[2][0]);
            }
            throw new UnsupportedOperationException("Підтримуються лише 2x2 та 3x3");
        }

        public double maxAbsDifference(ImmutableMatrix2D other) {
            if (this.rows != other.rows || this.cols != other.cols) {
                throw new IllegalArgumentException("Розміри матриць не збігаються");
            }
            double maxDiff = 0.0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    maxDiff = Math.max(maxDiff, Math.abs(this.data[i][j] - other.data[i][j]));
                }
            }
            return maxDiff;
        }

        public void printMatrix() {
            for (int i = 0; i < rows; i++) {
                System.out.print("  [ ");
                for (int j = 0; j < cols; j++) {
                    System.out.printf("%8.4f ", data[i][j]);
                }
                System.out.println("]");
            }
        }
    }

    interface InvertibleOperator {
        ImmutableMatrix2D applyInverse(ImmutableMatrix2D transformed);
    }

    interface OrthogonalOperator extends InvertibleOperator {
        default boolean verifyNormInvariance(ImmutableMatrix2D before, ImmutableMatrix2D after) {
            return Math.abs(before.getFrobeniusNorm() - after.getFrobeniusNorm()) < 1e-6;
        }
    }

    static abstract class AbstractMatrixOperator {
        private final String operatorName;

        public AbstractMatrixOperator(String operatorName) {
            this.operatorName = operatorName;
        }

        public String getOperatorName() { return operatorName; }
        public abstract boolean canApply(ImmutableMatrix2D m);
        protected abstract ImmutableMatrix2D transform(ImmutableMatrix2D m);

        public final ImmutableMatrix2D execute(ImmutableMatrix2D m) {
            if (m == null || !canApply(m)) {
                throw new IllegalArgumentException("Оператор " + operatorName + " не застосовний до цієї матриці");
            }
            ImmutableMatrix2D result = transform(m);
            if (this instanceof OrthogonalOperator ort && !ort.verifyNormInvariance(m, result)) {
                throw new IllegalStateException("Порушено інваріантність норми Фробеніуса!");
            }
            return result;
        }
    }

    static class NonZeroScaleOperator extends AbstractMatrixOperator implements InvertibleOperator {
        private final double factor;

        public NonZeroScaleOperator(double factor) {
            super(String.format("Scale(x%.1f)", factor));
            if (Math.abs(factor) < 1e-12) throw new IllegalArgumentException("Множник не може бути нульовим");
            this.factor = factor;
        }

        @Override public boolean canApply(ImmutableMatrix2D m) { return true; }

        @Override
        protected ImmutableMatrix2D transform(ImmutableMatrix2D m) {
            return scaleBy(m, factor);
        }

        @Override
        public ImmutableMatrix2D applyInverse(ImmutableMatrix2D transformed) {
            return scaleBy(transformed, 1.0 / factor);
        }

        private ImmutableMatrix2D scaleBy(ImmutableMatrix2D m, double k) {
            double[][] res = m.getDataCopy();
            for (int i = 0; i < m.getRows(); i++) {
                for (int j = 0; j < m.getCols(); j++) {
                    res[i][j] *= k;
                }
            }
            return new ImmutableMatrix2D(res);
        }
    }

    static class TransposeOperator extends AbstractMatrixOperator implements OrthogonalOperator {
        public TransposeOperator() {
            super("Transpose(A^T)");
        }

        @Override public boolean canApply(ImmutableMatrix2D m) { return true; }

        @Override
        protected ImmutableMatrix2D transform(ImmutableMatrix2D m) {
            double[][] res = new double[m.getCols()][m.getRows()];
            for (int i = 0; i < m.getRows(); i++) {
                for (int j = 0; j < m.getCols(); j++) {
                    res[j][i] = m.get(i, j);
                }
            }
            return new ImmutableMatrix2D(res);
        }

        @Override
        public ImmutableMatrix2D applyInverse(ImmutableMatrix2D transformed) {
            return transform(transformed);
        }
    }

    static class GivensRotationOperator extends AbstractMatrixOperator implements OrthogonalOperator {
        private final double angleRadians;

        public GivensRotationOperator(double angleDegrees) {
            super(String.format("GivensRotation(%.1f°)", angleDegrees));
            this.angleRadians = Math.toRadians(angleDegrees);
        }

        @Override public boolean canApply(ImmutableMatrix2D m) { return m.getRows() >= 2; }

        @Override
        protected ImmutableMatrix2D transform(ImmutableMatrix2D m) {
            return rotateRows(m, angleRadians);
        }

        @Override
        public ImmutableMatrix2D applyInverse(ImmutableMatrix2D transformed) {
            return rotateRows(transformed, -angleRadians);
        }

        private ImmutableMatrix2D rotateRows(ImmutableMatrix2D m, double theta) {
            double[][] res = m.getDataCopy();
            double c = Math.cos(theta);
            double s = Math.sin(theta);
            for (int j = 0; j < m.getCols(); j++) {
                double r0 = res[0][j];
                double r1 = res[1][j];
                res[0][j] = c * r0 - s * r1;
                res[1][j] = s * r0 + c * r1;
            }
            return new ImmutableMatrix2D(res);
        }
    }

    static class UpperTriangularProjection extends AbstractMatrixOperator {
        public UpperTriangularProjection() {
            super("UpperTriangularProjection");
        }

        @Override public boolean canApply(ImmutableMatrix2D m) { return m.getRows() == m.getCols(); }

        @Override
        protected ImmutableMatrix2D transform(ImmutableMatrix2D m) {
            double[][] res = m.getDataCopy();
            for (int i = 0; i < m.getRows(); i++) {
                for (int j = 0; j < i; j++) {
                    res[i][j] = 0.0;
                }
            }
            return new ImmutableMatrix2D(res);
        }
    }

    public static void main(String[] args) {
        System.out.println("================ СПЕКТРАЛЬНИЙ КОНВЕЄР МАТРИЧНИХ ОПЕРАТОРІВ ================");
        ImmutableMatrix2D x0 = new ImmutableMatrix2D(new double[][]{
            {3.0, 4.0},
            {0.0, 5.0}
        });

        System.out.printf("[ПОЧАТКОВА МАТРИЦЯ X0 (2x2 | Норма: %.4f | Слід: %.2f | det: %.2f)]:%n",
                x0.getFrobeniusNorm(), x0.getTrace(), x0.getDeterminant());
        x0.printMatrix();

        AbstractMatrixOperator[] pipeline = {
            new NonZeroScaleOperator(2.0),
            new TransposeOperator(),
            new GivensRotationOperator(60.0)
        };

        System.out.println("ПРЯМИЙ ХІД КОНВЕЄРА (Forward Pass через final execute()) ---");
        ImmutableMatrix2D current = x0;
        for (int i = 0; i < pipeline.length; i++) {
            AbstractMatrixOperator op = pipeline[i];
            current = op.execute(current);
            String ortNote = (op instanceof OrthogonalOperator)
                    ? String.format(" [Orthogonal: Норма %.4f збережена]", current.getFrobeniusNorm())
                    : String.format(" -> Норма: %.4f", current.getFrobeniusNorm());
            System.out.printf("%d. Оператор '%s'%s | det: %.2f%n",
                    i + 1, op.getOperatorName(), ortNote, current.getDeterminant());
        }

        System.out.println("[ЗАШИФРОВАНА МАТРИЦЯ Y ПІСЛЯ 3 ЕТАПІВ]:");
        current.printMatrix();

        System.out.println(" --- ЗВОРОТНИЙ ХІД ВІДНОВЛЕННЯ (Inverse Pass через instanceof InvertibleOperator) ---");
        for (int i = pipeline.length - 1; i >= 0; i--) {
            if (pipeline[i] instanceof InvertibleOperator inv) {
                current = inv.applyInverse(current);
                System.out.printf("<- Декодування '%s' виконано...%n", pipeline[i].getOperatorName());
            }
        }

        System.out.println(":");
        current.printMatrix();
        double diff = x0.maxAbsDifference(current);
        System.out.printf("Максимальна похибка відновлення |X0 - X_restored|: %.2e%n", diff);
        System.out.println("===========================================================================");
    }
}
