import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Omok extends JFrame {
	GoEgg goEgg[][];
	ImageIcon img = new ImageIcon("images//empty.png");
	ImageIcon white = new ImageIcon("images//white.png");
	ImageIcon black = new ImageIcon("images//black.png");
	ImageIcon turn = black;

	public Omok() {
		setTitle("오목");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new GridLayout(26, 26));

		goEgg = new GoEgg[26][];

		myActionListener goAction = new myActionListener();
		for (int i = 0; i < 26; i++) {
			goEgg[i] = new GoEgg[26];
			for (int j = 0; j < 26; j++) {
				goEgg[i][j] = new GoEgg(i, j, img);
				c.add(goEgg[i][j]);
				goEgg[i][j].addActionListener(goAction);
				goEgg[i][j].setBorderPainted(false);
			}
		}

		setSize(1000, 1000);
		setVisible(true);
	}

	class myActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
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
		}

	}

	public void checkWin(GoEgg e) {
		//�¿� Ž��
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
				JOptionPane.showMessageDialog(null, "흑돌 승리!", "흑돌 승리!", JOptionPane.QUESTION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "백돌 승리!", "백돌 승리!", JOptionPane.QUESTION_MESSAGE);
			}

		}
		/////////////////////////////////
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
				JOptionPane.showMessageDialog(null, "흑돌 승리!", "흑돌 승리!", JOptionPane.QUESTION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "백돌 승리!", "백돌 승리!", JOptionPane.QUESTION_MESSAGE);
			}

		}
		////////////////////
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
				JOptionPane.showMessageDialog(null, "흑돌 승리!", "흑돌 승리!", JOptionPane.QUESTION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "백돌 승리!", "백돌 승리!", JOptionPane.QUESTION_MESSAGE);
			}

		}
		//		//////////////////
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
				JOptionPane.showMessageDialog(null, "흑돌 승리!", "흑돌 승리!", JOptionPane.QUESTION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "백돌 승리!", "백돌 승리!", JOptionPane.QUESTION_MESSAGE);
			}

		}

	}

	public static void main(String[] args) {
		new Omok();
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