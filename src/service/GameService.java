package service;

import dto.GameDto;
import entity.GameAct;

import java.awt.*;
import java.util.Random;

public class GameService
{

    private GameDto gameDto;

    private Random random = new Random();
    private static final int MAX_TYPE = 6;

    public GameService(GameDto gameDto)
    {
        this.gameDto = gameDto;
        GameAct gameAct = new GameAct(random.nextInt(MAX_TYPE));
        gameDto.setGameAct(gameAct);
    }

    public void keyUp()
    {
        this.gameDto.getGameAct().round(this.gameDto.getGameMainMap());
    }

    public void keyDown()
    {
        if (!this.gameDto.getGameAct().move(0, 1, this.gameDto.getGameMainMap()))
        {

            boolean[][] map = this.gameDto.getGameMainMap();
            Point[] act = this.gameDto.getGameAct().getActPoints();
            for (int i = 0; i < act.length; i++)
            {
                map[act[i].x][act[i].y] = true;
            }
            this.gameDto.getGameAct().init(this.gameDto.getNext());
            this.gameDto.setNext(random.nextInt(MAX_TYPE));
        }
    }

    public void keyLeft()
    {
        this.gameDto.getGameAct().move(-1, 0, this.gameDto.getGameMainMap());
    }

    public void keyRight()
    {
        this.gameDto.getGameAct().move(1, 0, this.gameDto.getGameMainMap());

    }

}
