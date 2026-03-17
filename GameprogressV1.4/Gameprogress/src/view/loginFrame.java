package view;

import model.*;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;



//需要一个用户类 记录 用户的 关卡信息 装备信息
//关卡类
//装备类
//主界面类的创建需要一个用户类参数
public class loginFrame extends JFrame
{
    JTextField userField;
    JTextField pswField;
    JButton loginButton;
    JButton retButton;
    User user;
    
    public loginFrame()
    {
        userField = new JTextField();
        pswField = new JPasswordField();
        userField.setBounds(370, 218, 280, 40);
        pswField.setBounds(370,273,280,40);
        this.add(userField);
        this.add(pswField);
        
        loginButton = new JButton("登录");

        loginButton.addActionListener(e -> {
            String user = userField.getText();
            String password = pswField.getText();
            if ("".equals(user))
            {
                JOptionPane.showMessageDialog(null, "用户名不能为空，请重新输入！");
            }
            else if ("".equals(password))
            {
                JOptionPane.showMessageDialog(null, "密码不能为空，请重新输入！");
            }
            //判断是否正确登录
            else if(!login(user, password))
            {
                JOptionPane.showMessageDialog(null, "密码错误，请重新输入！");
            }
            else 
            {
                //跳转到新页面
                new mainFrame(this.user);
                dispose();
            }
        });

        loginButton.setIcon(new ImageIcon("resources/images/loginbutton.png"));
        loginButton.setBounds(430,310,95,40);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);


        this.add(loginButton);

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

        if (!initBackground())
        {
            JOptionPane.showMessageDialog(null, "程序资源缺失！");
            dispose();
        }
        
        this.setSize(900, 530);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setVisible(true);
    }
    //判断账户密码是否正确
    private Boolean login(String user,String pswd)
    {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("resources/data/gameData.txt"), "UTF-8")))
        {
            String temp = br.readLine();
            while(temp != null)
            {
                String[] infs = temp.split("_");
                if(infs[0].equals(user) && infs[1].equals(pswd))
                {
                    this.user = new User(infs);
                    return true;
                }
                temp = br.readLine();
            }
        }
        catch(Exception e)
        {
            return false;
        }
        return false;
    }

    private Boolean initBackground()
    {
        JPanel backPanel = new JPanel();
        try 
        {
            backPanel = new BackgroundPanel("resources/images/loginbg.png");
        } 
        catch (IOException e) 
        {
            return false;
        }
        //设置背景图片大小及位置
        backPanel.setBounds(0, 0, 900, 530);
        this.add(backPanel);
        return true;
    }

    public static void main(String[] args)
    {
        new loginFrame();
    }
}