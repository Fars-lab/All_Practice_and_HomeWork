package semester1.semester1.practice09.homework;

import java.util.Locale;

public class Task4MatrixOperatorPipeline {
    public static void main(String[] args) {
        System.out.println("================ КОНВЕЄР МАТРИЧНИХ ОПЕРАТОРІВ ================");

        double[][] raw = {{1, 2}, {3, 4}};
        EncapsulatedMatrix x0 = new EncapsulatedMatrix(raw);
        raw[0][0] = 777; // зовнішня зміна не повинна вплинути на x0
        x0.getGridCopy()[1][1] = -555; // зміна копії теж
        System.out.println("[Захисне копіювання] X0[0][0] = " + x0.getElement(0, 0)
                + ", X0[1][1] = " + x0.getElement(1, 1) + " (очікується 1.0 і 4.0)");
        System.out.println();
        System.out.println("[ВХІДНА МАТРИЦЯ X0 (" + x0.describe() + ")]:");
        System.out.println(x0.rowsToString());

        EncapsulatedMatrix b = new EncapsulatedMatrix(new double[][]{{1, 0}, {1, 1}});

        MatrixPipeline pipeline = new MatrixPipeline("Головний конвеєр", 5);
        pipeline.addStage(new ScaleMatrixOperator(2.0));
        pipeline.addStage(new TransposeMatrixOperator());
        pipeline.addStage(new DiagonalShiftOperator(3.0));
        pipeline.addStage(new MultiplyByMatrixOperator(b));

        EncapsulatedMatrix result = pipeline.runPipeline(x0);

        System.out.println();
        System.out.println("[Демонстрація executeSafely] Множення на матрицю 3x3 (несумісні розміри):");
        EncapsulatedMatrix unchanged = new MultiplyByMatrixOperator(
                new EncapsulatedMatrix(new double[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}})).executeSafely(result);
        System.out.println("Матриця лишилась без змін: " + (unchanged == result));
        System.out.println("==============================================================");
    }

    // ---- допоміжні класи завдання ----

    /** Незмінна суворо інкапсульована матриця. */
    static class EncapsulatedMatrix {
        private final int rows;
        private final int cols;
        private final double[][] grid;

        public EncapsulatedMatrix(double[][] source) {
            if (source == null || source.length == 0 || source[0] == null || source[0].length == 0) {
                throw new IllegalArgumentException("Матриця не може бути порожньою");
            }
            this.rows = source.length;
            this.cols = source[0].length;
            this.grid = new double[rows][cols];
            for (int i = 0; i < rows; i++) {
                if (source[i] == null || source[i].length != cols) {
                    throw new IllegalArgumentException("Рядки матриці мають різну довжину");
                }
                // Глибоке захисне копіювання кожного рядка
                System.arraycopy(source[i], 0, grid[i], 0, cols);
            }
        }

        public int getRows() {
            return rows;
        }

        public int getCols() {
            return cols;
        }

        public boolean isSquare() {
            return rows == cols;
        }

        public double[][] getGridCopy() {
            double[][] copy = new double[rows][cols];
            for (int i = 0; i < rows; i++) {
                System.arraycopy(grid[i], 0, copy[i], 0, cols);
            }
            return copy;
        }

        public double getElement(int r, int c) {
            if (r < 0 || r >= rows || c < 0 || c >= cols) {
                throw new IndexOutOfBoundsException("Індекс (" + r + ", " + c + ") поза межами матриці");
            }
            return grid[r][c];
        }

        public double getTrace() {
            if (!isSquare()) {
                return Double.NaN;
            }
            double sum = 0;
            for (int i = 0; i < rows; i++) {
                sum += grid[i][i];
            }
            return sum;
        }

        public double getFrobeniusNorm() {
            double sum = 0;
            for (double[] row : grid) {
                for (double v : row) {
                    sum += v * v;
                }
            }
            return Math.sqrt(sum);
        }

        /** Визначник для 2x2 та 3x3 (правило Саррюса); для інших розмірів — NaN. */
        public double getDeterminant() {
            if (!isSquare()) {
                return Double.NaN;
            }
            if (rows == 2) {
                return grid[0][0] * grid[1][1] - grid[0][1] * grid[1][0];
            }
            if (rows == 3) {
                return grid[0][0] * grid[1][1] * grid[2][2]
                        + grid[0][1] * grid[1][2] * grid[2][0]
                        + grid[0][2] * grid[1][0] * grid[2][1]
                        - grid[0][2] * grid[1][1] * grid[2][0]
                        - grid[0][0] * grid[1][2] * grid[2][1]
                        - grid[0][1] * grid[1][0] * grid[2][2];
            }
            return Double.NaN;
        }

        public String describe() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format(Locale.US, "%dx%d | Норма Фробеніуса: %.2f", rows, cols, getFrobeniusNorm()));
            if (isSquare()) {
                sb.append(String.format(Locale.US, " | Слід: %.2f", getTrace()));
                double det = getDeterminant();
                if (!Double.isNaN(det)) {
                    sb.append(String.format(Locale.US, " | det: %.2f", det));
                }
            }
            return sb.toString();
        }

        public String rowsToString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < rows; i++) {
                sb.append("  [");
                for (int j = 0; j < cols; j++) {
                    sb.append(String.format(Locale.US, "%s%7.2f", j == 0 ? "" : " ", grid[i][j]));
                }
                sb.append(" ]");
                if (i < rows - 1) {
                    sb.append(System.lineSeparator());
                }
            }
            return sb.toString();
        }

        @Override
        public String toString() {
            return "Матриця (" + describe() + "):" + System.lineSeparator() + rowsToString();
        }
    }

    static abstract class AbstractMatrixOperator {
        private final String operatorName;

        public AbstractMatrixOperator(String operatorName) {
            this.operatorName = operatorName;
        }

        public String getOperatorName() {
            return operatorName;
        }

        public abstract boolean isApplicable(EncapsulatedMatrix input);

        public abstract EncapsulatedMatrix apply(EncapsulatedMatrix input);

        /** Шаблонний метод: перевірка + застосування. */
        public final EncapsulatedMatrix executeSafely(EncapsulatedMatrix input) {
            if (input != null && isApplicable(input)) {
                return apply(input);
            }
            System.out.println("⚠️ Оператор '" + operatorName + "' не можна застосувати до цієї матриці. Пропущено.");
            return input;
        }
    }

    static class ScaleMatrixOperator extends AbstractMatrixOperator {
        private final double scaleFactor;

        public ScaleMatrixOperator(double scaleFactor) {
            super(String.format(Locale.US, "Scale(x%.1f)", scaleFactor));
            this.scaleFactor = scaleFactor;
        }

        @Override
        public boolean isApplicable(EncapsulatedMatrix input) {
            return true;
        }

        @Override
        public EncapsulatedMatrix apply(EncapsulatedMatrix input) {
            double[][] g = input.getGridCopy();
            for (double[] row : g) {
                for (int j = 0; j < row.length; j++) {
                    row[j] *= scaleFactor;
                }
            }
            return new EncapsulatedMatrix(g);
        }
    }

    static class TransposeMatrixOperator extends AbstractMatrixOperator {
        public TransposeMatrixOperator() {
            super("Transpose(A^T)");
        }

        @Override
        public boolean isApplicable(EncapsulatedMatrix input) {
            return true;
        }

        @Override
        public EncapsulatedMatrix apply(EncapsulatedMatrix input) {
            double[][] t = new double[input.getCols()][input.getRows()];
            for (int i = 0; i < input.getRows(); i++) {
                for (int j = 0; j < input.getCols(); j++) {
                    t[j][i] = input.getElement(i, j);
                }
            }
            return new EncapsulatedMatrix(t);
        }
    }

    static class DiagonalShiftOperator extends AbstractMatrixOperator {
        private final double lambda;

        public DiagonalShiftOperator(double lambda) {
            super(String.format(Locale.US, "DiagonalShift(%+.1f*I)", lambda));
            this.lambda = lambda;
        }

        @Override
        public boolean isApplicable(EncapsulatedMatrix input) {
            return input.isSquare();
        }

        @Override
        public EncapsulatedMatrix apply(EncapsulatedMatrix input) {
            double[][] g = input.getGridCopy();
            for (int i = 0; i < g.length; i++) {
                g[i][i] += lambda;
            }
            return new EncapsulatedMatrix(g);
        }
    }

    /** Оператор, що компонує всередині праву матрицю B. */
    static class MultiplyByMatrixOperator extends AbstractMatrixOperator {
        private final EncapsulatedMatrix rightMatrix;

        public MultiplyByMatrixOperator(EncapsulatedMatrix rightMatrix) {
            super("MultiplyBy(B " + rightMatrix.getRows() + "x" + rightMatrix.getCols() + ")");
            this.rightMatrix = rightMatrix; // незмінний об'єкт — безпечно зберігати посилання
        }

        @Override
        public boolean isApplicable(EncapsulatedMatrix input) {
            return input.getCols() == rightMatrix.getRows();
        }

        @Override
        public EncapsulatedMatrix apply(EncapsulatedMatrix input) {
            int m = input.getRows();
            int n = rightMatrix.getCols();
            int kMax = input.getCols();
            double[][] c = new double[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    double s = 0;
                    for (int k = 0; k < kMax; k++) {
                        s += input.getElement(i, k) * rightMatrix.getElement(k, j);
                    }
                    c[i][j] = s;
                }
            }
            return new EncapsulatedMatrix(c);
        }
    }

    static class MatrixPipeline {
        private final String pipelineName;
        private final AbstractMatrixOperator[] stages;
        private int stageCount;

        public MatrixPipeline(String pipelineName, int capacity) {
            this.pipelineName = pipelineName;
            this.stages = new AbstractMatrixOperator[Math.max(1, capacity)];
        }

        public void addStage(AbstractMatrixOperator op) {
            if (op == null) {
                System.out.println("⚠️ Порожній оператор не додано до конвеєра '" + pipelineName + "'");
                return;
            }
            if (stageCount >= stages.length) {
                System.out.println("⚠️ Конвеєр '" + pipelineName + "' переповнений");
                return;
            }
            stages[stageCount++] = op;
        }

        public EncapsulatedMatrix runPipeline(EncapsulatedMatrix initial) {
            EncapsulatedMatrix current = initial;
            for (int i = 0; i < stageCount; i++) {
                boolean ok = current != null && stages[i].isApplicable(current);
                current = stages[i].executeSafely(current);
                System.out.println();
                System.out.println("---> Етап " + (i + 1) + ": Оператор '" + stages[i].getOperatorName() + "' "
                        + (ok ? "виконано успішно:" : "пропущено (непридатний):"));
                System.out.println(current);
            }
            return current;
        }
    }
}
