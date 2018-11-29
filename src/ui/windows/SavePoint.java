package ui.windows;

import Util.FrameUtil;
import control.GameControl;
import dto.GameDto;

import javax.swing.*;
import java.awt.*;

public class SavePoint extends JFrame
{
    private GameControl gameControl;
    private GameDto gameDto;
    private JButton OJBK = null;
    private JTextField nameInput = null;
    private JLabel score = null;
    private JLabel name = null;

    public SavePoint(GameControl gameControl, GameDto gameDto)
    {
        this.gameControl = gameControl;
        this.gameDto = gameDto;
        this.setTitle("游戏结束");
        //固定窗口位置及大小
        this.setSize(300, 150);
        this.setResizable(false);
        FrameUtil.setFrameToCenter(this);
        //设置为边界布局
        this.setLayout(new BorderLayout());

        //初始化组件
        this.initCom(gameDto);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initCom(GameDto gameDto)
    {
        //北部
        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.score = new JLabel("得分:"+gameDto.getNowPoint());
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
        this.OJBK = new JButton("确定");
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        south.add(OJBK);
        OJBK.addActionListener(actionEvent ->
                               {
                                   if (nameInput.getText()!=null&&nameInput.getText().length()<15)
                                   {
                                       gameControl.savePoint(nameInput.getText());
                                       this.dispose();
                                   }
                                   else
                                   {
                                       JOptionPane.showMessageDialog(null, "请输入正确的ID");
                                   }
                               });
        this.add(south, BorderLayout.SOUTH);
    }

}
