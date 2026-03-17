package model;

import java.awt.*;

public interface Component {
    void printComponent(Graphics g);

    void step();

    int getx();

    int gety();

    int getWidth();

    int getHeight();
}