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
 
    int CorrectNum=0;//������ȷ��
    int ErrorNum=0;//���ִ�����
    int TypeNum=0;//��������
 
    Timer  time;
    int Time=0;//����ʱ��
 
    int v=0;//�����ٶ�
    double lv = 0;
 
    JFileChooser  chooser;
 
 
    public TypeFrame(){
        this.setBounds(600,300,1200,600);//λ�� ��С
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setVisible(true);
    }
 
    public void init(){
        Bar=new JMenuBar();
        menu=new JMenu("�˵�");
        item1=new JMenuItem("�����ı�");
        item1.addActionListener(this);
        item2=new JMenuItem("����");
        item2.addActionListener(this);
        item3=new JMenuItem("�˳�");
        item3.addActionListener(this);
 
        Bar.add(menu);
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        this.getContentPane().add(Bar);
 
 
 
        label1=new JLabel("��ʱ��");
        label2=new JLabel("��������");
        label3=new JLabel("��ȷ��");
        label4=new JLabel("����");
        label5=new JLabel("ƽ���ٶȣ�");
        label6=new JLabel("������");
 
 
        text1=new JTextField(10);
        text1.setHorizontalAlignment(JTextField.CENTER);//�����ı�������ʾ
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
 
        //����Ƚ���--��Ҫ����ȷ����������������Ҫ��׼д������ֲ�������
        // �Ƿ��Զ����У�Ĭ��Ϊ false
        //void setLineWrap(boolean wrap)
        //row--���� columns--����
        textArea1 = new JTextArea(11, 80);
        textArea1.setLineWrap(true);
        textArea1.setFont(new Font("����",Font.BOLD,20));
        textArea1.append("abcdefghijklmnopqrstuvwsyz");
 
        textArea2 = new JTextArea(11, 80);
        textArea2.setLineWrap(true);
        textArea2.setFont(new Font("����",Font.BOLD,20));
        textArea2.addKeyListener(this);
        try {
            BufferedReader br=new BufferedReader(new FileReader("D:\\���棨D��\\Typing.txt"));
            String str;
            textArea1.setText(null);
            while ((str=br.readLine())!=null){
                textArea1.append(str);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
// 
 
 
        //��ζ�textArea���м�����
 
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
            // ����û�ѡ�����ļ���Ŀ¼����ȡ�û���ѡ���ļ�·�������
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
                // System.out.println("�û�ѡ����ļ�·��Ϊ��" + filePath);
            }
        }else if(e.getSource()==item2){
            String str=textArea2.getText();
            try {
                BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\���棨D��\\TypingSave.txt"));
                bw.write(str);
                bw.close();//�������Ǳ��治���ı���
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(str);
            System.out.println("����ɹ�");
        } else if(e.getSource()==item3){
            System.exit(0);
        }
 
 
        Time++;
        text1.setText(Time+"");
 
        v=TypeNum*60/Time;
        text5.setText(v+""+"��/����");
        
        
 
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
            JOptionPane.showMessageDialog(null, "�ѳ������ַ�Χ��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
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