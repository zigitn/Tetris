package entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameAct
{
    private Point[] actPoints;

    private static int MIN_X = 0;
    private static int MIN_Y = 0;
    private static int MAX_X = 9;
    private static int MAX_Y = 17;

    private static List<Point[]> TYPE_CONFIG;

    static
    {
        TYPE_CONFIG=new ArrayList<Point[]>(7);
        TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(6, 0)});
        TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(4, 1)});
        TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(3, 1)});
        TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(5, 0), new Point(3, 1), new Point(4, 1)});
        TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(5, 0), new Point(4, 1), new Point(5, 1)});
        TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(5, 1)});
        TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(4, 1), new Point(5, 1)});
    }

    public int getTypeCode()
    {
        return typeCode;
    }

    private int typeCode;

    public GameAct(int typeCode)
    {
        this.init(typeCode);

    }

    public void init(int typeCode)
    {
        this.typeCode=typeCode;
        Point[] points=TYPE_CONFIG.get(typeCode);
        actPoints=new Point[points.length];
        for (int i=0;i<actPoints.length;i++)
        {
            actPoints[i]=new Point(points[i].x,points[i].y);
        }
    }

    public Point[] getActPoints()
    {
        return actPoints;
    }

    public boolean move(int moveX, int moveY, boolean[][] mainGameMap)
    {
        for (int i = 0; i < actPoints.length; i++)
        {
            int newX = actPoints[i].x + moveX;
            int newY = actPoints[i].y + moveY;
            if (isOverZone(newX, newY, mainGameMap))
            {
                return false;
            }
        }
        for (int i = 0; i < actPoints.length; i++)
        {
            actPoints[i].x += moveX;
            actPoints[i].y += moveY;
        }
        return true;
    }

    public void round(boolean[][] mainGameMap)
    {
        if (this.typeCode ==4){
            return;
        }

        for (int i = 1; i < actPoints.length; i++)
        {
            int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
            int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
            if (isOverZone(newX, newY, mainGameMap))
            {
                return;
            }
        }
        for (int i = 0; i < actPoints.length; i++)
        {
            int newX = actPoints[0].y + actPoints[0].x - actPoints[i].y;
            int newY = actPoints[0].y - actPoints[0].x + actPoints[i].x;
            actPoints[i].x = newX;
            actPoints[i].y = newY;
        }
    }

    private boolean isOverZone(int x, int y, boolean[][] mainGameMap)
    {
        return x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y || mainGameMap[x][y];
    }

}
