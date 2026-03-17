package model;

import java.awt.*;

public class Carrier 
{
    double oil;             // 油量
    int x, y;               // 坐标
    Image carImage;         // 车辆图片
    Image image;            // 图片
    int index;              // 图片序号
    int width, height;      // 宽度和高度
    int jumping;            // 跳跃状态，-1为未跳
    int temp;               // 缓存
    double scar;            // 撞一次减少的油耗值
    double cost;            // 每次移动油耗值
    int gear = 0;           // 档位，默认为0

    /**
     * 构造函数
     * @param x 车辆在游戏窗口中的横坐标位置
     * @param y 车辆在游戏窗口中的纵坐标位置
     * @param width 车辆的宽度
     * @param height车辆的高度
     */
    public Carrier(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        // 利用p进行运动图片读入
        carImage = image;
        oil = 200;          // 初始油量
        index = 0;

        this.width = width;
        this.height = height;

        jumping = -1;       // 初始跳跃状态为未跳
    }

    public int getx() 
    {
        return x;
    }

    public int gety() 
    {
        return y;
    }

    public double getOil() 
    {
        return oil;
    }

    public int getWidth() 
    {
        return width;
    }

    public int getHeight() 
    {
        return height;
    }

    public int getJumping() 
    {
        return height;
    }

    public Image getImages() 
    {
        return image;
    }

    public Image getImage() 
    {
        return carImage;
    }

    /**
     * 获取当前油耗值
     * @return 油耗值
     */
    public double getCost()
    {
        return cost;
    }

    public void setx(int x) 
    {
        this.x = x;
    }

    public void sety(int y) 
    {
        this.y = y;
    }

    public void setoil(double oil)
    {
        this.oil = oil;
    }

    public void setPersonImage(Image image) 
    {
        carImage = image;
    }

    public void setJumping(int jumping) 
    {
        this.jumping = jumping;
    }

    /**
     * 获取当前档位
     * @return 档位
     */
    public int getGear()
    {
        return this.gear;
    }

    /**
     * 设置档位
     * @param gear 档位
     */
    public void setGear(int gear)
    {
        this.gear = gear;
    }

    /**
     * 设置每次移动的油耗值
     * @param cost 油耗值
     */
    public void setCost(double cost)
    {
        this.cost = cost;
    }

    /**
     * 设置撞击一次减少的油耗值
     * @param scar 油耗值
     */
    public void setScar(double scar)
    {
        this.scar = scar;
    }

    /**
     * 获取撞击一次减少的油耗值
     * @return 油耗值
     */
    public double getScar()
    {
        return scar;
    }
}
