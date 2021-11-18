import java.util.Arrays;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        int[] array = {-2, 3, -1, 4, 3, -9};
        System.out.println("Массив: " + Arrays.toString(array));
        solution(array);
    }

    private static void solution(int[] array) {
        int[] maxValues = getMaxValues(array);
        int maxPositiveValue = maxValues[0];
        int maxNegativeValue = maxValues[1];
//        System.out.println("maxPositiveValue = " + maxPositiveValue);
//        System.out.println("maxNegativeValue = (-)" + maxNegativeValue);

        int[][] counters = getCounters(array, maxPositiveValue, maxNegativeValue);
        int[] positiveCounters = counters[0];
        int[] negativeCounters = counters[1];
//        System.out.println("positiveCounters = " + Arrays.toString(positiveCounters));
//        System.out.println("negativeCounters = " + Arrays.toString(negativeCounters));

        int popularPositive = getPopular(positiveCounters);
//        System.out.println("popularPositive = " + popularPositive);
        int popularNegative = getPopular(negativeCounters);
//        System.out.println("popularNegative = (-)" + popularNegative);

        if (popularPositive != -1 && popularNegative == -1) {
            int index = getIndex(array, popularPositive);
            System.out.println("Позиция второго с конца самого популярного элемента = " + index);
        } else if (popularNegative != -1 && popularPositive == -1) {
            int index = getIndex(array, (-1 * popularNegative));
            System.out.println("Позиция второго с конца самого популярного элемента = " + index);
        } else if (popularPositive == -1 && popularNegative == -1) {
            System.out.println(-1);
        } else if (positiveCounters[popularPositive] == negativeCounters[popularNegative]) {
            System.out.println("Наибольший по значению = " + popularPositive);
            System.out.println("Наибольший по абсолютному значению = " + (popularPositive >= popularNegative ? popularPositive : "(-)" + popularNegative));
        } else {
            System.out.println("Наибольший по значению = " + popularPositive);
        }

    }

    private static int[] getMaxValues(int[] array) {
        int maxPositiveValue = array[0];
        int maxNegativeValue = array[0];
        int current;
        int abs;

        for (int i = 0; i < array.length; i++) {
            current = array[i];
            if (current >= 0) {
                if (current > maxPositiveValue) {
                    maxPositiveValue = current;
                }
            } else {
                abs = Math.abs(current);
                if (abs > maxNegativeValue) {
                    maxNegativeValue = abs;
                }
            }
        }
        return new int[]{maxPositiveValue, maxNegativeValue};
    }

    private static int[][] getCounters(int[] array, int maxPositiveValue, int maxNegativeValue) {
        int[] positiveCounters = new int[maxPositiveValue + 1];
        int[] negativeCounters = new int[maxNegativeValue + 1];
        int current;
        int abs;

        for (int i = 0; i < array.length; i++) {
            current = array[i];
            abs = Math.abs(current);
            if (current >= 0) {
                positiveCounters[current]++;
            } else {
                negativeCounters[abs]++;
            }
        }
        return new int[][]{positiveCounters, negativeCounters};
    }

    private static int getPopular(int[] counters) {
        int popular = counters[0];
        int current;
        int count = 0;

        for (int i = 0; i < counters.length; i++) {
            current = counters[i];
            if (current > count) {
                popular = i;
                count = current;
            }
            if (current == count && count == 1) {
                popular = -1;
            }
            if (current == count && count > 1 && i > popular) {
                popular = i;
            }
        }
        return popular;
    }

    private static int getIndex(int[] array, int value) {
        int current;
        int[] pair = {0, 0};

        for (int i = 0; i < array.length; i++) {
            current = array[i];
            if (current == value) {
                pair[0] = pair[1];
                pair[1] = i;
            }
        }
        return pair[0];
    }
}
