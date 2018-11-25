package control;

import config.DataInterfaceConfig;
import config.gameConfig;
import dao.Data;
import service.GameService;
import ui.PanelGame;
import ui.cfg.FrameSet;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*接受玩家键盘事件
 * 控制游戏画面
 * 控制游戏逻辑*/
public class GameControl
{

    private Data dataDisk;
    private Data dataDataBase;
    private Properties PROP = new Properties();

    /*游戏界面层*/
    private PanelGame panelGame;

    /*游戏逻辑层*/
    private GameService gameService;

    /*游戏行为控制*/
    private Map<Integer, Method> action;

    private FrameSet frameSet;

    public GameControl(PanelGame panelGame, GameService gameService)
    {
        this.panelGame = panelGame;
        this.gameService = gameService;

        /*获得类对象*/

        dataDisk = createDataObject(gameConfig.getDataConfig().getDataDisk());
        this.gameService.setDiskRecode(dataDisk.loadData());

        dataDataBase = createDataObject(gameConfig.getDataConfig().getDataBase());
        this.gameService.setDbRecode(dataDataBase.loadData());
        setKeyProfile();
        this.frameSet=new FrameSet();
    }

    private void setKeyProfile()
    {
        action = new HashMap<Integer, Method>();
        try
        {
            FileInputStream fis = new FileInputStream("./data/control.Properties");
            PROP.load(fis);
            action.put((int) PROP.getProperty("up").charAt(0), this.gameService.getClass().getMethod("up"));
            action.put((int) PROP.getProperty("down").charAt(0), this.gameService.getClass().getMethod("down"));
            action.put((int) PROP.getProperty("left").charAt(0), this.gameService.getClass().getMethod("left"));
            action.put((int) PROP.getProperty("right").charAt(0), this.gameService.getClass().getMethod("right"));
            action.put((int) PROP.getProperty("cheat").charAt(0), this.gameService.getClass().getMethod("cheat"));

            fis.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /*创建数据对象*/
    private Data createDataObject(DataInterfaceConfig cfg)
    {
        try
        {
            /*获得类对象*/
            Class<?> cls = Class.forName(cfg.getClassName());
            /*获得构造器*/
            Constructor<?> ctr = cls.getConstructor(HashMap.class);
            /*创建对象*/
            return (Data) ctr.newInstance(cfg.getParam());

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void actionByKeyCode(int keyCode)
    {
        if (this.action.containsKey(keyCode))
        {
            try
            {
                this.action.get(keyCode).invoke(this.gameService);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        this.panelGame.repaint();
    }

    public void showFrameSet()
    {
        this.frameSet.setVisible(true);
    }
}
