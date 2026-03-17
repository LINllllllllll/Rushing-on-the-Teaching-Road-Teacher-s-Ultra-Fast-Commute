package view;

import model.*;
import controller.*;
import javax.swing.*;

import java.awt.event.KeyListener;

public class gameFrame extends JFrame implements KeyListener {
    User user;
    /** 游戏类型 */
    int type;
    /** 结束游戏标志 */
    public Boolean over = false;
    /** 暂停标志 */
    public Boolean pause = false;
    /** 键盘锁定标志 */
    private Boolean keyLock = false;
    private int FPS = 30;

    int diff;

    CarGameController cargame;
    PlaneGameController palnegame;
    ShipGameController shipgame;

    public gameFrame(int type, int diff, User user,int model) 
    {
        this.user = user;
        this.type = type;
        this.diff = diff;
        if (this.type == 0) {
            cargame = new CarGameController(diff, this, user,model);
        } else if (this.type == 1) {
            palnegame = new PlaneGameController(diff, user, this,model);
        } else if (this.type == 2) {
            shipgame = new ShipGameController(diff, user, this,model);
        }

        // 设置界面的基本属性
        this.setSize(900, 530);
        // 位置居中
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        // 设置窗体的Logo图标
        this.setVisible(true);

        draw();
    }

    public void draw() 
    {
        // 注册键盘监听
        addKeyListener(this);
        if(type == 0)
        {
            cargame.setBounds(0, 0, 900, 530);
            this.add(cargame);
        }
        else if(type == 1)
        {
            palnegame.setBounds(0, 0, 900, 530);
            this.add(palnegame);
        }
        else if(type == 2)
        {
            shipgame.setBounds(0, 0, 900, 530);
            this.add(shipgame);
        }
        while (!over) {
            if(type == 0)
            {
                if(cargame.getGameover())
                {
                    new gameclearFrame(user, "car", diff, cargame.getGears());
                    over = true;
                    JOptionPane.showMessageDialog(null, "恭喜通关！");
                    dispose();
                }
            }
            else if(type == 1)
            {
                if(palnegame.getGameover())
                {
                    new gameclearFrame(user, "plane", diff, palnegame.getGears());
                    over = true;
                    JOptionPane.showMessageDialog(null, "恭喜通关！");
                    dispose();
                }
            }
            else if(type == 2)
            {
                if(shipgame.getGameover())
                {
                    if(diff == 3)
                    {
                        JOptionPane.showMessageDialog(null, "您已经通过所有关卡！");
                        over = true;
                        new successFrame(user);
                        dispose();
                    }
                    else
                    {
                        new gameclearFrame(user, "ship", diff, shipgame.getGears());
                        over = true;
                        JOptionPane.showMessageDialog(null, "恭喜通关！");
                        dispose();
                    }
                }
            }
            // 未暂停
            if (!pause) 
            {
                // 渲染下一帧画面
                // 判别 用 cargame planegame shipgame
                if (type == 0) 
                {
                    cargame.nextFrame();
                    cargame.repaint();
                } 
                else if (type == 1) 
                {
                    palnegame.nextFrame();
                    palnegame.repaint();
                } 
                else if(type == 2) 
                {
                    shipgame.nextFrame();
                    shipgame.repaint();
                }
                // 键盘操作与帧同步的锁
                if (keyLock) {
                    keyLock = false;
                }
            }

            try {
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    /**
     * 监听键盘
     */
    // 32和38 空格 上 40 下
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        if (type == 0) {
            if (e.getKeyCode() == 38) {
                cargame.jump();
            }
            if (e.getKeyCode() == 32) {
                this.pause = !this.pause;
            }
        }
        if (type == 1) {
            if (e.getKeyCode() == 38) {
                palnegame.jump();
            }
            if (e.getKeyCode() == 32) {
                this.pause = !this.pause;
            }
        }
        if (type == 2) {
            if (e.getKeyCode() == 38) {
                shipgame.up();
            }
            if (e.getKeyCode() == 40) {
                shipgame.down();
            }
            if (e.getKeyCode() == 32) {
                this.pause = !this.pause;
            }
        }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {

    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {

    }

    public static void main(String[] args) 
    {
        String[] infs = { "1", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "1", "0", "0", "1", "0", "0", "1", "0",
                "0", "10000000" };
        User user = new User(infs);
        new gameFrame(1, 1, user,1);
    }
}