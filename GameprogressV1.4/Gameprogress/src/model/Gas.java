package model;

import java.util.Random;
import javax.swing.*;
import java.awt.*;


public class Gas implements Collision, Component
{
    Image image;                // 图片
    int SPEED;                  // 速度
    int x, y;                   // 坐标
    int width = 80, height = 80;    // 宽度和高度
    int num;                    // 数字
    int coled = 0;              // 碰撞状态，0为未碰撞
    int gametype;               // 游戏类型
    int[] ypos = {260, 340, 420};   // Y坐标位置数组
    Random ran = new Random();  // 随机数生成器

    /**
     * 构造函数
     * @param speed 速度
     * @param gametype 游戏类型
     */
    public Gas(int speed, int gametype)
    {
        image = new ImageIcon("resources/images/gascan.png").getImage();
        SPEED = speed;
        this.gametype = gametype;
        if (gametype == 0)
        {
            x = 900 + 450;
            y = 145;
        }
        if (gametype == 1)
        {
            x = (int) (Math.random() * 900) + 900;
            y = (int) (ran.nextInt(300) + 100);
        } else if (gametype == 2)
        {
            x = (int) (Math.random() * 900) + 900;
            y = ypos[ran.nextInt(3)];
        }
    }

    /**
     * 获取X坐标
     * @return X坐标
     */
    @Override
    public int getx()
    {
        return x;
    }

    /**
     * 获取Y坐标
     * @return Y坐标
     */
    @Override
    public int gety()
    {
        return y;
    }

    /**
     * 获取宽度
     * @return 宽度
     */
    @Override
    public int getWidth()
    {
        return width;
    }
    
    /**
     * 获取高度
     * @return 高度
     */
    @Override
    public int getHeight()
    {
        return height;
    }

    /**
     * 绘制燃油桶
     * @param g 画笔对象
     */
    public void printGas(Graphics g)
    {
        g.drawImage(image, x, y, width, height, null);
    }

    /**
     * 绘制组件
     * @param g 画笔对象
     */
    @Override
    public void printComponent(Graphics g)
    {
        printGas(g);
    }

    /**
     * 移动一步
     */
    @Override
    public void step()
    {
        x -= SPEED;
        if (x < -80)
        {
            resetPosition();
        }
    }

    /**
     * 重置位置
     */
    public void resetPosition()
    {
        // 新产生位置在显示屏幕外，窗口宽<位置<窗口宽x2
        if (gametype == 0)
        {
            x = (int) (Math.random() * 900) + 900;
        }
        if (gametype == 1)
        {
            x = (int) (Math.random() * 900) + 900;
            y = (int) (ran.nextInt(300) + 100);
        } else if (gametype == 2)
        {
            x = (int) (Math.random() * 900) + 900;
            y = ypos[ran.nextInt(3)];
        }
        setColed(0);
    }

    /**
     * 获取碰撞状态
     * @return 碰撞状态
     */
    public int getColed()
    {
        return coled;
    }

    /**
     * 设置碰撞状态
     * @param coled 碰撞状态
     */
    public void setColed(int coled)
    {
        this.coled = coled;
    }

    /**
     * 检测与Carrier对象的碰撞
     * @param carrier Carrier对象
     * @return 是否发生碰撞
     */
    @Override
    public Boolean isCollision(Carrier carrier)
    {
        return coled == 0 && carrier.gety() < this.gety() + 80 &&
            carrier.gety() + 120 > this.gety() &&
            carrier.getx() + carrier.getWidth() > this.getx() + 30 &&
            carrier.getx() < this.getx() + this.getWidth();
    }
}
