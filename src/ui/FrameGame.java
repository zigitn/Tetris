package ui;

import config.FrameConfig;
import config.gameConfig;

import javax.swing.*;
import java.awt.*;

public class FrameGame extends JFrame
{
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
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        this.setLocation(screen.width - this.getWidth() >> 1, (screen.height - this.getHeight() >> 1) - 50);
        this.setContentPane(panelGame);

        //显示本Frame

        this.setVisible(true);

    }

}
