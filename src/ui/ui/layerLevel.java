package ui.ui;

import ui.layer;

import javax.swing.*;
import java.awt.*;

public class layerLevel extends layer
{
    private static final Image IMG_LEVEL = new ImageIcon("./media/string/level.png").getImage();


    public layerLevel(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {
        this.createBlock(g);
        g.drawImage(IMG_LEVEL, this.x + PADDING, this.y + PADDING, null);
        this.shownum(this.gameDto.getNowlevel(), g,50);
    }



}
