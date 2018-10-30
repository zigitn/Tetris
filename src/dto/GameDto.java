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
    private List<PlayerInfo> localRecode;



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

    public GameDto()
    {
        gameDtoInit();

    }
    public void gameDtoInit()
    {
        this.gameMainMap=new boolean[10][18];
    }

    public void setDbRecode(List<PlayerInfo> dbRecode)
    {

        Collections.sort(dbRecode);
        this.dbRecode = dbRecode;
    }

    public List<PlayerInfo> getDbRecode()
    {
        if (this.dbRecode == null)
        {
            dbRecode=new ArrayList<PlayerInfo>();
            dbRecode.add(new PlayerInfo("no one",0));
        }
        return this.dbRecode;
    }

    public List<PlayerInfo> getLocalRecode()
    {
        if (this.localRecode == null)
        {
            this.localRecode=new ArrayList<PlayerInfo>();
            this.localRecode.add(new PlayerInfo("no one",0));
        }
        return this.localRecode;
    }

    public void setLocalRecode(List<PlayerInfo> localRecode)
    {
        Collections.sort(localRecode);
        this.localRecode = localRecode;
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
