package model;

import info.*;
import javax.swing.*;
import java.awt.*;

public class Fraction implements Component
{
    int x = 10,y = 10;
    int grads = 0;
    Score score;
    Carrier carrier;
    
    public Fraction(Carrier carrier)
    {
        this.carrier = carrier;
    }

    @Override
    public void printComponent(Graphics g) 
    {
        //图片读取
        Image image = new ImageIcon().getImage();
        g.drawImage(image,x,y,120,30,null);
        //添加分数显示
        String grads = "齿轮数为"+ carrier.getGear();
        String oil = "剩余油量为 " + (int)carrier.getOil();
        String path = "路程为" + score.getDistance();

        Font font = new Font("微软雅黑",Font.PLAIN, 20);
        g.setFont(font);
        g.drawString(grads,x + 10,y + 20);
        g.drawString(oil,x + 10,y + 40);
        g.drawString(path, x + 10, y + 60);
    }

    @Override
    public void step() 
    {
        grads++;
        score.setDistance(grads);
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
        return x;
    }

    @Override
    public int gety() 
    {
        return y;
    }

    public void addScore(Score score)
    {
        this.score = score;
    }

    public int getgrads()
    {
        return grads;
    }
}