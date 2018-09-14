


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class register {
    private JTextField Tusername;
    private JPasswordField Ppassword1;
    private JPasswordField Ppassword2;
    private JTextField Tname;
    private JTextField Temail;
    private JButton attachButton;
    private JLabel image;
    private JButton btnregister;
    private JButton btncancel;
    private JPanel mainPanel;
    static String imagelink="";
    public register() {
        attachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(mainPanel);
                File f=chooser.getSelectedFile();
                imagelink=f.getAbsolutePath();
                System.out.println(imagelink);
                ImageIcon iconLogo = new ImageIcon(imagelink);
                image.setIcon(iconLogo);
            }
        });
        btncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        btnregister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

    }

    public JPanel getMainPanel(){
        return  mainPanel;
    }

    }
}

