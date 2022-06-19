package Easy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import static Easy.GameTime.timeEnd;

public class LeaderBoard extends JFrame {
    JPanel panel = new JPanel(new GridLayout(0, 4, 9, 2));
    private String leader = "leader";
    private DefaultTableModel tm;
    static ArrayList<String> abc;
    static JTextField tf;
    static String html;

    public LeaderBoard() throws IOException {
        if (ChooseLevel.easy.isSelected()) {
            EasyGame.easyJFrameGamePlay.setVisible(false);
        }
        if (ChooseLevel.medium.isSelected()) {
            MediumGame.mediumJFrameGamePlay.setVisible(false);
        }
        if (ChooseLevel.hard.isSelected()) {
            HardGame.hardJFrameGamePlay.setVisible(false);
        }
        html = timeEnd;
        JLabel head = new JLabel(String.valueOf(JLabel.CENTER));
        add(head, BorderLayout.NORTH);
        JButton accept = new JButton("accept");
        accept.setBounds(150, 280, 70, 50);
        panel.add(accept);
        JButton accept2 = new JButton("accept2");
        accept.setBounds(200, 280, 70, 50);
        panel.add(accept2);
        addLabAndTxtFld(html);
        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.getText();
            }
        });
        accept2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new GameMenu();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    void addLabAndTxtFld(String txt) {
        JLabel lab = new JLabel(txt, JLabel.RIGHT);
        tf = new JTextField(20);
        tf.setToolTipText("Enter your nick");
        lab.setLabelFor(tf);
        panel.add(lab);
        panel.add(tf);

    }

    public void savLeaderBoard() throws IOException {
        FileOutputStream fs = new FileOutputStream(leader);
        ObjectOutputStream os = new ObjectOutputStream(fs);

        os.writeObject(tm.getDataVector());
        os.close();
        fs.close();
    }

        /*JFrame end = new JFrame();
        end.setSize(400, 400);
        end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        end.setResizable(false);
        end.setLayout(null);
        end.setLocationRelativeTo(null);
        JPanel gameOverPanel = new JPanel();
        gameOverPanel.setBounds(0, 0, 400, 400);
        gameOverPanel.setVisible(true);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monaco", Font.PLAIN, 25));
        textArea.setText(timeEnd);
        textArea.setEditable(false);
        textArea.setVisible(true);


        JButton accept = new JButton("accept");
        accept.setBounds(150,300,100,50);
        end.add(accept);


        gameOverPanel.add(textArea);
        end.setVisible(true);
        end.add(gameOverPanel);*/
}