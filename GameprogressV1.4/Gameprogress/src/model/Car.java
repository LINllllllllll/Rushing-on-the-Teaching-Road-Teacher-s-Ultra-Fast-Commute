package model;

import javax.swing.*;
import java.awt.*;

public class Car extends Carrier implements Component {
    // 车辆攻击力
    int attack = 0;
    // 跳跃路径1
    int[] yPath1 = { 270, 235, 205, 190, 160, 142, 145, 131, 99, 89, 81, 75, 81, 81, 82, 86, 89, 99, 111, 125, 142, 160,
            190, 205, 235, 270 };
    // 跳跃路径2
    int[] yPath2 = { 160, 142, 125, 111, 99, 89, 81, 75, 81, 81, 82, 86, 89, 99, 111, 125, 142, 160, 190, 205, 235,
            270 };

    /**
     * 构造函数
     * 
     * @param x     车辆在游戏窗口中的横坐标位置
     * @param y     车辆在游戏窗口中的纵坐标位置
     * @param width 车辆的宽度
     * @param height车辆的高度
     * @param model 车辆的型号，用于加载对应的图片和设置油耗和损耗值
     */
    public Car(int x, int y, int width, int height, int model) {
        super(x, y, width, height);
        // 加载车辆图片
        image = new ImageIcon("resources/images/car" + model + ".png").getImage();
        // 设置油耗和损耗值
        if (model == 1) {
            setCost(0.25);
            setScar(34.0);
        } else if (model == 2) {
            setCost(0.15);
            setScar(25.0);
        } else if (model == 3) {
            setCost(0.1);
            setScar(20.0);
        }
    }

    @Override
    public void printComponent(Graphics g) {
        printCar(g);
    }

    /**
     * 进入下一帧图
     */
    @Override
    public void step() {
        // 设置车辆图片
        carImage = image;
        // 减少油量
        super.setoil(super.getOil() - this.cost);
        // 跳起状态
        if (jumping >= 0) {
            jump();
        }
    }

    /**
     * 绘制车辆
     * 
     * @param g 系统图形类
     */
    public void printCar(Graphics g) {
        g.drawImage(carImage, x, y, width, height, null);
    }

    /**
     * 车辆跳跃
     */
    public void jump() {
        if (jumping >= 0 && jumping < yPath1.length && attack == 0) {
            // 跳跃路径1
            this.y = yPath1[jumping];
            jumping++;
        } else if (jumping >= 0 && jumping < yPath2.length && attack == 1) {
            // 跳跃路径2
            this.y = yPath2[jumping];
            jumping++;
        } else {
            // 跳跃结束
            jumping = -1;
            attack = 0;
        }
    }

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    @Override
    public int getx() {
        return super.getx();
    }

    @Override
    public int gety() {
        return super.gety();
    }

    public double getOil() {
        return super.getOil();
    }

    public void setOil(double oil) {
        super.setoil(oil);
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
