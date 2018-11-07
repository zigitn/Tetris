package config;

import org.dom4j.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SystemConfig
{
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;

    private final List<Point[]> typeConfig;

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

    public SystemConfig(Element system)
    {
        this.minX=Integer.parseInt(system.attributeValue("minX"));
        this.maxX=Integer.parseInt(system.attributeValue("maxX"));
        this.minY=Integer.parseInt(system.attributeValue("minY"));
        this.maxY=Integer.parseInt(system.attributeValue("maxY"));

        List<Element> rects= system.elements("rect");
        typeConfig=new ArrayList<Point[]>(rects.size());
        for (Element e: rects)
        {
            List<Element> pointConfig= e.elements("point");
            Point[] points=new Point[pointConfig.size()];
            for (int i=0;i<points.length;i++)
            {
                int x=Integer.parseInt( pointConfig.get(i).attributeValue("x"));
                int y=Integer.parseInt( pointConfig.get(i).attributeValue("y"));
                points[i]=new Point(x,y);
            }
            typeConfig.add(points);
        }


    }
}