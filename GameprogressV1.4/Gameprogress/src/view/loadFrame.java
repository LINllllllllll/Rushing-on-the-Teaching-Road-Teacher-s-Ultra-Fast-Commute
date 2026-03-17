package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class loadFrame extends JFrame
{
    int type;
    int diff;
    int model;
    User user;
    int width = 568;
    int height = 320;
    JProgressBar progressBar;

    public loadFrame(int type,int diff,User user,int model)
    {
        this.type = type;
        this.diff = diff;
        this.user = user;
        this.model = model;
        if (!initBg()) 
        {
            JOptionPane.showMessageDialog(null,"资源错误！");
        }
        initProgressBar();
        startProgress();
        this.setSize(width,height);
        //设定无装饰栏
        this.setUndecorated(true);
        //居中显示
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * 初始化背景图
     * @return 返回执行情况
     */
    private Boolean initBg(){
        JPanel bgPanel;
        try 
        {
            bgPanel = new BackgroundPanel("resources/images/loadbg.png",width,height);
        } 
        catch (IOException e) 
        {
            System.out.println("资源错误");
            return false;
        }
        bgPanel.setBounds(0,0,width,height);
        this.add(bgPanel);
        return true;
    }

    /**
     * 初始化进度条
     */
    private void initProgressBar(){
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        this.add(BorderLayout.SOUTH,progressBar);
    }

    /**
     * 创建单独线程用于控制进度条进度
     */
    private void startProgress(){
        Thread progress = new Progress(progressBar);
        progress.start();
    }

    /**
     * 用于展示进度条的多线程类
     */
    private class Progress extends Thread{
        private final JProgressBar progressBar;

        /**
         * 进度条多线程进行展示
         * @param progressBar 外部进度条组件
         */
        public Progress(JProgressBar progressBar){
            this.progressBar = progressBar;
        }
        @Override
        public void run() {
            //进度条终点
            int hundred = 100;
            for (int i = 0; i <= hundred; i++) {
                progressBar.setValue(i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            loadFrame.this.dispose();
            new gameFrame(type, diff, user, model);

        }
    }
}