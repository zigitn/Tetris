package service;

import dto.GameDto;
import dto.PlayerInfo;
import entity.GameAct;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class GameService
{

    private GameDto gameDto;

    private Random random = new Random();
    private static final int MAX_TYPE = 6;

    private int countRemoveLine = 0;
    public GameService(GameDto gameDto)
    {
        this.gameDto = gameDto;
        GameAct gameAct = new GameAct(random.nextInt(MAX_TYPE));
        gameDto.setGameAct(gameAct);
    }

    public void up()
    {
        this.gameDto.getGameAct().round(this.gameDto.getGameMainMap());
    }

    public void down()
    {
        if (!this.gameDto.getGameAct().move(0, 1, this.gameDto.getGameMainMap()))
        {
            boolean[][] map = this.gameDto.getGameMainMap();
            Point[] act = this.gameDto.getGameAct().getActPoints();
            for (Point point : act)
            {
                map[point.x][point.y] = true;
            }
            this.gameDto.getGameAct().init(this.gameDto.getNext());
            this.gameDto.setNext(random.nextInt(MAX_TYPE));
            this.checkRemoveLine();
            int nowPoint=gameDto.getNowPoint();
            gameDto.setNowPoint(countRemoveLine>0?nowPoint+(countRemoveLine<<1)-1:nowPoint);
            countRemoveLine=0;
        }
    }

    private void checkRemoveLine()
    {
        for (int y = 17; y > 0; y--)
        {
            if (canRemoveline(y))
            {
                gameDto.setNowRemoveLine(gameDto.getNowRemoveLine() + 1);
                countRemoveLine++;
                checkRemoveLine();
            }
        }
    }

    private boolean canRemoveline(int y)
    {
        boolean[][] mainMap = gameDto.getGameMainMap();
        for (int x = 0; x < 10; x++)
        {
            if (!mainMap[x][y])
            {
                return false;
            }
        }
        for (int i = y; i > 0; i--)
        {
            for (int x = 0; x < 10; x++)
            {
                mainMap[x][i] = mainMap[x][i - 1];
            }
        }
        return true;
    }

    public void left()
    {
        this.gameDto.getGameAct().move(-1, 0, this.gameDto.getGameMainMap());
    }

    public void right()
    {
        this.gameDto.getGameAct().move(1, 0, this.gameDto.getGameMainMap());

    }

    //===================================================
    public void cheat()
    {
        this.gameDto.setNowLevel(this.gameDto.getNowLevel() + 1);
        this.gameDto.setNowPoint(this.gameDto.getNowPoint() + 1);
        this.gameDto.setNowRemoveLine(this.gameDto.getNowRemoveLine() + 1);
    }

    public void setDbRecode(List<PlayerInfo> loadData)
    {
        this.gameDto.setDbRecode(loadData);
    }

    public void setDiskRecode(List<PlayerInfo> loadData)
    {
        this.gameDto.setDiskRecode(loadData);
    }
}
