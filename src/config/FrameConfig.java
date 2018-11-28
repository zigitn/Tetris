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

    public FrameConfig(Element frame, Properties systemSettings)
    {
        this.width = Integer.parseInt(frame.attributeValue("width"));
        this.height = Integer.parseInt(frame.attributeValue("height"));
        this.border = Integer.parseInt(frame.attributeValue("border"));
        this.padding = Integer.parseInt(frame.attributeValue("padding"));
        this.sizeRol = Integer.parseInt(frame.attributeValue("sizeRol"));
        this.title = frame.attributeValue("title");
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
