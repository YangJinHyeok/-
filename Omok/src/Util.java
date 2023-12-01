import java.awt.*;
import javax.swing.*;

public class Util extends JPanel {
    ImageIcon black = new ImageIcon("images//black_util.png");
    ImageIcon white = new ImageIcon("images//white_util.png");

    public Util() {
        this.setLayout(new BorderLayout(20, 5));

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // 타이머 부분, 턴에 따라서 타이머 구현 필요
        northPanel.add(new JLabel(black));
        northPanel.add(new JLabel("60"));
        northPanel.add(new JLabel("vs"));
        northPanel.add(new JLabel("60"));
        northPanel.add(new JLabel(white));

        JPanel southPanel = new JPanel();
        JButton newGame = new JButton("새 게임");
        JButton giveUp = new JButton("기권");
        JButton undo = new JButton("무르기");

        newGame.setPreferredSize(new Dimension(100, 50));
        giveUp.setPreferredSize(new Dimension(100, 50));
        undo.setPreferredSize(new Dimension(100, 50));

        southPanel.add(newGame);
        southPanel.add(giveUp);
        southPanel.add(undo);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
