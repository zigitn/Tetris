package dao;

import dto.PlayerInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBase implements Data
{
    private final String dbUrl;
    private final String dbUser;
    private final String dbPwd;

    public DataBase(HashMap<String, String> param)
    {
        this.dbUrl = param.get("dbUrl");
        this.dbUser = param.get("dbUser");
        this.dbPwd = param.get("dbPwd");
        try
        {
            Class.forName(param.get("driver"));
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<PlayerInfo> loadData()
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<PlayerInfo> playerInfoList = new ArrayList<>();

        try
        {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select name,point from Tetris.Tetris order by point desc");

            while (rs.next())
            {
                playerInfoList.add(new PlayerInfo(rs.getString(1), rs.getInt(2)));
            }
        }
        catch (SQLException e)
        {
            //忽略数据库未连接日志
            //e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                if (conn != null)
                {
                    conn.close();
                }
                if (stmt != null)
                {
                    stmt.close();
                }
                if (rs != null)
                {
                    rs.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return playerInfoList;
    }

    @Override
    public void saveData(PlayerInfo playerInfo)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try
        {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            stmt = conn.prepareStatement("insert into Tetris.Tetris(name,point) values (?,?)");
            stmt.setString(1, playerInfo.getUsername());
            stmt.setInt(2, playerInfo.getPoint());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (conn != null)
                {
                    conn.close();
                }
                if (stmt != null)
                {
                    stmt.close();
                }

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }

}
