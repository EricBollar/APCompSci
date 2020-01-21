
// Eric Bollar
// AP CS
// November 1, 2019

import acm.program.*;

public class HowRandomIsRandom extends ConsoleProgram
{
    // NOTE: your code below should use these constants; changing these
    //       constants should change the behavior of your program
    private static final int NUMRANDOMS = 1000000;
    private static final int NUMPOSSIBILITIES = 10;
    
    public void run()
    {
        int[] nums = new int[NUMRANDOMS];
        fillWithRandoms(nums);
        int[] freqs = createFreqs(nums);
        printFreqs(freqs);
    }
    
    /** Precondition: nums is initially empty
        Postcondition: each element of nums should be filled with 
                       a random number in the range [0, NUMPOSSIBILITIES)
     */
    public void fillWithRandoms(int[] nums)
    {
        for (int i = 0; i < NUMRANDOMS; i++) {
            nums[i] = (int)(Math.random() * NUMPOSSIBILITIES);
        }
    }
    
    /** Precondition: each element of nums is filled with 
                      a random number in the range [0, NUMPOSSIBILITIES)
        Postcondition: returns an array of ints representing the "frequencies"
                       of the values in nums; that is, result[0] should
                       equal the number of zeros in the array nums, result[1]
                       should equal the number of ones in the array nums, 
                       and so on
     */
    public int[] createFreqs(int[] nums)
    {
        int[] result = new int[NUMPOSSIBILITIES];
        for (int i = 0; i < NUMRANDOMS; i++) {
            result[nums[i]]++;
        }
        return result;
    }
    
    /** Precondition: freqs is filled with values representing frequencies
                      of values
        Postcondition: this method should print out something similar
                       to the following:
                       
                       Number of 0s = 104119
                       Number of 1s = 99327
                       Number of 2s = 100875
                       ...and so on...
     */
    public void printFreqs(int[] freqs)
    {
        println("Number of 0s = " + freqs[0]);
        println("Number of 1s = " + freqs[1]);
        println("Number of 2s = " + freqs[2]);
        println("Number of 3s = " + freqs[3]);
        println("Number of 4s = " + freqs[4]);
        println("Number of 5s = " + freqs[5]);
        println("Number of 6s = " + freqs[6]);
        println("Number of 7s = " + freqs[7]);
        println("Number of 8s = " + freqs[8]);
        println("Number of 9s = " + freqs[9]);
    }


}
