package dto;

import entity.GameAct;

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
    private int nowlevel;

    //
    private int nowPonit;
    private int nowRemoveLine;

    public GameDto()
    {
        gameDtoInit();

    }
    public void gameDtoInit()
    {
        this.gameMainMap=new boolean[10][18];
    }

    public List<PlayerInfo> getDbRecode()
    {
        return dbRecode;
    }

    public void setDbRecode(List<PlayerInfo> dbRecode)
    {
        this.dbRecode = dbRecode;
    }

    public List<PlayerInfo> getLocalRecode()
    {
        return localRecode;
    }

    public void setLocalRecode(List<PlayerInfo> localRecode)
    {
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

    public int getNowlevel()
    {
        return nowlevel;
    }

    public void setNowlevel(int nowlevel)
    {
        this.nowlevel = nowlevel;
    }

    public int getNowPonit()
    {
        return nowPonit;
    }

    public void setNowPonit(int nowPonit)
    {
        this.nowPonit = nowPonit;
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
