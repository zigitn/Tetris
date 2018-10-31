package ui.ui;

import ui.Img;
import ui.Layer;

import java.awt.*;
import java.util.Random;

public class LayerBackGround extends Layer
{
    private static int BG_INDEX;
    static
    {
        Random random=new Random();
        BG_INDEX=random.nextInt(Img.BG_LIST.size());
    }
    public LayerBackGround(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {
        g.drawImage(Img.BG_LIST.get(BG_INDEX), 0, 0, WINDOW_W,WINDOW_H,null);
    }

}
