import utils.ArrayUtils;
import utils.FileUtils;
import utils.InputArgs;

import java.util.Arrays;

public class Main8_1 {

    public static void main(String[] args) throws Exception {
        InputArgs inputArgs = InputArgs.parseCmdArgs(args);
        int[][] array = FileUtils.readIntArray2FromFile(inputArgs.getInputFile());
        int[][] newArray = ArrayUtils.filterArray(array);
        FileUtils.writeArrayToFile(inputArgs.getOutputFile(), newArray);
        printResult(newArray);
    }

    private static void printResult(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }
}
