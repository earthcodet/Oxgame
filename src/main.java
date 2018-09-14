import javax.swing.*;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Login loginPanel = new Login();
        frame.setContentPane(loginPanel.getMainPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
