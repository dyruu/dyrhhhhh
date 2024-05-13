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
    
    //private String testText; // 标准文本  
    private String userInput; // 用户输入  
    //private long testStartTime; // 测试开始时间（毫秒）  
    private long testEndTime; // 测试结束时间（毫秒）
  
    public TypingTest() {  
        JFrame frame = new JFrame("英文打字测试");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setSize(600, 400);  
  
        JPanel panel = new JPanel(new BorderLayout(10, 10));  
  
        testArea = new JTextArea(10, 50);  
        testArea.setEditable(false);  
        JScrollPane testScrollPane = new JScrollPane(testArea);  
  
        inputArea = new JTextArea(10, 50);  
        JScrollPane inputScrollPane = new JScrollPane(inputArea);  
  
        timerLabel = new JLabel("00:00");  
  
        JButton startButton = new JButton("开始");  
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
                loadTestText(); // 假设这里是从文件中加载测试文本  
                testArea.setText(testText);  
                inputArea.setText("");  
                inputArea.requestFocus();  
            }  
        });  
  
        JButton stopButton = new JButton("停止");  
        stopButton.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                timer.stop();  
                calculateStats(); // 计算错误率和录入速度  
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
        // 示例：从文件中读取测试文本，这里仅作为演示，使用硬编码的字符串  
        testText = "This is a sample text for typing test.";  
    }  
  

    private void calculateStats() {  
        if (testText == null || userInput == null || startTime <= 0 || testEndTime <= 0) {  
            System.out.println("无法计算统计信息，因为缺少必要的数据。");  
            return;  
        }  
  
        int totalCharacters = testText.length();  
        int correctCharacters = 0;  
  
        // 比较用户输入和标准文本  
        int minLength = Math.min(testText.length(), userInput.length());  
        for (int i = 0; i < minLength; i++) {  
            if (testText.charAt(i) == userInput.charAt(i)) {  
                correctCharacters++;  
            }  
        }  
  
        // 如果用户输入比标准文本长，那么超出的部分都视为错误  
        int errors = userInput.length() - totalCharacters;  
        if (errors > 0) {  
            correctCharacters -= errors;  
        }  
  
        // 计算错误率  
        double errorRate = (totalCharacters - correctCharacters) / (double) totalCharacters * 100;  
  
        // 计算平均录入速度（字符/分钟）  
        long testDurationInSeconds = (testEndTime - startTime) / 1000; // 转换为秒  
        double cpm = (double) correctCharacters / testDurationInSeconds * 60; // 字符/分钟  
  
        // 输出结果  
        System.out.println("总字符数: " + totalCharacters);  
        System.out.println("正确字符数: " + correctCharacters);  
        System.out.println("错误率: " + String.format("%.2f%%", errorRate));  
        System.out.println("平均录入速度: " + String.format("%.2f cpm", cpm));  
  
        // 如果需要，也可以将结果存储到类的属性中或返回结果  
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