import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Login {
    private JTextField Tusername;
    private JPasswordField Ppassword;
    private JButton btnlogin;
    private JButton btncancel;
    private JButton btnforget;
    private JPanel mainPanel;
    private JButton btnregister;

    public Login() {
        btnlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MongoClientURI uri = new MongoClientURI("mongodb://gameox:q1azwsxedc3@ds251902.mlab.com:51902/game_ox");
                    MongoClient client = new MongoClient(uri);
                    System.out.println("1");
                    DB db = client.getDB("game_ox");
                    try {
                        db.getCollection("username");
                    } catch (Exception exp) {
                        db.createCollection("username", null);
                    }
                    DBCollection collection = db.getCollection("username");
                    DBObject dfId = collection.findOne(new BasicDBObject("_id",Tusername.getText()));
                   if(dfId!=null){
                        JOptionPane.showMessageDialog(null,"ชื่อผู้ใช้ มีผู้ใช้แล้ว!!","",JOptionPane.INFORMATION_MESSAGE);
                    }

                    client.close();
                }catch (Exception exp){
                    System.out.println(exp);
                }
            }
        });
        btnregister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                register RegisterPanel = new register();
                frame.setContentPane(RegisterPanel.getMainPanel());
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);


            }
        });
    }
    public JPanel getMainPanel(){
        return  mainPanel;
    }
}
