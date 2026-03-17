package view;

import model.*;

import javax.swing.*;

import java.io.IOException;
import java.util.List;

public class sellFrame extends JFrame {
    JLabel gold;
    int model = -1;
    int t = -1;
    int pay = -1;
    User user;
    JButton retButton;
    JButton purButton;
    JButton carButton;
    JButton planeButton;
    JButton shipButton;
    JButton t1;
    JButton t2;
    JButton t3;
    JButton t4;
    JButton t5;
    JButton t6;
    JButton t7;
    JButton t8;
    JButton t9;
    JLabel show;
    JLabel intro;

    public sellFrame(User user) {
        show = new JLabel();
        intro = new JLabel();
        this.add(show);
        this.add(intro);

        this.user = user;

        t1 = new JButton();
        t2 = new JButton();
        t3 = new JButton();
        t4 = new JButton();
        t5 = new JButton();
        t6 = new JButton();
        t7 = new JButton();
        t8 = new JButton();
        t9 = new JButton();

        String goldS = "" + user.getGears();
        gold = new JLabel(goldS);
        gold.setBounds(755, 30, 100, 20);
        this.add(gold);

        t1.setBounds(200, 430, 100, 50);
        t4.setBounds(200, 430, 100, 50);
        t7.setBounds(200, 430, 100, 50);
        t2.setBounds(400, 430, 100, 50);
        t5.setBounds(400, 430, 100, 50);
        t8.setBounds(400, 430, 100, 50);
        t3.setBounds(600, 430, 100, 50);
        t6.setBounds(600, 430, 100, 50);
        t9.setBounds(600, 430, 100, 50);
        t1.setContentAreaFilled(false);
        t1.setBorderPainted(false);
        t2.setContentAreaFilled(false);
        t2.setBorderPainted(false);
        t3.setContentAreaFilled(false);
        t3.setBorderPainted(false);
        t4.setContentAreaFilled(false);
        t4.setBorderPainted(false);
        t5.setContentAreaFilled(false);
        t5.setBorderPainted(false);
        t6.setContentAreaFilled(false);
        t6.setBorderPainted(false);
        t7.setContentAreaFilled(false);
        t7.setBorderPainted(false);
        t8.setContentAreaFilled(false);
        t8.setBorderPainted(false);
        t9.setContentAreaFilled(false);
        t9.setBorderPainted(false);

        this.add(t1);
        this.add(t2);
        this.add(t3);
        this.add(t4);
        this.add(t5);
        this.add(t6);
        this.add(t7);
        this.add(t8);
        this.add(t9);

        retButton = new JButton();
        retButton.addActionListener(e -> {
            dispose();
            new mainFrame(user);
        });
        retButton.setIcon(new ImageIcon("resources/images/retbt.png"));
        retButton.setBounds(20, 30, 150, 50);
        retButton.setContentAreaFilled(false);
        retButton.setBorderPainted(false);

        carButton = new JButton();
        carButton.addActionListener(e -> {
            t1.setVisible(true);
            t2.setVisible(true);
            t3.setVisible(true);
            t4.setVisible(false);
            t5.setVisible(false);
            t6.setVisible(false);
            t7.setVisible(false);
            t8.setVisible(false);
            t9.setVisible(false);
            this.model = 0;
            t1.setIcon(new ImageIcon("resources/images/car1model1.png"));
            t2.setIcon(new ImageIcon("resources/images/car2model1.png"));
            t3.setIcon(new ImageIcon("resources/images/car3model1.png"));
            t1.addActionListener(p -> {
                this.t = 1;
                pay = 0;
                show.setIcon(new ImageIcon("resources/images/car1model2.png"));
                show.setBounds(100, 170, 300, 150);
                intro.setText(("高油耗,低耐久,售价: 0"));
                intro.setBounds(500, 200, 200, 100);
            });
            t2.addActionListener(p -> {
                this.t = 2;
                pay = 1000;
                show.setIcon(new ImageIcon("resources/images/car2model2.png"));
                show.setBounds(100, 170, 300, 150);
                intro.setText(("中等油耗,中等耐久,售价: 1000"));
                intro.setBounds(500, 200, 200, 100);
            });
            t3.addActionListener(p -> {
                this.t = 3;
                pay = 5000;
                show.setIcon(new ImageIcon("resources/images/car3model2.png"));
                show.setBounds(100, 170, 300, 150);
                intro.setText(("低油耗,高耐久,售价: 5000"));
                intro.setBounds(500, 200, 200, 100);
            });
        });
        carButton.setIcon(new ImageIcon("resources/images/carbt.png"));
        carButton.setBounds(120, 90, 165, 50);
        carButton.setContentAreaFilled(false);
        carButton.setBorderPainted(false);
        this.add(carButton);
        planeButton = new JButton();
        planeButton.setIcon(new ImageIcon("resources/images/planebt.png"));
        planeButton.addActionListener(e -> {
            t1.setVisible(false);
            t2.setVisible(false);
            t3.setVisible(false);
            t4.setVisible(true);
            t5.setVisible(true);
            t6.setVisible(true);
            t7.setVisible(false);
            t8.setVisible(false);
            t9.setVisible(false);
            this.model = 1;
            t4.setIcon(new ImageIcon("resources/images/plane1model1.png"));
            t5.setIcon(new ImageIcon("resources/images/plane2model1.png"));
            t6.setIcon(new ImageIcon("resources/images/plane3model1.png"));

            t4.addActionListener(p -> {
                this.t = 1;
                pay = 0;
                show.setIcon(new ImageIcon("resources/images/plane1model2.png"));
                show.setBounds(100, 170, 300, 150);
                intro.setText("高油耗,低耐久,售价: 0");
                intro.setBounds(500, 200, 200, 100);
            });
            t5.addActionListener(p -> {
                this.t = 2;
                pay = 1000;
                show.setIcon(new ImageIcon("resources/images/plane2model2.png"));
                show.setBounds(100, 170, 300, 150);
                intro.setText("中等油耗,中等耐久,售价: 1000");
                intro.setBounds(500, 200, 200, 100);
            });
            t6.addActionListener(p -> {
                this.t = 3;
                pay = 5000;
                show.setIcon(new ImageIcon("resources/images/plane3model2.png"));
                show.setBounds(100, 170, 300, 150);
                intro.setText("低油耗,高耐久,售价: 5000");
                intro.setBounds(500, 200, 200, 100);
            });
        });
        planeButton.setBounds(360, 90, 165, 50);
        planeButton.setContentAreaFilled(false);
        planeButton.setBorderPainted(false);
        this.add(planeButton);
        shipButton = new JButton(new ImageIcon());
        shipButton.setIcon(new ImageIcon("resources/images/shipbt.png"));
        shipButton.addActionListener(e -> {
            this.model = 2;
            t1.setVisible(false);
            t2.setVisible(false);
            t3.setVisible(false);
            t4.setVisible(false);
            t5.setVisible(false);
            t6.setVisible(false);
            t7.setVisible(true);
            t8.setVisible(true);
            t9.setVisible(true);
            t7.setIcon(new ImageIcon("resources/images/ship1model1.png"));
            t8.setIcon(new ImageIcon("resources/images/ship2model1.png"));
            t9.setIcon(new ImageIcon("resources/images/ship3model1.png"));
            t7.addActionListener(p -> {
                this.t = 1;
                pay = 0;
                show.setIcon(new ImageIcon("resources/images/ship1model2.png"));
                show.setBounds(100, 170, 300, 150);
                intro.setText("高油耗\n低耐久\n售价: 0");
                intro.setBounds(500, 200, 200, 100);
            });
            t8.addActionListener(p -> {
                this.t = 2;
                pay = 1000;
                show.setIcon(new ImageIcon("resources/images/ship2model2.png"));
                show.setBounds(100, 170, 300, 150);
                intro.setText("中等油耗\n中等耐久\n售价: 1000");
                intro.setBounds(500, 200, 200, 100);
            });
            t9.addActionListener(p -> {
                this.t = 3;
                pay = 5000;
                show.setIcon(new ImageIcon("resources/images/ship3model2.png"));
                show.setBounds(100, 170, 300, 150);
                intro.setText("低油耗\n高耐久\n售价: 5000");
                intro.setBounds(500, 200, 200, 100);
            });
        });
        shipButton.setBounds(650, 90, 165, 50);
        shipButton.setContentAreaFilled(false);
        shipButton.setBorderPainted(false);
        this.add(shipButton);
        this.add(retButton);
        purButton = new JButton();
        purButton.addActionListener(e -> {
            List<String> carriers = user.getCarrierList();
            if (model != -1 && t != -1) {
                int c = Integer.parseInt(carriers.get(this.model * 3 + t - 1));
                if (c == 0) {
                    if (user.getGears() >= pay) {
                        user.setGears(user.getGears() - pay);
                        String g = "" + user.getGears();
                        gold.setText(g);
                        carriers.set(this.model * 3 + t - 1, "1");
                        user.Save();
                        JOptionPane.showMessageDialog(null, "开发成功!");
                    } else {
                        JOptionPane.showMessageDialog(null, "开发失败！需要更多的材料!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "已拥有该载具!");
                }
            }
        });
        purButton.setIcon(new ImageIcon("resources/images/create.png"));
        purButton.setBounds(770, 250, 100, 50);

        this.add(purButton);

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
            backPanel = new BackgroundPanel("resources/images/supbg.png");
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
        new sellFrame(user);
    }
}