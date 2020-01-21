import java.util.ArrayList;

public class Site
{
    private Archaeologist[][] quadrants;

    public Site(ArrayList<Archaeologist> people, int rows, int cols)
    {
        // to be written in part (a)
        quadrants = new Archaeologist[rows][cols];

        int index = 0;
        for (int c = 0; c < quadrants[0].length; c++) {
            for (int r = 0; r < quadrants.length; r++) {
                if (index < people.size()) {
                    quadrants[r][c] = people.get(index);
                    index++;
                }
            }
        }
    }

    public int clearSomeQuadrants(String affiliation)
    {
        // to be written in part (b)
        int counter = 0;
        for (int r = 0; r < quadrants.length; r++) {
            for (int c = 0; c < quadrants[0].length; c++) {
                if (quadrants[r][c] != null) {
                    if (quadrants[r][c].getAffiliation().equals(affiliation)) {
                        quadrants[r][c] = null;
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    public void printSiteInfo()
    {
        for (int r=0; r<quadrants.length; r++)
        {
            for (int c=0; c<quadrants[0].length; c++)
            {
                if (quadrants[r][c]!=null)
                    System.out.print(quadrants[r][c].getIdNum() + " ");
                else
                    System.out.print("-----" + " ");
            }
            System.out.println();
        }
    }

}
