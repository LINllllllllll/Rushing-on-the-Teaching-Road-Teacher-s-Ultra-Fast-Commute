package model;

import info.Score;
import java.util.Random;
import java.awt.*;

public class GearList implements Component
{
    GameBackground gb;
    /**
     * 齿轮数
     */
    int gearNumber = 5;
    /**
     * 齿轮间间隔
     */
    int interval = 150;
    /**
     * 齿轮数组
     */
    Gear[] gears = new Gear[gearNumber];
    /**
     * x轴坐标表
     */
    int[] xs = new int[gearNumber];
    /**
     * y轴坐标表
     */
    int[] ys = new int[gearNumber];
    /**
     * 新生成x轴位置
     */
    int newX = 800;
    /**
     * 最后一个齿轮数组中坐标
     */
    int endIndex = gearNumber - 1;
    /**
     * 齿轮生成时x和y坐标范围
     */
    int maxY = 270, minY = 50;
    int[] ypos = {280,360,440};
    int gametype;
    Random ran = new Random();

    public GearList(GameBackground gb,int gametype) 
    {
        this.gametype = gametype;
        this.gb = gb;
        int temp = newX;
        for (int i = 0; i < gearNumber; i++) 
        {
            if(gametype == 0)
            {
                xs[i] = temp;
                temp += interval;
                ys[i] = randomY();
            }
            else if(gametype == 2)
            {
                xs[i] = temp;
                temp += interval;
                ys[i] =  ypos[ran.nextInt(3)];
            }
        }
        for (int i = 0; i < gearNumber; i++) 
        {
            gears[i] = new Gear(xs[i], ys[i]);
        }
    }

    public void printGoldList(Graphics g) 
    {
        printComponent(g);
    }

    @Override
    public void printComponent(Graphics g) 
    {
        for (int i = 0; i < gearNumber; i++) 
        {
            gears[i].printGear(g);
        }
    }

    /**
     * 下一帧
     * 图像移动到下一个位置，检查是否出界，出界图像重新生成位置
     */
    @Override
    public void step() 
    {
        for (int i = 0; i < gearNumber; i++) 
        {
            //和背景一起移动，速度一致
            xs[i] -= gb.getSpeed();
            gears[i].setX(xs[i]);
            //已出界，重置x和y位置
            if (xs[i] < -50) 
            {
                rest(i);
            }
        }
    }

    @Override
    public int getWidth() 
    {
        return 0;
    }

    @Override
    public int getHeight() 
    {
        return 0;
    }

    @Override
    public int getx() 
    {
        return 0;
    }

    @Override
    public int gety()
    {
        return 0;
    }

    private void rest(int i) 
    {
        xs[i] = xs[endIndex] + interval;
        endIndex = i;
        gears[i].setX(xs[i]);
        if(gametype == 0)
        {
            ys[i] = randomY();
        }
        else if(gametype == 2)
        {
            ys[i] = ypos[ran.nextInt(3)];
        }
        gears[i].setY(ys[i]);
    }

    /**
     * 随机y坐标，范围由maxy和minY控制
     *
     * @return 随机y坐标
     */
    private int randomY() 
    {
        return (int) (Math.random() * (maxY - minY) + minY);
    }

    /**
     * 碰撞检测
     *
     */
    public void Collision(Carrier carrier) 
    {
        for (int i = 0; i < gearNumber; i++) 
        {
            if(gametype == 0)
            {
                if (gears[i].isCollision(carrier)) 
                {
                    carrier.setGear(carrier.getGear() + gears[i].num);
                    rest(i);
                }
            }
            else if(gametype == 2)
            {
                if(gears[i].gety() == carrier.gety() + 60 && carrier.getx() + carrier.getWidth() > gears[i].getx() &&
                carrier.getx() < gears[i].x + gears[i].getWidth())
                {
                    carrier.setGear(carrier.getGear() + gears[i].num);
                    rest(i);
                }
            }
        }
    }

    /**
     * 存储分数
     * @param score 分数存储对象
     */
    public void addScore(Score score) 
    {
        for (int i = 0; i < gearNumber; i++) 
        {
            gears[i].addScore(score);
        }
    }
}
