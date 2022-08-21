import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class XorDecrypt {

    public static boolean Is_Valid(int[] nums, int[] key) {
        int sum = 0;
        int temp = -1;
        int lastNum = -1;
        String fin = "";

        for (int i = 0; i < nums.length; i++) {
            temp = nums[i] ^ (key[i % 3]);
            if (temp >= 128 || temp < 13) {
                return false;
            }
            if (lastNum == 32 && temp == 32) {
                return false;
            }
            if (lastNum == 33 && temp != 32) {
                return false;
            }
            if (lastNum == 59 && temp != 32) {
                return false;
            }
            if (lastNum == 43 && temp != 32) {
                return false;
            }
            if (temp == 36) {
                return false;
            }
            lastNum = temp;
            fin += (char) temp;
            sum += temp;
        }
        if (lastNum != 46 || temp != 46) {
            return false;
        }
        System.out.println(fin);
        System.out.println(sum);
        return true;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("./p059_cipher.txt");
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            String f = bf.readLine();
            String correct[] = f.split(",");
            int nums[] = new int[correct.length];
            for (int i = 0; i < correct.length; i++) {
                nums[i] = Integer.parseInt(correct[i]);
            }
            for (int i = 101; i <= 102; i++) {
                for (int j = 97; j <= 122; j++) {
                    for (int k = 97; k <= 122; k++) {
                        if (Is_Valid(nums, new int[] { i, j, k })) {
                            System.out.println("success");
                            return;
                        }
                    }
                }
            }
            System.out.println("Darn");

        }
    }

}