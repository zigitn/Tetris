package ui;

import ui.cfg.FrameSet;
import javax.swing.*;

public class MenuBar extends JMenuBar
{
    private FrameSet frameSet = new FrameSet();

    public MenuBar()
    {
        this.add(createMenuBar());
    }

    private JMenu createMenuBar()
    {
        JMenu menu = new JMenu("菜单");
        JMenuItem item = new JMenu("设置");
        menu.add(item);
        item.addActionListener(actionEvent -> frameSet.setVisible(true));
        return menu;
    }
}
