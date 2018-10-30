package dto;

import java.io.Serializable;

public class PlayerInfo implements Comparable<PlayerInfo>, Serializable

{

    private String username;

    private int point;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getPoint()
    {
        return point;
    }

    public void setPoint(int point)
    {
        this.point = point;
    }

    public PlayerInfo(String username, int point)
    {
        this.username = username;
        this.point = point;
    }

    @Override
    public int compareTo(PlayerInfo playerInfo)
    {
        return playerInfo.point-this.point;
    }
}
