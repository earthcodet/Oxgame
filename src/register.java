import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

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
                    DBObject dfEmail = collection.findOne(new BasicDBObject("Email",Temail.getText()));
                    String tempChack1 =Ppassword1.getText().toString();
                    String tempChack2 =Ppassword2.getText().toString();
                    if(imagelink.equals("")){
                        JOptionPane.showMessageDialog(null,"กรุณาอัพรูป","",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(!tempChack1.equals(tempChack2)&&!tempChack1.equals("")){
                        JOptionPane.showMessageDialog(null,"รหัสผ่านไม่ตรงกัน","",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(dfId!=null){
                        JOptionPane.showMessageDialog(null,"ชื่อผู้ใช้ มีผู้ใช้แล้ว!!","",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(dfEmail!=null){
                        JOptionPane.showMessageDialog(null,"อีเมล์ มีผู้ใช้แล้ว!!","",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(!Tusername.getText().equals("")&&!Temail.getText().equals("")&&!Tname.getText().equals("")) {
                        BasicDBObject document = new BasicDBObject();
                        document.put("_id", Tusername.getText());
                        document.put("Password", Ppassword1.getText().toString());
                        document.put("Email", Temail.getText());
                        document.put("Name", Tname.getText());
                        String newFileName = Tusername.getText();
                        byte[] imageBytes = LoadImage(imagelink);
                        GridFS gfsPhoto = new GridFS(db, "photo");
                        GridFSInputFile gfsFile = gfsPhoto.createFile(imageBytes);
                        gfsFile.setFilename(newFileName);
                        gfsFile.save();
                        DBCursor cursor = gfsPhoto.getFileList();
                        while (cursor.hasNext()) {
                            System.out.println(cursor.next());
                        }

                        collection.insert(document);
                        JOptionPane.showMessageDialog(null, "สมัครสมาชิกเรียบร้อยแล้ว^^", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                         JOptionPane.showMessageDialog(null,"ใส่ข้อมูลไม่ให้ครบถ้วน หรือ ใส่ข้อมูลไม่ถูกต้อง !!","",JOptionPane.INFORMATION_MESSAGE);

                        }
                        client.close();
                }catch (Exception exp){
                    System.out.println(exp);
                }
                }
        });
    }

    public JPanel getMainPanel(){
        return  mainPanel;
    }
    public static byte[] LoadImage(String filePath) throws Exception {
        File file = new File(filePath);
        int size = (int)file.length();
        byte[] buffer = new byte[size];
        FileInputStream in = new FileInputStream(file);
        in.read(buffer);
        in.close();
        return buffer;
    }
}

