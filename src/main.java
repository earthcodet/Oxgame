import javax.swing.*;

public class main {
        public static void main(String[] args) {
            JFrame frame = new JFrame();
            register registerPanel = new register();
            frame.setContentPane(registerPanel.getMainPanel());
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }


