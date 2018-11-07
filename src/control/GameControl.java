package control;

import config.DataInterfaceConfig;
import config.gameConfig;
import dao.Data;
import service.GameService;
import ui.PanelGame;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/*接受玩家键盘事件
 * 控制游戏画面
 * 控制游戏逻辑*/
public class GameControl
{

    private Data dataDisk;
    private Data dataDataBase;

    /*游戏界面层*/
    private PanelGame panelGame;

    /*游戏逻辑层*/
    private GameService gameService;

    public GameControl(PanelGame panelGame, GameService gameService)
    {
        this.panelGame = panelGame;
        this.gameService = gameService;

        /*获得类对象*/

        dataDisk = createDataObject(gameConfig.getDataConfig().getDataDisk());
        this.gameService.setDiskRecode(dataDisk.loadData());

        dataDataBase = createDataObject(gameConfig.getDataConfig().getDataBase());
        this.gameService.setDbRecode(dataDataBase.loadData());

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

    public void keyUp()
    {
        this.gameService.keyUp();
        this.panelGame.repaint();
    }

    public void keyDown()
    {
        this.gameService.keyDown();
        this.panelGame.repaint();
    }

    public void keyLeft()
    {
        this.gameService.keyLeft();
        this.panelGame.repaint();
    }

    public void keyRight()
    {
        this.gameService.keyRight();
        this.panelGame.repaint();
    }

    public void keyE()
    {
        this.gameService.keyE();
        this.panelGame.repaint();
    }
}
