package ui.ui;

import ui.Layer;

import java.awt.*;

public class LayerButton extends Layer
{
  public LayerButton(int x, int y, int w, int h)
  {
    super(x,y,w,h);
  }
  public void paint(Graphics g)
  {
    this.createBlock(g);
  }


}
