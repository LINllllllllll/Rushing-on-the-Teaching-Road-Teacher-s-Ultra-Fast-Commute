package model;

import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class Skyblock implements Collision,Component
{
    Image image;
    int x,y = 0;
    int width = 80;
    int height;
    int SPEED;
    int fundSpd = 10;
    Random ran = new Random();
    int coled = 0;
    
    public Skyblock(int x,int diff)
    {
        this.x = x;
        height = ran.nextInt(100) + 100;
        image = new ImageIcon("resources/images/skyblock.png").getImage();
        SPEED = diff * fundSpd;
    }

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

    public void printObstacle(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    @Override
    public void printComponent(Graphics g) {
        printObstacle(g);
    }

    @Override
    public void step() 
    {
        x -= SPEED;
        if(x < -180)
        {
            resetPosition();
        }
    }

    /**
     * 到窗口外后生成到新位置
     */
    public void resetPosition() 
    {
          
    }

    @Override
    public Boolean isCollision(Carrier carrier) 
    {
        return  coled == 0 && carrier.gety() < this.gety() + this.height &&
                carrier.gety() + carrier.height > this.gety() &&
                carrier.getx() + 80 > this.getx() &&
                carrier.getx() < this.getx() + 80;
    }

    public void setSpeed(int speed)
    {
        this.SPEED = speed;
    }

    public int getColed()
    {
        return coled;
    }
    
    public void setColed(int coled)
    {
        this.coled = coled;
    }
    
    public int getSpeed()
    {
        return SPEED;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
}