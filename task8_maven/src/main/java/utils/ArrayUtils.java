package utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {

    /**
     * Заполнение массива
     *
     * @param array
     * @param indexRows
     * @param indexColumns
     * @param newArray
     */
    private static void fillArray(int[][] array,
                                  List<Integer> indexRows,
                                  List<Integer> indexColumns,
                                  int[][] newArray) {
        int k = 0;
        int n = 0;
        for (int row = 0; row < array.length; row++) {
            if (indexRows.contains(row)) {
                continue;
            }
            for (int col = 0; col < array[0].length; col++) {
                if (indexColumns.contains(col)) {
                    continue;
                }
                newArray[k][n] = array[row][col];
                n++;
            }
            n = 0;
            k++;
        }
    }

    /**
     * Поиск строк, состоящих из одинаковых элементов
     *
     * @param array
     * @return
     */
    private static List<Integer> getIndexRowsWithDuplicateValues(int[][] array) {
        int count = 0;
        List<Integer> list = new ArrayList<>();
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                if (array[row][col] == array[row][0]) {
                    count++;
                }
            }
            if (count == array[0].length) {
                list.add(row);
            } else
                count = 0;
        }
        return list;
    }

    /**
     * Поиск столбцов, состоящих из одинаковых элементов
     *
     * @param array
     * @return
     */
    private static List<Integer> getIndexColumnWithDuplicateValues(int[][] array) {
        int count = 0;
        List<Integer> list = new ArrayList<>();
        for (int col = 0; col < array[0].length; col++) {
            for (int row = 0; row < array.length; row++) {
                if (array[row][col] == array[0][col]) {
                    count++;
                }
            }
            if (count == array.length) {
                list.add(col);
            } else
                count = 0;
        }
        return list;
    }

    /**
     * Фильтрация массива
     *
     * @param array
     * @return
     */
    public static int[][] filterArray(int[][] array) {
        List<Integer> indexRows = getIndexRowsWithDuplicateValues(array);
        List<Integer> indexColumns = getIndexColumnWithDuplicateValues(array);
        int newCountRows = array.length - indexRows.size();
        int newCountColumns = array[0].length - indexColumns.size();
        int[][] newArray = new int[newCountRows][newCountColumns];
        fillArray(array, indexRows, indexColumns, newArray);
        return newArray;
    }
}
