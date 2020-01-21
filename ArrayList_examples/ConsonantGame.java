
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import java.lang.*;

public class ConsonantGame extends GraphicsProgram
{
    private ArrayList<GLabel> labels;
    private RandomWordGenerator generator;
    private int turns;

    public void run()
    {
        labels = new ArrayList<GLabel>();
        generator = new RandomWordGenerator();
        turns = 0;
        for (int i = 0; i < 25; i++) {
            GLabel label = getRandomLabel();
            labels.add(label);
        }

        // fill up labels with 25 random labels here
        // play the game and tell me how many turns it took
        // to win here

    }

    public void keyPressed(KeyEvent event)
    {
        String letter = KeyEvent.getKeyText(event.getKeyCode()).toUpperCase();  
        // handle one key press here
        String vowels = "AEIOU";
        if (vowels.indexOf(letter) == -1 && Character.isLetter(letter.charAt(0))) {
            for (int i = 0; i < labels.size(); i++) {
                if (labels.get(i).getText().indexOf(letter) != -1) {
                    remove(labels.get(i));
                    labels.remove(i);
                    i--;
                }
            }

            turns++;
            if (turns % 3 == 0) {
                for (int i = 0; i < 5; i++) {
                    GLabel label = getRandomLabel();
                    labels.add(label);
                }
            }
        }
    }

    public GLabel getRandomLabel()
    {
        /* Return a GLabel, with a random word, random
        location, random color, and random font size.
        It should be added 
         */
        GLabel result = new GLabel(generator.getRandomWord(), (int)(Math.random() * getWidth()), (int)(Math.random() * getHeight()));
        Color color = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
        result.setColor(color);
        int size = (int)(Math.random() * 53) + 12;
        result.setFont("*-*-"+size);
        add(result);
        return result;
    }

}
