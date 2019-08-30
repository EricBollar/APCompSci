
import acm.program.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

// Eric Bollar
// APCS
// August 30, 2019

public class BaseballProgram extends ConsoleProgram
{

    public void run()
    {
        println("Let's play ball...");
        Random rnd = new Random();
        BaseballInning inning;
        inning = new BaseballInning(0);
        int strikes = 0;
        
        while (true) {
            rnd = new Random();
            int swing = rnd.nextInt(7); // 0 - 6
        
            switch (swing) {
                case 0: inning.batterBaseHit(4);
                        println("Home run...");
                        strikes = 0;
                        break;
                case 1: inning.batterBaseHit(3);
                        println("Triple...");
                        strikes = 0;
                        break;
                case 2: inning.batterBaseHit(2);
                        println("Double...");
                        strikes = 0;
                        break;
                case 3: inning.batterBaseHit(1);
                        println("Single...");
                        strikes = 0;
                        break;
                case 4: strikes++;
                        break;
                case 5: strikes++;
                        break;
                case 6: strikes++;
                        break;
            }
            
            if (strikes >= 3) {
                inning.batterOut();
                println("Strikeout!");
                strikes = 0;
            }
            
            println("Outs: " + inning.getOuts());
            println("Runs: " + inning.getRuns());
            println("Runners: " + inning.getBaseRunners());
            println("Strike " + strikes);
            
            
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (java.lang.InterruptedException e) {
                println("Error!");
            }
            
            println("-----------------------------------------------------------");
        }
    }


}
