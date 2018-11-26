package ui;

import control.GameControl;

import javax.swing.*;

public class MenuBar extends JMenuBar
{
    private GameControl gameControl;

    public MenuBar(GameControl gameControl)
    {
        this.gameControl = gameControl;
        this.add(createMenuBar());
    }

    private JMenu createMenuBar()
    {
        JMenu menu = new JMenu("菜单");
        JMenuItem item = new JMenuItem("设置");
        menu.add(item);
        item.addActionListener(actionEvent -> gameControl.showFrameSet());
        return menu;
    }
}
