package ui.ui;

import ui.layer;

import javax.swing.*;
import java.awt.*;

public class layerDataBase extends layer
{
  private static Image IMG_DB = new ImageIcon("media/string/db.png").getImage();


  public layerDataBase(int x, int y, int w, int h)
  {
    super(x, y, w, h);
  }

  public void paint(Graphics g)
  {
    this.createBlock(g);
    g.drawImage(IMG_DB, this.x, this.y + PADDING, null);
  }


}
