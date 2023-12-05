import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Util extends JPanel {
	StartScreen startScreen;
	
	ImageIcon img = new ImageIcon("images//empty.png");
    ImageIcon black2 = new ImageIcon("images//black_util.png");
    ImageIcon white2 = new ImageIcon("images//white_util.png");
    Omok omok;
    JLabel timerLabel, blackTimerLabel, whiteTimerLabel;;
    Timer utilTimer;
    Main main;
    public Util(Omok omok, Main main, StartScreen startScreen) {
    	this.startScreen = startScreen;
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
                    JOptionPane.showMessageDialog(null, "상대방이 기권하였습니다. 흑돌 플레이어가 승리하였습니다.");
                } else {
                    JOptionPane.showMessageDialog(null, "상대방이 기권하였습니다. 백돌 플레이어가 승리하였습니다.");
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
        
        JButton startScreenButton = new JButton();
        startScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.dispose();
                new StartScreen();
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
        
        startScreenButton.setPreferredSize(new Dimension(100, 50));
        startScreenButton.setBorder(null);
        startScreenButton.setMargin(new Insets(0, 0, 0, 0));
        startScreenButton.setOpaque(false);
        startScreenButton.setContentAreaFilled(false);
        startScreenButton.setBorderPainted(false);
        startScreenButton.setFocusPainted(false);
        startScreenButton.setIcon(new ImageIcon("images//undo.png"));
        startScreenButton.setRolloverIcon(new ImageIcon("images//undo_hover.png"));
        startScreenButton.setPressedIcon(new ImageIcon("images//undo_pressed.png"));
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalStrut(110));
        buttonPanel.add(newGame);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(giveUp);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(undo);

        JPanel gridPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gridPanel.add(buttonPanel, gbc);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(gridPanel, BorderLayout.CENTER);

        JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        eastPanel.add(startScreenButton);
        southPanel.add(eastPanel, BorderLayout.EAST);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
        
    }
}
