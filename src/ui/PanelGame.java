package ui;

import config.FrameConfig;
import config.LayerConfig;
import config.gameConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class PanelGame extends JPanel
{
    private List<Layer> layers;

    private GameDto gameDto;



    private GameControl gameControl=null;
    public PanelGame(GameDto gameDto)
    {
        this.gameDto = gameDto;
        initComponent();
        initLayer();

    }



    private void initComponent()
    {

    }

    private void initLayer()
    {
        try
        {
            FrameConfig fCfg = gameConfig.getFrameConfig();
            List<LayerConfig> layersCfg = fCfg.getLayersConfig();
            layers = new ArrayList<>(layersCfg.size());
            for (LayerConfig layerCfg : layersCfg)
            {
                Class<?> cls = Class.forName(layerCfg.getClassName());

                Constructor<?> ctr = cls.getConstructor(int.class, int.class, int.class, int.class);

                Layer layer = (Layer) ctr.newInstance(layerCfg.getX(),
                                                      layerCfg.getY(),
                                                      layerCfg.getW(),
                                                      layerCfg.getH());
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
        for (Layer layer : layers)
        {
            layer.paint(g);
        }
        this.requestFocus();
    }

    public void setGameControl(PlayerControl playerControl)
    {
        this.addKeyListener(playerControl);
    }
    public void setGameControl(GameControl gameControl)
    {
        this.gameControl = gameControl;
    }
}

