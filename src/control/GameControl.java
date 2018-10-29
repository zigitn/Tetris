package control;

import service.GameService;
import ui.PanelGame;


/*接受玩家键盘事件
* 控制游戏画面
* 控制游戏逻辑*/
public class GameControl
{

    /*游戏界面层*/
    private PanelGame panelGame;

    /*游戏逻辑层*/
    private GameService gameService;


    public GameControl(PanelGame panelGame,GameService gameService)
    {
        this.panelGame = panelGame;
        this.gameService= gameService;
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
    {this.gameService.keyLeft();
        this.panelGame.repaint();
    }

    public void keyRight()
    {
        this.gameService.keyRight();
        this.panelGame.repaint();
    }
}
