import javax.swing.*;   
import java.awt.event.*;  

//the main sets up the board the the other buttons (clear, reroll, enter)
public class Main {  

  public static void main(String[] args) {

  //create the board, randomize the dice, and run checker setup (which reads in the dictionary from the file)   
  JFrame f=new JFrame("Boggle");
  Board.construct(f);
  Board.reroll();
  Checker.setup(f);

  //Enter button
  JButton e = new JButton("Enter");
  e.setBounds(25,180,90,75); 
  f.add(e);
  e.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){Checker.enter();}  
       });

  //Restart button
  JButton r = new JButton("Restart");
  r.setBounds(25,20,90,25); 
  f.add(r);
  r.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent r){Board.reroll();}  
       });
  r.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent r){Checker.reset();}  
       });

  //Clear button
  JButton cl = new JButton("Clear");
  cl.setBounds(25,50,90,25);
  f.add(cl);
  cl.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent cl){Checker.clear();}  
       });

  //Make everything show
  f.setVisible(true);
    
  /*geting height/width (one time only) 400 x 275
    int height = (int) f.getContentPane().getSize().getHeight();
    System.out.println(height);
    int w = (int) f.getContentPane().getSize().getWidth();
    System.out.println(w);
    */
 } 
}

