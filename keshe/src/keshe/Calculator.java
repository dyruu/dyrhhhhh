package keshe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame{
    private static final long serialVersionUID = -1047298397568411277L;
    private JTextField textField;
    private ActionListener myListener;
    public Calculator() {
    	setTitle("keshe计算器");
    	setSize(350,400);
    	setLocationRelativeTo(null);
    	setResizable(false);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	Container container = getContentPane();//新建容器
    	container.setLayout(new BorderLayout(5, 5));
    	//BorderLayout：它将容器分为五个区域：北（NORTH）、南（SOUTH）、
    	//东（EAST）、西（WEST）和中（CENTER）。每个区域最多只能包含一个组件，
    	//GridLayout：它将容器划分为一个二维的网格，可以在网格的每个位置放置一个组件。
    	JPanel pnlNorth = new JPanel();//北面面板
    	JPanel pnlCenter = new JPanel();//中面面板
    	
    	//textFeild,panel 放于容器
    	container.add(pnlNorth,BorderLayout.NORTH);
    	container.add(pnlCenter, BorderLayout.CENTER); 
    	textField = new JTextField();//文本框设置
    	textField.setFont(new Font("宋体",Font.PLAIN,25));
    	textField.setEditable(false);
    	textField.setHorizontalAlignment (JTextField.RIGHT);
    	
    	 pnlNorth.setLayout(new BorderLayout(5, 5));//北面面板的边界布局
    	 // 设置上边的文本框和按钮的位置为边界布局
         JButton btnClear1 = useButton("<<<"); // new一个button按钮
         JButton btnClear2 = useButton("(+/-)");
         pnlNorth.add(textField,BorderLayout.CENTER);//文本框中间
         pnlNorth.add(btnClear1,BorderLayout.EAST);//按钮1右边
         pnlNorth.add(btnClear2,BorderLayout.WEST);//按钮2"(+/-)"左边
         String[] titles = {
                 "7", "8", "9", "C", "+", 
                 "4", "5", "6", "%", "-",
                 "1", "2", "3", "√", "×",
                 "0", ".", "=", "D", "÷"
         };
         //中央面板的按钮布局
         pnlCenter.setLayout(new GridLayout(4,5,5,5));///网格
         // pnlCenter 面板的布局设置为一个 4 行 5 列的网格布局，
         //并且每个网格单元之间（水平和垂直方向）都有 5 个像素的间距。
         for(int i = 0;i < titles.length;i++) {
        	 JButton btnNum = useButton(titles[i]);
        	 btnNum.setFont(new Font("微软雅黑",Font.PLAIN,24));
        	 //Font.PLAIN表普通样式，24磅
        	 pnlCenter.add(btnNum);
        	 
         }
    }  
    public JButton useButton (final String titles) {
    	/**
    	 * final 修饰titles 使不被修改
    	 */
    	JButton button = new JButton(String.valueOf(titles));
    	//事件监听
    	if(myListener == null) {
    		//创建匿名内部类
    		//当按钮上发生某个动作（例如被点击）时，`actionPerformed`方法会被调用。
    		//在这个方法中，首先通过`event.getActionCommand()`获取触发该事件的按钮上的
    		//文本或命令字符串（通常是通过`JButton`的构造函数或`setActionCommand`方法设置的）
    		myListener = new ActionListener() {  
    			public void actionPerformed(ActionEvent event) {
    				String  btnInformation  = event.getActionCommand();
    				char key2 = btnInformation.charAt(0);
    			}
    	};
    }
    button.addActionListener(myListener);//添加事件监听
    
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
	        		text = textField.getText()+key2;//获取文本，添加字符拼接String
	        		textField.setText(text);//将修改完的test设置回testFiled文本字段，GUI中更新显示内容
	        		text = "";//清空
	        	}break;
    	}
    }
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
      
}