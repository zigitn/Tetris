package ui.ui;

import ui.Img;
import ui.Layer;

import java.awt.*;

public class LayerBackGround extends Layer
{

    public LayerBackGround(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {
        g.drawImage(Img.IMG_BG, 0, 0, null);
    }

}
