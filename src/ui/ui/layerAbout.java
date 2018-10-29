package ui.ui;

import ui.layer;

import java.awt.*;

public class layerAbout extends layer
{
  public layerAbout(int x, int y, int w, int h)
  {
    super(x,y,w,h);
  }
  public void paint(Graphics g)
  {
    this.createBlock(g);
  }


}
