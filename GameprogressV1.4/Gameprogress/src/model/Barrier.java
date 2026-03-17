package model;
import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class Barrier implements Component, Collision {
    // 障碍物在游戏窗口中的纵坐标位置
    int[] ypos = {290, 370, 450};
    // 障碍物类型
    int type;
    // 障碍物图片
    Image image;
    // 障碍物的横坐标和纵坐标
    int x = 900 + 100, y = 310;
    // 障碍物的宽度和高度
    final int width = 80, height = 40;
    // 障碍物的速度
    int SPEED;
    // 基础速度
    int fundSpd = 10;
    // 标记是否发生碰撞
    int coled = 0;
    // 随机数生成器
    Random ran = new Random();

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

    public Barrier(int diff) {
        // 加载障碍物图片
        image = new ImageIcon("resources/images/ob1.png").getImage();
        // 根据难度调整速度
        SPEED = fundSpd * diff;
        // 随机生成障碍物类型
        type = ran.nextInt(3);
        // 随机生成障碍物的纵坐标位置
        y = ypos[type];
        // 随机生成障碍物的横坐标位置
        x = (int) (Math.random() * 900 + 900);
    }

    // 绘制障碍物
    public void printObstacle(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    @Override
    public void printComponent(Graphics g) {
        printObstacle(g);
    }

    @Override
    public void step() {
        // 障碍物向左移动
        x -= SPEED;
        // 当障碍物完全离开窗口时，重新设置障碍物的位置
        if (x < -180) {
            resetPosition();
        }
    }

    /**
     * 将障碍物重新设置到窗口外的新位置
     */
    public void resetPosition() {
        // 重新生成障碍物类型
        type = ran.nextInt(3);
        // 重新生成障碍物的横坐标位置，使其在显示屏幕外，即窗口宽度 < 位置 < 窗口宽度的两倍
        x = (int) (Math.random() * 900 + 900);
        // 重新生成障碍物的纵坐标位置
        y = ypos[type];
        // 将碰撞标记设为0
        setColed(0);
    }

    @Override
    public Boolean isCollision(Carrier carrier) {
        // 如果障碍物未发生碰撞且障碍物的纵坐标比运载工具的纵坐标小70，
        // 并且运载工具的横坐标加上宽度大于障碍物的横坐标加上30，并且运载工具的横坐标小于障碍物的横坐标加上宽度，则发生了碰撞
        return coled == 0 && carrier.gety() == this.gety() - 70 &&
                carrier.getx() + carrier.getWidth() > this.getx() + 30 &&
                carrier.getx() < this.getx() + this.getWidth();
    }

    public void setSpeed(int speed) {
        this.SPEED = speed;
    }

    public int getColed() {
        return coled;
    }

    public void setColed(int coled) {
        this.coled = coled;
    }
}
