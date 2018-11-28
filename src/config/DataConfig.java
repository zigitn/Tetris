package config;

import org.dom4j.Element;

import java.util.Properties;

public class DataConfig
{
    private final DataInterfaceConfig dataBase;
    private final DataInterfaceConfig dataDisk;

    public DataConfig(Element data, Properties systemSettings)
    {
        dataBase = new DataInterfaceConfig(data.element("dataBase"));
        dataDisk = new DataInterfaceConfig(data.element("dataDisk"));

    }

    public DataInterfaceConfig getDataBase()
    {
        return dataBase;
    }

    public DataInterfaceConfig getDataDisk()
    {
        return dataDisk;
    }
}
