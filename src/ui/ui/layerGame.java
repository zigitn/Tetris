package ui.ui;

import ui.layer;

import javax.swing.*;
import java.awt.*;

public class layerGame extends layer
{
    private static final Image ACT = new ImageIcon("./media/game/rect.png").getImage();
    private static final int ACT_SIZE_ROL = 5;

    public layerGame(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {

        this.createBlock(g);

        Point[] points = this.gameDto.getGameAct().getActPoints();

        int typeCode = (this.gameDto.getGameAct().getTypeCode())+1;

        for (int i = 0; i < points.length; i++)
        {
            drwaActById(points[i].x,points[i].y,typeCode,g);
        }

        boolean[][] map = this.gameDto.getGameMainMap();
        for (int x = 0; x < map.length; x++)
        {
            for (int y = 0; y < map[x].length; y++)
            {
                if (map[x][y])
                {
                    drwaActById(x,y,0,g);

                }
            }
        }

    }
    private void drwaActById(int x,int y,int imgIdx,Graphics g)
    {
        g.drawImage(ACT,
                    this.x + (x <<ACT_SIZE_ROL) + 7,
                    this.y +(y <<ACT_SIZE_ROL) + 7,
                    this.x + (x+1 <<ACT_SIZE_ROL)  + 7,
                    this.y + (y+1 <<ACT_SIZE_ROL)  + 7,
                    imgIdx<<ACT_SIZE_ROL,
                    0,
                    (imgIdx+1)<<ACT_SIZE_ROL,
                    1<<ACT_SIZE_ROL,
                    null);
    }

}
