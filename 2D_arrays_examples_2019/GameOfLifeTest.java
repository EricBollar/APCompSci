import javax.swing.JFrame;

/**
   This program tests the GameOfLifeFrame.
*/
public class GameOfLifeTest
{
   public static void main(String[] args)
   { 
      JFrame appFrame = new GameOfLifeFrame();
      appFrame.setTitle("The Game of Life");
      appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      appFrame.show();
   }
}
