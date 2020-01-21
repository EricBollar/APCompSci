import java.awt.*;
import java.util.*;
import acm.program.*;

public class ParticlesProgram extends Program
{
    
    // Eric Bollar
    // AP CS, Period A
    // 1/16/20
    
    //add constants for particle types here
    public static final int EMPTY = 0; /** Empty is empty. **/
    public static final int METAL = 1; /** Metal is motionless, except when there is a magnet present. **/
    public static final int SAND = 2; /** Sand falls straight down. **/
    public static final int WATER = 3; /** Water moves randomly sideways, but falls downwards. **/
    public static final int ICE = 4; /** Ice is motionless for a moment, then turns into water. **/
    public static final int FIRE = 5; /** [EASY] Fire stays around player's cursor while mouse is held. Can turn metal -> heatedmetal **/
    public static final int HEATEDMETAL = 6; /** [MED] Heated metal can heat metal around it. Turns back into metal ~ 2 secs. Can turn water into steam. **/
    public static final int STEAM = 7; /** [EASY] Flies upwards for a moment then turns into water. **/
    public static final int MAGNET = 8; /** [HARD] Attracts metal and heated metal to its position. **/
    public static final int MAGNETEDGE = 9; /** [EASY] For aesthetic **/

    //do not add any more private instance variables
    private Particle[][] grid;
    private ParticlesDisplay display;

    public void init()
    {
        initVariables(120, 80);
    }    

    public void initVariables(int numRows, int numCols)
    {
        String[] names;
        names = new String[9];
        names[EMPTY] = "Empty";
        names[METAL] = "Metal";
        names[SAND] = "Sand";
        names[WATER] = "Water";
        names[ICE] = "Ice";
        names[FIRE] = "Fire";
        names[HEATEDMETAL] = "Hot Metal";
        names[STEAM] = "Steam";
        names[MAGNET] = "Magnet";

        display = new ParticlesDisplay("Particles Game", 
            numRows, numCols, names);
        // initialize the grid here (task 0.1)
        grid = new Particle[numRows][numCols];
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                grid[r][c] = new Empty();
            }
        }
    }

    //called when the user clicks on a location using the given particleType
    private void locationClicked(int row, int col, int particleType)
    {
        // finish this cascading if (task 0.2)
        if (particleType == EMPTY)
            grid[row][col] = new Empty();
        else if (particleType == METAL)
            grid[row][col] = new Metal();          
        else if (particleType == SAND)
            grid[row][col] = new Sand();  
        else if (particleType == WATER)
            grid[row][col] = new Water(); 
        else if (particleType == ICE)
            grid[row][col] = new Ice(); 
        else if (particleType == FIRE)
            grid[row][col] = new Fire(); 
        else if (particleType == HEATEDMETAL)
            grid[row][col] = new HeatedMetal();
        else if (particleType == STEAM)
            grid[row][col] = new Steam();
        else if (particleType == MAGNET)
            grid[row][col] = new Magnet();

    }

    //called repeatedly.
    //causes one random particle to maybe do something.
    public void step()
    {
        int row = (int)(Math.random() * grid.length);
        int col = (int)(Math.random() * grid[0].length);
        Particle particle = grid[row][col];

        if (particle.getType() == SAND) {
            tryToMoveDown(row, col, particle, true);
        }
        else if (particle.getType() == WATER) {
            if (row != grid.length - 1 && grid[row+1][col].getType() == HEATEDMETAL) {
                Water w = (Water)particle;
                if (w.getCounter() >= 20) {
                    grid[row][col] = new Steam();
                } else {
                    w.incrementCounter();
                }
            } else {
                int direction = (int)(Math.random() * 3);
                if (direction == 0) {
                    tryToMoveDown(row, col, particle, false);
                } else if (direction == 1) {
                    tryToMoveLeft(row, col, particle, false);
                } else {
                    tryToMoveRight(row, col, particle, false);
                }
            }
        }
        else if (particle.getType() == STEAM) {
            Steam s = (Steam)particle;
            if (s.getCounter() < 100) {
                int direction = (int)(Math.random() * 3);
                if (direction == 0) {
                    tryToMoveUp(row, col, particle, true);
                } else if (direction == 1) {
                    tryToMoveLeft(row, col, particle, true);
                } else {
                    tryToMoveRight(row, col, particle, true);
                }
                s.incrementCounter();
            } else {
                grid[row][col] = new Water();
            }
        }
        else if (particle.getType() == ICE) {
            Ice ice = (Ice)particle;
            if (ice.getCounter() == 500) {
                grid[row][col] = new Water();
            } else {
                ice.incrementCounter();
            }
        }
        else if (particle.getType() == FIRE) {
            Fire fire = (Fire)particle;
            if (fire.getCounter() < 10) {
                int direction = (int)(Math.random() * 3);
                if (direction == 0) {
                    tryToMoveUp(row, col, particle, false);
                } else if (direction == 1) {
                    tryToMoveLeft(row, col, particle, false);
                } else {
                    tryToMoveRight(row, col, particle, false);
                }
                fire.incrementCounter();

                heatMetalRight(row, col);
                heatMetalLeft(row, col);
                heatMetalUp(row, col);
            } else {
                grid[row][col] = new Empty();
            }
        }
        else if (particle.getType() == HEATEDMETAL) {
            HeatedMetal h = (HeatedMetal)particle;
            if (h.getCounter() > 50) {
                grid[row][col] = new Metal();
            } else {
                h.incrementCounter();
            }

            heatMetalRight(row, col);
            heatMetalLeft(row, col);
            heatMetalUp(row, col);
        }
        else if (particle.getType() == MAGNET) {
            Magnet m = (Magnet)particle;

            if (!m.getCreated()) {
                createMagnetEdges(row, col);
            } else {
                m.setCreated();
            }

            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[r].length; c++) {
                    if (grid[r][c].getType() == METAL || grid[r][c].getType() == HEATEDMETAL) {
                        moveTowardsMagnet(r, c, row, col);

                        //moves metal randomly around magnet to evenly distribute
                        int direction = (int)(Math.random() * 8);
                        if (direction == 0) {
                            if (tryToMoveRight(r, c, grid[r][c], true)) {
                                r++;
                            }
                        } else if (direction == 1) {
                            tryToMoveLeft(r, c, grid[r][c], true);
                        }else if (direction == 2) {
                            tryToMoveUp(r, c, grid[r][c], true);
                        } else if (direction == 3) {
                            tryToMoveDown(r, c, grid[r][c], true);
                        }
                    }
                }
            }
        }
    }

    public void heatMetalUp(int row, int col) {
        if (row != 0 && grid[row-1][col].getType() == METAL) {
            Metal metal = (Metal)grid[row-1][col];
            if (metal.getCounter() >= 50) {
                grid[row-1][col] = new HeatedMetal();
            } else {
                metal.incrementCounter();
            }
        }
    }

    public void heatMetalRight(int row, int col) {
        if (col != grid[0].length - 1 && grid[row][col+1].getType() == METAL) {
            Metal metal = (Metal)grid[row][col+1];
            if (metal.getCounter() >= 50) {
                grid[row][col+1] = new HeatedMetal();
            } else {
                metal.incrementCounter();
            }
        }
    }

    public void heatMetalLeft(int row, int col) {
        if (col != 0 && grid[row][col-1].getType() == METAL) {
            Metal metal = (Metal)grid[row][col-1];
            if (metal.getCounter() >= 50) {
                grid[row][col-1] = new HeatedMetal();
            } else {
                metal.incrementCounter();
            }
        }
    }

    /** Creates the 8 MagnetEdges surrounding Magnet Particle **/
    public void createMagnetEdges(int row, int col) {
        if (col != grid[0].length - 1) {
            grid[row][col+1] = new MagnetEdge();
            if (row != 0) {
                grid[row-1][col+1] = new MagnetEdge();
            }
            if (row != grid.length - 1) {
                grid[row+1][col+1] = new MagnetEdge();
            }
        }
        if (col != 0) {
            grid[row][col-1] = new MagnetEdge();
            if (row != 0) {
                grid[row-1][col-1] = new MagnetEdge();
            }
            if (row != grid.length - 1) {
                grid[row+1][col-1] = new MagnetEdge();
            }
        }
        if (row != grid.length - 1) {
            grid[row + 1][col] = new MagnetEdge();
        }
        if (row != 0) {
            grid[row - 1][col] = new MagnetEdge();
        }
    }

    /** Moves a Metal/HeatedMetal Particle towards a given magnet **/
    public void moveTowardsMagnet(int metalR, int metalC, int magnetR, int magnetC) {
        // m = (y1 - y2)/(x1-x2)
        double slope = (magnetR - metalR) * 1.0 / (magnetC - metalC);
        // b = y - mx
        double yInt = magnetR - slope * magnetC;

        int nextX = metalC; // next input in linear function
        if (metalC < magnetC) {
            nextX = metalC + 1;
        } else if (metalC > magnetC){
            nextX = metalC - 1;
        }

        int nextY = (int)(slope * nextX + yInt + 0.5); // use int for pixel
        if (nextY == 0) {
            if (metalR < magnetR) {
                tryToMoveDown(metalR, metalC, grid[metalR][metalC], true);
            } else {
                tryToMoveUp(metalR, metalC, grid[metalR][metalC], true);
            }
        }else if (grid[nextY][nextX].getType() == EMPTY) {
            grid[metalR][metalC] = new Empty();
            grid[nextY][nextX] = new Metal();
        } else {
            if (magnetC < metalC) {
                tryToMoveLeft(metalR, metalC, grid[metalR][metalC], true);
            } else if (magnetC > metalC) {
                tryToMoveRight(metalR, metalC, grid[metalR][metalC], true);
            }
            if (magnetR < metalR) {
                tryToMoveUp(metalR, metalC, grid[metalR][metalC], true);
            } else if (magnetR > metalR) {
                tryToMoveDown(metalR, metalC, grid[metalR][metalC], true);
            }
        }
    }

    public boolean tryToMoveDown(int row, int col, Particle particle, boolean canGoThroughWater) {
        if (row != grid.length - 1 && (grid[row+1][col].getType() == EMPTY || canGoThroughWater && grid[row+1][col].getType() == WATER)) {
            Particle below = grid[row+1][col];
            grid[row+1][col] = particle;
            grid[row][col] = below;
            return true;
        }
        return false;
    }

    public boolean tryToMoveUp(int row, int col, Particle particle, boolean canGoThroughWater) {
        if (row != 0 && (grid[row-1][col].getType() == EMPTY || canGoThroughWater && grid[row-1][col].getType() == WATER)) {
            Particle below = grid[row-1][col];
            grid[row-1][col] = particle;
            grid[row][col] = below;
            return true;
        }
        return false;
    }

    public boolean tryToMoveRight(int row, int col, Particle particle, boolean canGoThroughWater) {
        if (col != grid[row].length-1 && grid[row][col+1].getType() == EMPTY || canGoThroughWater && grid[row][col+1].getType() == WATER) {
            Particle right = grid[row][col+1];
            grid[row][col+1] = particle;
            grid[row][col] = right;
            return true;
        }
        return false;
    }

    public boolean tryToMoveLeft(int row, int col, Particle particle, boolean canGoThroughWater) {
        if (col != 0 && grid[row][col-1].getType() == EMPTY || canGoThroughWater && grid[row][col-1].getType() == WATER) {
            Particle left = grid[row][col-1];
            grid[row][col-1] = particle;
            grid[row][col] = left;
            return true;
        }
        return false;
    }

    //copies each element of grid into the display (don't modify this)
    public void updateDisplay()
    {
        for (int r=0; r<grid.length; r++)
            for (int c=0; c<grid[0].length; c++)
                display.setColor(r, c, grid[r][c].getColor());
    }

    // repeatedly calls step and updates the display
    // (don't modify this)
    public void run()
    {
        while (true)
        {
            for (int i = 0; i < display.getSpeed(); i++)
                step();
            updateDisplay();
            display.repaint();
            display.pause(1);  //wait for redrawing and for mouse
            int[] mouseLoc = display.getMouseLocation();
            if (mouseLoc != null)  //test if mouse clicked
                locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
        }
    }
}
