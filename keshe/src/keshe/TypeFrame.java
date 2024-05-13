package keshe;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
 
public class TypeFrame extends JFrame implements ActionListener, KeyListener {
    JMenuBar Bar;
    JMenu menu;
    JMenuItem item1;
    JMenuItem item2;
    JMenuItem item3;
 
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;
 
    JTextField text1;
    JTextField text2;
    JTextField text3;
    JTextField text4;
    JTextField text5;
    JTextField text6;
 
    JTextArea textArea1;
    JTextArea textArea2;
 
    int CorrectNum=0;//打字正确数
    int ErrorNum=0;//打字错误数
    int TypeNum=0;//打字总数
 
    Timer  time;
    int Time=0;//打字时间
 
    int v=0;//打字速度
    double lv = 0;
 
    JFileChooser  chooser;
 
 
    public TypeFrame(){
        this.setBounds(600,300,1200,600);//位置 大小
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setVisible(true);
    }
 
    public void init(){
        Bar=new JMenuBar();
        menu=new JMenu("菜单");
        item1=new JMenuItem("导入文本");
        item1.addActionListener(this);
        item2=new JMenuItem("保存");
        item2.addActionListener(this);
        item3=new JMenuItem("退出");
        item3.addActionListener(this);
 
        Bar.add(menu);
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        this.getContentPane().add(Bar);
 
 
 
        label1=new JLabel("用时：");
        label2=new JLabel("总字数：");
        label3=new JLabel("正确：");
        label4=new JLabel("错误：");
        label5=new JLabel("平均速度：");
        label6=new JLabel("错误率");
 
 
        text1=new JTextField(10);
        text1.setHorizontalAlignment(JTextField.CENTER);//设置文本居中显示
        text2=new JTextField(10);
        text2.setHorizontalAlignment(JTextField.CENTER);
        text3=new JTextField(10);
        text3.setHorizontalAlignment(JTextField.CENTER);
        text4=new JTextField(10);
        text4.setHorizontalAlignment(JTextField.CENTER);
        text5=new JTextField(10);
        text5.setHorizontalAlignment(JTextField.CENTER);
        text6=new JTextField(10);
        text6.setHorizontalAlignment(JTextField.CENTER);
        
        time =new Timer(1000, this);
        time.start();
 
        //这里比较难--既要控制确定的行数和列数还要保准写入的数字不会缩进
        // 是否自动换行，默认为 false
        //void setLineWrap(boolean wrap)
        //row--行数 columns--列数
        textArea1 = new JTextArea(11, 80);
        textArea1.setLineWrap(true);
        textArea1.setFont(new Font("隶书",Font.BOLD,20));
        textArea1.append("abcdefghijklmnopqrstuvwsyz");
 
        textArea2 = new JTextArea(11, 80);
        textArea2.setLineWrap(true);
        textArea2.setFont(new Font("隶书",Font.BOLD,20));
        textArea2.addKeyListener(this);
        try {
            BufferedReader br=new BufferedReader(new FileReader("D:\\桌面（D）\\Typing.txt"));
            String str;
            textArea1.setText(null);
            while ((str=br.readLine())!=null){
                textArea1.append(str);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
// 
 
 
        //如何对textArea进行监听？
 
        chooser=new JFileChooser();
 
 
        this.add(label1);
        this.add(text1);
        this.add(label2);
        this.add(text2);
        this.add(label3);
        this.add(text3);
        this.add(label4);
        this.add(text4);
        this.add(label5);
        this.add(text5);
        this.add(label6);
        this.add(text6);
 
        this.getContentPane().add(textArea1);
        this.getContentPane().add(textArea2);
 
        this.setVisible(true);
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==item1){
            InitData();
            Time=0;
            InitText();
            textArea2.setText(null);
            int result = chooser.showOpenDialog(null);
            // 如果用户选择了文件或目录，获取用户所选的文件路径并输出
            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = chooser.getSelectedFile().getAbsolutePath();
                try {
                    BufferedReader br=new BufferedReader(new FileReader(filePath));
                    String str;
                    textArea1.setText(null);
                    while ((str=br.readLine())!=null){
                        textArea1.append(str);
                    }
                    Time=0;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // System.out.println("用户选择的文件路径为：" + filePath);
            }
        }else if(e.getSource()==item2){
            String str=textArea2.getText();
            try {
                BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\桌面（D）\\TypingSave.txt"));
                bw.write(str);
                bw.close();//不关流是保存不了文本的
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(str);
            System.out.println("保存成功");
        } else if(e.getSource()==item3){
            System.exit(0);
        }
 
 
        Time++;
        text1.setText(Time+"");
 
        v=TypeNum*60/Time;
        text5.setText(v+""+"字/分钟");
        
        
 
    }
 
    public void InitText() {
        text2.setText(TypeNum+"");
        text3.setText(CorrectNum+"");
        text4.setText(ErrorNum+"");
        text5.setText(v+"");
        text6.setText(lv+"");
    }
 
    public void InitData() {
        CorrectNum=0;
        ErrorNum=0;
        TypeNum=0;
        v=0;
    }
 
 
    @Override
    public void keyTyped(KeyEvent e) {
 
    }
 
    @Override
    public void keyPressed(KeyEvent e) {
        if(textArea2.getText().length()<=textArea1.getText().length()){
            InitData();
            for (int i=0;i<textArea2.getText().length();i++){
                if(textArea2.getText().charAt(i)==textArea1.getText().charAt(i)){
                    CorrectNum++;
                    TypeNum++;
                    text2.setText(TypeNum+"");
                    text3.setText(CorrectNum+"");
 
                }else{
                    ErrorNum++;
                    TypeNum++;
                    text2.setText(TypeNum+"");
                    text4.setText(ErrorNum+"");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "已超出数字范围！", "提示",JOptionPane.PLAIN_MESSAGE);
        }
        InitText();
        lv=(double)ErrorNum/TypeNum;
        String formattedLv = String.format("%.2f", lv * 100); 
        text6.setText(formattedLv + "%");
    }
 
    @Override
    public void keyReleased(KeyEvent e) {
 
    }
}