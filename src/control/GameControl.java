package control;

import dao.Data;
import dao.DataBase;
import dao.DataDisk;
import service.GameService;
import ui.PanelGame;
import ui.cfg.FrameSet;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*接受玩家键盘事件
 * 控制游戏画面
 * 控制游戏逻辑*/
public class GameControl
{

    private Data dataDisk =new DataDisk();
    private Data dataDataBase= new DataBase();

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

        this.gameService.setDiskRecode(dataDisk.loadData());

        this.gameService.setDbRecode(dataDataBase.loadData());
        setKeyProfile();
    }

    public void setKeyProfile()
    {
        action = new HashMap<Integer, Method>();
        try
        {
            Properties PROP = new Properties();
            FileInputStream fis = new FileInputStream("./data/control.Properties");
            PROP.load(fis);
            action.put((int) PROP.getProperty("up").charAt(0), this.gameService.getClass().getMethod("up"));
            action.put((int) PROP.getProperty("down").charAt(0), this.gameService.getClass().getMethod("down"));
            action.put((int) PROP.getProperty("left").charAt(0), this.gameService.getClass().getMethod("left"));
            action.put((int) PROP.getProperty("right").charAt(0), this.gameService.getClass().getMethod("right"));
            action.put((int) PROP.getProperty("cheat").charAt(0), this.gameService.getClass().getMethod("cheat"));
            action.put((int) PROP.getProperty("downToBottom").charAt(0), this.gameService.getClass().getMethod("downToBottom"));

            fis.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    void actionByKeyCode(int keyCode)
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
        new FrameSet(this);
    }

    public void startGame()
    {
        this.gameService.startMainThead();
        this.panelGame.repaint();
    }
}
