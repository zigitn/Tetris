package ui.windows;

import Util.FrameUtil;

import javax.swing.*;
import java.awt.*;

public class SavePoint extends JFrame
{
    private JButton OJBK=null;
    private JTextField nameInput=null;
    private JLabel score=null;
    private JLabel name=null;

    public SavePoint()
    {
        this.setTitle("游戏结束");
        //固定窗口位置及大小
        this.setSize(300, 150);
        this.setResizable(false);
        FrameUtil.setFrameToCenter(this);
        //设置为边界布局
        this.setLayout(new BorderLayout());
        
        //初始化组件
        this.initCom();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initCom()
    {
        //北部
        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.score = new JLabel("得分:9999");
        north.add(score);
        this.add(north, BorderLayout.NORTH);

        //中部
        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.name = new JLabel("姓名:");
        this.nameInput = new JTextField(10);
        center.add(name);
        center.add(nameInput);
        this.add(center, BorderLayout.CENTER);


        //南部
        this.OJBK=new JButton("确定");
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        south.add(OJBK);
        this.add(south,BorderLayout.SOUTH);
    }

    public static void main(String[] args)
    {
        new SavePoint();
    }
}
