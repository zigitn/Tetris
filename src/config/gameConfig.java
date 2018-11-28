package config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.util.Properties;

public class gameConfig
{
    private static FrameConfig FRAME_CONFIG = null;
    private static DataConfig DATA_CONFIG = null;
    private static SystemConfig SYSTEM_CONFIG = null;
    private static Properties PROP = new Properties();

    static
    {
        try
        {
            //读取Propertses文件

            FileInputStream fis = new FileInputStream("./data/systemconfig.Properties");
            PROP.load(fis);
            fis.close();

            //创建xml读取器
            SAXReader reader = new SAXReader();
            //读取xml文件
            Document doc = reader.read("./data/config.xml");
            //获得xml根结点
            Element game = doc.getRootElement();
            //创建界面配置对象
            FRAME_CONFIG = new FrameConfig(game.element("frame"), PROP);
            //创建系统对象
            SYSTEM_CONFIG = new SystemConfig(game.element("system"), PROP);
            //创建数据访问对象
            DATA_CONFIG = new DataConfig(game.element("data"), PROP);




        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //构造器私有化
    private gameConfig()
    {
    }

    /*获得窗口配置*/
    public static FrameConfig getFrameConfig()
    {
        return FRAME_CONFIG;
    }

    /*获得系统配置*/
    public static DataConfig getDataConfig()
    {
        return DATA_CONFIG;
    }

    /*获得数据访问配置*/
    public static SystemConfig getSystemConfig()
    {
        return SYSTEM_CONFIG;
    }



}


