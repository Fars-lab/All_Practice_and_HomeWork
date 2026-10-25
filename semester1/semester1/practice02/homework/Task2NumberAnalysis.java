package semester1.semester1.practice02.homework;

import java.util.Scanner;

public class Task2NumberAnalysis {
    static void main() {
        int  number = 742;
        int hundreds = number / 100;
        int tens = number / 10;
        int untis = number / 1;
        int sum = hundreds + tens + untis;
        int product = hundreds * tens * untis;
        int reversedNumber = untis * 100 + tens * 10 + hundreds;
        boolean isSumEven = sum % 2 == 0;
        System.out.println("Початкове число:         " + number);
        System.out.println("Розряди:                 Сотні =  " + hundreds + ", Десятки = " + tens + ", Одиниці = " + untis);
        System.out.println("Сума цифри:              " + sum);
        System.out.println("Добуток цифр:            " + product);
        System.out.println("Перевернуте число:       " +  reversedNumber);
        System.out.println("Чи є сума цифр парною?   " + isSumEven);
    }
}
