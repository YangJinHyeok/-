import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartScreen extends JFrame {
    public StartScreen() {
        setTitle("오목 시작 화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(null);

        JLabel background = new JLabel(new ImageIcon("images//bg.png"));
        background.setSize(1000, 1000);
        c.add(background);

        JButton winButton = new JButton("속기");
        winButton.setBounds(100, 700, 200, 100);
        winButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Omok(30);
            }
        });

        JButton loseButton = new JButton("보통");
        loseButton.setBounds(700, 700, 200, 100);
        loseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Omok(60);
            }
        });

        background.add(winButton);
        background.add(loseButton);

        setSize(1000, 1000);
        setVisible(true);
    }

    public static void main(String[] args) {
        new StartScreen();
    }
}

