

public class WaterPuzzle
{
    private int jugA; // 5 gallon
    private int jugB; // 3 gallon
    
    public WaterPuzzle()
    {
        jugA = 0;
        jugB = 0;
    }
    
    public void fill(String jug) {
        if (jug.equals("A")) {
            jugA = 5;
        } else {
            jugB = 3;
        }
    }
    
    public void empty(String jug) {
        if (jug.equals("A")) {
            jugA = 0;
        } else {
            jugB = 0;
        }
    }
    
    public void pourFromAToB() {
        int spaceInB = 3 - jugB;
        if (jugA <= spaceInB) {
            jugB += jugA;
            jugA = 0;
        } else {
            jugB = 3;
            jugA -= spaceInB;
        }
    }
    
    public void pourFromBToA() {
        int spaceInA = 5 - jugA;
        if (jugB <= spaceInA) {
            jugA += jugB;
            jugB = 0;
        } else {
            jugA = 5;
            jugB -= spaceInA;
        }
    }
    
    public int getAmount(String jug) {
        if (jug.equals("A")) {
            return jugA;
        } else {
            return jugB;
        }
    }

}
