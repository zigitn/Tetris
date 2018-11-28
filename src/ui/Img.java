package ui;

import config.GameConfig;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Img
{
    private Img()
    {
    }

    public static Image[] NEXT_ACT;
    public static List<Image> BG_LIST;
    static
    {
        //下一个方块图片
        NEXT_ACT = new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
        for (int i = 0; i < NEXT_ACT.length; i++)
        {
            NEXT_ACT[i] = new ImageIcon("./media/game/" + i + ".png").getImage();
        }

        //背景列表
        File dir=new File("./media/background");
        File[] files=dir.listFiles();
        BG_LIST=new ArrayList<Image>();
        for (File file:files)
        {
            BG_LIST.add(new ImageIcon(file.getPath()).getImage());
        }
    }

    //BackGround
    public static Image IMG_BG = new ImageIcon("media/background/bg02.jpg").getImage();


    // LayerPoint
    public static final Image IMG_POINT = new ImageIcon("./media/string/point.png").getImage();
    public static final Image IMG_RMLINE = new ImageIcon("./media/string/rmline.png").getImage();
    public static final Image IMG_SLOT = new ImageIcon("./media/window/rect.png").getImage();
    public static final Image IMG_SHADOW = new ImageIcon("./media/game/shadow.png").getImage();
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
