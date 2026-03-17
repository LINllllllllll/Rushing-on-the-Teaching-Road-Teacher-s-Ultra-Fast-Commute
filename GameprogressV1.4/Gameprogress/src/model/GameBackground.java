package model;

import javax.swing.*;
import java.awt.*;

public class GameBackground implements Component 
{
    //背景图
    Image bgimage;
    
    int width = 900,height = 530;

    int index = 0;
    
    int speed = 5;


    public GameBackground(int diff,String path)
    {
        bgimage = new ImageIcon(path).getImage();
        speed = speed * diff;
    }

    public void printMap(Graphics g)
    {
        g.drawImage(bgimage,width + index - 1,0,width,height,null);
        g.drawImage(bgimage,index,0,width,height,null);
    }

    @Override
    public void printComponent(Graphics g) 
    {
        printMap(g);
    }

    @Override
    public void step()
    {
        index-= speed;
        if (index <= -width)
        {
            index = 0;
        }
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
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

    public int getSpeed()
    {
        return speed;
    }
}