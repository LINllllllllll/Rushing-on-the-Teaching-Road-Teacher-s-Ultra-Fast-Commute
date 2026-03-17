package model;

import javax.swing.*;
import java.awt.*;

public class Ship extends Carrier implements Component
{
    int[] ypos = {220,300,380};
    int pos = 1;
    public Ship(int x,int y,int width,int height,int model)
    {
        super(x,y,width,height);
        image = new ImageIcon("resources/images/ship" + model + ".png").getImage();
       if (model == 1) {
            setCost(0.25);
            setScar(34.0);
        } else if (model == 2) {
            setCost(0.15);
            setScar(25.0);
        } else if (model == 3) {
            setCost(0.1);
            setScar(20.0);
        }
    }

    @Override
    public void printComponent(Graphics g) 
    {
        printPerson(g);
    }

    /**
     * 进入下一帧图
     */
    @Override
    public void step()
    {
        super.setoil(super.getOil() - cost);
    }

    /**
     * 跑动起来
     * @param g 系统图形类
     */
    public void printPerson(Graphics g)
    {
        g.drawImage(image,x,y, width, height,null);
    }

    public void up()
    {
        if(pos > 0)
        {
            pos--;
            sety(ypos[pos]);
        }
    }

    public void down()
    {
        if(pos < 2)
        {
            pos++;
            sety(ypos[pos]);
        }
    }

    @Override
    public int getWidth() 
    {
        return super.getWidth();
    }

    @Override
    public int getHeight() 
    {
        return super.getHeight();
    }

    @Override
    public int getx() 
    {
        return super.getx();
    }

    @Override
    public int gety() 
    {
        return super.gety();
    }

    public double getOil()
    {
        return super.getOil();
    }

    public void setOil(double oil)
    {
        super.setoil(oil);
    }
}