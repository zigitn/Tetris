package config;

import org.dom4j.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SystemConfig
{
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;
    private final int levelUp;
    private final List<Boolean> typeRound;
    private final List<Point[]> typeConfig;

    public String getDbdriver()
    {
        return dbdriver;
    }

    public String getDbAddress()
    {
        return dbAddress;
    }

    public String getDbUser()
    {
        return dbUser;
    }

    public String getDbPwd()
    {
        return dbPwd;
    }

    public String getLocalfilepath()
    {
        return localfilepath;
    }

    private final String dbdriver;
    private final String dbAddress;
    private final String dbUser;
    private final String dbPwd;
    private final String localfilepath;


    public SystemConfig(Element system, Properties systemSettings)
    {
        this.minX = Integer.parseInt(systemSettings.getProperty("minX"));
        this.maxX = Integer.parseInt(systemSettings.getProperty("maxX"));
        this.minY = Integer.parseInt(systemSettings.getProperty("minY"));
        this.maxY = Integer.parseInt(systemSettings.getProperty("maxY"));
        this.levelUp = Integer.parseInt(systemSettings.getProperty("levelUp"));
        this.dbdriver = systemSettings.getProperty("dbdriver");
        this.dbAddress = systemSettings.getProperty("dbAddress");
        this.dbUser = systemSettings.getProperty("dbUser");
        this.dbPwd = systemSettings.getProperty("dbPwd");
        this.localfilepath = systemSettings.getProperty("filePath");




        List<Element> rects = system.elements("rect");
        typeConfig = new ArrayList<Point[]>(rects.size());
        typeRound = new ArrayList<Boolean>(rects.size());
        for (Element e : rects)
        {
            //是否可以旋转
            this.typeRound.add(Boolean.parseBoolean(e.attributeValue("round")));

            //获得坐标对象
            List<Element> pointConfig = e.elements("point");
            Point[] points = new Point[pointConfig.size()];
            for (int i = 0; i < points.length; i++)
            {
                int x = Integer.parseInt(pointConfig.get(i).attributeValue("x"));
                int y = Integer.parseInt(pointConfig.get(i).attributeValue("y"));
                points[i] = new Point(x, y);
            }
            typeConfig.add(points);
        }

    }

    public int getLevelUp()
    {
        return levelUp;
    }

    public int getMinX()
    {
        return minX;
    }

    public int getMaxX()
    {
        return maxX;
    }

    public int getMinY()
    {
        return minY;
    }

    public int getMaxY()
    {
        return maxY;
    }

    public List<Point[]> getTypeConfig()
    {
        return typeConfig;
    }

    public List<Boolean> getTypeRound()
    {
        return typeRound;
    }
}