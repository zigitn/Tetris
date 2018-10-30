package ui;

import config.gameConfig;
import config.xmlReader;
import dto.GameDto;

import javax.swing.*;
import java.awt.*;

public abstract class Layer
{
    private static final Image IMG_NUMBER = new ImageIcon("./media/string/num.png").getImage();
    private static final int IMG_NUM_W = IMG_NUMBER.getWidth(null) / 10;
    private static final int IMG_NUM_H = IMG_NUMBER.getHeight(null);
    private static int RIM;
    protected static final int PADDING;

    protected static final int PADDING_SLOT=14;

    private static final Font FONT = new Font("黑体", Font.PLAIN, 20);

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

    public Layer(int x, int y, int w, int h)
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

    /*
     * 值槽显示
     * */
    protected void showSlot(int y, String tittle, int max, double percent, Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(this.x + PADDING - 1, y - 1, this.w - (PADDING << 1) + 2, (PADDING << 1) + 2);

        g.drawImage(Img.IMG_SLOT,
                    this.x + PADDING,
                    y,
                    this.x + PADDING + (int) (percent * Img.IMG_SLOT_W),
                    y+(PADDING<<1),
                    0,
                    0,
                    (int) (percent * Img.IMG_SLOT_W),
                    PADDING << 1,
                    null);

        /*
         * 值槽文字
         * */

        //标题
        g.setFont(FONT);
        g.setColor(Color.WHITE);
        g.drawString(tittle, this.x + (PADDING), y + PADDING + 7);

        //数字
        if (max != 0)
        {
            char spited[] = Integer.toString(max).toCharArray();
            for (int i = spited.length - 1; i >= 0; i--)
            {
                String temp = Character.toString(spited[i]);
                g.drawString(temp, this.x + Img.IMG_SLOT_W - 10 * (spited.length - i), y + PADDING + 7);

            }
        }
        else
        {
            return;
        }

    }

}
