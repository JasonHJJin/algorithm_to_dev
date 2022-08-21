import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.Buffer;
import java.util.Scanner;
import java.util.ArrayList;

public class largesum {

    public static void main(String[] args) throws IOException {

        try {

            File file = new File("/Users/jasonjin/Desktop/COMS4995/HW1/input10.txt");

            Scanner sc = new Scanner(file);
            // BufferedReader buf = new BufferedReader(new
            // FileReader("/Users/ethanruoff/Documents/Alg to
            // Dev/COMS4995/HW1-Jason/input1.txt"));

            ArrayList<String> lines = new ArrayList<String>();
            StringBuilder sb = new StringBuilder();

            if (!sc.hasNext()) {
                System.out.println("Full sum: " + 0 + "\n" +
                        "First 10 digits: " + 0);
            }

            while (sc.hasNext()) {
                lines.add(String.format("%50s", sc.nextLine()).replace(" ", "0"));
            }

            int count;
            int overflow = 0;
            int carry = 0;

            for (int i = 49; i >= 0; i--) {

                count = 0;
                count += overflow;

                for (int j = 0; j < lines.size(); j++) {

                    count += Integer.valueOf(String.valueOf(lines.get(j).charAt(i)));

                }

                String s = String.valueOf(count);
                sb.insert(0, s.substring(s.length() - 1));

                if (s.length() == 1) {
                    carry = overflow;
                    overflow = 0;
                }

                if (lines.get(49 - i).length() <= 0) {
                    break;
                }

                if (s.substring(0, s.length() - 1).length() > 0) {
                    overflow = Integer.parseInt(s.substring(0, s.length() - 1));
                    carry = overflow;
                }

            }

            if (carry != 0) {
                sb.insert(0, String.valueOf(carry));
            }

            String printResult = sb.toString();

            printResult = printResult.replaceFirst("^0*", "");

            if (printResult.length() < 10) {
                System.out.println("Full sum: " + printResult + "\n" +
                        "First 10 digits: " + printResult);
            } else {

                System.out.println("Full sum: " + printResult + "\n" +
                        "First 10 digits: " + printResult.substring(0, 10));
            }

            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}