package controller;

import model.*;
import model.Component;
import view.*;


import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

import info.Score;

public class CarGameController extends JPanel
{
    User user; // 用户对象
    List<Component> components = new LinkedList<>(); // 组件列表
    Car car; // 载具对象
    GameBackground map; // 背景对象
    Obstacle obstacle; // 障碍物对象
    GearList gear; // 齿轮列表对象
    Fraction fraction; // 积分板对象
    gameFrame jFrame; // 主窗口JFrame对象

    Boolean gameClear = false; // 游戏结束标志

    Gas gas; // 加油罐对象

    int diff; // 难度值
    Score score = new Score(); // 分数对象
    
    public CarGameController(int diff, gameFrame jFrame, User user, int model)
    {
        super();
        this.user = user;
        this.diff = diff;
        this.jFrame = jFrame;
        initComponents(model); // 初始化组件
        gear.addScore(score); // 添加分数到齿轮列表
        fraction.addScore(score); // 添加分数到积分板
    }
    
    /**
     * 初始化Components
     */
    private void initComponents(int model)
    {
        this.map = new GameBackground(diff, "resources/images/cargamebg.png"); // 创建背景对象
        components.add(map); // 将背景对象添加到组件列表
        this.obstacle = new Obstacle(1); // 创建障碍物对象
        components.add(obstacle); // 将障碍物对象添加到组件列表
        this.gas = new Gas(map.getSpeed(), 0); // 创建加油罐对象
        components.add(gas); // 将加油罐对象添加到组件列表
        this.gear = new GearList(map, 0); // 创建齿轮列表对象
        components.add(gear); // 将齿轮列表对象添加到组件列表
        this.car = new Car(0, 270, 120, 120, model); // 创建载具对象
        this.car.setCost(this.car.getCost() * diff); // 设置载具的成本
        components.add(car); // 将载具对象添加到组件列表
        this.fraction = new Fraction(car); // 创建积分板对象
        components.add(fraction); // 将积分板对象添加到组件列表
    }

    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);
        for (Component component : components) 
        {
            component.printComponent(g); // 绘制组件
        }
    }

    /**
     * 下一帧
     */
    public void nextFrame()
    {
        if(fraction.getgrads() >= 1000) // 判断是否达到游戏通关条件
        {
            gameClear = true;
        }
        for(Component component: components)
        {
            component.step(); // 组件执行下一步操作
        }
        //碰撞检测
        Collision(); // 执行碰撞检测
        if(car.getOil() < 0) // 判断汽车油量是否小于0
        {
            new gameoverFrame(user, "car", diff, car.getGear()); // 创建游戏结束界面对象
            jFrame.over = true; // 设置游戏结束标志为true
            jFrame.dispose(); // 关闭主窗口
        }
    }

    private void Collision() 
    {

        if(gas.isCollision(car)) // 判断是否发生与加油罐的碰撞
        {
            car.setoil(car.getOil() + 50); // 增加汽车油量
            gas.setColed(1); // 设置加油罐被收集的标志为1
            gas.resetPosition(); // 重置加油罐位置
        }
        //检测攻击 碰撞
        if(obstacle.isAttack(car)) // 判断是否发生与障碍物的攻击碰撞
        {
            return;
        }

        if (obstacle.isCollision(car)) // 判断是否发生与障碍物的碰撞
        {
            car.setOil(car.getOil() - car.getScar()); // 减少汽车油量
            obstacle.setColed(1); // 设置障碍物被收集的标志为1
            obstacle.resetPosition(); // 重置障碍物位置
            //gameover
            if(car.getOil() <= 0) // 判断汽车油量是否小于等于0
            {
                new gameoverFrame(user, "car", diff, car.getGear()); // 创建游戏结束界面对象
                jFrame.over = true; // 设置游戏结束标志为true
                jFrame.dispose(); // 关闭主窗口
            }
        }
        //检测碰撞齿轮
        gear.Collision(car); // 判断是否发生与齿轮的碰撞
    }

    /**
     * 汽车跳跃
     */
    public void jump()
    {
        car.setJumping(0); // 设置汽车跳跃状态为0
    }

    public Boolean getGameover()
    {
        return gameClear; // 返回游戏结束标志
    }

    public int getGears()
    {
        return car.getGear(); // 返回载具所在的齿轮数
    }
}
