package ui.ui;

import ui.Img;
import ui.Layer;

import java.awt.*;

public class LayerNext extends Layer
{
    public LayerNext(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {
        this.createBlock(g);
        if (this.gameDto.isStart())
        {
            this.drawImageAtCenter(Img.NEXT_ACT[this.gameDto.getNext()], g);
        }

    }


}
