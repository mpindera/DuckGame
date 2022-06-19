package Easy;

import javax.swing.*;
import java.io.IOException;

public class Main extends JFrame {
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new GameMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
