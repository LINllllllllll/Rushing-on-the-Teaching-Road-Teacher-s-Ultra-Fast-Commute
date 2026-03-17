package model;

import info.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Gear implements Collision {
    private final int NUM_IMAGE = 3;
    List<Image> images = new ArrayList<>();
    int SPEED;

    // 生成0-2的随机数，用于选择齿轮图案
    int randomImage = (int)(Math.random() * 3); // 随机选择齿轮图案
    int x, y;
    int width = 50, height = 50;
    int num;
    int[] nums = {1, 3, 5};
    private Score score;

    public Gear(int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < NUM_IMAGE; i++) {
            images.add(new ImageIcon("resources/images/gear" + i + ".png").getImage()); // 加载齿轮图片
        }
        num = nums[randomImage]; // 根据随机数选择齿轮对应的得分
    }

    public void printGear(Graphics g) {
        g.drawImage(images.get(randomImage), x, y, width, height, null); // 绘制齿轮图案
    }

    @Override
    public Boolean isCollision(Carrier Carrier) {
        if (Carrier.getx() + Carrier.getWidth() > this.x &&
                Carrier.getx() < this.x + this.width &&
                Carrier.gety() + Carrier.getHeight() > this.y &&
                Carrier.gety() < this.y + this.height) {
            score.setGear(score.getGear() + num); // 与携带物碰撞时更新得分
            return true;
        }
        return false;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addScore(Score score) {
        this.score = score; // 添加得分对象
    }
}
