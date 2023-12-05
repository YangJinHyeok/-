import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Util extends JPanel {
	ImageIcon img = new ImageIcon("images//empty.png");
    ImageIcon black2 = new ImageIcon("images//black_util.png");
    ImageIcon white2 = new ImageIcon("images//white_util.png");
    Omok omok;
    JLabel timerLabel, blackTimerLabel, whiteTimerLabel;;
    Timer utilTimer;
    Main main;
    public Util(Omok omok, Main main) {
    	this.main = main;
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
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        blackTimerLabel.setFont(labelFont);
        whiteTimerLabel.setFont(labelFont);
        
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
        JButton newGame = new JButton();
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.resetGame();
            }
        });

        JButton giveUp = new JButton();
        giveUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (omok.getTurn() == omok.white) {
                    JOptionPane.showMessageDialog(null, "흑돌 플레이어가 승리하였습니다.");
                } else {
                    JOptionPane.showMessageDialog(null, "백돌 플레이어가 승리하였습니다.");
                }
                main.resetGame();
            }
        });
        JButton undo = new JButton();
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!omok.stoneStack.isEmpty() && !omok.turnStack.isEmpty()) {
                    GoEgg lastStone = omok.stoneStack.pop();
                    ImageIcon lastTurn = omok.turnStack.pop();
                    lastStone.setIcon(img);
                    lastStone.state = "N";
                    omok.turn = lastTurn;
                    lastStone.addActionListener(omok.new myActionListener(omok.getTimeLeft()));
                }
            }
        });

        newGame.setPreferredSize(new Dimension(100, 50));
        newGame.setBorder(null);
        newGame.setMargin(new Insets(0, 0, 0, 0));
        newGame.setOpaque(false);
        newGame.setContentAreaFilled(false);
        newGame.setBorderPainted(false);
        newGame.setFocusPainted(false);
        newGame.setIcon(new ImageIcon("images//newgame.png"));
        newGame.setRolloverIcon(new ImageIcon("images//newgame_hover.png"));
        newGame.setPressedIcon(new ImageIcon("images//newgame_pressed.png"));

        giveUp.setPreferredSize(new Dimension(100, 50));
        giveUp.setBorder(null);
        giveUp.setMargin(new Insets(0, 0, 0, 0));
        giveUp.setOpaque(false);
        giveUp.setContentAreaFilled(false);
        giveUp.setBorderPainted(false);
        giveUp.setFocusPainted(false);
        giveUp.setIcon(new ImageIcon("images//giveUp.png"));
        giveUp.setRolloverIcon(new ImageIcon("images//giveup_hover.png"));
        giveUp.setPressedIcon(new ImageIcon("images//giveup_pressed.png"));

        undo.setPreferredSize(new Dimension(100, 50));
        undo.setBorder(null);
        undo.setMargin(new Insets(0, 0, 0, 0));
        undo.setOpaque(false);
        undo.setContentAreaFilled(false);
        undo.setBorderPainted(false);
        undo.setFocusPainted(false);
        undo.setIcon(new ImageIcon("images//undo.png"));
        undo.setRolloverIcon(new ImageIcon("images//undo_hover.png"));
        undo.setPressedIcon(new ImageIcon("images//undo_pressed.png"));

        southPanel.add(newGame);
        southPanel.add(giveUp);
        southPanel.add(undo);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}
