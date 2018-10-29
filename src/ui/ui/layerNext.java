package ui.ui;

import ui.layer;

import javax.swing.*;
import java.awt.*;

public class layerNext extends layer
{
    public layerNext(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    private static Image[] NEXT_ACT;

    static
    {
        NEXT_ACT = new Image[7];
        for (int i = 0; i < NEXT_ACT.length; i++)
        {
            NEXT_ACT[i] = new ImageIcon("./media/game/" + i + ".png").getImage();
        }
    }

    public void paint(Graphics g)
    {
        this.createBlock(g);
        this.drawImageAtCenter(NEXT_ACT[this.gameDto.getNext()],g);
    }

    public void drawImageAtCenter(Image img, Graphics g)
    {
        int imgW = img.getWidth(null);
        int imgH = img.getHeight(null);
        g.drawImage(img,this.x+(this.w-imgW>>1),this.y+(this.h-imgH>>1),null);
    }
}
