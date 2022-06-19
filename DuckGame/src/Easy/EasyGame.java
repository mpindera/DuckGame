package Easy;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class EasyGame extends JPanel {
    static JFrame easyJFrameGamePlay;
    static JPanel easyGamePlay;
    private JLabel labelEasy;
    private JLabel labelEasyPoints;
    private final JPanel[] panelEasy = new JPanel[10];
    static int Width = 1300;
    static int Height = 670;
    private int killedDucks = 0;
    private int score = 0;
    private int lifeWhite = 1, lifeBlue = 2, lifeGreen = 5;
    int milisWhite = 10;
    private int i = 9;
    static boolean gameOverBool = false;

    EasyGame() throws IOException, InterruptedException {
        Thread threadForInitGame = new Thread(() -> {
            try {
                frameEasy();
                obstacle();
                hearts();
                new GameTime();
                addTo();
                waitFor();
                setMainScore();
                easyJFrameGamePlay.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        threadForInitGame.start();
    }

    public void setMainScore() {
        panelEasy[0] = new JPanel();
        panelEasy[0].setBounds(550, 0, 125, 70);
        panelEasy[0].setBackground(new Color(0, 0, 0, 0));
        panelEasy[0].setBackground(new Color(40, 36, 36, 168));

        labelEasy = new JLabel("Killed " + killedDucks);
        labelEasy.setForeground(Color.WHITE);
        labelEasy.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

        panelEasy[0].add(labelEasy);
        easyGamePlay.add(panelEasy[0]);

        labelEasyPoints = new JLabel("Score " + score);
        labelEasyPoints.setForeground(Color.WHITE);
        labelEasyPoints.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

        panelEasy[0].add(labelEasyPoints);
        easyGamePlay.add(panelEasy[0]);
    }
    public void frameEasy() {
        easyJFrameGamePlay = new JFrame("Easy Game");
        easyJFrameGamePlay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        easyGamePlay = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgIcon = new ImageIcon("src/res/Background.png");
                g.drawImage(bgIcon.getImage(), 0, 0, 1800, 670, null);
            }
        };

        easyGamePlay.setLayout(null);
        easyJFrameGamePlay.add(easyGamePlay);
        easyJFrameGamePlay.setSize(Width, Height);
        easyJFrameGamePlay.setResizable(false);
        easyJFrameGamePlay.setLocationRelativeTo(null);
    }

    public void hearts() throws IOException {
        JPanel panelForHearts = new JPanel();
        panelForHearts.setLayout(new GridLayout(1, 10, 10, 0));
        panelForHearts.setBounds(0, 0, 550, 50);
        for (int i = 1; i < 10; i++) {
            panelEasy[i] = new JPanel();
            BufferedImage img = ImageIO.read(new File("src/res/heart.png"));
            JLabel pic = new JLabel(new ImageIcon(img));
            panelEasy[i].setBackground(new Color(0, 0, 0, 0));
            panelEasy[i].add(pic);
            panelEasy[i].setVisible(true);

            panelForHearts.add(panelEasy[i]);
        }
        panelForHearts.setBackground(new Color(0, 0, 0, 0));
        panelForHearts.setVisible(true);
        easyGamePlay.add(panelForHearts);
    }

    public void obstacle() {
        JPanel obstacleGrassG = new JPanel();
        JPanel obstaclesTabletG = new JPanel();
        JPanel buttonMushroomG = new JPanel();

        ImageIcon obstacleGrass = new ImageIcon("src/res/Grass.png");
        JRadioButton buttonGrass = new JRadioButton();
        buttonSet(buttonGrass, obstacleGrass);
        buttonGrass.setVisible(true);

        obstacleGrassG.add(buttonGrass);
        obstacleGrassG.setBounds(400, 400, 235, 231);
        obstacleGrassG.setBackground(new Color(0, 0, 0, 0));
        obstacleGrassG.setVisible(true);
        easyGamePlay.add(obstacleGrassG);

        ImageIcon obstaclesTablet = new ImageIcon("src/res/Tablet.png");
        JRadioButton buttonTablet = new JRadioButton();
        buttonSet(buttonTablet, obstaclesTablet);
        buttonTablet.setVisible(true);

        obstaclesTabletG.add(buttonTablet);
        obstaclesTabletG.setBounds(200, 400, 329, 329);
        obstaclesTabletG.setBackground(new Color(0, 0, 0, 0));
        obstaclesTabletG.setVisible(true);
        easyGamePlay.add(obstaclesTabletG);

        ImageIcon obstaclesMushroom = new ImageIcon("src/res/Mushroom.png");
        JRadioButton buttonMushroom = new JRadioButton();
        buttonSet(buttonMushroom, obstaclesMushroom);
        buttonMushroom.setVisible(true);

        buttonMushroomG.add(buttonMushroom);
        buttonMushroomG.setBounds(850, 400, 180, 156);
        buttonMushroomG.setBackground(new Color(0, 0, 0, 0));
        buttonMushroomG.setVisible(true);
        easyGamePlay.add(buttonMushroomG);
    }


    public JPanel generateDuckWhite() {
        final int[] xx = {0};
        int yyWhite = (int) (Math.random() * 190) + 280;
        JPanel jPanelRed = new JPanel();
        ImageIcon duck1 = new ImageIcon("src/res/duckMW.png");
        JRadioButton button1 = new JRadioButton();
        buttonSet(button1, duck1);

        jPanelRed.setVisible(true);
        Thread timer = new Thread(() -> {
            while (!gameOverBool) {
                jPanelRed.setBounds(xx[0], yyWhite, 200, 185);
                jPanelRed.add(button1);
                jPanelRed.repaint();
                jPanelRed.revalidate();
                jPanelRed.setBackground(new Color(0, 0, 0, 0));
                jPanelRed.setVisible(true);

                button1.setVisible(true);
                xx[0]++;
                if (xx[0] == 1300) {
                    easyGamePlay.remove(jPanelRed);
                    easyGamePlay.remove(button1);
                    jPanelRed.remove(button1);
                    panelEasy[i].setVisible(false);
                    i--;
                    if (i == 0) {
                        try {
                            new LeaderBoard();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        gameOverBool = true;
                        System.out.println("GAME OVER");
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
                if (count == lifeWhite) {
                    score += 10;
                    killedDucks++;
                    xx[0] = 1301;
                    jPanelRed.remove(button1);
                    count = 0;

                    labelEasyPoints.setText("Score " + score);
                    labelEasy.setText("killed " + killedDucks);
                    labelEasy.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

                    easyGamePlay.remove(jPanelRed);
                    easyGamePlay.remove(button1);
                    easyGamePlay.revalidate();
                    easyGamePlay.repaint();
                }
            }
        });
        timer.start();
        return jPanelRed;
    }


    public JPanel generateDuckBlue() {
        final int[] xx = {0};
        int yyBlue = (int) (Math.random() * 190) + 280;
        JPanel jPanelBlue = new JPanel();
        ImageIcon duckBlue = new ImageIcon("src/res/duckMB.png");
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
                if (xx[0] == 1300) {
                    easyGamePlay.remove(jPanelBlue);
                    easyGamePlay.remove(button2);
                    panelEasy[i].setVisible(false);
                    jPanelBlue.remove(button2);
                    i--;
                    if (i == 0) {
                        try {
                            new LeaderBoard();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        gameOverBool = true;
                        System.out.println("GAME OVER");
                    }
                    break;
                }
                try {
                    Thread.sleep(15);
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
                    xx[0] = 1301;
                    count = 0;
                    jPanelBlue.remove(button2);
                    labelEasyPoints.setText("Score " + score);
                    labelEasy.setText("killed " + killedDucks);
                    labelEasy.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

                    easyGamePlay.remove(jPanelBlue);
                    easyGamePlay.remove(button2);
                    easyGamePlay.revalidate();
                    easyGamePlay.repaint();
                }
            }
        });
        timer.start();

        return jPanelBlue;
    }


    public JPanel generateDuckGreen() {
        final int[] xx = {0};
        int yyGreen = (int) (Math.random() * 190) + 280;
        JPanel jPanelGreen = new JPanel();
        ImageIcon duckGreen = new ImageIcon("src/res/duckM-.png");
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
                if (xx[0] == 1300) {
                    easyGamePlay.remove(jPanelGreen);
                    easyGamePlay.remove(button3);
                    jPanelGreen.remove(button3);
                    panelEasy[i].setVisible(false);
                    i--;
                    if (i == 0) {
                        try {
                            new LeaderBoard();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        gameOverBool = true;
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
                    xx[0] = 1301;
                    jPanelGreen.remove(button3);
                    count = 0;
                    labelEasyPoints.setText("Score " + score);
                    labelEasy.setText("killed " + killedDucks);
                    labelEasy.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

                    easyGamePlay.remove(jPanelGreen);
                    easyGamePlay.remove(button3);
                    easyGamePlay.revalidate();
                    easyGamePlay.repaint();
                }
            }
        });
        timer.start();
        return jPanelGreen;
    }

    boolean ob = false;

    public void waitFor() {
        Thread duckThreadGreen = new Thread(() -> {
            while (!Thread.interrupted()) {
                easyGamePlay.add(generateDuckGreen());
                easyGamePlay.repaint();
                easyGamePlay.revalidate();
                try {
                    Thread.sleep(5000);
                    milisWhite = 5;
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
        if (!ob) {
            Thread duckThreadWhite = new Thread(() -> {
                while (!Thread.interrupted()) {
                    easyGamePlay.add(generateDuckWhite());
                    easyGamePlay.repaint();
                    easyGamePlay.revalidate();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            duckThreadWhite.start();
        }

        Thread duckThreadBlue = new Thread(() -> {
            while (!Thread.interrupted()) {
                easyGamePlay.add(generateDuckBlue());
                easyGamePlay.repaint();
                easyGamePlay.revalidate();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        duckThreadBlue.start();


    }
    public void buttonSet(JRadioButton button, ImageIcon icon) {
        button.setIcon(icon);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBorder(null);
    }

}
