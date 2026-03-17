package info;

/**
 * Score 分数类
 */
public class Score 
{
    /** 总分 */
    private int score;
    /* 金币数 */
    private int gear;
    /* 距离 */
    private int distance;

    private int mark = 0;

    /**
     * 获取总分
     * @return 总分
     */
    public int getScore() 
    {
        score = distance + gear * 100;
        return score;
    }

    /**
     * 设置总分
     * @param score 总分
     */
    public void setScore(int score) 
    {
        this.score = score;
    }

    /**
     * 获取金币数
     * @return 金币数
     */
    public int getGear() 
    {
        return gear;
    }

    /**
     * 设置金币数
     * @param gear 金币数
     */
    public void setGear(int gear) 
    {
        this.gear = gear;
    }

    /**
     * 获取距离
     * @return 距离
     */
    public int getDistance() 
    {
        return distance;
    }

    /**
     * 设置距离
     * @param distance 距离
     */
    public void setDistance(int distance) 
    {
        this.distance = distance;
    }

    /**
     * 获取下一个属性值
     * @return 下一个属性值（轮流返回总分、距离和金币数）
     */
    public int next()
    {
        if (mark == 0)
        {
            mark++;
            return getScore();
        }
        if (mark == 1)
        {
            mark++;
            return getDistance();
        }
        if (mark == 2)
        {
            mark = 0;
            return getGear();
        }
        return 0;
    }
}
