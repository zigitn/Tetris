package ui.ui;

import config.GameConfig;
import ui.Img;
import ui.Layer;

import java.awt.*;

public class LayerGame extends Layer
{
    private static final int ACT_SIZE_ROL = GameConfig.getFrameConfig().getSizeRol();


    public LayerGame(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void paint(Graphics g)
    {
        this.createBlock(g);
        if (this.gameDto.isStart())
        {
            Point[] points = this.gameDto.getGameAct().getActPoints();
            this.showShadow(points,g,true);
            this.drawMainAct(points,g);
        }
        this.drawGameMap(g);

        //pause
        if (gameDto.isPause())
        {
            this.drawImageAtCenter(Img.IMG_PAUSE,g);
        }
    }

    /*游戏主区域*/
    private void drawGameMap(Graphics g)
    {
        boolean[][] map = this.gameDto.getGameMainMap();
        for (int x = 0; x < map.length; x++)
        {
            for (int y = 0; y < map[x].length; y++)
            {
                if (map[x][y])
                {
                    drawActById(x, y, this.gameDto.isStart()?0:8, g);

                }
            }
        }
    }

    /*游戏方块*/
    private void drawMainAct(Point[] points,Graphics g)
    {

        int typeCode = (this.gameDto.getGameAct().getTypeCode()) + 1;

        for (Point point : points)
        {
            drawActById(point.x, point.y, typeCode, g);
        }
    }

    private void drawActById(int x, int y, int imgIdx, Graphics g)
    {
        g.drawImage(Img.ACT,
                    this.x + (x << ACT_SIZE_ROL) + BORDER,
                    this.y + (y << ACT_SIZE_ROL) + BORDER,
                    this.x + (x + 1 << ACT_SIZE_ROL) + BORDER,
                    this.y + (y + 1 << ACT_SIZE_ROL) + BORDER,
                    imgIdx<< ACT_SIZE_ROL,
                    0,
                    (imgIdx + 1) << ACT_SIZE_ROL,
                    1 << ACT_SIZE_ROL,
                    null);
    }

    private void showShadow(Point[] points, Graphics g,boolean show)
    {
        if (!show)
        {
            return;
        }
        int leftX = 9;
        int rightX = 0;
        for (Point p : points)
        {
            leftX = p.x < leftX ? p.x : leftX;
            rightX = p.x > rightX ? p.x : rightX;
        }
        g.drawImage(Img.IMG_SHADOW,
                    this.x + BORDER + (leftX << ACT_SIZE_ROL),
                    this.y + BORDER,
                    (rightX - leftX + 1) << ACT_SIZE_ROL,
                    this.h - (BORDER << 1),
                    null);
    }

}
