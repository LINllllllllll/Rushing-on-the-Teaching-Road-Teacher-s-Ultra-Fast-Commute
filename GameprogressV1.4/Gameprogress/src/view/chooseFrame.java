package view;

import model.*;

import javax.swing.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class chooseFrame extends JFrame // 选择界面类，继承自 JFrame
{
    User user; // 用户对象
    List<JButton> buttons = new ArrayList<JButton>(); // 按钮列表

    JButton retButton; // 返回按钮

    public chooseFrame(User user) // 构造函数，接收一个用户对象
    {
        this.user = user; // 初始化用户对象

        retButton = new JButton(); // 初始化返回按钮
        // 添加返回按钮的监听器
        retButton.addActionListener(e -> {
            dispose(); // 关闭当前界面
            new mainFrame(user); // 打开主界面
        });
        retButton.setIcon(new ImageIcon("resources/images/retbt.png")); // 设置返回按钮的图标
        retButton.setBounds(20, 30, 150, 50); // 设置返回按钮的位置和大小
        retButton.setContentAreaFilled(false); // 设置返回按钮的填充区域为透明
        retButton.setBorderPainted(false); // 设置返回按钮的边框不显示
        this.add(retButton); // 将返回按钮添加到界面中


        InitButtons(); // 初始化所有的按钮
        List<String> cps = user.getCheckPointsList(); // 获取用户已通关的关卡列表
        // 添加各个按钮的监听器，如果该关卡已通关，则打开装备选择界面
        buttons.get(0).addActionListener(e -> {
            if(!cps.get(0).equals("0"))
            {
                new equipFrame(user, "car", 1);
                dispose();
            }
        });
        buttons.get(1).addActionListener(e -> {
            if(!cps.get(1).equals("0"))
            {
                new equipFrame(user, "plane", 1);
                dispose();
            }
        });
        buttons.get(2).addActionListener(e -> {
            if(!cps.get(2).equals("0"))
            {
                new equipFrame(user, "ship", 1);
                dispose();
            }
        });
        buttons.get(3).addActionListener(e -> {
            if(!cps.get(3).equals("0"))
            {
                new equipFrame(user, "car", 2);
                dispose();
            }
        });
        buttons.get(4).addActionListener(e -> {
            if(!cps.get(4).equals("0"))
            {
                new equipFrame(user, "plane", 2);
                dispose();
            }
        });
        buttons.get(5).addActionListener(e -> {
            if(!cps.get(5).equals("0"))
            {
                new equipFrame(user, "ship", 2);
                dispose();
            }
        });
        buttons.get(6).addActionListener(e -> {
            if(!cps.get(6).equals("0"))
            {
                new equipFrame(user, "car", 3);
                dispose();
            }
        });
        buttons.get(7).addActionListener(e -> {
            if(!cps.get(7).equals("0"))
            {
                new equipFrame(user, "plane", 3);
                dispose();
            }
        });
        buttons.get(8).addActionListener(e -> {
            if(!cps.get(8).equals("0"))
            {
                new equipFrame(user, "ship", 3);
                dispose();
            }
        });

        // 初始化背景图片，如果失败，则提示程序资源缺失并关闭当前界面
        if(!initBackground())
        {
            JOptionPane.showMessageDialog(null, "程序资源缺失！");
            dispose();
        }
        
        this.setSize(900, 530); // 设置界面的大小
        this.setLocationRelativeTo(null); // 将界面居中显示
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置界面关闭时的操作
        this.setUndecorated(false); // 设置界面装饰
        this.setVisible(true); // 显示界面
    }

    public void InitButtons() // 初始化按钮的函数
    {
        List<String> checkPoints = user.getCheckPointsList(); // 获取用户已通关的关卡列表
        int cp = 1; // 当前关卡数
        for(String a : checkPoints) // 遍历每个关卡
        {
            String k;
            JButton button = new JButton(); // 创建新的按钮对象
            if(a.equals("0"))
            {
                k = "0";
            }
            else
            {
                k = "1";
            }
            button.setIcon(new ImageIcon("resources/images/cp" + cp + "-" + k + ".png")); // 设置按钮的图标
            button.setBounds(200 + ((cp - 1) % 3) * 200,210 +  100 * ((cp - 1) /3), 70, 60); // 设置按钮的位置和大小
            this.add(button); // 将按钮添加到界面中
            buttons.add(button); // 将按钮添加到按钮列表中
            cp++; // 关卡数加一
        }
    }

    public Boolean initBackground() // 初始化背景图片的函数
    {
        JPanel backPanel = new JPanel(); // 创建新的面板对象
        try
        {
            backPanel = new BackgroundPanel("resources/images/choosebg.png"); // 设置面板的背景图片
        }
        catch(IOException e)
        {
            return false; // 如果设置失败，则返回假
        }
        backPanel.setBounds(0, 0, 900, 530); // 设置面板的位置和大小
        this.add(backPanel); // 将面板添加到界面中
        return true; // 返回真
    }

    public static void main(String[] args) // 主函数，用于测试
    {
        String[] infs = {"1","1","1","1","1","1","1","1","1","1","0","1","1","1","1","1","1","1","1","1","0"};
        User user = new User(infs); // 创建新的用户对象
        new chooseFrame(user); // 打开选择界面
    }
}
