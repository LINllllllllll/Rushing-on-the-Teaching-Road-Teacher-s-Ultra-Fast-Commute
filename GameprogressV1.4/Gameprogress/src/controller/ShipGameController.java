package controller;

import model.*;
import model.Component;
import view.*;


import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

import info.Score;

public class ShipGameController extends JPanel
{
    User user; // 用户对象
    List<Component> components = new LinkedList<>(); // 组件列表
    Ship ship; // 载具对象
    GameBackground map; // 背景对象
    Barrier barrier1; // 障碍1对象
    Barrier barrier2; // 障碍2对象
    GearList gear; // 齿轮列表对象
    Fraction fraction; // 积分板对象
    gameFrame jFrame; // 主窗口Jframe对象

    Gas gas; // 汽油对象

    int diff; // 难度级别
    Score score = new Score(); // 分数对象
    Boolean gameclear = false; // 游戏是否结束的标志
    int model; // 游戏模式

    public ShipGameController(int diff, User user, gameFrame JFrame, int model)
    {
        super();
        this.user = user;
        this.diff = diff;
        this.model = model;
        this.jFrame = JFrame;
        initComponents();
        gear.addScore(score); // 将分数对象传递给齿轮列表对象
        fraction.addScore(score); // 将分数对象传递给积分板对象
    }

    // 初始化组件
    public void initComponents()
    {
        this.map = new GameBackground(diff, "resources/images/shipgamebg.png"); // 创建背景对象
        components.add(map); // 将背景对象添加到组件列表
        this.barrier1 = new Barrier(diff); // 创建障碍1对象
        components.add(barrier1); // 将障碍1对象添加到组件列表
        this.barrier2 = new Barrier(diff); // 创建障碍2对象
        components.add(barrier2); // 将障碍2对象添加到组件列表
        this.gas = new Gas(map.getSpeed(), 2); // 创建汽油对象
        components.add(gas); // 将汽油对象添加到组件列表
        this.gear = new GearList(map, 2); // 创建齿轮列表对象
        components.add(gear); // 将齿轮列表对象添加到组件列表
        this.ship = new Ship(0, 300, 120, 120, model); // 创建载具对象
        this.ship.setCost(this.ship.getCost() * diff); // 根据难度级别调整载具费用
        components.add(ship); // 将载具对象添加到组件列表
        this.fraction = new Fraction(ship); // 创建积分板对象
        components.add(fraction); // 将积分板对象添加到组件列表
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        for (Component component : components)
        {
            component.printComponent(g); // 绘制每个组件
        }
    }

    /**
     * 下一帧
     */
    public void nextFrame()
    {
        if (fraction.getgrads() >= 1000)
        {
            gameclear = true; // 达到积分上限，游戏结束
        }
        for (Component component : components)
        {
            component.step(); // 组件进行下一步操作
        }
        while (barrier1.gety() == barrier2.gety())
        {
            barrier2.resetPosition(); // 重置障碍2的位置，确保障碍1和障碍2的纵坐标不同
        }
        Collision(); // 进行碰撞检测
        if (ship.getOil() < 0)
        {
            new gameoverFrame(user, "ship", diff, ship.getGear()); // 载具油量耗尽，游戏结束
            jFrame.over = true;
            jFrame.dispose();
        }
    }

    private void Collision()
    {
        if (gas.isCollision(ship)) // 检测载具与汽油是否碰撞
        {
            ship.setoil(ship.getOil() + 50.0); // 载具油量增加
            gas.setColed(1); // 设置汽油对象为已被碰撞状态
            gas.resetPosition(); // 重置汽油对象的位置
        }
        if (barrier1.isCollision(ship)) // 检测载具与障碍1是否碰撞
        {
            ship.setOil(ship.getOil() - ship.getScar()); // 载具油量减少
            barrier1.setColed(1); // 设置障碍1对象为已被碰撞状态
            barrier1.resetPosition(); // 重置障碍1的位置
            if (ship.getOil() <= 0)
            {
                new gameoverFrame(user, "ship", diff, ship.getGear()); // 载具油量耗尽，游戏结束
                jFrame.over = true;
                jFrame.dispose();
            }
        }
        if (barrier2.isCollision(ship)) // 检测载具与障碍2是否碰撞
        {
            ship.setOil(ship.getOil() - ship.getScar()); // 载具油量减少
            barrier2.setColed(1); // 设置障碍2对象为已被碰撞状态
            barrier2.resetPosition(); // 重置障碍2的位置
            if (ship.getOil() <= 0)
            {
                new gameoverFrame(user, "ship", diff, ship.getGear()); // 载具油量耗尽，游戏结束
                jFrame.over = true;
                jFrame.dispose();
            }
        }
        gear.Collision(ship); // 检测载具与齿轮是否碰撞
    }

    public void up()
    {
        ship.up(); // 载具上移
    }

    public void down()
    {
        ship.down(); // 载具下移
    }

    public Boolean getGameover()
    {
        return gameclear; // 返回游戏结束标志
    }

    public int getGears()
    {
        return ship.getGear(); // 返回载具当前齿轮数
    }
}
