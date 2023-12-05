import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class StartScreen extends JFrame {
	Clip clip;
	
    public StartScreen() {
        setTitle("오목 시작 화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        Container c = getContentPane();
        c.setLayout(null);

        JLabel background = new JLabel(new ImageIcon("images//bg.png"));
        background.setSize(1000, 1000);
        c.add(background);

        JButton winButton = new JButton();
        winButton.setBounds(250, 700, 200, 70);
        winButton.setBorder(null);
        winButton.setMargin(new Insets(0, 0, 0, 0));
        winButton.setOpaque(false);
        winButton.setContentAreaFilled(false);
        winButton.setBorderPainted(false);
        winButton.setFocusPainted(false);
        winButton.setIcon(new ImageIcon("images//button1.png"));
        winButton.setRolloverIcon(new ImageIcon("images//button1_hover.png"));
        winButton.setPressedIcon(new ImageIcon("images//button1_pressed.png"));
        winButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                clip.stop();
                new Main(30, StartScreen.this);
            }
        });

        JButton loseButton = new JButton();
        loseButton.setBounds(550, 700, 200, 70);
        loseButton.setBorder(null);
        loseButton.setMargin(new Insets(0, 0, 0, 0));
        loseButton.setOpaque(false);
        loseButton.setContentAreaFilled(false);
        loseButton.setBorderPainted(false);
        loseButton.setFocusPainted(false);
        loseButton.setIcon(new ImageIcon("images//button2.png"));
        loseButton.setRolloverIcon(new ImageIcon("images//button2_hover.png"));
        loseButton.setPressedIcon(new ImageIcon("images//button2_pressed.png"));
        loseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                clip.stop();
                new Main(60, StartScreen.this);
            }
        });

        background.add(winButton);
        background.add(loseButton);

        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
        
        playSound();
    }
    
    public void playSound() {
        try {
            File soundFile = new File("sounds//StartScreen.wav"); 
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioIn);

            FloatControl gainControl = 
                (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            this.clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new StartScreen();
    }
}

