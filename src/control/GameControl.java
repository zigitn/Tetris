package control;

import config.DataInterfaceConfig;
import config.gameConfig;
import dao.Data;
import service.GameService;
import ui.PanelGame;

import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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

    /*游戏行为控制*/
    private Map<Integer, Method> action;

    public GameControl(PanelGame panelGame, GameService gameService)
    {
        this.panelGame = panelGame;
        this.gameService = gameService;

        /*获得类对象*/

        dataDisk = createDataObject(gameConfig.getDataConfig().getDataDisk());
        this.gameService.setDiskRecode(dataDisk.loadData());

        dataDataBase = createDataObject(gameConfig.getDataConfig().getDataBase());
        this.gameService.setDbRecode(dataDataBase.loadData());

        //初始化游戏行为
        action = new HashMap<Integer, Method>();
        try
        {
            action.put(KeyEvent.VK_UP, this.gameService.getClass().getMethod("keyUp"));
            action.put(KeyEvent.VK_DOWN, this.gameService.getClass().getMethod("keyDown"));
            action.put(KeyEvent.VK_LEFT, this.gameService.getClass().getMethod("keyLeft"));
            action.put(KeyEvent.VK_RIGHT,this.gameService.getClass().getMethod( "keyRight"));
            action.put(KeyEvent.VK_E, this.gameService.getClass().getMethod("keyTest"));
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
}
