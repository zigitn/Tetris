package ui.ui;

import ui.Layer;

import javax.swing.*;
import java.awt.*;

public class LayerDisk extends Layer
{
    private static Image IMG_DISK = new ImageIcon("media/string/disk.png").getImage();

    public LayerDisk(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {
        this.createBlock(g);
        g.drawImage(IMG_DISK, this.x, this.y + PADDING, null);

        int nowPoint=this.gameDto.getNowPoint();
        for (int i = 0; i < this.gameDto.getLocalRecode().size(); i++)
        {
            int max=this.gameDto.getLocalRecode().get(i).getPoint();
            showSlot(this.y + (PADDING << 2) + PADDING_SLOT * 3 * i,
                     this.gameDto.getLocalRecode().get(i).getUsername(),
                     max,
                     nowPoint<max?(double) nowPoint/max:1,
                     g);
        }
    }

}
