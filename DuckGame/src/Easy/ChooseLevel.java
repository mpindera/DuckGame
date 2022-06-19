package Easy;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChooseLevel extends JFrame implements ActionListener {
    JFrame chooseLevelDifficult;
    static JRadioButton easy, medium, hard;
    JButton accept;
    JLabel background;

    ChooseLevel() {
        chooseLevelDifficult = new JFrame("Select difficulty level");
        chooseLevelDifficult.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseLevelDifficult.setSize(400, 300);

        easy = new JRadioButton("Easy");
        easy.setBounds(50, 50, 75, 50);
        easy.setBackground(new Color(4, 4, 21));
        easy.setForeground(Color.WHITE);

        medium = new JRadioButton("Medium");
        medium.setBounds(150, 50, 75, 50);
        medium.setBackground(new Color(4, 4, 21));
        medium.setForeground(Color.WHITE);

        hard = new JRadioButton("Hard");
        hard.setBounds(270, 50, 75, 50);
        hard.setBackground(new Color(4, 4, 21));
        hard.setForeground(Color.WHITE);

        accept = new JButton("Accept");
        accept.setBounds(150, 150, 75, 30);
        accept.setBorder(new BevelBorder(BevelBorder.LOWERED));
        accept.addActionListener(this);
        chooseLevelDifficult.add(accept);

        ButtonGroup bg = new ButtonGroup();
        bg.add(easy);
        bg.add(medium);
        bg.add(hard);

        chooseLevelDifficult.add(easy);
        chooseLevelDifficult.add(medium);
        chooseLevelDifficult.add(hard);
        chooseLevelDifficult.add(accept);
        chooseLevelDifficult.setLayout(null);
        chooseLevelDifficult.setResizable(false);
        chooseLevelDifficult.setLocationRelativeTo(null);
        chooseLevelDifficult.setVisible(true);

        ImageIcon bgIcon = new ImageIcon("src/res/sf.jpg");
        background = new JLabel();
        background.setBounds(0, 0, 960, 600);
        background.setBackground(Color.black);
        background.setLayout(null);
        background.setIcon(bgIcon);
        background.setVisible(true);

        chooseLevelDifficult.add(background);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (easy.isSelected()) {
            chooseLevelDifficult.setVisible(false);
            try {
                new EasyGame();
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        if (medium.isSelected()) {
            chooseLevelDifficult.setVisible(false);
            try {
                new MediumGame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (hard.isSelected()) {
            chooseLevelDifficult.setVisible(false);
            try {
                new HardGame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
