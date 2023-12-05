import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    Util util;
    StartScreen startScreen;
    private int initialTime;
    Omok omok;
    public Main(int initialTime, StartScreen startScreen) {
        this.setTitle("오목 게임 중...");
        this.initialTime = initialTime;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        omok = new Omok(initialTime);
        util = new Util(omok, this, startScreen);        
        c.add(omok, BorderLayout.CENTER);
        c.add(util, BorderLayout.SOUTH);
        setSize(1000, 1200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void resetGame() {
        omok = new Omok(initialTime);
        util = new Util(omok, this, startScreen);
        Container c = getContentPane();
        c.removeAll();
        c.add(omok, BorderLayout.CENTER);
        c.add(util, BorderLayout.SOUTH);
        c.revalidate();
        c.repaint();
    }
}