package Easy;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;


import static Easy.LeaderBoard.html;
import static Easy.LeaderBoard.tf;

public class Leader extends JPanel {
    JFrame frame = new JFrame("Storage");
    JList<Product> list = new JList<>();
    DefaultListModel<Product> model = new DefaultListModel<>();

    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JSplitPane splitPane = new JSplitPane();

    public Leader() throws IOException {

        list.setModel(model);

        model.addElement(new Product(tf.getText()));

        list.getSelectionModel().addListSelectionListener(e -> {
            label.setText("Time: " + html);
        });

        splitPane.setLeftComponent(new JScrollPane(list));
        panel.add(label);
        splitPane.setRightComponent(panel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(splitPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        FileOutputStream fileOutput = new FileOutputStream("Game.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOutput);
        LeaderBoard leaderBoard = new LeaderBoard();
        out.writeObject(leaderBoard);
        out.close();
        fileOutput.close();
        System.out.println("Info save");


    }

    private class Product {
        String name;

        public Product(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }


}
