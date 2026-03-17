package controller;

import model.*;
import model.Component;
import view.*;

import java.util.LinkedList;
import java.util.List;

import info.Score;

import java.awt.Graphics;

import javax.swing.JPanel;

public class PlaneGameController extends JPanel {
    User user; // 用户类
    List<Component> components = new LinkedList<>(); // 游戏组件列表
    Plane plane; // 载具类
    GameBackground map; // 背景类
    PlaneObstacle planeObstacle; // 障碍物类
    GearList gear; // 齿轮类
    Fraction fraction; // 积分板类
    gameFrame jFrame; // 主窗口类
    Boolean gameClear = false; // 游戏结束标志
    Gas gas; // 油量类
    int diff; // 难度
    int model; // 模式
    Score score = new Score(); // 积分

    public PlaneGameController(int diff, User user, gameFrame JFrame, int model) {
        super();
        this.user = user;
        this.diff = diff;
        this.model = model;
        this.jFrame = JFrame;
        initComponents(); // 初始化游戏组件
        gear.addScore(score); // 将积分对象添加到齿轮类中
        fraction.addScore(score); // 将积分对象添加到积分板类中
    }

    public void initComponents() {
        this.map = new GameBackground(diff, "resources/images/planegamebg.png"); // 创建背景对象
        components.add(map); // 将背景对象添加到游戏组件列表中
        this.planeObstacle = new PlaneObstacle(1); // 创建障碍物对象
        components.add(planeObstacle); // 将障碍物对象添加到游戏组件列表中
        this.gas = new Gas(map.getSpeed(), 0); // 创建油量对象
        components.add(gas); // 将油量对象添加到游戏组件列表中
        this.gear = new GearList(map, 0); // 创建齿轮对象
        components.add(gear); // 将齿轮对象添加到游戏组件列表中
        this.plane = new Plane(0, 200, 80, 80, model); // 创建载具对象
        this.plane.setCost(this.plane.getCost() * diff); // 根据难度设置载具的消耗
        components.add(plane); // 将载具对象添加到游戏组件列表中
        this.fraction = new Fraction(plane); // 创建积分板对象
        components.add(fraction); // 将积分板对象添加到游戏组件列表中
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Component component : components) {
            component.printComponent(g); // 绘制游戏组件
        }
    }

    /**
     * 下一帧
     */
    public void nextFrame() {
        if (fraction.getgrads() >= 1000) { // 判断是否达到游戏胜利条件
            gameClear = true; // 设置游戏结束标志为true
        }

        for (Component component : components) {
            component.step(); // 更新游戏组件的状态
        }
        Collision(); // 碰撞检测

        if (plane.getOil() < 0 || plane.gety() > 610 || plane.gety() < -100) { // 判断游戏结束条件
            new gameoverFrame(user, "plane", diff, plane.getGear()); // 创建游戏结束窗口
            jFrame.over = true; // 设置主窗口的结束标志为true
            jFrame.dispose(); // 关闭主窗口
        }
    }

    private void Collision() {
        if (gas.isCollision(plane)) { // 检测是否与油量发生碰撞
            plane.setoil(plane.getOil() + 50); // 增加油量
            gas.setColed(1); // 设置油量对象被收集的标志为true
            gas.resetPosition(); // 将油量对象重置到屏幕外
        }

        gear.Collision(plane); // 检测是否与齿轮发生碰撞
        planeObstacle.Collision(plane); // 检测是否与障碍物发生碰撞
    }

    public void jump() {
        plane.setJumping(1); // 设置载具跳跃
    }

    public Boolean getGameover() {
        return this.gameClear; // 获取游戏结束标志
    }

    public int getGears() {
        return plane.getGear(); // 获取载具的齿轮数
    }
}
