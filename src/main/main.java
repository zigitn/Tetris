package main;

import control.GameControl;
import control.PlayerControl;
import dto.GameDto;
import service.GameService;
import ui.FrameGame;
import ui.PanelGame;

public class main
{
    public static void main(String[] args)
    {

        //游戏数据传输
        GameDto gameDto=new GameDto();

        //游戏主窗体
        PanelGame panel=new PanelGame(gameDto);


        GameService gameService=new GameService(gameDto);

        //游戏控制器
        GameControl gameControl=new GameControl(panel,gameService);

        panel.setGameControl(gameControl);


        PlayerControl playerControl=new PlayerControl(gameControl);
        panel.setGameControl(playerControl);
        new FrameGame(panel,gameControl);
    }


}
