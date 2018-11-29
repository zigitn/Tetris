package ui.windows;

import control.GameControl;

import javax.swing.*;

public class MenuBar extends JMenuBar
{
    private GameControl gameControl;
    private JMenuItem item1 = new JMenuItem("开始游戏");

    public MenuBar(GameControl gameControl)
    {
        this.gameControl = gameControl;

        //菜单按钮
        JMenu menu = new JMenu("菜单");
        JMenuItem item2 = new JMenuItem("设置");
        menu.add(item1);
        menu.add(item2);
        item1.addActionListener(actionEvent ->
                                {
                                    gameControl.startGame();
                                    switchStartButton(false);
                                });
        item2.addActionListener(actionEvent -> gameControl.showFrameSet());
        this.add(menu);

    }

    public void switchStartButton(boolean b)
    {
        item1.setEnabled(b);
    }
}

