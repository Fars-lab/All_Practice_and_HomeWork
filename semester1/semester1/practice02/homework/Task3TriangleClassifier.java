package semester1.semester1.practice02.homework;

public class Task3TriangleClassifier {
    static void main() {
        double a = 3.0; double b = 4.0; double c = 5.0;
        boolean exists = (a + b > c) && (a + c > b) && (b + c > a);
        boolean isEquilateral = (a == b) && (a == c);
        boolean isScalene = (a == b) || (b == c) || (a == c);
        boolean isRightAngled = (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)) ||
                (Math.pow(a, 2) + Math.pow(c, 2) == Math.pow(b, 2)) ||
                (Math.pow(b, 2) + Math.pow(c, 2) == Math.pow(a, 2));
        System.out.println("Сторони трикутника: a= " + a + " b= " + b + " c= " + c);
        System.out.println("-----------------------");
        System.out.println("Чи існує такий трикутник:  " + exists);
        System.out.println("Чи є рівностороннім:       " + isEquilateral);
        System.out.println("Чи є рівнобедреним:        " + isScalene);
        System.out.println("Чи є прямокутним:          " + isRightAngled);
    }
}
