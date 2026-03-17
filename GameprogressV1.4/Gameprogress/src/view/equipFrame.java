package view;

import model.*;

import javax.swing.*;

import java.io.IOException;
import java.util.List;

public class equipFrame extends JFrame {
    User user;
    int diff;
    int pos;
    int click;
    JButton retButton;
    JButton staButton;
    JButton t1;
    JButton t2;
    JButton t3;
    JLabel show;
    JLabel intro;
    JLabel gold;

    public equipFrame(User user, String type, int diff) {
        click = -1;
        this.diff = diff;
        this.user = user;
        show = new JLabel();
        intro = new JLabel();
        this.add(show);
        this.add(intro);

        if (type.equals("car")) {
            pos = 0;
        } else if (type.equals("plane")) {
            pos = 1;
        } else if (type.equals("ship")) {
            pos = 2;
        }

        t1 = new JButton();
        t2 = new JButton();
        t3 = new JButton();
        String goldS = "" + user.getGears();
        gold = new JLabel(goldS);
        gold.setBounds(755, 30, 100, 20);
        this.add(gold);
        t1.setBounds(200, 430, 100, 50);
        t2.setBounds(400, 430, 100, 50);
        t3.setBounds(600, 430, 100, 50);
        t1.setContentAreaFilled(false);
        t1.setBorderPainted(false);
        t2.setContentAreaFilled(false);
        t2.setBorderPainted(false);
        t3.setContentAreaFilled(false);
        t3.setBorderPainted(false);
        this.add(t1);
        this.add(t2);
        this.add(t3);
        retButton = new JButton();
        retButton.addActionListener(e -> {
            dispose();
            new chooseFrame(user);
        });
        retButton.setIcon(new ImageIcon("resources/images/retbt.png"));
        retButton.setBounds(20, 30, 150, 50);
        retButton.setContentAreaFilled(false);
        retButton.setBorderPainted(false);
        this.add(retButton);

        t1.setIcon(new ImageIcon("resources/images/" + type + "1model1.png"));
        t2.setIcon(new ImageIcon("resources/images/" + type + "2model1.png"));
        t3.setIcon(new ImageIcon("resources/images/" + type + "3model1.png"));
        t1.addActionListener(p -> {
            click = 0;
            show.setIcon(new ImageIcon("resources/images/" + type + "1model2.png"));
            show.setBounds(100, 170, 300, 150);
            intro.setText(("高油耗,低耐久"));
            intro.setBounds(500, 200, 200, 100);
        });
        t2.addActionListener(p -> {
            click = 1;
            show.setIcon(new ImageIcon("resources/images/" + type + "2model2.png"));
            show.setBounds(100, 170, 300, 150);
            intro.setText(("中等油耗,中等耐久"));
            intro.setBounds(500, 200, 200, 100);
        });
        t3.addActionListener(p -> {
            click = 2;
            show.setIcon(new ImageIcon("resources/images/" + type + "3model2.png"));
            show.setBounds(100, 170, 300, 150);
            intro.setText(("低油耗,高耐久"));
            intro.setBounds(500, 200, 200, 100);
        });
        staButton = new JButton();
        staButton.setIcon(new ImageIcon("resources/images/satbt.png"));
        staButton.setBounds(720, 230, 100, 50);
        staButton.addActionListener(e -> {
            List<String> cps = user.getCarrierList();
            if (click != -1) {
                int c = Integer.parseInt(cps.get(pos * 3 + click));
                if (c != 0) {
                    if (type.equals("car")) {
                        new loadFrame(0, diff, user, click + 1);
                    } else if (type.equals("plane")) {
                        new loadFrame(1, diff, user, click + 1);
                    } else if (type.equals("ship")) {
                        new loadFrame(2, diff, user, click + 1);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "未拥有该载具!");
                }
            }
        });
        this.add(staButton);

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

    public boolean initBackground() {
        JPanel backPanel = new JPanel();
        try {
            backPanel = new BackgroundPanel("resources/images/equbg.png");
        } catch (IOException e) {
            return false;
        }
        // 设置背景图片大小及位置
        backPanel.setBounds(0, 0, 900, 530);
        this.add(backPanel);
        return true;
    }

    public static void main(String[] args) {
        String[] infs = { "1", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "1", "0", "0", "1", "0", "0", "1", "0",
                "0", "10000000" };
        User user = new User(infs);
        new equipFrame(user, "car", 1);
    }
}