
public class SmurfVillage
{
    
    // Eric Bollar
    // AP CS, Period A
    // 22 January 2019
    
    public static void main(String[] args)
    {
        Picture village = new Picture("images/" + "village.jpg");
        Picture smurf = new Picture("images/" + "smurfA.png");

        // creates a border of smurfs around village
        Pixel[][] p = village.getPixels2D();
        for (int r = 0; r < p.length - smurf.getPixels2D().length; r += smurf.getPixels2D().length) {
            for (int c = 0; c < p[0].length - smurf.getPixels2D()[0].length; c += smurf.getPixels2D()[0].length) {
                boolean place = false;
                if (r == 0 || c == 0) {
                    place = true;
                }
                if (p[0].length - (c + smurf.getPixels2D()[0].length) < smurf.getPixels2D()[0].length) {
                    c = p[0].length - (smurf.getPixels2D()[0].length);
                    village.insert(smurf, r, c - smurf.getPixels2D()[0].length); // this prevents a large gap forming
                    place = true;
                }
                if (p.length - (r + smurf.getPixels2D().length) < smurf.getPixels2D().length) {
                    r = p.length - (smurf.getPixels2D().length);
                    if (place) {
                        village.insert(smurf, r - smurf.getPixels2D().length, c); // this prevents a large gap forming
                    }
                    place = true;
                }
                if (place) {
                    village.insert(smurf, r, c);
                }
            }
        }

        village.show();
    }
}