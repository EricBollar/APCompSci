
public class AuthorSignature
{
    // declare private instance variables here
    private String authorName;
    private double avgWordLength;
    private double differentWordRatio;
    private double hapaxRatio;
    private double avgWordsPerSentence;
    private double avgPhrasesPerSentence;
    
    public AuthorSignature(String authorName, double avgWordLength, 
    double differentWordRatio, double hapaxRatio, double avgWordsPerSentence, double avgPhrasesPerSentence )
    {
        this.authorName = authorName;
        this.avgWordLength = avgWordLength;
        this.differentWordRatio = differentWordRatio;
        this.hapaxRatio = hapaxRatio;
        this.avgWordsPerSentence = avgWordsPerSentence;
        this.avgPhrasesPerSentence = avgPhrasesPerSentence;
    }
    
    public String getAuthorName() {
        return authorName;
    }
    
    public double getAverageWordLength() {
        return avgWordLength;
    }
    
    public double getDifferentWordRatio() {
        return differentWordRatio;
    }
    
    public double getHapaxRatio() {
        return hapaxRatio;
    }
    
    public double getAverageWordsPerSentence() {
        return avgWordsPerSentence;
    }
    
    public double getAveragePhrasesPerSentence() {
        return avgPhrasesPerSentence;
    }
}
