package keshe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame{
    private static final long serialVersionUID = -1047298397568411277L;
    private JTextField textField;
    private ActionListener myListener;
    public Calculator() {
    	setTitle("keshe������");
    	setSize(350,400);
    	setLocationRelativeTo(null);
    	setResizable(false);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	Container container = getContentPane();//�½�����
    	container.setLayout(new BorderLayout(5, 5));
    	//BorderLayout������������Ϊ������򣺱���NORTH�����ϣ�SOUTH����
    	//����EAST��������WEST�����У�CENTER����ÿ���������ֻ�ܰ���һ�������
    	//GridLayout��������������Ϊһ����ά�����񣬿����������ÿ��λ�÷���һ�������
    	JPanel pnlNorth = new JPanel();//�������
    	JPanel pnlCenter = new JPanel();//�������
    	
    	//textFeild,panel ��������
    	container.add(pnlNorth,BorderLayout.NORTH);
    	container.add(pnlCenter, BorderLayout.CENTER); 
    	textField = new JTextField();//�ı�������
    	textField.setFont(new Font("����",Font.PLAIN,25));
    	textField.setEditable(false);
    	textField.setHorizontalAlignment (JTextField.RIGHT);
    	
    	 pnlNorth.setLayout(new BorderLayout(5, 5));//�������ı߽粼��
    	 // �����ϱߵ��ı���Ͱ�ť��λ��Ϊ�߽粼��
         JButton btnClear1 = useButton("<<<"); // newһ��button��ť
         JButton btnClear2 = useButton("(+/-)");
         pnlNorth.add(textField,BorderLayout.CENTER);//�ı����м�
         pnlNorth.add(btnClear1,BorderLayout.EAST);//��ť1�ұ�
         pnlNorth.add(btnClear2,BorderLayout.WEST);//��ť2"(+/-)"���
         String[] titles = {
                 "7", "8", "9", "C", "+", 
                 "4", "5", "6", "%", "-",
                 "1", "2", "3", "��", "��",
                 "0", ".", "=", "D", "��"
         };
         //�������İ�ť����
         pnlCenter.setLayout(new GridLayout(4,5,5,5));///����
         // pnlCenter ���Ĳ�������Ϊһ�� 4 �� 5 �е����񲼾֣�
         //����ÿ������Ԫ֮�䣨ˮƽ�ʹ�ֱ���򣩶��� 5 �����صļ�ࡣ
         for(int i = 0;i < titles.length;i++) {
        	 JButton btnNum = useButton(titles[i]);
        	 btnNum.setFont(new Font("΢���ź�",Font.PLAIN,24));
        	 //Font.PLAIN����ͨ��ʽ��24��
        	 pnlCenter.add(btnNum);
        	 
         }
    }  
    public JButton useButton (final String titles) {
    	/**
    	 * final ����titles ʹ�����޸�
    	 */
    	JButton button = new JButton(String.valueOf(titles));
    	//�¼�����
    	if(myListener == null) {
    		//���������ڲ���
    		//����ť�Ϸ���ĳ�����������类�����ʱ��`actionPerformed`�����ᱻ���á�
    		//����������У�����ͨ��`event.getActionCommand()`��ȡ�������¼��İ�ť�ϵ�
    		//�ı��������ַ�����ͨ����ͨ��`JButton`�Ĺ��캯����`setActionCommand`�������õģ�
    		myListener = new ActionListener() {  
    			public void actionPerformed(ActionEvent event) {
    				String  btnInformation  = event.getActionCommand();
    				char key2 = btnInformation.charAt(0);
    			}
    	};
    }
    button.addActionListener(myListener);//����¼�����
    
    return button;
    }
    
    int len;
    String a,b,value;
    
    char exit = '0';
    char op = 'n';
    boolean flag =false;
    
    private void action(char key2) {
    	String text;
    	switch(key2) {
	    	case '1':case '2':case '3':case '4':case '5':
	        case '6':case '7':case '8':case '9':case '0':
	        	if(flag) {
	        		textField.setText("");
	        		textField.setText(String.valueOf(key2));
	        		flag = false;
	        	}else {
	        		text = textField.getText()+key2;//��ȡ�ı�������ַ�ƴ��String
	        		textField.setText(text);//���޸����test���û�testFiled�ı��ֶΣ�GUI�и�����ʾ����
	        		text = "";//���
	        	}break;
    	}
    }
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
      
}