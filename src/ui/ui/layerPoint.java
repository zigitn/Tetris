package ui.ui;

import sun.java2d.loops.FillRect;
import ui.layer;

import javax.swing.*;
import java.awt.*;

public class layerPoint extends layer
{

    private static final Image IMG_POINT = new ImageIcon("./media/string/point.png").getImage();
    private static final Image IMG_RMLINE = new ImageIcon("./media/string/rmline.png").getImage();
    private static final Image IMG_SLOT = new ImageIcon("./media/window/rect.png").getImage();

    public layerPoint(int x,
                      int y,
                      int w,
                      int h
                     )
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {
        this.createBlock(g);
        g.drawImage(IMG_POINT, this.x, this.y + PADDING, null);
        this.showNum(this.gameDto.getNowPoint(), g, PADDING);

        g.drawImage(IMG_RMLINE, this.x, this.y + (PADDING << 2), null);
        this.showNum(this.gameDto.getNowRemoveLine(), g, (PADDING << 2));

        g.setColor(Color.BLACK);
        g.fillRect(this.x + PADDING - 1, this.y + this.h - PADDING * 3 - 1, this.w - (PADDING << 1) + 1,
                   (PADDING << 1) + 1);



    }

}
