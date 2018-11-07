package ui.ui;

import dto.PlayerInfo;
import ui.Layer;

import java.awt.*;
import java.util.List;

public abstract class LayerData extends Layer
{
    public LayerData(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    @Override
    abstract public void paint(Graphics g);

    public void showData(List<PlayerInfo> playerInfo, Graphics g)
    {
        int nowPoint = this.gameDto.getNowPoint();
        for (int i = 0; i < playerInfo.size(); i++)
        {
            int max = playerInfo.get(i).getPoint();
            showSlot(this.y + (PADDING << 2) + PADDING_SLOT * 3 * i,
                     playerInfo.get(i).getUsername(),
                     max,
                     nowPoint <= max ? (double) nowPoint / max : 1,
                     g);
        }
    }
}
