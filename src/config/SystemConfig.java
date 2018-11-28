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

    public String getDriver()
    {
        return driver;
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

    public String getLocalFilePath()
    {
        return LocalFilePath;
    }

    private final String driver;
    private final String dbAddress;
    private final String dbUser;
    private final String dbPwd;
    private final String LocalFilePath;


    public SystemConfig(Element system, Properties PROP)
    {
        this.minX = Integer.parseInt(PROP.getProperty("minX"));
        this.maxX = Integer.parseInt(PROP.getProperty("maxX"));
        this.minY = Integer.parseInt(PROP.getProperty("minY"));
        this.maxY = Integer.parseInt(PROP.getProperty("maxY"));
        this.levelUp = Integer.parseInt(PROP.getProperty("levelUp"));
        this.driver = PROP.getProperty("driver");
        this.dbAddress = PROP.getProperty("dbAddress");
        this.dbUser = PROP.getProperty("dbUser");
        this.dbPwd = PROP.getProperty("dbPwd");
        this.LocalFilePath = PROP.getProperty("filePath");


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