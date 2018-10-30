package ui.ui;

import ui.Img;

import java.awt.*;

public class LayerDisk extends LayerData
{
    public LayerDisk(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {
        this.createBlock(g);
        g.drawImage(Img.IMG_DISK, this.x, this.y + PADDING, null);

        showData(this.gameDto.getDiskRecode(), g);

    }

}
