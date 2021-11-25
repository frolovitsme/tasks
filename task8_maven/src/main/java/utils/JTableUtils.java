package utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.Arrays;
import java.util.function.Function;

public class JTableUtils {

    private static class JTableUtilsException extends ParseException {
        public JTableUtilsException(String s) {
            super(s, 0);
        }
    }

    /**
     * Запись данных из массива int[][] в JTable
     * (основная реализация, закрытый метод, используется в ArrayToGrid и Array2ToGrid)
     *
     * @param table
     * @param array
     */
    public static void writeArrayToJTable(JTable table, int[][] array) {
        writeArrayToJTable(table, array, "%d");
    }

    private static void writeArrayToJTable(JTable table, Object array, String itemFormat) {
        if (!array.getClass().isArray()) {
            return;
        }
        if (!(table.getModel() instanceof DefaultTableModel)) {
            return;
        }
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        if (itemFormat == null || itemFormat.length() == 0) {
            itemFormat = "%s";
        }

        int rank = 1;
        int len1 = Array.getLength(array), len2 = -1;
        if (len1 > 0) {
            for (int i = 0; i < len1; i++) {
                Object item = Array.get(array, i);
                if (item != null && item.getClass().isArray()) {
                    rank = 2;
                    len2 = Math.max(Array.getLength(item), len2);
                }
            }
        }
        tableModel.setRowCount(rank == 1 ? 1 : len1);
        tableModel.setColumnCount(rank == 1 ? len1 : len2);
        for (int i = 0; i < len1; i++) {
            if (rank == 1) {
                tableModel.setValueAt(String.format(itemFormat, Array.get(array, i)), 0, i);
            } else {
                Object line = Array.get(array, i);
                if (line != null) {
                    if (line.getClass().isArray()) {
                        int lineLen = Array.getLength(line);
                        for (int j = 0; j < lineLen; j++) {
                            tableModel.setValueAt(String.format(itemFormat, Array.get(line, j)), i, j);
                        }
                    } else {
                        tableModel.setValueAt(String.format(itemFormat, Array.get(array, i)), 0, i);
                    }
                }
            }
        }
    }

    /**
     * Чтение данных из JTable в двухмерный массив Integer[][]
     *
     * @param table
     * @return
     * @throws ParseException
     */
    public static int[][] readIntMatrixFromJTable(JTable table) throws ParseException {
        try {
            Integer[][] matrix = readMatrixFromJTable(table, Integer::parseInt);
            return (int[][]) Arrays.stream(matrix).map(JTableUtils::toPrimitive).toArray((n) -> new int[n][]);
        } catch (JTableUtilsException impossible) {
        }
        return null;
    }

    private static int[] toPrimitive(Integer[] arr) {
        if (arr == null) {
            return null;
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    /**
     * Чтение данных из JTable в двухмерный массив
     * (основная реализация, используется в остальных readArrayFromJTable и readMatrixFromJTable)
     *
     * @param table
     * @param converter
     * @return
     * @throws JTableUtilsException
     */
    private static Integer[][] readMatrixFromJTable(
            JTable table, Function<String, ? extends Integer> converter
    ) throws JTableUtilsException {
        TableModel tableModel = table.getModel();
        int rowCount = tableModel.getRowCount(), colCount = tableModel.getColumnCount();
        Integer[][] matrix = (Integer[][]) Array.newInstance(Integer.class, rowCount, colCount);
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                Integer value = null;
                Object obj = tableModel.getValueAt(r, c);
                if (obj == null || obj instanceof String && ((String) obj).trim().length() == 0) {
                    value = 0;
                } else {
                    value = converter.apply(obj.toString());
                }
                matrix[r][c] = value;
            }
        }
        return matrix;
    }

    /**
     * Создание таблицы
     */
    public static void createTable() {
        JFrame f = new JFrame();
        JPanel panel = new JPanel();
        JTable jTable;

        String[] colHeadings = {"COLUMN1", "COLUMN2"};
        int numRows = 5;
        DefaultTableModel model = new DefaultTableModel(numRows, colHeadings.length);
        model.setColumnIdentifiers(colHeadings);
        jTable = new JTable(model);

        JButton jButton1 = new JButton("Load data from file");

        /*
        Событие при нажатии на кнопку
        Загрузка данных из файла в таблицу
         */
        ActionListener listener1 = e -> {
            try {
                int[][] array = utils.FileUtils.readIntArray2FromFile("input2.txt");
                int[][] newArray = ArrayUtils.filterArray(array);
                JTableUtils.writeArrayToJTable(jTable, newArray);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
        jButton1.addActionListener(listener1);

        /*
        Событие при нажатии на кнопку
        Сохранение данных из таблицы в файл
         */
        JButton jButton2 = new JButton("Save data to file");
        ActionListener listener2 = e -> {
            try {
                int[][] data = JTableUtils.readIntMatrixFromJTable(jTable);
                int[][] newArray = ArrayUtils.filterArray(data);
                utils.FileUtils.writeArrayToFile("output2.txt", newArray);
            } catch (FileNotFoundException | ParseException ex) {
                ex.printStackTrace();
            }
        };
        jButton2.addActionListener(listener2);

        panel.add(jTable);
        panel.add(jButton1);
        panel.add(jButton2);
        f.add(panel);

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
    }
}
