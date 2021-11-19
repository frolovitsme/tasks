import util.ArrayUtils;
import util.FileUtils;
import util.JFrameUtils;
import util.JTableUtils;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.ParseException;

public class Main {

    private static final String inputFileName = ".\\input2.txt";
    private static final String outputFileName = ".\\output2.txt";

    public static void main(String[] args) throws Exception {
        int[][] array = FileUtils.readIntArray2FromFile(inputFileName);
        int[][] newArray = ArrayUtils.filterArray(array);
        createTableWithData(newArray);
    }

    /**
     * Создание таблицы с данными из файла
     *
     * @param array
     */
    private static void createTableWithData(int[][] array) {
        JFrame f = new JFrame();
        JPanel panel = new JPanel();
        JTable jt = new JTable();
        JTableUtils.writeArrayToJTable(jt, array);
        panel.add(jt);
        JButton jb = new JButton("Save data to file");
        panel.add(jb);
        f.add(panel);
        saveDataToFile(jt, jb);
        JFrameUtils.setParams(f);
    }

    /**
     * Обработчик для кнопки. Считывает данные с таблицы и сохраняет в файл
     *
     * @param jt
     * @param jb
     */
    private static void saveDataToFile(JTable jt, JButton jb) {
        ActionListener listener = e -> {
            try {
                int[][] data = JTableUtils.readIntMatrixFromJTable(jt);
                FileUtils.writeArrayToFile(outputFileName, data);
            } catch (FileNotFoundException | ParseException ex) {
                ex.printStackTrace();
            }
        };
        jb.addActionListener(listener);
    }
}
