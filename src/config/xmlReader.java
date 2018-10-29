package config;

public class xmlReader
{
    private static gameConfig GAME_CONFIG;

    static
    {
        try
        {
            GAME_CONFIG = new gameConfig();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static gameConfig getGameConfig()
    {
        return GAME_CONFIG;
    }
}
