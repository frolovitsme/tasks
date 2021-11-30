import util.InputArgs;
import util.JTableUtils;

public class Main8_2 {

    public static void main(String[] args) throws Exception {
        InputArgs inputArgs = InputArgs.parseCmdArgs(args);
        JTableUtils.createTable(inputArgs);
    }
}
