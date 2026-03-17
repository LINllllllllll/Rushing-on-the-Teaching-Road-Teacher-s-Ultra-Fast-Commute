package view;

import model.*;

import javax.swing.*;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class regisFrame extends JFrame {
    JLabel userLabel;
    JLabel pswLabel;
    JTextField userField;
    JTextField pswField;
    JButton regisButton;
    JButton retButton;
    User user;

    public regisFrame() {
        userField = new JTextField();
        pswField = new JPasswordField();
        userField.setBounds(365, 213, 280, 40);
        pswField.setBounds(365, 265, 280, 40);
        this.add(userField);
        this.add(pswField);

        regisButton = new JButton();

        regisButton.addActionListener(e -> {
            String user = userField.getText();
            String pswd = pswField.getText();
            if ("".equals(user)) 
            {
                JOptionPane.showMessageDialog(null, "用户名不能为空，请重新输入！");
            } 
            else if ("".equals(pswd)) 
            {
                JOptionPane.showMessageDialog(null, "密码不能为空，请重新输入！");
            } 
            else 
            {
                try 
                {
                    int p = register(user, pswd);
                    if (p == 0) 
                    {
                        JOptionPane.showMessageDialog(null, "注册失败！");
                    } 
                    else if (p == 1) 
                    {
                        JOptionPane.showMessageDialog(null, "账号已存在！");
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "注册成功！");
                        new mainFrame(this.user);
                        dispose();
                    }
                } 
                catch (HeadlessException e1) 
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } 
                catch (Exception e1) 
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                };
            }
        });
        regisButton.setBounds(405, 320, 88, 30);
        regisButton.setContentAreaFilled(false);
        regisButton.setBorderPainted(false);

        this.add(regisButton);

        retButton = new JButton();
        retButton.addActionListener(e -> {
            dispose();
            new firstFarme();
        });
        retButton.setIcon(new ImageIcon("resources/images/retbt.png"));
        retButton.setBounds(20, 30, 150, 50);
        retButton.setContentAreaFilled(false);
        retButton.setBorderPainted(false);
        this.add(retButton);

        if (!initBackground()) {
            JOptionPane.showMessageDialog(null, "程序资源缺失！");
            dispose();
        }

        this.setSize(900, 530);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setVisible(true);
    }

    // 进行注册
    public int register(String user, String pswd) 
    {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/data/gameData.txt"), "UTF-8"))) 
        {
            List<String> infs = new ArrayList<String>();
            String temp = br.readLine();
            while (temp != null)  
            {
                String[] temps = temp.split("_");
                if (temps[0].equals(user)) 
                {
                    return 1;
                }
                infs.add(temp);
                temp = br.readLine();
            }
            String inf1 = user + "_" + pswd + "_" + "1_0_0_1_0_0_1_0_0_1_0_0_0_0_0_0_0_0_0";
            this.user = new User(inf1.split("_"));
            infs.add(user + "_" + pswd + "_" + "1_0_0_1_0_0_1_0_0_1_0_0_0_0_0_0_0_0_0");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resources/data/gameData.txt"), "UTF-8"));
            for (String inf : infs) 
            {
                bw.write(inf + "\n");
            }
            bw.flush();
            return 2;
        } 
        catch (Exception e) 
        {
            System.out.println(e);
            return 0;
        }
    }

    private Boolean initBackground() 
    {

        JPanel backPanel = new JPanel();
        try {
            // 为背景图片初始化
            backPanel = new BackgroundPanel("resources/images/regisbg.png");
        } catch (IOException e) {
            return false;
        }

        // 设置背景图片大小及位置
        backPanel.setBounds(0, 0, 900, 530);
        this.add(backPanel);
        return true;
    }

    public static void main(String[] args) 
    {
        new regisFrame();
    }
}