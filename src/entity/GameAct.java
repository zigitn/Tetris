package entity;

import config.GameConfig;
import java.awt.*;
import java.util.List;

public class GameAct
{
    private Point[] actPoints;

    private static final int minX = GameConfig.getSystemConfig().getMinX();
    private static final int minY = GameConfig.getSystemConfig().getMinY();
    private static final int maxX = GameConfig.getSystemConfig().getMaxX();
    private static final int maxY = GameConfig.getSystemConfig().getMaxY();
    private static final List<Point[]> TYPE_CONFIG= GameConfig.getSystemConfig().getTypeConfig();
    private static final List<Boolean> TYPE_ROUND= GameConfig.getSystemConfig().getTypeRound();

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
        this.typeCode = typeCode;
        Point[] points = TYPE_CONFIG.get(typeCode);
        actPoints = new Point[points.length];
        for (int i = 0; i < actPoints.length; i++)
        {
            actPoints[i] = new Point(points[i].x, points[i].y);
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
        if (!TYPE_ROUND.get(this.typeCode))
        {
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
        return x < minX || x > maxX || y < minY || y > maxY || mainGameMap[x][y];
    }


}
