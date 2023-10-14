import javax.swing.*; 
import java.awt.event.*;
import java.util.*;

//the board. contains the 16 dice, runs the reroll method
public class Board{

  //The 16 dice are stored in this arraylist
  private static ArrayList<Die> dice;

  public static void construct(JFrame f){
    //set up JFrame
    f.setSize(410,305);  
    f.setLayout(null); 
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //initialize the ArrayList of Dice
    dice = new ArrayList<Die>();
    
    //This is all the letters for the dice. Each row is one die
    String[][] dieLetters = {{"A","A","E","E","G","N"},
                             {"A","B","B","J","O","O"},
                             {"A","C","H","O","P","S"},
                             {"A","F","F","K","P","S"},
                             {"A","O","O","T","T","W"},
                             {"C","I","M","O","T","U"},
                             {"D","E","I","L","R","X"},
                             {"D","E","L","R","V","Y"},
                             {"D","I","S","T","T","Y"},
                             {"E","E","G","H","N","W"},
                             {"E","E","I","N","S","U"},
                             {"E","H","R","T","V","W"},
                             {"E","I","O","S","S","T"},
                             {"E","L","R","T","T","Y"},
                             {"H","I","M","N","U","QU"},
                             {"H","L","N","N","R","Z"},
                            };

    //Using that data to create the dice
    int row = 0;
    //loop for the rows
    for(int y = 0; y < 4; y++)
      {
        //loop for the columns
        for(int x = 0; x < 4; x++)
        {
          //Make an empty string array to put the die's letters in
          String[] currentletters = new String[6];
          //input the correct row of the 2d array
          for(int j = 0; j < 6; j++)
            {
              currentletters[j] = dieLetters[row][j];
            }
          //intialize a new die and put it into the arraylist
          dice.add(new Die(x, y, f, 140 + 60 * x, 20 + 60 * y, currentletters));
          //increment row to move on to the next row in the 2d array
          row++;
        }
      }
  }


  //method to reroll all the dice
  public static void reroll()
  {
    //changing the position of the dice
    //temp array to put the dice into randomly
    Die[] temp = new Die[16];
    int count = 0;
    int num = 0;
    while(count <= 15)
      {
        //until all 16 have been used, each die is put into a random spot in temp that doesn't already have a die
        num = (int) (Math.random() * 16);
        if(temp[num] == null)
        {
          
          temp[num] = dice.get(count);
          
          //setting x position to the new position
          if(num % 4 == 0)
          {
            temp[num].setxpos(0);
          }
          else if((num - 1) % 4 == 0)
          {
            temp[num].setxpos(1);
          }
          else if((num - 2) % 4 == 0)
          {
            temp[num].setxpos(2);
          }
          else
          {
            temp[num].setxpos(3);
          }
          
          //setting y position to the new position
          if(num <= 3)
          {
            temp[num].setypos(0);
          }
          else if(num <= 7)
          {
            temp[num].setypos(1);
          }
          else if(num <= 11)
          {
            temp[num].setypos(2);
          }
          else
          {
            temp[num].setypos(3);
          }

          //incrementing count to move on to the next die
          count++;
        }
      }
    for(int i = 0; i < 16; i++)
      {
        dice.set(i, temp[i]);
      }

    //updating so they show up in the new position, then randomizing which face is up
    for(Die d : dice)
      {
        d.update();
        d.randomize();
      }
  }
}