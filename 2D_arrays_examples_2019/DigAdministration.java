import java.util.ArrayList;
 
public class DigAdministration
{
    public static void main(String[] args)
    {
        System.out.println("FIRST SITE:");
        ArrayList<Archaeologist> group1 = new ArrayList<Archaeologist>();
        group1.add(new Archaeologist("Alexa", "Gators", 56432));
        group1.add(new Archaeologist("Cody", "Iowa", 71650));
        group1.add(new Archaeologist("Rayan", "Boston", 57735));
        group1.add(new Archaeologist("Victor", "Gators", 34902));
        group1.add(new Archaeologist("James", "Iowa", 63028));
        group1.add(new Archaeologist("Ben", "Bulldogs", 11853));
        group1.add(new Archaeologist("Aidan", "Gators", 44822));
        group1.add(new Archaeologist("Ben", "Gators", 12693));
        Site site1 = new Site(group1, 3, 5);
        site1.printSiteInfo();

        System.out.println();
        System.out.println("SECOND SITE:");        
        ArrayList<Archaeologist> group2 = new ArrayList<Archaeologist>();
        group2.add(new Archaeologist("Samuel", "Gators", 65423));
        group2.add(new Archaeologist("Eliza", "Iowa", 17549));
        group2.add(new Archaeologist("Colin", "Boston", 72239));
        group2.add(new Archaeologist("Lauren", "Gators", 98155));
        group2.add(new Archaeologist("Walker", "Bulldogs", 12808));
        group2.add(new Archaeologist("Ella", "Gators", 28192));
        group2.add(new Archaeologist("Ben", "Gators", 74347));
        group2.add(new Archaeologist("Tommy", "Iowa", 14013));
        group2.add(new Archaeologist("Larsen", "Gators", 52681));
        group2.add(new Archaeologist("Paul", "Gators", 19206));
        group2.add(new Archaeologist("Anikait", "Gators", 48460));
        group2.add(new Archaeologist("Jai", "Boston", 93277));
        group2.add(new Archaeologist("Bridget", "Gators", 41932));
        group2.add(new Archaeologist("Nate", "Gators", 36998));
        group2.add(new Archaeologist("Devin", "Bulldogs", 30078));
        group2.add(new Archaeologist("Kristine", "Gators", 22495));
        group2.add(new Archaeologist("Ajay", "Gators", 30236));
        group2.add(new Archaeologist("Matt", "Boston", 68204));

        Site site2 = new Site(group2, 4, 2);
        site2.printSiteInfo();

        System.out.println();
        System.out.println("SECOND SITE AFTER REMOVING \"Iowa\":");
        int removed = site2.clearSomeQuadrants("Iowa");
        site2.printSiteInfo();
        System.out.println("Number removed = " + removed);
    }
}
