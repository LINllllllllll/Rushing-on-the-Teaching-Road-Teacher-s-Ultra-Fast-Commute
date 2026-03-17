package view;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.BackgroundPanel;
import model.User;

public class successFrame extends JFrame {
    User user;

    JButton retButton;

    public successFrame(User user){
        this.user=user;
        retButton = new JButton();
        retButton.addActionListener(e -> {
            dispose();
            new mainFrame(this.user);
        });
        retButton.setIcon(new ImageIcon("resources/images/retbt.png"));
        retButton.setBounds(20, 30, 150, 50);
        retButton.setContentAreaFilled(false);
        retButton.setBorderPainted(false);
        this.add(retButton);

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
            backPanel = new BackgroundPanel("resources/images/successbg.png");
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
}
