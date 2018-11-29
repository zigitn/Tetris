package service;

import config.GameConfig;
import dto.GameDto;
import dto.PlayerInfo;
import entity.GameAct;
import ui.windows.SavePoint;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class GameService
{
    private final GameDto gameDto;
    private Random random = new Random();
    private static final int MAX_TYPE = GameConfig.getSystemConfig().getTypeConfig().size() - 1;
    private static final double LEVEL_UP = GameConfig.getSystemConfig().getLevelUp();
    private int countRemoveLine = 0;
    private int sumRemoveLine = 0;

    public GameService(GameDto gameDto)
    {
        this.gameDto = gameDto;
    }

    public void up()
    {
        synchronized (this.gameDto)
        {
            this.gameDto.getGameAct().round(this.gameDto.getGameMainMap());
        }
    }

    public boolean down()
    {
        if (!gameDto.isPause())
        {
            synchronized (this.gameDto)
            {
                if (this.gameDto.getGameAct().move(0, 1, this.gameDto.getGameMainMap()))
                {
                    return true;
                }
                boolean[][] map = this.gameDto.getGameMainMap();
                Point[] act = this.gameDto.getGameAct().getActPoints();
                for (Point point : act)
                {
                    map[point.x][point.y] = true;
                }
                this.gameDto.getGameAct().init(this.gameDto.getNext());
                this.gameDto.setNext(random.nextInt(MAX_TYPE));

                this.checkRemoveLine();
                int nowPoint = gameDto.getNowPoint();
                gameDto.setNowPoint(countRemoveLine > 0 ? nowPoint + ((countRemoveLine - 1) << 1) : nowPoint);
                if (sumRemoveLine % 20 == 1 && sumRemoveLine != 1)
                {
                    gameDto.setNowLevel(gameDto.getNowLevel() + 1);
                }
                countRemoveLine = 0;

                if (checkIsLose())
                {
                    gameDto.setStart(false);
                    new SavePoint(this);
                }
                return false;
            }
        }
        return false;

    }


    public void left()
    {
        if (!gameDto.isPause())
        {
            synchronized (this.gameDto)
            {
                this.gameDto.getGameAct().move(-1, 0, this.gameDto.getGameMainMap());
            }
        }
    }

    public void right()
    {
        if (!gameDto.isPause())
        {
            synchronized (this.gameDto)
            {
                this.gameDto.getGameAct().move(1, 0, this.gameDto.getGameMainMap());
            }
        }
    }

    //===================================================
    public void cheat()
    {
        this.gameDto.setNowLevel(this.gameDto.getNowLevel() + 1);
        this.gameDto.setNowPoint(this.gameDto.getNowPoint() + 1);
        this.gameDto.setNowRemoveLine(this.gameDto.getNowRemoveLine() + 1);
    }

    public void downToBottom()
    {
        while (this.down());
    }

    public void pause()
    {
        if (gameDto.isStart())
        {
            gameDto.changePause();
        }
    }

    //==================================================
    /*启动主线程*/
    public void startGame()
    {
        GameAct gameAct = new GameAct(random.nextInt(MAX_TYPE));
        gameDto.setGameAct(gameAct);
        gameDto.setStart(true);
    }

    public void gameMainAction()
    {
        down();
    }


    private boolean checkIsLose()
    {
        Point[] points = this.gameDto.getGameAct().getActPoints();
        boolean[][] gameMap = this.gameDto.getGameMainMap();
        for (Point p : points)
        {
            if (gameMap[p.x][p.y])
            {
                return true;
            }
        }
        return false;
    }

    private void checkRemoveLine()
    {
        for (int y = 17; y > 0; y--)
        {
            if (canRemoveLine(y))
            {
                gameDto.setNowRemoveLine(gameDto.getNowRemoveLine() + 1);
                countRemoveLine++;
                sumRemoveLine++;
                checkRemoveLine();
            }
        }
    }

    private boolean canRemoveLine(int y)
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

}
