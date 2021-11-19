import util.ArrayUtils;
import util.FileUtils;
import util.InputArgs;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        InputArgs inputArgs = new InputArgs();
        inputArgs.parseCmdArgs(args);
        int[][] array = FileUtils.readIntArray2FromFile(inputArgs.getInputFile());
//        for (int i = 0; i < array.length; i++) {
//            System.out.println(Arrays.toString(array[i]));
//        }
        List<Integer> indexRowsWithDuplicateValues = ArrayUtils.getIndexRows(array);
        List<Integer> indexColumnWithDuplicateValues = ArrayUtils.getIndexColumns(array);
        int newCountRows = array.length - indexRowsWithDuplicateValues.size();
        int newCountColumns = array[0].length - indexColumnWithDuplicateValues.size();
        int[][] newArray = new int[newCountRows][newCountColumns];
        ArrayUtils.fillArray(array, indexRowsWithDuplicateValues, indexColumnWithDuplicateValues, newArray);
//        for (int i = 0; i < newArray.length; i++) {
//            System.out.println(Arrays.toString(newArray[i]));
//        }
        FileUtils.writeArrayToFile(inputArgs.getOutputFile(), newArray);
    }
}
