// import java.util.Scanner;
// import java.io.*; 

package FinalProject.Andrea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Sudoku {
    public char[][] board;

    public Sudoku(char[][] sud_board) {
        board = sud_board;
        if (solve(0)) {
            System.out.println(toString());
        }
    }

    public boolean solve(int ind) {
        while (ind < 81) {
            int row = ind / 9;
            int col = ind % 9;
            if (board[row][col] != '0') {
                ind++;
                continue;
            }
            for (int n = 1; n <= 9; n++) {
                char c = (char) (n + '0');
                if (safe_num(c, row, col)) {
                    board[row][col] = c;
                    if (solve(ind + 1)) {
                        return true;
                    }
                }
            }
            board[row][col] = '0';
            return false;
        }
        return true;
    }

    public boolean safe_num(char ch, int row, int col) {
        // Check if any values in the row or column are equal
        int r = (row/3)*3;
        int c = (col/3)*3;
        for (int i = 0; i < board.length; i++) {
            // Check if same row
            if (board[row][i] == ch && i != col) {
                return false;
            }
            // Check if same column
            if (board[i][col] == ch && i != row) {
                return false;
            }
            // Check if same cell
            if (board[r+(i/3)][c+(i%3)] == ch && (r+(i/3)) != row && (c+(i%3)) != col) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        // Create the top line
        String mid_cell_double = "\u2550\u2550\u2550";
        String inner_top_cell = mid_cell_double + "\u2564" + mid_cell_double + "\u2564" + mid_cell_double;
        String top_line = "\u2554" + inner_top_cell + "\u2566" + inner_top_cell + "\u2566" + inner_top_cell + "\u2557"
                + "\n";

        // Create the middle line for non cell lines
        String mid_cell_single = "\u2500\u2500\u2500";
        String inner_mid_cell = mid_cell_single + "\u253C" + mid_cell_single + "\u253C" + mid_cell_single;
        String mid_line_non_third = "\u255F" + inner_mid_cell + "\u256B" + inner_mid_cell + "\u256B" + inner_mid_cell
                + "\u2562" + "\n";

        // Create the middle line for cell lines
        String inner_mid_cell_third = mid_cell_double + "\u256A" + mid_cell_double + "\u256A" + mid_cell_double;
        String mid_line_third = "\u2560" + inner_mid_cell_third + "\u256C" + inner_mid_cell_third + "\u256C"
                + inner_mid_cell_third + "\u2563" + "\n";

        String finalString = top_line;
        for (int i = 0; i < board.length; i++) {
            // Add a middle line after each line
            if (i % 3 == 0 && i != 0) {
                finalString += "\u2551\n" + mid_line_third;
            } else if (i != 0) {
                finalString += "\u2551\n" + mid_line_non_third;
            }

            for (int j = 0; j < board[0].length; j++) {
                if (j % 3 == 0) {
                    finalString += "\u2551 " + board[i][j] + " ";
                } else {
                    finalString += "\u2502 " + board[i][j] + " ";
                }
            }
        }
        // Add the final line
        String mid_bottom_cell = mid_cell_double + "\u2567" + mid_cell_double + "\u2567" + mid_cell_double;
        finalString += "\u2551\n\u255A" + mid_bottom_cell + "\u2569" + mid_bottom_cell + "\u2569" + mid_bottom_cell
                + "\u255D";
        return finalString;
    }

    public static void main(String[] args) {
    // char[][] b = { { '5', '3', '0', '0', '7', '0', '0', '0', '0' }, { '6', '0', '0', '1', '9', '5', '0', '0', '0' },
    // { '0', '9', '8', '0', '0', '0', '0', '6', '0' }, { '8', '0', '0', '0', '6', '0', '0', '0', '3' },
    // { '4', '0', '0', '8', '0', '3', '0', '0', '1' }, { '7', '0', '0', '0', '2', '0', '0', '0', '6' },
    // { '0', '6', '0', '0', '0', '0', '2', '8', '0' }, { '0', '0', '0', '4', '1', '9', '0', '0', '5' },
    // { '0', '0', '0', '0', '8', '0', '0', '7', '9' } };
    // Sudoku suds = new Sudoku(b);
    int row = 9, column = 9;
    char[][] theString = new char [row][column];

    File file = new File("D:\\GitHub\\COMS4995\\FinalProject\\Andrea\\sudoku_tests.txt");
    try (Scanner scanner = new Scanner(file)) {
        int count = 9;

        while (scanner.hasNextLine()) {
            count = count + 1;
            // String the_next_line = scanner.nextLine();
            char[] the_next_line = scanner.nextLine().toCharArray();
            // System.out.print(the_next_line);
            // System.out.print(the_next_line.length);
            if (count == 10 ) {
                count = 0;
                Sudoku suds = new Sudoku(theString);   
   
            } else {
                for (int i = 1; i < the_next_line.length+1; i++) {
                    // System.out.print(the_next_line[i-1]);
                    theString[count-1][i-1] = the_next_line[i-1];
                    

                }
                // int a = 10;
                // theString = theString + "\n" + the_next_line;
            }
            Sudoku suds = new Sudoku(theString);   
        }
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    }
}
