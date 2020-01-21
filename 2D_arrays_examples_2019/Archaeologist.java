

public class Archaeologist
{
    private String n;
    private String a;
    private int num;

    public Archaeologist(String name, String affiliation, int idNum)
    {
        n=name;
        a=affiliation;
        num=idNum;
    }
    
    public String getName() {return n;}
    public String getAffiliation() {return a;}
    public int getIdNum() {return num;}


}
