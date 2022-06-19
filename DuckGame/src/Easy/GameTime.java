package Easy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTime extends JLabel {
    int updateTime = 0;
    static int seconds = 0;
    static int minutes = 0;
    static int hours = 0;
    JLabel timeCounter;
    static String secondsPrint = String.format("%02d", seconds);
    static String minutesPrint = String.format("%02d", minutes);
    static String hoursPrint = String.format("%02d", hours);
    static String timeEnd;

    GameTime() {
        Timer time = new Timer(1000, event -> {
            updateTime += 1000;

            hours = (updateTime / 3600000);
            minutes = (updateTime / 60000) % 60;
            seconds = (updateTime / 1000) % 60;

            secondsPrint = String.format("%02d", seconds);
            minutesPrint = String.format("%02d", minutes);
            hoursPrint = String.format("%02d", hours);

            timeCounter.setText(hoursPrint + ":" + minutesPrint + ":" + secondsPrint);
            timeEnd = hoursPrint + ":" + minutesPrint + ":" + secondsPrint;
        });
        time.start();

        timeCounter = new JLabel();
        timeCounter.setText("");
        timeCounter.setFont(new Font("Verdana", Font.PLAIN, 35));
        timeCounter.setBorder(BorderFactory.createBevelBorder(2));
        timeCounter.setBounds(0, 55, 200, 50);
        timeCounter.setOpaque(true);
        timeCounter.setHorizontalAlignment(JTextField.CENTER);

        if (ChooseLevel.easy.isSelected()) {
            EasyGame.easyGamePlay.add(timeCounter);
        }
        if (ChooseLevel.medium.isSelected()) {
            MediumGame.mediumGamePlay.add(timeCounter);
        }
        if (ChooseLevel.hard.isSelected()) {
            HardGame.hardGamePlay.add(timeCounter);
        }
        timeCounter.setVisible(true);
    }
}

