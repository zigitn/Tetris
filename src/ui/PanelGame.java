package ui;

import config.gameConfig;
import config.layerConfig;
import config.xmlReader;
import control.PlayerControl;
import dto.GameDto;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class PanelGame extends JPanel
{
    private List<layer> layers;

    private GameDto gameDto;
    public PanelGame(GameDto gameDto)
    {
        this.gameDto=gameDto;
        initComponent();
        initlayer();
    }

    public void setGameControl(PlayerControl playerControl)
    {
        this.addKeyListener(playerControl);
    }

    private void initComponent()
    {
    }

    private void initlayer()
    {
        try
        {
            gameConfig cfg = xmlReader.getGameConfig();
            List<layerConfig> layersCfg = cfg.getLayersConfig();
            layers = new ArrayList<>(layersCfg.size());
            for (layerConfig layerCfg : layersCfg)
            {
                Class<?> cls = Class.forName(layerCfg.getClassName());

                Constructor<?> ctr = cls.getConstructor(int.class, int.class, int.class, int.class);

                layer layer = (layer) ctr.newInstance(layerCfg.getX(), layerCfg.getY(), layerCfg.getW(), layerCfg.getH());
                layer.setGameDto(gameDto);
                layers.add(layer);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (int i = 0; i < layers.size(); i++)
        {

            layers.get(i).paint(g);
        }
        this.requestFocus();
    }
}

