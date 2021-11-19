package util;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {

    public static void fillArray(int[][] array,
                                 List<Integer> indexRows,
                                 List<Integer> indexColumn,
                                 int[][] newArray) {
        int k = 0;
        int n = 0;
        for (int row = 0; row < array.length; row++) {
            if (indexRows.contains(row)) {
                continue;
            }
            for (int col = 0; col < array[0].length; col++) {
                if (indexColumn.contains(col)) {
                    continue;
                }
                newArray[k][n] = array[row][col];
                n++;
            }
            n = 0;
            k++;
        }
    }

    public static List<Integer> getIndexRows(int[][] array) {
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

    public static List<Integer> getIndexColumns(int[][] array) {
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
}
