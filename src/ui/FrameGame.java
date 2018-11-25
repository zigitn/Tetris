package ui;

import Util.FrameUtil;
import config.FrameConfig;
import config.gameConfig;

import javax.swing.*;
import java.awt.*;

public class FrameGame extends JFrame
{
    private JMenuBar menuBar=new MenuBar();
    public FrameGame(PanelGame panelGame)
    {
        //默认关闭属性
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //配置读取器
        FrameConfig fCfg = gameConfig.getFrameConfig();

        //设置标题
        this.setTitle(fCfg.getTitle());

        //固定窗口大小
        this.setSize(fCfg.getWidth(), fCfg.getHeight());
        this.setResizable(false);

        //令窗口居中
        FrameUtil.setFrameToCenter(this);

        //设置默认panel
        this.setJMenuBar(menuBar);
        this.setContentPane(panelGame);

        //显示本Frame

        this.setVisible(true);

    }

}
