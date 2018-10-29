package ui.ui;

import ui.layer;

import javax.swing.*;
import java.awt.*;

public class layerPoint extends layer
{

    private static final Image IMG_POINT = new ImageIcon("./media/string/point.png").getImage();
    private static final Image IMG_RMLINE = new ImageIcon("./media/string/rmline.png").getImage();

    public layerPoint(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {
        this.createBlock(g);
        g.drawImage(IMG_POINT, this.x + PADDING, this.y + PADDING, null);
        this.shownum(100,g,PADDING);

        g.drawImage(IMG_RMLINE, this.x + PADDING, this.y + (PADDING<<2), null);

    }

}
