package Util;

import javax.swing.*;
import java.awt.*;

public class FrameUtil
{
    public static void setFrameToCenter(JFrame frame)
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        frame.setLocation(screen.width - frame.getWidth() >> 1,
                          (screen.height - frame.getHeight() >> 1) - 50);

    }
}
