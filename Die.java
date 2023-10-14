import javax.swing.*; 
import java.awt.event.*;
import java.util.*;

//class for the dice
public class Die{

private String[] letters; //the six faces of the die
private String faceUp;   //the letter that is currently face up
public JButton b; //the swing object that shows up
public int xpos; //x position on the 4x4 grid
public int ypos; //y position on the 4x4 grid
  
//the constructer
public Die(int xp, int yp, JFrame f, int x, int y, String[] inLetters)
{
  //set all the variables and create the jbutton
  xpos = xp;
  ypos = yp;
  letters = inLetters;
  faceUp = letters[0];
  b = new JButton(faceUp); 
  b.setBounds(x,y,55,55);  
  //add it to the JFrame
  f.add(b);  

  //when the die is clicked it runs the static Checker method 
  b.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){Checker.addletter(new Datainput(xpos, ypos, faceUp));}  
       });
 }

  
//method to set the appearance of the die to the correct spot after shuffling
public void update()
{
    b.setBounds(140 + 60 * xpos, 20 + 60 * ypos, 55, 55);
}

//method to randomize which side is showing
public void randomize()
  {
    int num = (int) (Math.random() * 6);
    this.faceUp = this.letters[num];
    this.b.setText(this.faceUp);
  }

//some get and set methods
public int getxpos()
  {
    return xpos;
  }

public int getypos()
  {
    return ypos;
  }
  
public String getFaceUp()
  {
    return faceUp;
  }

public void setxpos(int x)
  {
    xpos = x;
  }

public void setypos(int y)
  {
    ypos = y;
  }

//this returns all the letters on each face of the die
public String getletters()
  {
    String stuff = "";
    for(int i = 0; i < 6; i++)
      {
        stuff += letters[i];
      }
    return stuff;
  }

} 