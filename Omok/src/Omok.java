import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Omok extends JFrame {
    GoEgg goEgg[][];
    ImageIcon img = new ImageIcon("images//empty.png");
    ImageIcon white = new ImageIcon("images//white.png");
    ImageIcon black = new ImageIcon("images//black.png");
    ImageIcon turn = black;
    Timer timer;
    JLabel timerLabel;
    int timeLeft;
    Random random = new Random();

    public Omok(int initialTime) {
        setTitle("오목");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 1000));
        getContentPane().add(layeredPane);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(26, 26));
        panel.setBounds(0, 0, 1000, 1000);
        goEgg = new GoEgg[26][];

        myActionListener goAction = new myActionListener(initialTime);
        for (int i = 0; i < 26; i++) {
            goEgg[i] = new GoEgg[26];
            for (int j = 0; j < 26; j++) {
                goEgg[i][j] = new GoEgg(i, j, img);
                panel.add(goEgg[i][j]);
                goEgg[i][j].addActionListener(goAction);
                goEgg[i][j].setBorderPainted(false);
            }
        }
        
        layeredPane.add(panel, Integer.valueOf(1));
        timeLeft = initialTime;

        timerLabel = new JLabel(String.valueOf(initialTime), SwingConstants.RIGHT);
        timerLabel.setBounds(930, 0, 50, 50);
        timerLabel.setFont(new Font("Serif", Font.BOLD, 30));
        timerLabel.setForeground(Color.WHITE);
        layeredPane.add(timerLabel, Integer.valueOf(2));
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText(String.valueOf(timeLeft));
                if (timeLeft <= 0) {
                    timer.stop();
                    placeRandomStone();
                    timeLeft = initialTime;
                    timer.restart();
                }
            }
        });
        timer.start();
        

        pack();
        setVisible(true);
    }

    void placeRandomStone() {
        int x, y;
        do {
            x = random.nextInt(26);
            y = random.nextInt(26);
        } while (!goEgg[x][y].state.equals("N"));

        goEgg[x][y].setIcon(turn);
        goEgg[x][y].state = turn == white ? "W" : "B";
        if(goEgg[x][y].getActionListeners().length > 0) {
            goEgg[x][y].removeActionListener(goEgg[x][y].getActionListeners()[0]);
        }
        checkWin(goEgg[x][y]);

        turn = turn == white ? black : white; // Switch turn
    }

    class myActionListener implements ActionListener {
    	private int initialTime; // 멤버 변수 추가

        public myActionListener(int initialTime) { // 생성자 추가
            this.initialTime = initialTime; // 멤버 변수 초기화
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            GoEgg wi = (GoEgg) e.getSource();
            if (turn == white) {
                wi.setIcon(white);
                wi.state = "W";
                turn = black;

            } else {
                wi.setIcon(black);
                wi.state = "B";
                turn = white;
            }
            checkWin(wi);
            wi.removeActionListener(this);
            timeLeft = this.initialTime;
            timer.restart();
        }
    }

    public void checkWin(GoEgg e) {
		int checkx = e.x;
		int checky = e.y;
		int count = 0;
		while (checky >= 0 && goEgg[checkx][checky].state.equals(e.state)) {
			checky -= 1;
		}
		checky += 1;
		while (checky < 26 && goEgg[checkx][checky].state.equals(e.state)) {
			checky += 1;
			count++;
		}
		if (count == 5) {
			if (e.state.equals("B")) {
				JOptionPane.showMessageDialog(null, "흑돌 승리", "흑돌 승리", JOptionPane.QUESTION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "백돌 승리", "백돌 승리", JOptionPane.QUESTION_MESSAGE);
			}
		}
		
		checkx = e.x;
		checky = e.y;
		count = 0;

		while (checkx >= 0 && goEgg[checkx][checky].state.equals(e.state)) {
			checkx -= 1;
		}
		checkx += 1;
		while (checkx < 26 && goEgg[checkx][checky].state.equals(e.state)) {
			checkx += 1;
			count++;
		}
		if (count == 5) {
			if (e.state.equals("B")) {
				JOptionPane.showMessageDialog(null, "흑돌 승리", "흑돌 승리", JOptionPane.QUESTION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "백돌 승리", "백돌 승리", JOptionPane.QUESTION_MESSAGE);
			}
		}
		
		checkx = e.x;
		checky = e.y;
		count = 0;

		while (checkx >= 0 && checky >= 0 && goEgg[checkx][checky].state.equals(e.state)) {
			checkx -= 1;
			checky -= 1;
		}
		checkx += 1;
		checky += 1;
		while (checkx < 26 && checky < 26 && goEgg[checkx][checky].state.equals(e.state)) {
			checkx += 1;
			checky += 1;
			count++;
		}
		if (count == 5) {
			if (e.state.equals("B")) {
				JOptionPane.showMessageDialog(null, "흑돌 승리", "흑돌 승리", JOptionPane.QUESTION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "백돌 승리", "백돌 승리", JOptionPane.QUESTION_MESSAGE);
			}

		}
		
		checkx = e.x;
		checky = e.y;
		count = 0;

		while (checkx >= 0 && checky < 26 && goEgg[checkx][checky].state.equals(e.state)) {
			checkx -= 1;
			checky += 1;
		}
		checkx += 1;
		checky -= 1;
		while (checkx < 26 && checky >= 0 && goEgg[checkx][checky].state.equals(e.state)) {
			checkx += 1;
			checky -= 1;
			count++;
		}

		if (count == 5) {
			if (e.state.equals("B")) {
				JOptionPane.showMessageDialog(null, "흑돌 승리", "흑돌 승리", JOptionPane.QUESTION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "백돌 승리", "백돌 승리", JOptionPane.QUESTION_MESSAGE);
			}

		}

	}

    
}


class GoEgg extends JButton {
    int x;
    int y;
    String state;

    public GoEgg(int x, int y, ImageIcon image) {
        super(image);
        this.x = x;
        this.y = y;
        state = "N";
    }
}
