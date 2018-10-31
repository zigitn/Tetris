package config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;

public class gameConfig
{
    private int width;
    private int height;
    private int RIM;
    private int padding;
    private String title;
    private List<LayerConfig> layersConfig;

    public gameConfig() throws Exception
    {
        SAXReader reader = new SAXReader();
        Document doc = reader.read("./config/config.xml");
        Element game = doc.getRootElement();
        this.setUiConfig(game.element("frame"));
        this.setSystemConfig(game.element("system"));
        this.setDataConfig(game.element("data"));
    }

    private void setUiConfig(Element frame)
    {
        this.width = Integer.parseInt(frame.attributeValue("width"));
        this.height = Integer.parseInt(frame.attributeValue("height"));
        this.RIM = Integer.parseInt(frame.attributeValue("RIM"));
        this.padding = Integer.parseInt(frame.attributeValue("PADDING"));
        this.title=frame.attributeValue("title");


        List<Element> layers = frame.elements("Layer");
        layersConfig = new ArrayList<>();
        for (Element layer : layers)
        {
            LayerConfig lc = new LayerConfig(layer.attributeValue("className"),
                                             Integer.parseInt(layer.attributeValue("x")),
                                             Integer.parseInt(layer.attributeValue("y")),
                                             Integer.parseInt(layer.attributeValue("w")),
                                             Integer.parseInt(layer.attributeValue("h")));
            layersConfig.add(lc);
        }

    }

    private void setSystemConfig(Element system)
    {
    }

    private void setDataConfig(Element data)
    {
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

    public int getRIM()
    {
        return RIM;
    }

    public int getPadding()
    {
        return padding;
    }

    public List<LayerConfig> getLayersConfig()
    {
        return layersConfig;
    }
}
