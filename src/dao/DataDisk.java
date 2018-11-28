package dao;

import config.GameConfig;
import dto.PlayerInfo;

import java.io.*;
import java.util.List;

public class DataDisk implements Data
{

    private final String filePath=GameConfig.getSystemConfig().getLocalfilepath();


    @Override
    public void saveData(PlayerInfo playerInfo)
    {
        List<PlayerInfo> playerInfoList=this.loadData();
        playerInfoList.add(playerInfo);
        ObjectOutputStream oos = null;
        try
        {
            oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(playerInfoList);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                oos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
    @Override
    public List<PlayerInfo> loadData()
    {
        ObjectInputStream ois = null;
        List<PlayerInfo> playerInfoList=null;
        try
        {
            ois = new ObjectInputStream(new FileInputStream(filePath));
            playerInfoList=(List<PlayerInfo>) ois.readObject();
        }
        catch (Exception e)
        {
        }
        finally
        {
            try
            {
                ois.close();
            }
            catch (Exception e)
            {
            }
        } return playerInfoList;
    }
}
