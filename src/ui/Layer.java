package ui;

import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;

import javax.swing.*;
import java.awt.*;

public abstract class Layer
{
    private static final Image IMG_NUMBER = new ImageIcon("./media/string/num.png").getImage();
    private static final int IMG_NUM_W = IMG_NUMBER.getWidth(null) / 10;
    private static final int IMG_NUM_H = IMG_NUMBER.getHeight(null);
    protected static int BORDER;
    protected static final int PADDING;
    protected static final int WINDOW_W;
    protected static final int WINDOW_H;
    protected static final int PADDING_SLOT = 14;

    private static final Font FONT = new Font("黑体", Font.PLAIN, 20);

    static
    {
        FrameConfig fCfg = GameConfig.getFrameConfig();
        BORDER = fCfg.getBorder();
        PADDING = fCfg.getPadding();
        WINDOW_H = fCfg.getHeight();
        WINDOW_W = fCfg.getWidth();
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
        g.drawImage(IMG, x, y, x + BORDER, y + BORDER, 0, 0, BORDER, BORDER, null);
        g.drawImage(IMG, x + BORDER, y, w + x - BORDER, y + BORDER, BORDER, 0, IMG_W - BORDER, BORDER, null);
        g.drawImage(IMG, w + x - BORDER, y, w + x, y + BORDER, IMG_W - BORDER, 0, IMG_W, BORDER, null);

        g.drawImage(IMG, x, y + BORDER, x + BORDER, y + h - BORDER, 0, BORDER, BORDER, IMG_H - BORDER, null);
        g.drawImage(IMG,
                    x + BORDER,
                    y + BORDER,
                    x + w - BORDER,
                    y + h - BORDER,
                    BORDER,
                    BORDER,
                    IMG_W - BORDER,
                    IMG_H - BORDER,
                    null);
        g.drawImage(IMG,
                    x + w - BORDER,
                    y + BORDER,
                    w + x,
                    y + h - BORDER,
                    IMG_W - BORDER,
                    BORDER,
                    IMG_W,
                    IMG_H - BORDER,
                    null);

        g.drawImage(IMG, x, y + h - BORDER, x + BORDER, y + h, 0, IMG_H - BORDER, BORDER, IMG_H, null);
        g.drawImage(IMG,
                    x + BORDER,
                    y + h - BORDER,
                    x + w - BORDER,
                    y + h,
                    BORDER,
                    IMG_H - BORDER,
                    IMG_W - BORDER,
                    IMG_H,
                    null);
        g.drawImage(IMG,
                    x + w - BORDER,
                    y + h - BORDER,
                    x + w,
                    y + h,
                    IMG_W - BORDER,
                    IMG_H - BORDER,
                    IMG_W,
                    IMG_H,
                    null);

    }

    protected void drawImageAtCenter(Image img, Graphics g)
    {
        int imgW = img.getWidth(null);
        int imgH = img.getHeight(null);
        g.drawImage(img, this.x + (this.w - imgW >> 1), this.y + (this.h - imgH >> 1), null);
    }

    abstract public void paint(Graphics g);

    public void setGameDto(GameDto gameDto)
    {
        this.gameDto = gameDto;
    }

    protected void showNum(int num, Graphics g, int PaddingTop)
    {
        char[] spited = Integer.toString(num).toCharArray();
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
                    y + (PADDING << 1),
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
            char[] spited = Integer.toString(max).toCharArray();
            for (int i = spited.length - 1; i >= 0; i--)
            {
                String temp = Character.toString(spited[i]);
                g.drawString(temp, this.x + Img.IMG_SLOT_W - 10 * (spited.length - i), y + PADDING + 7);

            }
        }

    }

}
