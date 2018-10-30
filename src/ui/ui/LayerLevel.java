package ui.ui;

import ui.Img;
import ui.Layer;

import java.awt.*;

public class LayerLevel extends Layer
{


    public LayerLevel(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {
        this.createBlock(g);
        g.drawImage(Img.IMG_LEVEL, this.x , this.y + PADDING, null);
        this.showNum(this.gameDto.getNowLevel(), g,50);
    }



}
