import java.util.Scanner;

public class Main {

    private static double x;
    private static double n;
    private static double e;

    public static void main(String[] args) {
        setParameters();
        calculateSum();
        calculateFuncValue();
    }

    private static void setParameters() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input x: ");
        x = scanner.nextDouble();
        System.out.println("Input n: ");
        n = scanner.nextDouble();
        System.out.println("Input e: ");
        e = scanner.nextDouble();
    }

    private static void calculateSum() {
        double sumN = 0;
        double sumE = 0;
        double sumE01 = 0;
        for (int i = 1; i <= n; i++) {
            double a = Math.pow(-1, i) * (Math.pow(x, 2 * i) / getFactorial(i));
            sumN += a;
            if (Math.abs(a) > e) sumE += a;
            if (Math.abs(a) > e / 10) sumE01 += a;
        }
        System.out.println("sumN: " + sumN);
        System.out.println("sumE: " + sumE);
        System.out.println("sumE01: " + sumE01);
    }

    private static double getFactorial(double f) {
        double result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }

    private static void calculateFuncValue() {
        double funcValue = Math.exp(-Math.pow(x, 2));
        System.out.println("funcValue: " + funcValue);
    }
}
