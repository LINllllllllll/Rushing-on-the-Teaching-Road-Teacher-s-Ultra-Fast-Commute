package view;

import javax.swing.*;

import model.*;

import java.io.IOException;


//为关卡选择界面 商城界面提供用户信息

public class mainFrame extends JFrame
{
    JButton chooseButton;
    JButton sellButton;
    User user;
    
    public mainFrame(User user)
    {
        chooseButton = new JButton();
        sellButton = new JButton();

        this.user = user;
        
        chooseButton.addActionListener(e -> {
            new chooseFrame(user);
            dispose();
        });
        sellButton.addActionListener(e -> {
            new sellFrame(user);
            dispose();
        });
        
        chooseButton.setIcon(new ImageIcon("resources/images/choosebt.png"));
        sellButton.setIcon(new ImageIcon("resources/images/supbt.png"));

        chooseButton.setBounds(140, 300, 200, 180);

        chooseButton.setContentAreaFilled(false);
        chooseButton.setBorderPainted(false);

        sellButton.setBounds(530, 300, 200, 180);

        sellButton.setContentAreaFilled(false);
        sellButton.setBorderPainted(false);

        this.add(chooseButton);
        this.add(sellButton);

        if(!initBackground())
        {
            JOptionPane.showMessageDialog(null, "程序资源缺失！");
            dispose();
        }

        this.setSize(900,530);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public Boolean initBackground()
    {
        JPanel backPanel = new JPanel();
        try 
        {
            backPanel = new BackgroundPanel("resources/images/mainbg.png");
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
        String[] infs = {"1","1","0","0","0","0","0","0","0","0","0","1","0","0","0","0","0","0","0","0","0"};
        User user = new User(infs);
        new mainFrame(user);
    }
}