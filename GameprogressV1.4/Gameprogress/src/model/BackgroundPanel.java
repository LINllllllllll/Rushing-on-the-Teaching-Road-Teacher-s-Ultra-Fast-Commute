package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BackgroundPanel extends JPanel {
    private Image imageFile;   // 图像文件
    private int x = 0;   // 图像绘制的起始横坐标
    private int y = 0;   // 图像绘制的起始纵坐标
    private int height;   // 图像高度
    private int width;   // 图像宽度

    public BackgroundPanel() {
        super();
    }

    public BackgroundPanel(String path) throws IOException {
        this();   // 调用无参构造方法
        height = 530;   // 默认高度
        width = 900;   // 默认宽度
        this.imageFile = ImageIO.read(new File(path));   // 读取图像文件
    }

    public BackgroundPanel(int x, int y, int width, int height, String path) throws IOException {
        this(path);   // 调用带参构造方法
        this.x = x;   // 设置图像绘制的起始横坐标
        this.y = y;   // 设置图像绘制的起始纵坐标
        this.height = height;   // 设置图像高度
        this.width = width;   // 设置图像宽度
    }

    public BackgroundPanel(String path, int width, int height) throws IOException {
        this.height = height;   // 设置图像高度
        this.width = width;   // 设置图像宽度
        this.imageFile = ImageIO.read(new File(path));   // 读取图像文件
    }
    
    // 重写 JPanel 的 paintComponent 方法，用于绘制图像
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.imageFile, x, y, width, height, null);   // 在指定位置绘制图像
    }
}
