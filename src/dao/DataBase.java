package dao;

import dto.PlayerInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase implements Data
{
    private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String DB_URL = "jdbc:sqlserver://127.0.0.1:1433";
    private static String DB_USER = "tetris";
    private static String DB_PWD = "Tetris123";

    static
    {
        try
        {
            Class.forName(DRIVER);
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
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<PlayerInfo> playerInfoList = new ArrayList<>();

        try
        {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            stmt = conn.prepareStatement("select top 5 name,point from name_point order by point desc ");
            rs = stmt.executeQuery();
            while (rs.next())
            {
                playerInfoList.add(new PlayerInfo(rs.getString(1), rs.getInt(2)));
            }

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
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            stmt = conn.prepareStatement("insert into name_point(name,point) values (?,?)");
            stmt.setObject(1,playerInfo.getUsername());
            stmt.setObject(2,playerInfo.getPoint());
            stmt.execute();
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
