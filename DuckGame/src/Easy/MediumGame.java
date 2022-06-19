package Easy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MediumGame extends JPanel implements ActionListener {
    static JFrame mediumJFrameGamePlay;
    static JPanel mediumGamePlay;
    JLabel labelMedium;
    JLabel labelMediumPoints;
    static JPanel panelForHearts;
    JPanel[] panelMedium = new JPanel[12];
    static int Width = 1200;
    static int Height = 600;
    private int killedDucks = 0;
    private int score = 0;
    private int lifeWhite = 1, lifeBlue = 2, lifeGreen = 5;
    private int milisWhite = 10, milisBlue = 15;
    int i = 9;
    int x = 0, VelX = 9;
    static boolean gameOverBool = false;
    boolean ob = false;


    MediumGame() throws IOException {
        Thread threadForInitGame = new Thread(() -> {
            try {
                frameMedium();
                obstacle();
                hearts();
                new GameTime();
                addTo();
                waitFor();
                setMainScore();
                mediumJFrameGamePlay.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        threadForInitGame.start();
    }

    public void setMainScore() {
        panelMedium[0] = new JPanel();
        panelMedium[0].setBounds(200, 55, 125, 70);
        panelMedium[0].setBackground(new Color(0, 0, 0, 0));
        panelMedium[0].setBackground(new Color(40, 36, 36, 168));

        labelMedium = new JLabel("Killed " + killedDucks);
        labelMedium.setForeground(Color.WHITE);
        labelMedium.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

        panelMedium[0].add(labelMedium);
        mediumGamePlay.add(panelMedium[0]);

        labelMediumPoints = new JLabel("Score " + score);
        labelMediumPoints.setForeground(Color.WHITE);
        labelMediumPoints.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

        panelMedium[0].add(labelMediumPoints);
        mediumGamePlay.add(panelMedium[0]);
    }

    public void hearts() throws IOException {
        panelForHearts = new JPanel();
        panelForHearts.setLayout(new GridLayout(1, 10, 10, 0));
        panelForHearts.setBounds(0, 0, 550, 50);
        for (int i = 0; i < 10; i++) {
            BufferedImage img = ImageIO.read(new File("src/res/heart1.png"));
            JLabel pic = new JLabel(new ImageIcon(img));

            panelMedium[i] = new JPanel();
            panelMedium[i].setBackground(new Color(0, 0, 0, 0));
            panelMedium[i].add(pic);
            panelMedium[i].setVisible(true);
            panelForHearts.add(panelMedium[i]);
        }
        panelForHearts.setBackground(new Color(0, 0, 0, 0));
        panelForHearts.setVisible(true);
        mediumGamePlay.add(panelForHearts);
    }

    public void frameMedium() {
        mediumJFrameGamePlay = new JFrame("Medium Game");
        mediumJFrameGamePlay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mediumGamePlay = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgIcon = new ImageIcon("src/res/backgroundMedium.png");
                g.drawImage(bgIcon.getImage(), 0, 0, 1200, 600, null);
            }
        };

        mediumGamePlay.setLayout(null);
        mediumJFrameGamePlay.add(mediumGamePlay);
        mediumJFrameGamePlay.setSize(Width, Height);
        mediumJFrameGamePlay.setResizable(false);
        mediumJFrameGamePlay.setLocationRelativeTo(null);
    }

    public JPanel flySky() {
        final int[] x = {0};
        int yy = (int) (Math.random() * 100) + 100;
        ImageIcon cloud = new ImageIcon("src/res/cloud.png");
        JPanel jPanelWhite = new JPanel();
        JRadioButton buttonCloud = new JRadioButton();
        buttonSet(buttonCloud, cloud);


        jPanelWhite.setVisible(true);
        Thread timer = new Thread(() -> {
            while (!gameOverBool) {
                jPanelWhite.setBounds(x[0], yy, 200, 185);
                jPanelWhite.add(buttonCloud);
                jPanelWhite.repaint();
                jPanelWhite.revalidate();
                jPanelWhite.setBackground(new Color(0, 0, 0, 0));
                jPanelWhite.setVisible(true);

                buttonCloud.setVisible(true);
                x[0]++;
                if (x[0] == 1200) {
                    x[0] = 0;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }

            }
        });
        timer.start();
        return jPanelWhite;
    }

    public void obstacle() {
        ImageIcon bush = new ImageIcon("src/res/Grass.png");
        JPanel obstacleBush = new JPanel();

        JRadioButton b2 = new JRadioButton();
        buttonSet(b2, bush);
        obstacleBush.add(b2);
        b2.setVisible(true);
        obstacleBush.setBounds(200, 430, 235, 231);
        obstacleBush.setBackground(new Color(0, 0, 0, 0));
        obstacleBush.setVisible(true);
        mediumGamePlay.add(obstacleBush);
    }


    public JPanel generateDuckWhite() {
        final int[] xx = {0};
        int range = (500 - 100) + 1;
        int yy = (int) (Math.random() * range) + 300;
        ImageIcon duck1 = new ImageIcon("src/res/duckMW.png");
        JPanel jPanelWhite = new JPanel();
        JRadioButton button1 = new JRadioButton();
        buttonSet(button1, duck1);

        jPanelWhite.setVisible(true);
        Thread timer = new Thread(() -> {
            while (!gameOverBool) {
                jPanelWhite.setBounds(xx[0], yy, 200, 185);
                jPanelWhite.add(button1);
                jPanelWhite.repaint();
                jPanelWhite.revalidate();
                jPanelWhite.setBackground(new Color(0, 0, 0, 0));
                jPanelWhite.setVisible(true);

                button1.setVisible(true);
                xx[0]++;
                if (xx[0] == 1200) {
                    mediumGamePlay.remove(jPanelWhite);
                    mediumGamePlay.remove(button1);
                    jPanelWhite.remove(button1);
                    panelMedium[i].setVisible(false);
                    System.out.println(i);
                    i--;
                    if (i < 0) {
                        System.out.println("GAME OVER");
                        gameOverBool = true;
                        try {
                            new LeaderBoard();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                try {
                    Thread.sleep(milisWhite);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });
        button1.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                System.out.println("White -> " + count + " Click");
                if (count == lifeWhite) {
                    score += 10;
                    killedDucks++;
                    xx[0] = 1201;
                    jPanelWhite.remove(button1);
                    count = 0;

                    labelMediumPoints.setText("Score " + score);
                    labelMedium.setText("killed " + killedDucks);
                    labelMedium.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

                    mediumGamePlay.remove(jPanelWhite);
                    mediumGamePlay.remove(button1);
                    mediumGamePlay.revalidate();
                    mediumGamePlay.repaint();
                }
            }
        });
        timer.start();
        return jPanelWhite;
    }

    public JPanel generateDuckBlue() {
        final int[] xx = {0};
        int yy = (int) (Math.random() * 190) + 260;
        ImageIcon duckBlue = new ImageIcon("src/res/duckMB.png");
        JPanel jPanelBlue = new JPanel();
        JRadioButton button2 = new JRadioButton();
        buttonSet(button2, duckBlue);
        jPanelBlue.setVisible(true);
        Thread timer = new Thread(() -> {
            while (!gameOverBool) {
                jPanelBlue.setBounds(xx[0], yy, 200, 185);
                jPanelBlue.add(button2);
                jPanelBlue.repaint();
                jPanelBlue.revalidate();
                jPanelBlue.setBackground(new Color(0, 0, 0, 0));
                jPanelBlue.setVisible(true);
                jPanelBlue.setBounds(xx[0], yy, 200, 185);

                button2.setVisible(true);
                xx[0]++;
                if (xx[0] == 1200) {
                    mediumGamePlay.remove(jPanelBlue);
                    mediumGamePlay.remove(button2);
                    jPanelBlue.remove(button2);
                    panelMedium[i].setVisible(false);
                    System.out.println(i);
                    i--;
                    if (i < 0) {
                        gameOverBool = true;
                        System.out.println("GAME OVER");
                        try {
                            new LeaderBoard();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                try {
                    Thread.sleep(milisBlue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                if (count == lifeBlue) {
                    score += 20;
                    killedDucks++;
                    count = 0;
                    xx[0] = 1201;
                    jPanelBlue.remove(button2);

                    labelMediumPoints.setText("Score " + score);
                    labelMedium.setText("killed " + killedDucks);
                    labelMedium.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

                    mediumGamePlay.remove(jPanelBlue);
                    mediumGamePlay.remove(button2);
                    mediumGamePlay.revalidate();
                    mediumGamePlay.repaint();
                }
            }
        });
        timer.start();

        return jPanelBlue;
    }

    public JPanel generateDuckGreen() {
        final int[] xx = {0};
        int yy = (int) (Math.random() * 300) + 150;
        ImageIcon duckGreen = new ImageIcon("src/res/duckM.png");
        JPanel jPanelGreen = new JPanel();
        JRadioButton button3 = new JRadioButton();
        buttonSet(button3, duckGreen);

        jPanelGreen.setVisible(true);
        Thread timer = new Thread(() -> {
            while (!gameOverBool) {
                jPanelGreen.setBounds(xx[0], yy, 200, 185);
                jPanelGreen.add(button3);
                jPanelGreen.repaint();
                jPanelGreen.revalidate();
                jPanelGreen.setBackground(new Color(0, 0, 0, 0));
                jPanelGreen.setVisible(true);
                jPanelGreen.setBounds(xx[0], yy, 200, 185);

                button3.setVisible(true);
                xx[0]++;
                if (xx[0] == 1200) {
                    mediumGamePlay.remove(jPanelGreen);
                    mediumGamePlay.remove(button3);
                    jPanelGreen.remove(button3);
                    panelMedium[i].setVisible(false);
                    System.out.println(i);
                    i--;
                    if (i < 0) {
                        gameOverBool = true;
                        try {
                            new LeaderBoard();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("GAME OVER");
                    }
                    break;
                }
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });
        button3.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                if (count == lifeGreen) {
                    score += 30;
                    killedDucks++;
                    count = 0;
                    xx[0] = 1201;
                    jPanelGreen.remove(button3);

                    labelMediumPoints.setText("Score " + score);
                    labelMedium.setText("killed " + killedDucks);
                    labelMedium.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

                    mediumGamePlay.remove(jPanelGreen);
                    mediumGamePlay.remove(button3);
                    mediumGamePlay.revalidate();
                    mediumGamePlay.repaint();
                }
            }
        });
        timer.start();
        return jPanelGreen;
    }

    public void waitFor() {
        Thread duckThreadGreen = new Thread(() -> {
            while (!Thread.interrupted()) {
                mediumGamePlay.add(generateDuckGreen());
                mediumGamePlay.repaint();
                mediumGamePlay.revalidate();
                try {
                    Thread.sleep(5000);
                    milisWhite = 4;
                    milisBlue = 7;

                    lifeWhite++;
                    lifeBlue++;
                    lifeGreen++;
                    ob = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        duckThreadGreen.start();
    }

    public void addTo() {

        Thread flySkyThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                mediumGamePlay.add(flySky());
                mediumGamePlay.repaint();
                mediumGamePlay.revalidate();
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        flySkyThread.start();
        Thread duckThreadRed = new Thread(() -> {
            while (!Thread.interrupted()) {
                mediumGamePlay.add(generateDuckWhite());
                mediumGamePlay.repaint();
                mediumGamePlay.revalidate();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        duckThreadRed.start();

        Thread duckThreadBlue = new Thread(() -> {
            while (!Thread.interrupted()) {
                mediumGamePlay.add(generateDuckBlue());
                mediumGamePlay.repaint();
                mediumGamePlay.revalidate();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        duckThreadBlue.start();

        Thread duckThreadGreen = new Thread(() -> {
            while (!Thread.interrupted()) {
                mediumGamePlay.add(generateDuckGreen());
                mediumGamePlay.repaint();
                mediumGamePlay.revalidate();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        duckThreadGreen.start();
    }

    public void buttonSet(JRadioButton button, ImageIcon icon) {
        button.setIcon(icon);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBorder(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x = x + VelX;
        repaint();
    }
}
