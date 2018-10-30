package ui.ui;

import ui.Img;
import ui.Layer;

import java.awt.*;

public class LayerDataBase extends Layer
{
    public LayerDataBase(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {
        this.createBlock(g);
        g.drawImage(Img.IMG_DB, this.x, this.y + PADDING, null);

        int nowPoint = this.gameDto.getNowPoint();
        for (int i = 0; i < this.gameDto.getDbRecode().size(); i++)
        {
            int max = this.gameDto.getDbRecode().get(i).getPoint();
            showSlot(this.y + (PADDING << 2) + PADDING_SLOT * 3 * i,
                     this.gameDto.getDbRecode().get(i).getUsername(),
                     max,
                     nowPoint < max && nowPoint == 0 ? (double) nowPoint / max : 1,
                     g);
        }
    }

}
