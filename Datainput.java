import javax.swing.*; 
import java.awt.event.*;  
import java.util.*;

//simple class to have each move be associated with the position of the die and the letter
public class Datainput{
  //each object has x and y position and the letter
  String letter;
  int xpos;
  int ypos;

  //constructer
  public Datainput(int xp, int yp, String l)
  {
    xpos = xp;
    ypos = yp;
    letter = l;
  }

  //get methods
  public String getLetter()
  {
    return letter;
  }

  public int getxpos()
  {
    return xpos;
  }

  public int getypos()
  {
    return ypos;
  }
  
}
