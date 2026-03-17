package model;

import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class Roadblock implements Collision, Component {
    Image image; // 障碍物图片
    int x, y; // 障碍物的横纵坐标
    int width = 80; // 障碍物宽度
    int height; // 障碍物高度
    int SPEED; // 障碍物移动速度
    int fundSpd = 10; // 每个速度级别的基本速度
    Random ran = new Random(); // 随机数生成器
    int coled = 0; // 是否已碰撞

    /**
     * 构造函数，初始化障碍物
     * @param x 障碍物横坐标
     * @param y 障碍物纵坐标
     * @param diff 难度级别
     * @param height 障碍物高度
     */
    public Roadblock(int x, int y, int diff, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        image = new ImageIcon("resources/images/roadblock.png").getImage(); // 障碍物图片路径
        SPEED = diff * fundSpd; // 根据难度级别计算移动速度
    }

    /**
     * 获取障碍物横坐标
     * @return 障碍物横坐标
     */
    @Override
    public int getx() {
        return x;
    }

    /**
     * 获取障碍物纵坐标
     * @return 障碍物纵坐标
     */
    @Override
    public int gety() {
        return y;
    }

    /**
     * 获取障碍物宽度
     * @return 障碍物宽度
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * 获取障碍物高度
     * @return 障碍物高度
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * 绘制障碍物
     * @param g 绘图上下文
     */
    public void printObstacle(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    /**
     * 绘制组件
     * @param g 绘图上下文
     */
    @Override
    public void printComponent(Graphics g) {
        printObstacle(g);
    }

    /**
     * 下一帧
     * 障碍物移动到下一个位置，检查是否出界，出界则重新生成位置
     */
    @Override
    public void step() {
        x -= SPEED;
        if (x < -180) {
            resetPosition();
        }
    }

    /**
     * 重置障碍物位置
     * 移动到窗口外后重新生成到新位置
     */
    public void resetPosition() {

    }

    /**
     * 检测与飞机的碰撞
     * @param carrier 飞机对象
     * @return 是否发生碰撞
     */
    @Override
    public Boolean isCollision(Carrier carrier) {
        return coled == 0 && carrier.gety() < this.gety() + this.height &&
                carrier.gety() + carrier.height > this.gety() &&
                carrier.getx() > this.getx() - 80 &&
                carrier.getx() < this.getx() + 80;
    }

    /**
     * 设置障碍物速度
     * @param speed 速度值
     */
    public void setSpeed(int speed) {
        this.SPEED = speed;
    }

    /**
     * 获取是否已碰撞
     * @return 是否已碰撞
     */
    public int getColed() {
        return coled;
    }

    /**
     * 设置是否已碰撞
     * @param coled 是否已碰撞
     */
    public void setColed(int coled) {
        this.coled = coled;
    }

    /**
     * 设置障碍物横坐标
     * @param x 横坐标值
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 设置障碍物纵坐标
     * @param y 纵坐标值
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 设置障碍物高度
     * @param height 高度值
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
