package model;

import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class Obstacle implements Component, Collision 
{
    // 每一帧展示图片
    Image image;

    int type;  // 障碍物类型

    int x = 900 + 100, y = 310;  // 障碍物的初始坐标
    final int width = 80, height = 80;  // 障碍物的宽度和高度
    int SPEED;  // 障碍物移动的速度
    int fundSpd = 10;  // 基础速度
    Random ran = new Random();  // 随机数生成器
    int coled = 0;  // 碰撞标志

    @Override
    public int getx() {
        return x;
    }

    @Override
    public int gety() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Obstacle(int diff) 
    {
        type = ran.nextInt(2);  // 随机生成障碍物类型
        image = new ImageIcon("resources/images/ob" + (type + 1) + ".png").getImage();  // 加载障碍物图片
        SPEED = fundSpd * diff;  // 根据难度调整障碍物速度
        SPEED *= (type + 1);  // 根据障碍物类型调整速度
    }

    public void printObstacle(Graphics g) {
        g.drawImage(image, x, y, width, height, null);  // 在指定位置绘制障碍物
    }

    @Override
    public void printComponent(Graphics g) {
        printObstacle(g);
    }

    @Override
    public void step() 
    {
        x -= SPEED;  // 障碍物向左移动
        if(x < -180)
        {
            resetPosition();  // 障碍物移出窗口后重新设置位置
        }
    }

    /**
     * 到窗口外后生成到新位置
     */
    public void resetPosition() 
    {
        type = ran.nextInt(2);  // 随机生成障碍物类型
        image = new ImageIcon("resources/images/ob" + (type + 1) + ".png").getImage();  // 加载障碍物图片
        setSpeed(fundSpd * (type + 1));  // 根据障碍物类型调整速度
        int temp = 900;
        // 新产生位置在显示屏幕外，窗口宽<位置<窗口宽x2
        x = (int) (Math.random() * temp + temp);  // 随机设置障碍物的横坐标
        setColed(0);  // 设置碰撞标志为未碰撞
    }

    @Override
    public Boolean isCollision(Carrier carrier) 
    {
        return  coled == 0 && carrier.gety() < this.gety() + 80 &&  // 判断是否发生碰撞
                carrier.gety() + 120 > this.gety() &&
                carrier.getx() + carrier.getWidth() > this.getx() + 30 &&
                carrier.getx() < this.getx() + this.getWidth();
    }

    public Boolean isAttack(Car car) 
    {
        if (car.gety() + 120 == this.gety() && car.getx() + car.getWidth() > this.getx() + 30 && car.getx() < this.getx() + this.getWidth()) 
        {
            car.setAttack(1);  // 设置车辆的攻击状态
            car.setJumping(0);  // 设置车辆的跳跃状态
            return true;
        }
        return false;
    }

    public void setSpeed(int speed)
    {
        this.SPEED = speed;  // 设置障碍物速度
    }

    public int getColed()
    {
        return coled;  // 获取碰撞标志
    }
    
    public void setColed(int coled)
    {
        this.coled = coled;  // 设置碰撞标志
    }
}
