/**
 * SubstringDivisibility
 */
public class SubstringDivisibility {

    public static void permutation(String str, int si, int li) {

        if (si == li) {
            if (checkCondition(str) == true) {
                System.out.println(str);
            }
        } else {

            for (int i = si; i <= li; i++) {
                str = swapChar(str, si, i);
                permutation(str, si + 1, li);
                str = swapChar(str, si, i);
            }
        }

    }

    public static String swapChar(String str, int i, int j) {
        char temp;

        // converts string into character array
        char[] chArray = str.toCharArray();
        temp = chArray[i];
        chArray[i] = chArray[j];
        chArray[j] = temp;

        // returns string after swapping
        return String.valueOf(chArray);
    }

    public static boolean checkCondition(String output) {

        int sLength = output.length();
        int count = 4;
        int index = 0;
        int[] primeNumbers = { 2, 3, 5, 7, 11, 13, 17 };

        while (count <= 10) {

            if (index == 7) {
                index = 0;
            }
            if ((count) > sLength) {
                break;
            }

            if (Integer.parseInt(output.substring(count - 3, count)) % primeNumbers[index++] != 0) {
                return false;
            }
            count++;

        }
        return true;
    }

    public static void main(String[] args) {

        // Start measuring time
        long start = System.nanoTime();

        if (args.length != 1) {
            System.err.println("Invalid: Enter command line argument");
            return;
        }

        // Commented to reduce time

        String inputString = args[0];

        if (inputString.length() < 4 || inputString.length() > 10) {
            System.err.println("Invalid: Invalid input length");
            return;
        }

        // Perform calculation and print
        permutation(args[0], 0, args[0].length() - 1);

        // Print elapsed time
        System.out.printf("Elapsed time: %.6f ms\n", (System.nanoTime() - start) / 1e6);
    }
}