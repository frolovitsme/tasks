package util;

import java.util.Arrays;
import java.util.List;

public class InputArgs {
    private final List<String> SHORT_COMMANDS = Arrays.asList(
            "-i",
            "-o"
    );
    private final List<String> FULL_COMMANDS = Arrays.asList(
            "--input-file",
            "--output-file"
    );
    private String inputFile;
    private String outputFile;
    private final String REGEXP = "[\\=]";

    private boolean contains(final String[] array, final List<String> values) {
        int count = 0;
        boolean flag = false;
        for (String value : values) {
            if (Arrays.asList(array).contains(value)) {
                count++;
            } else {
                String[] part = value.split(REGEXP);
                if (Arrays.stream(array).anyMatch(s -> s.contains(part[0]))) {
                    count++;
                }
            }
            if (count == values.size()) {
                flag = true;
            }
        }
        return flag;
    }

    public void parseCmdArgs(String[] args) throws Exception {
        if (contains(args, SHORT_COMMANDS) && args.length == 4) {
            if (args[1].contains(".txt") && args[3].contains(".txt")) {
                setInputFile(args[1]);
                setOutputFile(args[3]);
            } else {
                throw new Exception("Input or output files missing");
            }
        } else if (contains(args, FULL_COMMANDS) && args.length == 2) {
            String inputFile = args[0].substring(FULL_COMMANDS.get(0).length());
            String outputFile = args[1].substring(FULL_COMMANDS.get(1).length());
            if (inputFile.contains(".txt") && outputFile.contains(".txt")) {
                setInputFile(inputFile);
                setOutputFile(outputFile);
            } else {
                throw new Exception("Input or output files missing");
            }
        } else {
            throw new Exception("Command not found");
        }
    }

    public String getInputFile() {
        return inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }
}
