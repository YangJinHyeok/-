import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    Util util;
    private int initialTime;
    Omok omok;
    public Main(int initialTime) {
        this.setTitle("오목 게임 중...");
        this.initialTime = initialTime;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        omok = new Omok(initialTime);
        util = new Util(omok, this);        
        c.add(omok, BorderLayout.CENTER);
        c.add(util, BorderLayout.SOUTH);
        setSize(1000, 1200);
        setVisible(true);
    }
    public void resetGame() {
        omok = new Omok(initialTime);
        util = new Util(omok, this);
        Container c = getContentPane();
        c.removeAll();
        c.add(omok, BorderLayout.CENTER);
        c.add(util, BorderLayout.SOUTH);
        c.revalidate();
        c.repaint();
    }
}