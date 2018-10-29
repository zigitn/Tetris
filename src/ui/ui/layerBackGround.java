package ui.ui;

import ui.layer;

import javax.swing.*;
import java.awt.*;

public class layerBackGround extends layer
{
    private static Image IMG_BG = new ImageIcon("media/background/bg02.jpg").getImage();

    public layerBackGround(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {
        g.drawImage(IMG_BG, 0, 0, null);
    }

}
