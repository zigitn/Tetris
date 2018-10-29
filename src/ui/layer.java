package ui;

import config.gameConfig;
import config.xmlReader;
import dto.GameDto;

import javax.swing.*;
import java.awt.*;

public abstract class layer
{
    private static final Image IMG_NUMBER = new ImageIcon("./media/string/num.png").getImage();
    private static final int IMG_NUM_W = IMG_NUMBER.getWidth(null) / 10;
    private static final int IMG_NUM_H = IMG_NUMBER.getHeight(null);
    private static int RIM;
    protected static final int PADDING;

    static
    {
        gameConfig cfg = xmlReader.getGameConfig();
        RIM = cfg.getRIM();
        PADDING = cfg.getPadding();
    }

    private static Image IMG = new ImageIcon("./media/window/Window.png").getImage();
    private static int IMG_W = IMG.getWidth(null);
    private static int IMG_H = IMG.getHeight(null);
    protected final int x, y, w, h;
    protected GameDto gameDto = null;

    public layer(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    protected void createBlock(Graphics g)
    {
        g.drawImage(IMG, x, y, x + RIM, y + RIM, 0, 0, RIM, RIM, null);
        g.drawImage(IMG, x + RIM, y, w + x - RIM, y + RIM, RIM, 0, IMG_W - RIM, RIM, null);
        g.drawImage(IMG, w + x - RIM, y, w + x, y + RIM, IMG_W - RIM, 0, IMG_W, RIM, null);

        g.drawImage(IMG, x, y + RIM, x + RIM, y + h - RIM, 0, RIM, RIM, IMG_H - RIM, null);
        g.drawImage(IMG, x + RIM, y + RIM, x + w - RIM, y + h - RIM, RIM, RIM, IMG_W - RIM, IMG_H - RIM, null);
        g.drawImage(IMG, x + w - RIM, y + RIM, w + x, y + h - RIM, IMG_W - RIM, RIM, IMG_W, IMG_H - RIM, null);

        g.drawImage(IMG, x, y + h - RIM, x + RIM, y + h, 0, IMG_H - RIM, RIM, IMG_H, null);
        g.drawImage(IMG, x + RIM, y + h - RIM, x + w - RIM, y + h, RIM, IMG_H - RIM, IMG_W - RIM, IMG_H, null);
        g.drawImage(IMG, x + w - RIM, y + h - RIM, x + w, y + h, IMG_W - RIM, IMG_H - RIM, IMG_W, IMG_H, null);

    }

    abstract public void paint(Graphics g);

    public void setGameDto(GameDto gameDto)
    {
        this.gameDto = gameDto;
    }

    protected void showNum(int num, Graphics g, int PaddingTop)
    {
        char spited[] = Integer.toString(num).toCharArray();
        for (int i = spited.length - 1; i >= 0; i--)
        {
            g.drawImage(IMG_NUMBER,
                        this.x + this.w - PADDING - (IMG_NUM_W * (spited.length - i)),
                        this.y + PaddingTop,
                        this.x + this.w - PADDING - (IMG_NUM_W * (spited.length - i - 1)),
                        this.y + PaddingTop + IMG_NUM_H,
                        IMG_NUM_W * Character.getNumericValue(spited[i]),
                        0,
                        IMG_NUM_W * (Character.getNumericValue(spited[i]) + 1),
                        IMG_NUM_H,
                        null);
        }
    }

}
