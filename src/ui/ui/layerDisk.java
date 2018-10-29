package ui.ui;

import ui.layer;

import javax.swing.*;
import java.awt.*;

public class layerDisk extends layer
{
    private static Image IMG_DISK = new ImageIcon("media/string/disk.png").getImage();

    public layerDisk(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {
        this.createBlock(g);
        g.drawImage(IMG_DISK, this.x + PADDING, this.y + PADDING, null);

    }

}
