package model;

import javax.swing.*;
import java.awt.*;

public class Plane extends Carrier implements Component 
{
    int[] ypath = { 20, 20, 20, 20, 20 };   // Y轴跳跃路径数组

    /**
     * 构造函数
     * @param x X坐标
     * @param y Y坐标
     * @param width 宽度
     * @param height 高度
     * @param model 飞机型号
     */
    public Plane(int x, int y, int width, int height,int model) 
    {
        super(x, y, width, height);
        image = new ImageIcon("resources/images/plane" + model + ".png").getImage();   // 加载飞机图片
        if (model == 1) {
            setCost(0.25);    // 设置燃油消耗
            setScar(34.0);    // 设置碰撞得分
        } else if (model == 2) {
            setCost(0.15);
            setScar(25.0);
        } else if (model == 3) {
            setCost(0.1);
            setScar(20.0);
        }
    }

    /**
     * 绘制组件
     * @param g 画笔对象
     */
    @Override
    public void printComponent(Graphics g) {
        printPerson(g);   // 绘制飞机
    }

    /**
     * 移动一步
     */
    @Override
    public void step() 
    {
        super.setoil(super.getOil() - cost);   // 更新燃油量
        if (jumping >= 0) 
        {
            jump();   // 飞机跳跃
        } 
        else 
        {
            sety(gety() + 10);   // 飞机下降
        }
    }

    /**
     * 绘制飞机
     * @param g 画笔对象
     */
    public void printPerson(Graphics g) 
    {
        g.drawImage(image, x, y, width, height, null);   // 绘制飞机图片
    }

    /**
     * 飞机跳跃
     */
    public void jump() 
    {
        if (jumping >= 0 && jumping < 5) 
        {
            sety(gety() - ypath[jumping]);   // 更新Y坐标
            jumping++;
        } 
        else 
        {
            jumping = -1;
        }
    }

    /**
     * 设置跳跃状态
     */
    public void setJumping() 
    {
        if (jumping == -1) 
        {
            jumping = 0;   // 更新跳跃状态
        }
    }

    /**
     * 获取宽度
     * @return 宽度
     */
    @Override
    public int getWidth() {
        return super.getWidth();
    }

    /**
     * 获取高度
     * @return 高度
     */
    @Override
    public int getHeight() {
        return super.getHeight();
    }

    /**
     * 获取X坐标
     * @return X坐标
     */
    @Override
    public int getx() {
        return super.getx();
    }

    /**
     * 获取Y坐标
     * @return Y坐标
     */
    @Override
    public int gety() {
        return super.gety();
    }

    /**
     * 获取燃油量
     * @return 燃油量
     */
    public double getOil() 
    {
        return super.getOil();
    }

    /**
     * 设置燃油量
     * @param oil 燃油量
     */
    public void setOil(double oil) 
    {
        super.setoil(oil);
    }
}
