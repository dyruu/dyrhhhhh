package keshe;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  
  
public class TypingTest {  
  
    private JTextArea testArea;  
    private JTextArea inputArea;  
    private JLabel timerLabel;  
    private Timer timer;  
    private long startTime;  
    private String testText;
    
    //private String testText; // ��׼�ı�  
    private String userInput; // �û�����  
    //private long testStartTime; // ���Կ�ʼʱ�䣨���룩  
    private long testEndTime; // ���Խ���ʱ�䣨���룩
  
    public TypingTest() {  
        JFrame frame = new JFrame("Ӣ�Ĵ��ֲ���");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setSize(600, 400);  
  
        JPanel panel = new JPanel(new BorderLayout(10, 10));  
  
        testArea = new JTextArea(10, 50);  
        testArea.setEditable(false);  
        JScrollPane testScrollPane = new JScrollPane(testArea);  
  
        inputArea = new JTextArea(10, 50);  
        JScrollPane inputScrollPane = new JScrollPane(inputArea);  
  
        timerLabel = new JLabel("00:00");  
  
        JButton startButton = new JButton("��ʼ");  
        startButton.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                startTime = System.currentTimeMillis();  
                timer = new Timer(1000, new ActionListener() {  
                    int seconds = 0;  
  
                    @Override  
                    public void actionPerformed(ActionEvent e) {  
                        seconds++;  
                        timerLabel.setText(String.format("%02d:%02d", seconds / 60, seconds % 60));  
                    }  
                });  
                timer.start();  
                loadTestText(); // ���������Ǵ��ļ��м��ز����ı�  
                testArea.setText(testText);  
                inputArea.setText("");  
                inputArea.requestFocus();  
            }  
        });  
  
        JButton stopButton = new JButton("ֹͣ");  
        stopButton.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                timer.stop();  
                calculateStats(); // ��������ʺ�¼���ٶ�  
            }  
        });  
  
        panel.add(testScrollPane, BorderLayout.NORTH);  
        panel.add(inputScrollPane, BorderLayout.CENTER);  
        panel.add(timerLabel, BorderLayout.EAST);  
        panel.add(startButton, BorderLayout.SOUTH);  
        panel.add(stopButton, BorderLayout.EAST);  
  
        frame.add(panel);  
        frame.setVisible(true);  
    }  
  
    private void loadTestText() {  
        // ʾ�������ļ��ж�ȡ�����ı����������Ϊ��ʾ��ʹ��Ӳ������ַ���  
        testText = "This is a sample text for typing test.";  
    }  
  

    private void calculateStats() {  
        if (testText == null || userInput == null || startTime <= 0 || testEndTime <= 0) {  
            System.out.println("�޷�����ͳ����Ϣ����Ϊȱ�ٱ�Ҫ�����ݡ�");  
            return;  
        }  
  
        int totalCharacters = testText.length();  
        int correctCharacters = 0;  
  
        // �Ƚ��û�����ͱ�׼�ı�  
        int minLength = Math.min(testText.length(), userInput.length());  
        for (int i = 0; i < minLength; i++) {  
            if (testText.charAt(i) == userInput.charAt(i)) {  
                correctCharacters++;  
            }  
        }  
  
        // ����û�����ȱ�׼�ı�������ô�����Ĳ��ֶ���Ϊ����  
        int errors = userInput.length() - totalCharacters;  
        if (errors > 0) {  
            correctCharacters -= errors;  
        }  
  
        // ���������  
        double errorRate = (totalCharacters - correctCharacters) / (double) totalCharacters * 100;  
  
        // ����ƽ��¼���ٶȣ��ַ�/���ӣ�  
        long testDurationInSeconds = (testEndTime - startTime) / 1000; // ת��Ϊ��  
        double cpm = (double) correctCharacters / testDurationInSeconds * 60; // �ַ�/����  
  
        // ������  
        System.out.println("���ַ���: " + totalCharacters);  
        System.out.println("��ȷ�ַ���: " + correctCharacters);  
        System.out.println("������: " + String.format("%.2f%%", errorRate));  
        System.out.println("ƽ��¼���ٶ�: " + String.format("%.2f cpm", cpm));  
  
        // �����Ҫ��Ҳ���Խ�����洢����������л򷵻ؽ��  
    }  
  
  
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(new Runnable() {  
            @Override  
            public void run() {  
                new TypingTest();  
            }  
        });  
    }  
}