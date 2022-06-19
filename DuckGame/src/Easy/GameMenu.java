package Easy;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GameMenu extends JFrame {
    JFrame frameMenu;
    JLabel[] bgLabel = new JLabel[5];
    JPanel panelForGame = new JPanel();
    JButton start, highScore, exit;
    JTextArea mainText;

    GameMenu() throws IOException {
        menu();
        gunGif();
        text();
        gunJpg();
        buttons();
        background();
        frameMenu.setVisible(true);
    }

    public void menu() {
        Image icon = Toolkit.getDefaultToolkit().getImage("src/res/pub.png");

        frameMenu = new JFrame("Run, run duck!");
        frameMenu.setIconImage(icon);
        frameMenu.setSize(960, 600);
        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMenu.setResizable(false);
        frameMenu.setLayout(null);
        frameMenu.setLocationRelativeTo(null);
    }

    public void background() {
        ImageIcon bgIcon = new ImageIcon("src/res/pub.png");

        bgLabel[0] = new JLabel();
        bgLabel[0].setBounds(0, 0, 960, 600);
        bgLabel[0].setBackground(Color.black);
        bgLabel[0].setLayout(null);
        bgLabel[0].setIcon(bgIcon);
        bgLabel[0].setVisible(true);
        frameMenu.add(bgLabel[0]);
    }

    public void gunJpg() {
        ImageIcon chooseGun1 = new ImageIcon("src/res/gunForChoose.png");
        bgLabel[1] = new JLabel();
        bgLabel[1].setBounds(588, 150, 110, 110);
        bgLabel[1].setLayout(null);
        bgLabel[1].setIcon(chooseGun1);
        bgLabel[1].setVisible(false);
        frameMenu.add(bgLabel[1]);

        ImageIcon chooseGun2 = new ImageIcon("src/res/gunForChoose.png");
        bgLabel[2] = new JLabel();
        bgLabel[2].setBounds(588, 220, 110, 110);
        bgLabel[2].setLayout(null);
        bgLabel[2].setIcon(chooseGun2);
        bgLabel[2].setVisible(false);
        frameMenu.add(bgLabel[2]);

        ImageIcon chooseGun3 = new ImageIcon("src/res/gunForChoose.png");
        bgLabel[3] = new JLabel();
        bgLabel[3].setBounds(588, 290, 110, 110);
        bgLabel[3].setLayout(null);
        bgLabel[3].setIcon(chooseGun3);
        bgLabel[3].setVisible(false);
        frameMenu.add(bgLabel[3]);
    }

    public void gunGif() {
        ImageIcon bgIcon = new ImageIcon("src/res/target.gif");
        bgLabel[1] = new JLabel();
        bgLabel[1].setBounds(0, 50, 180, 180);
        bgLabel[1].setLayout(null);
        bgLabel[1].setIcon(bgIcon);
        bgLabel[1].setVisible(true);
        frameMenu.add(bgLabel[1]);
    }

    public void text() {
        mainText = new JTextArea("Run      run duck!");
        mainText.setBounds(61, 117, 300, 40);
        mainText.setBackground(new Color(0, 0, 0, 0));
        mainText.setForeground(Color.white);
        mainText.setEditable(false);
        mainText.setLineWrap(true);
        mainText.setWrapStyleWord(true);
        mainText.setFont(new Font(Font.DIALOG, Font.BOLD, 28));
        frameMenu.add(mainText);
    }

    public void buttons() {
        panelForGame = new JPanel();
        panelForGame.setBounds(700, 150, 150, 200);
        panelForGame.setBackground(new Color(0, 0, 0, 0));
        panelForGame.setLayout(new GridLayout(3, 1, 5, 5));
        frameMenu.add(panelForGame);

        start = new JButton("START");
        start.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
        start.setForeground(Color.black);
        start.addActionListener(event -> {
            frameMenu.setVisible(false);
            new ChooseLevel();
        });
        start.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                bgLabel[1].setVisible(true);
            }

            public void mouseExited(MouseEvent e) {
                bgLabel[1].setVisible(false);
            }
        });

        panelForGame.add(start);

        highScore = new JButton("High Score");
        highScore.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
        highScore.setForeground(Color.black);
        highScore.addActionListener(e -> {
            try {
                new Leader();
                LeaderBoard leaderBoard = null;
                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Mikołaj\\Desktop\\copy\\Game.txt");
                ObjectInputStream in = new ObjectInputStream(fileInputStream);
                leaderBoard = (LeaderBoard) in.readObject();
                in.close();
                fileInputStream.close();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        highScore.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                bgLabel[2].setVisible(true);
            }

            public void mouseExited(MouseEvent e) {
                bgLabel[2].setVisible(false);
            }
        });
        panelForGame.add(highScore);

        exit = new JButton("EXIT");
        exit.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
        exit.setForeground(Color.BLACK);
        exit.addActionListener(event -> System.exit(0));
        exit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                bgLabel[3].setVisible(true);
            }

            public void mouseExited(MouseEvent e) {
                bgLabel[3].setVisible(false);
            }
        });
        panelForGame.add(exit);
    }

}
