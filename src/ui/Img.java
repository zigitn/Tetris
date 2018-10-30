package ui;

import javax.swing.*;
import java.awt.*;

public class Img
{
    private Img()
    {
    }
    //BackGround
    public static Image IMG_BG = new ImageIcon("media/background/bg02.jpg").getImage();


    // LayerPoint
    public static final Image IMG_POINT = new ImageIcon("./media/string/point.png").getImage();
    public static final Image IMG_RMLINE = new ImageIcon("./media/string/rmline.png").getImage();
    public static final Image IMG_SLOT = new ImageIcon("./media/window/rect.png").getImage();
    public static final int IMG_SLOT_W = Img.IMG_SLOT.getWidth(null);



    //LayerLevel
    public static final Image IMG_LEVEL = new ImageIcon("./media/string/level.png").getImage();



    //LayerGame
    public static final Image ACT = new ImageIcon("./media/game/rect.png").getImage();

    //layerDataBase
    public static Image IMG_DB = new ImageIcon("media/string/db.png").getImage();


    //LayerDisk
    public static Image IMG_DISK = new ImageIcon("media/string/disk.png").getImage();

}
