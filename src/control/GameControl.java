package control;

import dao.Data;
import dao.DataBase;
import dao.DataDisk;
import dto.GameDto;
import service.GameService;
import ui.FrameGame;
import ui.PanelGame;
import ui.windows.FrameSet;

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

    /*游戏界面层*/
    private PanelGame panelGame;

    private GameDto gameDto;

    /*游戏逻辑层*/
    private GameService gameService;

    /*游戏行为控制*/
    private Map<Integer, Method> action;

    private Thread mainThread;

    public GameControl()
    {
        //创建游戏数据源
        this.gameDto = new GameDto();
        //创建游戏逻辑块
        this.gameService = new GameService(gameDto);
        //
        Data dataDisk = new DataDisk();
        this.gameDto.setDiskRecode(dataDisk.loadData());

        Data dataDataBase = new DataBase();
        this.gameDto.setDbRecode(dataDataBase.loadData());

        this.panelGame = new PanelGame(gameDto, this);

        setKeyProfile();

        new FrameGame(panelGame, this);

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
            action.put((int) PROP.getProperty("downToBottom").charAt(0),
                       this.gameService.getClass().getMethod("downToBottom"));
            action.put((int) PROP.getProperty("pause").charAt(0), this.gameService.getClass().getMethod("pause"));

            fis.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void actionByKeyCode(int keyCode)
    {
        if (gameDto.isStart()&&this.action.containsKey(keyCode))
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
        this.gameService.startGame();
        this.mainThread = new MainThread();
        this.mainThread.start();
        this.panelGame.repaint();
    }

    private class MainThread extends Thread
    {
        public void run()
        {
            panelGame.repaint();
            while (gameDto.isStart())
            {
                try
                {
                    Thread.sleep(500);
                    if (gameDto.isPause())
                    {
                        continue;
                    }
                    gameService.gameMainAction();
                    panelGame.repaint();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }
        }
    }
}
