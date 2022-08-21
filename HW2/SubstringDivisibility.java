// HW2
// Created by Ethan Ruoff, Jason Jin, and Andreas Lin
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

class SubstringDivisibility {
    static BufferedWriter writer;
    // All primes that we need the full 3 digits for
    static int divs[][] = {{0},{0},{0},{0},{2,1},{3,3},{5,1},{7,3},{11,3},{13,3},{17,3}};
    static long total = 0;
    static int l;
    public static void main(String[] args) throws IOException{
        char[] a = args[0].toCharArray();
        long start = System.nanoTime();
        // Buffered writer is faster than print
        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Sort the digits and convert them into an arraylist of ints
        Arrays.sort(a);
        ArrayList<Integer> n = new ArrayList<Integer>();
        for(char c: a) {
            n.add(c-48);
        }

        // Call the recursive function to get the permutations
        get_permutations(n,"");

        writer.write("Sum: " + total + "\n");
        writer.flush();
        System.out.printf("Elapsed time: %.6f ms\n", (System.nanoTime() - start) / 1e6);
    }
    
    public static void get_permutations(ArrayList<Integer> nums, String s) throws IOException{
        l = s.length();
        // Check if the current last 3 digits follow the corresponding rule. If not, return and skip future recursions
        if(s.length() >= 4 && Integer.parseInt(s.substring(s.length()-divs[l][1])) % divs[l][0] != 0) {
            return;
        }
        // Base case: full permutation
        else if (nums.size() == s.length()) {
            writer.write(s + "\n");
            total += Long.parseLong(s);
            return;
        } 

        // Otherwise append number to string and call again
        int temp;
        for (int i=0; i < nums.size(); i++) {
            if (nums.get(i) == null) {
                continue;
            }
            /*
            // Skip duplicate adjacent digits
            while(i < nums.size()-1 && nums.get(i)==nums.get(i+1)) {
                i++;
            }
            */

            temp = nums.set(i, null);
            get_permutations(nums, s+temp);
            nums.set(i, temp);
        }
    }
}