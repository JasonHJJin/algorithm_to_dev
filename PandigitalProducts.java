public class PandigitalProducts {
    public static void main(String[] args) {
        long start = System.nanoTime();
        int i = 0;
        int j = 0;
        int prod  = 0;
        int l = 0;
        String comb;
        boolean true_prods[] = new boolean[9999]; //4 digits
        
        while(getLength(i,j,prod).length() <= 9) {
            i++;
            l = 0;
            while(l <= 9) {
                j++;
                prod = i * j;
                comb = getLength(i, j, prod);
                l = comb.length();
                if(l == 9 && ispalin(comb)) {
                    true_prods[prod] = true;
                }
            }
            j = 0;
        }
        long total = 0;
        for (int k = 10; k < true_prods.length; k++) {
            if (true_prods[k] == true) {
                total += k;
            }
        }
        System.out.println(total);
        System.out.printf("Elapsed time: %.6f ms\n", (System.nanoTime() - start) / 1e6);
    }

    public static String getLength(int a, int b, int c) {
        return ("" + a + b + c);
    }

    public static boolean ispalin(String digits) {
        int result = 0;
        int checker;
        for(int i = 0; i<digits.length();i++) {
            checker = 1 << (digits.charAt(i) - 48); // ASCII values ... 48 index for 0
            result = result ^ checker;
        }
        if (result==1022) {
            return true;
        }
        else {
            return false;
        }
    }
}
