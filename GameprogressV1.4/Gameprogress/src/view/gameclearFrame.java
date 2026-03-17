package view;

import model.*;

import javax.swing.*;

import java.awt.Font;

import java.io.IOException;
public class gameclearFrame extends JFrame
{
    User user;
    String type;
    int diff;
    int gears;
    JButton retButton;
    JButton nextcpButton;
    JLabel gearNum;
    Font font = new Font("微软雅黑",Font.PLAIN, 40);

    public gameclearFrame(User user,String type,int diff,int gears)
    {
        this.user = user;
        this.type = type;
        this.diff = diff;
        this.gears = gears;

        user.setGears(user.getGears() + gears);
        int cp = 3 * (diff - 1);
        if(type.equals("car"))
        {
            cp += 1;
        }
        else if(type.equals("plane"))
        {
            cp += 2;
        }
        else if(type.equals("ship"))
        {
            cp += 3;
        }
        if(cp < 9 && user.getCheckPointsList().get(cp).equals("0"))
        {
            user.getCheckPointsList().set(cp,"1");
        }
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

        nextcpButton = new JButton();
        nextcpButton.addActionListener(e -> {
            if(!(type.equals("ship") && diff == 3))
            {
                if(type.equals("car"))
                {
                    new equipFrame(user, "plane", diff);
                }
                else if(type.equals("plane"))
                {
                    new equipFrame(user, "ship", diff);
                }
                else if(type.equals("ship"))
                {
                    new equipFrame(user, "car", diff + 1);
                }
            }
            dispose();
        });
        nextcpButton.setBounds(476, 170, 310, 70);
        nextcpButton.setContentAreaFilled(false);
        nextcpButton.setBorderPainted(false);
        this.add(nextcpButton);

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
            backPanel = new BackgroundPanel("resources/images/gameclearbg.png");
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
        new gameclearFrame(user, "car", 1,0);
    }
}