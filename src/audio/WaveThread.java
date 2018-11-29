package audio;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class WaveThread extends Thread
{
    private String filename;
    private Player player;

    public WaveThread(String filename)
    {
        this.filename = filename;
    }

    public void run()
    {
        try
        {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream("./media/audio/"+filename+".mp3"));
            player = new Player(buffer);
            player.play();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}