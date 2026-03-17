package view;

import model.*;

import javax.swing.*;


import java.io.IOException;

//登陆注册界面
public class firstFarme extends JFrame {
    JButton loginButton, regisButton;

    public firstFarme() {
        loginButton = new JButton();
        loginButton.addActionListener(e -> {
            new loginFrame();
            dispose();
        });
        loginButton.setIcon(new ImageIcon("resources/images/loginbt.png"));
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);

        loginButton.setBounds(255, 261, 381, 48);  //900 * 530

        regisButton = new JButton();
        regisButton.addActionListener(e -> {
            new regisFrame();
            dispose();
        });
        regisButton.setIcon(new ImageIcon("resources/images/regisbt.png"));
        regisButton.setContentAreaFilled(false);
        regisButton.setBorderPainted(false);

        regisButton.setBounds(357, 209, 276, 50);

        this.add(loginButton);
        this.add(regisButton);

        if (!initBackground()) {
            JOptionPane.showMessageDialog(null, "程序资源缺失！");
            dispose();
        }
        //设置窗口的基础属性
        this.setSize(900, 530);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setVisible(true);
    }
    //初始化背景
    public boolean initBackground() {
        JPanel backPanel = new JPanel();
        try {
            backPanel = new BackgroundPanel("resources/images/firstbg.png");
        } catch (IOException e) {
            return false;
        }

        // 设置背景图片大小及位置
        backPanel.setBounds(0, 0, 900, 530);
        this.add(backPanel);
        return true;
    }

    public static void main(String[] args) {
        new firstFarme();
    }
}