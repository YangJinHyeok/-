import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Util extends JPanel {
    ImageIcon black2 = new ImageIcon("images//black_util.png");
    ImageIcon white2 = new ImageIcon("images//white_util.png");
    Omok omok;
    JLabel timerLabel, blackTimerLabel, whiteTimerLabel;;
    Timer utilTimer;
    public Util(Omok omok) {
        this.setLayout(new BorderLayout(20, 5));
        this.omok = omok;
        this.timerLabel = new JLabel(String.valueOf(omok.getTimeLeft()));
        this.blackTimerLabel = new JLabel(String.valueOf(omok.getTimeLeft()));
        this.whiteTimerLabel = new JLabel(String.valueOf(omok.getTimeLeft()));
        ImageIcon black = omok.black;
        ImageIcon white = omok.white;

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // 타이머 부분, 턴에 따라서 타이머 구현 필요
        northPanel.add(new JLabel(black2));
        northPanel.add(blackTimerLabel);
        northPanel.add(new JLabel("vs"));
        northPanel.add(whiteTimerLabel);
        northPanel.add(new JLabel(white2));
        
        utilTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (omok.isWin()) {
                    utilTimer.stop();
                } else {
                    if (omok.getTurn() == black) {
                        blackTimerLabel.setText(String.valueOf(omok.getTimeLeft()));
                        whiteTimerLabel.setText("");
                    } else {
                        whiteTimerLabel.setText(String.valueOf(omok.getTimeLeft()));
                        blackTimerLabel.setText("");
                    }
                }                
            }
        });
        utilTimer.start();

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
