package view;

import model.*;

import javax.swing.*;

import java.awt.Font;

import java.io.IOException;
public class gameoverFrame extends JFrame
{
    User user;
    String type;
    int diff;
    int gears;
    JButton retButton;
    JButton replayButton;
    JLabel gearNum;
    Font font = new Font("微软雅黑",Font.PLAIN, 40);

    public gameoverFrame(User user,String type,int diff,int gears)
    {
        this.type = type;
        this.diff = diff;
        this.user = user;
        this.gears = gears;

        user.setGears(user.getGears() + gears);
        user.Save();
        
        String getgear = "" + gears;
        gearNum = new JLabel(getgear);
        gearNum.setFont(font);
        gearNum.setBounds(500, 233, 200, 100);
        this.add(gearNum);

        retButton = new JButton();
        retButton.addActionListener(e -> {
            new mainFrame(user);
            dispose();
        });

        retButton.setBounds(150, 170, 310, 70);
        retButton.setContentAreaFilled(false);
        retButton.setBorderPainted(false);

        this.add(retButton);

        replayButton = new JButton();
        replayButton.addActionListener(e -> {
            new equipFrame(user, type, diff);
            dispose();
        });
        replayButton.setBounds(476, 170, 310, 70);
        replayButton.setContentAreaFilled(false);
        replayButton.setBorderPainted(false);
        this.add(replayButton);

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

    private Boolean initBackground()
    {
        JPanel backPanel = new JPanel();
        try 
        {
            backPanel = new BackgroundPanel("resources/images/gameoverbg.png");
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
        String[] infs = { "1", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "1", "0", "0", "1", "0", "0", "1", "0",
                "0", "10000000" };
        User user = new User(infs);
        new gameoverFrame(user, "car", 1,0);
    }
}