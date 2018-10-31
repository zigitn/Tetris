package dao;

import dto.PlayerInfo;

import java.io.IOException;
import java.util.List;

/*
 * 数据持久层接口
 * */
public interface Data
{

    //读取数据
    List<PlayerInfo> loadData();

    //存储数据
    void saveData(PlayerInfo playerInfo) throws IOException;
}
