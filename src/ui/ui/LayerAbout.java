package ui.ui;

import ui.Layer;

import java.awt.*;

public class LayerAbout extends Layer
{
  public LayerAbout(int x, int y, int w, int h)
  {
    super(x,y,w,h);
  }
  public void paint(Graphics g)
  {
    this.createBlock(g);
  }


}
