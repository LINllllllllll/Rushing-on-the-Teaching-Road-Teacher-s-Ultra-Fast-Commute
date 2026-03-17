package model;

import java.util.Random;
import java.awt.*;

/**
 * 飞机障碍物类，实现了Component接口
 */
public class PlaneObstacle implements Component {
    Random ran = new Random();
    int[] x = {300, 900}; // 保存飞机障碍物的横坐标位置
    int endIndex = 1; // 上一个飞机障碍物的索引
    int height1, height2; // 障碍物1和2的高度
    int bet1, bet2; // 障碍物1和2之间的间距
    Skyblock skyblock1; // 障碍物1（天空块）
    Skyblock skyblock2; // 障碍物2（天空块）
    Roadblock roadblock1; // 障碍物1（道路块）
    Roadblock roadblock2; // 障碍物2（道路块）
    int interval = 600; // 障碍物之间的距离

    /**
     * 构造函数，初始化飞机障碍物
     * @param diff 难度级别
     */
    public PlaneObstacle(int diff) {
        skyblock1 = new Skyblock(x[0], diff);
        height1 = skyblock1.getHeight();
        skyblock2 = new Skyblock(x[1], diff);
        height2 = skyblock2.getHeight();
        bet1 = ran.nextInt(90) + 180;
        bet2 = ran.nextInt(90) + 180;
        roadblock1 = new Roadblock(x[0], height1 + bet1, diff, 530 - height1 - bet1 + 100);
        roadblock2 = new Roadblock(x[1], height2 + bet2, diff, 530 - height2 - bet2 + 100);
    }

    /**
     * 绘制飞机障碍物
     * @param g 绘图上下文
     */
    public void printPlaneObstacle(Graphics g) {
        printComponent(g);
    }

    /**
     * 绘制组件
     * @param g 绘图上下文
     */
    @Override
    public void printComponent(Graphics g) {
        skyblock1.printComponent(g);
        skyblock2.printComponent(g);
        roadblock1.printComponent(g);
        roadblock2.printComponent(g);
    }

    /**
     * 下一帧
     * 图像移动到下一个位置，检查是否出界，出界图像重新生成位置
     */
    @Override
    public void step() {
        x[0] -= skyblock1.getSpeed();
        x[1] -= skyblock2.getSpeed();

        skyblock1.setX(x[0]);
        roadblock1.setX(x[0]);
        skyblock2.setX(x[1]);
        roadblock2.setX(x[1]);

        if (skyblock1.getx() < -180) {
            rest(0);
        }

        if (skyblock2.getx() < -180) {
            rest(1);
        }
    }

    /**
     * 获取组件宽度
     * @return 组件宽度
     */
    @Override
    public int getWidth() {
        return 0;
    }

    /**
     * 获取组件高度
     * @return 组件高度
     */
    @Override
    public int getHeight() {
        return 0;
    }

    /**
     * 获取组件横坐标
     * @return 组件横坐标
     */
    @Override
    public int getx() {
        return 0;
    }

    /**
     * 获取组件纵坐标
     * @return 组件纵坐标
     */
    @Override
    public int gety() {
        return 0;
    }

    /**
     * 重新生成障碍物位置
     * @param i 障碍物索引
     */
    private void rest(int i) {
        x[i] = x[endIndex] + interval;
        endIndex = i;
        if (i == 0) {
            skyblock1.setX(x[i]);
            roadblock1.setX(x[i]);
            skyblock1.setHeight(ran.nextInt(100) + 100);
            height1 = skyblock1.getHeight();
            bet1 = ran.nextInt(90) + 180;
            roadblock1.setY(height1 + bet1);
            roadblock1.setHeight(530 - height1 - bet1 + 100);
        }
        if (i == 1) {
            skyblock2.setX(x[i]);
            roadblock2.setX(x[i]);
            skyblock2.setHeight(ran.nextInt(100) + 100);
            height2 = skyblock2.getHeight();
            bet2 = ran.nextInt(90) + 180;
            roadblock2.setY(height2 + bet2);
            roadblock2.setHeight(530 - height2 - bet2 + 100);
        }
    }

    /**
     * 检测与飞机障碍物的碰撞
     * @param carrier 飞机对象
     */
    public void Collision(Carrier carrier) {
        if (skyblock1.isCollision(carrier) || roadblock1.isCollision(carrier)) {
            carrier.setoil(carrier.getOil() - carrier.getScar());
            rest(0);
        }
        if (skyblock2.isCollision(carrier) || roadblock2.isCollision(carrier)) {
            carrier.setoil(carrier.getOil() - carrier.getScar());
            rest(1);
        }
    }
}
