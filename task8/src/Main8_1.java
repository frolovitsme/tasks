import util.ArrayUtils;
import util.FileUtils;
import util.InputArgs;

public class Main8_1 {

    public static void main(String[] args) throws Exception {
        InputArgs inputArgs = InputArgs.parseCmdArgs(args);
        int[][] array = FileUtils.readIntArray2FromFile(inputArgs.getInputFile());
        int[][] newArray = ArrayUtils.filterArray(array);
        FileUtils.writeArrayToFile(inputArgs.getOutputFile(), newArray);
    }
}
