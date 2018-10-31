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
        this.drawImageAtCenter(Img.NEXT_ACT[this.gameDto.getNext()], g);
    }

    public void drawImageAtCenter(Image img, Graphics g)
    {
        int imgW = img.getWidth(null);
        int imgH = img.getHeight(null);
        g.drawImage(img,this.x+(this.w-imgW>>1),this.y+(this.h-imgH>>1),null);
    }
}
