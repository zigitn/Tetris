package dao;

import dto.PlayerInfo;
import ui.Layer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataDisk implements Data
{

    private static final String FILE_PATH = "./saves/recode.dat";

    @Override
    public void saveData(List<PlayerInfo> playerInfoList)
    {

        ObjectOutputStream oos = null;
        try
        {
            oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
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
            ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
            playerInfoList=(List<PlayerInfo>) ois.readObject();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                ois.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        } return playerInfoList;
    }
}
