package dao;

import dto.PlayerInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase implements Data
{
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306";
    private static String DB_USER = "tetris";
    private static String DB_PWD = "tetrisdbpass";

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
        Statement stmt = null;
        ResultSet rs = null;
        List<PlayerInfo> playerInfoList = new ArrayList<>();

        try
        {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select name,point from Tetris.Tetris order by point desc");

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
            stmt = conn.prepareStatement("insert into Tetris.Tetris(name,point) values (?,?)");
            stmt.setString(1,playerInfo.getUsername());
            stmt.setInt(2,playerInfo.getPoint());
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
