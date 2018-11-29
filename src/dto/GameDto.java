package dto;

import entity.GameAct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameDto
{



    public GameAct getGameAct()
    {
        return gameAct;
    }
    //左-显示数据库数据
    private List<PlayerInfo> dbRecode;
    //左-显示本地数据
    private List<PlayerInfo> diskRecode;



    //中-游戏主窗体
    private boolean[][] gameMainMap;
    private GameAct gameAct;

    //右-下一个方块
    private int next;

    //右-等级
    private int nowLevel;

    //
    private int nowPoint;
    private int nowRemoveLine;

    private boolean pause;

    public boolean isPause()
    {
        return pause;
    }

    public void changePause()
    {
        this.pause = !this.pause;
    }

    private boolean start;

    public boolean isStart()
    {
        return start;
    }


    public void setStart(boolean start)
    {
        this.start = start;
    }

    public GameDto()
    {
        gameDtoInit();

    }
    public void gameDtoInit()
    {
        this.gameMainMap=new boolean[10][18];
        this.nowLevel=0;
        this.nowPoint=0;
        this.nowRemoveLine=0;
        this.pause=false;
    }

    public void setDbRecode(List<PlayerInfo> dbRecode)
    {
        if (this.dbRecode == null)
        {
            this.dbRecode=new ArrayList<PlayerInfo>();
            this.dbRecode.add(new PlayerInfo("no one",0));
        }
        else
        {
            Collections.sort(dbRecode);
        }
        this.dbRecode = dbRecode;
    }
    public void setDiskRecode(List<PlayerInfo> diskRecode)
    {
        if (this.diskRecode == null)
        {
            this.diskRecode=new ArrayList<PlayerInfo>();
            this.diskRecode.add(new PlayerInfo("no one",0));
        }
        else
        {
            Collections.sort(diskRecode);
        }
        this.diskRecode = diskRecode;
    }

    public List<PlayerInfo> getDbRecode()
    {
        if (this.dbRecode == null)
        {
            this.dbRecode=new ArrayList<PlayerInfo>();
        }
        return this.dbRecode;
    }

    public List<PlayerInfo> getDiskRecode()
    {
        if (this.diskRecode == null)
        {
            this.diskRecode=new ArrayList<PlayerInfo>();
        }
        return this.diskRecode;
    }



    public boolean[][] getGameMainMap()
    {
        return gameMainMap;
    }

    public void setGameMainMap(boolean[][] gameMainMap)
    {
        this.gameMainMap = gameMainMap;
    }



    public void setGameAct(GameAct gameAct)
    {
        this.gameAct = gameAct;
    }

    public int getNext()
    {
        return next;
    }

    public void setNext(int next)
    {
        this.next = next;
    }

    public int getNowLevel()
    {
        return nowLevel;
    }

    public void setNowLevel(int nowLevel)
    {
        this.nowLevel = nowLevel;
    }

    public int getNowPoint()
    {
        return nowPoint;
    }

    public void setNowPoint(int nowPoint)
    {
        this.nowPoint = nowPoint;
    }

    public int getNowRemoveLine()
    {
        return nowRemoveLine;
    }

    public void setNowRemoveLine(int nowRemoveLine)
    {
        this.nowRemoveLine = nowRemoveLine;
    }

}
