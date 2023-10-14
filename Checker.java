import javax.swing.*; 
import java.awt.event.*;  
import java.util.*;
import java.io.File;
import java.io.FileWriter;

//this class does most of the work in the program using static stuff
public class Checker {
  //static arraylist to store the letters in the current guess
  private static ArrayList<Datainput> currentguess = new ArrayList<Datainput>();
  //static arraylist to put the dictionary into
  private static ArrayList<String> allwords = new ArrayList<String>();
  //static arraylist to keep track of which words have been used so they can't be repeated
  private static ArrayList<String> usedwords = new ArrayList<String>();
  //static int for the score
  private static int score;
  //show the guess
  public static JButton showguess = new JButton("");
  //show the score
  public static JButton showscore = new JButton("");
  //show the high score
  public static JButton highscore = new JButton("");


  //method to clear the current guess
  public static void clear()
  {
    currentguess.clear();
    updateshowing();
  }

  //make sure score and guessed letters are updated, update high score
  public static void updateshowing()
  {
    String word = "";
    for(Datainput d: currentguess)
      {
        word+= d.getLetter();
      }
    showguess.setText(word);
    Integer s = (Integer) score;
    showscore.setText(s.toString());

    //update the high score
    String temp = "";
    int currenthigh = 0;
    Scanner scan = new Scanner(System.in);
    try
    {
      Scanner input = new Scanner(new File("highScore.txt"));
      if(input.hasNextLine())
      {
        currenthigh = Integer.valueOf(input.nextLine());
      }
    }
    catch(Exception e)
    {
      System.out.println("An error occured.");
    }

    if(score > currenthigh)
    {
      Integer high = (Integer) score;
      highscore.setText("Hi: " + high.toString());

      //write it in
      try {
      FileWriter fw = new FileWriter("highScore.txt"); 
        fw.write(score + "");
        fw.close();
      }
      catch (Exception e) {
        System.out.println("An error occured.");
      }
    }
    else
    {
      Integer high = (Integer) currenthigh;
      highscore.setText("Hi: " + high.toString());
    }
  }
  
  //method to reset everything
  public static void reset()
  {
    currentguess.clear();
    usedwords.clear();
    score = 0;
    System.out.println("Board rerolled and score rest.");
    updateshowing();
  }
  
  public static void setup(JFrame f)
  {
    //initialize score to 0
    score = 0;

    //input dictionary
    Scanner scan = new Scanner(System.in);
    
    ArrayList<String> stuff = new  ArrayList<String>();
    try
    {
      //add each line of the dictionary to the arraylist
      Scanner input = new Scanner(new File("dictionary.txt"));
      while(input.hasNextLine())
      {
        stuff.add(input.nextLine());
      }

    }
    catch(Exception e)
    {
      System.out.println("An error occured.");
    }

    //sort the arraylist. Unnecessary since the dictionary I'm using is sorted
    for (int j = 1; j < stuff.size(); j++)
    {
        String temp = stuff.get(j);
        int possibleIndex = j;
        while(possibleIndex > 0 && temp.compareTo(stuff.get(possibleIndex - 1)) < 0)
        {
          stuff.set(possibleIndex, stuff.get(possibleIndex - 1));
          possibleIndex--;
        }
        stuff.set(possibleIndex, temp);
    }
    
    
    //set the static arraylist
    allwords = stuff;

    //set up the score and guess showing buttons
    showguess.setBounds(25,110,90,25);
    showscore.setBounds(25,80,90,25);
    highscore.setBounds(25,140,90,25);
    f.add(highscore);
    f.add(showguess);
    f.add(showscore);
    updateshowing();
  }

  //method to add a letter to the current guess
  public static void addletter(Datainput input)
  {
    //every die is a valid first letter
    if(currentguess.size() == 0)
    {
      currentguess.add(input);
    }
    else
    {
      //Set x and y from current and previous guess
      int prevx = currentguess.get(currentguess.size() -1).getxpos();
      int prevy = currentguess.get(currentguess.size() -1).getypos();
      int currx = input.getxpos();
      int curry = input.getypos();
      //Make sure the same die isn't being used twice
      Boolean duplicate = false;
      for(Datainput d : currentguess)
      {
          if(d.getxpos() == currx && d.getypos() == curry)
          {
            duplicate = true;
          }
      }
      
      //Check to make sure the move is adjacent or diagonal. Distance horizontally and vertically has to be 1 or less (this would allow the same die to be clicked twice if not for the fact that it has already been checked to not be a duplicate)
      if(!duplicate && (Math.abs(currx - prevx) <= 1 && Math.abs(curry - prevy) <= 1))
      {
        currentguess.add(input);
      }
      else
      {
        //if it isn't a valid move:
        System.out.println("Invalid move. Guess cleared.");
        currentguess.clear();
      }
    }
    updateshowing();
  }

  //method to guess a word
  public static void enter()
  {
    //setup the word from the current guess arraylist of letters
    String word = "";
    for(Datainput d: currentguess)
      {
        word+= d.getLetter();
      }
    word = word.toLowerCase();
    
    //spellcheck using binary search
    boolean realword = false;
    int left = 0;
    int right = allwords.size() - 1;
    while (left <= right)
    {
      int middle = (left + right) / 2;
      if (word.compareTo(allwords.get(middle)) < 0)
      {
         right = middle - 1;
      }
      else if(word.compareTo(allwords.get(middle)) > 0)
      {
         left = middle + 1;
      }
      else
      {
        realword = true;
        break;
      }
    }

    //make sure it's not a duplicate
    Boolean repeat = false;
    for(String s : usedwords)
    {
        if(word.equals(s))
        {
          repeat = true;
        }
    }

    //if it's a valid word add the correspoding value to the score
    if(!repeat && realword && currentguess.size() >= 3)
    {
      usedwords.add(word);
      if(word.length() <= 4)
      {
        score += 1;
      }
      else if(word.length() == 5)
      {
        score += 2;
      }
      else if(word.length() == 6)
      {
        score += 3;
      }
      else if(word.length() == 7)
      {
        score += 5;
      }
      else
      {
        score += 11;
      }
      //print out the stuff using this method
      Checker.print();
    }
    else if(!repeat)
    {
      System.out.println("Not a valid word.");
    }
    else
    {
      System.out.println("Already found.");
    }
    //reset the current guess
    currentguess.clear();
    updateshowing();
  }

  //method to print stuff
  public static void print()
  {
    //print out the word
    for(Datainput d : currentguess)
      {
        System.out.print(d.getLetter());
      }
    //print out the score
    System.out.println("\nYour score is: " + score);
  }

}