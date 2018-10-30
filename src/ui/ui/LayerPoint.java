package ui.ui;

import ui.Img;
import ui.Layer;

import java.awt.*;

public class LayerPoint extends Layer
{
    private static final double LEVEL_UP = 20;

    public LayerPoint(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {
        this.createBlock(g);
        g.drawImage(Img.IMG_POINT, this.x, this.y + PADDING, null);
        this.showNum(this.gameDto.getNowPoint(), g, PADDING);

        g.drawImage(Img.IMG_RMLINE, this.x, this.y + (PADDING << 2), null);
        this.showNum(this.gameDto.getNowRemoveLine(), g, (PADDING << 2));

        int rLine = this.gameDto.getNowRemoveLine();
        double numerator = rLine % LEVEL_UP == 0 && rLine != 0 ? LEVEL_UP : rLine % LEVEL_UP;
        double w = (numerator / LEVEL_UP);
        this.showSlot(this.y + this.h - PADDING * 3, "下一级", 0, w, g);

    }

}
