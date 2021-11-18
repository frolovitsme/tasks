import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[][] array = {
                {1, 7, 3, 16},
                {1, 7, 7, 12},
                {1, 1, 2, 3},
                {1, 1, 1, 1}
        };
        List<Integer> indexRowsWithDuplicateValues = getIndexRows(array);
        List<Integer> indexColumnWithDuplicateValues = getIndexColumns(array);
        System.out.println("indexRows = " + indexRowsWithDuplicateValues);
        System.out.println("indexColumn = " + indexColumnWithDuplicateValues);
        int newCountRows = array.length - indexRowsWithDuplicateValues.size();
        int newCountColumns = array[0].length - indexColumnWithDuplicateValues.size();
        int[][] newArray = new int[newCountRows][newCountColumns];
        fillArray(array, indexRowsWithDuplicateValues, indexColumnWithDuplicateValues, newArray);
        for (int i = 0; i < newArray.length; i++) {
            System.out.println(Arrays.toString(newArray[i]));
        }
    }

    private static void fillArray(int[][] array,
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

    private static List<Integer> getIndexRows(int[][] array) {
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

    private static List<Integer> getIndexColumns(int[][] array) {
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
