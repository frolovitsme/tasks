package util;

import javax.swing.*;

public class JFrameUtils {

    /**
     * Настройка компонента jFrame
     *
     * @param jFrame
     */
    public static void setParams(JFrame jFrame) {
//        jFrame.setSize(400, 400);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
    }
}
