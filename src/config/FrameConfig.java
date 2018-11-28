package config;

import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FrameConfig
{
    private final String title;
    private final int width;
    private final int height;
    private final int padding;
    private final int border;
    private final int sizeRol;

    private List<LayerConfig> layersConfig;

    public FrameConfig(Element frame, Properties PROP)
    {
        this.width = Integer.parseInt(PROP.getProperty("width"));
        this.height = Integer.parseInt(PROP.getProperty("height"));
        this.border = Integer.parseInt(PROP.getProperty("border"));
        this.padding = Integer.parseInt(PROP.getProperty("padding"));
        this.sizeRol = Integer.parseInt(PROP.getProperty("sizeRol"));
        this.title = PROP.getProperty("title");
        
        List<Element> layers = frame.elements("Layer");
        layersConfig = new ArrayList<>();
        for (Element layer : layers)
        {
            layersConfig.add(new LayerConfig(layer.attributeValue("className"),
                                             Integer.parseInt(layer.attributeValue("x")),
                                             Integer.parseInt(layer.attributeValue("y")),
                                             Integer.parseInt(layer.attributeValue("w")),
                                             Integer.parseInt(layer.attributeValue("h"))));
        }
    }

    public String getTitle()
    {
        return title;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getPadding()
    {
        return padding;
    }

    public int getBorder()
    {
        return border;
    }

    public int getSizeRol()
    {
        return sizeRol;
    }

    public List<LayerConfig> getLayersConfig()
    {
        return layersConfig;
    }
}
