package Easy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HardGame extends JFrame implements ActionListener {
    static JFrame hardJFrameGamePlay;
    static JPanel hardGamePlay;
    JLabel labelHard;
    JLabel labelHardPoints;
    static JPanel panelForHearts;
    JPanel[] panelHard = new JPanel[12];
    static int Width = 1208;
    static int Height = 501;
    private int killedDucks = 0;
    private int score = 0;
    private int lifeWhite = 1, lifeBlue = 4, lifeGreen = 5;
    private int milisWhite = 30, milisBlue = 38, milisGreen = 42;
    int i = 9;
    int x = 0, VelX = 9;
    boolean ob = false;
    static boolean gameOverBool = false;


    HardGame() throws IOException {
        Thread threadForInitGame = new Thread(() -> {
            try {
                frameMedium();
                obstacle();
                hearts();
                new GameTime();
                addTo();
                waitFor();
                setMainScore();
                hardJFrameGamePlay.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        threadForInitGame.start();
    }

    public void setMainScore() {
        panelHard[0] = new JPanel();
        panelHard[0].setBounds(200, 55, 125, 70);
        panelHard[0].setBackground(new Color(0, 0, 0, 0));
        panelHard[0].setBackground(new Color(40, 36, 36, 168));

        labelHard = new JLabel("Killed " + killedDucks);
        labelHard.setForeground(Color.WHITE);
        labelHard.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        panelHard[0].add(labelHard);

        hardGamePlay.add(panelHard[0]);

        labelHardPoints = new JLabel("Score " + score);
        labelHardPoints.setForeground(Color.WHITE);
        labelHardPoints.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        panelHard[0].add(labelHardPoints);

        hardGamePlay.add(panelHard[0]);
    }

    public void hearts() throws IOException {
        panelForHearts = new JPanel();
        panelForHearts.setLayout(new GridLayout(1, 10, 10, 0));
        panelForHearts.setBounds(0, 0, 550, 50);

        for (int i = 0; i < 10; i++) {
            BufferedImage img = ImageIO.read(new File("src/res/heart1.png"));
            JLabel pic = new JLabel(new ImageIcon(img));

            panelHard[i] = new JPanel();
            panelHard[i].setBackground(new Color(0, 0, 0, 0));
            panelHard[i].add(pic);
            panelHard[i].setVisible(true);
            panelForHearts.add(panelHard[i]);
        }
        panelForHearts.setBackground(new Color(0, 0, 0, 0));
        panelForHearts.setVisible(true);
        hardGamePlay.add(panelForHearts);
    }

    public void frameMedium() {
        hardJFrameGamePlay = new JFrame("Medium Game");
        hardJFrameGamePlay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hardGamePlay = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgIcon = new ImageIcon("src/res/hall.png");
                g.drawImage(bgIcon.getImage(), 0, 0, 1208, 501, null);
            }
        };

        hardGamePlay.setLayout(null);

        hardJFrameGamePlay.add(hardGamePlay);
        hardJFrameGamePlay.setSize(Width, Height);
        hardJFrameGamePlay.setResizable(false);
        hardJFrameGamePlay.setLocationRelativeTo(null);
    }

    public JPanel flySky() {
        final int[] x = {0};
        int yy = (int) (Math.random() * 170) + 170;

        ImageIcon duck1 = new ImageIcon("src/res/bat.png");
        JPanel jPanelWhite = new JPanel();
        JRadioButton button1 = new JRadioButton();
        buttonSet(button1, duck1);

        jPanelWhite.setVisible(true);
        Thread timer = new Thread(() -> {
            while (!gameOverBool) {
                jPanelWhite.setBounds(x[0], yy, 200, 185);
                jPanelWhite.add(button1);
                jPanelWhite.repaint();
                jPanelWhite.revalidate();
                jPanelWhite.setBackground(new Color(0, 0, 0, 0));
                jPanelWhite.setVisible(true);

                button1.setVisible(true);
                x[0]++;
                if (x[0] == 1208) {
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
        ImageIcon bush = new ImageIcon("src/res/pumpkin.png");

        JPanel obstaclePumpkin = new JPanel();
        JRadioButton buttonPumpkin = new JRadioButton();
        buttonSet(buttonPumpkin, bush);
        buttonPumpkin.setVisible(true);

        obstaclePumpkin.add(buttonPumpkin);
        obstaclePumpkin.setBounds(200, 320, 130, 146);
        obstaclePumpkin.setBackground(new Color(0, 0, 0, 0));
        obstaclePumpkin.setVisible(true);

        hardGamePlay.add(obstaclePumpkin);
    }

    public JPanel generateSkeletonWhite() {
        final int[] xx = {0};
        int yyWhite = 320;
        ImageIcon duck1 = new ImageIcon("src/res/skeletonWH.png");
        JPanel jPanelWhite = new JPanel();
        JRadioButton button1 = new JRadioButton();
        buttonSet(button1, duck1);

        jPanelWhite.setVisible(true);
        Thread timer = new Thread(() -> {
            while (!gameOverBool) {
                jPanelWhite.setBounds(xx[0], yyWhite, 194, 326);
                jPanelWhite.add(button1);
                jPanelWhite.repaint();
                jPanelWhite.revalidate();
                jPanelWhite.setBackground(new Color(0, 0, 0, 0));
                jPanelWhite.setVisible(true);

                button1.setVisible(true);
                xx[0]++;
                if (xx[0] == 1208) {
                    hardGamePlay.remove(jPanelWhite);
                    hardGamePlay.remove(button1);
                    jPanelWhite.remove(button1);
                    panelHard[i].setVisible(false);
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
                    xx[0] = 1209;
                    count = 0;
                    jPanelWhite.remove(button1);
                    labelHardPoints.setText("Score " + score);
                    labelHard.setText("killed " + killedDucks);
                    labelHard.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

                    hardGamePlay.remove(jPanelWhite);
                    hardGamePlay.remove(button1);
                    hardGamePlay.revalidate();
                    hardGamePlay.repaint();
                }
            }
        });
        timer.start();
        return jPanelWhite;
    }

    public JPanel generateSkeletonBlue() {
        final int[] xx = {0};
        int yyBlue = 320;
        ImageIcon duckBlue = new ImageIcon("src/res/skeletonBH.png");
        JPanel jPanelBlue = new JPanel();
        JRadioButton button2 = new JRadioButton();
        buttonSet(button2, duckBlue);
        jPanelBlue.setVisible(true);
        Thread timer = new Thread(() -> {
            while (!gameOverBool) {
                jPanelBlue.setBounds(xx[0], yyBlue, 200, 185);
                jPanelBlue.add(button2);
                jPanelBlue.repaint();
                jPanelBlue.revalidate();
                jPanelBlue.setBackground(new Color(0, 0, 0, 0));
                jPanelBlue.setVisible(true);

                button2.setVisible(true);
                xx[0]++;
                if (xx[0] == 1208) {
                    hardGamePlay.remove(jPanelBlue);
                    hardGamePlay.remove(button2);
                    jPanelBlue.remove(button2);
                    panelHard[i].setVisible(false);
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
                System.out.println("Blue -" + count + " Click");
                if (count == lifeBlue) {
                    score += 20;
                    killedDucks++;
                    xx[0] = 1209;
                    count = 0;
                    jPanelBlue.remove(button2);

                    labelHardPoints.setText("Score " + score);
                    labelHard.setText("killed " + killedDucks);
                    labelHard.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

                    hardGamePlay.remove(jPanelBlue);
                    hardGamePlay.remove(button2);
                    hardGamePlay.revalidate();
                    hardGamePlay.repaint();
                }
            }
        });
        timer.start();
        return jPanelBlue;
    }

    public JPanel generateSkeletonGreen() {
        final int[] xx = {0};
        int yyGreen = 320;
        ImageIcon duckGreen = new ImageIcon("src/res/skeletonGH.png");
        JPanel jPanelGreen = new JPanel();
        JRadioButton button3 = new JRadioButton();
        buttonSet(button3, duckGreen);

        jPanelGreen.setVisible(true);
        Thread timer = new Thread(() -> {
            while (!gameOverBool) {
                jPanelGreen.setBounds(xx[0], yyGreen, 200, 185);
                jPanelGreen.add(button3);
                jPanelGreen.repaint();
                jPanelGreen.revalidate();
                jPanelGreen.setBackground(new Color(0, 0, 0, 0));
                jPanelGreen.setVisible(true);

                button3.setVisible(true);
                xx[0]++;
                if (xx[0] == 1208) {
                    hardGamePlay.remove(jPanelGreen);
                    hardGamePlay.remove(button3);
                    jPanelGreen.remove(button3);
                    panelHard[i].setVisible(false);
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
                    Thread.sleep(milisGreen);
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
                    xx[0] = 1209;
                    jPanelGreen.remove(button3);
                    count = 0;

                    labelHardPoints.setText("Score " + score);
                    labelHard.setText("killed " + killedDucks);
                    labelHard.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

                    hardGamePlay.remove(jPanelGreen);
                    hardGamePlay.remove(button3);
                    hardGamePlay.revalidate();
                    hardGamePlay.repaint();
                }
            }
        });
        timer.start();
        return jPanelGreen;
    }

    public void waitFor() {
        Thread duckThreadGreen = new Thread(() -> {
            while (!Thread.interrupted()) {
                hardGamePlay.add(generateSkeletonGreen());
                hardGamePlay.repaint();
                hardGamePlay.revalidate();
                try {
                    Thread.sleep(5000);
                    milisWhite = 20;
                    milisBlue = 30;
                    milisGreen = 40;

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
                hardGamePlay.add(flySky());
                hardGamePlay.repaint();
                hardGamePlay.revalidate();
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
                hardGamePlay.add(generateSkeletonWhite());
                hardGamePlay.repaint();
                hardGamePlay.revalidate();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        duckThreadRed.start();

        Thread duckThreadBlue = new Thread(() -> {
            while (!Thread.interrupted()) {
                hardGamePlay.add(generateSkeletonBlue());
                hardGamePlay.repaint();
                hardGamePlay.revalidate();
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        duckThreadBlue.start();

        Thread duckThreadGreen = new Thread(() -> {
            while (!Thread.interrupted()) {
                hardGamePlay.add(generateSkeletonGreen());
                hardGamePlay.repaint();
                hardGamePlay.revalidate();
                try {
                    Thread.sleep(11000);
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
